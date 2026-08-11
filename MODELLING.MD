## General use rules

There are two user-facing zones. Which one a file belongs to is detected structurally via the import graph — no tags or annotations.

### 1. TwinModel

1. A Twin model has to import `TwinLibrary`.
2. A Twin model can import UserLibraries.
3. A model of a twin starts always by exactly one subclassifing  `Twin` (e.g. `part def Battery :> Twin { … }`)
4. Except using of Calculations each element whas to type,subsett or redfifne a library feature within the twin. Free usages are not considered.
5. We allow no defintions in the model file except the  entry point of point 3.

```sysml
part def Battery :> Twin {

        port p11 :> sensors { //this is considered because it extends sensors
            attribute temp    : TwinReal    :> measurements;
            attribute voltage : TwinReal    :> measurements;
            attribute current : TwinReal    :> measurements;
            attribute plug    : TwinBoolean :> measurements;
            attribute pos     : Position    :> measurements;
        }

        port p12 { // this is ignored
            attribute charge;
        }

}
```

### 2. UserLibrary

1. Only definitions are allowed here — this is where users extend library elements.
2. A package is a UserLibrary when it imports `UserLibrary` (directly, or transitively only other UserLibraries).
3. A UserLibrary may only import `UserLibrary` itself or other UserLibraries — never anything from the base library directly.
4. Definitions are only allowed at package level, never nested inside another def.
5. Inside def bodies only `:>>`, `:>`, and `:Type` are permitted — no nested defs.
6. Feature-chain referencing between definitions is not allowed. model may only impor UserLibraries — nothing else.

## Model elements

The following sections define the supported Digital Twin model elements and, importantly, the access rules between them.

These rules determine which attributes an element may read from and which attributes it may write to.

### Physical Twin

| Element | Description | Binding and access rules |
|---|---|---|
| `sensors : Sensor` | Out-ports of the physical asset. Each sensor port declares its `measurements`, for example `attribute temp : TwinReal :> measurements`. | Measurements are declarations only. Their values originate from the physical device. |
| `actuators : Actuator` | In-ports carrying `commands` toward the physical device, for example `attribute charge : TwinReal :> commands`. | Commands are declarations. They represent values that can be sent to the physical device. |
| `constAttributes` | Twin-level configuration constants. | Literals only, for example `= 100`. |
| `controlUnit : ControlUnit` | State machine running **on the physical device**. States support `entry`, `do`, `exit`, assignment actions, guarded transitions, and custom calculations from a `UserLibrary`. | A control unit may **read sensor attributes, actuator attributes, local attributes, and const attributes**. It may **write only local attributes and actuator attributes**. Access to an actuator attribute may occur through a feature chain. |

For example:

```sysml
state cm1 :> controlUnit {

    attribute maxCharge : TwinReal
        :> local_Attributes = maxCharge;

    state charging :>> states {
        entry assign p12.charge := p11.temp;
        do assign p12.charge := 50;
        exit assign p12.charge := 100;
    }
}
```

In:

```sysml
assign p12.charge := p11.temp;
```

the control unit:

- **reads** the sensor attribute `p11.temp`
- **writes** the actuator attribute `p12.charge`

A control unit cannot write a sensor attribute or a const attribute.

Custom calculations from a `UserLibrary` may be used in expressions and guards where supported.

For example:

```sysml
if All(bools = plug)
then charging;
```

---

### Shadow

The shadow is the database representation of the Digital Twin.

| Element | Description |
|---|---|
| `databases : Database` | Storage backend for Twin data. Supported database definitions include `RelationalDatabase` and `KeyValueDatabase`. `durationInDays` configures retention; `-1` means unlimited retention. |

The shadow is important for the descriptive, predictive, and prescriptive layers because references to persisted Twin data in those layers are resolved against the database rather than directly against the physical device.

---

### Descriptive Model

The descriptive model operates on the current and historical state stored in the shadow.

#### History Queries

| Element | Description | Binding and access rules |
|---|---|---|
| `query : QueryHistory` | Queries historical values from the shadow. Parameters include `twinAttribute`, `since`, `sinceUnit`, `orderBy`, `limit`, and `filterExpression`. The result is exposed through `result[0..*]`. | `twinAttribute` may reference only a **sensor attribute, actuator attribute, or const attribute**. The query reads the history of that attribute from the shadow. |

Example:

```sysml
part temp30 :> queryHistory {
    :>>twinAttribute : TwinReal default p11.temp;
    :>>since default 30;
    :>>result : TwinReal[0..*];
}
```

Here:

```sysml
p11.temp
```

identifies the Twin attribute whose historical values are queried.

The query does **not** read the live sensor directly and does not modify the sensor. It queries the corresponding persisted data from the shadow.

#### Derived Attributes

| Element | Description | Binding and access rules |
|---|---|---|
| `derivedAttributes` | Computed Twin attributes belonging to the descriptive model. | May reference **derived attributes, const attributes, sensor attributes, actuator attributes, and query calls**. References are resolved through the database. Literals and calculation expressions are allowed. A derived attribute may also remain unbound when it is intended to be written by a descriptive state machine. |

Example:

```sysml
attribute soc : TwinReal :> derivedAttributes =
    MULT_real(
        DIV_real(p11.voltage, nominalVoltage),
        100
    );
```

The expression references:

```text
p11.voltage
nominalVoltage
```

These references are resolved through the shadow.

Derived attributes may also consume query results:

```sysml
attribute avgTemp : TwinReal :> derivedAttributes =
    Avg(temp30.result);
```

Here `temp30.result` is the result produced by the history query.

#### Descriptive State Machines

| Element | Description | Binding and access rules |
|---|---|---|
| `descriptiveStateMachine_` | State machine running **in the cloud** over shadow data. | May **read sensor attributes, const attributes, actuator attributes, derived attributes, and query calls**. It may **write only derived attributes**. |

A descriptive state machine therefore has the following access model:

```text
READ:
    sensorAttributes
    constAttributes
    actuatorAttributes
    derivedAttributes
    queryCalls

WRITE:
    derivedAttributes
```

The derived attribute used as a write target must be an **unbound derived attribute of the Twin**.

This allows a descriptive state machine to publish a computed result, such as a state label represented by a `TwinString`.

It may not use its write access to directly command an actuator.

---

### Predictive Model

| Element | Description | Binding and access rules |
|---|---|---|
| `predictiveStrategies : PredictiveStrategy` | Prediction step implemented externally and referenced through `lambdaPath`. | **Inputs** may bind to sensor attributes, derived attributes, and const attributes. These values are resolved through the database and calculation expressions are allowed. **Outputs do not bind to another attribute**; they are pure declarations. |

Example:

```sysml
part consForecast :> predictiveStrategies {

    :>>lambdaPath = "battery/consForecast";

    in attribute avgTemperature : TwinReal
        :> inputs default avgTemp;

    in attribute current : TwinReal
        :> inputs default p11.current;

    attribute predicted : TwinReal
        :> outputs;
}
```

The inputs:

```text
avgTemp
p11.current
```

are read through the shadow.

The output:

```text
predicted
```

does not bind to another Twin attribute.

Predictive outputs are persisted automatically. Each predictive strategy receives its own database table for its outputs.

The predictive layer therefore cannot directly write to an actuator.

---

### Prescriptive Model

| Element | Description | Binding and access rules |
|---|---|---|
| `prescriptiveStrategies : CustomPrescriptiveStrategy` | Decision step implemented externally through `lambdaPath`. It is the **only cloud-side model element allowed to act back on the physical Twin**. | **Inputs** may bind to sensor attributes, derived attributes, const attributes, and output attributes of predictive strategies. **Outputs** may bind to actuator attributes or remain unbound. |

Example:

```sysml
part chargeStrategy :> prescriptiveStrategies {

    in attribute :>> lambdaPath
        default "battery/chargeStrategy";

    attribute :>> condition_
        default LT_real(consForecast.predicted, maxCharge);

    attribute chargeCmd : TwinReal
        :> outputs = p12.charge;
}
```

A prescriptive strategy may consume, among other supported values:

```text
sensor attribute
derived attribute
const attribute
predictive strategy output
```

These inputs are resolved through the database.

A prescriptive output has two possible forms.

**Bound output**

```sysml
attribute chargeCmd : TwinReal
    :> outputs = p12.charge;
```

The output binds to an actuator attribute.

This represents a command sent back to the physical Twin. The value is additionally persisted in the shadow.

**Unbound output**

```sysml
attribute result : TwinReal
    :> outputs;
```

The output is not sent to an actuator.

Instead, it is persisted in the same general manner as predictive outputs, with strategy-specific persistence.

---

## Access Policy Summary

The supported access rules can be summarized as follows:

| Model element | May read | May write / produce |
|---|---|---|
| `controlUnit` | Sensor, actuator, local, const attributes | Local attributes, actuator attributes |
| `queryHistory` | Sensor, actuator, const attribute history | Query result |
| `derivedAttributes` | Derived, const, sensor, actuator attributes and query calls | Own derived value |
| `descriptiveStateMachine_` | Sensor, const, actuator, derived attributes and query calls | Unbound derived attributes |
| `predictiveStrategies` | Sensor, derived, const attributes | Own unbound predictive outputs |
| `prescriptiveStrategies` | Sensor, derived, const attributes and predictive outputs | Own unbound outputs or actuator attributes |

The distinction between the physical and cloud-side control paths is intentional.

The physical control path is:

```text
Sensor
   ↓
Control Unit
   ↓
Actuator
```

The cloud-side analytical and decision path is:

```text
Shadow
   ↓
Descriptive / Predictive processing
   ↓
Prescriptive Strategy
   ↓
Actuator
```

A predictive strategy therefore cannot directly command an actuator. A descriptive state machine cannot directly command an actuator either. On the cloud side, acting back on the physical Twin is the responsibility of the prescriptive model.

## Policy Enforcement Status

These rules define the intended access policies of the modelling language.

**Automatic enforcement of all of these policies is not implemented yet.**

The converter already maps the model structures and references required to represent these relationships, but users should currently not assume that every invalid read or write described above will automatically be rejected.

The documented rules are therefore the modelling contract. Full policy validation and enforcement is still to be implemented.
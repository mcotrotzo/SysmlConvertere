## General use rules

There are two user-facing zones. Which one a file belongs to is detected structurally via the import graph — no tags or
annotations.

### 1. TwinModel

1. A Twin model has to import `TwinLibrary`.
2. A Twin model can import UserLibraries.
3. A model of a twin starts always by exactly one subclassifing `Twin` (e.g. `part def Battery :> Twin { … }`).
4. We allow no definitions in the model file except the entry point of point 3.

The `Twin` a model subclassifies is a composite of up to five optional (`0..1`) layers — `physicalTwin`, `shadow`,
`descriptiveModel`, `predictiveModel`, `prescriptiveModel` — connected by explicit `flow` elements. A model does not
have to use all five layers.

```sysml
part def Battery :> Twin {

    part physicalBattery :>> physicalTwin {

        port p11[30] :> sensors { // considered because it redefines sensors
            attribute temp    : TwinReal    :>> measurements;
            attribute voltage : TwinReal    :>> measurements;
            attribute plug    : TwinBoolean :>> measurements;
            attribute pos     : Position    :>> measurements;
        }

        out port constPort :>> constPort {
            attribute nominalVoltage : TwinReal :>> measurements = 48;
        }

        port p12 :> actuators {
            in attribute charge : TwinReal :>> commands;
        }

        state cm1 :> controlUnit {
            in attribute temp : TwinReal :>> inputs;
            out attribute charge : TwinReal :>> outputs;

            state charging :>> states {
                entry assign charge := temp;
            }
        }

        flow :>physicalFlows from p11.temp to cm1.temp;
    }

    part shadowBattery :>> shadow {
        :>>databases : RelationalDatabase {
            :>>durationInDays default 30;
        }
    }

    flow :>queryFlows from physicalBattery.p11.voltage to descriptiveBattery.soc.voltage {
        :>>limit = 1;
    }

    part descriptiveBattery :>> descriptiveModel {
        action soc :> derivedAttributes {
            in attribute voltage : TwinReal :>> inputs;
            out attribute soc : TwinReal :>> outputs;
            assign soc := MULT_real(voltage, 100);
        }
    }
}
```

A port declared without redefining `sensors`/`actuators`/`constPort` (e.g. `port p12 { attribute charge; }`) is
ignored by the mapper — only redefinitions of the library ports are considered.

### 2. UserLibrary

1. Only definitions are allowed here — this is where users extend library elements.
2. A package is a UserLibrary when it imports `UserLibrary` (directly, or transitively only other UserLibraries).
3. A UserLibrary may only import `UserLibrary` itself or other UserLibraries — never anything from the base library
   directly.
4. Definitions are only allowed at package level, never nested inside another def.
5. Inside def bodies only `:>>`, `:>`, and `:Type` are permitted — no nested defs.
6. Feature-chain referencing between definitions is not allowed. A model may only import UserLibraries — nothing
   else.
7. A calculation/action parameter (any feature with a direction, i.e. `in`/`out`/`inout`) may only specialize
   another calculation/action parameter through **redefinition** (`:>>`) — plain subsetting (`:>`) on a parameter is
   rejected. This applies to every `action def`/`action usage` (custom calculations, control units, strategies,
   derived attributes, ...), not only to queries.
8. An attribute that is only typed through the generic `TwinAttribute` (i.e. never specialized to a scalar Twin
   type, a custom type, or a more specific library feature) cannot be mapped and is rejected — `TwinAttribute` must
   always be specialized.
9. An attribute with neither an explicit type, a subsetting, nor a redefinition ("freestanding") cannot be mapped.

Example of a UserLibrary custom calculation:

```sysml
package PositionThings {
    private import UserLibrary::*;

    attribute def Position :> TwinCustomType {
        attribute x[1] : TwinInteger :> fields;
        attribute y[1] : TwinInteger :> fields;
        attribute z[1] : TwinInteger :> fields;
    }

    calc def AddPosition :> CustomCalculationAction {
        in attribute pos_a[1] :>> inputs : Position;
        in attribute pos_b[1] :>> inputs : Position;
        return pos_c[1] :>> outputs : Position;
        assign pos_c := new Position(
            x = PLUS_int(pos_a.x, pos_b.x),
            y = PLUS_int(pos_a.y, pos_b.y),
            z = PLUS_int(pos_a.z, pos_b.z)
        );
    }
}
```

---

## Model elements

The Digital Twin model is organized into five taxonomy layers, each an optional part of `Twin`:
`PhysicalTwin`, `Shadow`, `DescriptiveModel`, `PredictiveModel`, `PrescriptiveModel`. Every element that can hold
behavior — a control unit, a descriptive state machine, a strategy, a derived attribute, a custom calculation —
specializes the same base, `TwinAction`, which declares `inputs`, `outputs`, and `local_Attributes`.

Access between layers is no longer expressed by letting one layer's actions reference another layer's attributes by
feature chain. It is expressed by two, independently enforced mechanisms:

- **`Role`-based expression validation** — governs what an attribute's own default expression, or an `assign`
  action, is allowed to reference *within the same action/attribute's owning layer*. See
  [Expression role rules](#expression-role-rules) below.
- **`Flow`s** — govern how a value crosses from one attribute to another, including across layers. A `Flow` has an
  explicit `source` and `target` attribute reference and is declared at the point in the model where the two
  layers meet (inside a layer for intra-layer flows, at the `Twin` level for cross-layer flows).

### Physical Twin

| Element                          | Description                                                                                                     |
|-----------------------------------|-------------------------------------------------------------------------------------------------------------------|
| `sensors : Sensor`                | Out-ports of the physical asset. Each sensor port declares its `measurements`, which get `Role.SENSOR`.          |
| `actuators : Actuator`            | In-ports carrying `commands` toward the physical device, which get `Role.ACTUATOR`.                              |
| `constPort : ConstPort`           | Out-port carrying Twin-level configuration constants (`measurements`, `Role.CONST`).                              |
| `controlUnit : ControlUnit[0..*]` | State machine running **on the physical device**. It is a `TwinAction`/`Block`: it has its own `inputs`/`outputs` (`Role.ACTION`) and `local_Attributes` (`Role.LOCAL`). States support `entry`, `do`, `exit`, assignment actions, guarded transitions, and calls into `UserLibrary` calculations. |
| `physicalFlows : PhysicalFlow[0..*]` | Wires a sensor's or the const port's attribute to a control unit's `inputs`, or a control unit's `outputs` to an actuator's `commands`. |

A control unit does not read a sensor or write an actuator directly. It declares its own `inputs`/`outputs`, and a
`physicalFlow` connects the sensor/const/actuator attribute to that input/output:

```sysml
state cm1 :> controlUnit {
    in attribute temp    : TwinReal    :>> inputs;
    in attribute plug    : TwinBoolean :>> inputs;
    in attribute maxCharge : TwinReal  :>> inputs;
    out attribute charge  : TwinReal   :>> outputs;

    state idle :>> states;
    state charging :>> states {
        entry assign charge := temp;
        do    assign charge := 50;
        exit  assign charge := 100;
    }

    transition first idle if plug then charging;
    transition first charging if NOT_bool(plug) then idle;
}

flow :>physicalFlows from p11.temp to cm1.temp;
flow :>physicalFlows from p11.plug to cm1.plug;
flow :>physicalFlows from constPort.maxCharge to cm1.maxCharge;
```

Inside the control unit's own actions (`assign charge := temp;`), both `charge` (role `ACTION`, an output) and
`temp` (role `ACTION`, an input) are the control unit's *own* attributes — not the sensor/actuator directly. This is
what the expression validator enforces (see below): an `assign` target and value may only reference `LOCAL`,
`ACTION`, or `FOR_LOOP_VARIABLE` attributes, never `SENSOR`, `ACTUATOR`, or `CONST` attributes directly.

---

### Shadow

| Element                  | Description                                                                                                                                                                               |
|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `databases : Database[0..*]` | Storage backend for Twin data. Supported database definitions: `RelationalDatabase`, `KeyValueDatabase`. `durationInDays` (a plain, role-less configuration attribute) configures retention; `-1` means unlimited. |

The shadow is important for the descriptive, predictive, and prescriptive layers because their `queryFlows` /
cross-layer flows read persisted Twin data from the shadow rather than from the physical device directly.

```sysml
part shadowBattery :>> shadow {
    :>>databases : RelationalDatabase {
        :>>durationInDays default 30;
    }
}
```

---

### Descriptive Model

| Element                                  | Description                                                                                                                                             |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| `derivedAttributes : TwinAction[0..*]`      | Computed values belonging to the descriptive model. Each is an *action* with its own `inputs`/`outputs` — not a plain bound attribute anymore.          |
| `descriptiveStateMachine_ : DescriptiveStateMachine[0..*]` | State machine running **in the cloud**, over shadow data reaching it through flows. It is a `TwinAction`/`Block` like a control unit. |
| `descriptiveStrategies : Strategy[0..*]`    | Descriptive strategy, `CustomStrategy` or `ExternalStrategy`.                                                                                            |
| `descriptiveFlows : DescriptiveFlow[0..*]`  | Wires the output of one descriptive-model action (derived attribute, strategy, ...) to the input of another, within the descriptive layer.              |

A derived attribute has its own `inputs`, which reach it via `queryFlows` (from the physical layer) or
`descriptiveFlows` (from another descriptive-model action), and computes its `outputs` with an internal `assign`:

```sysml
action soc :> derivedAttributes {
    in attribute voltage : TwinReal :>> inputs;
    in attribute nominalVoltage : TwinReal :>> inputs;
    out attribute soc : TwinReal :>> outputs;

    assign soc := MULT_real(DIV_real(voltage, nominalVoltage), 100);
}

action avgTemp :> derivedAttributes {
    in attribute temps : TwinReal[0..*] :>> inputs;
    out attribute avgTemp : TwinReal :>> outputs;

    assign avgTemp := Avg(temps);
}

flow :>descriptiveFlows from temp30.res to avgTemp.temps;
```

A descriptive state machine's `entry`/`do`/`exit`/transition actions may only assign to its own `LOCAL`/`ACTION`
attributes, exactly like a control unit — it publishes a result on one of its own `outputs`, which downstream flows
can then pick up.

---

### Predictive Model

| Element                                       | Description                                                                                                                                    |
|-------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| `predictiveStrategies : Strategy[0..*]`          | Prediction step, `CustomStrategy` or `ExternalStrategy`.                                                                                        |
| `predictiveFlows : PredictiveFlow[0..*]`         | Wires predictive-model actions to each other, within the predictive layer.                                                                      |

An `ExternalStrategy` implements its logic outside the model:

```sysml
action consForecast : ExternalStrategy :> predictiveStrategies {
    attribute :> strategyType = CustomStrategyType::LAMBDA;
    attribute :>> contentPath = "battery/consForecast";

    in attribute avgTemperature : TwinReal :>> inputs;
    in attribute current : TwinReal :>> inputs;
    out attribute predicted : TwinReal :>> outputs;
}
```

`strategyType` (`CustomStrategyType::CONTAINER` or `CustomStrategyType::LAMBDA`) and `contentPath` are plain,
role-less configuration attributes and therefore must be literals — they cannot reference another attribute.

Predictive strategy inputs are fed by `descriptiveToPredictiveFlows` (declared at the `Twin` level) or by
`queryFlows` directly from the physical layer; outputs reach the prescriptive layer through
`predictiveToPrescriptiveFlows`. A predictive strategy's own body never writes directly to an actuator.

---

### Prescriptive Model

| Element                                          | Description                                                                                                                          |
|-----------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| `prescriptiveStrategies : Strategy[0..*]`            | Decision step, `CustomStrategy` or `ExternalStrategy`. Its `outputs`, once wired forward, are the only cloud-side path that reaches back onto the physical Twin. |
| `prescriptiveFlows : PrescriptiveFlow[0..*]`         | Wires prescriptive-model actions to each other, within the prescriptive layer.                                                       |

A `CustomStrategy` expresses its decision logic directly, with the same `assign`/`if`/`while`/`for` actions any
other `TwinAction` uses:

```sysml
action chargeStrategyInternal : CustomStrategy :> prescriptiveStrategies {
    in attribute predictedCurrent : TwinReal :>> inputs;
    in attribute maxCharge : TwinReal :>> inputs;
    out attribute chargeCmd : TwinReal :>> outputs;

    if LT_real(predictedCurrent, maxCharge) {
        assign chargeCmd := predictedCurrent;
    }
}
```

The prescriptive strategy's `chargeCmd` output is not bound to an actuator attribute inside the strategy. Instead,
a `prescriptiveToPhysicalFlow`, declared at the `Twin` level, connects it to the actuator:

```sysml
flow :>prescriptiveToPhysicalFlows from prescriptiveBattery.chargeStrategyInternal.chargeCmd to physicalBattery.p12.charge;
```

An output that is not wired forward by any flow is simply not consumed anywhere — there is no separate
"bound vs. unbound output" distinction anymore; whether an output is used is entirely a function of whether a
`Flow` targets it.

---

## Cross-layer wiring: Flows

All data movement between layers — what used to be either a feature-chain reference resolved "through the shadow"
or a separate query-part usage — is now an explicit `Flow`, declared at the point where the two layers meet:

| Flow                              | Declared in           | Connects                                          |
|-------------------------------------|------------------------|----------------------------------------------------|
| `physicalFlows`                     | `PhysicalTwin`         | sensor/const attribute → control unit input, control unit output → actuator command |
| `queryFlows`                        | `Twin`                 | any physical attribute → any cloud-layer action's input (this is the query mechanism) |
| `descriptiveFlows`                  | `DescriptiveModel`     | descriptive action output → descriptive action input |
| `predictiveFlows`                   | `PredictiveModel`      | predictive action output → predictive action input |
| `prescriptiveFlows`                 | `PrescriptiveModel`    | prescriptive action output → prescriptive action input |
| `descriptiveToPredictiveFlows`      | `Twin`                 | descriptive action output → predictive action input |
| `descriptiveToPrescriptiveFlows`    | `Twin`                 | descriptive action output → prescriptive action input |
| `predictiveToPrescriptiveFlows`     | `Twin`                 | predictive action output → prescriptive action input |
| `prescriptiveToPhysicalFlows`       | `Twin`                 | prescriptive action output → actuator command      |

`queryFlows` additionally carries history-query parameters — `since`, `sinceUnit`, `orderBy`, `limit` — all plain,
role-less configuration attributes (literals only):

```sysml
flow :>queryFlows from physicalBattery.p11.temp to descriptiveBattery.temp30.temps {
    :>>limit = 30;
}
```

This replaces the previous `QueryHistory`/`GroupedHistoryQuery` part usages entirely. There is no separate
"grouped" query type anymore — the shape of the historical result follows from the multiplicity of the target
input attribute.

`TriggerConfiguration` (time-based / event-based trigger configuration) no longer exists as a modeling element.

---

## Expression role rules

Every `TwinAttribute` carries a `Set<Role>` describing what kind of attribute it is:
`SENSOR`, `ACTUATOR`, `CONST`, `LOCAL`, `ACTION`, `CUSTOM_TYPE_MEMBER`, `FOR_LOOP_VARIABLE`, or no role at all (a
plain configuration attribute).

- `measurements` on a `Sensor` → `SENSOR`
- `commands` on an `Actuator` → `ACTUATOR`
- `measurements` on a `ConstPort` → `CONST`
- `inputs`/`outputs` on any `TwinAction` (control unit, state machine, strategy, derived attribute, custom
  calculation) → `ACTION`
- `local_Attributes` on any `TwinAction` → `LOCAL`
- `fields` on a custom type → `CUSTOM_TYPE_MEMBER`
- the loop variable of a `for` action → `FOR_LOOP_VARIABLE`
- everything else that isn't specialized as one of the above (e.g. `durationInDays`, `url`, `broker`, `topic`,
  `contentPath`, `strategyType`, `since`, `sinceUnit`, `orderBy`, `limit`) → no role (plain config)

These roles are checked automatically while mapping the model:

**An attribute's own default expression** (`attribute x : T = expr;` / `:>> role = expr;`):

| Attribute's role(s)        | Its expression may reference                                   |
|-----------------------------|-------------------------------------------------------------------|
| `CONST`                      | only other `CONST` attributes                                    |
| *(no role — plain config)*   | nothing — literals / constructors / calculations over literals only |
| `LOCAL`                      | only `LOCAL`, `ACTION`, or `FOR_LOOP_VARIABLE` attributes         |
| `SENSOR` / `ACTUATOR` / `CUSTOM_TYPE_MEMBER` | must not have an expression at all — pure declaration |

**An `assign target := value;` action:**

- `target` must have role `LOCAL`, `ACTION`, or `FOR_LOOP_VARIABLE`. You can never assign directly to a `SENSOR`,
  `ACTUATOR`, or `CONST` attribute — those only ever change through a `Flow`.
- `value` may only reference `LOCAL`, `ACTION`, or `FOR_LOOP_VARIABLE` attributes — same rule as the `LOCAL` case
  above.

For a feature-chain expression, the check is applied to the **union of roles across the whole chain** — it passes
as soon as *any* link in the chain carries an allowed role, not only the last one. For example, inside a `LOCAL`
attribute's expression, `posTest12.x` is valid: `posTest12` is an action input (`ACTION`), which alone satisfies the
rule, even though `x` itself is a `CUSTOM_TYPE_MEMBER` (which on its own would not be allowed).

```sysml
in attribute posTest12 : Position :>> inputs;
attribute test12 : TwinReal :> local_Attributes = Avg(posTest12.x);
```

---

## Access Policy Summary

| Model element                | May reference in its own expression / assign             | May be written by                              |
|-------------------------------|-------------------------------------------------------------|---------------------------------------------------|
| `SENSOR` attribute             | n/a — never has an expression                               | the physical device only (never assigned in-model) |
| `ACTUATOR` attribute           | n/a — never has an expression                               | a `physicalFlow` or a `prescriptiveToPhysicalFlow` |
| `CONST` attribute              | other `CONST` attributes                                    | its own literal default only                      |
| plain config attribute (no role) | nothing (literals only)                                  | its own literal default only                      |
| `ACTION`/`LOCAL`/`FOR_LOOP_VARIABLE` attribute (an action's `inputs`/`outputs`/`local_Attributes`/loop variable) | other `ACTION`/`LOCAL`/`FOR_LOOP_VARIABLE` attributes in the same action | an `assign` inside the same action, or an incoming `Flow` (for `inputs`) |

The physical control path is:

```text
Sensor / ConstPort
    ↓ physicalFlow
Control Unit (own inputs → assign → own outputs)
    ↓ physicalFlow
Actuator
```

The cloud-side analytical and decision path is:

```text
Physical attribute
    ↓ queryFlow (via Shadow)
Descriptive action (own inputs → assign → own outputs)
    ↓ descriptiveFlow / descriptiveToPredictiveFlow / descriptiveToPrescriptiveFlow
Predictive action (own inputs → assign/external → own outputs)
    ↓ predictiveFlow / predictiveToPrescriptiveFlow
Prescriptive action (own inputs → assign/external → own outputs)
    ↓ prescriptiveToPhysicalFlow
Actuator
```

A predictive action therefore cannot directly command an actuator, and a descriptive action cannot directly command
an actuator either — reaching the actuator always requires a `prescriptiveToPhysicalFlow` out of the prescriptive
layer. This is now structural (every attribute access is either inside one action's own `inputs`/`outputs`/`local`,
or crosses a layer through an explicit `Flow`), not just a documented convention.

## Policy Enforcement Status

The following are actively enforced while mapping a model (an invalid model fails to map, not a post-hoc lint):

- The `TwinModel`/`UserLibrary` structural rules described under [General use rules](#general-use-rules) (exactly
  one `Twin` subclassification per model file, import-graph zone detection, definitions only at package level in a
  `UserLibrary`, no feature-chain referencing between UserLibrary definitions).
- Calculation/action parameters may only specialize by redefinition (`CalcInputOutputRules`).
- An attribute must be explicitly typed, subset, or redefined — a "freestanding" attribute, or one typed only
  through the generic `TwinAttribute`, is rejected (`TwinAttributeHasToSpecialiced`).
- Multiplicity rules on library-derived features (lower/upper bound checks).
- The [expression role rules](#expression-role-rules) above (`ExpressionRoleValidator`) — for every attribute
  expression and every `assign` action.


# SysML Twin Mapper

`sysml-twin-mapper` maps supported SysML v2 Digital Twin models to a
Java object model.

The mapper loads the Twin model, the user library, the bundled Digital
Twin library, and the SysML standard libraries. The public result is a
`TwinDataBase` containing the mapped model elements.

For modeling rules and SysML examples, see `MODELING.md`.

## Requirements

* Java 21
* Maven
* Access to the GitHub Packages repository

## Maven

Configure GitHub Packages in `~/.m2/settings.xml`:

```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>GITHUB_USERNAME</username>
            <password>GITHUB_TOKEN</password>
        </server>
    </servers>
</settings>
```

A classic Personal Access Token normally needs `read:packages`. The
account must also have access to the package if it is private.

Add the repository:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/mcotrotzo/SysmlConvertere</url>
    </repository>
</repositories>
```

Add the dependency:

```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>sysml-twin-mapper</artifactId>
    <version>GITHUB-release version</version>
</dependency>
```

The bundled Digital Twin and SysML libraries do not have to be
downloaded separately.

## Usage

```java
MapperService mapperService = new MapperService("PathToYourModelDirectory");

TwinDataBase twinDataBase = mapperService.map();
```

The directory contains the Twin model and the user library files. All
mapped classes live under `org.example.Mapping.NewVersion2.Abstract`.

Errors:

* SysML issues (errors and warnings) stop loading with an
  `IllegalStateException`.
* A violated modeling rule (multiplicity, roles, flows) makes `map()`
  throw an `IllegalArgumentException`; semantic rule violations start
  with `Semantic exception:`.

# TwinDataBase

```java
List<AbstractModel<?>> all = twinDataBase.getAll();
Optional<AbstractModel<?>> element = twinDataBase.getById(id);
Optional<SensorUsage> sensor = twinDataBase.getById(id, SensorUsage.class);
List<SensorUsage> sensors = twinDataBase.getByType(SensorUsage.class);
```

`getByType` includes subclasses: `getByType(TwinFlowUsage.class)`
returns every flow, `getByType(TwinAttributeUsage.class)` every
attribute.

The result also contains library elements and inherited copies. Filter
them explicitly when only the elements written in the model are needed:

```java
List<SensorUsage> own = twinDataBase.getByType(SensorUsage.class).stream()
        .filter(sensor -> !sensor.isInherited() && !sensor.isLibrary())
        .toList();
```

# Mapped elements

## AbstractModel

Every mapped element extends `AbstractModel`:

```java
element.getId();          // deterministic UUID
element.getName();
element.getParent();      // Optional, empty for top-level elements
element.isInherited();
element.isLibrary();
element.getSysmlElement();
```

## Identity, copies and inheritance

An element is mapped once per owner. The id is derived from the SysML
path of the element and, below an owner, from the owner's id
(`owner.id | path`). The same element therefore gives

* the same instance below the same owner, and
* a separate instance (a copy) below another owner.

Example: `port p13 :> p11` inherits `temp` from `p11`. `p11.getMeasurements()`
holds the original `temp`, `p13.getMeasurements()` its own copy with
`getParent() == p13`. Likewise `part battery : Battery` holds copies of
everything defined in `Battery`.

`isInherited()` is `true` for a copy: the element does not lie inside
its owner in the model, or its owner is a copy itself.

Definitions are mapped without owner and exist once. Top-level elements
have no parent; packages are not mapped.

Library definitions are mapped, but they do not fill their slots.
Library usages (e.g. the `sensors` feature of `PhysicalTwin`) are not
mapped.

## Usage and Definition

```java
usage.getDefinition();       // typed, e.g. SensorDefinition for a SensorUsage
usage.getDirection();        // Optional<Direction>
usage.getMultiplicity();     // ElemWithMult
usage.getSpecializations();  // usages this usage subsets, e.g. p13 -> [p11]

definition.getSuperDefinitions(); // direct super definitions
```

Standard-library types (e.g. `ScalarValues::Real`) are not set as the
definition of a usage.

# Twin structure and taxonomies

`TwinDefinition` / `TwinUsage`:

```java
twin.getPhysicalTwin();
twin.getShadow();
twin.getDescriptiveModel();
twin.getPredictiveModel();
twin.getPrescriptiveModel();

twin.getQueryFlows();
twin.getDescriptiveToPredictiveFlows();
twin.getDescriptiveToPrescriptiveFlows();
twin.getPredictiveToPrescriptiveFlows();
twin.getPrescriptiveToPhysicalFlows();
```

`FederationTwinDefinition` exposes `getFederationFlows()`.

## PhysicalTwin

```java
physicalTwin.getSensors();
physicalTwin.getActuators();
physicalTwin.getControlUnits();
physicalTwin.getConstPorts();
physicalTwin.getPhysicalFlows();
```

## Shadow

```java
shadow.getDatabases();
```

## DescriptiveModel

```java
descriptiveModel.getDerivedAttributes();
descriptiveModel.getDescriptiveStateMachines();
descriptiveModel.getDescriptiveStrategies();
descriptiveModel.getDescriptiveFlows();
```

## PredictiveModel / PrescriptiveModel

```java
predictiveModel.getPredictiveStrategies();
predictiveModel.getPredictiveFlows();

prescriptiveModel.getPrescriptiveStrategies();
prescriptiveModel.getPrescriptiveFlows();
```

# Ports and protocols

All ports (`SensorUsage`, `ActuatorUsage`, `ConstPortUsage`):

```java
port.getProtocol();   // Optional
port.getDeviceKey();
```

```java
sensor.getMeasurements();
actuator.getCommands();
constPort.getMeasurements();
```

Protocols: `MqttProtocolUsage` (`getBroker()`, `getTopic()`) and
`HttpProtocolUsage` (`getUrl()`).

# Attributes

Scalar attributes: `TwinAttributeRealUsage`, `TwinAttributeIntegerUsage`,
`TwinAttributeBooleanUsage`, `TwinAttributeStringUsage`, with their
definitions `TwinAttributeRealDefinition` etc. `TwinBoolean` is an alias
of `ScalarValues::Boolean`, so `new TwinBoolean(true)` constructs a
`TwinAttributeBooleanDefinition`.

Custom types: `CustomTypeUsage` / `CustomTypeDefinition` with
`getFields()`.

Every `TwinAttributeUsage` exposes:

```java
attribute.getExpression();  // Optional
attribute.getRoles();       // Set<Role>
attribute.getTaxonomy();    // closest taxonomy above the attribute
```

Roles (`org.example.Mapping.Role`) are set by the slot the attribute
is in:

| Slot | Role |
|---|---|
| sensor `measurements` | `SENSOR` |
| actuator `commands` | `ACTUATOR` |
| const port `measurements` | `CONST` |
| action `inputs` / `outputs` | `ACTION` |
| action `local_Attributes` | `LOCAL` |
| custom type `fields` | `CUSTOM_TYPE_MEMBER` |
| for-loop variable | `FOR_LOOP_VARIABLE` |

Attributes in no such slot have no role (configuration). The rules
built on the roles are described in `MODELING.md`.

# Actions

Blocks (`TwinActionBlockUsage`, strategies, state machines, calculation
definitions):

```java
block.getInputs();
block.getOutputs();
block.getLocalAttributes();
block.getActions();
block.getSuccessions();
```

| Class | Getters |
|---|---|
| `TwinAssignmentUsage` | `getReferent()`, `getValue()` |
| `TwinIfElseUsage` | `getCondition()`, `getThenAction()`, `getElseAction()` |
| `TwinForLoopUsage` | `getLoopVariable()`, `getCollection()`, `getBody()` |
| `TwinWhileUsage` | `getCondition()`, `getUntil()`, `getBody()` |
| `TwinSuccessionUsage` | `getTargets()` |
| `TwinTransitionUsage` | `getSource()`, `getTarget()`, `getGuard()`, `getEffectAction()` |

`TwinStateMachineUsage` / `TwinStateMachineDefinition` additionally
expose `getStates()`, `getTransitions()`, `getEntryAction()`,
`getDoAction()` and `getExitAction()`.

# Expressions

All expressions extend `TwinExpressionUsage`.

| Class | Getters |
|---|---|
| `TwinLiteralRealUsage`, `...IntegerUsage`, `...BooleanUsage`, `...StringUsage` | `getValue()` |
| `TwinCalculationUsage` | `getInvokeType()`, `getArguments()` |
| `TwinConstructorUsage` | `getConstructorType()`, `getArguments()` |
| `TwinFeatureReferenceUsage`, `TwinFeatureChainUsage` | `getTarget()` |

`getInvokeType()` is a `BaseFunctionDefinition` for standard functions
(`getCore().getFunctionKind()` gives the `BaseFunctionKind`) or a
`CustomCalculationDefinition` for user calculations.

## References

A reference resolves to exactly one mapped instance, not just to the
referenced SysML feature. `getTarget()` of `pos_b.x.y` is the `y` held
by the `x` copy held by `pos_b`, i.e. the same id as the `y` in
`getFields()` of that `x`.

A reference inside a copy points to the copy (`maxCharge` below
`battery.physicalBattery.constPort` references the copied
`nominalVoltage`), and a reference to an inherited feature points to the
copy below the inheriting element (`voltage` in `soc2 :> soc`).

# Flows

All flows extend `TwinFlowUsage`:

```java
flow.getSource();            // TwinAttributeUsage, resolved like a reference
flow.getTarget();
flow.getSourceTaxonomy();    // taxonomy class the source has to lie in
flow.getTargetTaxonomy();
```

Kinds: `PhysicalFlowUsage`, `DescriptiveFlowUsage`,
`PredictiveFlowUsage`, `PrescriptiveFlowUsage`, `QueryFlowUsage`,
`DescriptiveToPredictiveFlowUsage`, `DescriptiveToPrescriptiveFlowUsage`,
`PredictiveToPrescriptiveFlowUsage`, `PrescriptiveToPhysicalFlowUsage`,
`FederationFlowUsage`.

`QueryFlowUsage` exposes `getSince()`, `getSinceUnit()`, `getOrderBy()`
and `getLimit()` (each `Optional`). `FederationFlowUsage` exposes
`getLinkType()`.

All flow definitions are mapped as `TwinFlowDefinition`.

# Strategies

`TwinStrategyUsage` is a block. `CustomStrategyUsage` adds nothing,
`ExternalStrategyUsage` exposes `getContentPath()` and
`getStrategyType()`.

# Databases

`RelationalDatabaseUsage` and `KeyValueDatabaseUsage` expose
`getDurationInDays()`.

# Enums

Enum attributes (`EnumCustomStrategyTypeUsage`, `EnumFederationLinkUsage`,
`EnumOrderByUsage`, `EnumTimeUnitUsage`) expose `getValue()` as an
`Optional` of the enum in `org.example.Mapping.TwinEnumPackage`. All enum
definitions are mapped as `EnumDefinition`.
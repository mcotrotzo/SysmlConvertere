# SysML Twin Mapper

`sysml-twin-mapper` maps supported SysML v2 Digital Twin models to a
Java object model.

The mapper loads the Twin model, the user library, the bundled Digital
Twin library, and the SysML standard libraries. The public result is a
`TwinDataBase` containing mapped model elements and contextual
`Compartment` objects.

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
MapperService mapperService = new MapperService(
        "PathToYourTwinDirectory",
        "PathToYourCustomLibraryDirectory"
);

TwinDataBase twinDataBase = mapperService.map();
```

Application code should normally use the interfaces under
`org.example.Mapping.Interfaces`.

# TwinDataBase

## Get mapped objects

```java
Set<Sensors<Usage>> sensors =
        twinDataBase.get(Sensors.class, Usage.class);
```

The current API is:

```java
<T extends Model<Z>, Z extends TypeKindNamespace>
Set<T> get(Class<? super T> type, Class<Z> typeKind)
```

`getAll()` returns every registered object, including `Compartment`
objects and library elements:

```java
Set<Model<?>> all = twinDataBase.getAll();
```

Every `Model` exposes `isLibraryElement()`. Therefore application code
can filter library objects explicitly:

```java
Set<Model<?>> modelOnly = twinDataBase.getAll().stream()
        .filter(element -> !element.isLibraryElement())
        .collect(java.util.stream.Collectors.toSet());
```

The same property can be used after a typed `get(...)` call.

## Lookup and references

```java
Model<?> model = twinDataBase.get(id);
```

```java
TwinAttribute<?> attribute =
        twinDataBase.getByReference(reference, TwinAttribute.class);
```

`Reference<T>` exposes both `getReferent()` and `getTargetId()`.

## Available mapped classes

```java
Set<Class<Model<?>>> types = twinDataBase.getAllTypes();
```

## Specialization children

```java
List<Model<Usage>> children =
        twinDataBase.getSpecializationChildren(element);
```

The method returns mapped usage elements whose SysML type specializes
the supplied usage.

## Multiplicity

```java
ElemWithMult multiplicity = twinDataBase.getMultiplicity(element);
```

This exposes the multiplicity range of a mapped usage.

## Compartments

A mapped `Type<Usage>` represents the SysML usage itself. A
`Compartment<T>` represents that usage in one concrete mapped
parent/context.

For example, a feature `x` may be the same mapped attribute definition
while occurring under a particular `Position` usage. The compartment
preserves that contextual occurrence.

```java
Compartment<TwinAttribute<Usage>> compartment =
        twinDataBase.getCompartment(parent, attribute);
```

A compartment exposes:

```java
compartment.getElement();
compartment.getParent();
compartment.isInherited();
```

Its identity is based on the pair `(parent, element)`. This matters for
nested feature chains where the final attribute alone is not enough to
identify the concrete occurrence.

# Core interfaces

## Model

All mapped public objects derive from `Model`.

```java
model.getId();
model.getDeterministicId();
model.getName();
model.getKind();
model.getParent();
model.path();
model.getTaxonomy();
model.isLibraryElement();
```

`getId()` is the runtime identity. `getDeterministicId()` is derived
from the SysML path and is stable for the same model structure.

## Type

`Type<T>` extends `Model<T>` and represents mapped SysML types/usages.
It provides type relationships such as specialization and the definition
of a usage.

## Package and libraries

The namespace API contains `Package`, `UserLibrary`, `TwinDefLibrary`,
`DTLibrary`, `NameSpace`, and `Import`.

`UserLibrary` exposes mapped user definitions, including custom
calculations, base Twin attribute definitions, custom types, and
query-flow definitions.

# Twin structure and taxonomies

`Twin` exposes the top-level taxonomy compartments:

```java
twin.getPhysicalTwin();
twin.getShadow();
twin.getDescriptiveModel();
twin.getPredictiveModel();
twin.getPrescriptiveModel();
```

It also exposes the cross-taxonomy flows:

```java
twin.getQueryFlows();
twin.getDescriptiveToPredictiveFlows();
twin.getDescriptiveToPrescriptiveFlows();
twin.getPredictiveToPrescriptiveFlows();
twin.getPrescriptiveToPhysicalFlows();
```

The taxonomy interfaces are `Taxonomy`, `PhysicalTaxonomy`,
`CloudTwinTaxonomy`, `DescriptiveTaxonomy`, `PredictiveTaxonomy`,
`PrescriptiveTaxonomy`, and `ShadowTaxonomy`.

## PhysicalTwin

```java
physicalTwin.getSensors();
physicalTwin.getActuators();
physicalTwin.getControlUnits();
physicalTwin.getPhysicalFlows();
physicalTwin.getConstPort();
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

`derivedAttributes` are actions in the current model, not standalone
Twin attributes.

## PredictiveModel

```java
predictiveModel.getPredictiveStrategies();
predictiveModel.getPredictiveFlows();
```

## PrescriptiveModel

```java
prescriptiveModel.getPrescriptiveStrategies();
prescriptiveModel.getPrescriptiveFlows();
```

# Ports and attributes

`TwinPort` exposes:

```java
port.getProtocol();
port.getAttributes();
```

`Sensors`, `Actuators`, and `ConstPort` specialize `TwinPort`.

Protocols currently include `HTTPProtocol` and `MQTTProtocol`. HTTP
exposes URL compartments; MQTT exposes topic and broker compartments.

`TwinAttribute` exposes:

```java
attribute.getDirection();
attribute.getExpression();
attribute.getRoles();
```

Directions are represented by `Direction`. Current roles are:

```text
SENSOR
ACTUATOR
CONST
LOCAL
ACTION
CUSTOM_TYPE_MEMBER
FOR_LOOP_VARIABLE
```

Sensor attributes receive `SENSOR`, actuator attributes receive
`ACTUATOR`, const-port attributes receive `CONST`, action inputs and
outputs receive `ACTION`, action-local attributes receive `LOCAL`,
custom-type fields receive `CUSTOM_TYPE_MEMBER`, and for-loop variables
receive `FOR_LOOP_VARIABLE`.

Scalar interfaces are `TwinBaseBoolean`, `TwinBaseInteger`,
`TwinBaseReal`, and `TwinBaseString`. `CustomType` exposes its field
compartments.

# Actions

`Action<T>` is the base action interface. `Block<T>` adds:

```java
block.getInputs();
block.getOutputs();
block.localAttributes();
block.getActions();
block.getSuccessions();
```

Action inputs and outputs are therefore part of the action itself. Local
attributes are also scoped to the action.

Supported action interfaces are:

* `Assignment`: target reference and value expression.
* `IfElse`: condition, then-action compartment, optional else-action
  compartment.
* `ForLoop`: loop-variable compartment, collection expression, body
  compartment.
* `WhileLoop`: condition, until expression, optional body compartment.
* `Succession`: ordered/referenced action list.
* `Transition`: source, target, guard expressions, optional effect
  action.
* `Block`: nested actions, successions, inputs, outputs, and local
  attributes.

State machines expose nested states, transitions, and entry/do/exit
action compartments.

# Expressions

The public expression API represents expressions structurally rather
than as source strings.

Supported public forms include literals, calculation invocations,
constructor calls, and feature references.

`Calculation` exposes the called `Function` and its argument
expressions. `ConstructorCall` exposes the constructed custom attribute
definition and constructor arguments.

## FeatureReference and feature chains

`FeatureReference` exposes two related views:

```java
reference.getChain();
reference.getAsCompartment();
```

`getChain()` preserves the semantic chain as references to mapped usage
elements. For:

```sysml
pos_b.x.y
```

the chain retains the traversed elements rather than flattening the
expression to a string.

`getAsCompartment()` resolves the reference to the contextual final
occurrence. This is important because the same mapped element can occur
under different parents.

For a chain, the mapper resolves compartments pairwise. Conceptually:

```text
(parent A, feature B) -> compartment B
(feature B, feature C) -> compartment C
```

The final compartment therefore identifies the last feature in the
context established by the chain.

A direct feature reference also resolves to its own compartment.

# ExpressionRoleValidator rules

The mapper validates attribute expressions and assignment expressions
using attribute roles.

For an attribute expression:

* A `CONST` attribute may reference only attributes whose effective
  referenced roles include `CONST`.
* An attribute with no role is treated as configuration data for this
  validation and may not contain feature references.
* A `LOCAL` attribute may reference only `LOCAL`, `ACTION`, or
  `FOR_LOOP_VARIABLE` attributes.
* Attributes with other role combinations must not have an expression.

For assignments:

* The assignment target must have `LOCAL`, `ACTION`, or
  `FOR_LOOP_VARIABLE`.
* The assignment value may reference only `LOCAL`, `ACTION`, or
  `FOR_LOOP_VARIABLE` attributes.

For a feature chain, the validator collects roles from every referenced
element in `FeatureReference.getChain()`. The rule is applied to that
combined role set. Invocation arguments are validated recursively, so
references inside nested calculation/invocation arguments are checked as
well.

# Flows

`Flow<T>` exposes:

```java
flow.sourceContexts();
flow.targetContexts();
flow.getSource();
flow.getTarget();
```

A flow connects an output-capable source attribute to an input-capable
target attribute.

The mapper validates:

* the source direction is `OUT` or `INOUT`;
* the target direction is `IN` or `INOUT`;
* source and target belong to the taxonomies required by the flow
  type;
* the source endpoint type is compatible with the target endpoint
  type.

The supported flow types are:

* `PhysicalFlow`: Physical -> Physical
* `DescriptiveFlow`: Descriptive -> Descriptive
* `PredictiveFlow`: Predictive -> Predictive
* `PrescriptiveFlow`: Prescriptive -> Prescriptive
* `QueryFlow`: Physical -> Cloud
* `DescriptiveToPredictiveFlow`: Descriptive -> Predictive
* `DescriptiveToPrescriptiveFlow`: Descriptive -> Prescriptive
* `PredictiveToPrescriptiveFlow`: Predictive -> Prescriptive
* `PrescriptiveToPhysicalFlow`: Prescriptive -> Physical

`QueryFlow` additionally exposes optional `since`, `sinceUnit`,
`orderBy`, and `limit` compartments. Each of these slots may occur at
most once.

# Strategies

`Strategy<T>` extends `Block<T>`, so strategies use the same action
input/output/local-attribute model.

`CustomStrategy` adds no public fields beyond `Strategy`.

`ExternalStrategy` exposes:

```java
strategy.getContentPath();
strategy.getStrategyType();
```

There is no trigger-configuration API in the current public interfaces.

# Databases

`Database` exposes:

```java
database.getDurationInDays();
```

Current database interfaces are `RelationalDatabase` and
`KeyValueDatabase`.

# Enums and functions

`TwinEnum` exposes its string representation. `EnumAttribute<T>` exposes
an optional enum value. Public enum attribute types include
`EnumOrderBy`, `EnumTimeUnit`, and `CustomStrategyType`.

Functions are represented by `Function`. `BaseFunction` additionally
exposes `BaseFunctionKind`. `CustomCalculation` represents user-defined
calculations.

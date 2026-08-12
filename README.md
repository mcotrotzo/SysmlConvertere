# SysML Twin Mapper

`sysml-twin-mapper` maps SysML v2 Digital Twin models to a Java object model.

The library parses a Twin model together with a user-defined SysML library and exposes the mapped model through Java interfaces and `TwinDataBase`.

For the supported SysML modeling conventions, see [`MODELING.md`](MODELING.md).

---

# Requirements

- Java 21
- Maven
- Access to the GitHub Packages repository

---

# Maven

## 1. GitHub Packages authentication

Add the following server configuration to:

```text
~/.m2/settings.xml
```

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

The GitHub token requires permission to read the package.

For a classic Personal Access Token this is normally:

```text
read:packages
```

The GitHub account must also have access to the repository/package when it is private.

---

## 2. Repository

Add the GitHub Packages repository to your project's `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/mcotrotzo/SysmlConvertere</url>
    </repository>
</repositories>
```

The repository ID must match the ID used in `settings.xml`:

```text
github
```

---

## 3. Dependency

Add the mapper:

```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>sysml-twin-mapper</artifactId>
    <version>GITHUB-release version</version>
</dependency>
```

After that the project can be built normally:

```bash
mvn clean compile
```

The SysML standard library and the Digital Twin library required internally by the mapper are bundled with the mapper library. Users do not have to download them manually.

---

# Usage

Create a `MapperService` with:

1. the directory containing the Twin model;
2. the directory containing the user's custom SysML library.

```java
MapperService mapperService = new MapperService(
        "PathToYourTwinDirectory",
        "PathToYourCustomLibraryDirectory"
);

TwinDataBase twinDataBase = mapperService.map();
```

The returned `TwinDataBase` contains the mapped Java representation of the model.

---

# MapperService

`MapperService` is the main entry point into the library.

```java
MapperService mapperService = new MapperService(
        twinModelPath,
        userLibraryPath
);

TwinDataBase database = mapperService.map();
```

The mapper loads the required libraries, parses the supplied SysML model and maps supported SysML elements to the public Java model interfaces.

Application code should normally work with these interfaces rather than the internal mapper implementation classes.

---

# TwinDataBase

`TwinDataBase` provides access to all mapped model elements.

## Get all elements of a type

```java
Set<Sensors> sensors = twinDataBase.get(Sensors.class);
```

The method:

```java
<T extends Model> Set<T> get(Class<T> type)
```

returns all mapped objects implementing the requested interface.

Examples:

```java
Set<Twin> twins =
        twinDataBase.get(Twin.class);

Set<Sensors> sensors =
        twinDataBase.get(Sensors.class);

Set<Actuators> actuators =
        twinDataBase.get(Actuators.class);
```

---

## Get an element by ID

```java
Model element = twinDataBase.get(id);
```

or with the expected type:

```java
Twin twin = twinDataBase.get(id);
```

---

## Resolve a Reference

References can be resolved through the database:

```java
TwinAttribute attribute =
        twinDataBase.getByReference(
                reference,
                TwinAttribute.class
        );
```

References themselves also retain their referenced model relationship, so consumers can work directly with the reference API when appropriate instead of manually reconstructing references from names.

---

## Get mapped types

```java
Set<Class<Model>> types =
        twinDataBase.getAllTypes();
```

This can be used when a consumer wants to inspect which Java model types occur in the mapped model.

---

## Specialization children

```java
List<Model> children =
        twinDataBase.getSpecializationChildren(element);
```

This returns the direct specialization children of a mapped usage.

Consider:

```sysml
port p11[30] :> sensors;
port p13[23] :> p11;
port p14[2]  :> p13, p11;
port p15[1]  :> p14;
```

The direct relationships are:

```text
p11
├── p13
└── p14

p13
└── p14

p14
└── p15

p15
└── []
```

`p15` is therefore not returned directly for `p11`, because another specialization lies between them.

---

## Multiplicity

```java
ElemWithMult multiplicity =
        twinDataBase.getMultiplicity(element);
```

For:

```sysml
port p11[30] :> sensors;
```

the multiplicity contains:

```text
lowerBound = 30
upperBound = 30
```

Multiplicity is important when interpreting feature-chain expressions such as:

```sysml
p11.temp
```

because the multiplicity of the base feature contributes to the semantic multiplicity represented by that expression.

---

# Core Model Interface

Mapped model objects implement `Model`.

`Model` provides the common information shared by mapped elements, including their identity, name, kind and parent relationship.

Typical access looks like:

```java
model.getId();
model.getName();
model.getKind();
model.getParent();
```

The parent describes structural containment in the mapped model.

---

# Twin

`Twin` represents a mapped Digital Twin definition.

A Twin exposes the different Digital Twin components belonging to it, including sensors, actuators, attributes, state machines, strategies, queries and databases.

Typical access includes:

```java
twin.getSensors();
twin.getActuators();

twin.getConstAttributes();
twin.getDerivedAttributes();

twin.getControlUnits();

twin.getQueriesHistory();
twin.getGroupQueriesHistory();

twin.getDescriptiveStateMachines();

twin.getDescriptiveStrategies();
twin.getPredictiveStrategies();
twin.getPrescriptiveStrategies();

twin.getDatabases();
```

These relationships correspond to the Digital Twin library features specialized by the SysML model.

---

# Sensors

`Sensors` represents a mapped sensor port.

For example:

```sysml
port p11 :> sensors {
    attribute temp    : TwinReal    :> measurements;
    attribute voltage : TwinReal    :> measurements;
    attribute plug    : TwinBoolean :> measurements;
}
```

The mapped sensor exposes its measurement attributes.

```java
Sensors sensor = ...;

sensor.getMeasurements();
```

Each measurement is represented as a mapped Twin attribute.

---

# Actuators

`Actuators` represents an actuator port.

```sysml
port p12 :> actuators {
    attribute charge : TwinReal :> commands;
}
```

The actuator exposes its command attributes:

```java
actuator.getCommands();
```

---

# Twin Attributes

Twin attributes represent values in the Digital Twin model.

The mapper supports scalar Twin types such as:

```text
TwinReal
TwinInteger
TwinString
TwinBoolean
```

as well as user-defined custom Twin types.

Attributes can occur as:

- sensor measurements;
- actuator commands;
- constant attributes;
- derived attributes;
- state-machine-local attributes;
- strategy inputs and outputs;
- calculation inputs and outputs;
- query values.

An attribute can also contain a mapped value expression.

---

# Custom Types

Structured user-defined values can be modeled through custom Twin types.

For example:

```sysml
attribute def Position :> TwinCustomType {
    attribute x : TwinInteger :> fields;
    attribute y : TwinInteger :> fields;
    attribute z : TwinInteger :> fields;
}
```

The mapped custom type exposes its fields rather than reducing the value to a string representation.

This allows downstream applications to inspect the structure of custom values.

---

# Expressions

Expressions are represented as mapped objects.

The mapper distinguishes between different expression forms instead of storing arbitrary SysML expressions as source-code strings.

Supported mapped expression categories include:

- literals;
- collections;
- references;
- feature chains;
- constructor calls;
- calculation calls.

For example:

```sysml
attribute maxCharge : TwinReal
    :> constAttributes = 100;
```

contains a literal expression.

```sysml
attribute position : Position
    :> constAttributes =
        new Position(x = 10, y = 20, z = 30);
```

contains a constructor expression.

```sysml
attribute soc : TwinReal
    :> derivedAttributes =
        MULT_real(
            DIV_real(p11.voltage, nominalVoltage),
            100
        );
```

contains calculation expressions.

---

# References

References preserve relationships between mapped model elements.

For example:

```sysml
maxCharge
```

is a direct reference.

A feature-chain expression such as:

```sysml
p11.temp
```

represents traversal from `p11` to its `temp` feature.

The mapper does not flatten this relationship into the string `"p11.temp"`.

This means downstream applications can follow the actual mapped model relationships.

References can either be inspected through the reference API itself or resolved through `TwinDataBase`.

---

# Calculations

Calculation invocations retain:

- a reference to the called calculation;
- their mapped argument expressions.

For example:

```sysml
DIV_real(p11.voltage, nominalVoltage)
```

is represented structurally.

The called calculation is therefore represented as a model reference rather than merely storing its textual name.

Arguments are mapped expressions and can themselves contain references, feature chains, constructors or nested calculations.

---

# Queries

The mapper supports two different history-query structures.

## QueryHistory

A normal history query returns one flat history collection.

Example:

```sysml
part temp30 :> queryHistory {
    :>>twinAttribute default p11.temp;
    :>>since default 30;
    :>>result : TwinReal[0..*];
}
```

The type of the result corresponds to the type of the queried Twin attribute.

If the queried expression crosses a usage with multiplicity, the resulting histories are represented as one flat result collection.

For example:

```sysml
port p11[30] :> sensors {
    attribute temp : TwinReal :> measurements;
}
```

then:

```sysml
p11.temp
```

represents `temp` across the instances represented by `p11`.

A normal `QueryHistory` returns those historical values as one flat result:

```text
p11 instance 1 ─┐
p11 instance 2 ─┤
p11 instance 3 ─┤
...              ├──> TwinReal[0..*]
p11 instance 30 ─┘
```

---

## GroupedHistoryQuery

`GroupedHistoryQuery` preserves the separation caused by the multiplicity of the referenced base feature.

For:

```sysml
port p11[30] :> sensors {
    attribute pos : Position :> measurements;
}
```

the expression:

```sysml
p11.pos
```

addresses `pos` across the represented `p11` instances.

A grouped history query returns `QueryResult` objects so those histories remain separated.

A query result type can for example be defined as:

```sysml
attribute def PositionQueryResult :> QueryResult {
    :>>result : Position[0..*];
}
```

and a grouped query as:

```sysml
part def PositionHistory :> GroupedHistoryQuery;
```

Usage:

```sysml
part positionHistory : PositionHistory :> groupedQueryHistory {
    :>>twinAttribute default p11.pos;
    :>>since default 30;
    :>>result : PositionQueryResult[0..*];
}
```

Conceptually:

```text
p11 instance 1
└── QueryResult
    └── Position[0..*]

p11 instance 2
└── QueryResult
    └── Position[0..*]

...

p11 instance 30
└── QueryResult
    └── Position[0..*]
```

The distinction is therefore:

```text
QueryHistory
    T[0..*]

GroupedHistoryQuery
    QueryResult[0..*]
        └── T[0..*]
```

See `MODELING.md` for the SysML modeling rules.

---

# State Machines

The mapper exposes control units and descriptive state machines.

Mapped states may contain:

- nested states;
- entry actions;
- do actions;
- exit actions;
- transitions;
- other mapped behavior.

Transitions retain references to their source and target states as well as their mapped guard expression.

---

# Actions

Mapped behavior includes actions such as:

```sysml
assign p12.charge := 50;
```
We allow:
+ assignment actions;
+ for loops;
+ while loops;
+ if else
+ successions
---

# Strategies

The mapper supports:

- descriptive strategies;
- predictive strategies;
- prescriptive strategies.

Strategies expose their mapped inputs, outputs and Lambda path.

Depending on the strategy type they may additionally expose conditions and trigger configurations.

---

# Trigger Configurations

Trigger configurations describe when strategy or state-machine behavior should execute.

Supported configurations include time-based and event-based configurations.

A time-based configuration contains values such as interval and time unit.

An event-based configuration can reference triggering attributes and its change behavior.

---

# Databases

Database configuration belonging to the Twin is exposed through the database interfaces.

The converter currently maps relational and key-value database configurations.

For example:

```sysml
:>>databases : RelationalDatabase {
    :>>durationInDays default 30;
}
```


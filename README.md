# Maven

## 1. GitHub Packages authentication

Add the following to `~/.m2/settings.xml`:

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

The GitHub token requires `read:packages` permission.

## 2. Repository

Add the GitHub Packages repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/mcotrotzo/SysmlConvertere</url>
    </repository>
</repositories>
```

## 3. Dependency

Add the library as a dependency:

```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>sysml-twin-mapper</artifactId>
    <version>1.0.0</version>
</dependency>
```


# Usage
```java
MapperService mapperService = new MapperService("PathToYourTwinDirectory","PathToYourCustomLibraryDirectory");
TwinDataBase twinDataBase = mapperService.map();
```
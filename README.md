# License #
Please see the file called LICENSE.

## How to use

#### Gradle


```groovy
allprojects {
    repositories {
        url  "https://dl.bintray.com/javasabr/maven" 
    }
}

dependencies {
    compile 'com.spaceshift:rlib.fx:4.2.1-Final'
}
```

    
#### Maven


```xml
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-javasabr-maven</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/javasabr/maven</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.spaceshift</groupId>
    <artifactId>rlib.fx</artifactId>
    <version>4.2.1-Final</version>
</dependency>
```

- Reference: https://howtodoinjava.com/spring-boot2/sb-multi-module-maven-project/

```shell
mvn archetype:generate -DgroupId=com.howtodoinjava \
                       -DartifactId=HelloWorldApp \
                       -DarchetypeArtifactId=maven-archetype-quickstart \
                       -DinteractiveMode=false
# provide pom.xml information
# remove src folder
# comment out of <modules />

cd HelloWorldApp
  
mvn archetype:generate -DgroupId=com.howtodoinjava \
                       -DartifactId=HelloWorldApp-ear \
                       -DarchetypeArtifactId=maven-archetype-quickstart \
                       -DinteractiveMode=false
# provide pom.xml information

mvn archetype:generate -DgroupId=com.howtodoinjava \
                       -DartifactId=HelloWorldApp-service \
                       -DarchetypeArtifactId=maven-archetype-quickstart \
                       -DinteractiveMode=false
# provide pom.xml information
  
mvn archetype:generate -DgroupId=com.howtodoinjava \
                       -DartifactId=HelloWorldApp-rws \
                       -DarchetypeArtifactId=maven-archetype-webapp \
                       -DinteractiveMode=false
# provide pom.xml information

# un-comment out <modules />

mvn clean install
```

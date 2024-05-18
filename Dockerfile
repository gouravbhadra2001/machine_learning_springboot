FROM openjdk:22
EXPOSE 8080
ADD target/machine_learning.jar machine_learning.jar
ENTRYPOINT [ "java", "-jar", "/machine_learning.jar" ]

FROM openjdk
ADD target/micro-users-0.0.1-SNAPSHOT.jar  micro-users-0.0.1-SNAPSHOT.jar
EXPOSE 8001

ENTRYPOINT ["java" , "-jar" , "micro-users-0.0.1-SNAPSHOT.jar"]
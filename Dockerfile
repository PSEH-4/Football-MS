FROM openjdk:8-jdk-alpine
ADD target/Football-MS-0.0.1-SNAPSHOT.jar Football-MS-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/Football-MS-0.0.1-SNAPSHOT.jar"]
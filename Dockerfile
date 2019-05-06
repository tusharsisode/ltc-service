FROM openjdk:8-jdk-alpine

ADD target/LTCAWSLambdaService*.jar LTCApp.jar

ENTRYPOINT ["java", "-jar", "LTCApp.jar" ]

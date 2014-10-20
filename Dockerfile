FROM dockerfile/java:oracle-java8 

RUN apt-get update && apt-get install -y maven

WORKDIR /code

ADD . /code

RUN ["mvn", "install"]

EXPOSE 8080

CMD ["java", "-jar", "target/todoapp-1.0-SNAPSHOT.jar"]
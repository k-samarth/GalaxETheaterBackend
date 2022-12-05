From openjdk:8
copy target/Theatre-0.0.1-SNAPSHOT.jar Theatre-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","Theatre-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081

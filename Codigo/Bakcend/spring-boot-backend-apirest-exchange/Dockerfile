FROM openjdk:8
VOLUME /tmp
EXPOSE 8181
ADD ./target/spring-boot-backend-apirest-exchange-0.0.1-SNAPSHOT.jar backend-apirest.jar
ENTRYPOINT ["java","-jar","/backend-apirest.jar"]
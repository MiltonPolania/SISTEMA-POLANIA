FROM amazoncorretto:17.0.10-alpine-jdk

COPY target/ServicioInyectores-2-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "Java","-jar","app.jar" ]
FROM openjdk:17
COPY "./target/Notas-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8098
ENTRYPOINT [ "java", "jar", "app.jar" ]
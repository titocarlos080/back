# Usa una imagen base de OpenJDK para Spring Boot
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/back-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Configura el comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

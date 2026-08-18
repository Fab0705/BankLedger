# Etapa 1: Construcción (Builder)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
# Copiamos los archivos de configuración de Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
# Descargamos las dependencias (se cachea si el pom.xml no cambia)
RUN ./mvnw dependency:go-offline
# Copiamos el código fuente y compilamos
COPY src src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen Final Ligera (Runtime)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos SOLO el .jar generado en la etapa anterior
COPY --from=builder /app/target/*.jar app.jar
# Exponemos el puerto
EXPOSE 8080
# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
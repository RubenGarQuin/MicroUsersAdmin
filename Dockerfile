# ---- Fase de Construcción ----
FROM eclipse-temurin:21-jdk as builder
WORKDIR /app

# Copia solo los archivos necesarios para descargar dependencias
COPY pom.xml .
COPY .mvn/ .mvn
COPY mvnw .

# Descarga dependencias (cachea esta capa)
RUN ./mvnw dependency:go-offline

# Copia el resto
COPY src ./src

# Empaca la aplicación
RUN ./mvnw package -DskipTests

# ---- Fase de Ejecución ----
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

RUN adduser --system --no-create-home appuser && chown appuser /app
USER appuser

# Copia el JAR desde la fase de construcción
COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
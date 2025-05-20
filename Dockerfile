# ---- Metadatos de la imagen ----
LABEL maintainer="TuNombre <tu@email.com>"
LABEL version="1.0"
LABEL description="Microservicio de gestión de usuarios"

# ---- Fase de Construcción (JDK necesario) ----
FROM eclipse-temurin:21-jdk as builder  
WORKDIR /app  
COPY .mvn/ .mvn  
COPY mvnw pom.xml ./  
RUN ./mvnw dependency:go-offline  
COPY src ./src  
RUN ./mvnw package -DskipTests  # Genera el .jar



# ---- Fase de Ejecución (Solo JRE) ----  
FROM eclipse-temurin:21-jre-jammy  
WORKDIR /app  

# Crear usuario no-root (seguridad)  
RUN adduser --system --no-create-home appuser && chown appuser /app  
USER appuser  

# Copiar solo el .jar desde la fase de construcción  
COPY --from=builder /app/target/com.wend.micro.login-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080  
ENTRYPOINT ["java", "-jar", "app.jar"]  
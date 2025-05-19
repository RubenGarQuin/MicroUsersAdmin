
FROM maven:3.9.6-eclipse-temurin-21 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Run stage (Solo JRE + usuario no-root)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
RUN adduser --system --no-create-home appuser && chown appuser /app
USER appuser  
COPY --from=builder /app/target/microservicio-*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# ---------- Etapa de build ----------
FROM gradle:8.10.2-jdk21-alpine AS builder
WORKDIR /app
# Copiamos Gradle wrapper y archivos de configuración primero para cache
COPY gradlew settings.gradle.kts build.gradle.kts ./ 
COPY gradle ./gradle
# Damos permiso de ejecución al wrapper (por si faltara en Git)
RUN chmod +x gradlew
# Copiamos el código
COPY src ./src
# Construimos el JAR (sin tests para acelerar)
RUN ./gradlew clean build -x test

# ---------- Etapa de runtime ----------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el JAR compilado y lo dejamos con nombre fijo
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar /app/app.jar
# Render inyecta PORT; Spring lo toma de application.yml
ENV JAVA_OPTS=""
EXPOSE 8080
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

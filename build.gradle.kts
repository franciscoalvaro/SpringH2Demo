plugins {
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25" // abre clases para proxys de Spring
    kotlin("plugin.jpa") version "1.9.25"    // genera no-arg ctor para JPA
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // o 17 si prefieres
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    runtimeOnly("com.h2database:h2")

    // ¡IMPRESCINDIBLE!
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Swagger/OpenAPI (opcional pero útil)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    // Actuator (opcional)
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

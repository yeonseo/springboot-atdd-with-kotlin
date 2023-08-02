import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.3.72"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // handlebars
    implementation("pl.allegro.tech.boot:handlebars-spring-boot-starter:0.3.0")

    // log
    implementation("net.rakugakibox.spring.boot:logback-access-spring-boot-starter:2.7.1")
    implementation("io.github.microutils:kotlin-logging:1.8.3")

    // jgraph
    implementation("org.jgrapht:jgrapht-core:1.5.1")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.20-RC")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.20-RC")

    // Kafka dependencies
    // Spring for Apache Kafka
    implementation("org.springframework.kafka:spring-kafka")
    // Spring for Apache Kafka - Test Support
    testImplementation("org.springframework.kafka:spring-kafka-test")


    runtimeOnly("com.h2database:h2")

    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

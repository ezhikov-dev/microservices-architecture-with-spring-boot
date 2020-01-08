import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.61"

    base
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false

    id("org.springframework.boot") version "2.2.2.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

allprojects {

    group = "dev.ezhikov"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "io.spring.dependency-management")

    tasks {

        withType(KotlinCompile::class).configureEach {
            apply(plugin = "org.jetbrains.kotlin.plugin.spring")
            apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        withType(Test::class).configureEach {
            useJUnitPlatform()
        }

        dependencyManagement {

            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:2.2.2.RELEASE") {
                    bomProperty("kotlin.version", "1.3.61")
                }
            }

            dependencies {
                dependency("org.springframework.boot:spring-boot-starter-actuator:2.2.2.RELEASE")
                dependency("org.springframework.boot:spring-boot-starter-data-jpa:2.2.2.RELEASE")
                dependency("org.springframework.boot:spring-boot-starter-security:2.2.2.RELEASE")
                dependency("org.springframework.boot:spring-boot-starter-web:2.2.2.RELEASE")
                dependency("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
                dependency("org.jetbrains.kotlin:kotlin-reflect:1.3.61")
                dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.61")
                dependency("org.springframework.boot:spring-boot-devtools:2.2.2.RELEASE")
                dependency("mysql:mysql-connector-java:8.0.18")
                dependency("org.springframework.boot:spring-boot-starter-test:2.2.2.RELEASE")
                dependency("org.springframework.security:spring-security-test:5.2.1.RELEASE")
            }
        }
    }
}
plugins {
	base
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation(kotlin("reflect"))
	implementation(project(":backus:core"))
	implementation(project(":dal:api"))
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	runtimeOnly(project(":web-ui"))
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

val artifactName = "${project.name}.jar"

springBoot {
	buildInfo {
		properties {
			artifact = artifactName
			version = project.version as String
			name = project.name
		}
	}
}

tasks {
	processResources {
		from("$projectDir/src/main/resources") {
			into("static")
		}
	}

	bootJar {
		archiveFileName.set(artifactName)
		exclude("**/*template.yml")
		launchScript()
	}
}

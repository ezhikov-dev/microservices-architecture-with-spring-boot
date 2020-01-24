plugins {
	base
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
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
	api(project(":dal:core"))
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-web")
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

	bootJar {
		archiveFileName.set(artifactName)
		exclude("**/*template.yml")
		launchScript()
	}
}


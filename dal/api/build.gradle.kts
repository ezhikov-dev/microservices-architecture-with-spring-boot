plugins {
	base
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation(kotlin("reflect"))
	implementation("org.springframework.boot:spring-boot-starter-web")
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
		enabled = false
	}

	jar {
		enabled = true
	}
}
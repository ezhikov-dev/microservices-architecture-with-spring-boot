import com.moowork.gradle.node.npm.NpmTask

plugins {
    base
    id("com.github.node-gradle.node")
}

node {
    version = "13.6.0"
    npmVersion = "6.13.6"
    download = true
}

val packageTask = tasks.register<Jar>("package_static_resources") {
    dependsOn("build_web_ui")
    archiveBaseName.set("web-ui")
    destinationDirectory.set(File("$projectDir/web-ui"))
    from("build") {
        into("static")
    }
}

val config = configurations.create("npmResources")
configurations.default.get().extendsFrom(config)

artifacts.add("npmResources", packageTask.get().archiveFile){
    type = "jar"
    builtBy(packageTask)
}

project.tasks.assemble.get().dependsOn(packageTask)

tasks.register<Delete>("clean_web_ui") {
    delete("build")
}

tasks.register<Delete>("distclean_web_ui") {
    delete("node")
    delete("node_modules")
    delete("build")
}

tasks.register<NpmTask>("install_npm_web_ui") {
    inputs.file("package.json").withPathSensitivity(PathSensitivity.RELATIVE)
    outputs.dir("$buildDir/node_modules")
    setNpmCommand("install")
}

tasks.register<NpmTask>("build_web_ui") {
    dependsOn("install_npm_web_ui")

    inputs.file("package-lock.json").withPathSensitivity(PathSensitivity.RELATIVE)
    inputs.dir("src").withPathSensitivity(PathSensitivity.RELATIVE)
    outputs.dir("$buildDir/build")
    outputs.cacheIf { true }

    setNpmCommand("run", "build")
}

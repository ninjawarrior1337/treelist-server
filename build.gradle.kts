/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.jooby:jooby-gradle-plugin:2.0.2")
        classpath("com.github.jengelman.gradle.plugins:shadow:5.1.0")
    }
}

apply(plugin = "com.github.johnrengelman.shadow")
apply(plugin = "jooby")
apply(plugin = "application")

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm") version "1.3.41"

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()

    maven (
        url = "https://dl.bintray.com/ijabz/maven"
    )
}

task("resolveDependencies") {
    doLast {
        project.rootProject.allprojects.forEach { subProject ->
            subProject.buildscript.configurations.filter {
                it.isCanBeResolved
            }.forEach { configuration ->
                configuration.resolve()
            }
            subProject.configurations.filter {
                it.isCanBeResolved
            }.forEach { configuration ->
                configuration.resolve()
            }
            }
        }
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0-RC")

    implementation("io.jooby:jooby-netty:2.0.3")

    implementation("com.google.code.gson:gson:2.8.5")

    implementation("net.jthink:jaudiotagger:2.2.3")

    implementation("io.github.cdimascio:java-dotenv:5.1.1")


    implementation("org.slf4j:slf4j-simple:2.0.0-alpha0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application.
    mainClassName = "treelist.server.AppKt"
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}

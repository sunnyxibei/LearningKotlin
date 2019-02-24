import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.mobvoi"
version = "1.0-SNAPSHOT"

plugins {
    application
    java
    kotlin("jvm") version "1.3.21"
}

application {
    mainClassName = "kotlin/MainKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    testImplementation(mapOf("group" to "junit", "name" to "junit", "version" to "4.12"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
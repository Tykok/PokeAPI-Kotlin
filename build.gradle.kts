import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifact = "pokeapi"
val projectName = "PokeApi"
val ossrhUsername: String by project
val ossrhPassword: String by project

description = "PokeApi is a simple library you can use to make request to get data about Pokémon."
group = "fr.tykok"
version = "1.0-SNAPSHOT"

plugins {
    `java-library`
    `maven-publish`
    java
    kotlin("jvm") version "1.7.10"
    application
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    withSourcesJar()
    withJavadocJar()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("$buildDir/publishing/$version")
            name = "PokeApi"
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

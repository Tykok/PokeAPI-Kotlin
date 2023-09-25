import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifact = "pokeapi"
val projectName = "PokeApi"
val projectDocUrl = "https://tykok.github.io/PokeAPI-Kotlin/"
val sonarSnapshotUri = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
val sonarReleaseUri = "https://s01.oss.sonatype.org/content/repositories/releases/"

description = "PokeApi is a simple library you can use to make request to get data about Pokémon."
group = "fr.tykok"
version = "0.0.3"

fun getUriSonar(): String = if (version.toString().endsWith("SNAPSHOT")) {
    sonarSnapshotUri
} else {
    sonarReleaseUri
}

plugins {
    `java-library`
    `maven-publish`
    java
    kotlin("jvm") version "1.7.10"
    application
    signing
    id("net.researchgate.release") version "3.0.2"
    id("org.jetbrains.dokka") version "1.9.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
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

tasks.create("getProjectVersion") {
    doLast {
        logger.quiet("VERSION: $version")
    }
}
tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-docs")
}

tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

project.tasks.named<Jar>("javadocJar") {
    from(tasks.named("dokkaJavadoc"))
}

task<Exec>("mkdocs-serve") {
    commandLine("mkdocs", "serve", "--config-file", "docs/mkdocs.yml")
}

task<Exec>("mkdocs-build") {
    commandLine("mkdocs", "build", "--config-file", "docs/mkdocs.yml")
}

application {
    mainClass.set("PokeApi")
}

signing {
    useGpgCmd()
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
        create<MavenPublication>("mavenJava") {
            pom {
                name.set(projectName)
                description.set(description)
                url.set(projectDocUrl)
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/Tykok/PokeAPI-Kotlin/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("Tykok")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            url = uri(getUriSonar())
            name = projectName
            group = group
            version = version.toString()
            description = description
            credentials {
                username = project.findProperty("ossrh.username") as String?
                    ?: System.getenv("OSSRH_USERNAME")
                password = project.findProperty("ossrh.password") as String?
                    ?: System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}

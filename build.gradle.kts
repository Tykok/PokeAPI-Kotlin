import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val artifact = "pokeapi"
val projectName = "PokeApi"
val projectDocUrl = "https://tykok.github.io/PokeAPI-Kotlin/"
val projectUrl = "https://github.com/Tykok/PokeAPI-Kotlin"
val mavenCentralPublishURI = uri("https://central.sonatype.com/api/v1/publisher/deployments/download/")

description = "PokeApi is a simple library you can use to make request to get data about Pokémon."
group = "fr.tykok"
version = "1.0.0"

plugins {
    alias(libs.plugins.netResearchgateRelease)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ktlint)
    `java-library`
    `maven-publish`
    signing
    java
    kotlin("jvm") version "2.0.0"
    application
    jacoco
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
    implementation(libs.okhttp)
    implementation(libs.jackson)
    implementation(libs.gson)

    testImplementation(platform(libs.junitBom))
    testImplementation(libs.junitJupiter)
    testRuntimeOnly(libs.junitPlatform)
    testImplementation(libs.mockk)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

kotlin {
    compilerOptions {
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
        jvmTarget.set(JvmTarget.JVM_17)
    }
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
    mainClass.set("fr.tykok.pokeapi.PokeApi")
}

sourceSets {
    test {
        kotlin {
            srcDir("src/test/kotlin")
        }
    }
}

signing {
    useGpgCmd()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["kotlin"])

            groupId = groupId
            artifactId = artifactId
            version = version

            pom {
                name.set(projectName)
                url.set(projectUrl)

                licenses {
                    license {
                        name.set("MIT License")
                    }
                }

                developers {
                    developer {
                        name.set("Tykok")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            url = mavenCentralPublishURI

            credentials {
                username = project.findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME")
                password = project.findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}

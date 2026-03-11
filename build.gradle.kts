import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

val artifact = "pokeapi"
val projectName = "PokeApi"
val projectDocUrl = "https://tykok.github.io/PokeAPI-Kotlin/"
val projectUrl = "https://github.com/Tykok/PokeAPI-Kotlin"

description = "PokeApi is a simple library you can use to make request to get data about Pokémon."
group = "fr.tykok"
version = "2.0.0"

val localProperties = Properties()
val localPropertiesFile = rootProject.file("gradle-local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use(localProperties::load)
    localProperties.forEach { (key, value) ->
        val propertyName = key.toString()
        val propertyValue = value.toString()
        if (!project.hasProperty(propertyName)) {
            project.extensions.extraProperties.set(propertyName, propertyValue)
        }

        // Make values available as true Gradle project properties for plugins that
        // resolve them via ProviderFactory#gradleProperty(...)
        val gradleSystemPropertyKey = "org.gradle.project.$propertyName"
        if (System.getProperty(gradleSystemPropertyKey).isNullOrBlank()) {
            System.setProperty(gradleSystemPropertyKey, propertyValue)
        }
    }
}

if (!project.hasProperty("signingInMemoryKey")) {
    val signingInMemoryKeyFile = project.findProperty("signingInMemoryKeyFile")?.toString()
    if (!signingInMemoryKeyFile.isNullOrBlank()) {
        project.extensions.extraProperties.set(
            "signingInMemoryKey",
            file(signingInMemoryKeyFile).readText()
        )
    }
}

plugins {
    kotlin("jvm") version "2.1.0"

    `java-library`
    `maven-publish`
    application
    jacoco

    alias(libs.plugins.netResearchgateRelease)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ktlint)

    id("com.vanniktech.maven.publish") version "0.33.0"
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(libs.okhttp)
    implementation(libs.jackson)

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
    jvmToolchain(17)
    compilerOptions {
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_1)
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

tasks.create("getProjectVersion") {
    doLast {
        logger.quiet("VERSION: $version")
    }
}

dokka {
    moduleName.set(projectName)
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        failOnWarning.set(true)
    }
    dokkaSourceSets.main {
        // includes.from("README.md")
        sourceLink {
            localDirectory.set(file("src/main/kotlin"))
            remoteUrl("https://test.com")
        }
    }

    pluginsConfiguration.html {
        // customStyleSheets.from("styles.css")
        customAssets.from("docs/mkdocs-markdown/img/project_img.png")
        footerMessage.set("Tykok")
    }
}

task<Exec>("mkdocs-serve") {
    commandLine("mkdocs", "serve", "--config-file", "docs/mkdocs.yml")
}

task<Exec>("mkdocs-build") {
    commandLine("mkdocs", "build", "--config-file", "docs/mkdocs.yml")
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = false)
    signAllPublications()
    coordinates(groupId = group.toString(), artifactId = artifact, version = version.toString())

    pom {
        name.set(projectName)
        description.set(project.description)
        url.set(projectUrl)

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("tykok")
                name.set("Tykok")
            }
        }

        scm {
            url.set(projectUrl)
            connection.set("scm:git:$projectUrl.git")
            developerConnection.set("scm:git:ssh://git@github.com/Tykok/PokeAPI-Kotlin.git")
        }
    }
}

extensions.configure<SigningExtension>("signing") {
    fun prop(name: String): String? = findProperty(name)?.toString()?.takeIf { it.isNotBlank() }

    val inMemoryKey =
        prop("signingInMemoryKey")
            ?: prop("signingInMemoryKeyFile")?.let { file(it).readText() }
    val keyPassword = prop("signingInMemoryKeyPassword") ?: prop("signing.password")
    val keyId = prop("signingInMemoryKeyId")

    if (!inMemoryKey.isNullOrBlank()) {
        if (!keyId.isNullOrBlank()) {
            useInMemoryPgpKeys(keyId, inMemoryKey, keyPassword)
        } else {
            useInMemoryPgpKeys(inMemoryKey, keyPassword)
        }
    }
}

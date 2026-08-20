import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.Base64
import java.util.Properties

val artifact = "pokeapi"
val projectName = "PokeApi"
val projectDocUrl = "https://tykok.github.io/PokeAPI-Kotlin/"
val projectUrl = "https://github.com/Tykok/PokeAPI-Kotlin"
val sonatypeBaseUrl = "https://central.sonatype.com/api/v1/"
val publishedRegex = Regex("\"published\"\\s*:\\s*(true|false)")

description = "PokeApi is a simple library you can use to make request to get data about Pokémon."
group = "fr.tykok"

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
        project.extensions.extraProperties.set("signingInMemoryKey", file(signingInMemoryKeyFile).readText())
    }
}

plugins {
    kotlin("jvm") version "2.1.0"

    `java-library`
    `maven-publish`
    jacoco

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
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

// Published artifacts must be byte-for-byte reproducible: without this the jars
// embed build timestamps and a rebuild of the same commit produces a different file.
tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to projectName,
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "Tykok",
            "Documentation-URL" to projectDocUrl
        )
    }
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_1)
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

// The Central Portal bearer token is base64("username:password"), so the publishing credentials
// already configured for `mavenPublishing` are enough - no extra secret to rotate.
val centralUsername =
    providers
        .gradleProperty("mavenCentralUsername")
        .orElse(providers.environmentVariable("ORG_GRADLE_PROJECT_mavenCentralUsername"))
val centralPassword =
    providers
        .gradleProperty("mavenCentralPassword")
        .orElse(providers.environmentVariable("ORG_GRADLE_PROJECT_mavenCentralPassword"))

tasks.register("getProjectVersion") {
    val projectVersion = project.version.toString()
    doLast {
        logger.quiet("VERSION: $projectVersion")
    }
}

tasks.register("isPublishedVersion") {
    val namespace = project.group.toString()
    val artifactId = artifact
    val projectVersion = project.version.toString()
    val username = centralUsername
    val password = centralPassword

    doLast {
        val user = username.orNull
        val pass = password.orNull
        if (user.isNullOrBlank() || pass.isNullOrBlank()) {
            throw GradleException(
                "Missing Central Portal credentials: set mavenCentralUsername and mavenCentralPassword " +
                    "(properties, or the ORG_GRADLE_PROJECT_* environment variables)."
            )
        }

        logger.info("Checking if $namespace:$artifactId:$projectVersion is published on Maven Central...")
        val bearer = Base64.getEncoder().encodeToString("$user:$pass".toByteArray())
        val request =
            HttpRequest
                .newBuilder()
                .uri(
                    URI.create(
                        "${sonatypeBaseUrl}publisher/published" +
                            "?namespace=$namespace&name=$artifactId&version=$projectVersion"
                    )
                ).header("Authorization", "Bearer $bearer")
                .GET()
                .build()

        val response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
        val statusCode = response.statusCode()
        val isPublished =
            when {
                statusCode == 200 -> publishedRegex.find(response.body())?.groupValues?.get(1) == "true"
                // Central answers 404 when those coordinates were never published at all.
                statusCode == 404 -> false
                else ->
                    throw GradleException(
                        "Cannot check publication status of $namespace:$artifactId:$projectVersion " +
                            "(HTTP $statusCode): ${response.body()}"
                    )
            }

        logger.quiet("IS_PUBLISHED: $isPublished")
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
            remoteUrl("$projectUrl/blob/main/src/main/kotlin")
            remoteLineSuffix.set("#L")
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
        inceptionYear.set("2022")

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
                url.set("https://github.com/Tykok")
            }
        }

        issueManagement {
            system.set("GitHub Issues")
            url.set("$projectUrl/issues")
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

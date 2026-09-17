// binary-compatibility-validator has no plugin marker on the Gradle Plugin Portal, so its id
// cannot resolve directly through `plugins { alias(...) }`; map it to its real Maven Central
// coordinates so the alias in build.gradle.kts still works.
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.jetbrains.kotlinx.binary-compatibility-validator") {
                useModule("org.jetbrains.kotlinx:binary-compatibility-validator:${requested.version}")
            }
        }
    }
}

rootProject.name = "pokeapi"

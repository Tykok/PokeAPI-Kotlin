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
    `java-library`
    `maven-publish`
    jacoco

    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.binaryCompatibility)
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
    // PokeApiConfig.httpClient exposes OkHttpClient in a public signature, so consumers need
    // it on their own compile classpath - api, not implementation.
    api(libs.okhttp)
    implementation(libs.jackson)
    // suspendCancellableCoroutine is confined to the non-inline HttpEngine.executeAsync, so no
    // kotlinx.coroutines type reaches a consumer's bytecode - implementation, not api.
    implementation(libs.coroutinesCore)

    testImplementation(platform(libs.junitBom))
    testImplementation(libs.junitJupiter)
    testRuntimeOnly(libs.junitPlatform)
    testImplementation(libs.mockk)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.coroutinesTest)
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
    explicitApi()
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

// The user agent must never drift from the published coordinates, so the version is
// generated from the Gradle property rather than typed into the source a second time.
val generateVersionConstant by tasks.registering {
    val outputDir = layout.buildDirectory.dir("generated/source/version/main/kotlin")
    val libraryVersion = project.version.toString()
    // Without this, the version is only baked into the `doLast` action's closure, which Gradle's
    // up-to-date check never inspects - so after a version bump this task stayed UP-TO-DATE and
    // kept emitting the previous release's LIBRARY_VERSION until a `clean` forced a rerun.
    // Declaring it as a task input makes the version part of the up-to-date check itself.
    inputs.property("libraryVersion", libraryVersion)
    outputs.dir(outputDir)
    doLast {
        val file = outputDir.get().file("fr/tykok/pokeapi/LibraryVersion.kt").asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            package fr.tykok.pokeapi

            internal const val LIBRARY_VERSION: String = "$libraryVersion"

            """.trimIndent()
        )
    }
}

kotlin.sourceSets.main {
    kotlin.srcDir(generateVersionConstant)
}

// Compiles the documentation's ```kotlin samples on every pull request.
//
// 3.0.0 shipped with 50 documentation snippets that no longer compiled, because PokeApi.get
// became suspend and nothing checked the docs against the code - they were fixed by hand only
// after release. This task extracts every ```kotlin fenced block from README.md and
// docs/mkdocs-markdown, wraps each so it compiles on its own, and writes the result into a
// generated source directory that compileTestKotlin picks up - so a snippet that drifts from the
// API fails the pull request instead of reaching the published site.
//
// The Markdown is the single source of truth: nothing here is a hand-maintained copy of a
// sample, so there is nothing to drift out of sync with what the reader actually sees.
//
// Scope: every non-declaration block is wrapped in a `private suspend fun`, so this gate proves a
// sample still matches the library's signatures - it does NOT prove the sample would compile at
// its documented call site (e.g. a context-free one-liner actually needs a coroutine scope or a
// `runBlocking`/`getBlocking`). That call-context gap is exactly the 3.0.0 breakage shape for the
// bulk of these one-liners; this task narrows it to "does the API surface still match", which is
// still the thing that silently rotted last time.
//
// Total blocks found is checked against a hardcoded floor (see expectedTotalDocBlocks below) so
// that a doc path moving, or a fence quietly losing its language tag, cannot make this task pass
// while checking nothing.
val docSampleMarkdownFiles =
    (listOf(file("README.md")) + fileTree("docs/mkdocs-markdown") { include("**/*.md") }.files)
        .sortedBy { it.path }

// The symbol table below is rebuilt from src/main/kotlin on every run, so a rename in the library
// changes the import a sample needs without anyone hand-updating a lookup table.
val docSampleLibrarySources = fileTree("src/main/kotlin") { include("**/*.kt") }

val docSamplesOutputDir = layout.buildDirectory.dir("generated/docSamples/kotlin")

val extractDocSamples by tasks.registering {
    inputs.files(docSampleMarkdownFiles).withPropertyName("markdown")
    inputs.files(docSampleLibrarySources).withPropertyName("librarySources")
    outputs.dir(docSamplesOutputDir).withPropertyName("generatedKotlin")
    // The checked/skipped report below must be visible on every run, not just the first one, so
    // a reviewer scanning CI output always sees whether the skip list grew - never silently
    // reused from an UP-TO-DATE or build-cache hit.
    outputs.upToDateWhen { false }

    doLast {
        val outDir = docSamplesOutputDir.get().asFile
        project.delete(outDir)
        outDir.mkdirs()

        // ---- 1. A name -> fully-qualified-name table, built from every top-level
        // class/object/interface declared under src/main/kotlin/fr/tykok/pokeapi. Only
        // zero-indent (true top-level) declarations are captured, so nested members such as
        // CacheConfig.OnDisk are not mistaken for importable top-level symbols.
        val topLevelDeclaration =
            Regex(
                """^(?:public\s+|internal\s+|private\s+)?(?:sealed\s+|abstract\s+|open\s+|final\s+|""" +
                    """annotation\s+|inline\s+|value\s+|enum\s+|data\s+)*(?:class|object|interface)\s+(\w+)"""
            )
        val packageDeclaration = Regex("""^package\s+([\w.]+)""")
        val symbolTable = mutableMapOf<String, MutableSet<String>>()
        docSampleLibrarySources.files.sortedBy { it.path }.forEach { src ->
            var pkg = ""
            src.readLines().forEach { line ->
                packageDeclaration.find(line)?.let { pkg = it.groupValues[1] }
                topLevelDeclaration.find(line)?.let { m ->
                    val name = m.groupValues[1]
                    symbolTable.getOrPut(name) { mutableSetOf() }.add("$pkg.$name")
                }
            }
        }
        // A handful of stable JDK/OkHttp types used in the samples that do not live in this
        // library's own source tree, so the scan above cannot discover them.
        symbolTable.getOrPut("File") { mutableSetOf() }.add("java.io.File")
        symbolTable.getOrPut("Interceptor") { mutableSetOf() }.add("okhttp3.Interceptor")
        symbolTable.getOrPut("OkHttpClient") { mutableSetOf() }.add("okhttp3.OkHttpClient")

        // ---- 2. Extract every Kotlin-tagged fenced block from the Markdown. Pygments (and so
        // MkDocs' highlighter) treats `kotlin`, `kt` and `kts` as the same language and renders
        // them identically, so all three info strings must be caught here - otherwise renaming a
        // fence from ```kotlin to ```kt would drop a block from this gate with a byte-identical
        // rendered page and no trace in the skip list.
        data class DocBlock(val file: File, val lineNumber: Int, val rawLines: List<String>)

        val fenceOpen = Regex("""^```(kotlin|kts|kt)\b""")
        val blocks = mutableListOf<DocBlock>()
        docSampleMarkdownFiles.forEach { md ->
            val lines = md.readLines()
            var i = 0
            while (i < lines.size) {
                val line = lines[i]
                val trimmed = line.trimStart()
                if (fenceOpen.containsMatchIn(trimmed)) {
                    val indent = line.length - trimmed.length
                    val bodyLines = mutableListOf<String>()
                    var j = i + 1
                    while (j < lines.size && lines[j].trimStart() != "```") {
                        val raw = lines[j]
                        bodyLines.add(if (raw.length >= indent) raw.substring(indent) else raw.trimStart())
                        j++
                    }
                    blocks.add(DocBlock(md, i + 1, bodyLines))
                    i = j
                }
                i++
            }
        }

        // ---- 3. THE EXCLUSION MECHANISM.
        //
        // Some blocks are genuinely not Kotlin library usage: the Gradle Kotlin-DSL install
        // snippet under "Installation" / "Getting started". A block is skipped ONLY when, once
        // its own leading `import` lines are stripped, its first non-blank line is a Gradle
        // build-script DSL call - `dependencies {`, `plugins {` or `repositories {`. That marker
        // is narrow and structural: it matches Gradle build-file DSL, never ordinary library
        // usage, and it does not key on a file name or a per-block opt-out comment. Nothing else
        // in this documentation is skipped, and this list is printed on every run so a reviewer
        // sees immediately if it grows.
        val gradleDslMarker = Regex("""^(dependencies|plugins|repositories)\s*\{""")
        // Matches a plain import, a wildcard import (`import x.y.*`) and an aliased import
        // (`import x.Y as Z`) - anything narrower left an import line sitting in the code body,
        // where it either fails with a confusing "imports are only allowed in the beginning of
        // file", or - worse - was misread as the block's first line of code, which could mask
        // the Gradle-DSL check above.
        val importLine = Regex("""^import\s+\S+(\s+as\s+\w+)?$""")

        fun firstNonBlankCode(rawLines: List<String>): String? =
            rawLines.map { it.trim() }.firstOrNull { it.isNotEmpty() && !importLine.matches(it) }

        val (skipped, checked) = blocks.partition { gradleDslMarker.containsMatchIn(firstNonBlankCode(it.rawLines) ?: "") }

        // A hardcoded floor: this number must be updated by hand (in the same commit as the doc
        // change) whenever a ```kotlin/kt/kts block is genuinely added or removed. That is a
        // deliberate speed bump - without it, a doc path moving, a fence losing its language tag,
        // or this task's own extraction regressing would all quietly report "checked 0(s)
        // skipped 0(s)" and a green build, which is the one failure mode a printed count cannot
        // by itself catch.
        val expectedTotalDocBlocks = 72
        if (blocks.size != expectedTotalDocBlocks) {
            throw GradleException(
                "extractDocSamples found ${blocks.size} ```kotlin/kt/kts block(s) in the " +
                    "documentation, but expectedTotalDocBlocks in build.gradle.kts is set to " +
                    "$expectedTotalDocBlocks. If you deliberately added or removed a documentation " +
                    "sample, update expectedTotalDocBlocks to match. If you did not, a block silently " +
                    "stopped being checked - find out why before changing the number."
            )
        }

        // ---- 4. Generate one compilable Kotlin file per checked block.
        val topLevelBodyStart =
            Regex(
                """^(?:private\s+|internal\s+|public\s+)?(?:suspend\s+)?""" +
                    """(?:fun\s|class\s|data\s+class\s|object\s|interface\s|enum\s+class\s)"""
            )
        val identifierRegex = Regex("""\b[A-Z][A-Za-z0-9_]*\b""")
        val declaredNameRegex =
            Regex(
                """(?:class|object|interface)\s+(\w+)|fun\s+(\w+)"""
            )

        checked.forEachIndexed { index, block ->
            val number = "%03d".format(index + 1)
            val relativePath = block.file.relativeTo(rootDir).path

            val importLines = block.rawLines.filter { importLine.matches(it.trim()) }.map { it.trim() }
            val codeLines = block.rawLines.filterNot { importLine.matches(it.trim()) }

            val declaredNames =
                codeLines.flatMap { l -> declaredNameRegex.findAll(l).flatMap { m -> m.groupValues.drop(1) } }
                    .filter { it.isNotEmpty() }
                    .toSet()

            val bodyText = codeLines.joinToString("\n")
            val isTopLevel = topLevelBodyStart.containsMatchIn(firstNonBlankCode(codeLines) ?: "")

            val neededImports =
                identifierRegex.findAll(bodyText)
                    .map { it.value }
                    .filter { it !in declaredNames }
                    .mapNotNull { name -> symbolTable[name]?.let { name to it } }
                    .toMap()

            // Two library classes can share a simple name (PokemonEncounter already does, in
            // entities.pokemon and entities.locations). Silently guessing which one a sample
            // meant is exactly the false-pass this gate exists to prevent - a wrong guess can
            // compile clean against the wrong type and prove nothing. Fail loudly instead, and
            // tell the author how to fix it: write the import explicitly in the Markdown block,
            // which lifts it above this auto-import step entirely and is better documentation
            // for the reader besides.
            val autoImports =
                neededImports.entries.map { (name, candidates) ->
                    if (candidates.size > 1) {
                        throw GradleException(
                            "extractDocSamples: '$name' is ambiguous at $relativePath:${block.lineNumber} " +
                                "- candidates: ${candidates.sorted()}. Add an explicit " +
                                "`import <the one you mean>` to that ```kotlin block in the Markdown " +
                                "to disambiguate."
                        )
                    } else {
                        candidates.single()
                    }
                }

            val allImports = (importLines.map { it.removePrefix("import ").trim() } + autoImports).distinct().sorted()

            val content = StringBuilder()
            content.appendLine("// GENERATED - DO NOT EDIT.")
            content.appendLine("// Extracted by the extractDocSamples Gradle task from $relativePath:${block.lineNumber}.")
            content.appendLine("package fr.tykok.pokeapi.docsamples.block$number")
            content.appendLine()
            allImports.forEach { content.appendLine("import $it") }
            if (allImports.isNotEmpty()) content.appendLine()

            if (isTopLevel) {
                // A top-level declaration (a `suspend fun main()`, a `data class`, ...) must be
                // emitted as a sibling of the imports, not nested inside a wrapper function -
                // Kotlin does not allow an `import` or another top-level declaration inside a
                // function body.
                content.appendLine(bodyText)
            } else {
                // A plain sequence of statements. Try ONE private suspend fun for the whole
                // block first - splitting on every blank line regardless of need used to break
                // an ordinary two-paragraph sample where the second paragraph uses a variable
                // the first paragraph declared, with a confusing "Unresolved reference" pointing
                // at a generated file the author never wrote. Re-split into paragraphs only when
                // the block itself would not compile as one scope - i.e. it redeclares the same
                // `val`/`var` name twice, the way "or, blocking:" alternatives intentionally do.
                val localDeclaration = Regex("""\b(?:val|var)\s+(\w+)""")
                val declaredLocalNames =
                    codeLines.flatMap { localDeclaration.findAll(it).map { m -> m.groupValues[1] } }
                val hasNameCollision = declaredLocalNames.size != declaredLocalNames.toSet().size

                val segments =
                    (if (!hasNameCollision) {
                        listOf(codeLines)
                    } else {
                        // Split on blank lines and on standalone full-line comments (the
                        // Markdown's own way of introducing "or, blocking:" alternatives) so
                        // that two alternatives shown in one fenced block, which intentionally
                        // reuse a variable name, do not redeclare it in the same scope.
                        val built = mutableListOf<MutableList<String>>()
                        codeLines.forEach { raw ->
                            val trimmedLine = raw.trim()
                            val startsNewSegment =
                                built.isEmpty() || trimmedLine.isEmpty() || trimmedLine.startsWith("//")
                            if (startsNewSegment) built.add(mutableListOf())
                            if (trimmedLine.isNotEmpty()) built.last().add(raw)
                        }
                        built.filter { it.isNotEmpty() }
                    }).filter { it.isNotEmpty() }

                segments.forEachIndexed { segIndex, segment ->
                    content.appendLine("private suspend fun sample$segIndex() {")
                    segment.forEach { content.appendLine("    $it") }
                    content.appendLine("}")
                    content.appendLine()
                }
            }

            val outFile = File(outDir, "Sample$number.kt")
            outFile.writeText(content.toString())
        }

        // ---- 5. Report. A reviewer reading CI output should see immediately if this list grows.
        logger.quiet(
            "Documentation sample gate: checked ${checked.size} block(s), skipped ${skipped.size} block(s)."
        )
        if (skipped.isNotEmpty()) {
            logger.quiet("Skipped (Gradle install DSL, not Kotlin library usage):")
            skipped.forEach {
                logger.quiet("  - ${it.file.relativeTo(rootDir).path}:${it.lineNumber}")
            }
        }
    }
}

kotlin.sourceSets.test {
    kotlin.srcDir(extractDocSamples)
}

ktlint {
    // Generated sources are not hand-written and must not be linted.
    filter {
        exclude { it.file.path.startsWith(layout.buildDirectory.get().asFile.path) }
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

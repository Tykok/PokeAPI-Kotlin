package fr.tykok.pokeapi.entities

import com.fasterxml.jackson.databind.JsonNode
import fr.tykok.pokeapi.http.JacksonUtils
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

/**
 * Controller ruling R17: Task 4 converted 124 `Number` fields to `Int` on the strength of
 * schema knowledge, not observation. Jackson's default `ACCEPT_FLOAT_AS_INT` setting means a
 * fractional JSON value read into an `Int` field is TRUNCATED SILENTLY - the same shape of
 * corruption as the null-to-zero coercion already closed on this branch (see
 * `EntityNullabilityTest`). That setting is deliberately left enabled, because PokeAPI could
 * legitimately send a zero-fraction float like `60.0` and throwing on that would break working
 * data.
 *
 * So this test turns the belief ("PokeAPI never sends a genuinely fractional number") into an
 * observation: it walks the raw JSON tree of every committed fixture and asserts that no
 * numeric value anywhere - at any depth, in any object or array - carries a non-zero fractional
 * part. The fixtures directory is enumerated rather than named file-by-file, so a newly added
 * fixture is automatically covered by this audit.
 */
class FractionalValueAuditTest {
    private fun fixturesDir(): File {
        val url =
            checkNotNull(javaClass.getResource("/fixtures")) { "fixtures resource directory not found on classpath" }
        return File(url.toURI())
    }

    /**
     * Recursively collects every fractional numeric value found in [node], reporting each as a
     * "jsonPointer=value" string so a failure points straight at the offending key.
     */
    private fun findFractionalValues(
        node: JsonNode,
        pointer: String = ""
    ): List<String> =
        when {
            node.isNumber && node.isFloatingPointNumber && node.doubleValue() != Math.floor(node.doubleValue()) ->
                listOf("$pointer=${node.asText()}")
            node.isObject ->
                node
                    .fields()
                    .asSequence()
                    .flatMap { (key, value) -> findFractionalValues(value, "$pointer/$key").asSequence() }
                    .toList()
            node.isArray ->
                node
                    .elements()
                    .asSequence()
                    .withIndex()
                    .flatMap { (index, value) -> findFractionalValues(value, "$pointer[$index]").asSequence() }
                    .toList()
            else -> emptyList()
        }

    @Test
    fun `no fixture contains a fractional numeric value anywhere in its JSON tree`() {
        val fixtures =
            fixturesDir()
                .listFiles { file -> file.isFile && file.extension == "json" }
                ?.sortedBy { it.name }
                ?: emptyList()

        assertTrue(fixtures.isNotEmpty(), "expected at least one fixture file to audit")

        val offenders =
            fixtures.flatMap { file ->
                val tree = JacksonUtils.mapper.readTree(file)
                findFractionalValues(tree).map { "${file.name}$it" }
            }

        assertTrue(
            offenders.isEmpty(),
            "found fractional numeric value(s) in fixture JSON, which Int fields would silently " +
                "truncate: $offenders"
        )
    }
}

# Releasing

`fr.tykok:pokeapi` is published to [Maven Central](https://central.sonatype.com/artifact/fr.tykok/pokeapi)
through the [Central Portal](https://central.sonatype.com/), and its documentation to
[GitHub Pages](https://tykok.github.io/PokeAPI-Kotlin/).

## TL;DR

1. Bump `version` in [`gradle.properties`](gradle.properties).
2. Merge to `main`.
3. The **Push on main ⛳️** workflow does the rest.

## Pipeline

Everything is driven by [`.github/workflows/on-main-push.yml`](.github/workflows/on-main-push.yml):

| Job | What it does | Skipped when |
| --- | --- | --- |
| `verify` | Reuses [`ci.yml`](.github/workflows/ci.yml): ktlint, tests, publishable artifacts, docs build | never |
| `version` | Reads the project version and asks the Central Portal whether it is already published | never |
| `publish` | Calls [`publish_maven_central.yml`](.github/workflows/publish_maven_central.yml) | the version is already on Central |
| `github-release` | Tags `v<version>`, builds the changelog, attaches jar + sources + javadoc | the version is already on Central |

Two properties this ordering buys:

- **Nothing is announced before it exists.** The tag and the GitHub release are created *after* Maven
  Central has accepted the upload, so a failed publication never leaves a dangling tag behind.
- **Re-running is safe.** The `version` job re-queries Central every time, so a re-run of a failed
  pipeline publishes only what is genuinely missing. `allowUpdates` on the release step makes the
  GitHub half idempotent too.

A `concurrency: release-main` group (with `cancel-in-progress: false`) guarantees two releases can
never overlap.

### Replaying a failed release

Re-run the **Push on main ⛳️** workflow (`Actions` → the failed run → *Re-run all jobs*), or trigger
it manually from its `workflow_dispatch` entry. `publish_maven_central.yml` is deliberately not
dispatchable on its own: going through the parent workflow keeps the "is it already published?"
guard in the loop.

`publish_maven_central.yml` also refuses to run if the checked-out revision does not build the exact
version its caller resolved — publication to Central is irreversible, so the guard is cheap insurance.

## Required repository secrets

| Secret | Where it comes from |
| --- | --- |
| `MAVEN_CENTRAL_USERNAME` | Central Portal → *Generate User Token* (username half) |
| `MAVEN_CENTRAL_PASSWORD` | Central Portal → *Generate User Token* (password half) |
| `SIGNING_IN_MEMORY_KEY` | ASCII-armored GPG private key (`gpg --armor --export-secret-keys <key-id>`) |
| `SIGNING_IN_MEMORY_KEY_ID` | Last 8 characters of the GPG key id — optional, only needed when the keyring holds several keys |
| `SIGNING_IN_MEMORY_KEY_PASSWORD` | Passphrase of that GPG key |

Secrets are passed to the reusable workflow **explicitly**, never with `secrets: inherit`, so the
publishing job only ever sees the five values it needs.

The publish job runs in the `maven-central` GitHub environment. GitHub creates it on the first run;
adding *Required reviewers* to it in `Settings → Environments` turns every publication into a
manually approved step, without touching a workflow file.

## Publishing from a workstation

Copy [`gradle-local.properties.example`](gradle-local.properties.example) to `gradle-local.properties`
(git-ignored) and fill it in, then:

```bash
./gradlew publishToMavenLocal          # dry run: signs and installs into ~/.m2
./gradlew publishAndReleaseToMavenCentral
```

## Documentation

[`deploy_docs.yml`](.github/workflows/deploy_docs.yml) publishes the MkDocs site to the `gh-pages`
branch on every push to `main` touching `docs/**` — documentation fixes ship without waiting for a
version bump. `mkdocs` is pinned in [`docs/requirements.txt`](docs/requirements.txt) and every build
runs with `--strict`, so a broken nav entry or dead internal link fails CI instead of shipping.

## Supply-chain notes

- Every GitHub Action is pinned to a commit SHA (the trailing `# vX` comment is the human-readable
  tag). Dependabot rewrites both together.
- `gradle/actions/wrapper-validation` runs in every job that executes `./gradlew`, so a tampered
  `gradle-wrapper.jar` is rejected before it runs.
- Workflows default to `permissions: contents: read`; only `github-release` and `deploy_docs` widen
  it to `contents: write`.
- Published archives are reproducible (no embedded timestamps, stable file order).

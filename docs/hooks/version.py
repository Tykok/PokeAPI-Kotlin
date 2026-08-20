"""Injects the library version into the documentation at build time.

`gradle.properties` is the same file the release pipeline reads, so the version
shown on the site is always the one the current revision publishes - there is
nothing to bump by hand in the docs.

Exposes it two ways:
  - `config.extra.library_version`, for templates;
  - the literal token `{{ version }}` in any Markdown page.
"""

import re
from pathlib import Path

VERSION_RE = re.compile(r"^version\s*=\s*(.+)$", re.MULTILINE)
FALLBACK = "unreleased"


def _read_version(config) -> str:
    properties = Path(config.config_file_path).parent.parent / "gradle.properties"
    if not properties.is_file():
        return FALLBACK
    match = VERSION_RE.search(properties.read_text(encoding="utf-8"))
    return match.group(1).strip() if match else FALLBACK


def on_config(config):
    config.extra["library_version"] = _read_version(config)
    return config


def on_page_markdown(markdown, page, config, files):
    return markdown.replace("{{ version }}", config.extra["library_version"])

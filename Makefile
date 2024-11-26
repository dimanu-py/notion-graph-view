.DEFAULT_GOAL := help

.PHONY: help
help:  ## Show this help.
	@grep -E '^\S+:.*?## .*$$' $(firstword $(MAKEFILE_LIST)) | \
		awk 'BEGIN {FS = ":.*?## "}; {printf "%-30s %s\n", $1, $2}'

.PHONY: test
test:
	pdm run pytest -n auto tests -ra

.PHONY: test-unit
test-unit:
	scripts/tests/unit.sh

.PHONY: all-test-unit
all-test-unit:
	scripts/tests/all-unit.sh

.PHONY: test-integration
test-integration:
	scripts/tests/integration.sh

.PHONY: all-test-integration
all-test-integration:
	scripts/tests/all-integration.sh

.PHONY: all-test-acceptance
all-test-acceptance:
	scripts/tests/acceptance.sh

.PHONY: coverage
coverage:
	pdm run coverage run --branch -m pytest tests
	pdm run coverage html
	@open "${PWD}/htmlcov/index.html"

.PHONY: local-setup
local-setup:
	scripts/local-setup.sh
	make install

.PHONY: install
install:
	rm -rf pdm.lock
	pdm install

.PHONY: update
update:
	pdm update

.PHONY: add-dependency
add-dependency:
	scripts/add-dependency.sh

.PHONY: check-typing
check-typing:
	pdm run mypy .

.PHONY: check-lint
check-lint:
	pdm run ruff check src tests

.PHONY: lint
lint:
	pdm run ruff check --fix src tests

.PHONY: check-format
check-format:
	pdm run ruff format --check src tests

.PHONY: format
format:
	pdm run ruff format src tests

.PHONY: pre-commit
pre-commit: check-typing check-lint check-format all-test-unit

.PHONY: pre-push
pre-push: all-test-integration all-test-acceptance

.PHONY: watch
watch:
	pdm run ptw --runner "pytest -n auto tests -ra"

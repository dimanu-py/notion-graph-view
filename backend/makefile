.PHONY: help
help:  ## Show this help.
	@grep -E '^[a-zA-Z_-]+:.*?## ' $(firstword $(MAKEFILE_LIST)) | \
			awk 'BEGIN {FS = ":.*## "}; {printf "%-30s %s\n", $$1, $$2}'

.PHONY: build
build: ## Build application
	@./gradlew build --warning-mode all

.PHONY: test
test: ## Run all test
	@./gradlew test --warning-mode all

.PHONY: run
run: ## Run application
	@./gradlew run --warning-mode all

.PHONY: linter
linter: ## Run spotless linter
	@./gradlew spotlessApply --warning-mode all
	@./gradlew spotlessCheck --warning-mode all

.PHONY: up
up: ## Create and start containers.
	@docker-compose up -d

.PHONY: down
down: ## Stop and remove containers.
	@docker-compose down -v --remove-orphans
.PHONY: help
help:  ## Show this help.
	@grep -E '^[a-zA-Z_-]+:.*?## ' $(firstword $(MAKEFILE_LIST)) | \
			awk 'BEGIN {FS = ":.*## "}; {printf "%-30s %s\n", $$1, $$2}'

.PHONY: build
build: # Build application
	@./gradlew build --warning-mode all

.PHONY: test
test: # Run all test
	@./gradlew test --warning-mode all

.PHONY: run
run: # Run application
	@./gradlew run --warning-mode all

.PHONY: linter
linter: # Run spotless linter
	@./gradlew spotlessApply --warning-mode all
	@./gradlew spotlessCheck --warning-mode all

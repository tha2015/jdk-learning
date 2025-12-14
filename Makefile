.PHONY: help run test build package clean docker-run docker-test ide pages

# Default target
.DEFAULT_GOAL := help

## help: Display this help message
help:
	@echo "Available targets:"
	@echo ""
	@grep -E '^## ' $(MAKEFILE_LIST) | sed 's/## //' | column -t -s ':'

## run: Compile and run Main.java using Maven Wrapper
run:
	./mvnw compile exec:java

## test: Run JUnit tests
test:
	./mvnw test

## build: Compile Java sources (no packaging)
build:
	./mvnw compile

## package: Build full package including TeaVM JavaScript
package:
	./mvnw clean package

## clean: Clean all build artifacts
clean:
	./mvnw clean

## pages: Build and open GitHub Pages preview locally
pages: package
	@echo "Opening generated page in browser..."
	@open target/generated/js/index.html || xdg-open target/generated/js/index.html

## docker-run: Run using Docker (fallback method)
docker-run:
	docker run --rm -v "$$HOME/.m2":/root/.m2 -v "$(shell pwd):/app" -w /app amazoncorretto:21 \
		./mvnw compile exec:java

## docker-test: Run tests using Docker (fallback method)
docker-test:
	docker run --rm -v "$$HOME/.m2":/root/.m2 -v "$(shell pwd):/app" -w /app amazoncorretto:21 \
		./mvnw test

## docker-package: Build package using Docker (fallback method)
docker-package:
	docker run --rm -v "$$HOME/.m2":/root/.m2 -v "$(shell pwd):/app" -w /app amazoncorretto:21 \
		./mvnw clean package

## ide: Open project in Pulsar editor
ide:
	pulsar .

## setup: Bootstrap Maven Wrapper (run once)
setup:
	docker run --rm -v "$$HOME/.m2":/root/.m2 -v "$(shell pwd):/app" -w /app maven:3.9-amazoncorretto-21 \
		mvn wrapper:wrapper

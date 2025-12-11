.PHONY: run


run:
	docker run --rm -v "$(shell pwd):/app" -w /app amazoncorretto:21 sh -c "javac -d out Main.java && java -cp out Main"
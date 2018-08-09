NAMESPACE := bitvector
NAME := gateway
VERSION := 0.1.0

docker-clean:
	docker rm $(shell docker ps -aq) || true
	docker rmi $(shell docker images -aq) || true

docker-build: /target/$(NAME)-$(VERSION).jar
	docker build --build-arg JAR_FILE=/target/$(NAME)-$(VERSION).jar -t $(NAMESPACE)/$(NAME):$(VERSION) .

docker-push: docker-build
	docker push $(NAMESPACE)/$(NAME):$(VERSION)

docker-run: docker-build
	docker run --rm -it $(NAMESPACE)/$(NAME):$(VERSION)


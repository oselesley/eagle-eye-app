dev:
	./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

prod:
	./mvnw spring-boot:run -Dspring-boot.run.profiles=prod

test:
	./mvnw test -Dspring.profiles.active=

compose-all:
	docker system prune -a --volumes
	cd confsvr && mvn clean install && cd ../  && cd eurekasvr && mvn clean install && cd ../
	cd licensing-service && mvn clean install && cd ../ && cd organization-service && mvn clean install && cd ../
	docker-compose -f docker/dev/docker-compose.yml up

compose:
	docker-compose -f docker/dev/docker-compose.yml up

decompose:
	docker-compose -f docker/dev/docker-compose.yml down

license:
	cd licensing-service && mvn clean install && cd ../



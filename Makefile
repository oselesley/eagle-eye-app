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
	cd gatewayserver && mvn clean install && cd ../
	docker-compose -f docker/dev/docker-compose.yml up

compose:
	docker-compose -f docker/dev/docker-compose.yml up

decompose:
	docker-compose -f docker/dev/docker-compose.yml down

config:
	cd confsvr && mvn clean install && cd ../

eureka:
	cd eurekasvr && mvn clean install && cd ../

license:
	cd licensing-service && mvn clean install && cd ../

organization:
	cd organization-service && mvn clean install && cd ../

gateway0:
	cd gatewaysvr && mvn clean install && cd ../

gateway:
	cd gatewayserver && mvn clean install && cd ../


FROM openjdk:13-jdk-alpine3.10
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/organizationservice
ADD target/organization-service-0.0.1-SNAPSHOT.jar /usr/local/organizationservice/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
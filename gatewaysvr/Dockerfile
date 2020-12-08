FROM openjdk:13-jdk-alpine3.10
RUN apk update && apk upgrade && apk add netcat-openbsd && apk add bind-tools
RUN mkdir -p /usr/local/gatewaysvr
ADD target/gatewaysvr-0.0.1-SNAPSHOT.jar /usr/local/gatewaysvr/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
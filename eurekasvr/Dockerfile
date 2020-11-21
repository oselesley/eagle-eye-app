FROM openjdk:13-jdk-alpine3.10
RUN  apk update && apk upgrade && apk add netcat-openbsd && apk add curl
RUN mkdir -p /usr/local/eurekaserver
RUN echo $JAVA_HOME
ADD  target/eurekasvr-0.0.1-SNAPSHOT.jar /usr/local/eurekaserver/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
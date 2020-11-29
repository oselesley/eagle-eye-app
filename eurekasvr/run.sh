#!/bin/sh
echo "********************************************************"
echo "Starting The Eureka Server"
echo "********************************************************"
java -jar /usr/local/eurekaserver/eurekasvr-0.0.1-SNAPSHOT.jar
#java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/eurekaserver/@project.build.finalName@.jar
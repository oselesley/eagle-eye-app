#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
while ! `nc -z eurekaserver $EUREKASERVER_PORT `; do sleep 3; done
echo ">>>>>>>>>>>> Eureka Server has started"

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z configserver $CONFIGSERVER_PORT `; do sleep 3; done
echo ">>>>>>>>>>>> Configuration Server has started"

echo "********************************************************"
echo "Waiting for the database server to start on port $DATABASESERVER_PORT"
echo "********************************************************"
while ! `nc -z database $DATABASESERVER_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

echo "********************************************************"
echo "Starting Gateway Server with Configuration Service :  $CONFIGSERVER_URI";
echo "********************************************************"
java  -Dspring.cloud.config.uri=$CONFIGSERVER_URI \
      -Dserver.port=$SERVER_PORT \
      -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI \
      -Dspring.profiles.active=$PROFILE -jar /usr/local/gatewaysvr/gatewaysvr-0.0.1-SNAPSHOT.jar
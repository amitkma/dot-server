#!/bin/bash
./gradlew build
sudo /usr/bin/java -jar build/libs/gs-rest-service-0.1.0.jar

#!/bin/bash
./gradlew clean build

# Remove existing image
docker rmi laterality/jwp-blog:latest

docker build -t laterality/jwp-blog:latest .

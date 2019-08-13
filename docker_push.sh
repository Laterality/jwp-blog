#!/bin/bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push laterality/myblog:${DOCKER_IMAGE_VERSION}
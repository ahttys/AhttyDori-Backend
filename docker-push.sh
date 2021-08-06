#!/bin/bash
# docker.io 로그인
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USER" --password-stdin

# docker hub에 push
docker push jrhong95/ahttyduri
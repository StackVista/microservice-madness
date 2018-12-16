#!/usr/bin/env bash

#docker network create --driver bridge mm-net
docker run -d --network mm-net -p 8080:8080 -v /var/run/docker.sock:/var/run/docker.sock mad-service:latest

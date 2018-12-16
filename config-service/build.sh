#!/usr/bin/env bash

./gradlew clean build && docker build -t config-service:latest .

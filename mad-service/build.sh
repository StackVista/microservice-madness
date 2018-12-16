#!/usr/bin/env bash

./gradlew clean build && docker build -t mad-service:latest .

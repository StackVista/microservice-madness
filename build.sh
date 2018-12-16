#!/usr/bin/env bash -e

cd mad-service
./build.sh
cd ..

cd config-service
./build.sh
cd ..

cd load-generator-tsung
./build.sh
cd ..

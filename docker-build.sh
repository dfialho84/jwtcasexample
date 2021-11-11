#!/bin/bash
./mvnw package
docker build -t jwtcasexample .

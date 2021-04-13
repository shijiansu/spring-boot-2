#!/bin/bash
./mvnw clean package
docker build -t shijian/springboot2-docker .

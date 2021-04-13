#!/bin/bash
for i in {1..5}; do
  # docker run hello-world
  # echo "--------------------------------------------------------------------------------"
  docker run hello-world >/dev/null 2>&1
  sleep 5s
done

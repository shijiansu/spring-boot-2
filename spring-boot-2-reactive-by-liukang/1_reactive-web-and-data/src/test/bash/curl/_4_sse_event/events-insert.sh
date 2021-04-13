#!/bin/bash
for i in {1..5}; do
  json="["
  for j in {1..5}; do
     json=$json'{"id":'$(date +%s)',"message":"message-bash-'$i$j'"},'
     sleep 1s # sleep 1 second so that the timestamp is with different value
  done
  json=${json: : -1} # remove last char
  json=$json"]"
  # echo $json

  curl -H "Content-Type:application/stream+json" -X POST -d "$json" http://localhost:8080/events/
  # result as, another 5 events come in console
  sleep 2s
done

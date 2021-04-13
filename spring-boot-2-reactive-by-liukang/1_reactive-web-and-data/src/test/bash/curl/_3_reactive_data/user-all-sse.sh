#!/bin/bash
# -N stop buffer, so when receiving data it will return immediately
curl -s -N -H 'Content-Type:application/stream+' http://localhost:8080/user/sse | jq
curl -v -N -H 'Content-Type:application/stream+' http://localhost:8080/user/sse | jq

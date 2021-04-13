#!/bin/bash
# stop buffer, so when receiving data it will return immediately
curl -N http://localhost:8080/greeting/sse2/ -H 'Accept:text/event-stream'

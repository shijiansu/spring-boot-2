#!/bin/bash
curl -s -X POST \
-H "Content-Type:application/json" \
-d '{"email":"sachin@gmail.com"}' \
http://localhost:8080/employees/ | jq

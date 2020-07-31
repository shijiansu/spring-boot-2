#!/bin/bash
curl -s -X POST \
-H "Content-Type:application/json" \
-d '{"lastName":"Gupta","email":"sachingmail.com"}' \
http://localhost:8080/employees/ | jq

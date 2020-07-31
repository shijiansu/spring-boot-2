#!/bin/bash
curl -s -X POST \
-H "Content-Type:application/json" \
-H "X-COM-PERSIST:TURE" \
-d @$(dirname "${BASH_SOURCE[0]}")/data/employee.json \
http://localhost:8080/employees/

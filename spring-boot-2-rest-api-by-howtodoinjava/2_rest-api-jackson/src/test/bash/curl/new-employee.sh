#!/bin/bash
curl -s -X POST \
-H "Content-Type:application/json" \
-d '{"firstName":"Sachin","lastName":"Gupta","email":"sachin@gmail.com"}' \
http://localhost:8080/employees/

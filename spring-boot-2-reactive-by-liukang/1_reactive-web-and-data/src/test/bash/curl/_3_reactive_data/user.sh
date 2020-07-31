#!/bin/bash
curl -H "Content-Type:application/json" -X POST -d '{"username": "zhangsan", "phone":"18610864861", "name": "Zhang San", "birthday": "1990-01-01"}' http://localhost:8080/user/

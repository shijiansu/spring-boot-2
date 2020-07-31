#!/bin/bash
curl -H "Content-Type:application/json" -X POST -d '{"username": "lisi", "phone":"18610869999", "name": "Li Si", "birthday": "1990-12-01"}' http://localhost:8080/user/

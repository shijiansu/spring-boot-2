#!/bin/bash
http http://localhost:8080/employees/ Content-Type:application/json < $(dirname "${BASH_SOURCE[0]}")/data/employee.json

#!/bin/bash
http http://localhost:8080/employees/ Content-Type:application/json X-COM-PERSIST:TURE < $(dirname "${BASH_SOURCE[0]}")/data/employee.json

#!/bin/bash
/bin/bash ./src/test/bash/curl/get-employee-id-not-exists.sh
/bin/bash ./src/test/bash/httpie/new-employee.sh
/bin/bash ./src/test/bash/httpie/get-employee-id-1.sh | tee ./src/test/bash/httpie/example/employees.json

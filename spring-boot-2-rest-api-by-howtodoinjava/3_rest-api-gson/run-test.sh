#!/bin/bash
/bin/bash ./src/test/bash/curl/get-employees.sh
/bin/bash ./src/test/bash/httpie/get-employees.sh | tee ./src/test/bash/httpie/example/employees.json

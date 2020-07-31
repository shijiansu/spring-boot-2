#!/bin/bash
curl -v http://localhost:8080/employees/ # HTTP 405 - Method not allowed

#*   Trying ::1...
#* TCP_NODELAY set
#* Connected to localhost (::1) port 8080 (#0)
#> GET /employees/ HTTP/1.1
#> Host: localhost:8080
#> User-Agent: curl/7.54.0
#> Accept: */*
#>
#< HTTP/1.1 405
#< Allow: POST
#< Content-Length: 0
#< Date: Tue, 03 Mar 2020 01:53:01 GMT
#<
#* Connection #0 to host localhost left intact


```text
2019-07-01 22:51:40 - Mapped "{[/employees/],methods=[GET],produces=[application/json]}" onto public spring.boot._2.based.on.howtodoinjava.Employees spring.boot._2.based.on.howtodoinjava.EmployeeController.getEmployees()
2019-07-01 22:51:40 - Mapped "{[/employees/],methods=[POST],consumes=[application/json],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<java.lang.Object> spring.boot._2.based.on.howtodoinjava.EmployeeController.addEmployee(spring.boot._2.based.on.howtodoinjava.Employee)
```

```shell
curl -v -X GET -H "Accept:application/json" http://localhost:8080/employees/

Note: Unnecessary use of -X or --request, GET is already inferred.
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /employees/ HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept:application/json
>
< HTTP/1.1 200
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Mon, 01 Jul 2019 15:50:33 GMT
<
* Connection #0 to host localhost left intact
{"employeeList":[{"id":1,"firstName":"Lokesh","lastName":"Gupta","email":"howtodoinjava@gmail.com"},{"id":2,"firstName":"Alex","lastName":"Kolenchiskey","email":"abc@gmail.com"},{"id":3,"firstName":"David","lastName":"Kameron","email":"titanic@gmail.com"},{"id":4,"firstName":"Sachin","lastName":"Gupta","email":"sachin@gmail.com"},{"id":5,"firstName":"Sachin","lastName":"Gupta","email":"sachin@gmail.com"},{"id":6,"firstName":"Sachin","lastName":"Gupta","email":"sachin@gmail.com"},{"id":7,"firstName":"Sachin","lastName":"Gupta","email":"sachin@gmail.com"},{"id":8,"firstName":"Sachin","lastName":"Gupta","email":"sachin@gmail.com"},{"id":9,"firstName":"Sachin","lastName":"Gupta","email":"sachin@gmail.com"}]}%
```

> Employee.java must have public fields, else `No serializer found for class` error

```shell
curl -v -X POST -H "Content-Type: application/json" -d '{"firstName":"Sachin","lastName":"Gupta","email":"sachin@gmail.com"}' http://localhost:8080/employees/
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST /employees/ HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 68
>
* upload completely sent off: 68 out of 68 bytes
< HTTP/1.1 201
< Location: http://localhost:8080/employees/9
< Content-Length: 0
< Date: Mon, 01 Jul 2019 15:49:40 GMT
<
* Connection #0 to host localhost left intact
```

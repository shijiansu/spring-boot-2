#!/bin/bash
# manually execute EventControllerTest.insert_5_events()
# or, using below scripts to insert 5 records in mongodb
/bin/bash events-insert.sh &

# now the request will wait for next
curl http://localhost:8080/events/

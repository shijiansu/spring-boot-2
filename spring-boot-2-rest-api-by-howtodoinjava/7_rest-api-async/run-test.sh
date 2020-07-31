#!/bin/bash
for i in {1..10}; do
   /bin/bash ./src/test/bash/curl/asynch.sh &
done

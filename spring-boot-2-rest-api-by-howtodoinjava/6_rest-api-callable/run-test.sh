#!/bin/bash
for i in {1..10}; do
   /bin/bash ./src/test/bash/curl/hello.sh &
done

for i in {1..10}; do
   /bin/bash ./src/test/bash/curl/goodbye.sh &
done

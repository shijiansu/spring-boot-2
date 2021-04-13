#!/bin/bash

cd springboot2-docker || exit 1
/bin/bash run-docker-build.sh
cd ..

cd springboot2-docker-client || exit 1
/bin/bash run-docker-build.sh
cd ..

#!/bin/bash
/bin/bash run-stop.sh
source env.sh
./gradlew build && java -jar "${PACKAGE_FOLDER}/${LIB_FOLDER}${ARTIFACT_ID}-0.0.1-SNAPSHOT.jar"

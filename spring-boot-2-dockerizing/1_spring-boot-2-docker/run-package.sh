#!/bin/bash
./mvnw clean package || exit 1
source env.sh
mkdir -p "${PACKAGE_FOLDER}/dependency" && cd "${PACKAGE_FOLDER}/dependency" || exit 1
# extract all jar to be the class files
jar -xf ../"${LIB_FOLDER}"*.jar

#!/bin/bash
./mvnw clean package || exit 1
source env.sh
mkdir -p "${PACKAGE_FOLDER}/dependency" && cd "${PACKAGE_FOLDER}/dependency" || exit 1
jar -xf ../"${LIB_FOLDER}"*.jar

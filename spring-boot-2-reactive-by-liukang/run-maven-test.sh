#!/bin/bash

BASEDIR=$(dirname "$0")
# echo "$BASEDIR"

# execute all maven projects in this folders
failed=0
for d in *; do
  if [[ -d "$d" ]]; then
    cd "$d" || return
    if [[ -f pom.xml ]]; then # maven project
      printf "%s: " "$d" # no new line
      if [[ -f mvnw ]]; then response=$(./mvnw clean test); else response=$(mvn clean test); fi
      if [[ "$(echo "$response" | grep "BUILD SUCCESS")" != "" ]]; then # success
        echo "Test successfully... ..."
      else
        echo "Test failed!!!"
        failed=$((failed + 1))
      fi
    fi
    cd "$BASEDIR" || exit
  fi
done

echo "--------------------------------------------------------------------------------"
echo "Total failed: $failed"
echo "--------------------------------------------------------------------------------"

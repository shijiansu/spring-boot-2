#!/bin/bash
# take few seconds to download all contents
curl -s http://localhost:8080/user/ | jq

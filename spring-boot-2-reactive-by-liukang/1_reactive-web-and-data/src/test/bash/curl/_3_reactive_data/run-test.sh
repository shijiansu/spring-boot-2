#!/bin/bash
echo "0 user(s)... ..."
/bin/bash ./user-all.sh

echo "1 user(s)... ..."
/bin/bash ./user.sh
/bin/bash ./user-all.sh

echo "2 user(s)... ..."
/bin/bash ./user2.sh
/bin/bash ./user-lizi.sh
/bin/bash ./user-all.sh

echo "2 user(s) - sse... ..."
/bin/bash ./user-all-sse.sh

echo "1 user(s)... ..."
/bin/bash ./user2-delete.sh
/bin/bash ./user-all.sh

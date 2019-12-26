#!/bin/bash

echo ">>> delete public"
rm -rf public
# echo ">>> git pull origin master"
# git pull
echo ">>> grunt index"
#/usr/local/node-v10.15.0/bin/grunt index
echo ">>> run hugo"
/usr/local/node-v10.15.0/bin/gulp && /usr/local/node-v10.15.0/bin/grunt index
./hugo
/usr/local/node-v10.15.0/bin/gulp html
echo ">>> run ok!"
sh run1.sh

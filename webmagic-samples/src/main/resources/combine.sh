#!/bin/sh
touch wordpress.xml
cat wp-head.xml >> wordpress.xml
for f in `ls`;
 do
  cat ${f} >> ../wordpress.xml
 done;
cat wp-bottom.xml >> wordpress.xml
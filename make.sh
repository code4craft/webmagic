#!/bin/sh
mvn clean package
cp ./webmagic-samples/target/webmagic-0.3.*.jar ./bin/
rsync -avz --delete ./webmagic-samples/target/lib/ ./bin/lib/
git add .
git add -u
git cim "update jar"
git push

#!/bin/sh
mvn clean package
rsync -avz --delete ./webmagic-extension/target/lib/ ./lib/
git add .
git add -u
git cim "update jar"
git push

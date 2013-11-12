#!/bin/sh
VERSION="0.4.1-SNAPSHOT"
mvn clean package
cp target/webmagic-scripts-${VERSION}.jar /usr/local/webmagic/webmagic-console.jar
rsync -avz --delete target/lib/ /usr/local/webmagic/lib/

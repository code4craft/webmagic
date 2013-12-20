#!/bin/sh
mvn clean package
rsync -avz --delete ./webmagic-samples/target/lib/ ./lib/

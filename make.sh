#!/bin/sh
mvn clean dependency:copy-dependencies -DoutputDirectory=target/lib
rsync -avz --delete ./webmagic-samples/target/lib/ ./lib/

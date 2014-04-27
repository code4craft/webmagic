#!/bin/sh
mvn dependency:copy-dependencies -DoutputDirectory=target/lib
rsync -avz --delete ./webmagic-samples/target/lib/ ./lib/

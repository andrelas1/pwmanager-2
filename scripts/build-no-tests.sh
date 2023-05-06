#! /bin/sh

echo "File changes detected... rebuilding project"
mvn clean install -DskipTests

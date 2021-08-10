#!/bin/bash

JAR_NAME=graylog-cli-0.1-jar-with-dependencies.jar
#if the fat JAR does not exist in the target directory, create it 
if [[ ! -d "../target" || ! -f "../target/${JAR_NAME}" ]]; then
  echo "compiling and generating sources"
  cd ../
  mvn clean package
  cd -
  echo "finished compiling and generating sources"
fi

#forward CLI args to the fat JAR for execution
java -jar ../target/graylog-cli-0.1-jar-with-dependencies.jar "$@"

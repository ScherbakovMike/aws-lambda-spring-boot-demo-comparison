#!/bin/bash

cp ../../../target/aws-lambda-spring-boot-demo-comparison ./
chmod +x ./bootstrap
zip -r aws-lambda-spring-boot-demo-comparison_aarch64.zip ./aws-lambda-spring-boot-demo-comparison ./bootstrap


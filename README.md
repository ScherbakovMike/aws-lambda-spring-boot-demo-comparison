## Configuration parameters

SnapStart: false

## Build

    docker run -it -v ${PWD}:/project awslinux-graalvm
    mvn -Pnative native:compile

## Deployment

    cd aws/lambda/dist
    ./compile.sh
    cd ../../../
    sam build
    sam deploy --guided

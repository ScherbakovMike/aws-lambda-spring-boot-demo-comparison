## Configuration parameters

GraalVM: true

## Build

    docker run -it -v ${PWD}:/project awslinux-graalvm
    mvn -Pnative native:compile

## Deployment

    cd aws/lambda/dist
    chmod +x ./compile.sh
    ./compile.sh
    cd ../../../
    sam build
    sam deploy --guided

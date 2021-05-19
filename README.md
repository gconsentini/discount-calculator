# Discount-Calculator
A simple discount calculator with 4 microservices that talk to each other with gRPC protocol.

# Before all

```This was created and tested on a macbook with an m1 chip(ARM architecture), the packages for gRPC on node and java were not yet available for it, so it may contain some issues.```

## Stack
* nodejs
* grpc
* mongodb
* docker
* java
* springboot

## Requirements

* For running it locally it requires nodejs, java 8+ and a mongodb installed.

* For running anywhere: Docker

## Documentation

There's a swagger documentation for the project on:

http://localhost:8080/swagger-ui.html

## How to deploy the application locally

The rest application runs on http://localhost:8080

### With Docker

1. First things first we should build the java application

    `cd products-api`

    `./gradlew build`

2. Build the docker containers with docker compose(compose is built in docker now) the `--force-recreate` is just in case you wanna build it again so it recreates the docker image.

    `docker compose build --force-recreate`

3. Run the application with the docker compose command.

    `docker compose up`

    if you wanna run it detached you put the `-d` flag within the command.

    `docker compose up -d`

it should create 5 containers, 4 for the services and 1 for the database.


### Locally

1. Before all, it's good to create a mongodb instance, this command should create one with no issues running on port 27017.

    `docker run -d --name=mongo -p 27017:27017 bitnami/mongodb`

2. Each node project (`calculator`, `product`, `user`) has a `yarn dev` command.

    `cd "$node_project"`
    
    `yarn && yarn dev`

3. To run the java application just run the command.

    `cd products-api`

    `./gradlew bootRun`



## Other things

```if you want to test if the calculator service is not running, or an error occurs calculating the discount, just run the command on docker to shut down the service, or does not run the calculator service locally.```

`docker compose down calculator`
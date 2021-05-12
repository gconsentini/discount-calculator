const path = require('path');
const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');
const dotenv = require("dotenv");
dotenv.config();

const controller = require('./controller');

const port = process.env.PORT;

const packageDefinition = protoLoader.loadSync(
    path.resolve(__dirname, 'pb', 'calculator.proto'),
    {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true,
    }
);

const proto = grpc.loadPackageDefinition(packageDefinition);

const server = new grpc.Server();

server.addService(proto.CalculatorService.service, controller);
server.bind(`0.0.0.0:${port}`, grpc.ServerCredentials.createInsecure());
server.start();

console.log(`Server running at 0.0.0.0:${port}`);
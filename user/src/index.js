const path = require('path');
const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');

const controller = require('./controller');

require('./database');

const packageDefinition = protoLoader.loadSync(
    path.resolve(__dirname, 'pb', 'user.proto'),
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

server.addService(proto.UserService.service, controller);
server.bind('0.0.0.0:3334', grpc.ServerCredentials.createInsecure());
server.start();

console.log("Server running at 0.0.0.0:3334");
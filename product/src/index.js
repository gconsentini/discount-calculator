const path = require('path');
const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');

const controller = require('./controller');

require('./database');

const packageDefinition = protoLoader.loadSync(
  path.resolve(__dirname, 'pb', 'product.proto'),
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

server.addService(proto.ProductService.service, controller);
server.bind('0.0.0.0:3335', grpc.ServerCredentials.createInsecure());
server.start();
const path = require("path");
const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const protoConfig = require("../config/proto");

const userDef = protoLoader.loadSync(
  path.resolve(__dirname, "..", "pb", "user.proto"),
  protoConfig
);

const user = grpc.loadPackageDefinition(userDef);

const userClient = new user.UserService(
  "localhost:3334",
  grpc.credentials.createInsecure()
);

module.exports = userClient;

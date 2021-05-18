const path = require("path");
const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const protoConfig = require("../config/proto");

const userHost = process.env.USER_HOST;

const userDef = protoLoader.loadSync(
  path.resolve(__dirname, "..", "pb", "user.proto"),
  protoConfig
);

const user = grpc.loadPackageDefinition(userDef);

const userClient =
  userHost === "user"
    ? new user.UserService("user:3334", grpc.credentials.createInsecure())
    : new user.UserService("localhost:3334", grpc.credentials.createInsecure());

module.exports = userClient;

const path = require("path");
const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const protoConfig = require("../config/proto");

const productDef = protoLoader.loadSync(
  path.resolve(__dirname, "..", "pb", "product.proto"),
  protoConfig
);

const product = grpc.loadPackageDefinition(productDef);

const productClient = new product.ProductService(
  "localhost:3335",
  grpc.credentials.createInsecure()
);

module.exports = productClient;

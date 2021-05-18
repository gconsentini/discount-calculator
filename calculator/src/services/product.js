const path = require("path");
const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const protoConfig = require("../config/proto");

const productHost = process.env.PRODUCT_HOST;

const productDef = protoLoader.loadSync(
  path.resolve(__dirname, "..", "pb", "product.proto"),
  protoConfig
);

const product = grpc.loadPackageDefinition(productDef);

//muito obrigado docs...
const productClient =
  productHost === "product"
    ? new product.ProductService(
        "product:3335",
        grpc.credentials.createInsecure()
      )
    : new product.ProductService(
        "localhost:3335",
        grpc.credentials.createInsecure()
      );

module.exports = productClient;

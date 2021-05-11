const Product = require('./models/Product');

module.exports = {
  async getProductById(call, callback) {
    const { id } = call.request;

    const response = await Product.findById(id);

    return callback(null, { product: response });
  },
  async listProducts(call, callback) {
    const products = await Product.find();

    return callback(null, { products });
  },
  async product(call, callback) {
    const { price_in_cents, title, description } = call.request.product;

    const product = await Product.create({ price_in_cents, title, description }); 

    return callback(null, {
      product,
    });
  },
};
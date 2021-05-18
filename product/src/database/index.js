const mongoose = require('mongoose');

const mongoHost = process.env.MONGO_HOST;

mongoose.connect(`mongodb://${mongoHost}:27017/product`, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});
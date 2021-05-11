const mongoose = require('mongoose');

const ProductSchema = new mongoose.Schema({
    price_in_cents: Number,
    title: String, 
    description: String
});

module.exports = mongoose.model('Product', ProductSchema);
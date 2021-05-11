const mongoose = require('mongoose');

const UserSchema = new mongoose.Schema({
    first_name: String,
    last_name: String,
    date_of_birth: Date,
});

module.exports = mongoose.model('User', UserSchema);
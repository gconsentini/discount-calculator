const User = require('./models/User');

module.exports = {
    async getUserById(call, callback) {
        const { id } = call.request;

        const user = await User.findById(id);

        if (!user) {
            return callback(null, { error: 'User not found' });
        }

        return callback(null, {
            user: { user },
        });
    },

    async registerUser(call, callback) {
        const { first_name, last_name, date_of_birth } = call.request.user;

        const user = await User.create({ first_name, last_name, date_of_birth });
        console.log(user._id);

        return callback(null, {
            user,
        });
    },
};
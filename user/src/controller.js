const User = require('./models/User');

module.exports = {
    async getUserById(call, callback) {
        const { id } = call.request;

        console.log(call.request);

        const user = await User.findById(id);

        if (!user) {
            return callback(null, { error: 'User not found' });
        }

        return callback(null, {
          user: {
            ...user.toObject(),
            id: user._id,
            date_of_birth: new Date(user.date_of_birth).toISOString(),
          },
        });
    },

    async registerUser(call, callback) {
        const { first_name, last_name, date_of_birth } = call.request.user;

        const user = await User.create({ first_name, last_name, date_of_birth });
        console.log(user._id);

        return callback(null, {
          user: {
            ...user.toObject(),
            id: user._id,
            date_of_birth: new Date(user.date_of_birth).toISOString(),
          },
        });
    },
};
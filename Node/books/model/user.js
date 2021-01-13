const mongoose = require('mongoose');

let UserSchema = new mongoose.Schema({
    nickname: {
        type: String,
        required: [true, 'Nickname is mandatory']
    },
    email: {
        type: String,
        required: [true, 'Email is mandatory']
    }
});

module.exports = mongoose.model('User', UserSchema);
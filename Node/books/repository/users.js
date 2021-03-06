const User = require('../model/user');

async function findById(id) {
    return await User.findById(id).exec();
}

async function findByNickname(nickname) {
    return await User.find({nickname: nickname}).exec()
}

async function save(user) {
    let newUser = new User({
        nickname: user.nickname,
        email: user.email })
    await newUser.save();
    return newUser;
}

exports.findById = findById;
exports.findByNickname = findByNickname;
exports.save = save;
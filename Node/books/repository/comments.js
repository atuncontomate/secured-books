const Comment = require('../model/comment');

async function findAllByBookId(id) {
    return await Comment.find({book: id}).exec()
}

async function save(comment, bookId, userId) {
    let newComment = new Comment({
        score: comment.score,
        content: comment.content,
        user: userId,
        book: bookId })
    await newComment.save();
    return newComment;
}

async function findById(id) {
    return await Comment.findById(id).exec()
}

async function deleteById(id) {
    await Comment.findByIdAndDelete(id);
}

exports.findAllByBookId = findAllByBookId;
exports.save = save;
exports.findById = findById;
exports.deleteById = deleteById;
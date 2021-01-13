const mongoose = require('mongoose');

let Schema = mongoose.Schema;
let CommentSchema = new Schema({
    score: {
        type: Number,
        min: [0, 'Score has to be between 0 and 5'],
        max: [5, 'Score has to be between 0 and 5'],
        required: true
    },
    content: String,
    user: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true},
    book: {
        type: Schema.Types.ObjectId,
        ref: 'Book',
        required: true}
});

module.exports = mongoose.model('Comment', CommentSchema);
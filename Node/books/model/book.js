const mongoose = require('mongoose');

let BookSchema = new mongoose.Schema({
    title: {
        type: String,
        required: [true, 'Title is mandatory']
    },
    author: String,
    publisher: String,
    year: Number,
    summary: String
});

module.exports = mongoose.model('Book', BookSchema);
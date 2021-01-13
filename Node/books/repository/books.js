const Book = require('../model/book');

async function save(book) {
    let newBook = new Book({
        title: book.title,
        author: book.author,
        publisher: book.publisher,
        year: book.year,
        summary: book.summary })
    await newBook.save();
    return newBook;
}

async function findAll() {
    return await Book.find({}).exec()
}

async function findById(id) {
    return await Book.findById(id).exec()
}

exports.save = save;
exports.findAll = findAll;
exports.findById = findById;
const express = require ('express');
const mongoose = require('mongoose');

const server = express();
const booksRouter = require('./routes/books');
const usersRouter = require('./routes/users');

server.use(express.json());
server.use('/books',booksRouter);
server.use('/users',usersRouter);

mongoose.connect('mongodb://localhost:27017/local', {
    useUnifiedTopology: true,
    useNewUrlParser: true,
    useFindAndModify: false
}, (err, db) => {
    if (err) {
        throw err;
    }
    console.log('Connected to database');
});


server.listen(3000, () => {
    console.log('Books server listening on port 3000!');
});
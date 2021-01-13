const express = require ('express');
const dtoMappers = require('../dto/dtoMappers');

const booksRepository = require('../repository/books');
const commentsRepository = require('../repository/comments');
const usersRepository = require('../repository/users');

const router = express.Router();

router.post('/', async (req, res) => {
    const book = req.body;
    if (!(book.title)) {
        res.sendStatus(400);
    } else {
        const savedBook = await booksRepository.save(book);
        res.json(dtoMappers.bookDTO(savedBook))
    }
});

router.get('/', async (req, res) => {
    const books = await booksRepository.findAll();
    res.json(books.map(book => dtoMappers.bookPreviewDTO(book)));
});

router.get('/:id', async (req, res) => {
    const id = req.params.id;
    let [book, comments] = await Promise.all([
        booksRepository.findById(id),
        commentsRepository.findAllByBookId(id)
    ])

    const users = await Promise.all(
        comments.map(comment => usersRepository.findById(comment.user))
    );

    comments.forEach(function (comment, i) {
        comment.user = users[i];
    });

    const commentsDto = comments.map(comment => dtoMappers.commentDto(comment));

    if (book) {
        res.json(dtoMappers.bookDetailsDTO(book, commentsDto))
    } else {
        res.sendStatus(404);
    }
});

router.post('/:id/comments', async (req, res) => {
    const bookId = req.params.id;
    const comment = req.body;

    let [book, user] = await Promise.all([
        booksRepository.findById(bookId),
        usersRepository.findByNickname(comment.user)
    ])

    if (book && user && user.length > 0){
        const newComment = await commentsRepository.save(comment, bookId, user[0]._id);
        newComment.user = user[0]
        res.json(dtoMappers.commentDto(newComment));

    } else {
        res.sendStatus(404);
    }
});

router.delete('/:bookId/comments/:commentId', async (req, res) => {
    const bookId = req.params.bookId;
    const commentId = req.params.commentId;

    let [book, comment] = await Promise.all([
        booksRepository.findById(bookId),
        commentsRepository.findById(commentId)
    ])

    if (book && comment && (comment.book == bookId)){
        await commentsRepository.deleteById(commentId)
        res.sendStatus(200);
    } else {
        res.sendStatus(404);
    }
});

module.exports = router;
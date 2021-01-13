function bookPreviewDTO(book){
    return {
        id: book.id,
        title: book.title
    }
}

function bookDTO(book){
    return {
        id: book._id,
        title: book.title,
        author: book.author,
        publisher: book.publisher,
        year: book.year,
        summary: book.summary
    }
}

function bookDetailsDTO(book, commentsDto){
    return {
        id: book.id,
        title: book.title,
        author: book.author,
        publisher: book.publisher,
        year: book.year,
        summary: book.summary,
        comments: commentsDto
    }
}

function userDTO(user){
    return {
        id: user._id,
        nickname: user.nickname,
        email: user.email
    }
}

function commentDto(comment){
    return {
        id: comment._id,
        score: comment.score,
        content: comment.content,
        user: userDTO(comment.user)
    }
}

exports.bookPreviewDTO = bookPreviewDTO;
exports.bookDTO = bookDTO;
exports.bookDetailsDTO = bookDetailsDTO;
exports.userDTO = userDTO;
exports.commentDto = commentDto;
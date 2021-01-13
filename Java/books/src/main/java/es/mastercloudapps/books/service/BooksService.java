package es.mastercloudapps.books.service;

import es.mastercloudapps.books.dto.*;
import es.mastercloudapps.books.exception.*;
import es.mastercloudapps.books.model.Book;
import es.mastercloudapps.books.model.Comment;
import es.mastercloudapps.books.model.User;
import es.mastercloudapps.books.repository.BookRepository;
import es.mastercloudapps.books.repository.CommentRepository;
import es.mastercloudapps.books.repository.UserRepository;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BooksService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public BooksService(BookRepository bookRepository,
                        CommentRepository commentRepository,
                        UserRepository userRepository,
                        Mapper mapper) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public BookDto createBook(BookBaseDto newBook){
        Book book = mapper.map(newBook, Book.class);
        bookRepository.save(book);
        return mapper.map(book, BookDto.class);
    }

    public List<BookPreviewDto> findAllBooks(){
        return bookRepository.findAll().stream()
                .map(book -> mapper.map(book, BookPreviewDto.class))
                .sorted(Comparator.comparing(BookPreviewDto::getTitle))
                .collect(Collectors.toList());
    }

    public BookDetailsDto findBookById(long bookId){
        Optional<Book> foundBook = bookRepository.findById(bookId);

        if (foundBook.isEmpty()){
            throw new BookNotFoundException();
        }

        BookDetailsDto bookDetails = mapper.map(foundBook.get(), BookDetailsDto.class);
        bookDetails.setComments(findCommentsByBook(foundBook.get()));
        return bookDetails;
    }

    private List<CommentDto> findCommentsByBook(Book book){
        List<Comment> foundComments = commentRepository.findAllByBook(book);
        return foundComments.stream()
                .map(comment -> mapper.map(comment, CommentDto.class))
                .sorted(Comparator.comparing(CommentDto::getId).reversed())
                .collect(Collectors.toList());
    }

    public CommentDto addComment(long bookId, CommentBaseDto newComment){
        Optional<Book> foundBook = bookRepository.findById(bookId);
        if (foundBook.isEmpty()){
            throw new BookNotFoundException();
        }

        Optional<User> foundUser = userRepository.findByNickname(newComment.getUser());
        if (foundUser.isEmpty()){
            throw new UserNotFoundException();
        }

        Comment comment = Comment.builder()
                .score(newComment.getScore())
                .user(foundUser.get())
                .content(newComment.getContent())
                .book(foundBook.get())
                .build();

        commentRepository.save(comment);
        return mapper.map(comment, CommentDto.class);
    }

    public void deleteComment(long bookId, long commentId){
        Optional<Book> foundBook = bookRepository.findById(bookId);
        if (foundBook.isEmpty()){
            throw new BookNotFoundException();
        }

        Optional<Comment> foundComment = commentRepository.findById(commentId);
        if(foundComment.isEmpty() || !foundBook.get().equals(foundComment.get().getBook())){
            throw new CommentNotFoundException();
        }

        commentRepository.delete(foundComment.get());
    }
}

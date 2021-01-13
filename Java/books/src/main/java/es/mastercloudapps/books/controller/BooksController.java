package es.mastercloudapps.books.controller;

import es.mastercloudapps.books.dto.*;
import es.mastercloudapps.books.service.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/")
    public List<BookPreviewDto> getBooks(){
        return booksService.findAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookDetailsDto getBookDetails(@PathVariable long bookId){
        return booksService.findBookById(bookId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@Valid @RequestBody BookBaseDto book){
        return booksService.createBook(book);
    }

    @PostMapping("/{bookId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@PathVariable long bookId, @Valid @RequestBody CommentBaseDto comment){
        return booksService.addComment(bookId, comment);
    }

    @DeleteMapping("/{bookId}/comments/{commentId}")
    public void deleteComment(@PathVariable long bookId, @PathVariable long commentId){
        booksService.deleteComment(bookId, commentId);
    }
}

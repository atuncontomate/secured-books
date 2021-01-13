package es.mastercloudapps.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book was not found")
public class BookNotFoundException extends RuntimeException{
}
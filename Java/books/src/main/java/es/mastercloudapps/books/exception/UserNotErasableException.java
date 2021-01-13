package es.mastercloudapps.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User cannot be deleted")
public class UserNotErasableException extends RuntimeException{
}
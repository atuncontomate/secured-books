package es.mastercloudapps.books.controller;

import es.mastercloudapps.books.dto.*;
import es.mastercloudapps.books.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public List<UserDto> getUsers(){
        return usersService.findAllUsers();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserBaseDto user){
        return usersService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id){
        usersService.deleteUser(id);
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getUsers(@PathVariable long id){
        return usersService.findCommentsByUser(id);
    }

    @PutMapping("/{id}")
    public UserDto changeEmail(@PathVariable long id, @RequestBody UserBaseDto user){
        return usersService.changeEmail(id, user);
    }
}

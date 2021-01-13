package es.mastercloudapps.books.service;

import es.mastercloudapps.books.dto.*;
import es.mastercloudapps.books.exception.*;
import es.mastercloudapps.books.model.User;
import es.mastercloudapps.books.repository.CommentRepository;
import es.mastercloudapps.books.repository.UserRepository;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final Mapper mapper;

    public UsersService(UserRepository userRepository, CommentRepository commentRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    public UserDto createUser(UserBaseDto newUser) {
        if(userRepository.findByNickname(newUser.getNickname()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        User user = mapper.map(newUser, User.class);
        userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }


    public void deleteUser(long id) {
        Optional<User> userToDelete = userRepository.findById(id);

        if(userToDelete.isEmpty()){
            throw new UserNotFoundException();
        }

        if(!commentRepository.findAllByUser(userToDelete.get()).isEmpty()){
            throw new UserNotErasableException();
        }

        userRepository.delete(userToDelete.get());
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .sorted(Comparator.comparing(UserDto::getNickname))
                .collect(Collectors.toList());
    }

    public List<CommentDto> findCommentsByUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        return commentRepository.findAllByUser(user.get()).stream()
                .map(comment -> mapper.map(comment, CommentDto.class))
                .sorted(Comparator.comparing(CommentDto::getId).reversed())
                .collect(Collectors.toList());
    }

    public UserDto changeEmail(long id, UserBaseDto user) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()){
            throw new UserNotFoundException();
        }

        User userToModify = foundUser.get();
        userToModify.setEmail(user.getEmail());
        userRepository.save(userToModify);
        return mapper.map(user, UserDto.class);
    }
}
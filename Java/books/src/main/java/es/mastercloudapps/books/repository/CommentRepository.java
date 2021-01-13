package es.mastercloudapps.books.repository;

import es.mastercloudapps.books.model.Book;
import es.mastercloudapps.books.model.Comment;
import es.mastercloudapps.books.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBook(Book book);

    List<Comment> findAllByUser(User user);
}

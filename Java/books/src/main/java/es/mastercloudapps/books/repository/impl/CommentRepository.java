package es.mastercloudapps.books.repository.impl;

import es.mastercloudapps.books.model.Comment;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

//@Repository
public class CommentRepository {

    private final ConcurrentMap<Long, ConcurrentMap<Long, Comment>> comments;
    private final AtomicLong nextCommentId;

    public CommentRepository() {
        this.comments = new ConcurrentHashMap<>();
        this.nextCommentId = new AtomicLong();
    }

    public Collection<Comment> findAllByBookId(long bookId){
        return Objects.isNull(comments.get(bookId))
                ? Collections.emptyList()
                : comments.get(bookId).values();
    }

    public void add(long bookId, Comment comment){
        long commentId = nextCommentId.getAndIncrement();
        ConcurrentMap<Long, Comment> bookComments = Objects.nonNull(comments.get(bookId))
                ? comments.get(bookId)
                : new ConcurrentHashMap<>();

        bookComments.put(commentId, comment);
        comments.put(bookId, bookComments);
        comment.setId(commentId);
    }

    public Comment findByBookIdAndCommentId(long bookId, long commentId){
        ConcurrentMap<Long, Comment> bookComments = comments.get(bookId);
        return Objects.isNull(bookComments) ? null : bookComments.get(commentId);
    }

    public void delete(long bookId, long commentId){
        comments.get(bookId).remove(commentId);
    }
}

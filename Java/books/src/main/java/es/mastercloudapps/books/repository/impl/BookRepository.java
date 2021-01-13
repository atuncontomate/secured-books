package es.mastercloudapps.books.repository.impl;

import es.mastercloudapps.books.model.Book;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

//@Repository
public class BookRepository {

    private final ConcurrentMap<Long, Book> books;
    private final AtomicLong nextBookId;

    public BookRepository() {
        this.books = new ConcurrentHashMap<>();
        this.nextBookId = new AtomicLong();
    }

    public Collection<Book> findAll(){
        return books.values();
    }

    public Book findById(long bookId) {
        return books.get(bookId);
    }

    public void add(Book book){
        long id = nextBookId.getAndIncrement();
        book.setId(id);
        books.put(id, book);
    }
}

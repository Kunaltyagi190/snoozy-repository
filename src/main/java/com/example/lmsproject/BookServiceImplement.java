package com.example.lmsproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImplement implements BookService {
    @Autowired
    private BookRepo bookRepo;

    @Override
    public String createBook(Book book) {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(book, bookEntity);
        bookRepo.save(bookEntity);
        return "Saved";
    }

    @Override
    public Book readBook(Long id) {
        BookEntity bookEntity = bookRepo.findById(id).orElse(null);
        if (bookEntity == null) return null;
        Book book = new Book();
        BeanUtils.copyProperties(bookEntity, book);
        return book;
    }

    @Override
    public List<Book> readBooks() {
        List<BookEntity> bookList = bookRepo.findAll();
        List<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : bookList) {
            Book bk = new Book();
            BeanUtils.copyProperties(bookEntity, bk);
            books.add(bk);
        }
        return books;
    }

    @Override
    public boolean deleteBook(Long id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return true;
        }
        return false;
    }

    

    @Override
    public String updteBook(Long id, Book book) {
        BookEntity existingBook = bookRepo.findById(id).orElse(null);
        if (existingBook == null) return "Book not found";
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthorId(book.getAuthorId());
        existingBook.setPublishedDate(book.getPublishedDate());
        existingBook.setIsbn(book.getIsbn());
        bookRepo.save(existingBook);
        return "Update Successfully";
    }
}

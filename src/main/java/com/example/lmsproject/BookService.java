package com.example.lmsproject;

import java.util.List;

public interface BookService {
String createBook(Book book);
List<Book> readBooks();
boolean deleteBook(Long id);
String updteBook(Long id, Book book);
Book readBook(Long id);


    
}
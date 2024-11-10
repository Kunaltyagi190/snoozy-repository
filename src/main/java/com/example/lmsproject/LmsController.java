package com.example.lmsproject;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LmsController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepo authorRepo;

    // Book Endpoints

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.readBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.readBook(id);
    }

    @PostMapping("/books")
    public String createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/books/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updteBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id) ? "Successfully deleted" : "Book not found";
    }

    // Author Endpoints

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        List<AuthorEntity> authorEntities = authorRepo.findAll();
        return authorEntities.stream().map(entity -> {
            Author author = new Author();
            BeanUtils.copyProperties(entity, author);
            return author;
        }).toList();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        AuthorEntity authorEntity = authorRepo.findById(id).orElse(null);
        if (authorEntity == null) return null;
        Author author = new Author();
        BeanUtils.copyProperties(authorEntity, author);
        return author;
    }

    @PostMapping("/authors")
    public String createAuthor(@RequestBody Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        BeanUtils.copyProperties(author, authorEntity);
        authorRepo.save(authorEntity);
        return "Author saved";
    }

    @PutMapping("/authors/{id}")
    public String updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        AuthorEntity existingAuthor = authorRepo.findById(id).orElse(null);
        if (existingAuthor == null) return "Author not found";
        existingAuthor.setName(author.getName());
        existingAuthor.setBirthdate(author.getBirthdate());
        authorRepo.save(existingAuthor);
        return "Author updated successfully";
    }

    @DeleteMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        if (authorRepo.existsById(id)) {
            authorRepo.deleteById(id);
            return "Author deleted successfully";
        }
        return "Author not found";
    }
}


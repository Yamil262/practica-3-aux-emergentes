package com.example.cambio.service;

import com.example.cambio.dto.BookDTO;
import com.example.cambio.model.Book;
import com.example.cambio.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return repository.findById(id);
    }

    public Book createBook(BookDTO dto) {
        Book book = new Book(
                dto.getTitle(),
                dto.getAuthor(),
                dto.getIsbn(),
                dto.getPublicationYear(),
                dto.getCategory(),
                dto.getAvailable()
        );

        return repository.save(book);
    }

    public Optional<Book> updateBook(Long id, BookDTO dto) {

        return repository.findById(id).map(book -> {

            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setIsbn(dto.getIsbn());
            book.setPublicationYear(dto.getPublicationYear());
            book.setCategory(dto.getCategory());
            book.setAvailable(dto.getAvailable());

            return repository.save(book);
        });
    }

    public boolean deleteBook(Long id) {

        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
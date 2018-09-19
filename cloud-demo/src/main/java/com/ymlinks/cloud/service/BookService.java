package com.ymlinks.cloud.service;

import com.ymlinks.cloud.model.Book;
import com.ymlinks.cloud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }
}

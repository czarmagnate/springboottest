package com.example.springboot.service;

import com.example.springboot.entity.Book;
import com.example.springboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    //@Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}

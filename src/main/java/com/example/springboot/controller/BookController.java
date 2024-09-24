package com.example.springboot.controller;

import com.example.springboot.entity.Book;
import com.example.springboot.entity.BookDTO;
import com.example.springboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public String findAll(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "list";
    }
}

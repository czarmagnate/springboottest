package com.example.springboot.service;

import com.example.springboot.entity.Book;
import com.example.springboot.entity.BookDTO;
import com.example.springboot.entity.Review;
import com.example.springboot.entity.ReviewDTO;
import com.example.springboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        List<Book> books = bookRepository.findAllWithReviews();
        return books;
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooksDTO(){
        // Book 정보와 Review 정보 함께 가져오기
        // 1. 서비스 계층에서 reviews 를 미리 로드하여, 뷰 계층에서 LazyInitializationException을 방지
        // List<Book> books = bookRepository.findAll(); // Book 정보만 존재
        // books.forEach(book -> Hibernate.initialize(book.getReviews()));
        List<Book> books = bookRepository.findAllWithReviews();
//        List<BookDTO> bookDTOS = books.stream().map(this::convertToDTO).collect(Collectors.toList());
//        return bookDTOS;
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    // Book->BookDTO
    private BookDTO convertToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPage(book.getPage());
        // Review->ReviewDTO converting 후 bookDTO의 reviews 에 넣어주기
        List<ReviewDTO> reviews=book.getReviews().stream().map(this::convertToDTO).collect(Collectors.toList());
        bookDTO.setReviews(reviews);

        return bookDTO;
    }
    // Review->ReviewDTO
    private ReviewDTO convertToDTO(Review review){

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setCost(review.getCost());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setCreatedAt(review.getCreatedAt());

        return reviewDTO;
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

}

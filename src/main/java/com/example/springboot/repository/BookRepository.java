package com.example.springboot.repository;

import com.example.springboot.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // 1. JPA에서 제공해주는 메서드(CRUD)
    // 2. Query Method
    // 3. JPQL(SQL을 직접 사용 가능)
    // 4. Querydsl(복잡한 SQL을 메서드 기반으로 사용) - x
    // select().from().where() >> 함수로 사용
    // 페치조인(fetch join)
//    @Query("select b from Book b LEFT JOIN FETCH b.reviews")
    // Entity Graph를 이용
    @EntityGraph(attributePaths = {"reviews"})
    @Query("select b from Book b")
    public List<Book> findAllWithReviews();
}

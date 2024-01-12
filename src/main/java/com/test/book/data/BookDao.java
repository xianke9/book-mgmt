package com.test.book.data;

import com.test.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface BookDao extends JpaRepository<Book, Serializable> {

    public List<Book> findByBookCategoryId(Integer id);

    public List<Book> findByIdIn(List<Integer> ids);


}

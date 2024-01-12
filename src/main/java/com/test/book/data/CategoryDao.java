package com.test.book.data;

import com.test.book.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CategoryDao extends JpaRepository<BookCategory, Serializable> {
}

package com.test.book.data;

import com.test.book.entity.Book;
import com.test.book.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface TeacherDao extends JpaRepository<Teacher, Serializable> {

    public Optional<Teacher> findByName(String name);
}

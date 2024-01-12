package com.test.book.data;

import com.test.book.entity.Book;
import com.test.book.entity.Student;
import com.test.book.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface StudentDao extends JpaRepository<Student, Serializable> {
    public Optional<Student> findByName(String name);



}

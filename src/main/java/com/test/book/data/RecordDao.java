package com.test.book.data;


import com.test.book.entity.Book;
import com.test.book.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface RecordDao extends JpaRepository<Record, Serializable> {


    public int deleteByBookIDAndStudentNumber(Integer id,String studentNumber);

    public int deleteByBookIDAndTeacherNumber(Integer id,String teacherNumber);

    public List<Record> findByStudentNumber(String number);

    public List<Record> findByStudentNumberIn(List<String> numbers);

    public List<Record> findByTeacherNumber(String number);

}

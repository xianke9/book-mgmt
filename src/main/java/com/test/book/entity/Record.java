package com.test.book.entity;

import com.test.book.utils.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_record")
public class Record {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="student_number")
    private String studentNumber;



    @Column(name="teacher_number")
    private String teacherNumber;


    @Column(name="book_id")
    private Integer bookID;


    @Column(name="book_date")
    private Date borrowDate;

    public String toString() {
        StringBuilder sb = new StringBuilder("|");
        sb.append(StringUtils.inputBalankWithSplit(2,this.getId().toString(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.studentNumber,20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getBookID().toString(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getBorrowDate().toString(),20));
        return sb.toString();
    }

}

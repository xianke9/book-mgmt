package com.test.book.entity;

import com.test.book.constants.Sex;
import com.test.book.customized.ConvertList;
import com.test.book.customized.SexEnumConvert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public class Person {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String name;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getBorrowedCount() {
        if (borrowedCount == null) {
            return 0;
        }
        return borrowedCount;
    }

    public void setBorrowedCount(Integer borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    public List<String> getBorrowedList() {
        if (borrowedList == null) {
            borrowedList = new ArrayList();
        }
        return borrowedList;
    }

    public void setBorrowedList(List<String> borrowedList) {
        this.borrowedList = borrowedList;
    }



    private Integer age;

    private String college;

    private Integer borrowedCount;

    @Convert(converter= ConvertList.class)
    private List<String> borrowedList;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String password;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Convert(converter = SexEnumConvert.class)
    private Sex sex;

}

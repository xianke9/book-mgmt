package com.test.book.entity;

import com.test.book.utils.StringUtils;

import javax.persistence.*;

@Table(name="t_book_category")
@Entity
public class BookCategory {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public String toString() {
        StringBuilder sb = new StringBuilder("|");
        sb.append(StringUtils.inputBalankWithSplit(2,this.getId().toString(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getName(),20));
        return sb.toString();
    }
}

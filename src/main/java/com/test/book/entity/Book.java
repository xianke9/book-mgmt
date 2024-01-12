package com.test.book.entity;

import com.test.book.utils.StringUtils;

import javax.persistence.*;

@Table(name="t_book")
@Entity
public class Book {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Integer getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(Integer bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    private Integer bookCategoryId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    private String author;

    private String publisher;

    private String topics;

    private String ISBN;

    private Integer availableCount;


    public String toString() {
        StringBuilder sb = new StringBuilder("|");
        sb.append(StringUtils.inputBalankWithSplit(2,this.getId().toString(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getName(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getAuthor(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getISBN(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getPublisher(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getAvailableCount().toString(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getTopics(),20));
        return sb.toString();
    }

}

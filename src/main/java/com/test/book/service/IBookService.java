package com.test.book.service;

import com.test.book.entity.Book;

import java.util.List;
import java.util.Scanner;

public interface IBookService {

    public String borrowBook(int book);

    public void returnBook(int book);

    public List<Book> getCurrentUserBook();

    public void showMyBooks(Scanner sc);

    public void showAllBooks(Scanner sc);

    public List<Book> getAll();

    public List<Book> getCategoryBooks(Integer id);
}

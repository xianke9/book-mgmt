package com.test.book.service;

import com.test.book.data.CategoryDao;
import com.test.book.entity.Book;
import com.test.book.entity.BookCategory;
import com.test.book.entity.Record;
import com.test.book.entity.Student;
import com.test.book.utils.ConsoleUtils;
import com.test.book.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao cdao;



    public List<BookCategory> getList(){
        return cdao.findAll();
    }

    public void showCategorys(Scanner sc) {
        Boolean loop = true;
        while (loop) {
            System.out.println("图书分类:");
            List<BookCategory> ctgList = this.getList();
            if (ctgList != null && ctgList.size() > 0) {
                StringBuilder sb = new StringBuilder("|");
                int lineSize = 0;
                for (int i = 0; i < ctgList.size(); i++) {
                    BookCategory book = ctgList.get(i);
                    String s = book.toString();
                    if (i == 0) {
                        lineSize = s.length();
                        sb.append(StringUtils.inputNChar(lineSize, "-"));
                        sb.append("\n");
                    }
                    sb.append(book.toString());
                    sb.append("\n");
                }
                sb.append(StringUtils.inputNChar(lineSize, "-"));
                sb.append("\n");
                System.out.println(sb.toString());
            }
            System.out.println("输入ID号，查看详细。");
            System.out.println("输入'0'，退出。");
            String input = ConsoleUtils.getConsoleInput(sc);
            switch (input) {
                case "0":
                    loop = false;
                    break;
                default:
                    try {
                        Integer id = Integer.parseInt(input);
                        this.showDetail(sc,id);
                    } catch (Exception e) {
                        System.out.println("输入错误");
                    }

            }
        }
    }

    @Autowired
    private IBookService bookService;

    private void showDetail(Scanner sc, Integer id) {
        Boolean loop = true;
        while (loop) {
            System.out.println("分类详细:");
            List<Book> books = bookService.getCategoryBooks(id);
            if (books != null) {
                    int lineSize = 0;
                    StringBuilder sb = new StringBuilder("|");
                    for (int i = 0; i < books.size(); i++) {
                        Book book = books.get(i);
                        String s = book.toString();
                        if (i == 0) {
                            lineSize = s.length();
                            sb.append(StringUtils.inputNChar(lineSize, "-"));
                            sb.append("\n");
                        }
                        sb.append(book.toString());
                        sb.append("\n");
                    }
                    sb.append(StringUtils.inputNChar(lineSize, "-"));
                    sb.append("\n");
                    System.out.println(sb.toString());
                    System.out.println("输入'0'，退出。");
                    String input = ConsoleUtils.getConsoleInput(sc);
                    switch (input) {
                        case "0":
                            loop = false;
                            break;
                        default:
                            System.out.println("输入错误");
                    }

            }
        }
    }

}

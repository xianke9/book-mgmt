package com.test.book.commands;

import com.test.book.data.StudentDao;
import com.test.book.data.TeacherDao;
import com.test.book.entity.Student;
import com.test.book.entity.Teacher;
import com.test.book.service.CategoryService;
import com.test.book.service.ClazzService;
import com.test.book.service.IBookService;
import com.test.book.service.UserService;
import com.test.book.session.Session;
import com.test.book.utils.ConsoleUtils;
import com.test.book.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class L2Command {

    @Autowired
    private UserService userService;

    @Autowired
    private IBookService bookService;

    private String studentLogin(String username, String password) {
        Optional<Student> t = stuRepo.findByName(username);
        if (t.isPresent()) {
            if (password.equals(t.get().getPassword())) {
                Session.createSession(t.get().getId(),username,true);
                List<KeyValue> keyValueList = Arrays.asList(
                        new KeyValue("1", "用户基本信息"),
                        new KeyValue("2", "书籍信息"),
                        new KeyValue("3", "已借书籍"),
                        new KeyValue("0", "返回上级")
                );


                StringBuilder sb = new StringBuilder();
                String tmp  = StringUtils.append("|",StringUtils.inputBalank(2, keyValueList.get(0).getCommand(), 20),"|",StringUtils.inputBalank(2, keyValueList.get(0).getDescription(), 20));
                int max = tmp.length();
                sb.append(StringUtils.inputNChar(max, "-"));
                sb.append("\n");
                keyValueList.stream().forEach(x->{
                    sb.append(StringUtils.append(StringUtils.append("|",StringUtils.inputBalank(2, x.getCommand(), 20),"|",StringUtils.inputBalank(2, x.getDescription(), 20)),"\n"));
                });

                return sb.toString();

            } else {
                throw new RuntimeException(LOGIN_PWD_ERROR);
            }


        } else {
            throw new RuntimeException(LOGIN_USER_NULL);

        }
    }


    @Autowired
    TeacherDao teacherRepo;

    @Autowired
    StudentDao stuRepo;

    @Autowired
    ClazzService clazzService;

    @Autowired
    CategoryService ctgService;

    private String teacherLogin(String username, String password) {
        Optional<Teacher> t = teacherRepo.findByName(username);
        if (t.isPresent()) {
            if (password.equals(t.get().getPassword())) {
                Session.createSession(t.get().getId(),username,false);

                List<KeyValue> keyValueList = Arrays.asList(
                        new KeyValue("1", "用户基本信息"),
                        new KeyValue("2", "书籍信息"),
                        new KeyValue("3", "已借书籍"),
                        new KeyValue("4", "相关班级"),
                        new KeyValue("5", "图书分类"),
                        new KeyValue("0", "返回上级")
                );
                StringBuilder sb = new StringBuilder();
                String tmp  =StringUtils.append("|",StringUtils.inputBalank(2, keyValueList.get(0).getCommand(), 20),"|",StringUtils.inputBalank(2, keyValueList.get(0).getDescription(), 20),"\n");
                int max = tmp.length();
                sb.append(StringUtils.inputNChar(max, "-"));
                sb.append("\n");
                keyValueList.stream().forEach(x->{
                    sb.append(StringUtils.append(StringUtils.append("|",StringUtils.inputBalank(2, x.getCommand(), 20),"|",StringUtils.inputBalank(2, x.getDescription(), 20)),"\n"));
                });

                return sb.toString();


            } else {
                throw new RuntimeException(LOGIN_PWD_ERROR);
            }


        } else {
            throw new RuntimeException(LOGIN_USER_NULL);
        }
    }


    public static final String LOGIN_PWD_ERROR = "登录失败,密码错误!";
    public static final String LOGIN_USER_NULL = "登录失败,不存在用户!";

    private LoginInfo getLoginInfo(Scanner sc){
        System.out.print("用户名:");
        String username = ConsoleUtils.getConsoleInput(sc);
        System.out.print("密 码:");
        String password = ConsoleUtils.getConsoleInput(sc);
        System.out.print("是学生吗？(Y/N):");
        String y = ConsoleUtils.getConsoleInput(sc);

        LoginInfo li = new LoginInfo();
        li.setUsername(username);
        li.setPassword(password);
        li.setY(y);
        return li;
    }

    public void handle(Scanner sc) {
        Boolean loop = true;
        LoginInfo loginInfo = getLoginInfo(sc);
        while (loop) {
            if (loginInfo.getY().toLowerCase().equals("y")) {//学生
                try {
                    String output = studentLogin(loginInfo.getUsername(), loginInfo.getPassword());
                    System.out.println(output);
                    System.out.print("输入序号进行相关操作:");
                    String input = ConsoleUtils.getConsoleInput(sc);
                    switch (input) {
                        case "1":
                            //用户基本信息
                            userService.userInfo(sc);
                            break;
                        case "0":
                            loop = false;
                            break;
                        case "2": //
                            bookService.showAllBooks(sc);
                            break;
                        case "3":
                            bookService.showMyBooks(sc);
                            break;
                        default:
                            System.out.println("输入错误");
                    }
                } catch (RuntimeException e) {
                    System.out.println("请重新登录!");
                    break;
                }
            } else if (loginInfo.getY().toLowerCase().equals("n")) {//老师
                try {
                    String output = teacherLogin(loginInfo.getUsername(), loginInfo.getPassword());
                    System.out.println(output);
                    System.out.print("输入序号进行相关操作:");
                    String input = ConsoleUtils.getConsoleInput(sc);
                    switch (input) {
                        case "1":
                            //用户基本信息
                            userService.userInfo(sc);
                            break;
                        case "0":
                            loop = false;
                            break;
                        case "2": //
                            bookService.showAllBooks(sc);
                            break;
                        case "3":
                            bookService.showMyBooks(sc);
                            break;
                        case "4":
                            clazzService.showClasses(sc);
                            break;
                        case "5":
                            ctgService.showCategorys(sc);
                            break;
                        default:
                            System.out.println("输入错误");
                    }
                } catch (RuntimeException e) {
                    System.out.println("请重新登录!");
                    break;

                }
            }
        }





    }

}

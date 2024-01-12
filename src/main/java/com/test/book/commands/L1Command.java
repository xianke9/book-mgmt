package com.test.book.commands;



import com.test.book.session.Session;

import com.test.book.utils.ConsoleUtils;
import com.test.book.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class L1Command {

    public static int N = 5;

    @Autowired
    L2Command l2;


    public void handle(Scanner sc){
        Boolean loop = true;
        while (loop) {
            System.out.println("------------------------------------------------------");
            System.out.println(StringUtils.append("|",StringUtils.inputBalank(N,"0-exist",20),"|",StringUtils.inputBalank(N,"退出",20),"|"));
            System.out.println(StringUtils.append("|",StringUtils.inputBalank(N,"1-login",20),"|",StringUtils.inputBalank(N,"登录",20),"|"));

            System.out.println("------------------------------------------------------");
            System.out.print("输入序号:");
            String consoleInput = ConsoleUtils.getConsoleInput(sc);
            switch (consoleInput) {
                case "1":
                    l2.handle(sc);
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    System.out.println("输入错误");
            }




        }




    }




//    private String studentLogin(String username, String password) {
//        Optional<Student> t = stuRepo.findByName(username);
//        if (t.isPresent()) {
//            if (password.equals(t.get().getPassword())) {
//                Session.createSession(t.get().getId(),username,true);
//                List<Command> keyValueList = Arrays.asList(
//                        new Command("1", "用户基本信息"),
//                        new Command("2", "书籍信息"),
//                        new Command("3", "以借书籍"),
//                        new Command("0", "返回上级")
//                );
//
//
//                StringBuilder sb = new StringBuilder();
//                String tmp  =StringUtils.append("|",StringUtils.inputBalank(2, keyValueList.get(0).getCommand(), 20),"|",StringUtils.inputBalank(2, keyValueList.get(0).getDescription(), 20));
//                int max = tmp.length();
//                sb.append(StringUtils.inputNChar(max, "-"));
//                sb.append("\n");
//                keyValueList.stream().forEach(x->{
//                    sb.append(StringUtils.append(StringUtils.append("|",StringUtils.inputBalank(2, x.getCommand(), 20),"|",StringUtils.inputBalank(2, x.getDescription(), 20)),"\n"));
//                });
//
//                return sb.toString();
//
//            } else {
//                throw new RuntimeException(LOGIN_PWD_ERROR);
//            }
//
//
//        } else {
//            throw new RuntimeException(LOGIN_USER_NULL);
//
//        }
//    }
//
//    private String teacherLogin(String username, String password) {
//        Optional<Teacher> t = teacherRepo.findByName(username);
//        if (t.isPresent()) {
//            if (password.equals(t.get().getPassword())) {
//                Session.createSession(t.get().getId(),username,false);
//
//                List<Command> keyValueList = Arrays.asList(
//                        new Command("1", "用户基本信息"),
//                        new Command("2", "书籍信息"),
//                        new Command("3", "以借书籍"),
//                        new Command("0", "返回上级")
//                );
//                StringBuilder sb = new StringBuilder();
//                String tmp  =StringUtils.append("|",StringUtils.inputBalank(2, keyValueList.get(0).getCommand(), 20),"|",StringUtils.inputBalank(2, keyValueList.get(0).getDescription(), 20),"\n");
//                int max = tmp.length();
//                sb.append(StringUtils.inputNChar(max, "-"));
//                sb.append("\n");
//                keyValueList.stream().forEach(x->{
//                    sb.append(StringUtils.append(StringUtils.append("|",StringUtils.inputBalank(2, x.getCommand(), 20),"|",StringUtils.inputBalank(2, x.getDescription(), 20)),"\n"));
//                });
//
//                return sb.toString();
//
//
//            } else {
//                throw new RuntimeException(LOGIN_PWD_ERROR);
//            }
//
//
//        } else {
//            throw new RuntimeException(LOGIN_USER_NULL);
//        }
//    }



//    public Availability loginAvailability() {
//
//        return !Session.isLogin()
//                ? Availability.available()
//                : Availability.unavailable("you has logined");
//    }


//    @ShellMethod(key = "logout", interactionMode = InteractionMode.INTERACTIVE)
    public String logout(){

        Session.logout();
        return  "已退出，感谢使用!";
    }

//    public static CellMatcher at(final int theRow, final int col) {
//        return new CellMatcher() {
//            @Override
//            public boolean matches(int row, int column, TableModel model) {
//                return row == theRow && column == col;
//            }
//        };
//    }
}


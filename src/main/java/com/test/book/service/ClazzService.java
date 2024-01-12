package com.test.book.service;

import com.test.book.data.ClazzDao;
import com.test.book.data.RecordDao;
import com.test.book.data.TeacherDao;
import com.test.book.entity.*;
import com.test.book.session.Session;
import com.test.book.utils.ConsoleUtils;
import com.test.book.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class ClazzService {

    @Autowired
    private ClazzDao clazzDao;


    @Autowired
    private TeacherDao teacherDao;

    public ClazzService() {
    }

    public List<Clazz> getClazzList() {
        List<Clazz> all = clazzDao.findAll();
        Integer teacherID = Session.CurrentUserId();
        Optional<Teacher> t = teacherDao.findById(teacherID);
        if (t.isPresent()) {
            List<Clazz> classList = t.get().getClazzes().stream().collect(Collectors.toList());
            return classList;

        }

        return null;
    }

    public void showClasses(Scanner sc) {
        Boolean loop = true;
        while (loop) {
            System.out.println("我的班级:");
            List<Clazz> clazzes = this.getClazzList();
            if (clazzes != null && clazzes.size() > 0) {
                StringBuilder sb = new StringBuilder("|");
                int lineSize = 0;

                for (int i = 0; i < clazzes.size(); i++) {
                    Clazz cls = clazzes.get(i);
                    String s = cls.toString();
                    if (i == 0) {
                        lineSize = s.length();
                        sb.append(StringUtils.inputNChar(lineSize, "-"));
                        sb.append("\n");
                    }
                    sb.append(cls.toString());
                    sb.append("\n");
                }
                sb.append(StringUtils.inputNChar(lineSize, "-"));
                sb.append("\n");
                System.out.println(sb.toString());
            }
            System.out.println("输入ID号,查看详情.");
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
    private UserService userService;

    @Autowired
    private RecordDao recDao;


    public void showDetail(Scanner sc,Integer clazzid) {
        Boolean loop = true;
        while (loop) {
            System.out.println("班级借书信息:");
            List<Student> studentsByClazz = userService.getStudentsByClazz(clazzid);
            if (studentsByClazz != null) {
                List<String> collect = studentsByClazz.stream().map(Student::getStudentNumber).collect(Collectors.toList());
                List<Record> recList = recDao.findByStudentNumberIn(collect);
                System.out.println("借书详情：");
                if (recList != null) {
                    int lineSize = 0;
                    StringBuilder sb = new StringBuilder("|");
                    for (int i = 0; i < recList.size(); i++) {
                        Record cls = recList.get(i);
                        String s = cls.toString();
                        if (i == 0) {
                            lineSize = s.length();
                            sb.append(StringUtils.inputNChar(lineSize, "-"));
                            sb.append("\n");
                        }
                        sb.append(cls.toString());
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



}

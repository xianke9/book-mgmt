package com.test.book.service;

import com.test.book.data.StudentDao;
import com.test.book.data.TeacherDao;
import com.test.book.entity.Person;
import com.test.book.entity.Student;
import com.test.book.entity.Teacher;
import com.test.book.session.Session;
import com.test.book.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private StudentDao stuDao;

    @Autowired
    private TeacherDao teacherDao;


    public void userInfo(Scanner sc) {

        String user = Session.CurrentUserName();
        Person person = null;
        if (Session.isStudent()) {
            person =  stuDao.findByName(user).get();
        } else {
            person =  teacherDao.findByName(user).get();
        }
        String s = person.toString();
        int len = s.length();
        System.out.println(StringUtils.inputNChar(len, "-"));
        System.out.println(s);
        System.out.println(StringUtils.inputNChar(len, "-"));

    }


    public Person currentPerson() {
        String user = Session.CurrentUserName();

        Person person = null;
        if (Session.isStudent()) {
            person =  stuDao.findByName(user).get();
        } else {
            person =  teacherDao.findByName(user).get();
        }
        return person;
    }


    public List<Student> getStudentsByClazz(Integer classId) {

        List<Student> all = stuDao.findAll();
        if (all != null) {
            List<Student> collect = all.stream().filter(x -> x.getClazz().getId().equals(classId)).collect(Collectors.toList());
            return collect;
        }
        return null;


    }

}

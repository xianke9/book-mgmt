package com.test.book.service;

import com.test.book.entity.Person;
import com.test.book.entity.Student;

import java.util.List;
import java.util.Scanner;

public interface IUserService {


    public void userInfo(Scanner sc);

    public Person currentPerson();

    public List<Student> getStudentsByClazz(Integer classId);
}
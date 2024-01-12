package com.test.book.service;

import com.test.book.data.BookDao;
import com.test.book.data.RecordDao;
import com.test.book.data.StudentDao;
import com.test.book.data.TeacherDao;
import com.test.book.entity.*;
import com.test.book.session.Session;
import com.test.book.utils.ConsoleUtils;
import com.test.book.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookService implements IBookService{


    @Autowired
    private BookDao repo;

    @Autowired
    private StudentDao stuRepo;

    @Autowired
    private TeacherDao teacherRepo;


    @Transactional
    public String borrowBook(int id) {

        Book book = repo.getReferenceById(id);

        if (book == null) {
            return "书不能不存在";
        }
        String user = Session.CurrentUserName();
        Record rec = new Record();
        if (Session.isStudent()) {
            Optional<Student> byName = stuRepo.findByName(user);
            if (byName.isPresent()) {
                Student student = byName.get();
                int borrowedCount = student.getBorrowedCount();
                if (borrowedCount >= 2) {
                    return "不能借阅，已经借了2本.学生最多借2本";
                }
                student.setBorrowedCount(student.getBorrowedCount() + 1);
                student.getBorrowedList().add(book.getName());
                stuRepo.save(student);
                rec.setStudentNumber(student.getStudentNumber());
            }
        } else {
            Optional<Teacher> byName = teacherRepo.findByName(user);
            if (byName.isPresent()) {
                Teacher teacher = byName.get();
                int borrowedCount = teacher.getBorrowedCount();
                if (borrowedCount >= 4) {
                    return "不能借阅，已经借了4本.老师最多借4本";
                }
                teacher.setBorrowedCount(teacher.getBorrowedCount() + 1);
                teacher.getBorrowedList().add(book.getName());
                rec.setTeacherNumber(teacher.getTeacherNumber());

                teacherRepo.save(teacher);
            }
        }

        if (book.getId() != null && !"".equals(book.getId())) {
            if (book.getAvailableCount() > 0){
                book.setAvailableCount(book.getAvailableCount() - 1);
            }
        }

        rec.setBookID(book.getId());
        rec.setBorrowDate(new Date());
        recRepo.save(rec);
        repo.save(book);
        return "借书成功";

    }

    @Autowired
    private RecordDao recRepo;


    @Transactional
    public void returnBook(int id) {
        Book book = repo.getReferenceById(id);

        if (book == null) {
            return;
        }
        String user = Session.CurrentUserName();
        if (Session.isStudent()) {
            Optional<Student> byName = stuRepo.findByName(user);
            if (byName.isPresent()) {
                Student student = byName.get();
                if (student.getBorrowedCount() == 0) {
                    return;
                }
                student.setBorrowedCount(student.getBorrowedCount() - 1);
                student.getBorrowedList().remove(book.getName());
                stuRepo.save(student);
                recRepo.deleteByBookIDAndStudentNumber(book.getId(), student.getStudentNumber());
            }

        } else {
            Optional<Teacher> byName = teacherRepo.findByName(user);
            if (byName.isPresent()) {
                Teacher teacher = byName.get();
                if (teacher.getBorrowedCount() == 0) {
                    return;
                }
                teacher.setBorrowedCount(teacher.getBorrowedCount() - 1);
                teacher.getBorrowedList().remove(book.getName());
                teacherRepo.save(teacher);
                recRepo.deleteByBookIDAndTeacherNumber(book.getId(), teacher.getTeacherNumber());

            }
        }

        if (book.getId()  != null && !"".equals(book.getId())) {
            book.setAvailableCount(book.getAvailableCount() + 1);

        }

        repo.save(book);
    }

    @Autowired
    private UserService userService;

    public List<Book> getCurrentUserBook() {
        Person person = userService.currentPerson();
        if (person instanceof Teacher) {
            Teacher t = (Teacher)person;
            List<Record> records = recRepo.findByTeacherNumber(t.getTeacherNumber());
            if (records != null && records.size() > 0) {
                List<Integer> ids = new ArrayList<>();
                for (Record r : records) {
                    ids.add(r.getBookID());
                }
                return repo.findByIdIn(ids);
            }
        }  else {
            Student stu = (Student) person;
            List<Record> records = recRepo.findByStudentNumber(stu.getStudentNumber());
            if (records != null && records.size() > 0) {
                List<Integer> ids = new ArrayList<>();
                for (Record r : records) {
                    ids.add(r.getBookID());
                }
                return repo.findByIdIn(ids);
            }

        }
        return null;
    }

    @Transactional
    public void showMyBooks(Scanner sc) {
        Boolean loop = true;
        while (loop) {
            System.out.println("已借书籍:");
            List<Book> currentUserBooks = this.getCurrentUserBook();
            if (currentUserBooks != null && currentUserBooks.size() > 0) {

                StringBuilder sb = new StringBuilder("|");
                int lineSize = 0;

                for (int i = 0; i < currentUserBooks.size(); i++) {
                    Book book = currentUserBooks.get(i);
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
            System.out.println("输入ID号，还书。");
            System.out.println("输入'0'，退出。");
            String input = ConsoleUtils.getConsoleInput(sc);
            switch (input) {
                case "0":
                    loop = false;
                    break;
                default:
                    try {
                        Integer id = Integer.parseInt(input);
                        this.returnBook(id);
                    } catch (Exception e) {
                        System.out.println("输入错误");
                    }

            }
        }
    }

    @Transactional
    public void showAllBooks(Scanner sc) {
        Boolean loop = true;
        while (loop) {
            System.out.println("所有书籍：");
            List<Book> currentUserBooks = repo.findAll();
            StringBuilder sb = new StringBuilder("|");
            int lineSize = 0;
            for (int i = 0; i < currentUserBooks.size(); i++) {
                Book book = currentUserBooks.get(i);
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
            System.out.println("输入ID号，借书。");
            System.out.println("输入'0'，退出。");
            String input = ConsoleUtils.getConsoleInput(sc);
            switch (input) {
                case "0":
                    loop = false;
                    break;
                default:
                    try {
                        Integer id = Integer.parseInt(input);
                        this.borrowBook(id);
                    } catch (Exception e) {
                        System.out.println("输入错误");
                    }





            }
        }

    }

    @Override
    public List<Book> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Book> getCategoryBooks(Integer id) {
        return repo.findByBookCategoryId(id);
    }
}

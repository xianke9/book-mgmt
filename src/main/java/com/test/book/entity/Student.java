package com.test.book.entity;


import com.test.book.utils.StringUtils;

import javax.persistence.*;

@Table(name="t_student")
@Entity
public class Student extends Person{

     public String getStudentNumber() {
          return studentNumber;
     }

     public void setStudentNumber(String studentNumber) {
          this.studentNumber = studentNumber;
     }

     @Column(name="student_number",unique=true)
     private String studentNumber;


     public Clazz getClazz() {
          return clazz;
     }

     public void setClazz(Clazz clazz) {
          this.clazz = clazz;
     }

     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "clazz")
     private Clazz clazz;

     public String toString(){

          StringBuilder sb = new StringBuilder("|");
          sb.append(StringUtils.inputBalankWithSplit(2,this.getId().toString(),20));
          sb.append(StringUtils.inputBalankWithSplit(2,this.getName(),20));
          sb.append(StringUtils.inputBalankWithSplit(2,this.getSex().getValue(),20));
          sb.append(StringUtils.inputBalankWithSplit(2,this.getClazz().getClassName(),20));
          sb.append(StringUtils.inputBalankWithSplit(2,this.getStudentNumber(),20));
          sb.append(StringUtils.inputBalankWithSplit(2,this.getBorrowedCount()+"",20));
          sb.append(StringUtils.inputBalankWithSplit(2,this.getBorrowedList().toString(),40));
          return sb.toString();
     }
}

package com.test.book.entity;


import com.test.book.utils.StringUtils;

import javax.persistence.*;
import java.util.Set;

@Table(name="t_teacher")
@Entity
public class Teacher extends Person{



    public Integer getFromDate() {
        return fromDate;
    }

    public void setFromDate(Integer fromDate) {
        this.fromDate = fromDate;
    }

    @Column(name="teacher_number",unique=true)
    private String teacherNumber;

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    private Integer fromDate;


    public Set<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(Set<Clazz> clazzes) {
        this.clazzes = clazzes;
    }

    @ManyToMany(mappedBy="teachers",fetch = FetchType.EAGER)
    private Set<Clazz> clazzes;

    public String toString(){

        StringBuilder sb = new StringBuilder("|");

        sb.append(StringUtils.inputBalankWithSplit(2,this.getId().toString(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getName(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getSex().getValue(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getTeacherNumber(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getBorrowedCount()+"",20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getBorrowedList().toString(),40));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getCollege(),40));
        return sb.toString();
    }
}

package com.test.book.entity;

import com.test.book.utils.StringUtils;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="t_class")
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    private String className;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="t_class_teacher",joinColumns = {
            @JoinColumn(name="class_id",referencedColumnName = "id"),
    },inverseJoinColumns = {
            @JoinColumn(name="teacher_id",referencedColumnName = "id")
    })
    private Set<Teacher> teachers;


    public String toString() {
        StringBuilder sb = new StringBuilder("|");
        sb.append(StringUtils.inputBalankWithSplit(2,this.getId().toString(),20));
        sb.append(StringUtils.inputBalankWithSplit(2,this.getClassName(),20));
        return sb.toString();
    }

}

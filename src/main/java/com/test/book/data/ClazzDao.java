package com.test.book.data;

import com.test.book.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ClazzDao extends JpaRepository<Clazz, Serializable> {



}

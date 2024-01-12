package com.test.book.session;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private static Session caches = new Session();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    public Boolean isaStudent() {
        return aStudent;
    }

    public void setaStudent(Boolean aStudent) {
        this.aStudent = aStudent;
    }

    private boolean aStudent;


    public static Session createSession(Integer id,String username,Boolean aStudent){
        caches.setId(id);
        caches.setUsername(username);
        caches.setaStudent(aStudent);
        return caches;
    }

    public static boolean isLogin() {

        if (caches.getUsername() != null) {
            return true;
        }
        return false;
    }

    public static boolean isStudent() {

        return caches.aStudent;
    }

    public static String CurrentUserName() {

        return caches.getUsername();

    }

    public static Integer CurrentUserId() {

        return caches.getId();

    }
        public static void logout() {
            caches.setId(null);
        caches.setUsername(null);
        caches.setaStudent(null);
    }

}

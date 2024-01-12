package com.test.book.validate;

import com.test.book.BookApplication;
import com.test.book.session.Session;

public class Validate {



    public static boolean isLogin() {
        return Session.isLogin();
    }

}

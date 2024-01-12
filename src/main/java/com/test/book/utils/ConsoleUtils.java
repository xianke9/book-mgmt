package com.test.book.utils;

import java.util.Scanner;

public class ConsoleUtils {

    public static String getConsoleInput(Scanner sc){

        if (sc == null) {
            return null;
        }
        String s = sc.nextLine();
        return s;
    }
}

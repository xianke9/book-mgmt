package com.test.book.customized;

import com.test.book.commands.L1Command;
import com.test.book.utils.ConsoleUtils;
import com.test.book.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private L1Command loginCommand;

    public void onApplicationEvent(ApplicationReadyEvent event) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("欢迎来到图书管理系统");
                Scanner scanner = new Scanner(System.in);
                boolean loop = true;
                while(loop){
                    loop = false;

                    loginCommand.handle(scanner);
                }

            }
        });
        t.start();
    }
}

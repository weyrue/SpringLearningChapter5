package com.zy.advisor;

import org.springframework.stereotype.Component;

@Component
public class Waiter {
    public void greetTo(String name) {
        System.out.println("Waiter greet to " + name + "...");
    }

    public void serveTo(String name) {
        System.out.println("Waiter serving " + name + "...");
    }
}

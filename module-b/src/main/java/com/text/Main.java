package com.text;

import com.test.User;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) {

        try {
            User user = new User("AAA", 18);
            Class<User> userClass = User.class;
            Field field = userClass.getDeclaredField("name");
            field.setAccessible(true);
            System.out.println(field.get(user));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}

package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
byte age = 10;
        UserServiceImpl userService = new UserServiceImpl();
        //userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("artem","dunaev", age );
        userService.saveUser("ivan","ivanov", (byte) 21);
        userService.saveUser("Semen","semenov", (byte) 30);
        userService.saveUser("Misha","petrov", (byte) 15);

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

        // реализуйте алгоритм здесь
    }
}

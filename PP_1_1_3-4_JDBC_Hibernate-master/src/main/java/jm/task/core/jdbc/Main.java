package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Александ", "Петров", (byte) 15);
        userService.saveUser("Сергей", "Летов", (byte) 26);
        userService.saveUser("Владимир", "Пушкин", (byte) 23);
        userService.saveUser("Алексей", "Орлов", (byte) 42);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSessionFactory();
    }
}

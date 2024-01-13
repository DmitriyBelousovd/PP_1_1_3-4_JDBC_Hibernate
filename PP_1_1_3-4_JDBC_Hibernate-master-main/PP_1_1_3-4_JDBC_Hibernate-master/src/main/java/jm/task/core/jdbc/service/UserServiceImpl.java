package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao USER_DAO = new UserDaoHibernateImpl();

    public void createUsersTable() {
        USER_DAO.createUsersTable();
    }

    public void dropUsersTable() {
        USER_DAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        USER_DAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        USER_DAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return USER_DAO.getAllUsers();
    }

    public void cleanUsersTable() {
        USER_DAO.cleanUsersTable();
    }
}
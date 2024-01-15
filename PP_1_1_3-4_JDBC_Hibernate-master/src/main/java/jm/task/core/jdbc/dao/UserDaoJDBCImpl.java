package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS user (
                id SERIAL PRIMARY KEY ,
                name CHAR(50) NOT NULL,
                last_name CHAR(50) NOT NULL,
                age SMALLINT NOT NULL
            )
            """;
    private static final String DROP_TABLE = """
            DROP TABLE IF EXISTS user
            """;
    private static final String SAVE_USER = """
            INSERT INTO user (name, last_name, age)
            VALUES (?, ?, ?)
            """;

    private static final String GET_ALL_USERS = """
            SELECT *
            FROM user
            """;

    private static final String REMOVE_USER_BY_ID = """
            DELETE
            FROM user
            WHERE id = ?
            """;

    private static final String CLEAN_TABLE = """
            TRUNCATE user
            """;

    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             var ps = connection.prepareStatement(CREATE_TABLE)) {
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             var ps = connection.prepareStatement(DROP_TABLE)) {
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             var ps = connection.prepareStatement(SAVE_USER)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        System.out.printf("User с именем – %s добавлен в базу данных ", name);
        System.out.println();
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             var ps = connection.prepareStatement(REMOVE_USER_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             var ps = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                System.out.println(user);
            }
            return userList;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             var ps = connection.prepareStatement(CLEAN_TABLE)) {
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    User user = new User();

    public void createUsersTable() {
        String creatSQL = "create table users\n" +
                "(\n" +
                "    Id       int         not null\n" +
                "        primary key auto_increment,\n" +
                "    Name     varchar(50) ,\n" +
                "    LastName varchar(50) ,\n" +
                "    Age      tinyint    \n" +
                ");";
        try {
            try (Statement statement = Util.getConnection().createStatement()) {
                statement.executeUpdate(creatSQL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropSQL = "DROP TABLE IF EXISTS Users";
        try {
            try (Statement statement = Util.getConnection().createStatement()) {
                statement.executeUpdate(dropSQL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSql = "insert into Users (Name, LastName, Age)  values( ?, ? , ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(saveSql)) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
String removeSql = "delete from Users where id = ? ";
        try {
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(removeSql)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String getSql = "select * from Users";
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = Util.getConnection().prepareStatement(getSql)) {
            ResultSet rs = ps.executeQuery();


            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("Id"));
                user.setName(rs.getString("Name"));
                user.setLastName(rs.getString("LastName"));
                user.setAge(rs.getByte("Age"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
String cleanSql = "TRUNCATE TABLE users";
        try (PreparedStatement ps = Util.getConnection().prepareStatement(cleanSql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

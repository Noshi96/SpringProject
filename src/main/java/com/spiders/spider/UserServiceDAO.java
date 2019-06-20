package com.spiders.spider;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class UserServiceDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Users> getUsers(){
        List<Users> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Collection<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        rows.stream().map((row)->{
            Users users = new Users();
            users.setId((Integer) row.get("id"));
            users.setName((String) row.get("login"));
            users.setRole((String) row.get("role"));
            users.setPassword((String) row.get("password"));
            return users;
        }).forEach((user)->{

            userList.add(user);
        });
        return userList;
    }

    public void createUser(Users user) {
        jdbcTemplate.update((Connection connection)->{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (login,role,password) VALUES (?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.setString(3, user.getPassword());
            return preparedStatement;
        });
    }

    public void updateUser(Users user, int id) {
        jdbcTemplate.update((Connection connection)->{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET login=?, role=?, password=? WHERE id="+ id +"");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.setString(3, user.getPassword());
            return preparedStatement;
        });
    }

    public void deleteUser(int id) {
        jdbcTemplate.update((Connection connection)->{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id="+ id +"");
            return preparedStatement;
        });
    }

    public List<Users> getById(int id){
        List<Users> userList = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE id='"+ id +"'";
        Collection<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        rows.stream().map((row)->{
            Users users = new Users();
            users.setId((Integer) row.get("id"));
            users.setName((String) row.get("login"));
            users.setRole((String) row.get("role"));
            users.setPassword((String) row.get("password"));
            return users;
        }).forEach((user)->{

            userList.add(user);
        });
        return userList;
    }

    public List<Users> sqlQuery(String sql){
        List<Users> userList = new ArrayList<>();
        Collection<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        rows.stream().map((row)->{
            Users users = new Users();
            users.setId((Integer) row.get("id"));
            users.setName((String) row.get("login"));
            users.setRole((String) row.get("role"));
            users.setPassword((String) row.get("password"));
            return users;
        }).forEach((user)->{

            userList.add(user);
        });
        return userList;
    }

    public String getByName(String login){
        return "SELECT * FROM users WHERE login='"+ login +"'";
    }

    public String getByPassword(String password){
        return "SELECT * FROM users WHERE password='"+ password +"'";
    }


    //--------------------------------------------------------------------------------------------------------------------

}

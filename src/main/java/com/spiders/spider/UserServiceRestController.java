package com.spiders.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class UserServiceRestController {

    @Autowired
    UserServiceDAO userServiceDAO;

    /**
     * Metoda wyświetla listę wszsytkich użytkowników w bazie.
     * Metoda : [GET]
     * URL    : /users
     * SQL    : SELECT      {"SELECT * FROM users"}
     * TABELA : users
     *
     * @return Listę wszsytkich użytkowników w bazie w formacie JSON.
     */
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<Users> getUsers(){
        return userServiceDAO.getUsers();
    }

    /**
     * Metoda pozwala na wprowadzenie nowego użytkownika.
     * Metoda : [POST]
     * URL    : /users
     * SQL    : INSERT      {"INSERT INTO users (login,role,password) VALUES (?,?,?);"}
     * TABELA : users
     *
     * @param user Podany nowy użytkownik, który dostanie role oraz name.
     * @return Informację że dodano poprawnie użytkownika.
     */
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String insertUser(@RequestBody Users user){
        userServiceDAO.createUser(user);
        return "User created successfully";
    }

    /**
     * Metoda pozwala na aktualizację podanego użytkownika o podanym w parametrze url identyfikatorze.
     * Metoda : [POST]
     * URL    : /users/{id}
     * SQL    : UPDATE      {"UPDATE users SET login=?, role=?, password=? WHERE id="+ id +";"}
     * TABELA : users
     *
     * @param user Podany użytkownik, który będzie aktualizowany.
     * @param id Identyfikator podawany w adresie url.
     * @return Informację, że zaktualizowano poprawnie użytkownika.
     */
    @RequestMapping(value="/users/{id}", method=RequestMethod.POST)
    public String updateUser(@RequestBody Users user, @PathVariable int id){
        userServiceDAO.updateUser(user, id);
        return "User updated successfully";
    }

    /**
     * Metoda pozwala na usunięcie podanego użytkownika o podanym w parametrze url identyfikatorze.
     * Metoda : [POST]
     * URL    : /delUser/{id}
     * SQL    : DELETE      {"DELETE FROM users WHERE id="+ id +";"}
     * TABELA : users
     *
     * @param id Identyfikator podawany w adresie url.
     * @return Informację, że usunięto poprawnie użytkownika.
     */
    @RequestMapping(value = "/delUser/{id}", method = RequestMethod.POST)
    public String addUser(@PathVariable int id){
        userServiceDAO.deleteUser(id);
        return "User deleted successfully";
    }

    /**
     * Metoda pozwala na znalezienie i pokazanie użytkownika o podanym w url identyfikatorze.
     * Metoda : [GET]
     * URL    : /getById/{id}
     * SQL    : SELECT      {"SELECT * FROM users WHERE id='"+ id +"';"}
     * TABELA : users
     *
     * @param id Identyfikator podawany w adresie url.
     * @return Pojedyńczy rekord o podanym ID jako lista w formacie JSON.
     */
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public List<Users> getById(@PathVariable int id){
        return userServiceDAO.getById(id);
    }

    /**
     * Metoda pozwala na znalezienie i pokazanie użytkownika o podanym w url imieniu.
     *      Wykorzystywana jest tutaj metoda sqlQuery(), która pozwala tworzyć zapytania SQL.
     *      Metoda getByName() tworzy zapytanie SQL o parametrze WHERE=name.
     * Metoda : [GET]
     * URL    : /getByName/{name}
     * SQL    : SELECT      {"SELECT * FROM users WHERE login='"+ login +"';"}
     * TABELA : users
     *
     * @param name imię użytkownika podawane w adresie url.
     * @return Lista z jednym użytkownikiem znalezionym po loginie w formacie JSON.
     */
    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET)
    public List<Users> getByName(@PathVariable String name){
        return userServiceDAO.sqlQuery(userServiceDAO.getByName(name));
    }

    /**
     * Metoda pozwala na znalezienie i pokazanie użytkownika o podanym w url haśle.
     *      Wykorzystywana jest tutaj metoda sqlQuery(), która pozwala tworzyć zapytania SQL.
     *      Metoda getByPassword() tworzy zapytanie SQL o parametrze WHERE=password.
     * Metoda : [GET]
     * URL    : /getByPassword/{password}
     * SQL    : SELECT      {"SELECT * FROM users WHERE password='"+ password +"';"}
     * TABELA : users
     *
     * @param password imię użytkownika podawane w adresie url.
     * @return Lista z jednym użytkownikiem znalezionym po haśle w formacie JSON.
     */
    @RequestMapping(value = "/getByPassword/{password}", method = RequestMethod.GET)
    public List<Users> getByPassword(@PathVariable String password){
        return userServiceDAO.sqlQuery(userServiceDAO.getByPassword(password));
    }
}

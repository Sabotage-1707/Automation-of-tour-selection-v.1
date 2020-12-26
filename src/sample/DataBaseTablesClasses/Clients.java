package sample.DataBaseTablesClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DatabaseHadler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Clients {
    private int id;
    private String Login;
    private String Name;
    private String Passport;
    private String Password;
    private int Age;
    private int idPayment;
    private String Phone;


    public Clients(int id, String login, String name, String passport, String password, int age, int idPayment, String phone) {
        this.id = id;
        Login = login;
        Name = name;
        Passport = passport;
        Password = password;
        Age = age;
        this.idPayment = idPayment;
        Phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassport() {
        return Passport;
    }

    public void setPassport(String passport) {
        Passport = passport;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }



}

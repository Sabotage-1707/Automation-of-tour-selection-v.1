package sample;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import sample.DataBaseTablesClasses.*;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseHadler {
    Connection connection;
    ObservableList observableList;
    PreparedStatement pst;
    ResultSet rs;
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:sqlserver://localhost;integratedSecurity=true;databaseName=Tourism";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(connectionString);
        return connection;
    }
    public Connection getSQLServerConnection_JTDS() throws ClassNotFoundException, SQLException {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");

        // jdbc:jtds:sqlserver://localhost:1433/simplehr;instance=SQLEXPRESS
        String connectionURL = "jdbc:jtds:sqlserver://localhost/Tourism";

        Connection conn = DriverManager.getConnection(connectionURL);
        return conn;
    }
    public void showReport(String name){
        try {
            JasperReport jasperReport = JasperCompileManager
                    .compileReport(String.format("%s\\src\\sample\\AdditionalMenu\\Reports\\%s",
                            System.getProperty("user.dir"), name));
            Map<String, Object> parameters = new HashMap<String, Object>();

            connection = getSQLServerConnection_JTDS();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Clients> getDataClients(){
        observableList = FXCollections.observableArrayList();
        try {
            //dbHadler = new DatabaseHadler();
            connection = getConnection();
            pst = connection.prepareStatement("Select * from Clients");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new Clients( rs.getInt("id"), rs.getString("Login"), rs.getString("Name"), rs.getString("Passport"), rs.getString("Password"),
                                rs.getInt("Age"), rs.getInt("idPayment"),rs.getString("Phone")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<Companies> getDataCompanies(){
        observableList = FXCollections.observableArrayList();
        try {
            //dbHadler = new DatabaseHadler();
            connection = getConnection();
            pst = connection.prepareStatement("Select * from Companies");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new Companies( rs.getInt("id"), rs.getString("CompanyName"),rs.getDouble("Profit"), rs.getString("CompanyAdres")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<Countries> getDataCourties(){
        observableList = FXCollections.observableArrayList();
        try {
            //dbHadler = new DatabaseHadler();
            connection = getConnection();
            pst = connection.prepareStatement("Select * from Countries");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new Countries( rs.getInt("id"), rs.getString("CountryName"), rs.getString("SignsCountry")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<Turs> getDataTurs(){
        observableList = FXCollections.observableArrayList();
        try {
            connection = getConnection();
            pst = connection.prepareStatement("Select * from Turs");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new Turs( rs.getInt("id"),
                        rs.getString("HotelName"),
                        rs.getString("Ecscursions"),
                        rs.getDate("DateStart"),
                        rs.getDate("DateFinish"),
                        rs.getDouble("Price"),
                        rs.getString("Photo"),
                        rs.getInt("idHotel"),
                        rs.getInt("idCountry"),
                        rs.getInt("idDiscount"),
                        rs.getInt("idFood"),
                        rs.getInt("idTransit")
                ));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<TursOfClients> getDataBurningTurs(){
        observableList = FXCollections.observableArrayList();
        try {
            //dbHadler = new DatabaseHadler();
            connection = getConnection();
            pst = connection.prepareStatement("Select TursOfClients.id as id,IdClient as idClient ,TursOfClients.IdTure as idTure, CounterPeople, CounterLess14yo, BukingDate, FinalPrice from TursOfClients Join Turs On (TursOfClients.IdTure = Turs.id) Join ViewsOfDiscounts On(Turs.IdDiscount = ViewsOfDiscounts.id) where NameOFDiscount like '%Горящее предложение%'");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new TursOfClients( rs.getInt("id"), rs.getInt("idClient"),rs.getInt("idTure"),rs.getInt("CounterPeople"),rs.getInt("CounterLess14yo"),rs.getDate("BukingDate"),rs.getDouble("FinalPrice")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<TursOfClients> getDataTursOfClients(){
        observableList = FXCollections.observableArrayList();
        try {
            //dbHadler = new DatabaseHadler();
            connection = getConnection();
            pst = connection.prepareStatement("Select * from TursOfClients");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new TursOfClients( rs.getInt("id"), rs.getInt("idClient"),rs.getInt("idTure"),rs.getInt("CounterPeople"),rs.getInt("CounterLess14yo"),rs.getDate("BukingDate"),rs.getDouble("FinalPrice")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<TursOfCompanies> getDataTursOfCompanies(){
        observableList = FXCollections.observableArrayList();
        try {
            connection = getConnection();
            pst = connection.prepareStatement("Select * from TursOfCompanies");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new TursOfCompanies( rs.getInt("id"), rs.getInt("IdCompany"),rs.getInt("IdTure"),rs.getInt("Counter")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<ViewsOfDiscounts> getDataViewsOfDiscounts(){
        observableList = FXCollections.observableArrayList();
        try {
            connection = getConnection();
            pst = connection.prepareStatement("Select * from ViewsOfDiscounts");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new ViewsOfDiscounts( rs.getInt("id"), rs.getString("NameOFDiscount"),rs.getString("PercentsOfDiscount")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<ViewsOfFood> getDataViewsOfFood(){
        observableList = FXCollections.observableArrayList();
        try {
            connection = getConnection();
            pst = connection.prepareStatement("Select * from ViewsOfFood");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new ViewsOfFood( rs.getInt("id"), rs.getString("Name")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<ViewsOfHotels> getDataViewsOfHotels(){
        observableList = FXCollections.observableArrayList();
        try {
            connection = getConnection();
            pst = connection.prepareStatement("Select * from ViewsOfHotels");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new ViewsOfHotels( rs.getInt("id"), rs.getString("HotelClass")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<ViewsOfPayments> getDataViewsOfPayments(){
        observableList = FXCollections.observableArrayList();
        try {
            connection = getConnection();
            pst = connection.prepareStatement("Select * from ViewsOfPayments");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new ViewsOfPayments( rs.getInt("id"), rs.getString("Name")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public ObservableList<ViewsOfTransits> getDataViewsOfTransits(){
        observableList = FXCollections.observableArrayList();
        try {
            connection = getConnection();
            pst = connection.prepareStatement("Select * from ViewsOfTransits");
            rs = pst.executeQuery();

            while(rs.next()){
                observableList.add(new ViewsOfTransits( rs.getInt("id"), rs.getString("Name")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public void signUpUser(String name, String Passport, Short age, String kindOfPayment ,
                           String password, String login, Label label){
        try{
            Short index = 1;
            Statement stmt = getConnection().createStatement();
            ResultSet executeQuery = stmt.executeQuery("SELECT * FROM ViewsOfPayments");
            // Обход результатов выборки
            while (executeQuery.next()) {
                if(kindOfPayment.equals(executeQuery.getString("Name"))){
                    index = executeQuery.getShort("id");
                }
            }
            executeQuery.close();
            stmt.close();
            Pattern pattern = Pattern.compile("^(?=.*[A-zА-я])(?=.*\\d)[A-zА-я\\d]{8,}$");
            Matcher matcher = pattern.matcher(password);
            while (!matcher.find()) {
                throw new Exception("Пароль должен быть не менее 8 символов, а также содеражать как минимум 1 букву и цифру");
            }
            if(age < 18){
                throw new Exception("Пользователь должен быть старше 18 лет!");

            }
            if(age > 130){
                throw new Exception("Пользователь не может быть старше 130 лет!");
            }
            String insert = "INSERT INTO Clients(Name, Passport, Age, IdPayment, Password, Login) " +
                    "Values(?,?,?,?,?,?);";
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setString(1,name );
            prSt.setString(2,Passport );
            prSt.setShort(3,age );
            prSt.setShort(4,index );
            prSt.setString(5,password );
            prSt.setString(6,login );


            prSt.executeUpdate();

            label.setTextFill(Color.rgb(33,190,2));
            label.setText("Profile successfully created!");

        }
        catch (SQLException ex) {

            Pattern pattern = Pattern.compile("Повторяющееся значение ключа.+");
            Matcher matcher = pattern.matcher(ex.getMessage());
            while (matcher.find()) {
                label.setText(ex.getMessage().substring(matcher.start(), matcher.end()).replace("ключа","поля").
                        replace("Повторяющееся значение","Недопустимое значение(ужк занято)"));

                label.setTextFill(Color.rgb(245,58,58));
            }

            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());;
        }
        catch (NumberFormatException e){
            label.setText(e.getMessage());
            label.setTextFill(Color.rgb(245,58,58));

        }
        catch(Exception ex){
            label.setText(ex.getMessage());
            label.setTextFill(Color.rgb(245,58,58));
        }

    }
    public void showHelp(){
        try {
            java.awt.Desktop.getDesktop().browse(new File(String.format("%s\\src\\sample\\help\\%s",
                    System.getProperty("user.dir"), "index.htm")).toURI());
        }
       catch (Exception ex){
            System.out.println(ex.getMessage());
       }
    }

}

package sample.AdditionalMenu.Account;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.App.appController;
import sample.DatabaseHadler;
import sample.animations.Attenuation;
import sample.animations.Shake;
import net.sf.jasperreports.engine.*;

import net.sf.jasperreports.view.*;

public class AccountController {
    private Parent parent;
    private Scene scene;
    private Stage stage;
    private DatabaseHadler dbHadler;
    private PreparedStatement pst;
    private ResultSet rs;
    private appController appcontroller;
    private int currentUserId ;
    private int currentIdPayment;
    @FXML
    private Button goToTursButton;

    @FXML
    private ImageView goBackButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passportField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessage;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<String> kinOfPaymentComboBox;

    @FXML
    private Button editButton;

    @FXML
    private Label idLabel;
    @FXML
    private Button supportButton;

    @FXML
    void initialize() {

        supportButton.setOnAction(event->{
            dbHadler = new DatabaseHadler();
            dbHadler.showHelp();
        });

    }
    public void onEditButtonClick(){

        try {
            pst = dbHadler.getConnection().prepareStatement("SELECT * From ViewsOfPayments");
            rs = pst.executeQuery();
            while (rs.next()) {
                if(rs.getString("Name").equals(kinOfPaymentComboBox.getValue()))
                    currentIdPayment = rs.getInt("id");
            }
            Pattern pattern = Pattern.compile("(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?");
            Matcher matcher = pattern.matcher(phoneField.getText());
            while (!matcher.find()) {
                throw new Exception("Пароль должен быть не менее 8 символов, а также содеражать как минимум 1 букву и цифру");
            }
            if(Integer.parseInt(ageField.getText()) < 18){
                throw new Exception("Пользователь должен быть старше 18 лет!");
            }
            if(Integer.parseInt(ageField.getText()) > 130){
                throw new Exception("Пользователь не может быть старше 130 лет!");
            }
            dbHadler = new DatabaseHadler();
            phoneField.setText(phoneField.getText() == null || phoneField.getText().equals("") ? "" :phoneField.getText());
            String sql = "update Clients set Login = '"+loginField.getText()+"',Name = '"+nameField.getText()+"', Passport ='"+passportField.getText()+"',Password = '"+passwordField.getText()+"', Age = "+ageField.getText()+", idPayment= "+currentIdPayment+", Phone = '"+phoneField.getText()+"' where id = "+currentUserId+"";
            pst = dbHadler.getConnection().prepareStatement(sql);
            pst.execute();

            errorMessage.setTextFill(Color.rgb(33,190,2));
            errorMessage.setText("Profile successfully edited!");
            Attenuation at = new Attenuation(errorMessage);
            at.playAnim();
        } catch (SQLException throwables) {
            Pattern pattern = Pattern.compile("Повторяющееся значение ключа.+");
            Matcher matcher = pattern.matcher(throwables.getMessage());
            while (matcher.find()) {
                errorMessage.setText(throwables.getMessage().substring(matcher.start(), matcher.end()).replace("ключа","поля").
                        replace("Повторяющееся значение","Недопустимое значение(уже занято)"));

                errorMessage.setTextFill(Color.rgb(245,58,58));
            }
            Attenuation at = new Attenuation(errorMessage);
            at.playAnim();
        } catch (ClassNotFoundException e) {

            errorMessage.setText("Error");
            errorMessage.setTextFill(Color.rgb(245,58,58));
            Attenuation at = new Attenuation(errorMessage);
            at.playAnim();
        } catch (Exception e) {
            errorMessage.setText("Некорректные данные");
            errorMessage.setTextFill(Color.rgb(245,58,58));
            Attenuation at = new Attenuation(errorMessage);
            at.playAnim();
        }

    }
    public void onGoToTursButtonClick(){
        appcontroller = new appController();
        appcontroller.launchAppController(stage,currentUserId);
    }
    private void fillAllTheFields() {
        try {
            dbHadler = new DatabaseHadler();
            pst = dbHadler.getConnection().prepareStatement("SELECT * From Clients where id = " + currentUserId + "");
            rs = pst.executeQuery();
                while (rs.next()) {
                    idLabel.setText(String.valueOf(rs.getInt("id")));
                    nameField.setText(rs.getString("Name"));
                    loginField.setText(rs.getString("Login"));
                    passportField.setText(rs.getString("Passport"));
                    passwordField.setText(rs.getString("Password"));
                    confirmPasswordField.setText(rs.getString("Password"));
                    ageField.setText(rs.getString("Age"));
                    phoneField.setText(rs.getString("Phone") == null ? "" : rs.getString("Phone"));
                    currentIdPayment = rs.getInt("IdPayment");
                }
            fillTheKindsOfPayment();
            pst = dbHadler.getConnection().prepareStatement("SELECT * From ViewsOfPayments");
            rs = pst.executeQuery();
            int trueIndex = 0;
            while (rs.next()) {
               if(rs.getInt("id") == currentIdPayment)
                   kinOfPaymentComboBox.getSelectionModel().select(trueIndex);
               trueIndex++;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public AccountController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/AdditionalMenu/Account/account.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 400, 440);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchAccountController(Stage stage, int id) {
        this.stage = stage;
        stage.setTitle("Account");
        stage.setScene(scene);
        stage.setResizable(true);
        this.currentUserId = id;
        stage.hide();
        stage.show();
        fillAllTheFields();
    }
    private void fillTheKindsOfPayment() {
            List<String> names = new ArrayList<String>();
            try {
                dbHadler = new DatabaseHadler();
                String sql = "SELECT Name From ViewsOfPayments";
                pst = dbHadler.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    names.add(rs.getString("Name"));
                }
                ObservableList<String> viewsOfPayment = FXCollections.observableArrayList(names);
                kinOfPaymentComboBox.setItems(viewsOfPayment);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }
        }
}

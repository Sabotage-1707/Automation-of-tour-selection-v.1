package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Admin.AdminMenuController;
import sample.App.appController;
import sample.Registration.Registration;
import sample.animations.Attenuation;
import sample.animations.Shake;
import javafx.scene.image.Image;

public class Controller {
    private Parent parent;
    private Scene scene;
    private Stage stage;
    private DatabaseHadler dbHadler;
    private Statement st;
    private ResultSet rs;
    private Registration registration;
    private appController appcontroller;
    private AdminMenuController adminMenuController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button supportButton;
    @FXML
    private TextField loginField;

    @FXML
    private Button signInButton;

    @FXML
    private Label messageErrorText;

    @FXML
    private TextField passwordFieldOpened;

    @FXML
    private PasswordField passwordFieldHidden;

    @FXML
    private CheckBox showPassword;

    @FXML
    private Button signUpButton;


    @FXML
    void initialize() {
        supportButton.setOnAction(event->{
            dbHadler = new DatabaseHadler();
            dbHadler.showHelp();
        });

    }
    private String passwordValue() {
        return showPassword.isSelected()?
                passwordFieldOpened.getText().trim(): passwordFieldHidden.getText().trim();
    }

    @FXML
    public void toggleVisiblePassword() {
        if (showPassword.isSelected()) {
            passwordFieldOpened.setText(passwordFieldHidden.getText());
            passwordFieldOpened.setVisible(true);
            passwordFieldHidden.setVisible(false);
            return;
        }
        passwordFieldHidden.setText(passwordFieldOpened.getText());
        passwordFieldHidden.setVisible(true);
        passwordFieldOpened.setVisible(false);
    }
    @FXML
    private void loginUser(String login, String password) {
        try{
            dbHadler = new DatabaseHadler();
            st  = dbHadler.getConnection().createStatement();
            rs = st.executeQuery("SELECT id,Login,Password From Clients");
            // Обход результатов выборки
            boolean success = false;
            if(login.equals("N") && password.equals("N")){
            //if(login.equals("TheBestChoiceForTraveler") && password.equals("myCourseProject4")){
                adminMenuController = new AdminMenuController();
                adminMenuController.launchAdminMenuController(stage);
            }
            else{
                while (rs.next()) {
                    if(login.equals(rs.getString("Login")) && password.equals(rs.getString("Password"))) {
                        success = true;
                        appcontroller = new appController();
                        appcontroller.launchAppController(stage,rs.getInt("id"));
                    }

                }
            }

            if(!success){
                Shake userLoginAnim = new Shake(loginField);
                Shake userPass = new Shake(showPassword.isSelected()?passwordFieldOpened:passwordFieldHidden);
                userLoginAnim.playAnim();
                userPass.playAnim();
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 700, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void handleSignInButtonAction(ActionEvent event) {
        String login = loginField.getText().trim();
        //String password = showPassword.isSelected()?passwordFieldOpened.getText().trim():passwordFieldHidden.getText().trim();
        if(!login.equals("") && !passwordValue().equals(""))
            loginUser(login, passwordValue());
        else{
            messageErrorText.setText("Login or password is empty");
            Attenuation at = new Attenuation(messageErrorText);
            at.playAnim();

        }

    }
    @FXML
    protected void handleSignUpButtonAction(ActionEvent event) {
        signUpButton.getScene().getWindow().hide();
        registration = new Registration();
        registration.launchRegisterController(stage);
    }

    public void launchLogingController(Stage stage) {
        this.stage = stage;
        stage.setTitle("User Login");
        stage.setScene(scene);
        Image image = new Image("/sample/assets/tourist.png");
        stage.getIcons().add(image);
        stage.setResizable(true);
        stage.hide();
        stage.show();
    }
}


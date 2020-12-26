package sample.Registration;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Controller;
import sample.DatabaseHadler;
import sample.animations.Attenuation;

public class Registration {

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private DatabaseHadler dbHadler;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private Button supportButton;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Font x1;

    @FXML
    private Hyperlink exit;

    @FXML
    private Insets x3;

    @FXML
    private TextField userName;

    @FXML
    private TextField userLogin;

    @FXML
    private TextField userPasport;

    @FXML
    private TextField Password;

    @FXML
    private Font x11;

    @FXML
    private TextField userAge;

    @FXML
    private Font x111;

    @FXML
    private ComboBox<String> kindOfPayment;

    @FXML
    private Label success;

    @FXML
    private Font x2;

    @FXML
    private Button reset;

    @FXML
    private Button register;

    @FXML
    void initialize() {
            fillTheKindsOfPayment();
        supportButton.setOnAction(event->{
            dbHadler.showHelp();
        });
    }
    public Registration(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Registration/registration.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 500, 440);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void resetProfile(ActionEvent event){
        userName.setText("");
        userLogin.setText("");
        userPasport.setText("");
        userAge.setText("");
        Password.setText("");
        kindOfPayment.setValue("");
        success.setOpacity(0.0);

    }
    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) {
        try {
            if(userName.getText().equals("")||userPasport.getText().equals("")||userAge.getText().equals("")||
                    kindOfPayment.getValue().equals("")||Password.getText().equals("")||userLogin.getText().equals("")){
                success.setText("All the fields must be filled!");
                success.setTextFill(Color.rgb(245,58,58));
            }else{
                dbHadler = new DatabaseHadler();
                dbHadler.signUpUser(userName.getText(), userPasport.getText(), Short.parseShort(userAge.getText()),
                        kindOfPayment.getValue(), Password.getText(), userLogin.getText(), success);
            }

        }
        catch(RuntimeException exception){
            success.setText("Введите возраст цифрами!!!");
            success.setTextFill(Color.rgb(245,58,58));

        }
        Attenuation at = new Attenuation(success);
        at.playAnim();
    }
    public void backToLoginWindow(ActionEvent event){
        exit.getScene().getWindow().hide();
        Controller login = new Controller();
        login.launchLogingController(stage);


    }
    public void launchRegisterController(Stage stage) {
        this.stage = stage;
        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.hide();
        stage.show();
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
            kindOfPayment.setItems(viewsOfPayment);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}

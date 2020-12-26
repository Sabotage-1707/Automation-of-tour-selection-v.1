package sample.ItemTour;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemTourController implements Initializable{
    @FXML
    private ImageView itemImageHotel;

    @FXML
    private Label itemtourNumber;

    @FXML
    private Label itemHotelName;

    @FXML
    private Label itemTourPrice;

    public void setData(ItemTour item){
        try {
            Image img = new Image(item.getImageHotel());
            itemImageHotel.setImage(img);
        }
        catch(Exception ex){
            itemImageHotel.setImage(new Image("/sample/assets/cantLoadPhoto.jpg"));
        }
        itemtourNumber.setText("Tour Number: " + item.getNumberTour());
        itemHotelName.setText("Hotel: " + item.getHotelName());
        itemTourPrice.setText("Price: " + item.getTourPrice());
    }
    public String getCurrentNumber(){
       return itemtourNumber.getText().split(":")[1];
    }
    @Override
    public void initialize(URL location, ResourceBundle resource) {


    }

}

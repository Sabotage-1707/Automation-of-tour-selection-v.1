package sample.App;


import com.sun.jmx.remote.util.OrderClassLoaders;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import net.sf.jasperreports.engine.*;
import org.apache.commons.logging.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.w3c.dom.css.Counter;

import sample.AdditionalMenu.Account.AccountController;
import sample.Admin.AdminMenuController;
import sample.DataBaseTablesClasses.Turs;
import sample.DatabaseHadler;
import sample.ItemTour.ItemTour;
import sample.ItemTour.ItemTourController;
import sample.animations.Attenuation;
import sample.animations.Shift;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class appController {

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private DatabaseHadler dbHadler;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;
    private ObservableList<Turs> listTurs;
    private CallableStatement callableStatement;
    private List<ItemTour> listItemsTour;
    private int indexItem;
    private String CurrentSort;
    private String sqlForSelect;
    private boolean priceSliderIsChanged = false;
    private boolean quantutySpinerIsChanged = false;
    private int currentUserId;
    private AccountController accountController;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button supportButton;

    @FXML
    private ImageView logo;

    @FXML
    private TextField searchBox;

    @FXML
    private Button menu;

    @FXML
    private ComboBox<String> sortBox;

    @FXML
    private Label itemsNotFoundError;

    @FXML
    private VBox workingArea;

    @FXML
    private  MenuButton geograficalFeaturesBox;

    @FXML
    private HBox selectedItemArea;
    @FXML
    private Slider priceSlider;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private MenuButton foodBox;

    @FXML
    private MenuButton transitsBox;

    @FXML
    private MenuButton companyBox;

    @FXML
    private HBox criteriaArea;

    @FXML
    private CheckBox excursionsCheck;

    @FXML
    private Label reportButton1;
    @FXML
    private MenuButton hotelClassBox;

    @FXML
    private MenuButton countryBox;

    @FXML
    private CheckBox discountsCheck;

    @FXML
    private Label departureDateChoice;

    @FXML
    private DatePicker departureDateCriteria;

    @FXML
    private HBox arrivalDateChoice;

    @FXML
    private DatePicker arrivalDateCriteria;

    @FXML
    private Button selectButton;

    @FXML
    private Label selectedTourName;

    @FXML
    private ImageView imageOfSelectedItem;

    @FXML
    private Label hotelNameOfSelectedItem;

    @FXML
    private Label hotelClassOfSelectedItem;

    @FXML
    private Label foodOfSelectedItem;

    @FXML
    private Label transitsOfSelectedItem;

    @FXML
    private Label priceFor1OfSelectedItem;

    @FXML
    private Label companyOfSelectedItem;

    @FXML
    private Label countryOfSelectedItem;

    @FXML
    private Label departureDateOfSelectedItem;

    @FXML
    private Label arrivalDateOfSelectedItem;

    @FXML
    private Label discountsOfSelectedItem;

    @FXML
    private Label excursionsOfSelectedItem;

    @FXML
    private Label finalPrice;

    @FXML
    private ImageView counterMinusButton;

    @FXML
    private TextField counterField;

    @FXML
    private Button minusPeopleCounter;

    @FXML
    private Button plusPeopleCounter;

    @FXML
    private Button minusChildrenCounter;

    @FXML
    private Button plusChildrenCounter;
    @FXML
    private ImageView counterPlusButton;

    @FXML
    private ImageView childrenCounterMinusButton;

    @FXML
    private TextField counterChildrenField;

    @FXML
    private ImageView childrenCounterPlusButton;

    @FXML
    private Button buyTourButton;

    @FXML
    private VBox additionalMenu;
    @FXML
    private Label accountButton;

    @FXML
    private Label reportButton;

    @FXML
    private Label reportCompanies;

    @FXML
    private Label exitButton;

    @FXML
    void initialize() {
        List<String> geograficalFeatures = Arrays.asList( "Горы", "Море", "Пляжи", "Каньоны", "Оазисы", "Сады", "Реки", "Пустыни","Леса","Острова" );
        List<String> sorts = Arrays.asList("Номер тура по возростанию","По убыванию номера тура", "Цена по возростанию","Цена по убыванию","Название компаний","Класс отеля");
        sortBox.getItems().addAll(sorts);
        sortBox.getSelectionModel().selectFirst();
        fillChoiceMenuButton(foodBox,createListItemFromDB("Select * from ViewsOfFood","Name"));
        fillChoiceMenuButton(geograficalFeaturesBox,geograficalFeatures);
        fillChoiceMenuButton(transitsBox, createListItemFromDB("Select * from ViewsOfTransits","Name"));
        fillChoiceMenuButton(companyBox, createListItemFromDB("Select * from Companies","CompanyName"));
        fillChoiceMenuButton(hotelClassBox, createListItemFromDB("Select * from ViewsOfHotels","HotelClass"));
        fillChoiceMenuButton(countryBox,createListItemFromDB("Select * from Countries","CountryName"));
        additionalMenu.setTranslateX(340f);
        Shift shift = new Shift(additionalMenu, 600);
        menu.setOnAction(event -> {
            additionalMenu.setVisible(true);
                shift.leftShift(340.0);
                shift.playAnim();
        });
        additionalMenu.setOnMouseExited(event -> {
            shift.rightShift(343.0);
            shift.playAnim2();
        });
        customizationSlider(priceSlider);
        priceSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            priceSliderIsChanged=true;
        });
        maxCountSpinner();
        quantitySpinner.valueProperty().addListener((obs, oldValue, newValue)->{
            quantutySpinerIsChanged=true;
        });
        priceSlider.valueProperty().addListener((obsVal, oldVal,  newVal) ->{

                System.out.println(newVal.doubleValue());
        });
        sortBox.addEventHandler(ActionEvent.ANY, this::comboAction);
        listItemsTour = getTurs2("SELECT * From getFullTour() "+setCurrentSort()+"");


        fillWorkingArea();
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty()||newValue.equals("")||newValue == ""){
                listItemsTour = getTurs2("SELECT * From getFullTour() "+setCurrentSort()+"");
            }
            else{

                listItemsTour = getTurs2("SELECT * from getFullTour() where  HotelName like '%"+newValue+"%' OR Ecscursions like '%"+newValue+"%' OR NameOFDiscount like '%"+newValue+"%' OR CountryName like '%"+newValue+"%' OR CompanyName like '%"+newValue+"%' "+setCurrentSort()+"");
                if(listItemsTour.size() <= 0){
                    itemsNotFoundError.setText("Nothing was found for your search.");
                    itemsNotFoundError.setTextFill(Color.rgb(245,58,58));
                    Attenuation at = new Attenuation(itemsNotFoundError);
                    at.playAnim();
                }
                else{
                    fillWorkingArea();
                }

            }
            /*fillWorkingArea(");*/
        });
        accountButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::onAccountClick );
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::onExitButtonClick);
        reportButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::onReport1ButtonClick);
        reportButton1.addEventHandler(MouseEvent.MOUSE_CLICKED,this::onReport2ButtonClick);
        reportCompanies.addEventHandler(MouseEvent.MOUSE_CLICKED,this::onReport3ButtonClick);
        supportButton.setOnAction(event->{
            dbHadler.showHelp();
        });

    }
    private void onAccountClick(MouseEvent event) {
            accountController = new AccountController();
            accountController.launchAccountController(stage, this.currentUserId);

    }
    private void onExitButtonClick(MouseEvent event){ System.exit(0);}
    private void onReport1ButtonClick(MouseEvent event){
        dbHadler.showReport("Tourism.jrxml");
    }
    private void onReport2ButtonClick(MouseEvent event){
        dbHadler.showReport("Tourism2.jrxml");
    }
    private void onReport3ButtonClick(MouseEvent event){
        dbHadler.showReport("Agenstva.jrxml");
    }
    public void onBuyButtonClick(){
        try {
            dbHadler = new DatabaseHadler();
            String sql = "SELECT Name, Phone From Clients where id ="+this.currentUserId+"";
            pst = dbHadler.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            TextInputDialog dialog = new TextInputDialog("+375xxxxxxxxx");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Alert alert2= new Alert(Alert.AlertType.INFORMATION);


            while(rs.next()){
                System.out.println(rs.getString("Phone"));
                if(rs.getString("Phone") == null || rs.getString("Phone").equals("")) {
                    dialog.setTitle("Покупка тура");
                    dialog.setHeaderText("В вашем аккаунте еще не зарегистрирован телефон чтобы связаться с вами");
                    dialog.setContentText("Пожалуйста введите ваш номер телефона:");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                        sql = "update Clients set Phone = '"+result.get()+"' where id = "+this.currentUserId+"";
                        pst = dbHadler.getConnection().prepareStatement(sql);
                        pst.execute();

                    }
                }
                else{

                    alert.setTitle("Информационное окно");
                    alert.setHeaderText(null);
                    alert.setContentText("Вы уверены что хотите заказать тур? Если это так наш сотрудник скоро свяжется с вами!");
                    Optional<ButtonType> res = alert.showAndWait();
                    if (res.get() == ButtonType.OK){
                        sql = "INSERT INTO TursOfClients(IdClient,IdTure, CounterPeople, CounterLess14yo, BukingDate, FinalPrice) Values (?,?,?,?,?,?)";
                        pst = dbHadler.getConnection().prepareStatement(sql);
                        Date date = new Date();
                        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
                        pst.setInt(1, this.currentUserId);
                        pst.setInt(2, Integer.parseInt(selectedTourName.getText()));
                        pst.setInt(3, Integer.parseInt(counterField.getText()));
                        pst.setInt(4, Integer.parseInt(counterChildrenField.getText()));
                        pst.setDate(5, java.sql.Date.valueOf(formatForDateNow.format(date)));
                        pst.setDouble(6, Double.parseDouble(finalPrice.getText()));
                        pst.execute();

                        sql = "SELECT Counter, IdCompany From TursOfCompanies where IdTure ="+selectedTourName.getText()+"";
                        pst = dbHadler.getConnection().prepareStatement(sql);
                        rs = pst.executeQuery();
                        int oldCounter = 0;
                        int idCompany = 0;
                        while(rs.next()){
                            oldCounter = rs.getInt("Counter");
                            idCompany = rs.getInt("IdCompany");
                        }
                        int newCounter =  oldCounter - (Integer.parseInt(counterField.getText())+Integer.parseInt(counterChildrenField.getText()));
                        sql = "update TursOfCompanies set Counter = "+newCounter+" where IdTure = "+selectedTourName.getText()+"";
                        pst = dbHadler.getConnection().prepareStatement(sql);
                        pst.execute();

                        sql = "SELECT Profit From Companies where id ="+idCompany+"";
                        pst = dbHadler.getConnection().prepareStatement(sql);
                        rs = pst.executeQuery();
                        double oldProfit = 0;
                        double newProfit = 0;
                        while(rs.next()){
                            oldProfit = rs.getDouble("Profit");
                        }
                        newProfit = Double.parseDouble(finalPrice.getText())+oldProfit;
                        sql = "update Companies set Profit = "+newProfit+" where id = "+idCompany+"";
                        pst = dbHadler.getConnection().prepareStatement(sql);
                        pst.execute();

                        alert2.setTitle("Информационное окно");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Ожидайте звонка. Чек находится в папке где распологается программа.");
                        alert2.showAndWait();

                        sql = "SELECT Top(1) TursOfClients.id as id,Clients.Name as Name," +
                                "TursOfClients.IdTure as IdTure,HotelName,DateStart, DateFinish, " +
                                "TursOfClients.CounterPeople as CounterPeople,TursOfClients.CounterLess14yo as CounterLess14yo, FinalPrice,ViewsOfPayments.Name as PaymentName     from TursOfClients Join Turs On(TursOfClients.IdTure=Turs.id) " +
                                " Join Clients On(TursOfClients.IdClient = Clients.id) " +
                                " Join ViewsOfPayments On(Clients.idPayment = ViewsOfPayments.id)" +
                                " ORDER BY TursOfClients.id Desc";
                        pst = dbHadler.getConnection().prepareStatement(sql);
                        rs = pst.executeQuery();
                        while(rs.next()) {

                            XWPFDocument document = new XWPFDocument();

                            //Write the Document in file system
                            FileOutputStream out = new FileOutputStream(new File("Order.docx"));
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            XWPFParagraph paragraph = document.createParagraph();
                            paragraph.setAlignment(ParagraphAlignment.CENTER);
                            XWPFRun run = paragraph.createRun();
                            run.setText("Удтверждено приказом Министерства финансов РБ\n" +
                                    "ООО\"The Best Choice For Traveler\"");
                            run.setTextPosition(80);


                            XWPFParagraph paragraph2 = document.createParagraph();
                            paragraph2.setAlignment(ParagraphAlignment.CENTER);
                            XWPFRun run2 = paragraph2.createRun();
                            run2.setText("ТУРИСТИЧЕСКАЯ ПУТЕВКА №" + rs.getInt("id"));
                            run2.setTextPosition(50);

                            XWPFParagraph paragraph3 = document.createParagraph();
                            paragraph3.setAlignment(ParagraphAlignment.LEFT);
                            XWPFRun run3 = paragraph3.createRun();
                            run3.setText("Заказчик туристического продукта: " + rs.getString("Name"));
                            run3.addBreak();
                            run3.setText("Номер тура: " + rs.getInt("IdTure"));
                            run3.addBreak();
                            run3.setText("Название отеля: " + rs.getString("HotelName"));
                            run3.addBreak();
                            run3.setText("Дата начала тура: " + rs.getDate("DateStart"));
                            run3.addBreak();
                            run3.setText("Дата окончания тура: " + rs.getDate("DateFinish"));
                            run3.addBreak();
                            run3.setText("Количество забронированных путевок: " + (rs.getInt("CounterPeople")+rs.getInt("CounterLess14yo")));
                            run3.addBreak();
                            run3.setText("Окончательная цена с учетом всех скидок: " + rs.getDouble("FinalPrice"));
                            Date now = new Date();
                            XWPFParagraph paragraph4 = document.createParagraph();
                            paragraph4.setAlignment(ParagraphAlignment.LEFT);
                            XWPFRun run4 = paragraph4.createRun();
                            run4.setText("Способ оплаты выбранный клиентом: " + rs.getString("PaymentName"));
                            run4.addBreak();
                            run4.addBreak();

                            run4.setText("Дата оформления заказа: " + simpleDateFormat.format(now));
                            XWPFParagraph paragraph5 = document.createParagraph();
                            paragraph5.setAlignment(ParagraphAlignment.CENTER);
                            XWPFRun run5 = paragraph5.createRun();
                            run5.setText("Данный документ является неотъемлемой частью " +
                                    "договора о реализации туристического продукта от " + simpleDateFormat.format(now) + " №" + rs.getInt("id"));
                            document.write(out);
                            out.close();



                            System.out.println("createdocument.docx written successully");
                        }
                        selectedItemArea.setVisible(false);

                    } else {
                        return;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    public void OnMinusCounterPeopleClick(){
        dbHadler = new DatabaseHadler();
        try {
            callableStatement = dbHadler.getConnection().prepareCall("{? = call CounterCurrentTour(?)}");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, Integer.parseInt(selectedTourName.getText()));
            callableStatement.execute();

                if(callableStatement.getInt(1)<1||Integer.parseInt(counterField.getText())<=1)
                    return;
                else{
                    counterField.setText(String.valueOf(Integer.parseInt(counterField.getText())-1));
                    if(!discountsOfSelectedItem.getText().equals("") && !discountsOfSelectedItem.getText().contains("Для детей младше 14 лет")) {
                        finalPrice.setText(String.valueOf(round(Double.parseDouble(finalPrice.getText()) - (Double.parseDouble(priceFor1OfSelectedItem.getText())
                                * (100.0 -Double.parseDouble(discountsOfSelectedItem.getText().trim().split("-")[1].replace("%", ".0"))) / 100.0), 2)));
                    }else {
                        finalPrice.setText(String.valueOf(round(Double.parseDouble(finalPrice.getText())-Double.parseDouble(priceFor1OfSelectedItem.getText()),2)));
                    }

                }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    public void onSelectButtonClick(){
        List<String> geograficalFeaturesSelectedItems = getSelectedMenuCheckBoxesItems(geograficalFeaturesBox);
        double MaxPrice = priceSlider.getValue();
        int quantity = quantitySpinner.getValue();
        List<String>  foodSelectedItems = getSelectedMenuCheckBoxesItems(foodBox);
        List<String>  transitsSelectedItems = getSelectedMenuCheckBoxesItems(transitsBox);
        List<String>  companiesSelectedItems = getSelectedMenuCheckBoxesItems(companyBox);
        boolean ecscursionsIsSelected = excursionsCheck.isSelected();
        List<String>  hotelClassSelectedItems = getSelectedMenuCheckBoxesItems(hotelClassBox);
        List<String>  countrySelectedItems = getSelectedMenuCheckBoxesItems(countryBox);
        boolean DiscountsIsSelected = discountsCheck.isSelected();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date departureDate = new Date();
        Date arrvalDate = null;
        try {
             departureDate = departureDateCriteria.getValue() != null && !departureDateCriteria.getValue().toString().equals("") ?sdf.parse(departureDateCriteria.getValue().toString().trim()):null;
             arrvalDate = arrivalDateCriteria.getValue() != null && !arrivalDateCriteria.getValue().toString().equals("") ? sdf.parse(arrivalDateCriteria.getValue().toString().trim()):null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            arrvalDate =null;
            e.printStackTrace();
        }


        sqlForSelect ="where ";
        if(geograficalFeaturesSelectedItems.size() > 0){
            if(geograficalFeaturesSelectedItems.size()==1){
                geograficalFeaturesSelectedItems.forEach(item->{
                    sqlForSelect+=" SignsCountry like '%"+item+"%'";
                });
            }else{
                sqlForSelect+=" SignsCountry like ";
                geograficalFeaturesSelectedItems.forEach(item->{
                    sqlForSelect+="'%"+item+"%' OR SignsCountry like ";
                });
                sqlForSelect= sqlForSelect.substring(0,sqlForSelect.length()-20);
            }
        };
        if(priceSliderIsChanged){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            sqlForSelect+=" Price <= "+(priceSlider.getValue()+4)+"";
        };
        if(quantutySpinerIsChanged){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            sqlForSelect+=" Counter >= "+quantitySpinner.getValue()+"";
        };
        if(foodSelectedItems.size() > 0){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            if(foodSelectedItems.size() == 1){
                foodSelectedItems.forEach(item->{
                    sqlForSelect+=" FoodName like '%"+item+"%'";
                });
            }
            else{
                sqlForSelect+=" FoodName IN (";
                foodSelectedItems.forEach(item->{
                    sqlForSelect+="'"+item+"',";
                });
                sqlForSelect = sqlForSelect.substring(0,sqlForSelect.length()-1);
                sqlForSelect+=") ";
            }
        };
        if(transitsSelectedItems.size() > 0){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            if(transitsSelectedItems.size() == 1){
                transitsSelectedItems.forEach(item->{
                    sqlForSelect+=" TransitsName like '%"+item+"%'";
                });
            }
            else{
                sqlForSelect+=" TransitsName IN (";
                transitsSelectedItems.forEach(item->{
                    sqlForSelect+="'"+item+"',";
                });
                sqlForSelect = sqlForSelect.substring(0,sqlForSelect.length()-1);
                sqlForSelect+=") ";
            }

        };
        if(companiesSelectedItems.size() > 0){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            if(companiesSelectedItems.size() == 1){
                companiesSelectedItems.forEach(item->{
                    sqlForSelect+=" CompanyName like '%"+item+"%'";
                });
            }
            else {
                sqlForSelect+=" CompanyName IN (";
                companiesSelectedItems.forEach(item->{
                    sqlForSelect+="'"+item+"',";
                });
                sqlForSelect = sqlForSelect.substring(0,sqlForSelect.length()-1);
                sqlForSelect+=") ";
            }

        };
        if(ecscursionsIsSelected){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            sqlForSelect+=" Ecscursions <> '' AND Ecscursions IS NOT NULL ";
        };
        if(hotelClassSelectedItems.size() > 0){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            if(hotelClassSelectedItems.size() == 1){
                hotelClassSelectedItems.forEach(item->{
                    sqlForSelect+=" HotelClass like '%"+item+"%'";
                });
            }else{
                sqlForSelect+=" HotelClass IN (";
                hotelClassSelectedItems.forEach(item->{
                    sqlForSelect+="'"+item+"',";
                });
                sqlForSelect = sqlForSelect.substring(0,sqlForSelect.length()-1);
                sqlForSelect+=") ";
            }

        };
        if(countrySelectedItems.size() > 0){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            if(countrySelectedItems.size() == 1){
                countrySelectedItems.forEach(item->{
                    sqlForSelect+=" CountryName like '%"+item+"%'";
                });
            }
            else{
                sqlForSelect+=" CountryName IN (";
                countrySelectedItems.forEach(item->{
                    sqlForSelect+="'"+item+"',";
                });
                sqlForSelect = sqlForSelect.substring(0,sqlForSelect.length()-1);
                sqlForSelect+=") ";
            }

        };
        if(DiscountsIsSelected){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            sqlForSelect+=" NameOFDiscount <> '' AND NameOFDiscount IS NOT NULL ";
        };
        if(departureDate != null && !departureDate.equals("")){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            sqlForSelect+=" (Year(DateStart) = Year('"+sdf.format(departureDate)+"') AND MONTH(DateStart) = MONTH('"+sdf.format(departureDate)+"') AND DAY(DateStart) = DAY('"+sdf.format(departureDate)+"'))  ";
        }
        if(arrvalDate != null && !arrvalDate.equals("")){
            if(!sqlForSelect.endsWith("where "))
                sqlForSelect+=" And ";
            sqlForSelect+=" (Year(DateFinish) = Year('"+sdf.format( arrvalDate)+"') AND MONTH(DateFinish) = MONTH('"+sdf.format( arrvalDate)+"') AND DAY(DateFinish) = DAY('"+sdf.format( arrvalDate)+"')) ";
        }
        System.out.println(sqlForSelect);
        if(!sqlForSelect.endsWith("where ")){
            listItemsTour = getTurs2("Select * from getFullTour() "+sqlForSelect+"");
            if(listItemsTour.size() <= 0){
                itemsNotFoundError.setText("Nothing was found for your search.");
                itemsNotFoundError.setTextFill(Color.rgb(245,58,58));
                Attenuation at = new Attenuation(itemsNotFoundError);
                at.playAnim();

            }
            else{
                fillWorkingArea();
            }
        }
        else{
            listItemsTour = getTurs2("Select * from getFullTour()");
            fillWorkingArea();
        }
    }
    public List<String> getSelectedMenuCheckBoxesItems(MenuButton menu){
        List<String> list = new ArrayList<>();
        for (MenuItem item:
                menu.getItems()) {
            CheckMenuItem checkMenuItem = (CheckMenuItem) item;
            if(checkMenuItem.isSelected()) {
                list.add(checkMenuItem.getText());
            }
        }
        return list;
    }
    public void OnPlusCounterPeopleClick(){
        dbHadler = new DatabaseHadler();
        try {
            callableStatement = dbHadler.getConnection().prepareCall("{? = call CounterCurrentTour(?)}");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, Integer.parseInt(selectedTourName.getText()));
            callableStatement.execute();
            if(Integer.parseInt(counterField.getText()) + Integer.parseInt(counterChildrenField.getText()) < callableStatement.getInt(1)){
                counterField.setText(String.valueOf(Integer.parseInt(counterField.getText())+1));
                if(!discountsOfSelectedItem.getText().equals("") && !discountsOfSelectedItem.getText().contains("Для детей младше 14 лет")) {
                    finalPrice.setText(String.valueOf(round(Double.parseDouble(finalPrice.getText()) + (Double.parseDouble(priceFor1OfSelectedItem.getText())
                            * (100.0 -Double.parseDouble(discountsOfSelectedItem.getText().trim().split("-")[1].replace("%", ".0"))) / 100.0), 2)));
                }else {
                    finalPrice.setText(String.valueOf(round(Double.parseDouble(finalPrice.getText())+Double.parseDouble(priceFor1OfSelectedItem.getText()),2)));
                }
            }
            else
                return;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void OnMinusCounterChildrenClick(){
        dbHadler = new DatabaseHadler();
        try {
            callableStatement = dbHadler.getConnection().prepareCall("{? = call CounterCurrentTour(?)}");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, Integer.parseInt(selectedTourName.getText()));
            callableStatement.execute();
            if(Integer.parseInt(counterChildrenField.getText()) > 0){
                counterChildrenField.setText(String.valueOf(Integer.parseInt(counterChildrenField.getText())-1));
                if(!discountsOfSelectedItem.getText().equals("")) {
                    finalPrice.setText(String.valueOf(round(Double.parseDouble(finalPrice.getText()) - (Double.parseDouble(priceFor1OfSelectedItem.getText())
                            * (100.0 -Double.parseDouble(discountsOfSelectedItem.getText().trim().split("-")[1].replace("%", ".0"))) / 100.0), 2)));
                }else {
                    finalPrice.setText(String.valueOf(round(Double.parseDouble(finalPrice.getText())-Double.parseDouble(priceFor1OfSelectedItem.getText()),2)));
                }

            }
            else
                return;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void OnPlusCounterChildrenClick(){
        dbHadler = new DatabaseHadler();
        try {
            callableStatement = dbHadler.getConnection().prepareCall("{? = call CounterCurrentTour(?)}");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, Integer.parseInt(selectedTourName.getText()));
            callableStatement.execute();
            if(((Integer.parseInt(counterChildrenField.getText())+1) +
                    Integer.parseInt(counterField.getText())) <=
                    (callableStatement.getInt(1))){

                counterChildrenField.setText(String.valueOf(Integer.parseInt(counterChildrenField.getText())+1));
                if(!discountsOfSelectedItem.getText().equals("")) {
                    finalPrice.setText(String.valueOf(round(Double.parseDouble(finalPrice.getText()) + (Double.parseDouble(priceFor1OfSelectedItem.getText())
                            * (100.0 - Double.parseDouble(discountsOfSelectedItem.getText().trim().split("-")[1].replace("%", ".0"))) / 100.0), 2)));
                }else {
                    finalPrice.setText(String.valueOf(round(Double.parseDouble(finalPrice.getText())+Double.parseDouble(priceFor1OfSelectedItem.getText()),2)));
                }
            }

            else
                return;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void comboAction(ActionEvent event) {

        listItemsTour = getTurs2("SELECT * from getFullTour() "+setCurrentSort()+"");
        searchBox.setText("");
        fillWorkingArea();
    }
    public void fillWorkingArea(){
        workingArea.getChildren().clear();
        for(int i = 0; i!=listItemsTour.size();++i){
            FXMLLoader fxmlLoader = new FXMLLoader();
            //fxmlLoader.setController(new ItemTourController());
            fxmlLoader.setLocation(getClass().getResource("/sample/ItemTour/itemTour.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                hBox.getStyleClass().add("tour");
                ItemTourController itemTourController = fxmlLoader.getController();
                itemTourController.setData(listItemsTour.get(i));
                hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            selectedItemArea.setVisible(true);

                            dbHadler = new DatabaseHadler();
                            String curId = itemTourController.getCurrentNumber();
                            String sql = "Select * from getCurrentFullTour("+curId+")";
                            pst = dbHadler.getConnection().prepareStatement(sql);
                            rs = pst.executeQuery();
                            while (rs.next()) {
                                selectedTourName.setText(rs.getString("id"));
                                try{
                                    imageOfSelectedItem.setImage(new Image(rs.getString("Photo")));
                                }
                                catch(IllegalArgumentException ex){
                                    imageOfSelectedItem.setImage(new Image("/sample/assets/cantLoadPhoto.jpg"));
                                }
                                hotelNameOfSelectedItem.setText(rs.getString("HotelName"));
                                hotelClassOfSelectedItem.setText(rs.getString("HotelClass"));
                                foodOfSelectedItem.setText(rs.getString("FoodName"));
                                transitsOfSelectedItem.setText(rs.getString("TransitsName"));
                                priceFor1OfSelectedItem.setText(rs.getString("Price"));
                                companyOfSelectedItem.setText(rs.getString("CompanyName"));
                                countryOfSelectedItem.setText(rs.getString("CountryName"));
                                departureDateOfSelectedItem.setText(rs.getString("DateStart"));
                                arrivalDateOfSelectedItem.setText(rs.getString("DateFinish"));
                                if (rs.getString("NameOFDiscount") == null || rs.getString("NameOFDiscount").isEmpty()) {
                                    discountsOfSelectedItem.setText("");
                                    finalPrice.setText(rs.getString("Price"));
                                } else {
                                    discountsOfSelectedItem.setText(rs.getString("NameOFDiscount") + "-" + rs.getString("PercentsOfDiscount"));
                                    if(!discountsOfSelectedItem.getText().equals("") && !discountsOfSelectedItem.getText().contains("Для детей младше 14 лет")) {
                                        finalPrice.setText(String.valueOf(Double.parseDouble(rs.getString("Price")) * (100.0 - Double.parseDouble(rs.getString("PercentsOfDiscount").replace("%", ".0"))) / 100.0));
                                    }
                                    else{
                                        finalPrice.setText(String.valueOf(Double.parseDouble(rs.getString("Price"))));

                                    }
                                }
                                if (rs.getString("Ecscursions") == null || rs.getString("Ecscursions").isEmpty()) {
                                    excursionsOfSelectedItem.setText("");
                                } else {
                                    excursionsOfSelectedItem.setText(rs.getString("Ecscursions"));
                                }
                                /*if(discountsOfSelectedItem.getText().equals("")){
                                    finalPrice.setText(rs.getString("Price"));
                                }
                                else{
                                    finalPrice.setText(String.valueOf(Double.parseDouble(rs.getString("Price")) / 100.0 * 95.0));
                                }*/

                            }
                            dbHadler = new DatabaseHadler();

                            callableStatement = dbHadler.getConnection().prepareCall("{? = call CounterCurrentTour(?)}");
                            callableStatement.registerOutParameter(1, Types.INTEGER);
                            callableStatement.setInt(2, Integer.parseInt(selectedTourName.getText()));
                            callableStatement.execute();
                            if(callableStatement.getInt(1)<1){
                                counterField.setText("0");
                                counterChildrenField.setText("0");
                                minusPeopleCounter.setDisable(true);
                                plusPeopleCounter.setDisable(true);
                                minusChildrenCounter.setDisable(true);
                                plusChildrenCounter.setDisable(true);
                            }
                            else{
                                counterField.setText("1");
                                counterChildrenField.setText("0");
                                minusPeopleCounter.setDisable(false);
                                plusPeopleCounter.setDisable(false);
                                minusChildrenCounter.setDisable(false);
                                plusChildrenCounter.setDisable(false);
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                workingArea.getChildren().add(hBox);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public List<ItemTour> getTurs2(String sql){
        List<ItemTour> ls = new ArrayList<>();
        try {
            dbHadler = new DatabaseHadler();
            pst = dbHadler.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ItemTour item = new ItemTour();
                item.setImageHotel(rs.getString("Photo"));
                item.setNumberTour(rs.getString("id"));
                item.setHotelName(rs.getString("HotelName"));
                item.setTourPrice(rs.getString("Price"));
                ls.add(item);
            }
            return ls;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return ls;
    }
    public appController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/App/app.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();

            scene = new Scene(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchAppController(Stage stage, int id) {
        this.stage = stage;
        stage.setTitle("Main");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setResizable(true);
        this.currentUserId = id;
        stage.hide();
        stage.show();
    }
    public void fillChoiceMenuButton(MenuButton menu, List<String> list){
        menu.getItems().clear();
        for(String str: list){
            menu.getItems().add(new CheckMenuItem(str));
            //menu.getItems().add(new CustomMenuItem(new CheckBox(str)));
        }

    }
    public void maxCountSpinner(){
        dbHadler = new DatabaseHadler();
        try {
            callableStatement = dbHadler.getConnection().prepareCall ("{? = call MaxCountOfTours()}");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.execute();
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,callableStatement.getInt(1),1);
            quantitySpinner.setValueFactory(valueFactory);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void customizationSlider(Slider sl){
        sl.setMin(findPrice("{? = call GetMinPrice()}"));
        sl.setMax(findPrice("{? = call GetMaxPrice()}"));
        sl.setValue(findPrice("{? = call GetMinPrice()}"));
        sl.setShowTickLabels(true);
        sl.setBlockIncrement(100.0);
        sl.setMajorTickUnit(30);
        sl.setMinorTickCount(1);
        sl.setSnapToTicks(true);
    }
    public List<String> createListItemFromDB(String sql, String element) {
        List<String> names = new ArrayList<String>();
        try {
            dbHadler = new DatabaseHadler();
            pst = dbHadler.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                names.add(rs.getString(element));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return names;
    }
    public String setCurrentSort(){
        switch(sortBox.getSelectionModel().getSelectedItem()){
            case "Номер тура по возростанию":
                return "order by id";
            case "По убыванию номера тура":
                return "order by id desc";

            case "Цена по возростанию":
                return "order by Price";

            case "Цена по убыванию":
                return "order by Price desc";
            case "Название компаний":
                return "order by CompanyName";

            case "Класс отеля":
                return "order by HotelClass";
            default:
                return "order by id";
        }
    }
    public float findPrice(String sql){
        dbHadler = new DatabaseHadler();
        try {
            callableStatement = dbHadler.getConnection().prepareCall (sql);
            callableStatement.registerOutParameter(1, Types.FLOAT);
            callableStatement.execute();
            return  callableStatement.getFloat(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

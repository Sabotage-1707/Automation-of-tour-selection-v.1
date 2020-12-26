package sample.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.DataBaseTablesClasses.*;
import sample.DatabaseHadler;

public class AdminMenuController {

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private DatabaseHadler dbHadler;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;
    private ObservableList<Clients> listClients;
    private ObservableList<Companies> listCompanies;
    private ObservableList<Countries> listCountries;
    private ObservableList<Turs> listTurs;
    private ObservableList<TursOfClients> listTursOfClients;
    private ObservableList<TursOfCompanies> listTursOfCompanies;
    private ObservableList<ViewsOfDiscounts> listViewsOfDiscounts;
    private ObservableList<ViewsOfFood> listViewsOfFood;
    private ObservableList<ViewsOfHotels> listViewsOfHotels;
    private ObservableList<ViewsOfPayments> listViewsOfPayments;
    private ObservableList<ViewsOfTransits> listViewsOfTransits;
    private ObservableList<javafx.scene.layout.VBox> allVBox;
    private boolean isBurning = false;
    @FXML
    private Button deleteEntry;

    @FXML
    private Button updateEntry;

    @FXML
    private Button addNewEntry;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox clientsFields;

    @FXML
    private TextField txt_Login;

    @FXML
    private TextField txt_ClientName;

    @FXML
    private TextField txt_Passport;

    @FXML
    private TextField txt_Password;

    @FXML
    private TableColumn<TursOfClients, Double> TursOfClientsFinalPriceColumn;
    @FXML
    private TextField txt_Age;

    @FXML
    private TextField txt_ClientIdPayment;

    @FXML
    private VBox companiesFields;

    @FXML
    private TextField txt_CompanyName;

    @FXML
    private TextField txt_CompanyProfit;

    @FXML
    private TextField txt_CompanyAdres;

    @FXML
    private VBox countriesFields;

    @FXML
    private TextField txt_CountryName;

    @FXML
    private TextArea txt_CountrySigns;

    @FXML
    private VBox tourFields;

    @FXML
    private TextField txt_HotelName;

    @FXML
    private TextField txt_Ecscursions;

    @FXML
    private TextField txt_TourDateStart;

    @FXML
    private TextField txt_TourDateFinish;

    @FXML
    private TextField txt_TourPrice;

    @FXML
    private TextField txt_TourPhoto;


    @FXML
    private TextField txt_TourIdHotel;

    @FXML
    private TextField txt_TourIdCountry;

    @FXML
    private TextField txt_TourIdDiscount;

    @FXML
    private TextField txt_TourIdFood;

    @FXML
    private TextField txt_TourIdTransit;

    @FXML
    private VBox tursOfClientsFields;

    @FXML
    private TextField txt_ToursOfClientsIdClient;

    @FXML
    private TextField txt_ToursOfClientsIdTure;

    @FXML
    private TextField txt_ToursOfClientsCounterPeople;

    @FXML
    private TextField txt_ToursOfClientsCounterLess14yo;

    @FXML
    private TextField txt_ToursOfClientsBukingDate;

    @FXML
    private TextField txt_ToursOfClientsFinalPrice;


    @FXML
    private VBox tursOfCompaniesFields;

    @FXML
    private TextField txt_ToursOfCompaniesIdCompany;

    @FXML
    private TextField txt_ToursOfCompaniesIdTure;

    @FXML
    private TextField txt_ToursOfCompaniesCounter;

    @FXML
    private VBox viewsOfDiscountsFields;

    @FXML
    private TextField txt_viewsOfDiscountsNameOfDiscount;

    @FXML
    private TextField txt_viewsOfDiscountsPercentsOfDiscount;

    @FXML
    private VBox viewsOfFoodFields;

    @FXML
    private TextField txt_viewsOfFoodName;

    @FXML
    private VBox viewsOfHotelsFields;

    @FXML
    private TextField txt_viewsOfHotelsClass;

    @FXML
    private VBox viewsOfPaymentsFields;

    @FXML
    private TextField txt_viewsOfPaymentName;

    @FXML
    private VBox viewsOfTransitsFields;

    @FXML
    private TextField txt_viewsOfTransitName;

    @FXML
    private TabPane tabPane1;

    @FXML
    private Tab clientsTab;

    @FXML
    private TableView<Clients> clientsTable;

    @FXML
    private Tab companiesTab;
    @FXML
    private TableColumn<Clients, Integer> clientsIdColumn;
    @FXML
    private TableColumn<Clients, String> clientsLoginColumn;

    @FXML
    private TableColumn<Clients, String> clientsNameColumn;

    @FXML
    private TableColumn<Clients, String> clientsPassportColumn;

    @FXML
    private TableColumn<Clients, String> clientsPasswordColumn;

    @FXML
    private TableColumn<Clients, Integer> clientsAgeColumn;

    @FXML
    private TableColumn<Clients, Integer> clientsidPaymentColumn;
    @FXML
    private TableView<Companies> companiesTable;

    @FXML
    private TableColumn<Companies, Integer> companiesIdColumn;

    @FXML
    private TableColumn<Companies, String> companiesNameColumn;

    @FXML
    private TableColumn<Companies, Double> companiesProfitColumn;

    @FXML
    private TableColumn<Companies, String> companiesAdresColumn;

    @FXML
    private Tab countriesTab;

    @FXML
    private TableView<Countries> countriesTable;

    @FXML
    private TableColumn<Countries, Integer> countriesIdColumn;

    @FXML
    private TableColumn<Countries, String> countriesNameColumn;

    @FXML
    private TableColumn<Countries, String> countriesSignsColumn;

    @FXML
    private Tab tursTab;

    @FXML
    private TableView<Turs> tursTable;

    @FXML
    private TableColumn<Turs, Integer> TursIdColumn;

    @FXML
    private TableColumn<Turs, String> TursHotelNameColumn;

    @FXML
    private TableColumn<Turs, String> TursEcscursionsColumn;

    @FXML
    private TableColumn<Turs, Date> TursDateStartColumn;

    @FXML
    private TableColumn<Turs, Date> TursDateFinishColumn;

    @FXML
    private TableColumn<Turs, Double> TursPriceColumn;

    @FXML
    private TableColumn<Turs, String> TursPhotoColumn;



    @FXML
    private TableColumn<Turs, Integer> TursIdHotelColumn;

    @FXML
    private TableColumn<Turs, Integer> TursIdCountryColumn;

    @FXML
    private TableColumn<Turs, Integer> TursIdDiscountColumn;

    @FXML
    private TableColumn<Turs, Integer> TursIdFoodColumn;

    @FXML
    private TableColumn<Turs, Integer> TursIdTransitColumn;


    @FXML
    private Tab tursOfClientsTab;

    @FXML
    private TableView<TursOfClients> tursOfClientsTable;
    @FXML
    private TableColumn<TursOfClients, Integer> TursOfClientsIdColumn;

    @FXML
    private TableColumn<TursOfClients, Integer> TursOfClientsIdClientColumn;

    @FXML
    private TableColumn<TursOfClients, Integer> TursOfClientsIdTureColumn;

    @FXML
    private TableColumn<TursOfClients, Integer> TursOfClientsCounterPeopleColumn;

    @FXML
    private TableColumn<TursOfClients, Integer> TursOfClientsCounterLess14yoColumn;

    @FXML
    private TableColumn<TursOfClients, Date> TursOfClientsBukingDateColumn;

    @FXML
    private Tab tursOfCompaniesTab;

    @FXML
    private TableView<TursOfCompanies> tursOfCompaniesTable;
    @FXML
    private TableColumn<TursOfCompanies, Integer> TursOfCompaniesIdColumn;

    @FXML
    private TableColumn<TursOfCompanies, Integer> TursOfCompaniesIdCompanyColumn;

    @FXML
    private TableColumn<TursOfCompanies, Integer> TursOfCompaniesIdTureColumn;

    @FXML
    private TableColumn<TursOfCompanies, Integer> TursOfCompaniesCounterColumn;

    @FXML
    private Tab viewsOfDiscountsTab;

    @FXML
    private TableView<ViewsOfDiscounts> viewsOfDiscountsTable;
    @FXML
    private TableColumn<ViewsOfDiscounts, Integer> ViewsOfDiscountsIdColumn;

    @FXML
    private TableColumn<ViewsOfDiscounts, String> ViewsOfDiscountsNameColumn;

    @FXML
    private TableColumn<ViewsOfDiscounts, String> ViewsOfDiscountsPercentsColumn;

    @FXML
    private Tab viewsOfFoodTab;

    @FXML
    private TableView<ViewsOfFood> viewsOfFoodTable;

    @FXML
    private TableColumn<ViewsOfFood, Integer> ViewsOfFoodIdColumn;

    @FXML
    private TableColumn<ViewsOfFood, String> ViewsOfFoodNameColumn;

    @FXML
    private Tab viewsOfHotelsTab;

    @FXML
    private Button supportButton;

    @FXML
    private TableView<ViewsOfHotels> viewsOfHotelsTable;

    @FXML
    private TableColumn<ViewsOfHotels, Integer> ViewsOfHotelsIdColumn;

    @FXML
    private TableColumn<ViewsOfHotels, String> ViewsOfHotelsClassColumn;

    @FXML
    private Tab viewsOfPaymentsTab;

    @FXML
    private TableView<ViewsOfPayments> viewsOfPaymentsTable;

    @FXML
    private TableColumn<ViewsOfPayments, Integer> ViewsOfPaymentsIdColumn;

    @FXML
    private TableColumn<ViewsOfPayments, String> ViewsOfPaymentsNameColumn;

    @FXML
    private Tab viewsOfTransitsTab;

    @FXML
    private TableView<ViewsOfTransits> viewsOfTransitsTable;

    @FXML
    private TableColumn<ViewsOfTransits, Integer> ViewsOfTransitsIdColumn;

    @FXML
    private TableColumn<ViewsOfTransits, String> ViewsOfTransitsNameColumn;

    @FXML
    private TextField txt_phone;

    @FXML
    private TableColumn<Clients, String> clientsPhoneColumn;
    @FXML
    private Tab reportsTab;

    private TableView curentTable;

    private int index = 0;
    private int indexForUpdate = 0;
    @FXML
    void initialize() {
        curentTable = clientsTable;
        tabPane1.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {

            switch (newTab.getId().trim()){
                case "clientsTab":
                    hideTheAllFields();
                    clientsFields.setVisible(true);
                    curentTable = clientsTable;
                    break;
                case "companiesTab":
                    hideTheAllFields();
                    companiesFields.setVisible(true);
                    curentTable = companiesTable;
                    break;
                case "countriesTab":
                    hideTheAllFields();
                    countriesFields.setVisible(true);
                    curentTable = countriesTable;
                    break;
                case "tursTab":
                    hideTheAllFields();
                    tourFields.setVisible(true);
                    curentTable = tursTable;
                    break;
                case "tursOfClientsTab":
                    hideTheAllFields();
                    tursOfClientsFields.setVisible(true);
                    curentTable = tursOfClientsTable;
                    break;
                case "tursOfCompaniesTab":
                    hideTheAllFields();
                    tursOfCompaniesFields.setVisible(true);
                    curentTable = tursOfCompaniesTable;
                    break;
                case "viewsOfDiscountsTab":
                    hideTheAllFields();
                    viewsOfDiscountsFields.setVisible(true);
                    curentTable = viewsOfDiscountsTable;
                    break;
                case "viewsOfHotelsTab":
                    hideTheAllFields();
                    viewsOfHotelsFields.setVisible(true);
                    curentTable = viewsOfHotelsTable;
                    break;
                case "viewsOfFoodTab":
                    hideTheAllFields();
                    viewsOfFoodFields.setVisible(true);
                    curentTable = viewsOfFoodTable;
                    break;
                case "viewsOfPaymentsTab":
                    hideTheAllFields();
                    viewsOfPaymentsFields.setVisible(true);
                    curentTable = viewsOfPaymentsTable;
                    break;
                case "viewsOfTransitsTab":
                    hideTheAllFields();
                    viewsOfTransitsFields.setVisible(true);
                    curentTable = viewsOfTransitsTable;
                    break;
                case "reportsTab":
                    hideTheAllFields();
                    curentTable = null;
                    break;
            }
            changeVisebleButtons();
        });

        fillTheAllTables();
        supportButton.setOnAction(event->{
            dbHadler.showHelp();
        });
    }
    public void showFirstReport(ActionEvent action){
        dbHadler.showReport("Tourism.jrxml");
    }
    public void showSecondReport(ActionEvent action){
        dbHadler.showReport("Tourism2.jrxml");
    }
    public void showThirdReport(ActionEvent action){
        dbHadler.showReport("Agenstva.jrxml");
    }

    public VBox currentVisibleFields(){
        if(clientsFields.isVisible())
            return clientsFields;
        if(companiesFields.isVisible())
            return companiesFields;
        if(countriesFields.isVisible())
            return countriesFields;
        if(tourFields.isVisible())
            return tourFields;
        if (tursOfClientsFields.isVisible())
            return tursOfClientsFields;
        if(tursOfCompaniesFields.isVisible())
            return tursOfCompaniesFields;
        if(viewsOfDiscountsFields.isVisible())
            return viewsOfDiscountsFields;
        if(viewsOfFoodFields.isVisible())
            return viewsOfFoodFields;
        if(viewsOfHotelsTable.isVisible())
            return viewsOfHotelsFields;
        if(viewsOfPaymentsFields.isVisible())
            return viewsOfPaymentsFields;
        if(viewsOfTransitsFields.isVisible())
            return  viewsOfTransitsFields;
        return null;

    }
    public void fillTheAllTables(){
        dbHadler = new DatabaseHadler();
        //clientsTable
        clientsIdColumn.setCellValueFactory(new PropertyValueFactory<Clients,Integer>("id"));
        clientsLoginColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("Login"));
        clientsNameColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("Name"));
        clientsPassportColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("Passport"));
        clientsPasswordColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("Password"));
        clientsAgeColumn.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("Age"));
        clientsidPaymentColumn.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("idPayment"));
        clientsPhoneColumn.setCellValueFactory(new PropertyValueFactory<Clients, String>("Phone"));
        listClients = dbHadler.getDataClients();
        clientsTable.setItems(listClients);

        //companiesTable
        companiesIdColumn.setCellValueFactory(new PropertyValueFactory<Companies,Integer>("id"));
        companiesNameColumn.setCellValueFactory(new PropertyValueFactory<Companies, String>("CompanyName"));
        companiesProfitColumn.setCellValueFactory(new PropertyValueFactory<Companies, Double>("Profit"));
        companiesAdresColumn.setCellValueFactory(new PropertyValueFactory<Companies, String>("CompanyAdres"));

        listCompanies = dbHadler.getDataCompanies();
        companiesTable.setItems(listCompanies);

        //countriesTable
        countriesIdColumn.setCellValueFactory(new PropertyValueFactory<Countries,Integer>("id"));
        countriesNameColumn.setCellValueFactory(new PropertyValueFactory<Countries, String>("CountryName"));
        countriesSignsColumn.setCellValueFactory(new PropertyValueFactory<Countries, String>("SignsCountry"));

        listCountries = dbHadler.getDataCourties();
        countriesTable.setItems(listCountries);

        //tursTable
        TursIdColumn.setCellValueFactory(new PropertyValueFactory<Turs,Integer>("id"));
        TursHotelNameColumn.setCellValueFactory(new PropertyValueFactory<Turs, String>("HotelName"));
        TursEcscursionsColumn.setCellValueFactory(new PropertyValueFactory<Turs, String>("Ecscursions"));
        TursDateStartColumn.setCellValueFactory(new PropertyValueFactory<Turs, Date>("DateStart"));
        TursDateFinishColumn.setCellValueFactory(new PropertyValueFactory<Turs, Date>("DateFinish"));
        TursPriceColumn.setCellValueFactory(new PropertyValueFactory<Turs, Double>("Price"));
        TursPhotoColumn.setCellValueFactory(new PropertyValueFactory<Turs, String>("Photo"));
        TursIdHotelColumn.setCellValueFactory(new PropertyValueFactory<Turs,Integer>("idHotel"));
        TursIdCountryColumn.setCellValueFactory(new PropertyValueFactory<Turs,Integer>("idCountry"));
        TursIdDiscountColumn.setCellValueFactory(new PropertyValueFactory<Turs,Integer>("idDiscount"));
        TursIdFoodColumn.setCellValueFactory(new PropertyValueFactory<Turs,Integer>("idFood"));
        TursIdTransitColumn.setCellValueFactory(new PropertyValueFactory<Turs,Integer>("idTransit"));


        listTurs = dbHadler.getDataTurs();
        tursTable.setItems(listTurs);
        //tursOfClientstable
        TursOfClientsIdColumn.setCellValueFactory(new PropertyValueFactory<TursOfClients,Integer>("id"));
        TursOfClientsIdClientColumn.setCellValueFactory(new PropertyValueFactory<TursOfClients,Integer>("idClient"));
        TursOfClientsIdTureColumn.setCellValueFactory(new PropertyValueFactory<TursOfClients,Integer>("idTure"));
        TursOfClientsCounterPeopleColumn.setCellValueFactory(new PropertyValueFactory<TursOfClients,Integer>("CounterPeople"));
        TursOfClientsCounterLess14yoColumn.setCellValueFactory(new PropertyValueFactory<TursOfClients,Integer>("CounterLess14yo"));
        TursOfClientsBukingDateColumn.setCellValueFactory(new PropertyValueFactory<TursOfClients,Date>("BukingDate"));
        TursOfClientsFinalPriceColumn.setCellValueFactory(new PropertyValueFactory<TursOfClients,Double>("FinalPrice"));
        listTursOfClients = isBurning ? dbHadler.getDataBurningTurs():dbHadler.getDataTursOfClients();
        tursOfClientsTable.setItems(listTursOfClients);

        //tursOfCompaniestable
        TursOfCompaniesIdColumn.setCellValueFactory(new PropertyValueFactory<TursOfCompanies,Integer>("id"));
        TursOfCompaniesIdCompanyColumn.setCellValueFactory(new PropertyValueFactory<TursOfCompanies,Integer>("IdCompany"));
        TursOfCompaniesIdTureColumn.setCellValueFactory(new PropertyValueFactory<TursOfCompanies,Integer>("IdTure"));
        TursOfCompaniesCounterColumn.setCellValueFactory(new PropertyValueFactory<TursOfCompanies,Integer>("Counter"));

        listTursOfCompanies = dbHadler.getDataTursOfCompanies();
        tursOfCompaniesTable.setItems(listTursOfCompanies);

        //viewsOfDiscountstable
        ViewsOfDiscountsIdColumn.setCellValueFactory(new PropertyValueFactory<ViewsOfDiscounts,Integer>("id"));
        ViewsOfDiscountsNameColumn.setCellValueFactory(new PropertyValueFactory<ViewsOfDiscounts,String>("NameOfDiscount"));
        ViewsOfDiscountsPercentsColumn.setCellValueFactory(new PropertyValueFactory<ViewsOfDiscounts,String>("PercentOfDiscount"));

        listViewsOfDiscounts = dbHadler.getDataViewsOfDiscounts();
       viewsOfDiscountsTable.setItems(listViewsOfDiscounts);

        //viewsOfFoodtable
        ViewsOfFoodIdColumn.setCellValueFactory(new PropertyValueFactory<ViewsOfFood,Integer>("id"));
        ViewsOfFoodNameColumn.setCellValueFactory(new PropertyValueFactory<ViewsOfFood,String>("Name"));

        listViewsOfFood = dbHadler.getDataViewsOfFood();
        viewsOfFoodTable.setItems(listViewsOfFood);

        //viewsOfPaymentstable
        ViewsOfPaymentsIdColumn.setCellValueFactory(new PropertyValueFactory<ViewsOfPayments,Integer>("id"));
        ViewsOfPaymentsNameColumn.setCellValueFactory(new PropertyValueFactory<ViewsOfPayments,String>("Name"));

        listViewsOfPayments = dbHadler.getDataViewsOfPayments();
        viewsOfPaymentsTable.setItems(listViewsOfPayments);

        //viewsOfHotelstable
        ViewsOfHotelsIdColumn.setCellValueFactory(new PropertyValueFactory< ViewsOfHotels,Integer>("id"));
        ViewsOfHotelsClassColumn.setCellValueFactory(new PropertyValueFactory< ViewsOfHotels,String>("HotelClass"));

        listViewsOfHotels = dbHadler.getDataViewsOfHotels();
        viewsOfHotelsTable.setItems(listViewsOfHotels);

        //viewsOfTransitsstable
        ViewsOfTransitsIdColumn.setCellValueFactory(new PropertyValueFactory< ViewsOfTransits,Integer>("id"));
        ViewsOfTransitsNameColumn.setCellValueFactory(new PropertyValueFactory< ViewsOfTransits,String>("Name"));

        listViewsOfTransits = dbHadler.getDataViewsOfTransits();
        viewsOfTransitsTable.setItems(listViewsOfTransits);
    }
    public void setIsBurningTrue(){
        isBurning = true;
        changeVisebleButtons();
    }
    public void setIsBurningFalse(){
        isBurning = false;
        changeVisebleButtons();
    }
    public void changeVisebleButtons(){
        try{
            if(curentTable.getId() == tursOfClientsTable.getId() && isBurning == true){
                addNewEntry.setVisible(false);
                updateEntry.setVisible(false);
                deleteEntry.setVisible(false);
                fillTheAllTables();
            }
            else{
                addNewEntry.setVisible(true);
                updateEntry.setVisible(true);
                deleteEntry.setVisible(true);
                fillTheAllTables();
            }
        }catch(NullPointerException ex){
            addNewEntry.setVisible(false);
            updateEntry.setVisible(false);
            deleteEntry.setVisible(false);
        }

    }
    public void hideTheAllFields(){
        clientsFields.setVisible(false);
        companiesFields.setVisible(false);
        countriesFields.setVisible(false);
        tourFields.setVisible(false);
        tursOfClientsFields.setVisible(false);
        tursOfCompaniesFields.setVisible(false);
        viewsOfDiscountsFields.setVisible(false);
        viewsOfFoodFields.setVisible(false);
        viewsOfHotelsFields.setVisible(false);
        viewsOfPaymentsFields.setVisible(false);
        viewsOfTransitsFields.setVisible(false);
    }
    public void handleAddNewEntryButton(ActionEvent event){
        dbHadler= new DatabaseHadler();
        String sql;
        try{
           switch(currentVisibleFields().getId()){
               case "clientsFields":
                       conn = dbHadler.getConnection();
                   Pattern pattern = Pattern.compile("^(?=.*[A-zА-я])(?=.*\\d)[A-zА-я\\d]{8,}$");
                   Matcher matcher = pattern.matcher(txt_Password.getText().trim());
                   while (!matcher.find()) {
                       throw new SQLException("Пароль должен быть не менее 8 символов, а также содеражать как минимум 1 буквку и цифру");
                   }
                   if(Integer.parseInt(txt_Age.getText().trim()) < 18){
                       throw new SQLException("Пользователь должен быть старше 18 лет!");

                   }
                   if(Integer.parseInt(txt_Age.getText().trim()) > 130){
                       throw new SQLException("Пользователь не может быть старше 130 лет!");
                   }
                       sql = "INSERT INTO Clients(Login, Name, Passport, Password, Age, idPayment, Phone) Values (?,?,?,?,?,?,?)";
                       pst = conn.prepareStatement(sql);
                       pst.setString(1, txt_Login.getText().trim());
                       pst.setString(2, txt_ClientName.getText().trim());
                       pst.setString(3, txt_Passport.getText().trim());
                       pst.setString(4, txt_Password.getText().trim());
                       pst.setInt(5, Integer.parseInt(txt_Age.getText().trim()));
                       pst.setInt(6, Integer.parseInt(txt_ClientIdPayment.getText().trim()));
                       pst.setString(7, txt_phone.getText().trim());
                       pst.execute();
                       break;
               case "companiesFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO Companies(CompanyName, Profit, CompanyAdres) Values (?,?,?)";
                   pst = conn.prepareStatement(sql);

                   pst.setString(1, txt_CompanyName.getText().trim());
                   pst.setDouble(2, Double.parseDouble(txt_CompanyProfit.getText().trim()));
                   pst.setString(3, txt_CompanyAdres.getText().trim());
                   pst.execute();
                   break;
               case "countriesFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO Countries(CountryName, SignsCountry) Values (?,?)";
                   pst = conn.prepareStatement(sql);

                   pst.setString(1, txt_CountryName.getText().trim());
                   pst.setString(2, txt_CountrySigns.getText().trim());
                   pst.execute();
                   break;
               case "tourFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO Turs(HotelName,Ecscursions,DateStart,DateFinish,Price, Photo, idHotel, idCountry, idDiscount, idFood,idTransit ) Values (?,?,?,?,?,?,?,?,?,?,?)";
                   pst = conn.prepareStatement(sql);

                   pst.setString(1, txt_HotelName.getText().trim());
                   pst.setString(2, txt_Ecscursions.getText().trim());
                   pst.setDate(3, Date.valueOf(txt_TourDateStart.getText().trim()));
                   pst.setDate(4, Date.valueOf(txt_TourDateFinish.getText().trim()));
                   pst.setDouble(5, Double.parseDouble(txt_TourPrice.getText().trim()));
                   pst.setString(6, txt_TourPhoto.getText().trim());
                   pst.setInt(7, Integer.parseInt(txt_TourIdHotel.getText().trim()));
                   pst.setInt(8, Integer.parseInt(txt_TourIdCountry.getText().trim()));
                   pst.setInt(9, Integer.parseInt(txt_TourIdDiscount.getText().trim()));
                   pst.setInt(10, Integer.parseInt(txt_TourIdFood.getText().trim()));
                   pst.setInt(11, Integer.parseInt(txt_TourIdTransit.getText().trim()));
                   pst.execute();
                   break;
               case "tursOfClientsFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO TursOfClients(IdClient,IdTure, CounterPeople, CounterLess14yo, BukingDate,FinalPrice) Values (?,?,?,?,?,?)";
                   pst = conn.prepareStatement(sql);

                   pst.setInt(1, Integer.parseInt(txt_ToursOfClientsIdClient.getText().trim()));
                   pst.setInt(2, Integer.parseInt(txt_ToursOfClientsIdTure.getText().trim()));
                   pst.setString(3, txt_ToursOfClientsCounterPeople.getText().trim());
                   pst.setString(4, txt_ToursOfClientsCounterLess14yo.getText().trim());
                   pst.setDate(5, Date.valueOf(txt_ToursOfClientsBukingDate.getText().trim()));
                   pst.setDouble(6,Double.parseDouble(txt_ToursOfClientsFinalPrice.getText().trim()));
                   pst.execute();
                   break;
               case "tursOfCompaniesFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO TursOfCompanies(IdCompany,IdTure, Counter) Values (?,?,?)";
                   pst = conn.prepareStatement(sql);

                   pst.setInt(1, Integer.parseInt(txt_ToursOfCompaniesIdCompany.getText().trim()));
                   pst.setInt(2, Integer.parseInt(txt_ToursOfCompaniesIdTure.getText().trim()));
                   pst.setInt(3, Integer.parseInt(txt_ToursOfCompaniesCounter.getText().trim()));
                   pst.execute();
                   break;
               case "viewsOfDiscountsFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO ViewsOfDiscounts(NameOFDiscount,PercentOfDiscount) Values (?,?)";
                   pst = conn.prepareStatement(sql);
                   pst.setString(1, txt_viewsOfDiscountsNameOfDiscount.getText().trim());
                   pst.setString(2, txt_viewsOfDiscountsPercentsOfDiscount.getText().trim());
                   pst.execute();
                   break;
               case "viewsOfFoodFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO ViewsOfFood(Name) Values (?)";
                   pst = conn.prepareStatement(sql);
                   pst.setString(1, txt_viewsOfFoodName.getText().trim());
                   pst.execute();
                   break;
               case "viewsOfHotelsFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO ViewsOfHotels(HotelsClass) Values (?)";
                   pst = conn.prepareStatement(sql);
                   pst.setString(1, txt_viewsOfHotelsClass.getText().trim());
                   pst.execute();
                   break;
               case "viewsOfPaymentsFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO ViewsOfPayments(Name) Values (?)";
                   pst = conn.prepareStatement(sql);
                   pst.setString(1, txt_viewsOfPaymentName.getText().trim());
                   pst.execute();
                   break;
               case "viewsOfTransitsFields":
                   conn = dbHadler.getConnection();
                   sql = "INSERT INTO ViewsOfTransits(Name) Values (?)";
                   pst = conn.prepareStatement(sql);
                   pst.setString(1, txt_viewsOfTransitName.getText().trim());
                   pst.execute();
                   break;

           }
            fillTheAllTables();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //tabPane1.getSelectionModel().getSelectedItem();
    }
    public void getSelected(){
        index = curentTable.getSelectionModel().getSelectedIndex();
        if(index <= -1 ) {
            return;
        }

        switch(curentTable.getId()){
            case "clientsTable":
                txt_Login.setText(clientsLoginColumn.getCellData(index).toString());
                txt_ClientName.setText(clientsNameColumn.getCellData(index).toString());
                txt_Passport.setText(clientsPassportColumn.getCellData(index).toString());
                txt_Password.setText(clientsPasswordColumn.getCellData(index).toString());
                txt_Age.setText(clientsAgeColumn.getCellData(index).toString());
                txt_ClientIdPayment.setText(clientsidPaymentColumn.getCellData(index).toString());
                indexForUpdate = clientsIdColumn.getCellData(index);
                try {
                    txt_phone.setText(clientsPhoneColumn.getCellData(index).toString());
                }catch(NullPointerException ex){
                    txt_phone.setText("");
                }
                break;
            case "companiesTable":

                 txt_CompanyName.setText(companiesNameColumn.getCellData(index).toString());
                 txt_CompanyProfit.setText(companiesProfitColumn.getCellData(index).toString());
                 txt_CompanyAdres.setText(companiesAdresColumn.getCellData(index).toString());
                indexForUpdate = companiesIdColumn.getCellData(index);
                break;
            case "countriesTable":
                txt_CountryName.setText(countriesNameColumn.getCellData(index).toString());
                txt_CountrySigns.setText(countriesSignsColumn.getCellData(index).toString());
                indexForUpdate = countriesIdColumn.getCellData(index);
                break;
            case "tursTable":
                txt_HotelName.setText(TursHotelNameColumn.getCellData(index).toString());
                try{
                    txt_Ecscursions.setText(TursEcscursionsColumn.getCellData(index).toString());
                }catch (NullPointerException ex){
                    txt_Ecscursions.setText("");
                }
                txt_TourDateStart.setText(TursDateStartColumn.getCellData(index).toString());
                txt_TourDateFinish.setText(TursDateFinishColumn.getCellData(index).toString());
                txt_TourPrice.setText(TursPriceColumn.getCellData(index).toString());
                txt_TourPhoto.setText(TursPhotoColumn.getCellData(index).toString());
                txt_TourIdHotel.setText(TursIdHotelColumn.getCellData(index).toString());
                txt_TourIdCountry.setText(TursIdCountryColumn.getCellData(index).toString());
                txt_TourIdDiscount.setText(TursIdDiscountColumn.getCellData(index).toString());
                txt_TourIdFood.setText(TursIdFoodColumn.getCellData(index).toString());
                txt_TourIdTransit.setText(TursIdTransitColumn.getCellData(index).toString());
                indexForUpdate = TursIdColumn.getCellData(index);
                break;
            case "tursOfClientsTable":
                txt_ToursOfClientsIdClient.setText(TursOfClientsIdClientColumn.getCellData(index).toString());
                txt_ToursOfClientsIdTure.setText(TursOfClientsIdTureColumn.getCellData(index).toString());
                txt_ToursOfClientsCounterPeople.setText(TursOfClientsCounterPeopleColumn.getCellData(index).toString());
                txt_ToursOfClientsCounterLess14yo.setText(TursOfClientsCounterLess14yoColumn.getCellData(index).toString());
                txt_ToursOfClientsBukingDate.setText(TursOfClientsBukingDateColumn.getCellData(index).toString());
                txt_ToursOfClientsFinalPrice.setText(TursOfClientsFinalPriceColumn.getCellData(index).toString());
                indexForUpdate = TursOfClientsIdColumn.getCellData(index);
                break;
            case "tursOfCompaniesTable":
                txt_ToursOfCompaniesIdCompany.setText(TursOfCompaniesIdCompanyColumn.getCellData(index).toString());
                txt_ToursOfCompaniesIdTure.setText(TursOfCompaniesIdTureColumn.getCellData(index).toString());
                txt_ToursOfCompaniesCounter.setText(TursOfCompaniesCounterColumn.getCellData(index).toString());
                indexForUpdate = TursOfCompaniesIdColumn.getCellData(index);
                break;
            case "viewsOfDiscountsTable":
                txt_viewsOfDiscountsNameOfDiscount.setText(ViewsOfDiscountsNameColumn.getCellData(index).toString());
                txt_viewsOfDiscountsPercentsOfDiscount.setText(ViewsOfDiscountsPercentsColumn.getCellData(index).toString());
                indexForUpdate = ViewsOfDiscountsIdColumn.getCellData(index);
                break;
            case "viewsOfFoodTable":
                txt_viewsOfFoodName.setText(ViewsOfFoodNameColumn.getCellData(index).toString());
                indexForUpdate = ViewsOfFoodIdColumn.getCellData(index);
                break;
            case "viewsOfHotelsTable":
                 txt_viewsOfHotelsClass.setText(ViewsOfHotelsClassColumn.getCellData(index).toString());
                indexForUpdate = ViewsOfHotelsIdColumn.getCellData(index);
                break;
            case "viewsOfPaymentsTable":
                txt_viewsOfPaymentName.setText(ViewsOfPaymentsNameColumn.getCellData(index).toString());
                indexForUpdate = ViewsOfPaymentsIdColumn.getCellData(index);
                break;
            case "viewsOfTransitsTable":
                txt_viewsOfTransitName.setText(ViewsOfTransitsNameColumn.getCellData(index).toString());
                indexForUpdate = ViewsOfTransitsIdColumn.getCellData(index);
                break;
        }

    }
    public void Edit(){
        dbHadler = new DatabaseHadler();
        String sql, value1,value2,value3,value4,value5,value6,value7,value8,value9,value10,value11,value12;
        //index = curentTable.getSelectionModel().getSelectedIndex()+1;

        try {
            conn = dbHadler.getConnection();
            switch(currentVisibleFields().getId()){
                case "clientsFields":
                     value1 = txt_Login.getText().trim();
                     value2 = txt_ClientName.getText().trim();
                     value3 = txt_Passport.getText().trim();
                     value4 = txt_Password.getText().trim();
                     value5 = txt_Age.getText().trim();
                     value6 = txt_ClientIdPayment.getText().trim();
                     value7 = txt_phone.getText().trim();
                    sql = "update Clients set Login = '"+value1+"',Name = '"+value2+"', Passport ='"+value3+"',Password = '"+value4+"', Age = "+value5+", idPayment= "+value6+", Phone = '"+value7+"' where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                    break;
                case "companiesFields":
                    value1 = txt_CompanyName.getText().trim();
                    value2 = txt_CompanyProfit.getText().trim();
                    value3 = txt_CompanyAdres.getText().trim();
                    sql = "update Companies set CompanyName ='"+value1+"',Profit = "+value2+", CompanyAdres ='"+value3+"' where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    break;
                case "countriesFields":
                    value1 = txt_CountryName.getText().trim();
                    value2 = txt_CountrySigns.getText().trim();
                    sql = "update Countries set CountryName ='"+value1+"',SignsCountry = '"+value2+"' where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    break;
                case "tourFields":
                    value1 = txt_HotelName.getText().trim();
                    value2 = txt_Ecscursions.getText().trim();
                    value3 = txt_TourDateStart.getText().trim();
                    value4 = txt_TourDateFinish.getText().trim();
                    value5 = txt_TourPrice.getText().trim();
                    value6 = txt_TourPhoto.getText().trim();
                    value8 = txt_TourIdHotel.getText().trim();
                    value9 = txt_TourIdCountry.getText().trim();
                    value10 = Integer.parseInt(txt_TourIdDiscount.getText().trim()) == 0? "null":txt_TourIdDiscount.getText().trim();
                    value11 = txt_TourIdFood.getText().trim();
                    value12 = txt_TourIdTransit.getText().trim();
                    try{
                        if(Date.valueOf(value3).getTime() > Date.valueOf(value4).getTime())
                            throw new SQLException("Дата отьзда не может быть позже даты прибытия!");
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }

                    sql = "update Turs set HotelName = '"+value1+"', Ecscursions = '"+value2+"', DateStart = ?, DateFinish = ?, Price = "+value5+", Photo = ? " +
                            ", idHotel= "+value8+", idCountry= "+value9+", idDiscount= "+value10+", idFood= "+value11+", idTransit= "+value12+" where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.setDate(1, Date.valueOf(value3));
                    pst.setDate(2, Date.valueOf(value4));
                    pst.setString(3, value6);
                    pst.execute();

                    break;
                case "tursOfClientsFields":
                    value1 = txt_ToursOfClientsIdClient.getText().trim();
                    value2 = txt_ToursOfClientsIdTure.getText().trim();
                    value3 = txt_ToursOfClientsCounterPeople.getText().trim();
                    value4 = txt_ToursOfClientsCounterLess14yo.getText().trim();
                    value5 = txt_ToursOfClientsBukingDate.getText().trim();
                    value6 = txt_ToursOfClientsFinalPrice.getText().trim();
                    sql = "update TursOfClients set IdClient ="+value1+",IdTure = "+value2+",CounterPeople = "+value3+",CounterLess14yo = "+value4+",BukingDate = ?,FinalPrice = "+value6+" where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.setDate(1,Date.valueOf(value5));
                    pst.execute();
                    break;
                case "tursOfCompaniesFields":
                    value1 = txt_ToursOfCompaniesIdCompany.getText().trim();
                    value2 = txt_ToursOfCompaniesIdTure.getText().trim();
                    value3 = txt_ToursOfCompaniesCounter.getText().trim();
                    sql = "update TursOfCompanies set IdCompany ="+value1+",IdTure = "+value2+",Counter= "+value3+" where id = "+indexForUpdate+" ";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    break;
                case "viewsOfDiscountsFields":
                    value1 = txt_viewsOfDiscountsNameOfDiscount.getText().trim();
                    value2 = txt_viewsOfDiscountsPercentsOfDiscount.getText().trim();
                    sql = "update ViewsOfDiscounts set NameOFDiscount ='"+value1+"',PercentOfDiscount = '"+value2+"' where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    break;
                case "viewsOfFoodFields":
                    value1 = txt_viewsOfFoodName.getText().trim();
                    sql = "update ViewsOfFood set Name ='"+value1+"' where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    break;
                case "viewsOfHotelsFields":
                    value1 = txt_viewsOfHotelsClass.getText().trim();
                    sql = "update ViewsOfHotels set HotelClass ='"+value1+"' where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    break;
                case "viewsOfPaymentsFields":
                    value1 = txt_viewsOfPaymentName.getText().trim();
                    sql = "update ViewsOfPayments set Name ='"+value1+"' where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    break;
                case "viewsOfTransitsFields":
                    value1 = txt_viewsOfTransitName.getText().trim();
                    sql = "update ViewsOfTransits set Name ='"+value1+"' where id = "+indexForUpdate+"";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    break;
            }
            fillTheAllTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void Delete(){
        dbHadler = new DatabaseHadler();
        index = curentTable.getSelectionModel().getSelectedIndex();
        String sql="";
        switch(currentVisibleFields().getId()){
            case "clientsFields":
                sql = "delete from Clients where id = "+indexForUpdate+"";
                break;
            case "companiesFields":
                sql = "delete from Companies where id = "+indexForUpdate+"";
                break;
            case "countriesFields":
                sql = "delete from Countries where id = "+indexForUpdate+"";
                break;
            case "tourFields":
                sql = "delete from turs where id = "+indexForUpdate+"";
                break;
            case "tursOfClientsFields":
                sql = "delete from TursOfClients where id = "+indexForUpdate+"";
                break;
            case "tursOfCompaniesFields":
                sql = "delete from TursOfCompanies  where id = "+indexForUpdate+" ";
                break;
            case "viewsOfDiscountsFields":
                sql = "delete from ViewsOfDiscounts where id = "+indexForUpdate+"";
                break;
            case "viewsOfFoodFields":
                sql = "delete from ViewsOfFood where id = "+indexForUpdate+"";
                break;
            case "viewsOfHotelsFields":
                sql = "delete from ViewsOfHotels where id = "+indexForUpdate+"";
                break;
            case "viewsOfPaymentsFields":
                sql = "delete from ViewsOfPayments where id = "+indexForUpdate+"";
                break;
            case "viewsOfTransitsFields":
                sql = "delete from ViewsOfTransits where id = "+indexForUpdate+"";
                break;
        }
        try{
            conn = dbHadler.getConnection();
            pst = conn.prepareStatement(sql);
            pst.execute();
            fillTheAllTables();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public AdminMenuController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Admin/adminMenu.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 1250, 504);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchAdminMenuController(Stage stage) {
        this.stage = stage;
        stage.setTitle("Admin menu");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.hide();
        stage.show();
    }
}

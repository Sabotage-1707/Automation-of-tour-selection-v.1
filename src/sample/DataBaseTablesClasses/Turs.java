package sample.DataBaseTablesClasses;

import java.sql.Date;

public class Turs {
    private int id;
    private String HotelName;
    private String Ecscursions;
    private Date DateStart;
    private Date DateFinish;
    private double price;
    private String Photo;
    private int idHotel;
    private int idCountry;
    private int idDiscount;
    private int idFood;
    private int idTransit;

    public Turs(int id, String hotelName, String ecscursions, Date dateStart, Date dateFinish, double price, String photo, int idHotel, int idCountry, int idDiscount, int idFood, int idTransit) {
        this.id = id;
        HotelName = hotelName;
        Ecscursions = ecscursions;
        DateStart = dateStart;
        DateFinish = dateFinish;
        this.price = price;
        Photo = photo;
        this.idHotel = idHotel;
        this.idCountry = idCountry;
        this.idDiscount = idDiscount;
        this.idFood = idFood;
        this.idTransit= idTransit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getEcscursions() {
        return Ecscursions;
    }

    public void setEcscursions(String ecscursions) {
        Ecscursions = ecscursions;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public Date getDateFinish() {
        return DateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        DateFinish = dateFinish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getIdTransit() {
        return idTransit;
    }

    public void setIdTransit(int idTransit) {
        this.idTransit = idTransit;
    }
}

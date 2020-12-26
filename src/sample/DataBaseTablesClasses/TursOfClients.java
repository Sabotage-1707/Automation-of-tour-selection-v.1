package sample.DataBaseTablesClasses;

import java.sql.Date;

public class TursOfClients {
    private int id;
    private int idClient;
    private int IdTure;
    private int CounterPeople;
    private int CounterLess14yo;
    private Date BukingDate;
    private double FinalPrice;

    public double getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        FinalPrice = finalPrice;
    }

    public TursOfClients(int id, int idClient, int idTure, int counterPeople, int counterLess14yo, Date bukingDate, double finalPrice) {
        this.id = id;
        this.idClient = idClient;
        IdTure = idTure;
        CounterPeople = counterPeople;
        CounterLess14yo = counterLess14yo;
        BukingDate = bukingDate;
        FinalPrice = finalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdTure() {
        return IdTure;
    }

    public void setIdTure(int idTure) {
        IdTure = idTure;
    }

    public int getCounterPeople() {
        return CounterPeople;
    }

    public void setCounterPeople(int counterPeople) {
        CounterPeople = counterPeople;
    }

    public int getCounterLess14yo() {
        return CounterLess14yo;
    }

    public void setCounterLess14yo(int counterLess14yo) {
        CounterLess14yo = counterLess14yo;
    }

    public Date getBukingDate() {
        return BukingDate;
    }

    public void setBukingDate(Date bukingDate) {
        BukingDate = bukingDate;
    }
}

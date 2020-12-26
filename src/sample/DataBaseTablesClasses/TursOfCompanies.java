package sample.DataBaseTablesClasses;

public class TursOfCompanies {
    private int id;
    private int IdTure;
    private int IdCompany;
    private int Counter;

    public TursOfCompanies(int id, int idCompany,int idTure ,int counter) {
        this.id = id;
        this.IdTure = idTure;
        this.IdCompany = idCompany;
        Counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTure() {
        return IdTure;
    }

    public void setIdTure(int idTure) {
        this.IdTure = idTure;
    }

    public int getIdCompany() {
        return IdCompany;
    }

    public void setIdCompany(int idCompany) {
        this.IdCompany = idCompany;
    }

    public int getCounter() {
        return Counter;
    }

    public void setCounter(int counter) {
        Counter = counter;
    }
}

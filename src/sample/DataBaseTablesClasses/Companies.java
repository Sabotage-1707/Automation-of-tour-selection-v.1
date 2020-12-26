package sample.DataBaseTablesClasses;

public class Companies {
    private int id;
    private String CompanyName;
    private double Profit;
    private String CompanyAdres;

    public Companies(int id, String companyName, double profit, String companyAdres) {
        this.id = id;
        CompanyName = companyName;
        Profit = profit;
        CompanyAdres = companyAdres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public double getProfit() {
        return Profit;
    }

    public void setProfit(double profit) {
        Profit = profit;
    }

    public String getCompanyAdres() {
        return CompanyAdres;
    }

    public void setCompanyAdres(String companyAdres) {
        CompanyAdres = companyAdres;
    }



}

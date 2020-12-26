package sample.DataBaseTablesClasses;

public class Countries {
    private int id;
    private String CountryName;
    private String SignsCountry;

    public Countries(int id, String countryName, String signsCountry) {
        this.id = id;
        CountryName = countryName;
        SignsCountry = signsCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getSignsCountry() {
        return SignsCountry;
    }

    public void setSignsCountry(String signsCountry) {
        SignsCountry = signsCountry;
    }
}

package sample.DataBaseTablesClasses;

public class ViewsOfHotels {
    private int id;
    private String HotelClass;

    public ViewsOfHotels(int id, String hotelClass) {
        this.id = id;
        HotelClass = hotelClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelClass() {
        return HotelClass;
    }

    public void setHotelClass(String hotelClass) {
        HotelClass = hotelClass;
    }
}

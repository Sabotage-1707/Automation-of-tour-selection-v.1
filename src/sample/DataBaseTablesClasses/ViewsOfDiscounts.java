package sample.DataBaseTablesClasses;

public class ViewsOfDiscounts {
    private int id;
    private String NameOfDiscount;
    private String PercentOfDiscount;

    public ViewsOfDiscounts(int id, String nameOfDiscount, String percentOfDiscount) {
        this.id = id;
        NameOfDiscount = nameOfDiscount;
        PercentOfDiscount = percentOfDiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfDiscount() {
        return NameOfDiscount;
    }

    public void setNameOfDiscount(String nameOfDiscount) {
        NameOfDiscount = nameOfDiscount;
    }

    public String getPercentOfDiscount() {
        return PercentOfDiscount;
    }

    public void setPercentOfDiscount(String percentOfDiscount) {
        PercentOfDiscount = percentOfDiscount;
    }
}

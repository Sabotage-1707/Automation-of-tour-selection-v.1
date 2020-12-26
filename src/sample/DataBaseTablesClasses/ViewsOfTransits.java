package sample.DataBaseTablesClasses;

public class ViewsOfTransits {
    private int id;
    private String Name;

    public ViewsOfTransits(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

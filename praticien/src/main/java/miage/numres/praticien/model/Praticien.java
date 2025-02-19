package miage.numres.praticien.model;

public class Praticien {

    private String name;
    private int id;

    public Praticien(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Praticien [name=" + name + ", id=" + id + "]";
    }
}
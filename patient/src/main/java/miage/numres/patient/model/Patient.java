package miage.numres.patient.model;

public class Patient {

    private String name;
    private int id;

    public Patient(int id, String name) {
        this.id = id;
        this.name = name;
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
        return "Patient [name=" + name + ", id=" + id + "]";
    }
}
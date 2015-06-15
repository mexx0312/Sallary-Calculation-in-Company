package Model;

/**
 * Created by Yevhenii on 5/3/15.
 */
public class Employee {
    private int id;
    private String name;
    private String surname;
    private Department department;
    private int sallery;
    private double restBalance;

    public Employee (int id, String name, String surname, int sallery){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sallery = sallery;
    }

    public Employee (int id, String name, String surname, int sallery, int bonus){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sallery = sallery;
        this.restBalance = bonus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getRestBalance() {
        return restBalance;
    }

    public void setRestBalance(double restBalance) {
        this.restBalance = restBalance;
    }

    public int getSallery() {
        return sallery;
    }

    public void setSallery(int sallery) {
        this.sallery = sallery;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sallery=" + sallery +
                ", bonus=" + restBalance +
                '}' + "\n";
    }
}

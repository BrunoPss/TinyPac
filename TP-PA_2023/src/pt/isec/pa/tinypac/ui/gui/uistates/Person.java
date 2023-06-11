package pt.isec.pa.tinypac.ui.gui.uistates;

public class Person { //DELETE
    //Internal Data
    private String firstName;
    private String secondName;

    //Constructor
    public Person(String name, String surname) {
        this.firstName = name;
        this.secondName = surname;
    }

    //Get Methods

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }


    //Set Methods

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }


    //Methods


    //Overrides


    //Internal Functions


}
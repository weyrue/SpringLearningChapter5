package com.zy.bean.propertyEditor;

public class FullName {
    private String surname;
    private String firstname;

    public FullName(String surname, String firstname) {
        this.surname = surname;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "FullName{" +
                "surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}

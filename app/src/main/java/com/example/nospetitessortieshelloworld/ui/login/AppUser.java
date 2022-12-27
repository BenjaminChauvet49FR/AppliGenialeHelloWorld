package com.example.nospetitessortieshelloworld.ui.login;
public class AppUser {

    private String name;
    private String password;
    private int departement;

    public AppUser() {

    }

    public AppUser(String name, String password, int departement) {
        this.name = name;
        this.password = password;
        this.departement = departement;
    }

    public String getName() {
        return name;
    }

    public int getDepartement() {
        return departement;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String name) {
        this.name = name;
    }

    public void setDepartment(String name) {
        this.name = name;
    }
}
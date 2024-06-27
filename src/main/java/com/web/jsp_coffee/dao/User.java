package com.web.jsp_coffee.dao;

public class User {
    private int UserID;
    private String Name;
    private String DateOfBirth;
    private String Phone;
    private String Email;
    private String Password;
    private String Address;
    private String Role;

    public User() {
    }

    public User(int UserID, String Name, String DateOfBirth, String Phone, String Email, String Password, String Address, String Role) {
        this.UserID = UserID;
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.Phone = Phone;
        this.Email = Email;
        this.Password = Password;
        this.Address = Address;
        this.Role = Role;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
}




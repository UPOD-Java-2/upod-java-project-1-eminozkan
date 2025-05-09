package user;

import java.math.BigDecimal;

public class User {
    private String username;

    private String password;

    private String name;

    private String surname;

    private String tckn;

    private Gender gender;


    public User(String username, String password, String name, String surname, String tckn, Gender gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.tckn = tckn;
        this.gender = gender;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public User setTckn(String tckn) {
        this.tckn = tckn;
        return this;
    }

    public User setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTckn() {
        return tckn;
    }

    public Gender getGender() {
        return gender;
    }
}

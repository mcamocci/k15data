package com.haikarose.k15data.HaikarosePojos;

public class UserPreference {
    public String getEmail() {
        return email;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setEmail(String phone) {
        this.email = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String password;
    private int id;
}

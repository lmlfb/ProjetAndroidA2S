package com.a2s.mvvv;

public class Login {

    int isLogged;
    int id;

    static int idStatic;

    Login(int isLogged){
        this.isLogged = isLogged;
    }

    int getIsLogin(){
        return isLogged;
    }

    void setIsLogin(int isLogged){
        this.isLogged = isLogged;
    }

    int getId(){
        return  id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

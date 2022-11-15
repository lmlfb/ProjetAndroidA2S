package com.a2s.mvvv;

public class Login {

    int isLogged;

    Login(int isLogged){
        this.isLogged = isLogged;
    }

    int getIsLogin(){
        return isLogged;
    }

    void setIsLogin(int isLogged){
        this.isLogged = isLogged;
    }


}

package com.a2s.mvvv;

public class Login {

    int isLogged;
    int id_user;

    Login(int isLogged, int id_user){
        this.isLogged = isLogged;
        this.id_user = id_user;
    }

    int getIsLogin(){
        return isLogged;
    }

    void setIsLogin(int isLogged){
        this.isLogged = isLogged;
    }

    int getid_user(){
        return  id_user;
    }

    void setId(int id) {
        this.id_user = id;
    }
}

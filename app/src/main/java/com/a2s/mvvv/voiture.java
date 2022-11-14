package com.a2s.mvvv;

public class voiture {

    private Integer id;
    private String modele;
    private int prix;

    voiture(Integer id, String modele, int prix) {
        this.id=id;
        this.modele=modele;
        this.prix=prix;
    }

    public Integer getid() {
        return id;
    }
    public void setid(Integer exoLvl) {
        this.id = id;
    }
    public String getmodele() {
        return modele;
    }
    public void setmodele(String titre) {
        this.modele = modele;
    }
    public int getprix() {
        return prix;
    }
    public void setprix(int prix) {
        this.prix = prix;
    }
}

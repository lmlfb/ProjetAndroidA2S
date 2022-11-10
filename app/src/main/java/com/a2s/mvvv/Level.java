package com.a2s.mvvv;

public class Level {

    private Integer exoLvl;
    private String titre;


    Level(Integer exoLvl, String titre) {
        this.exoLvl=exoLvl;
        this.titre=titre;
    }

    public Integer getexoLvl() {
        return exoLvl;
    }
    public void setexoLvl(Integer exoLvl) {
        this.exoLvl = exoLvl;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

}

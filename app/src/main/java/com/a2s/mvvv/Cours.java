package com.a2s.mvvv;

import java.io.Serializable;

public class Cours implements Serializable {

    private Integer id;
    private String titre;
    private String resume;
    private String cours;

    Cours(Integer id, String titre, String resume, String cours) {
        this.id=id;
        this.titre=titre;
        this.resume=resume;
        this.cours=cours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}



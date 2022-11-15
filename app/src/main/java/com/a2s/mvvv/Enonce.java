package com.a2s.mvvv;

import java.io.Serializable;

public class Enonce implements Serializable {

        private Integer id;
        private String titre;
        private String question;
        private String bdd_enonce;
        private Integer exoNb;
        private Integer exoLvl;

        Enonce(Integer id, String titre, String question, String bdd_enonce, Integer exoNb, Integer exoLvl) {
            this.id=id;
            this.titre=titre;
            this.question=question;
            this.bdd_enonce=bdd_enonce;
            this.exoNb=exoNb;
            this.exoLvl=exoLvl;
        }

        public Integer getId() {
            return this.id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getTitre() {
            return titre;
        }
        public void setTitre(String titre) {
            this.titre = titre;
        }
        public String getQuestion() {
            return question;
        }
        public void setQuestion(String question) {
            this.question = question;
        }
        public String getbdd_enonce() {
            return bdd_enonce;
        }
        public void setbdd_enonce(String array) {
            this.bdd_enonce = bdd_enonce;
        }
        public Integer getExoNb() {
            return exoNb;
        }
        public void setExoNb(Integer exoNb) {
            this.exoNb = exoNb;
        }
        public Integer getExoLvl() {
            return exoLvl;
        }
        public void setExoLvl(Integer exoLvl) {
            this.exoLvl = exoLvl;
        }


    }



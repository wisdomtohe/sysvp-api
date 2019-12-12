package models;

import io.ebean.Finder;

import javax.persistence.Entity;

@Entity
public class Categorie extends BaseModel{

    private String nomCategorie;

    public static Finder<Long, Categorie> find = new Finder<>(Categorie.class);

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
}

package models;

import io.ebean.Finder;

import javax.persistence.Entity;

@Entity
public class Agent extends BaseModel {
    private String nom;
    private String prenom;

    public static Finder<Long, Agent> find = new Finder<>(Agent.class);


}

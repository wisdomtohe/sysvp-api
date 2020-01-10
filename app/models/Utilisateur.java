package models;

import io.ebean.Finder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Utilisateur extends BaseModel {
    @Column(name = "userName")
    private String login;
    @Column(name = "userPassword")
     private String  password;

    public static Finder<Long, Utilisateur> find = new Finder<>(Utilisateur.class);

    public static Boolean checkIfAlreadyExists(String login){
        Utilisateur user = null;
        try {
            user = find.query()
                    .where()
                    .eq("login", login)
                    .findOne();
        } catch (Exception e){

        }
        return  user != null;
    }

    public static Utilisateur findUser(String login, String password){
        Utilisateur user = null;
        try {
            user = find.query()
                    .where()
                    .eq("login", login)
                    .eq("password", password)
                    .findOne();
        } catch (Exception e){

        }
        return user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

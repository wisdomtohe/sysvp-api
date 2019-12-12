package models;

import io.ebean.Finder;

import java.util.List;

public class Boutique extends BaseModel{
    private String nomBoutique, telBoutique, adresseBoutique;
    public static Finder<Long, Boutique> find = new Finder<>(Boutique.class);
    public String getNomBoutique() {
        return nomBoutique;
    }

    public void setNomBoutique(String nomBoutique) {
        this.nomBoutique = nomBoutique;
    }

    public String getTelBoutique() {
        return telBoutique;
    }

    public void setTelBoutique(String telBoutique) {
        this.telBoutique = telBoutique;
    }

    public String getAdresseBoutique() {
        return adresseBoutique;
    }

    public void setAdresseBoutique(String adresseBoutique) {
        this.adresseBoutique = adresseBoutique;
    }
    public static List<Boutique> listeBoutiquesParVeneur(Long id_vendeur){
        List<Boutique> listeBoutiques = find.nativeSql("SELECT b.* from Boutique b, Agent a, Suivi s" +
	"WHERE b.id = s.boutique_id and a.id = s.agent_id and a.id = "+id_vendeur).findList();

    return  listeBoutiques;
    }
}

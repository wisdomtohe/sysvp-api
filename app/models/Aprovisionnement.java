package models;

import io.ebean.*;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

public class Aprovisionnement extends BaseModel {
    private int quantite_stock;
    private Date date_stock;

    @ManyToOne
    private Agent agent;
    @ManyToOne
    private Produit produit;
    @ManyToOne
    private Boutique boutique;

    public int getQuantite_stock() {
        return quantite_stock;
    }

    public void setQuantite_stock(int quantite_stock) {
        this.quantite_stock = quantite_stock;
    }

    public Date getDate_stock() {
        return date_stock;
    }

    public void setDate_stock(Date date_stock) {
        this.date_stock = date_stock;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public static Finder<Long, Aprovisionnement> find = new Finder<>(Aprovisionnement.class);

    public static List<Aprovisionnement> produitsVendusParUnAgent(Long id_agent, Long id_boutique, Date debut, Date fin){
        String req = "SELECT p.* FROM Aprovisionnement ap, Agent a, Boutique b" +
                "WHERE ap.agent_id = :agentId";
        if(id_boutique != null ){
            req += " AND ap.boutique_id = :boutiqueId";
        }
        if (debut != null && fin != null) {
            req += " AND ap.date_stock BETWEEN :debut AND :fin";
        }
        RawSql rawSql = RawSqlBuilder.parse(req).create();

        Query<Aprovisionnement> reqAprovisionnement = Ebean.find(Aprovisionnement.class);
        reqAprovisionnement.setRawSql(rawSql).setParameter("agentId", id_agent);
                if(id_boutique != null){
                    reqAprovisionnement.setParameter("boutiqueId", id_boutique);
                }
                if (debut != null && fin != null) {
                reqAprovisionnement.setParameter("debut", debut)
                .setParameter("fin", fin);

                }
        List<Aprovisionnement> lesAprovisionnements = reqAprovisionnement.findList();
        return  lesAprovisionnements;
    }
}

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Aprovisionnement;
import models.Categorie;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CategorieController extends Controller {

    public Result getAllCatgorie(){

        List<Categorie> list = Categorie.find.all();
        return ok(Json.toJson(list));
    }

    public Result getById(Long id){
        Categorie categorie = Categorie.find.byId(id);
        if(categorie==null)
            return notFound("Aucun enregistrement trouvé");
        return  ok(Json.toJson(categorie));
    }

    public Result createCateg(){
        JsonNode json = request().body().asJson();

        Categorie categorie = Json.fromJson(json,Categorie.class);

        categorie.save();

        return  created(Json.toJson(categorie));
    }
    public Result listeProduits(){
        Date debut= null, fin= null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        JsonNode json = request().body().asJson();
        JsonNode idAgentNode = json.findPath("idAgent");
        JsonNode idBoutiqueNode = json.findPath("idBoutique");
        JsonNode debutNode = json.findPath("debut");
        JsonNode finNode = json.findPath("fin");
        Long idAgent = idAgentNode.asLong();
        Long idBoutique = idBoutiqueNode.asLong();
        try {
           debut = sdf.parse(debutNode.asText());
           fin = sdf.parse(finNode.asText());
        } catch (Exception e){}
        List<Aprovisionnement> lesAprovisionnements = Aprovisionnement.produitsVendusParUnAgent(idAgent,idBoutique,debut, fin);
        return ok(Json.toJson(lesAprovisionnements));
    }
}

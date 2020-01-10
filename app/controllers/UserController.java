package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Utilisateur;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Security;

public class UserController extends Controller {
    public Result createUser(){
        JsonNode body = request().body().asJson();
        JsonNode loginNode = body.findPath("loginKey");
        JsonNode passwordNode = body.findPath("passwordKey");

        if(loginNode.isMissingNode() || passwordNode.isMissingNode()){
            ObjectNode error = Json.newObject();
            error.put("errormsg", "Parametres manquants... veuillez revoir le formulaire d'enregistrement");
            badRequest(error);
        }
        if (Utilisateur.checkIfAlreadyExists(loginNode.asText())){
            ObjectNode error = Json.newObject();
            error.put("errormsg", "login deja utilise");
            forbidden(error);
        }
        Utilisateur newUser = new Utilisateur();
        newUser.setLogin(loginNode.asText());
        newUser.setPassword(Security.md5Hash(passwordNode.asText()));
        newUser.save();

        return created(Json.toJson(newUser));

    }

    public Result login(){
        JsonNode body = request().body().asJson();
        JsonNode loginNode = body.findPath("loginKey");
        JsonNode passwordNode = body.findPath("passwordKey");

        if(loginNode.isMissingNode() || passwordNode.isMissingNode()) {
            ObjectNode error = Json.newObject();
            error.put("errormsg", "Parametres manquants... veuillez revoir le formulaire d'enregistrement");
            badRequest(error);
        }
        String login= loginNode.asText();
        String password = passwordNode.asText();

        Utilisateur user = Utilisateur.findUser(login, Security.md5Hash(password));

        if (user == null){
            ObjectNode error = Json.newObject();
            error.put("errormsg", "login ou password incorrects");
            notFound(error);
        }
        return ok(Json.toJson(user));
    }
}

package com.revature;

import com.revature.DAOs.OwnerDAO;
import com.revature.controllers.CardController;
import com.revature.controllers.OwnerController;
import com.revature.models.Owner;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        //Typical Javalin setup Syntax
        var app = Javalin.create().start(7000);

        //Very basic callable resource just for fun
        //NOTE: we sent a response from Launcher here, but Responses will really be in the Controllers
        app.get("/", ctx -> ctx.result("Welcome to The MTG Card Inventory System!"));

        //Instantiate Controllers so we can access the Handlers
        CardController cc = new CardController();
        OwnerController oc = new OwnerController();

        // Gets all cards from db
        app.get("/cards", cc.getAllCardsHandler);

        // Inserts card into db
        app.post("/addCard", cc.insertCardHandler);

        // Get card by db
        app.post("/getCard/{id}", cc.getCardHandler);

        // Get card by db
        app.get("/getOwnersCards/{id}", oc.getUsersCardsHandler);

        // Deletes card from db
        app.delete("/deleteCard/{id}", cc.deleteCardHandler);

        // Gets all cards from db
        app.get("/owners", oc.getAllUsersHandler);

        // Adds an owner to db
        app.post("/addOwner", oc.insertOwnerHandler);

        // Delete Owner from db
        app.delete("/deleteOwner/{id}", oc.deleteUserHandler);

        // Delete Owner from db
        app.get("/stats/{id}", oc.getUsersStatsHandler);

        // Trade Card
        app.patch("/trade/{card_id}/{user_id}", cc.tradeHandler);
    }
}
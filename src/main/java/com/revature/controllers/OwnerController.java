package com.revature.controllers;

import com.revature.models.Card;
import com.revature.models.Owner;
import com.revature.DAOs.OwnerDAO;
import io.javalin.http.Handler;
import java.util.ArrayList;

public class OwnerController {
    OwnerDAO oDao = new OwnerDAO();

    public Handler insertOwnerHandler = ctx -> {

        Owner newOwner = ctx.bodyAsClass(Owner.class);
        oDao.addOwner(newOwner);

        ctx.result("Successfuly added Owner!");
        ctx.status(201);

    };

    /*
    public Handler getAvgManaHandler = ctx -> {

         = oDao.getCard(Integer.parseInt(ctx.body));

        ctx.json(card);
        ctx.status(200); //OK

    };
    */

    public Handler getAllUsersHandler = ctx -> {

        //Populate an ArrayList of Employee objects from the DAO!
        ArrayList<Owner> employees = oDao.getAllUsers();

        //SOLUTION: We can use the ctx.json() method to convert this ArrayList to JSON
        //Note: This also returns the object to the client once the code block completes. Convenient!
        ctx.json(employees);

        //We can also set the status code ctx.status()
        ctx.status(200);

    };

    public Handler getUsersCardsHandler = ctx -> {

        int id = Integer.parseInt(ctx.pathParam("id"));

        //Populate an ArrayList of Employee objects from the DAO!
        ArrayList<Card> cardList = oDao.getUsersCards(id);
        System.out.println();
        ctx.json(cardList);
        ctx.status(200);

    };

    public Handler getUsersStatsHandler = ctx -> {

        int id = Integer.parseInt(ctx.pathParam("id"));

        ctx.result(oDao.getStats(id));
        ctx.status(200);

    };
    public Handler deleteUserHandler = ctx -> {

        int id = Integer.parseInt(ctx.pathParam("id"));

        //Populate an ArrayList of Employee objects from the DAO!
        oDao.deleteUser(id);

        ctx.result("User Deleted");
        ctx.status(200);

    };

}

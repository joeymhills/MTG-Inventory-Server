package com.revature.controllers;

import com.revature.models.Card;
import com.revature.DAOs.CardDAO;
import io.javalin.http.Handler;
import java.util.ArrayList;

public class CardController {
    CardDAO cDao = new CardDAO();

    public Handler insertCardHandler = ctx -> {

        Card newCard = ctx.bodyAsClass(Card.class);
        cDao.addCard(newCard);

        ctx.result("Successfuly added card!");
        ctx.status(201);

    };

    public Handler getCardHandler = ctx -> {

        int card_id = Integer.parseInt(ctx.pathParam("id"));
        Card card = cDao.getCard(card_id);

        ctx.json(card);
        ctx.status(200); //OK

    };
    public Handler deleteCardHandler = ctx -> {

        int card_id = Integer.parseInt(ctx.pathParam("id"));
        cDao.deleteCard(card_id);

        ctx.result("Card deleted.");
        ctx.status(200); //OK

    };

    public Handler tradeHandler = ctx -> {

        int card_id = Integer.parseInt(ctx.pathParam("card_id"));
        int user_id = Integer.parseInt(ctx.pathParam("user_id"));
        cDao.tradeCard(card_id, user_id);

        ctx.result("Card traded.");
        ctx.status(200); //OK

    };
    public Handler getAllCardsHandler = ctx -> {

        //Populate an ArrayList of Employee objects from the DAO!
        ArrayList<Card> cards = cDao.getAllCards();

        //PROBLEM: We can't send plain Java in an HTTP response - we want to use JSON

        //SOLUTION: We can use the ctx.json() method to convert this ArrayList to JSON
        //Note: This also returns the object to the client once the code block completes. Convenient!
        ctx.json(cards);

        //We can also set the status code ctx.status()
        ctx.status(200);
    };

}

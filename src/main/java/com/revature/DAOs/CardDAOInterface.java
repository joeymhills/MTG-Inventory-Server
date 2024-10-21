package com.revature.DAOs;

import com.revature.models.Card;
import com.revature.models.Owner;

import java.util.ArrayList;

public interface CardDAOInterface {
   public void addCard(Card card);
   public Card getCard(int id);
   public ArrayList<Card> getAllCards();
   public void tradeCard(int card_id, int user_id);
   public void deleteCard(int id);
}

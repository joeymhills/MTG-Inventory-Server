package com.revature.DAOs;

import com.revature.models.Card;
import com.revature.models.Owner;

import java.util.ArrayList;

public interface OwnerDAOInterface {
    public Owner addOwner(Owner owner);
    public ArrayList<Owner> getAllUsers();
    public ArrayList<Card> getUsersCards(int id);
    public void deleteUser(int id);
    public String getStats(int id);
}

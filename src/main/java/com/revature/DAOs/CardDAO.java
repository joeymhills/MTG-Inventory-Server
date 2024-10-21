package com.revature.DAOs;

import com.revature.models.Card;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CardDAO implements CardDAOInterface {

    @Override
    public void addCard(Card card) {
        try(Connection conn = ConnectionUtil.getConnection()) {

            // Inserts the user into the table
            String sql = "INSERT INTO public.cards (name,mana,power,toughness,owner) values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, card.getName());
            ps.setInt(2, card.getMana());
            ps.setInt(3, card.getPower());
            ps.setInt(4, card.getToughness());
            ps.setInt(5, card.getOwner_fk());
            ps.executeUpdate();


            // This code gets us the uuid for the owner we just inserted into the db
            sql = "SELECT lastval() as last_owner_id;";
            ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                int owner_id = result.getInt("last_owner_id");

            } else {
                throw new RuntimeException("could not get owner id from newly created owner.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Card getCard(int id) {
        return null;
    }

    @Override
    public ArrayList<Card> getAllCards() {

        ArrayList<Card> cardList = new ArrayList<Card>();
        try(Connection conn = ConnectionUtil.getConnection()) {

            // Inserts the user into the table
            String sql = "SELECT * FROM public.cards";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                Card card = new Card(result.getInt("id"), result.getString("name"), result.getInt("mana"), result.getInt("power"), result.getInt("toughness"), result.getInt("owner"));
                cardList.add(card);
                System.out.println(card.getPower());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return cardList;
    }

    @Override
    public void tradeCard(int cardId, int userId) {
        try(Connection conn = ConnectionUtil.getConnection()) {

            // Inserts the user into the table
            String sql = "UPDATE public.cards SET owner = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, cardId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteCard(int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {

            // Inserts the user into the table
            String sql = "DELETE FROM public.cards where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

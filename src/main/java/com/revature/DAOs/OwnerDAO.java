package com.revature.DAOs;

import com.revature.models.Card;
import com.revature.models.Owner;
import com.revature.DAOs.OwnerDAOInterface;
import com.revature.utils.ConnectionUtil;

import java.text.DecimalFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

public class OwnerDAO implements OwnerDAOInterface{
    @Override
    public Owner addOwner(Owner owner) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            // Inserts the user into the table
            String sql = "INSERT INTO public.owners (name) values (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, owner.getName());
            ps.executeUpdate();


            // This code gets us the uuid for the owner we just inserted into the db
            sql = "SELECT lastval() as last_owner_id;";
            ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                int owner_id = result.getInt("last_owner_id");
                System.out.println(owner_id);
            } else {
                throw new RuntimeException("could not get owner id from newly created owner.");
            }

            return owner;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return owner;
    }

    @Override
    public ArrayList<Card> getUsersCards(int id) {
        ArrayList<Card> cardList = new ArrayList<Card>();
        try(Connection conn = ConnectionUtil.getConnection()) {

            // Grabs cards by user
            String sql = "SELECT * FROM public.cards WHERE owner = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                Card card = new Card(result.getInt("id"), result.getString("name"), result.getInt("mana"), result.getInt("power"), result.getInt("toughness"), result.getInt("owner"));
                cardList.add(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return cardList;
    }

    @Override
    public ArrayList<Owner> getAllUsers() {
        ArrayList<Owner> ownerList = new ArrayList<Owner>();
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM public.owners";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                Owner owner = new Owner(result.getInt("id"), result.getString("name"));
                ownerList.add(owner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return ownerList;
    }
    @Override
    public void deleteUser(int id) {
        ArrayList<Owner> ownerList = new ArrayList<Owner>();
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM public.owners WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public String getStats(int id) {
        String name = "";
        int card_count = 0;
        double avg_mana = 0.0;
        double avg_power = 0.0;
        double avg_toughness = 0.0;

        try(Connection conn = ConnectionUtil.getConnection()) {

            // Inserts the user into the table
            String sql = "SELECT count(*) as card_count, avg(mana) as avg_mana, avg(power) as avg_power, avg(toughness) as avg_toughness FROM public.cards WHERE owner = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                card_count = result.getInt("card_count");
                avg_mana = result.getDouble("avg_mana");
                avg_power = result.getDouble("avg_power");
                avg_toughness = result.getDouble("avg_toughness");
            } else {
                throw new RuntimeException("could not get owner id from newly created owner.");
            }

            String sql2 = "SELECT * FROM public.owners WHERE id = ?";
            PreparedStatement prepstate = conn.prepareStatement(sql2);
            prepstate.setInt(1, id);
            ResultSet nameResult = prepstate.executeQuery();

            if (nameResult.next()) {
                name = nameResult.getString("name");
            } else {
                throw new RuntimeException("could not get owner id from newly created owner.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return name + "'s Stats \n" + "Card count: " + df.format(card_count) + "\n" + "Average mana per card: " + df.format(avg_mana) + "\n" + "Average power per card: " + df.format(avg_power) + "\n" + "Average toughness per card: " + df.format(avg_toughness);
    }

}

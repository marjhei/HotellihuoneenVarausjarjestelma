/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varausjarjestelma;


import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Component;



@Component

public class AsiakasDao implements Dao<Asiakas, Integer> {

    

    @Autowired

    JdbcTemplate jdbcTemplate;

    

    @Autowired

    VarausDao varausDao;

    



    @Override

    public Integer create(Asiakas asiakas) throws SQLException {   

        KeyHolder keyHolder = new GeneratedKeyHolder();

        

        jdbcTemplate.update(connection -> {

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Asiakas (nimi, puhnro, email) VALUES (?, ?, ?)",

                    Statement.RETURN_GENERATED_KEYS);

                    stmt.setString(1, asiakas.getNimi());

                    stmt.setString(2, asiakas.getNumero());

                    stmt.setString(3, asiakas.getSahkoposti());

                    return stmt;

        }, keyHolder);

        

        int id = (int) keyHolder.getKey();

           return id;

    }



    @Override

    public Asiakas read(Integer key) throws SQLException {

        List<Asiakas> a = jdbcTemplate.query("SELECT id, nimi, email, puhnro FROM Asiakas WHERE id = ?", (rs, rowNum) -> 

                new Asiakas(rs.getInt("id"), rs.getString("nimi"), rs.getString("puhnro"), rs.getString("email")), key);

        

        if (a.isEmpty()) {

            return null;

        }

        

        return a.get(0);

    }



    @Override

    public Asiakas update(Asiakas asiakas) throws SQLException {

        jdbcTemplate.update("UPDATE Asiakas SET nimi = ?, puhnro = ?, email = ? WHERE id = ?",

                asiakas.getNimi(), asiakas.getNumero(), asiakas.getSahkoposti(), asiakas.getId());

        return asiakas;

    }



    @Override

    public void delete(Integer key) throws SQLException {

        jdbcTemplate.update("DELETE FROM Asiakas WHERE id = ?",

                key);

    }



   

      @Override
      

    public List<Asiakas> list() throws SQLException {

        List<Asiakas> asiakkaat = jdbcTemplate.query("SELECT id, nimi, puhnro, email FROM Asiakas;", (rs, rowNum) -> 

                new Asiakas(rs.getInt("id"), rs.getString("nimi"), rs.getString("puhnro"), rs.getString("email")));

        

        List<Varaus> varaukset = varausDao.list();

        

        for (Asiakas a : asiakkaat) {

            for (Varaus v : varaukset) {

                if (v.getAsiakas().getId().equals(a.getId())) {

                    a.varaukset.add(v);

                }

            }

        }

        

        return asiakkaat;

    }

}

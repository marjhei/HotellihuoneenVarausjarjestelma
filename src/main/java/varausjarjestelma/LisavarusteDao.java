/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varausjarjestelma;



import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Component;



@Component

public class LisavarusteDao implements Dao<Lisavaruste, Integer> {



    @Autowired

    JdbcTemplate jdbcTemplate;

    

    // Samalla kun lisätään uuden lisävarusteen tiedot tietokantataulukkoon (kyseinen lisävaruste on tässä metodin parametrina),

    // niin metodi palauttaa automaattisesti luodun (primary key) indeksin, jota voidaan sitten käyttää tarpeen mukaan.

    @Override

    public Integer create(Lisavaruste lisavaruste) throws SQLException {

        KeyHolder keyHolder = new GeneratedKeyHolder();



        jdbcTemplate.update(connection -> {

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Lisavaruste (varustetyyppi, lisavaruste_lkm) VALUES (?, ?)",

                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, lisavaruste.getNimi());

            stmt.setInt(2, lisavaruste.getVaraustenMaara());

            return stmt;

        }, keyHolder);



        int id = (int) keyHolder.getKey();

        return id;

    }



    @Override

    public Lisavaruste read(Integer key) throws SQLException {

        List<Lisavaruste> lisavarusteet = jdbcTemplate.query("SELECT * FROM Lisavaruste WHERE id = ?", (rs, rowNum)

                -> new Lisavaruste(rs.getInt("id"), rs.getString("varustetyyppi"), rs.getInt("lisavaruste_lkm")), key);



        if (lisavarusteet.isEmpty()) {

            return null;

        }



        return lisavarusteet.get(0);

    }



    @Override

    public Lisavaruste update(Lisavaruste lisavaruste) throws SQLException {

        jdbcTemplate.update("UPDATE Lisavaruste SET varustetyyppi = ?, lisavaruste_lkm = ? WHERE id = ?",

                lisavaruste.getNimi(), lisavaruste.getVaraustenMaara(), lisavaruste.getId());

        return lisavaruste;

    }



    @Override

    public void delete(Integer key) throws SQLException {

        jdbcTemplate.update("DELETE FROM Lisavaruste WHERE id = ?", key);

    }



    @Override

    public List<Lisavaruste> list() throws SQLException {

        return jdbcTemplate.query("SELECT id, varustetyyppi, lisavaruste_lkm FROM Lisavaruste;", (rs, rowNum) -> new Lisavaruste(rs.getInt("id"), rs.getString("varustetyyppi"), rs.getInt("lisavaruste_lkm")));

    }



}
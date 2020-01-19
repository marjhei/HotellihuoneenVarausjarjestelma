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

public class VarausDao implements Dao<Varaus, Integer> {



    @Autowired

    JdbcTemplate jdbcTemplate;

    

    @Autowired

    HuoneVarausDao huonevarausDao;

    

    @Autowired

    VarausLisavarusteDao varauslisavarusteDao;

    

    @Autowired

    AsiakasDao asiakasDao;




    @Override

     public Integer create(Varaus varaus) throws SQLException {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        

        jdbcTemplate.update(connection -> {

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Varaus (alkpva, loppupva, asiakas_id, huonenumero, kesto, yhthinta) VALUES (?, ?, ?, ?, ?, ?)",

                    Statement.RETURN_GENERATED_KEYS);

                    stmt.setDate(1, varaus.getAlkupvm());

                    stmt.setDate(2, varaus.getLoppupvm());

                    stmt.setInt(3, varaus.getAsiakas().getId());
                    
                    stmt.setInt(4, varaus.getKesto());
                    
                    stmt.setInt(5, varaus.getYhthinta());
                    
                    stmt.setInt(6, varaus.getHuonenumero());

                    return stmt;

        }, keyHolder);

        

        int id = (int) keyHolder.getKey();

        return id;

    }



    @Override

    public Varaus read(Integer key) throws SQLException {

        

        List<Varaus> varaukset = jdbcTemplate.query("SELECT id, alkpva, loppupva, asiakas_id, huonenumero, kesto, yhthinta FROM Varaus WHERE id = ?", (rs, rowNum) -> 

                new Varaus(rs.getInt("id"), rs.getDate("alkpva"), rs.getDate("loppupva"), asiakasDao.read(rs.getInt("asiakas_id")), rs.getInt("huonenumero"), rs.getInt("kesto"), rs.getInt("yhthinta")), key);

        

        if (varaukset.isEmpty()) {

            return null;

        }

        

        return varaukset.get(0);

    }



    @Override

    public Varaus update(Varaus varaus) throws SQLException {

        jdbcTemplate.update("UPDATE Varaus SET alkpva = ?, loppupva = ?, asiakas_id = ?, huonenumero = ?, kesto = ?, yhthinta = ? WHERE id = ?",

                varaus.getAlkupvm(), varaus.getLoppupvm(), varaus.getAsiakas().getId(), varaus.getId(), varaus.getKesto(), varaus.getYhthinta());    

        return varaus;

    }



    @Override

    public void delete(Integer key) throws SQLException {

        jdbcTemplate.update("DELETE FROM Varaus WHERE id = ?",

                key);

    }



    @Override

    public List<Varaus> list() throws SQLException {

        

        List<Varaus> varauksetIlmanHuoneitaJaVarusteita = jdbcTemplate.query("SELECT id, alkpva, loppupva, asiakas_id, huonenumero, kesto, yhthinta  FROM Varaus", 

                (rs, rowNum) -> new Varaus(rs.getInt("id"), rs.getDate("alkpva"), rs.getDate("loppupva"), asiakasDao.read(rs.getInt("asiakas_id")), rs.getInt("huonenumero"), rs.getInt("kesto"), rs.getInt("yhthinta")));

        

        List<HuoneVaraus> huonevaraukset = huonevarausDao.list();

        

        List<VarausLisavaruste> varauslisavarusteet = varauslisavarusteDao.list();

        

        List<Varaus> varaukset = varauksetIlmanHuoneitaJaVarusteita;

        

        for (Varaus v : varaukset) {

            for (HuoneVaraus hv : huonevaraukset) {

                int a = hv.getVaraus().getId();

                int b = v.getId();

                if (a == b) {

                    List<HuoneVaraus> huoneet = v.getHuoneVaraukset();

                    huoneet.add(hv);

                    v.setHuoneVaraukset(huoneet);

                }

            }

            

            for (VarausLisavaruste vlV : varauslisavarusteet) {

                int a = vlV.getVaraus().getId();

                int b = v.getId();

                if (a == b) {

                    List<VarausLisavaruste> lisavarusteet = v.getVarausLisavarusteet();

                    lisavarusteet.add(vlV);

                    v.setVarausLisavarusteet(lisavarusteet);

                }

            }

           }

        

        return varaukset;

    }

    

}
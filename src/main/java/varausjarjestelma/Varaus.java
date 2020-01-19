/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varausjarjestelma;



import java.sql.Date;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;



public class Varaus implements Comparable<Varaus> {



    Integer id;
    
    Asiakas asiakas;

    Date alkpva;

    Date loppupva;
    
    Integer kesto;
    
    Integer yhthinta;
    
    Integer huonenumero;
    
    List<HuoneVaraus> huonevaraukset;

    List<VarausLisavaruste> varauslisavarusteet;



    public Varaus(Integer id, Date alkpva, Date loppupva, Asiakas asiakas, Integer huonenumero, Integer kesto, Integer yhthinta) {

        this.id = id;

        this.alkpva = alkpva;

        this.loppupva = loppupva;

        this.asiakas = asiakas;
        
        this.huonenumero = huonenumero;
        
        this.kesto = kesto;
        
        this.yhthinta = yhthinta;

        this.huonevaraukset = new ArrayList<>();

        this.varauslisavarusteet = new ArrayList<>();

    }



    public Varaus(Date alku, Date loppu, Asiakas asiakas, Integer huonenumero, Integer kesto, Integer yhthinta) {

        this.alkpva = alkpva;

        this.loppupva = loppupva;

        this.asiakas = asiakas;
        
        this.huonenumero = huonenumero;
        
        this.kesto = kesto;
        
        this.yhthinta = yhthinta;

        this.huonevaraukset = new ArrayList<>();

        this.varauslisavarusteet = new ArrayList<>();

    }



    public Integer getId() {

        return this.id;

    }



    public void setId(Integer id) {

        this.id = id;

    }



    public Date getAlkupvm() {

        return this.alkpva;

    }



    public void setAlkupvm(Date alkpva) {

        this.alkpva = alkpva;

    }



    public Date getLoppupvm() {

        return this.loppupva;

    }



    public void setLoppupvm(Date loppupva) {

        this.loppupva = loppupva;

    }



    public Asiakas getAsiakas() {

        return this.asiakas;

    }



    public void setAsiakas(Asiakas asiakas) {

        this.asiakas = asiakas;

    }
    
    public Integer getHuonenumero() {

        return this.huonenumero;

    }



    public void setHuonenumero(Integer huonenumero) {

        this.huonenumero = huonenumero;

    }
    
   

    
    public Integer getKesto() {

        return this.kesto;

    }



    public void setKesto(Integer Kesto) {

        this.kesto = kesto;

    }
    
      public Integer getYhthinta() {

        return this.yhthinta;

    }



    public void setYhthinta(Integer yhthinta) {

        this.yhthinta = yhthinta;

    }
    
    



    public List<HuoneVaraus> getHuoneVaraukset() {

        return this.huonevaraukset;

    }



    public void setHuoneVaraukset(List<HuoneVaraus> huonevaraukset) {

        this.huonevaraukset = huonevaraukset;

    }



    public List<VarausLisavaruste> getVarausLisavarusteet() {

        return this.varauslisavarusteet;

    }



    public void setVarausLisavarusteet(List<VarausLisavaruste> vlV) {

        this.varauslisavarusteet = vlV;

    }



    public String toString() {

        int paivienMaara = (int) ((this.loppupva.getTime() - this.alkpva.getTime()) / 1000 / 60 / 60 / 24);

        String paiva = " päivää";

        String huoneTeksti = " huonetta.";



        if (paivienMaara == 1) {

            paiva = " päivä";

        }



        if (this.huonevaraukset.size() == 1) {

            huoneTeksti = " huone.";

        }



        int paivasumma = 0;

        String huoneet = "";

        for (HuoneVaraus hv : this.huonevaraukset) {

            huoneet += "\n" + "\t" + hv.getHuone();

            paivasumma += hv.getHuone().getPaivahinta();

        }



        return this.asiakas.getNimi() + ", " + this.asiakas.getSahkoposti() + ", "

                + this.alkpva + ", " + this.loppupva + ", " + paivienMaara

                + paiva + ", " + this.varauslisavarusteet.size() + " lisävarustetta, " + this.huonevaraukset.size()

                + huoneTeksti + " Huoneet:" + huoneet + "\n" + "\t" + "Yhteensä: " + (paivasumma * paivienMaara) + " euroa";

    }



    @Override

    public int compareTo(Varaus o) {

        if (this.getAlkupvm().before(o.getAlkupvm())) {

            return -1;

        } else if (this.getAlkupvm().after(o.getAlkupvm())) {

            return 1;

        } else {

            return 0;

        }

    }



}
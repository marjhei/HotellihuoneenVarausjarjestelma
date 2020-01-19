/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package varausjarjestelma;



import java.util.ArrayList;

import java.util.List;



 public class Lisavaruste implements Comparable<Lisavaruste> {



    Integer id;

    String varustetyyppi;

    Integer lisavaruste_lkm;

    List<VarausLisavaruste> lisavarusteet;



    public Lisavaruste(Integer id, String varustetyyppi, Integer lisavaruste_lkm) {

        this.id = id;

        this.varustetyyppi = varustetyyppi;

        this.lisavaruste_lkm = lisavaruste_lkm;

        this.lisavarusteet = new ArrayList<>();

    }



    public Lisavaruste(String nimi) {

        this.varustetyyppi = varustetyyppi;

        this.lisavaruste_lkm = 1;

        this.lisavarusteet = new ArrayList<>();

    }



    public Integer getId() {

        return this.id;

    }



    public void setId(Integer id) {

        this.id = id;

    }



    public String getNimi() {

        return this.varustetyyppi;

    }



    public void setNimi(String varustetyyppi) {

        this.varustetyyppi = varustetyyppi;

    }



    public Integer getVaraustenMaara() {

        return this.lisavaruste_lkm;

    }



    public void setVaraustenMaara(Integer lisavaruste_lkm) {

        this.lisavaruste_lkm = lisavaruste_lkm;

    }



    public List<VarausLisavaruste> getLisavarusteet() {

        return this.lisavarusteet;

    }



    public void setLisavarusteet(List<VarausLisavaruste> l) {

        this.lisavarusteet = l;

    }

    

    @Override

    public String toString() {

        String varausta = " varausta";

        if (this.lisavaruste_lkm == 1) {

            varausta = " varaus";

        }

        return this.varustetyyppi + ", " + this.lisavaruste_lkm + varausta;

    }

    

    @Override

    public int compareTo(Lisavaruste o) {

        if (this.getVaraustenMaara() > o.getVaraustenMaara()) {

            return -1;

        } else if (this.getVaraustenMaara() < o.getVaraustenMaara()) {

            return 1;

        } else {

            return 0;

        }

    }

}
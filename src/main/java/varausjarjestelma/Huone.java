/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varausjarjestelma;


import java.util.ArrayList;

import java.util.List;

import java.util.Objects;



public class Huone implements Comparable <Huone> {

    Integer id;

    Integer huonenumero;

    Integer paivahinta;

    String huonetyyppi;

    List<HuoneVaraus> huonevaraukset;

     

    public Huone(Integer id, Integer huonenumero, Integer paivahinta, String huonetyyppi) {

        this.id = id;

        this.huonenumero = huonenumero;

        this.paivahinta = paivahinta;

        this.huonetyyppi = huonetyyppi;

        this.huonevaraukset = new ArrayList<>();

    }

    

    public Huone(Integer huonenumero, Integer paivahinta, String huonetyyppi) {

        this.huonenumero = huonenumero;

        this.paivahinta = paivahinta;

        this.huonetyyppi = huonetyyppi;

        this.huonevaraukset = new ArrayList<>();

    }

    

    public Integer getId() {

        return this.id;

    }

    

    public void setId(Integer id) {

        this.id = id;

    }

    

    public Integer getNumero() {

        return this.huonenumero;

    }

    

    public void setNumero(Integer huonenumero) {

        this.huonenumero = huonenumero;

    }

    

    public Integer getPaivahinta() {

        return this.paivahinta;

    }

    

    public void setPaivahinta(Integer paivahinta) {

        this.paivahinta = paivahinta;

    }

    

    public String getTyyppi() {

        return this.huonetyyppi;

    }

    

    public void setTyyppi(String huonetyyppi) {

        this.huonetyyppi = huonetyyppi;

    }

    

    public List<HuoneVaraus> getHuoneVaraukset() {

        return this.huonevaraukset;

    }

    

    public void setHuoneVaraukset(List<HuoneVaraus> huonevaraukset) {

        this.huonevaraukset = huonevaraukset;

    }

    

    @Override

    public String toString() {

        String apuNumero = "";

        if (this.huonenumero < 10) {

            apuNumero = "00" + this.huonenumero;

        } else if (this.huonenumero < 100) {

            apuNumero = "0" + this.huonenumero;

        } else {

            apuNumero = "" + this.huonenumero;

        }

        return this.huonetyyppi + ", "  + apuNumero + ", " + this.paivahinta + " euroa";

    }



    @Override

    public int hashCode() {

        int hash = 5;

        hash = 11 * hash + Objects.hashCode(this.id);

        hash = 11 * hash + Objects.hashCode(this.huonenumero);

        return hash;

    }



    @Override

    public boolean equals(Object obj) {

        if (this == obj) {

            return true;

        }

        if (obj == null) {

            return false;

        }

        if (getClass() != obj.getClass()) {

            return false;

        }

        final Huone other = (Huone) obj;

        if (!Objects.equals(this.huonenumero, other.huonenumero)) {

            return false;

        } else if (!Objects.equals(this.id, other.id)) {

            return false;

        }

        

        return true;

    }



  

    @Override

    public int compareTo(Huone o) {

        if (this.getPaivahinta() < o.getPaivahinta()) {

            return 1;

        } else if (this.getPaivahinta() > o.getPaivahinta()) {

            return -1;

        } else {

            return 0;

        }

    }

   

    

}

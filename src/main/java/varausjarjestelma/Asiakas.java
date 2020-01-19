/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varausjarjestelma;



import java.util.ArrayList;

import java.util.List;



public class Asiakas implements Comparable<Asiakas> {



    Integer id;

    String nimi;

    String puhnro;

    String email;

    List<Varaus> varaukset;



    public Asiakas(Integer id, String nimi, String puhnro, String email) {

        this.id = id;

        this.nimi = nimi;

        this.puhnro = puhnro;

        this.email = email;

        this.varaukset = new ArrayList<>();

    }



    public Asiakas(String nimi, String puhnro, String email) {

        this.nimi = nimi;

        this.puhnro = puhnro;

        this.email = email;

    }



    public Integer getId() {

        return this.id;

    }



    public void setId(Integer id) {

        this.id = id;

    }



    public String getNimi() {

        return this.nimi;

    }



    public void setNimi(String nimi) {

        this.nimi = nimi;

    }



    public String getNumero() {

        return this.puhnro;

    }



    public void setPuhelinnumero(String puhnro) {

        this.puhnro = puhnro;

    }



    public String getSahkoposti() {

        return this.email;

    }



    public void setSahkoposti(String email) {

         this.email = email;

    }

    

    

     public Integer varaustenSumma() {

        int varausten_summa = 0;

        for (Varaus v : this.varaukset) {

            int paivasumma = 0;

            int paivienMaara = (int) ((v.loppupva.getTime() - v.alkpva.getTime()) / 1000 / 60 / 60 / 24);

            for (HuoneVaraus hv : v.huonevaraukset) {

                paivasumma += hv.getHuone().getPaivahinta();

            }

            varausten_summa += paivasumma * paivienMaara;

        }

        return varausten_summa;

    }

    

    @Override

    public String toString() {

        return this.nimi + ", " + this.email + ", " + this.puhnro + ", " + this.varaustenSumma() + " euroa";

    }

    

    @Override

    public int compareTo(Asiakas o) {

        int summa_a = this.varaustenSumma();

        int summa_o = o.varaustenSumma();

        

        if (summa_a < summa_o) {

            return 1;

        } else if (summa_a > summa_o) {

            return -1;

        } else {

            return 0;

        }

    }



}

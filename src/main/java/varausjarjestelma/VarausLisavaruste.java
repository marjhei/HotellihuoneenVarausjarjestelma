/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package varausjarjestelma;






public class VarausLisavaruste  {

    Integer id;

    Varaus varaus;

    Lisavaruste lisavaruste;



    public VarausLisavaruste(Integer id, Varaus varaus, Lisavaruste lisavaruste) {

        this.id = id;

        this.varaus = varaus;

        this.lisavaruste = lisavaruste;

    }

    

    public VarausLisavaruste(Varaus v, Lisavaruste l) {

        this.varaus = v;

        this.lisavaruste = l;

    }

    

    public Integer getId() {

        return this.id;

    }

    

    public void setId(Integer id) {

        this.id = id;

    }

    

    public Varaus getVaraus() {

        return this.varaus;

    }

    

    public void setVaraus(Varaus v) {

        this.varaus = v;

    }

    

    public Lisavaruste getLisavaruste() {

        return this.lisavaruste;

    }

    

    public void setLisavaruste(Lisavaruste l) {

        this.lisavaruste = l;

    }

}

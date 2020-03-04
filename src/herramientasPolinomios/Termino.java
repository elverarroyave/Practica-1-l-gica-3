/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientasPolinomios;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Termino {
    
    private int exp;
    private double coef;
    
    public Termino(int exp, double coef) {
        this.exp = exp;
        this.coef = coef;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

}

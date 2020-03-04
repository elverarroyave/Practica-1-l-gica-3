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
public class Nodo {
    
    private Termino t;
    private Nodo liga;
    
    public Nodo(Termino t){
        this.t = t;
        liga = null;
    }

    public Termino getT() {
        return t;
    }

    public void setT(Termino t) {
        this.t = t;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
    
}

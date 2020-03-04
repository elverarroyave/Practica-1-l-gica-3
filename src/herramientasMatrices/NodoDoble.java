/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientasMatrices;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class NodoDoble {
    private final Tripleta t;
    private NodoDoble ligaF;
    private NodoDoble ligaC;
    
    public NodoDoble(Tripleta t){
        this.t = t;
    }
    
    public Tripleta getT(){
        return t;
    }

    public NodoDoble getLigaF() {
        return ligaF;
    }

    public void setLigaF(NodoDoble ligaF) {
        this.ligaF = ligaF;
    }

    public NodoDoble getLigaC() {
        return ligaC;
    }

    public void setLigaC(NodoDoble ligaC) {
        this.ligaC = ligaC;
    }
    
    
}

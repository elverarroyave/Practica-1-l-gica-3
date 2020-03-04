/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientasPolinomios;


/**
 * 
 * @author Elver Arroyave 
 */
public class PolinomioListaSimple {
    private Nodo cab;
    
    /**
     * Constructor construye una lista.
     */
    
    public PolinomioListaSimple(){
        cab = new Nodo(null);
        cab.setLiga(null);
    }
    
    /**
     * 
     * @return Retorna la cabesa de la lista.
     */
    
     public Nodo getCab(){
         return cab;
     }
     
     /**
      * 
      * @param pA Recibe un dato de tipo polinomiol lista simple 
      * @param pB Recibe un dato de tipo polinomio lista simple
      * @return retorna igual mente un dato de tipo polinomio lista siple con el resultado de la suma de los anteriores
      */
    
     public static PolinomioListaSimple sumar(PolinomioListaSimple pA, PolinomioListaSimple pB){
         PolinomioListaSimple pSumar = new PolinomioListaSimple();
         
        pA=pA.Ordenar(pA);
        pB=pB.Ordenar(pB);
         
         Nodo nodoRecorreA = pA.getCab().getLiga();
         Nodo nodoRecorreB = pB.getCab().getLiga();
         Nodo recorreResultado = pSumar.getCab();
         
         while(nodoRecorreA != null && nodoRecorreB != null ){
             // Variables de exponente y coeficiente de terminos
            int eA = nodoRecorreA.getT().getExp();
            double cA = nodoRecorreA.getT().getCoef();
            int eB = nodoRecorreB.getT().getExp();
            double cB = nodoRecorreB.getT().getCoef();
            
            if(eA > eB){
                Termino t = new Termino(eA, cA);
                Nodo n = new Nodo(t);
                recorreResultado.setLiga(n);
                recorreResultado = n;
                nodoRecorreA = nodoRecorreA.getLiga();
            } else if( eA == eB){
                //Si el coeficiente es cero no se lleva polinomio pSumar 
                double posibleCoeficienteC = cA + cB;
                if(posibleCoeficienteC != 0){
                    Termino t = new Termino(eA, posibleCoeficienteC);
                    Nodo n = new Nodo(t);
                    recorreResultado.setLiga(n);
                    recorreResultado = n;
                }
                nodoRecorreA = nodoRecorreA.getLiga();
                nodoRecorreB = nodoRecorreB.getLiga();
                
            } else{
            Termino t = new Termino(eB, cB);
            Nodo n = new Nodo(t);
            recorreResultado.setLiga(n);
            recorreResultado = n;
            
            nodoRecorreB = nodoRecorreB.getLiga();
            }
         }
         
         // Si la lista de terminos del polinomio b finaliza primero
          while (nodoRecorreA!=null) {
            // Variables para exponente y coefiente de lista polinomio a
            int eA = nodoRecorreA.getT().getExp();
            double cA = nodoRecorreA.getT().getCoef();
            // Creo el nuevo termino, nuevo nodo y ligo en c
            Termino t = new Termino(eA, cA);
            Nodo n = new Nodo(t);
            recorreResultado.setLiga(n);
            recorreResultado = n;
            // me sigo moviendo en lista a
            nodoRecorreA = nodoRecorreA.getLiga();
        }
          
         // Si la lista de terminos del polinomio a finalizo primero
        while (nodoRecorreB!=null) {
            // Variables para exponente y coefiente de lista polinomio a
            int eB = nodoRecorreB.getT().getExp();
            double cB = nodoRecorreB.getT().getCoef();
            // Creo el nuevo termino, nuevo nodo y ligo en c
            Termino t = new Termino(eB, cB);
            Nodo n = new Nodo(t);
            recorreResultado.setLiga(n);
            recorreResultado = n;
            // me sigo moviendo en lista b
            nodoRecorreB = nodoRecorreB.getLiga();
        }
        
        return pSumar;
        
      }
     
     /**
      * 
      * @param polA dato de tipo Polinomio lista simple en este caso es el minuendo 
      * @param polB dato de tipo Polinoimo lista simple en este csao es el sustraendo 
      * @return Retorna la diferencian entre los dos polinomios mostrados anterior mente 
      */
     
       public static PolinomioListaSimple restar(PolinomioListaSimple polA, PolinomioListaSimple polB){
           
           polA = polA.Ordenar(polA);
           polB = polB.Ordenar(polB);
           
           //realicemos un metodo para multiplicar el polB por -1
           
           Nodo posB = polB.getCab().getLiga();
           
            while(posB != null){
                
                Termino tr = new Termino(posB.getT().getExp(),posB.getT().getCoef()*-1);
                
                posB.setT(tr);
                       
                posB = posB.getLiga();
            }
         
           PolinomioListaSimple pResta = sumar(polA, polB);
        
        return pResta;
           
       }
     /**
      * 
      * @param polA dato de tipo PLS que es el multiplicando 
      * @param polB dato de tipo PLS que es el multiplicador    
      * @return dato de tipo PLLS que es el producto de los dos mentionados anteriormete, este es de tipo PLS igualmente.
      */
     
     public static PolinomioListaSimple multiplicar(PolinomioListaSimple polA, PolinomioListaSimple polB) {
        // Variables para manipular los terminos
        PolinomioListaSimple polC = new PolinomioListaSimple();
       
        //Elver arroyave 
        polA=polA.Ordenar(polA);
        polB=polB.Ordenar(polB);
        
     
        
        Nodo prA = polA.getCab().getLiga();
        Nodo prB;
        Nodo uC = polC.getCab();
       
        // en estos dos ciclos While se realiza la multiplicación termino a termino
        int exp; double coef;
         while (prA != null) {
             exp=prA.getT().getExp();
             coef=prA.getT().getCoef();
             prB=polB.getCab().getLiga();
             while (prB != null) {
                 int exp1=exp+prB.getT().getExp();
                 double coef1=coef*prB.getT().getCoef();
                 Termino TerProvisional=new Termino(exp1,coef1);
                 Nodo NodProv=new Nodo(TerProvisional);
                 uC.setLiga(NodProv);
                 uC=NodProv;
                 prB=prB.getLiga();    
             
         }
             
             prA=prA.getLiga();
             
         }
         
         /*
         System.out.println("Aqui se muestra el vector sin Factorizar");
         System.out.println( polC.mostrar()) ;
        
         //En esta parte hacemos la suma de los terminos del mismo exponente. 
         //Sin embargo no se elminan los terminos
         */
         
         uC=polC.getCab().getLiga();
         
         double nuevoCoef=0;
         int cont=0;
         while(uC != null){
             
             int exp1=uC.getT().getExp();
            nuevoCoef=uC.getT().getCoef();
            Nodo uCProv=uC.getLiga();
         cont++;
             while(uCProv != null){
                                  
                 int exp2=uCProv.getT().getExp();
                 if(exp1==exp2){
                    nuevoCoef=nuevoCoef+uCProv.getT().getCoef();
                    
                }
                                
               uCProv=uCProv.getLiga();
                 }
            
             Termino t=new Termino(exp1,nuevoCoef);
             Nodo n=new Nodo(t);
             uC.setT(t);
             
             uC=uC.getLiga();
             
         }
         
         //En esta parte se quitan los Terminos ya sumados
         uC=polC.getCab().getLiga();
         int[] exponentes=new int[cont];
         int cont1=0;
         while(cont1<cont){
             exponentes[cont1]=uC.getT().getExp();
             uC=uC.getLiga();
            
             cont1++;
             
             
        }int cont2=0;int cont3=0;
         for(int i=0;i<cont;i++){
            for(int j=i+1;j<cont;j++){
            if(exponentes[i]==exponentes[j]){
                
                exponentes[j]=-1;
                
            }
                    }
        }for(int m:exponentes){
            if(m==-1){
                cont2++;
            }
        }
         cont3=exponentes.length-cont2;
         int m=0;int exp1;int exp2;uC=polC.getCab();
         while(m<exponentes.length){
             exp1=exponentes[m];
             if(uC.getLiga()==null){
                 break;
             }
             exp2=uC.getLiga().getT().getExp();
             
             if(exp1!=exp2){
                 uC.setLiga(uC.getLiga().getLiga());
                 m++;
             }else{
                 m++;
                 uC=uC.getLiga();
             }
         }
        
         return polC;
     }
     
     /**
      * 
      * @param polA dato de tipo PLS que es el Dividiendo 
      * @param polB dato de tipo PLS que es el Divisor 
      * @return Retorna el cociente de los dos mencionados anteriormente, dato de tipo PLS igualmente 
      */
     
     public static PolinomioListaSimple dividirBeta(PolinomioListaSimple polA,PolinomioListaSimple polB ){
         PolinomioListaSimple pCociente = new PolinomioListaSimple();
         
         polA=polA.Ordenar(polA);
         polB=polB.Ordenar(polB);
         
         PolinomioListaSimple pResiduo = polA;
      
         Nodo posB = polB.getCab().getLiga(); // posicion en nodo de polB
         Nodo posC = pCociente.getCab();       // posicion en nodo de Cociente 
            
        while(pResiduo.getCab().getLiga().getT().getExp() >= posB.getT().getExp()){
            double coefREsido = pResiduo.getCab().getLiga().getT().getCoef();
            int expResiduo = pResiduo.getCab().getLiga().getT().getExp();
            double coefPolB = posB.getT().getCoef();
            int expPolB = posB.getT().getExp();
            
            double coefCociente = coefREsido/coefPolB;
            int expCociente = expResiduo-expPolB;

            Termino tCociente = new Termino(expCociente, coefCociente);

            Nodo n = new Nodo(tCociente);
            posC.setLiga(n);
            posC = n;

            PolinomioListaSimple monoCociente = new PolinomioListaSimple();
            monoCociente.getCab().setLiga(posC);
            
            //String ver = OperadorPolinomios.mostrar(monoCociente);
            //String ver4 = OperadorPolinomios.mostrar(pCociente);
            
            PolinomioListaSimple restaTemporal = multiplicar(polB, monoCociente);
            
            //String ver1 = OperadorPolinomios.mostrar(restaTemporal);
            //String ver2 = OperadorPolinomios.mostrar(pResiduo);
            
            pResiduo = restar(pResiduo, restaTemporal);
            
            //String ver5 = OperadorPolinomios.mostrar(pResiduo);
            
            if(pResiduo.getCab().getLiga() == null )
                break;
            }
         return pCociente;
     }
     
     /**
      * 
      * @param polA Recibe un polinomio de tipo PLS para ser derivado
      * @return retorna el polinomio ya derivado de tipo PLS
      */
     
     public static PolinomioListaSimple derivar(PolinomioListaSimple polA){
         PolinomioListaSimple polR = new PolinomioListaSimple();
         
         polA = polA.Ordenar(polA);
         
         Nodo posA = polA.getCab().getLiga();
         Nodo posR = polR.getCab();
         
         while(posA != null){
             
             int expPosA = posA.getT().getExp();
             double coefPosA = posA.getT().getCoef();
             
             int expPosR = expPosA-1;
             double coefPosR = expPosA*coefPosA;
             
             Termino r = new Termino(expPosR,coefPosR);
             
             Nodo nuevo = new Nodo(r);
             posR.setLiga(nuevo);
             posR = nuevo;
             
             posA = posA.getLiga();
         }
         
         return polR;
         
     }
     
     /**
      * 
      * @param polA Recibe un polinomio para ser ordenado correctamente
      * @return retorna polinomio ya ordenado
      */
     
     public PolinomioListaSimple Ordenar(PolinomioListaSimple polA)  {
        // Variables para manipular los terminos
       PolinomioListaSimple pol = new PolinomioListaSimple();
       polA.factorizar(polA);
      int terminos=0;
          Nodo prA = polA.getCab().getLiga();
          Nodo pr=pol.getCab();
          Nodo prB=prA;
           while(prA != null){
           terminos++;
           prA=prA.getLiga();
       }
           
          int exp;int exp1;int cont=0;int[] cont1=new int[terminos];int pos=0;
        while(prB != null){
        exp=prB.getT().getExp();
        prA = polA.getCab().getLiga();
        cont=0;
        while(prA!= null){
            exp1=prA.getT().getExp();
            if(exp1>exp){
                cont++;
            }else{
                
            }prA=prA.getLiga();
        }cont1[pos]=cont;
        pos++;
        prB=prB.getLiga();
       
        
    }
         
         int j=0;
        for(int i=0;i<terminos;i++){
            prA = polA.getCab().getLiga();
            j=0;
        while(j<terminos){
            if(cont1[j]==i){
                Termino t = new Termino(prA.getT().getExp(), prA.getT().getCoef());
                Nodo n = new Nodo(t);
                pr.setLiga(n);
                pr=pr.getLiga();
                j++;
               
            }else{
                prA=prA.getLiga();
                j++;
            }
        }
        
    }
        
        return pol;
        
    }
     
     /**
      * 
      * @param polC Recibe polinomio para factorizar
      * @return retorna polinomio ya factorizado
      */
     
     public PolinomioListaSimple factorizar(PolinomioListaSimple polC){
          
         Nodo uC=polC.getCab().getLiga();
         
         double nuevoCoef=0;
         int cont=0;
         while(uC != null){
             
             int exp1=uC.getT().getExp();
            nuevoCoef=uC.getT().getCoef();
            Nodo uCProv=uC.getLiga();
         cont++;
             while(uCProv != null){
                                  
                 int exp2=uCProv.getT().getExp();
                 if(exp1==exp2){
                    nuevoCoef=nuevoCoef+uCProv.getT().getCoef();
                    
                }
                                
               uCProv=uCProv.getLiga();
                 }
            
             Termino t=new Termino(exp1,nuevoCoef);
             Nodo n=new Nodo(t);
             uC.setT(t);
             
             uC=uC.getLiga();
             
         }
         
         //En esta parte se quitan los Terminos ya sumados
         uC=polC.getCab().getLiga();
         int[] exponentes=new int[cont];
         int cont1=0;
         while(cont1<cont){
             exponentes[cont1]=uC.getT().getExp();
             uC=uC.getLiga();
            
             cont1++;
             
             
        }int cont2=0;int cont3=0;
         for(int i=0;i<cont;i++){
            for(int j=i+1;j<cont;j++){
            if(exponentes[i]==exponentes[j]){
                
                exponentes[j]=-1;
                
            }
                    }
        }for(int m:exponentes){
            if(m==-1){
                cont2++;
            }
        }
         cont3=exponentes.length-cont2;
         int m=0;int exp1;int exp2;uC=polC.getCab();
         while(m<exponentes.length){
             exp1=exponentes[m];
             if(uC.getLiga()==null){
                 break;
             }
             exp2=uC.getLiga().getT().getExp();
             
             if(exp1!=exp2){
                 uC.setLiga(uC.getLiga().getLiga());
                 m++;
             }else{
                 m++;
                 uC=uC.getLiga();
             }
         }
         return polC;
     }
}

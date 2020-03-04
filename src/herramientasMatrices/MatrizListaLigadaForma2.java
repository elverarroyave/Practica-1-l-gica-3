/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientasMatrices;

import javax.swing.JOptionPane;


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MatrizListaLigadaForma2 {
    
     public NodoDoble nodoConfiguracion; // en el libro se llama mat

    public MatrizListaLigadaForma2(int numeroFilas, int numeroColumnas) {
        construirNodosCabeza(numeroFilas, numeroColumnas);
    }

    /**
     * Métdo que construye el nodo configuración y nodo cabeza
     *
     * @param numeroFilas
     * @param numeroColumnas
     */
    private void construirNodosCabeza(int numeroFilas, int numeroColumnas) {
        Tripleta t = new Tripleta(numeroFilas, numeroColumnas, 0);
        nodoConfiguracion = new NodoDoble(t);

        //Creo el Nodo cabeza y lo configuro para que sea lista ligada circular
        NodoDoble cabeza = new NodoDoble(null);
        // Referencia circular por la liga filas y la liga columnas
        cabeza.setLigaC(cabeza);
        cabeza.setLigaF(cabeza);

        // conecto con el nodo de configuración
        nodoConfiguracion.setLigaC(cabeza);
        nodoConfiguracion.setLigaF(cabeza);
    }
    
    /**
     * Método para ingresar los datos de un nuevo registro e insertarlos en la
     * matriz
     *
     * @param fila fila donde se encuentra el dato
     * @param columna columnas donde se encuentra el dato
     * @param valor valor
     */
    public void insertar(int fila, int columna, int valor) {
        Tripleta nuevoTripletaRegistro = new Tripleta(fila, columna, valor);
        insertar(nuevoTripletaRegistro);
    }

    /**
     * Método para ingresar los datos de un nuevo registro e insertarlos en la
     * matriz
     *
     * @param t
     */
    public void insertar(Tripleta t) {
        NodoDoble nuevoNodo = new NodoDoble(t);
        conectarFilas(nuevoNodo);
        conectarColumnas(nuevoNodo);
        int c = (Integer) nodoConfiguracion.getT().getV();
        nodoConfiguracion.getT().setV(c++);
    }

    /**
     * Método que ingresa un nodo recorriendo la lista de las filas
     *
     * @param nuevoNodo
     */
    private void conectarFilas(NodoDoble nuevoNodo) {
        // datos para la comparación
        int filaNuevoNodo = nuevoNodo.getT().getF();
        int columnaNuevoNodo = nuevoNodo.getT().getC();

        // nodos para el recorrido
        NodoDoble cabeza = getCabeza();
        NodoDoble ultimo = cabeza;
        NodoDoble nodoRecorrido = cabeza.getLigaF();

        // Permite posicionar el nodoRecorrido en la fila correcta para ingresar 
        while (nodoRecorrido != cabeza && nodoRecorrido.getT().getF() < filaNuevoNodo) {
            ultimo = nodoRecorrido;
            nodoRecorrido = nodoRecorrido.getLigaF();
        }

        while (nodoRecorrido != cabeza && nodoRecorrido.getT().getF() == filaNuevoNodo && nodoRecorrido.getT().getC() < columnaNuevoNodo) {
            ultimo = nodoRecorrido;
            nodoRecorrido = nodoRecorrido.getLigaF();
        }

        ultimo.setLigaF(nuevoNodo);
        nuevoNodo.setLigaF(nodoRecorrido);
    }

    /**
     * Método que ingresa un nodo recorriendo la lista de las columnas
     *
     * @param nuevoNodo
     */
    private void conectarColumnas(NodoDoble nuevoNodo) {
        // datos para la comparación
        int fNuevoNodo = nuevoNodo.getT().getF();
        int cNuevoNodo = nuevoNodo.getT().getC();

        // nodos para el recorrido
        NodoDoble cabeza = getCabeza();
        NodoDoble ultimo = cabeza;
        NodoDoble r = cabeza.getLigaC();

        while (r != cabeza && r.getT().getC() < cNuevoNodo) {
            ultimo = r;
            r = r.getLigaC();
        }

        while (r != cabeza && r.getT().getC() == cNuevoNodo && r.getT().getF() < fNuevoNodo) {
            ultimo = r;
            r = r.getLigaC();
        }

        ultimo.setLigaC(nuevoNodo);
        nuevoNodo.setLigaC(r);

    }

    private NodoDoble getCabeza() {
        return nodoConfiguracion.getLigaC();
    }
    
    public String mostrarMatrizEnTripletaPorPantalla() {
        // Obtengo la configuración de la matriz, fr y cr y la cantidadValores
        StringBuilder matrizMostrar = new StringBuilder();
        
        Tripleta configuracion = nodoConfiguracion.getT();
        int fr = configuracion.getF();
        int cr = configuracion.getC();
        // Imprimir una línea con encabezado de las columnas
        matrizMostrar.append("\t");
        for (int i = 1; i <= cr; i++) {
            matrizMostrar.append(i + "\t");
        }
        matrizMostrar.append("\n");

        NodoDoble nodoCabeza = nodoConfiguracion.getLigaF();
        NodoDoble nodoRecorrido = nodoCabeza.getLigaF();
        // Recorrido por una matriz virtual m x n
        for (int fv = 1; fv <= fr; fv++) {
            matrizMostrar.append(fv + "\t");
            for (int cv = 1; cv <= cr; cv++) {
                if (nodoRecorrido != null && nodoRecorrido != nodoCabeza) {
                    Tripleta triMo = nodoRecorrido.getT();
                    int ft = triMo.getF();
                    int ct = triMo.getC();
                    if (fv == ft) {
                        if (cv < ct) {
                            matrizMostrar.append("0\t");
                        } else if (cv == ct) {
                            Object vt = triMo.getV();
                            if (vt != null) {
                                matrizMostrar.append(vt + "\t");
                            } else {
                                System.out.print("ERROR x COLUMNAS!!!!");
                                
                            }
                            nodoRecorrido = nodoRecorrido.getLigaF();
                        } else {
                            System.out.print("ERROR x COLUMNAS se paso!!!!");
                        }
                    } else {
                        matrizMostrar.append("0\t");
                    }
                } else {
                    matrizMostrar.append("0\t");
                }
            }
            matrizMostrar.append("\n");
        }
        
        return matrizMostrar.toString();

    }

    public void mostrarMatrizEnTripletaPorPantallaTexto() {
        // Obtengo la configuración de la matriz, fr y cr y la cantidadValores
        Tripleta configuracion = nodoConfiguracion.getT();
        int fr = configuracion.getF();
        int cr = configuracion.getC();
        // Imprimir una línea con encabezado de las columnas
        System.out.print("\t");
        for (int i = 1; i <= cr; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");

        NodoDoble nodoCabeza = nodoConfiguracion.getLigaF();
        NodoDoble nodoRecorrido = nodoCabeza.getLigaF();
        // Recorrido por una matriz virtual m x n
        for (int fv = 1; fv <= fr; fv++) {
            System.out.print(fv + "\t");
            for (int cv = 1; cv <= cr; cv++) {
                if (nodoRecorrido != null && nodoRecorrido != nodoCabeza) {
                    Tripleta triMo = nodoRecorrido.getT();
                    int ft = triMo.getF();
                    int ct = triMo.getC();
                    if (fv == ft) {
                        if (cv < ct) {
                            System.out.print("0\t");
                        } else if (cv == ct) {
                            Object vt = triMo.getV();
                            if (vt != null) {
                                System.out.print(vt + "\t");
                            } else {
                                System.out.print("ERROR x COLUMNAS!!!!");
                            }
                            nodoRecorrido = nodoRecorrido.getLigaF();
                        } else {
                            System.out.print("ERROR x COLUMNAS se paso!!!!");
                        }
                    } else {
                        System.out.print("0\t");
                    }
                } else {
                    System.out.print("0\t");
                }
            }
            System.out.println("");
        }
        
        

    }
    
    public static MatrizListaLigadaForma2 matrizPorEscalar(int pEscalar, MatrizListaLigadaForma2 pMatriz){
       
        MatrizListaLigadaForma2 matrizResultado = new MatrizListaLigadaForma2(pMatriz.nodoConfiguracion.getT().getF(), pMatriz.nodoConfiguracion.getT().getC());
        
        NodoDoble cab = pMatriz.nodoConfiguracion.getLigaC();
        
        NodoDoble recorreM = cab.getLigaF();
        
        while(recorreM != cab ){
            
            int x = (Integer)recorreM.getT().getV();
            
            matrizResultado.insertar(recorreM.getT().getF(), recorreM.getT().getC(), pEscalar*x );
            
            recorreM = recorreM.getLigaF();
        }
        
       
       return matrizResultado;
    }
    
    public static MatrizListaLigadaForma2 matrizPorMatriz(MatrizListaLigadaForma2 aMatriz, MatrizListaLigadaForma2 bMatriz){
        MatrizListaLigadaForma2 matrizResultado = new MatrizListaLigadaForma2(aMatriz.nodoConfiguracion.getT().getF(), bMatriz.nodoConfiguracion.getT().getC());
        
        NodoDoble cabA = aMatriz.nodoConfiguracion.getLigaF();
        NodoDoble cabB = bMatriz.nodoConfiguracion.getLigaC();
        
        NodoDoble recorreA = cabA.getLigaF();
        NodoDoble recorreB = cabB.getLigaC();
        
        int cantidadFilasDeA = aMatriz.nodoConfiguracion.getT().getF();
        int cantidadColumnasDeA = aMatriz.nodoConfiguracion.getT().getC();
        
        int cantidadFilasDeB = bMatriz.nodoConfiguracion.getT().getF();
        int cantidadColumnasDeB = bMatriz.nodoConfiguracion.getT().getC();
        
        
        NodoDoble nodoVirtual = null;
        NodoDoble cambioDeFila = null;
        
            /*
            Estos for creo que no me estan funcionando porque cantidad de filasDeA solo toma cuando las filas NO son vacias.
            */
        
        for(int m = 1; m <= cantidadFilasDeA; m++){
        
            int i = 1;
            
            recorreB = cabB.getLigaC();
            
            /*
            Estos for creo que no me estan funcionando porque porque cantidad de columnasDeB solo toma cuando las columnas NO son vacias.
            */

            for( int p = 1; p <= cantidadColumnasDeB;p++){
                
                /*
                if(recorreB == cabB){
                    recorreA = cambioDeFila;
                }
                */
                
                nodoVirtual = recorreA;
                
                /*
                if(nodoVirtual == cabA){
                    break;
                }
                */
                
                
                int acum = 0;

                int fPosicion = 0;
                int cPosicion = 0;
                
                if(recorreB == cabB){
                        break;
                }

                while(recorreB.getT().getC()==i){
                    
                    if(nodoVirtual == cabA){
                    break;
                    }
                    
                    if(recorreB == cabB){
                        break;
                    }
                    
                    //Nivelacion en pisiciones de los nodos
                    while(nodoVirtual.getT().getC() < recorreB.getT().getF()){
                        nodoVirtual = nodoVirtual.getLigaF();
                        if(nodoVirtual == cabA){
                        break;
                        }
                    }
                    
                    if(nodoVirtual == cabA){
                    break;
                    }
                    
                    //nivelacion en posiciones de los nodos
                    while(nodoVirtual.getT().getC() > recorreB.getT().getF() ){
                        recorreB = recorreB.getLigaC();
                        if(recorreB == cabB){
                        break;
                        }
                    }
                    
                    if(recorreB == cabB){
                        break;
                    }
                        
                    if(nodoVirtual.getT().getC() == recorreB.getT().getF()){
                        int mulTermino = (int)nodoVirtual.getT().getV()*(int)recorreB.getT().getV();
                        acum += mulTermino;
                        
                        fPosicion = nodoVirtual.getT().getF();
                        cPosicion = recorreB.getT().getC();
                        
                        nodoVirtual = nodoVirtual.getLigaF();
                        recorreB = recorreB.getLigaC();    
                        
                    }
                    
                    
                    if(recorreB == cabB){
                        break;
                    }

                }

                i++;
                if(acum!=0){
                    matrizResultado.insertar(fPosicion, cPosicion, acum);
                }


            }
            
            //recorreA = nodoVirtual;
            
            /*
            Debo plantear una forma de cambiar de fila en la matriz A cuando 
            terminemos de recorre b por todas las columnas esto es cuando el recorreB sea igual a cabeza
            */
            
            if(recorreB == cabB){
                //Esto debe arreglar el error 
                if(nodoVirtual == cabA){
                    break;
                }
                                
                while(nodoVirtual.getT().getF()==recorreA.getT().getF()){
                    nodoVirtual = nodoVirtual.getLigaF();
                    if(nodoVirtual == cabA){
                        break;
                    }
                }
                
                recorreA = nodoVirtual;    
            }
                       
        }
        
        return matrizResultado;
    }
    
}

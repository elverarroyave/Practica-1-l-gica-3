/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazPolinomio;

import InterfazMatriz.Menu;
import javax.swing.DefaultListModel;
import herramientasPolinomios.Nodo;
import herramientasPolinomios.PolinomioListaSimple;
import herramientasPolinomios.Termino;

import java.text.DecimalFormat;

/**
 *
 * @author Elver Arroyave <sguergachi at gmail.com>
 */
public class OperadorPolinomios extends javax.swing.JFrame {

    static DefaultListModel modeloLista;

    
    public OperadorPolinomios() {
        initComponents();
        this.setLocationRelativeTo(null); // Para localizar la ventana en el medio de la pantalla
        this.setResizable(false); // Para permitir al usuario minimizar o mazimizar tamaño de la ventana
        modeloLista = new DefaultListModel();
        listaDePolinomios.setModel(modeloLista);
        agregarPol1.setEditable(false);
        agregarPol2.setEditable(false);
        resultado.setEditable(true);
        BotonRestar.setEnabled(true);
        BotonDividir.setEnabled(true);
        BotonMultiplicar.setEnabled(true);
    }
    
    /**
     * Con este metodo determinamos la cantidad de terminos del polinomio ingresado en forma String 
     * 
     * @param polinomioAlfaNumerico polinomio recibido en tipo String
     * @return nos retorna int numero de terminos de polinomio
     */

    public static int cantidadTerminosPol(String polinomioAlfaNumerico) {
        int positivos = 0;
        int negativos = 0;

        for (int i = 0; i < polinomioAlfaNumerico.length(); i++) {
            if (polinomioAlfaNumerico.charAt(i) == '+' && i != 0) {
                positivos++;
            }
            if (polinomioAlfaNumerico.charAt(i) == '-' && i != 0) {
                negativos++;
            }
        }
        return positivos + negativos + 1;
    }
    
    /**
     * Dividimos el polinomio en forma String por terminos 
     * @param polinomioAlfaNumerico recibe el polinomio en forma String ingresado por el ususario
     * @return String Retorna un vector que en sus posiciones tiene cada uno de los terminos de los polinomio
     */

    public static String[] terminosDelPolinomio(String polinomioAlfaNumerico){ 
        
        String[] terminosPolAlfaNumerico = new String[cantidadTerminosPol(polinomioAlfaNumerico)];
        
        int y = 0;
        int x = 0;
        
        for(int i = 0; i < cantidadTerminosPol(polinomioAlfaNumerico); i++){
            boolean seguir = true;
            while(x<polinomioAlfaNumerico.length() && seguir){
                if((polinomioAlfaNumerico.charAt(x) =='+' || polinomioAlfaNumerico.charAt(x) == '-') && x!=0){
                    terminosPolAlfaNumerico[i] = polinomioAlfaNumerico.substring(y, x);
                    y=x;
                    seguir = false;
                }else{
                    if(x==polinomioAlfaNumerico.length()-1)
                        terminosPolAlfaNumerico[i] = polinomioAlfaNumerico.substring(y);
                }
                x++;
            }
        }
        return terminosPolAlfaNumerico;
    }
    
    /**En este metodo recibimos el polinomio ya dividido por terminos en un vector
     * 
     * @param polAlfaNumerico vector que contiene los terminos del polinomio en cada una de sus respectivas posisiones 
     * @return retorna polinomio en forma de lista ligada
     */
    
    public static PolinomioListaSimple transformar(String[] polAlfaNumerico){
        PolinomioListaSimple polTransformado = new PolinomioListaSimple();
        
        Nodo uR = polTransformado.getCab();
        
        for (String termino : polAlfaNumerico) {
            int exp = 0;
            double coef= 0;
            
            String[] subTerminos;
                    
            
            subTerminos = termino.split("x\\^");
            
            coef = Double.parseDouble(subTerminos[0]);
            exp = Integer.parseInt(subTerminos[1]);
            
            Termino t = new Termino(exp,coef);
            
            Nodo nuevo = new Nodo(t);
            uR.setLiga(nuevo);
            uR = nuevo;
        }
        
        return polTransformado;
    }
    
    
    
    //Clase para acotar el numero de decimales(No implementada porque lo hace en todas las operaciones y solo lo necesitamos en la division)
    
    static DecimalFormat df = new DecimalFormat("#.###");
    
    /**
     * Este metodo recibe un polinomio en forma de lista ligada y nos lo conviente en un string para poder mostrar al usuario
     * @param pol Polinomio en forma lista ligada
     * @return String polinomio en forma Alfanumerica listo para mostrar al usuario.
     */
    
    public static String mostrar(PolinomioListaSimple pol) {
        StringBuilder polinomio = new StringBuilder();
        Nodo p = pol.getCab().getLiga();
        while (p != null) {
            Termino ti = p.getT();
            if(ti.getExp()==0){
                if(ti.getCoef()>0){
                    if(p == pol.getCab().getLiga()){
                        polinomio.append(df.format(ti.getCoef()));
                    }else{
                        polinomio.append("+").append(df.format(ti.getCoef()));
                    }
                }else{
                    polinomio.append(df.format(ti.getCoef()));
                }
            }
            if(ti.getExp()>0){
                if(ti.getCoef()>0){
                    if(p == pol.getCab().getLiga()){
                        polinomio.append(df.format(ti.getCoef())).append("x^").append(ti.getExp());
                    }else{
                        polinomio.append("+").append(df.format(ti.getCoef())).append("x^").append(ti.getExp());
                    }
                    
                }else{
                    polinomio.append(df.format(ti.getCoef())).append("x^").append(ti.getExp());
                }          
            }
            p = p.getLiga();
        }
        return polinomio.toString();
    }
    
    /**
     * agrega un polinomio a la lista 
     */
    
    public void agregarPolinomio() {
        String polFormaAlfaNum = agregarNuePol.getText();
        modeloLista.addElement(polFormaAlfaNum);

    }
    
    // Elimina un polinomio seleccionado de la lista

    public void eliminarPolinomio() {
        int posicion = listaDePolinomios.getSelectedIndex();
        modeloLista.remove(posicion);
    }

    int seleccionDeCasilla = 5;
    
    /**
     * Realiza proceso para seleccionar polinomio de la lista 
     */

    public void seleccionarPolinomioDeLista() {
        int posicion = listaDePolinomios.getSelectedIndex();

        String polSeleccionado = modeloLista.getElementAt(posicion).toString();

        if (seleccionDeCasilla % 2 == 1) {
            agregarPol1.setText(polSeleccionado);
            seleccionDeCasilla++;
        } else {
            agregarPol2.setText(polSeleccionado);
            seleccionDeCasilla++;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        BotonSuamar = new javax.swing.JButton();
        BotonRestar = new javax.swing.JButton();
        BotonMultiplicar = new javax.swing.JButton();
        BotonDividir = new javax.swing.JButton();
        BotonDerivar = new javax.swing.JButton();
        labelAgregarPol1 = new javax.swing.JLabel();
        labelAgregarPol2 = new javax.swing.JLabel();
        agregarPol1 = new javax.swing.JTextField();
        agregarPol2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaDePolinomios = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        resultado = new javax.swing.JTextField();
        labelResultado = new javax.swing.JLabel();
        labelAgregarNuePol = new javax.swing.JLabel();
        agregarNuePol = new javax.swing.JTextField();
        BotonSeleccionarPol = new javax.swing.JButton();
        BotonAgregarNuePol = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(335, 625));
        setMinimumSize(new java.awt.Dimension(335, 625));
        setSize(new java.awt.Dimension(335, 625));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("OPERACIONES CON POLINOMIOS ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 10, 283, 44);

        BotonSuamar.setBackground(new java.awt.Color(0, 153, 153));
        BotonSuamar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BotonSuamar.setText("Sumar");
        BotonSuamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSuamarActionPerformed(evt);
            }
        });
        getContentPane().add(BotonSuamar);
        BotonSuamar.setBounds(30, 400, 67, 27);

        BotonRestar.setBackground(new java.awt.Color(0, 153, 153));
        BotonRestar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BotonRestar.setText("Restar");
        BotonRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRestarActionPerformed(evt);
            }
        });
        getContentPane().add(BotonRestar);
        BotonRestar.setBounds(120, 400, 67, 27);

        BotonMultiplicar.setBackground(new java.awt.Color(0, 153, 153));
        BotonMultiplicar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BotonMultiplicar.setText("Multiplicar");
        BotonMultiplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonMultiplicarActionPerformed(evt);
            }
        });
        getContentPane().add(BotonMultiplicar);
        BotonMultiplicar.setBounds(200, 400, 91, 27);

        BotonDividir.setBackground(new java.awt.Color(0, 153, 153));
        BotonDividir.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BotonDividir.setText("Dividir");
        BotonDividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonDividirActionPerformed(evt);
            }
        });
        getContentPane().add(BotonDividir);
        BotonDividir.setBounds(80, 440, 71, 27);

        BotonDerivar.setBackground(new java.awt.Color(0, 153, 153));
        BotonDerivar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BotonDerivar.setText("Derivar");
        BotonDerivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonDerivarActionPerformed(evt);
            }
        });
        getContentPane().add(BotonDerivar);
        BotonDerivar.setBounds(170, 440, 74, 27);

        labelAgregarPol1.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        labelAgregarPol1.setForeground(new java.awt.Color(0, 0, 0));
        labelAgregarPol1.setText("Polinomio 1:");
        getContentPane().add(labelAgregarPol1);
        labelAgregarPol1.setBounds(30, 323, 80, 20);

        labelAgregarPol2.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        labelAgregarPol2.setForeground(new java.awt.Color(0, 0, 0));
        labelAgregarPol2.setText("Polinomio 2:");
        getContentPane().add(labelAgregarPol2);
        labelAgregarPol2.setBounds(30, 365, 100, 19);

        agregarPol1.setBackground(new java.awt.Color(153, 255, 204));
        agregarPol1.setToolTipText("Selecciones un polinomio de la lista ");
        agregarPol1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPol1ActionPerformed(evt);
            }
        });
        getContentPane().add(agregarPol1);
        agregarPol1.setBounds(110, 320, 180, 30);

        agregarPol2.setBackground(new java.awt.Color(153, 255, 204));
        agregarPol2.setToolTipText("Seleccione un polinomio de la lista");
        agregarPol2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPol2ActionPerformed(evt);
            }
        });
        getContentPane().add(agregarPol2);
        agregarPol2.setBounds(110, 360, 180, 30);

        listaDePolinomios.setBackground(new java.awt.Color(153, 255, 255));
        jScrollPane1.setViewportView(listaDePolinomios);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 170, 261, 100);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Lista de polinomios agregados:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 150, 200, 19);

        resultado.setBackground(new java.awt.Color(153, 255, 255));
        resultado.setToolTipText("Resultado!");
        resultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadoActionPerformed(evt);
            }
        });
        getContentPane().add(resultado);
        resultado.setBounds(30, 500, 260, 30);

        labelResultado.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        labelResultado.setForeground(new java.awt.Color(0, 0, 0));
        labelResultado.setText("Resultado:");
        getContentPane().add(labelResultado);
        labelResultado.setBounds(30, 480, 90, 15);

        labelAgregarNuePol.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        labelAgregarNuePol.setForeground(new java.awt.Color(0, 0, 0));
        labelAgregarNuePol.setText("Agregar polinomio:");
        getContentPane().add(labelAgregarNuePol);
        labelAgregarNuePol.setBounds(30, 58, 140, 19);

        agregarNuePol.setBackground(new java.awt.Color(153, 255, 255));
        agregarNuePol.setToolTipText("Agrege un polinomio: Ej. 1x^2+3x^1-2x^0");
        agregarNuePol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarNuePolActionPerformed(evt);
            }
        });
        getContentPane().add(agregarNuePol);
        agregarNuePol.setBounds(30, 80, 261, 30);

        BotonSeleccionarPol.setBackground(new java.awt.Color(0, 153, 153));
        BotonSeleccionarPol.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BotonSeleccionarPol.setText("Seleccionar");
        BotonSeleccionarPol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSeleccionarPolActionPerformed(evt);
            }
        });
        getContentPane().add(BotonSeleccionarPol);
        BotonSeleccionarPol.setBounds(30, 280, 96, 27);

        BotonAgregarNuePol.setBackground(new java.awt.Color(0, 153, 153));
        BotonAgregarNuePol.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BotonAgregarNuePol.setText("Agregar");
        BotonAgregarNuePol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarNuePolActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAgregarNuePol);
        BotonAgregarNuePol.setBounds(30, 110, 76, 27);

        BotonEliminar.setBackground(new java.awt.Color(0, 153, 153));
        BotonEliminar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BotonEliminar.setText("Eliminar");
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(BotonEliminar);
        BotonEliminar.setBounds(210, 280, 77, 27);

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton1.setText("Volver al menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(100, 540, 114, 28);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imege/fondo2.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(360, 800));
        jLabel2.setMinimumSize(new java.awt.Dimension(360, 800));
        jLabel2.setPreferredSize(new java.awt.Dimension(360, 800));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 370, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonMultiplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonMultiplicarActionPerformed
        PolinomioListaSimple polA = transformar(terminosDelPolinomio(agregarPol1.getText()));
        PolinomioListaSimple polB = transformar(terminosDelPolinomio(agregarPol2.getText()));
        
        PolinomioListaSimple resultadoLL = PolinomioListaSimple.multiplicar(polA, polB);
        
        String resultadoAlfaNumerico = mostrar(resultadoLL);
        
        resultado.setText(resultadoAlfaNumerico);
    }//GEN-LAST:event_BotonMultiplicarActionPerformed

    private void BotonDerivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonDerivarActionPerformed
        int pos = listaDePolinomios.getSelectedIndex();
        
        String polSel = modeloLista.getElementAt(pos).toString();
        
        PolinomioListaSimple polDerivar = transformar(terminosDelPolinomio(polSel));
        
        PolinomioListaSimple resultadoLL = PolinomioListaSimple.derivar(polDerivar);
        
        resultado.setText(mostrar(resultadoLL));
        
    }//GEN-LAST:event_BotonDerivarActionPerformed

    private void BotonSuamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSuamarActionPerformed
        
        
        PolinomioListaSimple polA = transformar(terminosDelPolinomio(agregarPol1.getText()));
        PolinomioListaSimple polB = transformar(terminosDelPolinomio(agregarPol2.getText()));
        
        PolinomioListaSimple resultadoLL = PolinomioListaSimple.sumar(polA, polB);
        
        String resultadoAlfaNumerico = mostrar(resultadoLL);
        
        resultado.setText(resultadoAlfaNumerico);
        
        
        
    }//GEN-LAST:event_BotonSuamarActionPerformed

    private void agregarPol2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPol2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agregarPol2ActionPerformed

    private void resultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resultadoActionPerformed

    private void BotonAgregarNuePolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarNuePolActionPerformed
        agregarPolinomio();
    }//GEN-LAST:event_BotonAgregarNuePolActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
        eliminarPolinomio();
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void agregarNuePolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarNuePolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agregarNuePolActionPerformed

    private void agregarPol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agregarPol1ActionPerformed

    private void BotonSeleccionarPolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSeleccionarPolActionPerformed
        seleccionarPolinomioDeLista();
    }//GEN-LAST:event_BotonSeleccionarPolActionPerformed

    private void BotonRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRestarActionPerformed
        PolinomioListaSimple polA = transformar(terminosDelPolinomio(agregarPol1.getText()));
        PolinomioListaSimple polB = transformar(terminosDelPolinomio(agregarPol2.getText()));
        
        PolinomioListaSimple resultadoLL = PolinomioListaSimple.restar(polA, polB);
        
        String resultadoAlfaNumerico = mostrar(resultadoLL);
        
        resultado.setText(resultadoAlfaNumerico);
    }//GEN-LAST:event_BotonRestarActionPerformed

    private void BotonDividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonDividirActionPerformed
        PolinomioListaSimple polA = transformar(terminosDelPolinomio(agregarPol1.getText()));
        PolinomioListaSimple polB = transformar(terminosDelPolinomio(agregarPol2.getText()));
        
        PolinomioListaSimple resultadoLL = PolinomioListaSimple.dividirBeta(polA, polB);
        
        String resultadoAlfaNumerico = mostrar(resultadoLL);
        
        resultado.setText(resultadoAlfaNumerico);
    }//GEN-LAST:event_BotonDividirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Menu men = new Menu();
        men.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.out.println();
        
        
        
        /**
         * Prueba de capturar terminos de polinomio AlfaNumerico
         */
        
        /*
        String prueba = "-3x^3+2x^2-1x^1+4x^0";

        int cantidad = cantidadTerminosPol(prueba);

        System.out.println("La cantidad de terminos es: " + cantidad);

        String[] pruebaTerminos;
        pruebaTerminos = terminosDelPolinomio(prueba);

        for (int i = 0; i < cantidad; i++) {
            System.out.println("Termino " + i + ": " + pruebaTerminos[i]);
        }
        
        System.out.println("---------------------------------------------");
        
        PolinomioListaSimple pruebaPol = transformar(pruebaTerminos);
        
        System.out.println("Mi polinomio: " + mostrar(pruebaPol));
        
        System.out.println();
        
        */
        
        /*
        
        */
        PolinomioListaSimple polA = transformar(terminosDelPolinomio("-3x^2+2x^1+1x^0"));
        PolinomioListaSimple polB = transformar(terminosDelPolinomio("5x^4-3x^1"));
        
        String resulPolA = mostrar(polA);
        String resulPolB = mostrar(polB);
        
        System.out.println(resulPolA +  "  +  " +  resulPolB);
        
        PolinomioListaSimple resultadoLL = PolinomioListaSimple.sumar(polA, polB);
        
        String resultadoAlfaNumerico = mostrar(resultadoLL);
        
        
        System.out.println("Resultado: " + resultadoAlfaNumerico);
                
        
        
        

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OperadorPolinomios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OperadorPolinomios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OperadorPolinomios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OperadorPolinomios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OperadorPolinomios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarNuePol;
    private javax.swing.JButton BotonDerivar;
    private javax.swing.JButton BotonDividir;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonMultiplicar;
    private javax.swing.JButton BotonRestar;
    private javax.swing.JButton BotonSeleccionarPol;
    private javax.swing.JButton BotonSuamar;
    private javax.swing.JTextField agregarNuePol;
    private javax.swing.JTextField agregarPol1;
    private javax.swing.JTextField agregarPol2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAgregarNuePol;
    private javax.swing.JLabel labelAgregarPol1;
    private javax.swing.JLabel labelAgregarPol2;
    private javax.swing.JLabel labelResultado;
    private javax.swing.JList<String> listaDePolinomios;
    private javax.swing.JTextField resultado;
    // End of variables declaration//GEN-END:variables
}

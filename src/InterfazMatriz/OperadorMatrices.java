/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazMatriz;
import herramientasMatrices.MatrizListaLigadaForma2;
import herramientasMatrices.Tripleta;
import javax.swing.JOptionPane;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class OperadorMatrices extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public OperadorMatrices() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        areaMatriz.setEditable(false);
        this.btnInversa.setEnabled(false);
    }
    
    /*Creamos las varibles en memoria de las matrices a almacenas
     */
    
    MatrizListaLigadaForma2 matrizA;
    MatrizListaLigadaForma2 matrizB;
    
    /**
     * Metodo para ingresar la matriz
     */
    
    public void ingresarMatriz(){
        
        if(jComboBox1.getSelectedItem().toString().equals("A")){
        String f = textFila.getText();
        String c = textColumna.getText();
        
        int fila = Integer.parseInt(f);
        int columna = Integer.parseInt(c);
        
        matrizA = new MatrizListaLigadaForma2( fila, columna);
        }
        
        if(jComboBox1.getSelectedItem().toString().equals("B")){
        String f = textFila.getText();
        String c = textColumna.getText();
        
        int fila = Integer.parseInt(f);
        int columna = Integer.parseInt(c);
        
        matrizB = new MatrizListaLigadaForma2( fila, columna);
        }
        
        Tripleta t;
        while((t = ingresarTripletaPorDiaologo()) != null){
            if(jComboBox1.getSelectedItem().toString().equals("A")){
            matrizA.insertar(t);
            }
            if(jComboBox1.getSelectedItem().toString().equals("B")){
            matrizB.insertar(t);
            }
        }
        
        JOptionPane.showMessageDialog(null, "Hás ingresado la matriz de manera exitosa", "Correcto", JOptionPane.INFORMATION_MESSAGE);
        
        if(jComboBox1.getSelectedItem().toString().equals("A")){
            String representacionMatriz = matrizA.mostrarMatrizEnTripletaPorPantalla();
            areaMatriz.setText(representacionMatriz);
            System.out.println(representacionMatriz);
        }
        if(jComboBox1.getSelectedItem().toString().equals("B")){
            String representacionMatriz = matrizB.mostrarMatrizEnTripletaPorPantalla();
            areaMatriz.setText(representacionMatriz);
            System.out.println(representacionMatriz);
        }
        
        
        
        
        /**
         * String matrizVisual = matrizX.mostrarMatrizEnTripletaPorPantallaTexto();
         */
        
    }
    
    /**
     * Con este medoto recibiremos las tripletas de la matriz ingresadas por el usuario
     * @return Tripleta devuelve la tripleta ingresada por el usuario donde lo utilizamos en el metodo anterior
     */
    
    private Tripleta ingresarTripletaPorDiaologo() {
        String datos[] = panelVector("Ingrese la tripleta separada por (coma ,) [ Ejemplo: 2,3,5] :\n"
                                   + "(Para finalizar digita 0,0,0).");
        Tripleta t = null;
        int f = Integer.valueOf(datos[0]);
        int c = Integer.valueOf(datos[1]);
        int v = Integer.valueOf(datos[2]);
        if (!(f == 0 || c == 0 || v == 0)) {
            t = new Tripleta(f, c, v);
        }
        return t;
    }
    
    /**En este metodo simplificamos la lectura del usuario al ingresar una tripleta por teclado de tipo String
     * 
     * @param mensaje obtiene la tripleta en forma String nigresada por el usuario
     * @return String[] nos retorna un String con los valores de fila, columna , dato en cada posicion respectivamente 
     */
    
    public String[] panelVector(String mensaje){
        String textTriplta = JOptionPane.showInputDialog(mensaje);
        String[] datos;
        datos = textTriplta.split(",");
        return datos;
        
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIngresarMatriz = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaMatriz = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textFila = new javax.swing.JTextField();
        textColumna = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnInversa = new javax.swing.JButton();
        btnMulEscalar = new javax.swing.JButton();
        bntAporB = new javax.swing.JButton();
        btnBporA = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(620, 480));
        getContentPane().setLayout(null);

        btnIngresarMatriz.setBackground(new java.awt.Color(0, 153, 153));
        btnIngresarMatriz.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnIngresarMatriz.setText("Ingresar Matriz");
        btnIngresarMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarMatrizActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresarMatriz);
        btnIngresarMatriz.setBounds(42, 171, 189, 28);

        areaMatriz.setBackground(new java.awt.Color(153, 255, 255));
        areaMatriz.setColumns(20);
        areaMatriz.setRows(5);
        jScrollPane2.setViewportView(areaMatriz);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(272, 65, 300, 311);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("OPERACIONES CON MATRICES");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 20, 257, 23);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ingresar Matrices");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 60, 140, 21);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("# Filas:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(42, 135, 50, 18);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("# Colmunas:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(140, 135, 80, 18);

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Seleccionar matriz:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 95, 108, 18);

        textFila.setBackground(new java.awt.Color(153, 255, 255));
        textFila.setToolTipText("Ingrese el numero de filas de la matriz");
        textFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFilaActionPerformed(evt);
            }
        });
        getContentPane().add(textFila);
        textFila.setBounds(87, 129, 30, 30);

        textColumna.setBackground(new java.awt.Color(153, 255, 255));
        textColumna.setToolTipText("Ingrese el número de columnas de la matriz");
        getContentPane().add(textColumna);
        textColumna.setBounds(210, 130, 30, 30);

        jComboBox1.setBackground(new java.awt.Color(0, 153, 153));
        jComboBox1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B" }));
        jComboBox1.setToolTipText("Seleccione el nombre de la matriz a ingresar");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(150, 90, 50, 30);

        btnInversa.setBackground(new java.awt.Color(0, 153, 153));
        btnInversa.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnInversa.setText("Calcular inversa");
        btnInversa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInversaActionPerformed(evt);
            }
        });
        getContentPane().add(btnInversa);
        btnInversa.setBounds(42, 348, 123, 28);

        btnMulEscalar.setBackground(new java.awt.Color(0, 153, 153));
        btnMulEscalar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnMulEscalar.setText("Multiplicar por escalar");
        btnMulEscalar.setToolTipText("");
        btnMulEscalar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMulEscalarActionPerformed(evt);
            }
        });
        getContentPane().add(btnMulEscalar);
        btnMulEscalar.setBounds(42, 302, 162, 28);

        bntAporB.setBackground(new java.awt.Color(0, 153, 153));
        bntAporB.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        bntAporB.setText("A*B");
        bntAporB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAporBActionPerformed(evt);
            }
        });
        getContentPane().add(bntAporB);
        bntAporB.setBounds(42, 256, 56, 28);

        btnBporA.setBackground(new java.awt.Color(0, 153, 153));
        btnBporA.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnBporA.setText("B*A");
        btnBporA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBporAActionPerformed(evt);
            }
        });
        getContentPane().add(btnBporA);
        btnBporA.setBounds(175, 256, 56, 28);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Operar matrices:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(42, 223, 113, 21);

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton1.setText("Volver al menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(370, 390, 114, 28);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imege/fondo3.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, -10, 640, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarMatrizActionPerformed
        ingresarMatriz();
        textFila.setText(null);
        textColumna.setText(null);
    }//GEN-LAST:event_btnIngresarMatrizActionPerformed

    private void btnBporAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBporAActionPerformed
         
            if(matrizB.nodoConfiguracion.getT().getC()==matrizA.nodoConfiguracion.getT().getF()){
            MatrizListaLigadaForma2 resulMatriz = MatrizListaLigadaForma2.matrizPorMatriz(matrizB, matrizA);
            String representacionMatriz = resulMatriz.mostrarMatrizEnTripletaPorPantalla();
            areaMatriz.setText(representacionMatriz);
            }
            else{
                areaMatriz.setText("-----------------------ERROR-----------------------\n"
                        + "Las matrices deben coincidir en el número de \n"
                        + "columnas B con el número de filas de A para \n"
                        + "poder ser multiplicadas.");
            }
         
    }//GEN-LAST:event_btnBporAActionPerformed

    private void btnInversaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInversaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInversaActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(jComboBox1.getSelectedItem().toString().equals("A") && matrizA != null){
            String representacionMatriz = matrizA.mostrarMatrizEnTripletaPorPantalla();
            areaMatriz.setText(representacionMatriz);
        }
        if(jComboBox1.getSelectedItem().toString().equals("B") && matrizB != null){
            String representacionMatriz = matrizB.mostrarMatrizEnTripletaPorPantalla();
            areaMatriz.setText(representacionMatriz);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void textFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFilaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFilaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Menu men = new Menu();
        men.setVisible(true);   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnMulEscalarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMulEscalarActionPerformed
        int escalar = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el escalar a multiplicar","Ingresar Escalar",JOptionPane.INFORMATION_MESSAGE));
        
        if(jComboBox1.getSelectedItem().toString().equals("A") && matrizA != null){
            MatrizListaLigadaForma2 pMatriz = MatrizListaLigadaForma2.matrizPorEscalar(escalar, matrizA);
            String representacionMatriz = pMatriz.mostrarMatrizEnTripletaPorPantalla();
            areaMatriz.setText(representacionMatriz);
        }
        if(jComboBox1.getSelectedItem().toString().equals("B") && matrizB != null){
            MatrizListaLigadaForma2 pMatriz = MatrizListaLigadaForma2.matrizPorEscalar(escalar, matrizB);
            String representacionMatriz = pMatriz.mostrarMatrizEnTripletaPorPantalla();
            areaMatriz.setText(representacionMatriz);
        }
        
    }//GEN-LAST:event_btnMulEscalarActionPerformed

    private void bntAporBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAporBActionPerformed
            
            if(matrizA.nodoConfiguracion.getT().getC()==matrizB.nodoConfiguracion.getT().getF()){
            MatrizListaLigadaForma2 resulMatriz = MatrizListaLigadaForma2.matrizPorMatriz(matrizA, matrizB);
            String representacionMatriz = resulMatriz.mostrarMatrizEnTripletaPorPantalla();
            areaMatriz.setText(representacionMatriz);
            }
            
            else{
                areaMatriz.setText("-----------------------ERROR-----------------------"
                        + "\nLas matrices deben coincidir en el número de "
                        + "\ncolumnas A con el número de filas de B para "
                        + "\npoder ser multiplicadas.");
            }
           
    }//GEN-LAST:event_bntAporBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(OperadorMatrices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OperadorMatrices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OperadorMatrices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OperadorMatrices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OperadorMatrices().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaMatriz;
    private javax.swing.JButton bntAporB;
    private javax.swing.JButton btnBporA;
    private javax.swing.JButton btnIngresarMatriz;
    private javax.swing.JButton btnInversa;
    private javax.swing.JButton btnMulEscalar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField textColumna;
    private javax.swing.JTextField textFila;
    // End of variables declaration//GEN-END:variables

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author Joaquín
 */
public class GUICelda extends javax.swing.JPanel {

    /**
     * Creates new form GUICelda
     */
    public GUICelda() {
        initComponents();
        //labelArriba.setText("");
        //labelAbajo.setText("");
        labelAbajo.setVisible(false);
        labelArriba.setVisible(false);
        //panel.setBackground(Color.red);
    }
    
    public void modificarFondo(Color color){
        panel.setBackground(color);
    }
    
    public void agregarValorArriba(String valor){
       labelArriba.setText(valor); 
       labelArriba.setVisible(true);
    }
    public void agregarValorAbajo(String valor){
       labelAbajo.setText(valor); 
       labelAbajo.setVisible(true);
    }
    public void agregarValorAmbos(String valorArriba,String valorAbajo){
        labelArriba.setText(valorArriba);
        labelAbajo.setText(valorAbajo);
        labelAbajo.setVisible(true);
        labelArriba.setVisible(true);
    }
    public void agregarValorNulos(){
        labelAbajo.setVisible(false);
        labelArriba.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        labelAbajo = new javax.swing.JLabel();
        labelArriba = new javax.swing.JLabel();

        jLabel3.setText("\\");

            jLabel5.setText("\\");

                setBackground(new java.awt.Color(0, 0, 0));
                setForeground(new java.awt.Color(255, 255, 255));

                panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                panel.setPreferredSize(new java.awt.Dimension(55, 55));

                labelAbajo.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
                labelAbajo.setForeground(new java.awt.Color(0, 102, 255));
                labelAbajo.setText("0");

                labelArriba.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
                labelArriba.setForeground(new java.awt.Color(0, 204, 51));
                labelArriba.setText("0");

                javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
                panel.setLayout(panelLayout);
                panelLayout.setHorizontalGroup(
                    panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(labelAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                panelLayout.setVerticalGroup(
                    panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(labelArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelAbajo)
                        .addGap(10, 10, 10))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                );
            }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelAbajo;
    private javax.swing.JLabel labelArriba;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
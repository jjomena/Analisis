package kakuro;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Joaquín
 */
public class GUIMatriz extends javax.swing.JFrame {
    private Administrador admin;
    private List<Celda> listaCeldas;
    
    
    /**
     * Creates new form GUIMatriz
     */
    public GUIMatriz() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        admin = new Administrador();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jpanelDesign = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jpanelDesign.setBackground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout jpanelDesignLayout = new javax.swing.GroupLayout(jpanelDesign);
        jpanelDesign.setLayout(jpanelDesignLayout);
        jpanelDesignLayout.setHorizontalGroup(
            jpanelDesignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        jpanelDesignLayout.setVerticalGroup(
            jpanelDesignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(jpanelDesign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jpanelDesign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        jpanelDesign.removeAll();
        jpanelDesign.revalidate();
        jpanelDesign.repaint();
        admin.limpiarLista();
        admin.generarListaCeldas(14);
        admin.asignarBarraInferior();
        admin.asignarBarraIzquierda();
        admin.asignarBarraDerecha();
        admin.asignarBarraSuperior();
        admin.asignarCentrosLibres();
        

        listaCeldas = admin.getListaCeldas();
        jpanelDesign.setLayout(new GridLayout(0,14,0,0));
        for (int recorrido = 0; recorrido < listaCeldas.size();recorrido++){
            Celda celda = listaCeldas.get(recorrido);
            TipoCelda tipo = celda.getTipocelda();
            GUICelda panel = null;
            String valorInferior;
            String valorSuperior;
            if(null != tipo)switch (tipo) {
                case ABAJO:
                    panel = new GUICelda();
                    valorInferior = String.valueOf(celda.getValorInferior());
                    panel.agregarValorAbajo(valorInferior);
                    break;
                case ARRIBA:
                    panel = new GUICelda();
                    valorSuperior = String.valueOf(celda.getValorSuperior());
                    panel.agregarValorArriba(valorSuperior);
                    break;
                case BLANCO: 
                    panel = new GUICelda();              
                    panel.modificarFondo(Color.white);
                    break;
                case NEUTRO:
                    panel = new GUICelda();
                    panel.agregarValorAmbos("", "");
                    break;
                case NEGRO:
                    panel = new GUICelda();
                    panel.agregarValorNulos();
                    panel.modificarFondo(Color.black); 
                    break;
                case CENTRO:
                    panel = new GUICelda();
                    valorInferior = String.valueOf(celda.getValorInferior());
                    valorSuperior = String.valueOf(celda.getValorSuperior());
                    panel.agregarValorAmbos(valorSuperior,valorInferior);
                    break;
                default:
                    System.out.println("Entro en caso desconocido");
                    break;
            }
            jpanelDesign.add(panel);
            jpanelDesign.revalidate();
            jpanelDesign.repaint();
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GUIMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIMatriz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jpanelDesign;
    // End of variables declaration//GEN-END:variables
}

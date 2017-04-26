/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

/**
 *
 * @author Joaquín
 */
public class Kakuro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Administrador admin = new Administrador();
        //admin.generarListaCeldas(10);
        //admin.imprimirCeldas();
        //admin.asignarCeldas();
        GUIMatriz gui = new GUIMatriz();
        gui.setTitle("Matriz");
        gui.show();
        
    }
    
}

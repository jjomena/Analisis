/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import static kakuro.kakuroSolucionador.cantidadLista;
import static kakuro.kakuroSolucionador.resolverKakuro;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;

/**
 *
 * @author mailon2
 */
public class hiloResolver extends Thread{
    boolean activo;
    boolean pausa;
    int   segundos;
    Matriz matriz;
    int contador;
    int anterior;
    ArrayList<Matriz> arreglo;
    public hiloResolver(Matriz matriz,ArrayList<Matriz> arreglo){
        this.activo = true;
        this.pausa = false;
        this.matriz = matriz;
        this.segundos =1;
        this.contador =0;
        this.arreglo = arreglo;
        this.anterior = 0;
    }
    
    @Override
    public void run(){
        while(activo){
            
            Matriz m = matriz;
            int colocadas = cantidadLista(m);
            contador++;
            //System.out.println(contador);
            if(contador==colocadas/2){
                System.out.println("No hay solucion");
                this.stop();
            }
            //System.out.println("Matriz de hilo:");
            //m.imprimirMatriz();
            ArrayList<Matriz> soluciones = arreglo;
            //System.out.println(colocadas);
            if(colocadas == m.getDimensiones()){
                this.stop();
            }
            resolverKakuro(m,soluciones);
            if(!soluciones.isEmpty()){
                System.out.println("(-.-)");
                soluciones.get(0).imprimirMatriz();
                this.stop();
            }
        }
    }    
    
    
//    @Override
//    public void run(){
//        while(activo){
//            int miliseconds = this.segundos * 1;   
//            try {
//                sleep(miliseconds);
//            } catch (InterruptedException ex) {
//               Logger.getLogger(hiloResolver.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            aax+=1;
//            //System.out.println(aax);
//            Matriz m = matriz;
//            ArrayList<Matriz> soluciones = arreglo;
//            int colocada = cantidadLista(m);
//            System.out.println("Casillas colocadas: "+colocada);
//            if(colocada >= m.getDimensiones()&&solucion(m)==false){
//                this.stop();
//                if(solucion(m)==false&&solucionColumnas(m)==true){
//                    System.out.println("-");
//                    m.imprimirMatriz();
//                    soluciones.add(m); 
//                }else{
//                //System.out.println("-.-");
//                }
//            }else{  
//                int[]menor = menorFilas(m);
//                boolean modifico = false;
//                if(menor[0]>1){
//                    int fila = menor[1];
//                    int columna = menor[2];
//                    int espacios  = menor[0];
//                    int result[] = new int[espacios];
//                    Celda[][] celd = new Celda[m.getTamano()][m.getTamano()];
//                    celd = clonarM(m);
//                    Matriz m1 = new Matriz();
//                    m1.setMatriz(celd);
//                    ArrayList<int[]> resultado = new ArrayList<int[]>();
//                    int numero = m.getCelda()[fila][columna-1].getDerecha();
//                    backNum(result,numero,resultado,0);
//                    if(resultado.isEmpty()){
//                        this.stop();
//                        System.out.println("Fila: "+fila+" columna: "+columna+" numero:" +numero+" en espacios: "+espacios);
//                    }
//                    for(int i=0;i<resultado.size();i++){
//                        int [] permutacion = resultado.get(i);
//                        if(correctoVector(m1,permutacion,fila,columna)!=true
//                                && columnaCorrecto(m,permutacion,fila,columna)!=true && revisarPermutacion(m,permutacion,fila,columna)!=true){
//                            for(int j=0;j<espacios;j++){
//                                m.getCelda()[fila][columna+j].setValor(permutacion[j]);
//                                m.getCelda()[fila][columna+j].setTipo("t4");
//                            }
//                            modifico = true;
//                        }else{
//                            //contador++;
//                            //System.out.println("Fila: "+fila+" columna: "+columna);
//                            //m.imprimirMatriz();
//                            //imprimir(permutacion);
//                        }
//                    }
//                    int aa=  valorAbajo(m,fila,columna);
//                    Celda cd = m.getCelda()[fila][columna-1];
//                    int der = cd.getDerecha();
//                    int  mt = cantidadLista(m);
//                    //System.out.println("Resultado: "+resultado.size()+" no admitidas: "+contador+" admitidas: "+contado+" en la fila: "+fila+" columna: "+columna+" valor abajo: "+aa+" valor derecha: "+der+" faltan: "+mt);
//                }
//                else{
//                    System.out.println("raios");
//                }
//             if(modifico==false){
//                 this.stop();
//             }   
//            }
//            while (pausa)
//            {
//                try {
//                    sleep(100);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(hiloResolver.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
}

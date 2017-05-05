/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

/**
 *
 * @author mailon2
 */
public class Celdaa {
    private String tipo;  //Si es t1 significa que no es una casilla permitida o pared
                          //Si es t2 significa que es casilla jugable
                          //Si es t3 significa que es casilla indice
    private int valor;    
    private int derecha;
    private int abajo;
    private boolean fila;
    private boolean columna;
    
    public Celdaa(){
        this.tipo = "t1";
        this.derecha = -1;
        this.abajo = -1;
        this.valor = -1;
        this.fila = false;
        this.columna = false;
    }
    public Celdaa(int valor){
        this.tipo = "t2";
        this.derecha = -1;
        this.abajo = -1;
        this.valor = valor;
        this.fila = false;
        this.columna = false;
    }
    
    public Celdaa(int derecha,int abajo) {
        this.tipo = "t3";
        this.derecha = derecha;
        this.abajo = abajo;
        this.valor = -1;
        this.fila = false;
        this.columna = false;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getDerecha() {
        return derecha;
    }

    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }

    public int getAbajo() {
        return abajo;
    }

    public void setAbajo(int abajo) {
        this.abajo = abajo;
    }

    public boolean isFila() {
        return fila;
    }

    public void setFila(boolean fila) {
        this.fila = fila;
    }

    public boolean isColumna() {
        return columna;
    }

    public void setColumna(boolean columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Celdaa{" + "tipo=" + tipo + ", valor=" + valor + ", derecha=" + derecha + ", abajo=" + abajo + ", fila=" + fila + ", columna=" + columna + '}';
    }

    
    

    
}                  

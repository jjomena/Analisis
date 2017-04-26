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
public class Celda {
    TipoCelda tipocelda;
    public posicionEnMatriz posicionEnMatriz;
    public int posicionEnLista;
    public int filaEnMatriz;
    private int valorSuperior;
    private int valorInferior;
    private int valorContenido; 
    
    public Celda(){
    }
    
    public Celda(TipoCelda tipocelda,posicionEnMatriz posicionEnMatriz,
            int posicionEnLista,int filaEnMatriz){
        this.tipocelda = tipocelda;
        this.posicionEnMatriz = posicionEnMatriz;
        this.posicionEnLista = posicionEnLista;
        this.filaEnMatriz = filaEnMatriz;
    }

    
    public void asignarCeldaSuperior(int valorSuperior){
        setTipocelda(TipoCelda.ARRIBA);
        this.valorSuperior = valorSuperior;  
    }
    
    public void asignarCeldaMixta(int valorSuperior,int valorInferior){
        setTipocelda(TipoCelda.CENTRO);
        this.valorSuperior = valorSuperior;
        this.valorInferior = valorInferior;
    }
    public void asignarCeldaInferior(int valorInferior){
        setTipocelda(TipoCelda.ABAJO);
        this.valorInferior = valorInferior;
    }
    
    public void asignarCeldaBlanco(){
        setTipocelda(TipoCelda.BLANCO);
    }
    
    public void asignarCeldaNeutro(){
        setTipocelda(TipoCelda.NEUTRO);
    }
    public void asignarCeldaNegro(){
        setTipocelda(TipoCelda.NEGRO);
    }
    
    public TipoCelda getTipocelda() {
        return tipocelda;
    }

    public void setTipocelda(TipoCelda tipocelda) {
        this.tipocelda = tipocelda;
    }

    public posicionEnMatriz getPosicionEnMatriz() {
        return posicionEnMatriz;
    }

    public void setPosicionEnMatriz(posicionEnMatriz posicionEnMatriz) {
        this.posicionEnMatriz = posicionEnMatriz;
    }

    public int getPosicionEnLista() {
        return posicionEnLista;
    }

    public void setPosicionEnLista(int posicionEnLista) {
        this.posicionEnLista = posicionEnLista;
    }

    public int getFilaEnMatriz() {
        return filaEnMatriz;
    }

    public void setFilaEnMatriz(int filaEnMatriz) {
        this.filaEnMatriz = filaEnMatriz;
    }

    public int getValorSuperior() {
        return valorSuperior;
    }

    public void setValorSuperior(int valorSuperior) {
        this.valorSuperior = valorSuperior;
    }

    public int getValorInferior() {
        return valorInferior;
    }

    public void setValorInferior(int valorInferior) {
        this.valorInferior = valorInferior;
    }

    public int getValorContenido() {
        return valorContenido;
    }

    public void setValorContenido(int valorContenido) {
        this.valorContenido = valorContenido;
    }
    
    
}

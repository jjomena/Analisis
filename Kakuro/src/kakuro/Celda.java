
package kakuro;

public class Celda {
    TipoCelda tipocelda;
    public posicionEnMatriz posicionEnMatriz;
    public int posicionEnLista;
    public int filaEnMatriz;
    private int valorSuperior;
    private int valorInferior;
    private int valorContenido; 
    private int cantidadSuperior;
    private int cantidadInferior;
    
    public Celda(){
    }
    
    public Celda(TipoCelda tipocelda,posicionEnMatriz posicionEnMatriz,
            int posicionEnLista,int filaEnMatriz){
        this.tipocelda = tipocelda;
        this.posicionEnMatriz = posicionEnMatriz;
        this.posicionEnLista = posicionEnLista;
        this.filaEnMatriz = filaEnMatriz;
    }

    
    public void asignarCeldaSuperior(int valorSuperior,int cantidadSuperior){
        setTipocelda(TipoCelda.ARRIBA);
        this.valorSuperior = valorSuperior; 
        this.cantidadSuperior = cantidadSuperior;
    }
    
    public void asignarCeldaMixta(int valorSuperior,int valorInferior,int cantidadSuperior, int cantidadInferior){
        setTipocelda(TipoCelda.CENTRO);
        this.valorSuperior = valorSuperior;
        this.valorInferior = valorInferior;
        this.cantidadSuperior = cantidadSuperior;
        this.cantidadInferior = cantidadInferior;
    }
    public void asignarCeldaInferior(int valorInferior,int cantidadInferior){
        setTipocelda(TipoCelda.ABAJO);
        this.valorInferior = valorInferior;
        this.cantidadInferior = cantidadInferior;
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

    public int getCantidadSuperior() {
        return cantidadSuperior;
    }

    public void setCantidadSuperior(int cantidadSuperior) {
        this.cantidadSuperior = cantidadSuperior;
    }

    public int getCantidadInferior() {
        return cantidadInferior;
    }

    public void setCantidadInferior(int cantidadInferior) {
        this.cantidadInferior = cantidadInferior;
    }
    
    
    
}

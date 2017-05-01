/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Joaquín
 */
public class Administrador {
    private List<Celda> listaCeldas;
    private int dimension;
    
    public Administrador(){
        listaCeldas = new ArrayList<Celda>();
    }

    public List<Celda> getListaCeldas() {
        return listaCeldas;
    }

    public void setListaCeldas(List<Celda> listaCeldas) {
        this.listaCeldas = listaCeldas;
    }
    
    public void limpiarLista(){
        listaCeldas.clear();
    }
    
    public void generarListaCeldas(int dimension){
        this.dimension = dimension;
        posicionEnMatriz posicion = null;
        int dimensionMatriz = dimension * dimension;
        int ultimaFila = dimension*(dimension-1);
        int recorrido = 0;
        int punteroLinea = 1;
        for(int indice = 0; indice < dimensionMatriz; indice++){
            int finDeLinea = (dimension * punteroLinea)-1;
            if(recorrido == dimension){
                recorrido=0;
                punteroLinea++;
            }
            if(indice < dimension){
                posicion = posicion.BarraArriba;
            }
            else if(((indice % dimension)==0)&&(indice<ultimaFila)){
                posicion = posicion.BarraIzquierda;
            }
            else if(indice >= ultimaFila){
                posicion = posicion.BarraAbajo;
            }
            else if(finDeLinea == indice){
                posicion = posicion.BarraDerecha;
            }
            else{
                posicion = posicion.BarraCampo;
            }
            agregarCelda(TipoCelda.NEUTRO,posicion,indice,punteroLinea);
            recorrido++;
        }
     }
     
     public void agregarCelda(TipoCelda tipo,posicionEnMatriz posicion,int indice, int linea){
         Celda cl = new Celda(tipo,posicion,indice,linea);
         this.listaCeldas.add(cl);  
     }
     
    
    public void asignarBarraDerecha(){
        String [] arr = {"Negro", "CeldaInferior"};
        int fila = dimension-1;
        TipoCelda tipo;
        for(int x=1; x<dimension; x++){
            tipo = listaCeldas.get(fila).getTipocelda();
            if(tipo == TipoCelda.NEUTRO){
                Random random = new Random();
                int select = random.nextInt(arr.length);
                if("CeldaInferior".equals(arr[select])){
                    int cantidadDisponibleAbajo = calcularAbajo(fila);
                    int N = cantidadDisponibleAbajo;
                    int M = 2;
                    if(N > 2){
                        int numero = (int) Math.floor(Math.random()*(N-M+1)+M);
                        numero = numero + revisarCruce(numero,fila,cantidadDisponibleAbajo);
                        int maximo = calcularMaximo(numero);
                        int minimo = calcularMinimo(numero);
                        int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
                        System.out.println("Valor entero generado "+valorEntero);
                        asignarBlancosCentrosVerticales(fila,numero);
                        listaCeldas.get(fila).asignarCeldaInferior(valorEntero); 
                    }
                    else{
                        asignarNegrosVerticales(fila,cantidadDisponibleAbajo);
                    }
                    
                }
                else{
                    if(tipo==TipoCelda.NEUTRO){
                        listaCeldas.get(fila).asignarCeldaNegro();
                    }
                }
            }
            fila = fila+dimension;
            
        }
        
    }
    
    public void asignarBarraIzquierda(){
        String [] arr = {"Negro", "CeldaSuperior"};
        listaCeldas.get(0).asignarCeldaNegro();
        int fila = 0;
        int rango = dimension-1;
        TipoCelda tipo;
        for(int  x= 1; x < rango; x++){
            fila = fila+dimension;
            tipo = listaCeldas.get(fila).getTipocelda();
            Random random = new Random();
            int select = random.nextInt(arr.length);
            if("CeldaSuperior".equals(arr[select])){
                //int maximoFila = calcularPosiblesEnfila(fila);
                int maximoFila = calcularCeldaDerecha(fila);
                int minimoFila = 2;
                if(maximoFila>2){
                    int numero = (int) Math.floor(Math.random()*(maximoFila-minimoFila+1)+minimoFila);
                    numero = revisarProximoNulo(fila+numero,numero);
                    int maximo = calcularMaximo(numero);
                    int minimo = calcularMinimo(numero);
                    int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
                    listaCeldas.get(fila).asignarCeldaSuperior(valorEntero);
                    asignarBlancosHorizontales(fila,numero,maximoFila);
                }
                else{
                    asignarNegrosVerticales(fila,maximoFila);   
                }
            }
            else{
                if(tipo==TipoCelda.NEUTRO){
                    listaCeldas.get(fila).asignarCeldaNegro();
                }
            }

        }
    }
    
    public void asignarBarraInferior(){
        String [] arr = {"Negro", "CeldaSuperior"};
        int posicion = dimension*(dimension-1);
        int valorFinal = (dimension*dimension)-1;
        while(posicion <= valorFinal){
            Random random = new Random();
            int select = random.nextInt(arr.length);
            if("CeldaSuperior".equals(arr[select])){
                int cantidadDisponibleDerecha = calcularCeldaDerecha(posicion);
                System.out.println("Cantidad Disponibles "+cantidadDisponibleDerecha);
                int N = cantidadDisponibleDerecha;
                if(N>2){
                    int M = 2;
                    int valorDerecha = (int) Math.floor(Math.random()*(N-M+1)+M);
                    System.out.println("Valores derecha "+valorDerecha);
                    int maximo = calcularMaximo(valorDerecha);
                    int minimo = calcularMinimo(valorDerecha);
                    int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
                    asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
                    System.out.println("POSICION "+posicion+" valoDerecha"+valorDerecha+" cantidadDisponible"+cantidadDisponibleDerecha);
                    listaCeldas.get(posicion).asignarCeldaSuperior(valorEntero); 
                    posicion = (posicion+valorDerecha)+1;
                }
                else{
                    asignarNegrosHorizontales(posicion,cantidadDisponibleDerecha);
                    posicion=posicion+1;
                }
            }
            else{
                listaCeldas.get(posicion).asignarCeldaNegro();
                posicion=posicion+1;
            }
        }
    }
//    public int calcularPosiblesEnfila(int fila){
//        int celda = 0;
//        int puntero = fila;
//        TipoCelda tipo = TipoCelda.BLANCO;
//        while((tipo == TipoCelda.BLANCO)||(tipo == TipoCelda.NEUTRO)&&(celda<9)){
//            puntero+=1;
//            tipo = listaCeldas.get(puntero).getTipocelda();
//            if((tipo == TipoCelda.BLANCO)||(tipo == TipoCelda.NEUTRO)){
//                celda+=1;
//            }
//        }
//        return celda;
//    }
    
//    public int calcularPosiblesEnfilaAbajo(int fila){
//        int celda = 0;
//        int puntero = fila;
//        int maximoValor = dimension*dimension;
//        TipoCelda tipo = TipoCelda.BLANCO;
//        while(((tipo == TipoCelda.BLANCO)||(tipo == TipoCelda.NEUTRO))&&(celda<9)&&(puntero<maximoValor)){
//            puntero = puntero + dimension;
//            tipo = listaCeldas.get(puntero).getTipocelda();
//            if((tipo == TipoCelda.BLANCO)||(tipo == TipoCelda.NEUTRO)){
//                celda+=1;
//            }
//
//        }
//        return celda;
//    }

    public int revisarProximoNulo(int posicion,int numero){
        int celda = numero;
        int puntero = posicion;
        TipoCelda tipo = TipoCelda.BLANCO;
        while((celda<9)&&(tipo == TipoCelda.BLANCO)){
            puntero+=1;
            tipo = listaCeldas.get(puntero).getTipocelda(); 
            if(tipo == TipoCelda.BLANCO){
                celda+=1;
            }
        }
        return celda;
    }
    
    public void asignarBarraSuperior(){
        String [] arr = {"Negro", "CeldaInferior"};
        listaCeldas.get(0).asignarCeldaNegro();
        for(int posicion = 1 ; posicion < dimension; posicion++){
            Random random = new Random();
            int select = random.nextInt(arr.length);
            if("CeldaInferior".equals(arr[select])){
                //int numero = (int) (Math.random() * 7) + 2;
                int numeroMaximo = calcularAbajo(posicion);
                if(numeroMaximo >= 2){
                    int numeroMinimo = 2;
                    int numero = (int) Math.floor(Math.random()*(numeroMaximo-numeroMinimo+1)+numeroMinimo);
                    if(numero != 9){
                        numero = numero + revisarCruce(numero,posicion,9);
                    }
                    int maximo = calcularMaximo(numero);
                    int minimo = calcularMinimo(numero);
                    int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
                    listaCeldas.get(posicion).asignarCeldaInferior(valorEntero);
                    asignarBlancosVerticales(posicion,numero);
                }
                else{
                    listaCeldas.get(posicion).asignarCeldaNegro();
                    int posicionSiguiente = posicion + dimension;
                    listaCeldas.get(posicionSiguiente).asignarCeldaNegro();
                }

            }
            else{
                listaCeldas.get(posicion).asignarCeldaNegro();
            }
        }
    }
    
    public void asignarCentrosLibres(){
        //int indice = dimension;
        System.out.println("Dimension "+dimension);
        TipoCelda tipo;
        int recorrido = ((dimension * dimension)-1);
        recorrido = recorrido-dimension;
        System.out.println("El recorrido llega hasta "+recorrido);
        for(int x=15; x <= recorrido; x++){
            //indice= indice+1;
            System.out.println("indice "+x);
            tipo = listaCeldas.get(x).getTipocelda();
            if(tipo == TipoCelda.NEUTRO){
                //System.out.println("Encontro Neutro en "+x);
                asignarCentroHorizontales(x);
            }
        }          
    }
    public int revisarCruce(int numero,int posicion, int maximo){
        int contador = 0;
        int signumero = numero+1;
        int fila = listaCeldas.get(posicion).getFilaEnMatriz();
        int ultimaFila = ((dimension-fila)*dimension)+posicion;
        int posicionFila = (signumero * dimension)+posicion;
        if(signumero <= maximo){
            TipoCelda tipo = listaCeldas.get(posicionFila).getTipocelda();
            while((tipo == TipoCelda.BLANCO) && (signumero <= maximo)){
                contador+=1;
                signumero+=1;
                posicionFila = posicionFila + dimension;
                if(posicionFila <= ultimaFila){
                    tipo = listaCeldas.get(posicionFila).getTipocelda();
                }
            }
        }
        return contador;
    }
    
    public void asignarBlancosVerticales(int posicion,int numero){
        int posicionFila = posicion;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + dimension;
            listaCeldas.get(posicionFila).asignarCeldaBlanco();
        }
        if(numero < 9){
            posicionFila = posicionFila + dimension;
            asignarCentroHorizontales(posicionFila);
        }
    }
    
    public void asignarBlancosHorizontales(int posicion,int numero,int maximo){
        int posicionFila = posicion;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + 1;
            listaCeldas.get(posicionFila).asignarCeldaBlanco();
        }
        if(numero<maximo){
            posicionFila = posicionFila + 1;
            listaCeldas.get(posicionFila).asignarCeldaNegro();
        }
    }
    public void asignarBlancosCentrosVerticales(int posicion,int numero){
        int posicionFila = posicion;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + dimension;
            listaCeldas.get(posicionFila).asignarCeldaBlanco();
        }
        
    }
    
    public void asignarNegrosVerticales(int posicion,int numero){
        int posicionFila = posicion;
        TipoCelda tipo;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + dimension;
            tipo = listaCeldas.get(posicionFila).getTipocelda();
            if(tipo==TipoCelda.NEUTRO){
                listaCeldas.get(posicionFila).asignarCeldaNegro();
            }
        } 
    }
    public void asignarNegrosHorizontales(int posicion,int numero){
        int posicionFila = posicion;
        TipoCelda tipo;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + 1;
            tipo = listaCeldas.get(posicionFila).getTipocelda();
            if(tipo==TipoCelda.NEUTRO){
                listaCeldas.get(posicionFila).asignarCeldaNegro();
            }
        } 
    }
    
    public void asignarCentroHorizontales(int posicion){
        int cantidadDisponibleDerecha = calcularCeldaDerecha(posicion);
        int cantidadDisponibleAbajo = calcularAbajo(posicion);
        if((cantidadDisponibleDerecha > 2) && (cantidadDisponibleAbajo > 2)){
            int N = cantidadDisponibleDerecha;
            int M = 2;
            int valorDerecha = (int) Math.floor(Math.random()*(N-M+1)+M);
            int maximo = calcularMaximo(valorDerecha);
            int minimo = calcularMinimo(valorDerecha);
            int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
            asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
            //
            N = cantidadDisponibleAbajo;
            int valorAbajo = (int) Math.floor(Math.random()*(N-M+1)+M);
            valorAbajo = valorAbajo + revisarCruce(valorAbajo,posicion,cantidadDisponibleAbajo);
            int maximo2 = calcularMaximo(valorAbajo);
            int minimo2 = calcularMinimo(valorAbajo);
            int valorEntero2 = (int) Math.floor(Math.random()*(maximo2-minimo2+1)+minimo2);
            asignarBlancosCentrosVerticales(posicion,valorAbajo);
            listaCeldas.get(posicion).asignarCeldaMixta(valorEntero, valorEntero2); 
        }
        else if((cantidadDisponibleDerecha > 2) && (cantidadDisponibleAbajo < 2)){
            int N = cantidadDisponibleDerecha;
            int M = 2;
            int valorDerecha = (int) Math.floor(Math.random()*(N-M+1)+M);
            int maximo2 = calcularMaximo(valorDerecha);
            int minimo2 = calcularMinimo(valorDerecha);
            int valorEntero2 = (int) Math.floor(Math.random()*(maximo2-minimo2+1)+minimo2);
            asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
            listaCeldas.get(posicion).asignarCeldaSuperior(valorEntero2);
        }
        else if((cantidadDisponibleDerecha < 2) && (cantidadDisponibleAbajo > 2)){
            int N = cantidadDisponibleAbajo;
            int M = 2;
            int valorAbajo= (int) Math.floor(Math.random()*(N-M+1)+M);
            valorAbajo = valorAbajo + revisarCruce(valorAbajo,posicion,cantidadDisponibleAbajo);
            int maximo2 = calcularMaximo(valorAbajo);
            int minimo2 = calcularMinimo(valorAbajo);
            int valorEntero2 = (int) Math.floor(Math.random()*(maximo2-minimo2+1)+minimo2);
            asignarBlancosCentrosVerticales(posicion,valorAbajo);
            listaCeldas.get(posicion).asignarCeldaInferior(valorEntero2);
        }
        else{
            listaCeldas.get(posicion).asignarCeldaNegro();
        }
    }
    
    public int calcularCeldaDerecha(int posicion){
        int contador = 0;
        int filaEnMatriz = listaCeldas.get(posicion).getFilaEnMatriz();
        int finDeLinea = (dimension*filaEnMatriz)-1;
        int valoresPosibles = finDeLinea-posicion;
        if(valoresPosibles >= 5){
            valoresPosibles = 5;
        }
        if(valoresPosibles >= 2){
            posicion = posicion + 1;
            TipoCelda tipo = listaCeldas.get(posicion).getTipocelda();
            while((tipo == TipoCelda.BLANCO || tipo == TipoCelda.NEUTRO) && (contador < valoresPosibles)){
              contador+=1;
              posicion = posicion + 1;
              if(posicion <= finDeLinea){
                  tipo = listaCeldas.get(posicion).getTipocelda();
              }
              else{
                  return contador;
              }
            }
        }
        return contador;
    }
    
    public int calcularAbajo(int posicion){
        int contador = 0;
        int filaEnMatriz = listaCeldas.get(posicion).getFilaEnMatriz();
        int finDeColumna = dimension;
        int ultimaFila = ((dimension-filaEnMatriz)*dimension)+posicion;
        int valoresPosibles = finDeColumna-filaEnMatriz;
        if(valoresPosibles >= 5){
            valoresPosibles = 5;
        }
        if(valoresPosibles >= 2){
            posicion = posicion + dimension;
            TipoCelda tipo = listaCeldas.get(posicion).getTipocelda();
            while((tipo == TipoCelda.BLANCO || tipo == TipoCelda.NEUTRO) && (contador < valoresPosibles)){
              contador+=1;
              posicion = posicion + dimension;
              if(posicion <= ultimaFila){
                  tipo = listaCeldas.get(posicion).getTipocelda();
              }
              else{
                  return contador;
              }
            }
        }
        return contador;
    } 
    public int calcularMaximo(int valor){
        int base = 9;
        int suma = 0;
        for(int x=0;x<valor;x++){
            suma = suma + (base-x);
        }
        return suma;
    }
    
    public int calcularMinimo(int valor){
        int base = 1;
        int suma = 0;
        for(int x = 0;x<valor;x++){
           suma = suma + (base+x); 
        }
        return suma;
    } 
}

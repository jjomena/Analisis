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
                //System.out.println("BarraArriba");
                posicion = posicion.BarraArriba;
            }
            else if(((indice % dimension)==0)&&(indice<ultimaFila)){
                //System.out.println("BarraIzquierda");
                posicion = posicion.BarraIzquierda;
            }
            else if(indice >= ultimaFila){
                //System.out.println("BarraAbajo");
                posicion = posicion.BarraAbajo;
            }
            else if(finDeLinea == indice){
                //System.out.println("BarraDerecha");
                posicion = posicion.BarraDerecha;
            }
            else{
                //System.out.println("BarraCentro");
                posicion = posicion.BarraCampo;
            }
            agregarCelda(TipoCelda.NEUTRO,posicion,indice,punteroLinea);
            recorrido++;
            

        }
         System.out.println("Se generaron correctamente las celdas");
     }
     
     public void agregarCelda(TipoCelda tipo,posicionEnMatriz posicion,int indice, int linea){
         Celda cl = new Celda(tipo,posicion,indice,linea);
         this.listaCeldas.add(cl);  
     }
     
     
     
    public void imprimirCeldas(){
        int cantidadEle = listaCeldas.size();
        System.out.println(String.valueOf(cantidadEle));
        for(int x = 0; x < listaCeldas.size();x++ ){
            System.out.println(listaCeldas.get(x).posicionEnMatriz);
        }
    }
    
    public void asignarBarraIzquierda(){
        String [] arr = {"Negro", "CeldaSuperior"};
    }
    
    public void asignarBarraSuperior(){
        String [] arr = {"Negro", "CeldaInferior"};
        listaCeldas.get(0).asignarCeldaNegro();
        for(int posicion = 1 ; posicion < dimension; posicion++){
            Random random = new Random();
            int select = random.nextInt(arr.length);
            //System.out.println("Random String selected: " + arr[select]);
            if("CeldaInferior".equals(arr[select])){
                int numero = (int) (Math.random() * 7) + 2;
                //System.out.println("El numero previo es: "+numero);
                if(numero != 9){
                    if(revisarCruce(numero,posicion)){
                        numero = numero+1;}
                }
                int maximo = calcularMaximo(numero);
                int minimo = calcularMinimo(numero);
                //System.out.println("Maximo "+maximo);
                //System.out.println("Minimo "+minimo);
                int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
                //System.out.println("Generado "+valorEntero);
                listaCeldas.get(posicion).asignarCeldaInferior(valorEntero);
                asignarBlancosVerticales(posicion,numero);
            }
            else{
                listaCeldas.get(posicion).asignarCeldaNegro();
            }
        }
    }
    
    public boolean revisarCruce(int numero,int posicion){
        int signumero = numero+1;
        int posicionMatriz = (signumero*dimension)+ posicion;
        TipoCelda tipo = listaCeldas.get(posicionMatriz).getTipocelda();
        return tipo == TipoCelda.BLANCO;     
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
    
    public void asignarBlancosHorizontales(int posicion,int numero){
        int posicionFila = posicion;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + 1;
            listaCeldas.get(posicionFila).asignarCeldaBlanco();
        } 
    }
    public void asignarBlancosCentrosVerticales(int posicion,int numero){
        int posicionFila = posicion;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + dimension;
            listaCeldas.get(posicionFila).asignarCeldaBlanco();
        }
    }
    
    public void asignarCentroHorizontales(int posicion){
        int cantidadDisponibleDerecha = calcularCeldaDerecha(posicion);
        int cantidadDisponibleAbajo = calcularAbajo(posicion);
        //System.out.println("Cantidad Disponibles Derecha: "+cantidadDisponibleDerecha);
        //System.out.println("Cantidad Disponibles Abajo: "+cantidadDisponibleAbajo);
        if((cantidadDisponibleDerecha > 2) && (cantidadDisponibleAbajo > 2)){
            int N = cantidadDisponibleDerecha;
            if(N>9){
                N = 9;
            }
            int M = 2;
            int valorDerecha = (int) Math.floor(Math.random()*(N-M+1)+M);
            System.out.println("Se genero un campo mixto Derecho: "+valorDerecha);
            int maximo = calcularMaximo(valorDerecha);
            int minimo = calcularMinimo(valorDerecha);
            System.out.println("Maximo "+maximo);
            System.out.println("Minimo "+minimo);
            int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
            System.out.println("Valor Generado: "+valorEntero);
            asignarBlancosHorizontales(posicion,valorDerecha);
            //System.out.println("Generado "+valorEntero);
            //
            N = cantidadDisponibleAbajo;
            int valorAbajo = (int) Math.floor(Math.random()*(N-M+1)+M);
            //System.out.println("Se genero un campo mixto Abajo: "+valorAbajo);
            int maximo2 = calcularMaximo(valorAbajo);
            int minimo2 = calcularMinimo(valorAbajo);
            //System.out.println("Maximo "+maximo2);
            //System.out.println("Minimo "+minimo2);
            int valorEntero2 = (int) Math.floor(Math.random()*(maximo2-minimo2+1)+minimo2);
            //System.out.println("Generado "+valorEntero2);
            asignarBlancosCentrosVerticales(posicion,valorAbajo);
            listaCeldas.get(posicion).asignarCeldaMixta(valorEntero, valorEntero2); 
        }
        else if((cantidadDisponibleDerecha > 2) && (cantidadDisponibleAbajo < 2)){
            int N = cantidadDisponibleDerecha;
            int M = 2;
            int valorDerecha = (int) Math.floor(Math.random()*(N-M+1)+M);
            asignarBlancosHorizontales(posicion,valorDerecha);
            listaCeldas.get(posicion).asignarCeldaSuperior(valorDerecha);
        }
        else if((cantidadDisponibleDerecha < 2) && (cantidadDisponibleAbajo > 2)){
            int N = cantidadDisponibleAbajo;
            int M = 2;
            int valorAbajo= (int) Math.floor(Math.random()*(N-M+1)+M);
            asignarBlancosCentrosVerticales(posicion,valorAbajo);
            listaCeldas.get(posicion).asignarCeldaInferior(valorAbajo);
        }
        else{
            listaCeldas.get(posicion).asignarCeldaNegro();
        }
    }
    
    public int calcularCeldaDerecha(int posicion){
        int filaEnMatriz = listaCeldas.get(posicion).getFilaEnMatriz();
        int finDeLinea = (dimension * filaEnMatriz)-1;
        int valoresPosibles = finDeLinea-posicion; 
        return valoresPosibles;
    }
    
    public int calcularAbajo(int posicion){
        int filaEnMatriz = listaCeldas.get(posicion).getFilaEnMatriz();
        int diferenciaLineas = dimension-filaEnMatriz;
        int finDeLinea = diferenciaLineas * dimension;
        int valoresPosibles = (finDeLinea/dimension);
        System.out.println("Valores Posibles Abajo: "+valoresPosibles);
        return valoresPosibles;
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
    
    
     
      
     public void asignarCeldas(){
         posicionEnMatriz posicionEnMatriz;
          String [] arr = {"Negro", "CeldaInferior"};
          String [] arr2 = {"Negro", "CeldaSuperior"};
          String [] arr3 = {"Blanco", "CeldaMixta","CeldaInferior","CeldaSuperior","Negro"};
         for(int posicion = 0;posicion < listaCeldas.size(); posicion++){
             posicionEnMatriz = listaCeldas.get(posicion).getPosicionEnMatriz();
             if(null == posicionEnMatriz){
                 //System.out.println("CENTRO");
             }
             else switch (posicionEnMatriz) {
                 case BarraArriba:
                     {
                         Random random = new Random();
                         int select = random.nextInt(arr.length);
                         //System.out.println("Random String selected: " + arr[select]);
                         if("CeldaInferior".equals(arr[select])){
                             listaCeldas.get(posicion).asignarCeldaInferior(0);
                         }
                         break;
                     }
                 case BarraIzquierda:
                     {
                         Random random = new Random();
                         int select = random.nextInt(arr2.length);
                         //System.out.println("Random String selected: " + arr2[select]);
                         if("CeldaSuperior".equals(arr2[select])){
                             listaCeldas.get(posicion).asignarCeldaSuperior(0);
                         }
                         break;
                     }
                 case BarraDerecha:
                     {
                         Random random = new Random();
                         int select = random.nextInt(arr.length);
                         //System.out.println("Random String selected: " + arr[select]);
                         if("CeldaInferior".equals(arr[select])){
                             listaCeldas.get(posicion).asignarCeldaInferior(0);
                         }
                         break;
                     }
                 case BarraAbajo:
                     {
                         Random random = new Random();
                         int select = random.nextInt(arr2.length);
                         //System.out.println("Random String selected: " + arr2[select]);
                         if("CeldaSuperior".equals(arr2[select])){
                             listaCeldas.get(posicion).asignarCeldaSuperior(0);
                         }
                         break;
                     }
                 default:
                     //System.out.println("CENTRO");
                     Random random = new Random();
                     int select = random.nextInt(arr3.length);
                     //System.out.println("Random String selected: " + arr3[select]);
                     if ("CeldaInferior".equals(arr3[select])){
                         listaCeldas.get(posicion).asignarCeldaInferior(0);
                     }
                     else if("CeldaSuperior".equals(arr3[select])){
                         listaCeldas.get(posicion).asignarCeldaSuperior(0);
                     }
                     else if("CeldaMixta".equals(arr3[select])){
                         listaCeldas.get(posicion).asignarCeldaMixta(0, 0);
                     }
                     else if("Blanco".equals(arr3[select])){
                         listaCeldas.get(posicion).asignarCeldaBlanco();
                     }
                     else{
                         listaCeldas.get(posicion).asignarCeldaNeutro();
                     }
                     
                     break;
             }
         }
     }
    
}

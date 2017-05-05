package kakuro;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Administrador {
    private ArrayList<Celda> listaCeldas;
    private int dimension;
    
    public Administrador(){
        listaCeldas = new ArrayList<Celda>();
    }

    public ArrayList<Celda> getListaCeldas() {
        return listaCeldas;
    }

    public void setListaCeldas(ArrayList<Celda> listaCeldas) {
        this.listaCeldas = listaCeldas;
    }
    
    public void limpiarLista(){
        listaCeldas.clear();
    }
    
    /*Permite generar la matriz inicial,clasificarlo en su posición y generando celdas en estado Neutro*/
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
     /*Agrega las celdas al ArrayList */
     public void agregarCelda(TipoCelda tipo,posicionEnMatriz posicion,int indice, int linea){
         Celda cl = new Celda(tipo,posicion,indice,linea);
         this.listaCeldas.add(cl);  
     }
     
    /*Genera los valores para la Barra Derecha*/
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
                        asignarBlancosCentrosVerticales(fila,numero,cantidadDisponibleAbajo);
                        listaCeldas.get(fila).asignarCeldaInferior(valorEntero,numero); 
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
    /*Genera los valores para la Barra Izquierda*/
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
                    if(numero == 5){
                        numero = 4;
                    }
                    //numero = revisarProximoNulo(fila+numero,numero);
                    int maximo = calcularMaximo(numero);
                    int minimo = calcularMinimo(numero);
                    int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
                    listaCeldas.get(fila).asignarCeldaSuperior(valorEntero,numero);
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
    
    /*Asigna los valores para la Barra Inferior*/
    public void asignarBarraInferior(){
        String [] arr = {"Negro", "CeldaSuperior"};
        int posicion = dimension*(dimension-1);
        int valorFinal = (dimension*dimension)-1;
        while(posicion <= valorFinal){
            Random random = new Random();
            int select = random.nextInt(arr.length);
            if("CeldaSuperior".equals(arr[select])){
                int cantidadDisponibleDerecha = calcularCeldaDerecha(posicion);
                int N = cantidadDisponibleDerecha;
                if(N>2){
                    int M = 2;
                    int valorDerecha = (int) Math.floor(Math.random()*(N-M+1)+M);
                    int maximo = calcularMaximo(valorDerecha);
                    int minimo = calcularMinimo(valorDerecha);
                    int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
                    asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
                    listaCeldas.get(posicion).asignarCeldaSuperior(valorEntero,valorDerecha); 
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
    
    /*Asigna valores para la Barra Superior*/
    public void asignarBarraSuperior(){
        String [] arr = {"Negro", "CeldaInferior"};
        listaCeldas.get(0).asignarCeldaNegro();
        TipoCelda tipo;
        for(int posicion = 1 ; posicion < dimension; posicion++){
            Random random = new Random();
            int select = random.nextInt(arr.length);
            tipo = listaCeldas.get(posicion).getTipocelda();
            if("CeldaInferior".equals(arr[select])){
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
                    listaCeldas.get(posicion).asignarCeldaInferior(valorEntero,numero);
                    //asignarBlancosVerticales(posicion,numero);
                    asignarBlancosCentrosVerticales(posicion,numero,numeroMaximo);
                }
                else{
                    asignarNegrosVerticales(posicion,numeroMaximo);
                }

            }
            else{
                if(tipo==TipoCelda.NEUTRO){
                    listaCeldas.get(posicion).asignarCeldaNegro();
                }
                listaCeldas.get(posicion).asignarCeldaNegro();
            }
        }
    }
    
    /*Asigna Centros horizontales, valores en el centro*/
    public void asignarCentrosLibres(){
        TipoCelda tipo;
        int recorrido = ((dimension * dimension)-1);
        recorrido = recorrido-dimension;
        for(int x=15; x <= recorrido; x++){
            tipo = listaCeldas.get(x).getTipocelda();
            if(tipo == TipoCelda.NEUTRO){
                asignarCentroHorizontales(x);
            }
        }          
    }
    /*Permite revisar si hay blancos adicionales, verticalmente*/
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
    
    /*Permite revisar si hay blancos adicionales, horizontalmente*/
    public int revisarCruceHorizontal(int numero,int posicion,int maximo){
        int contador = 0;
        int signumero = numero+1;
        int fila = listaCeldas.get(posicion).getFilaEnMatriz();
        int ultimaFila = (fila*dimension)-1;
        int posicionFila = posicion+signumero;
        if(signumero <= maximo){
            TipoCelda tipo = listaCeldas.get(posicionFila).getTipocelda();
            while((tipo == TipoCelda.BLANCO) && (signumero <= maximo)){
                contador+=1;
                signumero+=1;
                posicionFila = posicionFila + 1;
                if(posicionFila <= ultimaFila){
                    tipo = listaCeldas.get(posicionFila).getTipocelda();
                }
            }
        }
        return contador;
    }

    /*Asignar celdas horizontales en blanco */
    public void asignarBlancosHorizontales(int posicion,int numero,int maximo){
        int posicionFila = posicion;
        TipoCelda tipo;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + 1;
            listaCeldas.get(posicionFila).asignarCeldaBlanco();
        }
        if(numero<maximo){
            posicionFila = posicionFila + 1;
            tipo = listaCeldas.get(posicionFila).getTipocelda();
            if(tipo==TipoCelda.NEUTRO){
                listaCeldas.get(posicionFila).asignarCeldaNegro();
            }
        }
    }
    /*Permite asignar las celdas enblanco para las celdas centrales*/
    public void asignarBlancosCentrosVerticales(int posicion,int numero,int maximo){
        int posicionFila = posicion;
        TipoCelda tipo;
        for(int x = 0; x<numero; x++){
            posicionFila = posicionFila + dimension;
            listaCeldas.get(posicionFila).asignarCeldaBlanco();
        }
        if(numero<maximo){
            posicionFila = posicionFila + dimension;
            tipo = listaCeldas.get(posicionFila).getTipocelda();
            if(tipo==TipoCelda.NEUTRO){
                listaCeldas.get(posicionFila).asignarCeldaNegro();
            }
        }
    }
    
    /*Permite asignar Celdas en Negro Verticales*/
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
    /*Permite asignar Celdas enNegro Horizontales*/
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
    
    /*Permite asignar las celdas centrales*/
    public void asignarCentroHorizontales(int posicion){
        int cantidadDisponibleDerecha = calcularCeldaDerecha(posicion);
        int cantidadDisponibleAbajo = calcularAbajo(posicion);
        if((cantidadDisponibleDerecha > 2) && (cantidadDisponibleAbajo > 2)){
            int N = cantidadDisponibleDerecha;
            int M = 2;
            int valorDerecha = (int) Math.floor(Math.random()*(N-M+1)+M);
            valorDerecha = valorDerecha + revisarCruceHorizontal(valorDerecha,posicion,cantidadDisponibleDerecha);
            int maximo = calcularMaximo(valorDerecha);
            int minimo = calcularMinimo(valorDerecha);
            int valorEntero = (int) Math.floor(Math.random()*(maximo-minimo+1)+minimo);
            //asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
            //
            N = cantidadDisponibleAbajo;
            int valorAbajo = (int) Math.floor(Math.random()*(N-M+1)+M);
            valorAbajo = valorAbajo + revisarCruce(valorAbajo,posicion,cantidadDisponibleAbajo);
            int maximo2 = calcularMaximo(valorAbajo);
            int minimo2 = calcularMinimo(valorAbajo);
            int valorEntero2 = (int) Math.floor(Math.random()*(maximo2-minimo2+1)+minimo2);
            //asignarBlancosCentrosVerticales(posicion,valorAbajo,cantidadDisponibleAbajo);
            if((valorDerecha < 9) && (valorAbajo < 9) && (valorDerecha<cantidadDisponibleDerecha) && (valorAbajo < cantidadDisponibleAbajo)){
                asignarBlancosCentrosVerticales(posicion,valorAbajo,cantidadDisponibleAbajo);
                asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
                listaCeldas.get(posicion).asignarCeldaMixta(valorEntero, valorEntero2,valorDerecha,valorAbajo);
            }
            else if((valorDerecha < 9) && (valorAbajo < 9) &&(valorDerecha<cantidadDisponibleDerecha)){
                asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
                listaCeldas.get(posicion).asignarCeldaSuperior(valorEntero,valorDerecha);
            }
            else if((valorDerecha < 9) && (valorAbajo < 9) && (valorAbajo < cantidadDisponibleAbajo)){
                asignarBlancosCentrosVerticales(posicion,valorAbajo,cantidadDisponibleAbajo);
                listaCeldas.get(posicion).asignarCeldaInferior(valorEntero2,valorAbajo);
            }
            else if((valorDerecha < 9) && (valorAbajo > 9) && (valorDerecha<cantidadDisponibleDerecha)){
                asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
                listaCeldas.get(posicion).asignarCeldaSuperior(valorEntero,valorDerecha);
            }
            else if((valorDerecha > 9)&&(valorAbajo <9)&& (valorAbajo < cantidadDisponibleAbajo)){
                asignarBlancosCentrosVerticales(posicion,valorAbajo,cantidadDisponibleAbajo);
                listaCeldas.get(posicion).asignarCeldaInferior(valorEntero2,valorAbajo);
            }
            else{
                listaCeldas.get(posicion).asignarCeldaNegro();
            }
             
        }
        else if((cantidadDisponibleDerecha > 2) && (cantidadDisponibleAbajo < 2)){
            int N = cantidadDisponibleDerecha;
            int M = 2;
            int valorDerecha = (int) Math.floor(Math.random()*(N-M+1)+M);
            valorDerecha = valorDerecha + revisarCruceHorizontal(valorDerecha,posicion,cantidadDisponibleDerecha);
            if(valorDerecha > 9 || valorDerecha >= cantidadDisponibleDerecha){
                listaCeldas.get(posicion).asignarCeldaNegro();
            }else{
                int maximo2 = calcularMaximo(valorDerecha);
                int minimo2 = calcularMinimo(valorDerecha);
                int valorEntero2 = (int) Math.floor(Math.random()*(maximo2-minimo2+1)+minimo2);
                asignarBlancosHorizontales(posicion,valorDerecha,cantidadDisponibleDerecha);
                listaCeldas.get(posicion).asignarCeldaSuperior(valorEntero2,valorDerecha);
            }

        }
        else if((cantidadDisponibleDerecha < 2) && (cantidadDisponibleAbajo > 2)){
            int N = cantidadDisponibleAbajo;
            int M = 2;
            int valorAbajo= (int) Math.floor(Math.random()*(N-M+1)+M);
            valorAbajo = valorAbajo + revisarCruce(valorAbajo,posicion,cantidadDisponibleAbajo);
            if(valorAbajo > 9 || valorAbajo >= cantidadDisponibleAbajo){
                listaCeldas.get(posicion).asignarCeldaNegro();
            }
            else{
                int maximo2 = calcularMaximo(valorAbajo);
                int minimo2 = calcularMinimo(valorAbajo);
                int valorEntero2 = (int) Math.floor(Math.random()*(maximo2-minimo2+1)+minimo2);
                asignarBlancosCentrosVerticales(posicion,valorAbajo,cantidadDisponibleAbajo);
                listaCeldas.get(posicion).asignarCeldaInferior(valorEntero2,valorAbajo);
            }
        }
        else{
            listaCeldas.get(posicion).asignarCeldaNegro();
        }
    }
    
    /*Permite calcular celdas disponibles a la Derecha*/
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
    
    /*Permite calcular celdas disponibles hacia abajo*/
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
    
    /*Valor maximo que puede tomar una celda segun el numero de espacios blancos*/
    public int calcularMaximo(int valor){
        int base = 9;
        int suma = 0;
        for(int x=0;x<valor;x++){
            suma = suma + (base-x);
        }
        return suma;
    }
    
    /*Valor minimo que puede tomar una celda segun el numero de espacios blancos */
    public int calcularMinimo(int valor){
        int base = 1;
        int suma = 0;
        for(int x = 0;x<valor;x++){
           suma = suma + (base+x); 
        }
        return suma;
    } 
}

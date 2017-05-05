/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mailon2
 */
public class kakuroSolucionador {
    private Matriz matriz;
    private ArrayList<int[]> arreglo = new ArrayList();
    public static int aax=0;
    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public ArrayList<int[]> getArreglo() {
        return arreglo;
    }

    public void setArreglo(ArrayList<int[]> arreglo) {
        this.arreglo = arreglo;
    }
    
    public static int suma(int[]fila){
        int result = 0;
        for(int i=0;i<fila.length;i++){
            result += fila[i];
        }
        return result;
    }
    public static boolean existeElemento(int[]arreglo,int elem){
        for(int i=0;i<arreglo.length;i++){
            if(arreglo[i]==elem){
                return true;
            }
        }
        return false;
    }
    public static int posicion(int[]arreglo,int elem){
        for(int i=0;i<arreglo.length;i++){
            if(arreglo[i]==elem){
                return i;
            }
        }
        return -1;
    }
    
    public static boolean ceros(int[]arreglo){
        for(int i=0;i<arreglo.length;i++){
            if(arreglo[i]==0){
                return true;
            }
        }
        return false;
    }
    
    public static void imprimir(int[]arreglo){
        String cadena = "";
        for(int i=0;i<arreglo.length;i++){
            cadena += arreglo[i]+" ";
        }
        System.out.println(cadena);
    }

    
    public static void imprimirArrayList(ArrayList<int[]>resultado){
        for(int i=0;i<resultado.size();i++){
            imprimir(resultado.get(i));
        }
    }
        
    public static void backNum(int[]result,int numero,ArrayList<int[]>resultado,int k){
        int[]resultAux = (int[])(result.clone());
        if(suma(resultAux)==numero && ceros(resultAux)!=true){
            resultado.add(resultAux);
        }else{
            if(k<result.length){
                int k1 = k+1;
                for(int i=1;i<=9;i++){
                    if(existeElemento(resultAux,i)!=true && i<numero){
                        resultAux[k]=i;
                        backNum(resultAux,numero,resultado,k1);
                    }
                }
            }
        }
    }
    
        public static boolean filaDer(Matriz m,int numero,int fila,int columna){
        for(int i=columna;i<m.getTamano();i++){
            if(fila<m.getTamano()){
               Celdaa celda = m.getCelda()[fila][i];
                if("t1".equals(celda.getTipo()) || "t3".equals(celda.getTipo())){
                    return false;
                }else{
                    if(celda.getValor()==numero){
                    return true;
                    }
                } 
            }
            
        }
        return false;
    }
    
    public static boolean filaIzq(Matriz m,int numero,int fila,int columna){
        for(int i=columna;i>0;i--){
            //System.out.println(fila+" "+i);
            if(i<m.getTamano()&&fila<m.getTamano()){
                Celdaa celda = m.getCelda()[fila][i];
                if("t1".equals(celda.getTipo()) || "t3".equals(celda.getTipo())){
                    return false;
                }else{
                    if(celda.getValor()==numero){
                    return true;
                    }
                } 
            }
            
        }
        return false;
    }
    
    public static boolean columnaAba(Matriz m,int numero,int fila,int columna){
        for(int i=fila;i<m.getTamano();i++){
            if(columna<m.getTamano()){
                Celdaa celda = m.getCelda()[i][columna];
                if("t1".equals(celda.getTipo()) || "t3".equals(celda.getTipo())){
                    return false;
                }else{
                    if(celda.getValor()==numero){
                        return true;
                    }
                }
            }
            
        }
        return false;
    }
    
    public static boolean columnaArr(Matriz m,int numero,int fila,int columna){
        for(int i=fila;i>0;i--){
            if(columna<m.getTamano() && i<m.getTamano()){
                 Celdaa celda = m.getCelda()[i][columna];
                if("t1".equals(celda.getTipo()) || "t3".equals(celda.getTipo())){           
                    return false;
                }else{
                    if(celda.getValor()==numero){
                    return true;
                    }
                }
            }
           
        }
        return false;
    }
    
    /**
     * Metodo encargado de retorna la fila con menos espacio mayor que uno
     * @param m
     * @return 
     */
    public static int[]menorFilas(Matriz m){
        int []result = new int[3];
        for(int i=0;i<m.getTamano();i++){
            int contador = 0;
            int fila = i;
            for(int j=0;j<m.getTamano();j++){
                if("t2".equals(m.getCelda()[i][j].getTipo())){
                    contador++;
                }else{
                    if(contador!=0 && contador!=1){
                        if((result[0]>contador  || result[0]==0) && tieneDerecha(m,fila,j-contador)==false){                          
                            result[0]=contador;
                            result[1]=fila;
                            result[2]=j-contador;
                            contador= 0;
                        }else{
                            contador =0;
                        }
                    }
                }
            }
        }       
        //imprimir(result);
        return result;
    }
    
    public static boolean tieneDerecha(Matriz m,int fila,int columna){
        
        Celdaa celda = m.getCelda()[fila][columna-1];
        if(celda.getDerecha() ==-1){
            return true;
        }
        return false;
    }
    
    public static int cantidadLista(Matriz m){
        int contador = 0;
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                if(!"t2".equals(m.getCelda()[i][j].getTipo())){
                    contador++;
                }
            }
        }
        
        return contador;
    }
    
    public static boolean correctoVector(Matriz m,int[] permutacion,int fila,int columna){
        for(int i=0;i<permutacion.length;i++){
            if(columnaAba(m,permutacion[i],fila,columna+i)==true || columnaArr(m,permutacion[i],fila,columna+i) == true
                    || filaDer(m,permutacion[i],fila,columna+i) == true || filaIzq(m,permutacion[i],fila,columna+i)==true){
                return true;
        }
        }
        return false;
    }
    
    public static boolean vectorColumna(Matriz m,int[] permutacion,int fila,int columna){
        //System.out.println("Fila: "+fila+" columna:"+columna);
        for(int i=0;i<permutacion.length;i++){
            if(filaDer(m,permutacion[i],fila,columna+i) == true || filaIzq(m,permutacion[i],fila,columna+i)==true){
                return true;
        }
        }
        return false;
    }
    
    
    public static boolean vectorFila(Matriz m,int[] permutacion,int fila,int columna){
        for(int i=0;i<permutacion.length;i++){
            if(columnaAba(m,permutacion[i],fila,columna+i)==true || columnaArr(m,permutacion[i],fila,columna+i) == true){
                return true;
        }
        }
        return false;
    }
    
    public static int valorAbajo(Matriz m,int fila,int columna){
        for(int i=fila;i>=0;i--){
            if("t3".equals(m.getCelda()[i][columna].getTipo())){
                int numero = m.getCelda()[i][columna].getAbajo();
                return numero;
            }
        }
        return -1;
    }
    
    public static int lugarFila(Matriz m,int fila,int columna){
        for(int i=fila;i>=0;i--){
            if("t3".equals(m.getCelda()[i][columna].getTipo())){
                return i;
            }
        }
        return -1;
    }
    public static boolean columnaCorrecto(Matriz m,int[] permutacion,int fila,int columna){
        for(int i=0;i<permutacion.length;i++){
            int numero = valorAbajo(m,fila,columna+i);
            if(numero != -1 && permutacion[i]>numero){
                return true;
            }
        }
        return false;
    }
    
    public static Celdaa[][] clonarM(Matriz m){
        Celdaa[][] celda = new Celdaa[m.getTamano()][m.getTamano()];
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                Celdaa aux = new Celdaa();
                aux.setAbajo(m.getCelda()[i][j].getAbajo());
                aux.setDerecha(m.getCelda()[i][j].getDerecha());
                aux.setTipo(m.getCelda()[i][j].getTipo());
                aux.setValor(m.getCelda()[i][j].getValor());
                aux.setColumna(m.getCelda()[i][j].isColumna());
                aux.setFila(m.getCelda()[i][j].isFila());
                celda[i][j] = aux;
            }
        }
        return celda;
    }
    
    public static Matriz pocasPermutaciones(Matriz m,int fila,int columna,int espacios){
        int result[] = new int[espacios];
        Celdaa[][] celd = new Celdaa[m.getTamano()][m.getTamano()];
        celd = clonarM(m);
        Matriz m1 = new Matriz();
        m1.setMatriz(celd);
        ArrayList<int[]> resultado = new ArrayList<int[]>();
        int numero = m.getCelda()[fila][columna-1].getDerecha();
        backNum(result,numero,resultado,0);
        for(int i=0;i<resultado.size();i++){
            int [] permutacion = resultado.get(i);
            if(correctoVector(m1,permutacion,fila,columna)!=true && columnaCorrecto(m,permutacion,fila,columna)!=true){
                for(int j=0;j<espacios;j++){
                    m.getCelda()[fila][columna+j].setValor(permutacion[j]);
                    m.getCelda()[fila][columna+j].setTipo("t4");
                }
                Matriz m2 = new Matriz();
                Celdaa[][] c1 = clonarM(m);
                m2.setMatriz(c1);
                int res = cantidadLista(m2)+espacios;
                backResolver(m2,res);
            }else{
                //m.imprimirMatriz();
                //imprimir(permutacion);
            }
        }
        return m;
    }
    
    public static boolean solucion(Matriz m){
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                Celdaa celda = m.getCelda()[i][j];
                if("t4".equals(celda.getTipo())){
                    if(columnaAba(m,celda.getValor(),i+1,j)==true || columnaArr(m,celda.getValor(),i-1,j) == true
                    || filaDer(m,celda.getValor(),i,j+1) == true || filaIzq(m,celda.getValor(),i,j-1)==true){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean evaluarColumna(Matriz m,int fila,int columna){
        int numero = m.getMatriz()[fila][columna].getAbajo();
        int result = 0;
        for(int i=1;i<m.getTamano();i++){
            Celdaa celda = m.getCelda()[fila+i][columna];
            if("t4".equals(celda.getTipo())){
                result += celda.getValor();
            }else{
                if(result==numero){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    public static boolean solucionColumnas(Matriz m){
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                Celdaa celda = m.getCelda()[i][j];
                if("t3".equals(celda.getTipo())){
                    if(celda.getAbajo()!=-1){
                        if(evaluarColumna(m,i,j)!=true){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public static int valorColumna(Matriz m,int fila,int columna){
        int result = 0;
        for(int i=1;i<m.getTamano();i++){
            Celdaa celda = m.getCelda()[fila+i][columna];
            if("t4".equals(celda.getTipo())||"t2".equals(celda.getTipo())){
                result += celda.getValor();
            }else{
                return result;
            }
        }
        return -1;
    }
    
    public static boolean cerosColumnas(Matriz m,int fila,int columna){
        for(int i=fila;i<m.getTamano();i++){
            Celdaa celda = m.getCelda()[i][columna];
            if("t3".equals(celda.getTipo())){
                return false;
            }else if("t2".equals(celda.getTipo())){
                return true;
            }
        }
        return false;
    }
    
    public static boolean revisarPermutacion(Matriz m,int[]permutacion,int fila,int columna){
        for(int i=0;i<permutacion.length;i++){
            int valor = valorAbajo(m,fila,columna+i);
            int filaC = lugarFila(m,fila,columna+i);
            int sumaAct = valorColumna(m,filaC,columna);
            sumaAct+=permutacion[i];
            if(cerosColumnas(m,filaC,columna)==true){
                if(sumaAct>valor+3){
                    return true;
                }
            }
            
        }
        return false;
    }
    
    public static Matriz backResolver(Matriz m,int colocadas){
        aax+=1;
        int colocada = cantidadLista(m);
        if(colocada >= m.getDimensiones()){
            if(solucion(m)==false && solucionColumnas(m)==true){
                System.out.println("-");
                m.imprimirMatriz();
            }
            return m;
        }else{
            
            int[]menor = menorFilas(m);
            if(menor[0]>1){
                return pocasPermutaciones(m,menor[1],menor[2],menor[0]);
            }
        }
        return m;
    }
    
    public static boolean filasListas(Matriz m){
        int conta = 0;
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                if("t3".equals(m.getCelda()[i][j].getTipo()) && m.getCelda()[i][j].getDerecha()!=0){
                    if(m.getCelda()[i][j].isFila()==false){
                        return true;
                    }
                    conta++;
                }
            }
        }
        return false;
    }
    
    public static int filasListasN(Matriz m){
        int conta = 0;
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                if("t3".equals(m.getCelda()[i][j].getTipo()) && m.getCelda()[i][j].getDerecha()!=0){
                    if(m.getCelda()[i][j].isFila()==true){
                        conta++;
                    }
                }
            }
        }
        return conta;
    }
    
    
    public static void resolverKakuro(Matriz m,ArrayList soluciones){
        aax+=1;
        int colocada = cantidadLista(m);
        if(colocada >= m.getDimensiones()){
            if(solucion(m)==false&&solucionColumnas(m)==true){
                    System.out.println("solucion");
                    soluciones.add(m); 
            }else{
                //System.out.println("-.-");
            }
        }else{  
            int[]menor = menorFilas(m);
            if(menor[0]>1){
                int fila = menor[1];
                int columna = menor[2];
                int espacios  = menor[0];
                int result[] = new int[espacios];
                Celdaa[][] celd = new Celdaa[m.getTamano()][m.getTamano()];
                celd = clonarM(m);
                Matriz m1 = new Matriz();
                m1.setMatriz(celd);
                ArrayList<int[]> resultado = new ArrayList<int[]>();
                int numero = m.getCelda()[fila][columna-1].getDerecha();
                backNum(result,numero,resultado,0);
                if(resultado.size()==0){
                    System.out.println("Fila: "+fila+" columna: "+columna+" numero:" +numero+" en espacios: "+espacios);
                }
                for(int i=0;i<resultado.size();i++){
                    int [] permutacion = resultado.get(i);
                    if(correctoVector(m1,permutacion,fila,columna)!=true
                            && columnaCorrecto(m,permutacion,fila,columna)!=true && revisarPermutacion(m,permutacion,fila,columna)!=true){
                        for(int j=0;j<espacios;j++){
                            m.getCelda()[fila][columna+j].setValor(permutacion[j]);
                            m.getCelda()[fila][columna+j].setTipo("t4");
                        }
                        Matriz m2 = new Matriz();
                        Celdaa[][] c1 = clonarM(m);
                        m2.setMatriz(c1);
                        resolverKakuro(m2,soluciones);
                    }else{
                    }
                }
                //System.out.println("Resultado: "+resultado.size()+" no admitidas: "+contador+" admitidas: "+contado+" en la fila: "+fila+" columna: "+columna+" valor abajo: "+aa+" valor derecha: "+der+" faltan: "+mt);
            }
            else{
                System.out.println("raios");
            }
        }
    }
    
    public static int[] siguientePermutacion(Matriz m){
        int[] resultado = new int[2];
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                if("t3".equals(m.getCelda()[i][j].getTipo())){
                    if((m.getCelda()[i][j].isFila()==false&&m.getCelda()[i][j].getDerecha()!=-1)
                            || (m.getCelda()[i][j].isColumna()==false && m.getCelda()[i][j].getAbajo()!=-1)){
                        //System.out.println("Fila_ "+i+" columna:"+j);
                        //System.out.println(m.getCelda()[i][j].toString());
                        resultado[0] =i;
                        resultado[1]= j;
                        return resultado;
                    }
                }
            }
        }
        return resultado;
    }
    
    public static int casillasFila(Matriz m,int fila,int columna){
        int resultado = 0;
        for(int i=columna+1;i<m.getTamano();i++){
            Celdaa celd = m.getCelda()[fila][i];
            if("t2".equals(celd.getTipo())||"t4".equals(celd.getTipo())){
                resultado++;
            }else{
                return resultado;
            }
        }
        return resultado;
    }
    
    public static int casillasColumna(Matriz m,int fila,int columna){
        int resultado = 0;
        for(int i=fila+1;i<m.getTamano();i++){
            Celdaa celd = m.getCelda()[i][columna];
            if("t2".equals(celd.getTipo())||"t4".equals(celd.getTipo())){
                resultado++;
            }else{
                return resultado;
            }
        }
        return resultado;
    }
    
    public static boolean cualquierFila(Matriz m,int fila,int columna){
        for(int i=columna;i<m.getTamano();i++){
            Celdaa cel = m.getCelda()[fila-1][columna];
            if("t2".equals(cel.getTipo())||"t4".equals(cel.getTipo())){
                return true;
            }
        }
        return false;
    }
    

    public static boolean comparar(Matriz m,Matriz m1){
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTablero();j++){
                Celdaa celd = m.getCelda()[i][j];
                Celdaa celd1 = m1.getCelda()[i][j];
                if("t3".equals(celd.getTipo())){
                    if(celd.getValor()!=celd1.getValor()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean respuestaRepetida(Matriz m,ArrayList<Matriz> soluciones){
        for(int i=0;i<soluciones.size();i++){
            Matriz m1 = soluciones.get(i);
            if(comparar(m,m1)==true){
                return true;
            }
        }
        return false;
    }
    
                
    
    public static void resolver(Matriz m,ArrayList soluciones){
        int colocada = cantidadLista(m);
        if(colocada >= m.getDimensiones()){
            if(solucion(m)==false&&solucionColumnas(m)==true){
                if(respuestaRepetida(m,soluciones)!=true){
                    System.out.println("solucion: "+soluciones.size());
                    soluciones.add(m); 
                }else{
                    System.out.println("Rechazo string");
                }
            }else{
            }   
        }else{
            int[] casilla = siguientePermutacion(m);
            int fila = casilla[0];
            int columna = casilla[1];
            System.out.println("fila: "+fila+" columna: "+columna+" colocadas:"+colocada);
            int derecha = m.getCelda()[casilla[0]][casilla[1]].getDerecha();
            int abajo = m.getCelda()[casilla[0]][casilla[1]].getAbajo();
            m.getCelda()[fila][columna].setColumna(true);
            m.getCelda()[fila][columna].setFila(true);
            Celdaa[][] celd = new Celdaa[m.getTamano()][m.getTamano()];
            celd = clonarM(m); Matriz m1 = new Matriz(); m1.setMatriz(celd);
            if(derecha!=-1){
                int espacios = casillasFila(m,casilla[0],casilla[1]);
                int result[] = new int[espacios];
                ArrayList<int[]> resultado = new ArrayList<>();
                int numero = m.getCelda()[casilla[0]][casilla[1]].getDerecha();
                backNum(result,numero,resultado,0);
                if(!resultado.isEmpty()){
                    //System.out.println("Derecha: "+"Fila: "+fila+" columna: "+columna+" Numero: "+numero+" espacios: "+espacios+"permuaciones"+resultado.size());
                    for(int i=0;i<resultado.size();i++){
                        int [] permutacion = resultado.get(i);
                        if(vectorColumna(m1,permutacion,fila,columna+1)!=true){// &&cualquierFila(m,fila,columna+1)!=false){ 
                            for(int j=0;j<espacios;j++){
                               // System.out.println("FILA: "+fila+"+"+j+" COLUMNA:"+columna+" PERMUTACION"+permutacion[j]);
                                m.getCelda()[fila][columna+j+1].setValor(permutacion[j]);
                                m.getCelda()[fila][columna+j+1].setTipo("t4");
                            }
                            Matriz m2 = new Matriz(); Celdaa[][] c1 = clonarM(m); m2.setMatriz(c1);
                            resolver(m2,soluciones); 
                        }   
                    }
                    Matriz m2 = new Matriz(); Celdaa[][] c1 = clonarM(m); m2.setMatriz(c1);
                    resolver(m2,soluciones); 
                }
                
            }
            if(abajo!=-1){
                int numero = m.getCelda()[casilla[0]][casilla[1]].getAbajo();
                int espacios = casillasColumna(m,casilla[0],casilla[1]);
                int result[] = new int[espacios];
                ArrayList<int[]> resultado = new ArrayList<>();
                backNum(result,numero,resultado,0);
                if(!resultado.isEmpty()){
                    for(int i=0;i<resultado.size();i++){
                        int [] permutacion = resultado.get(i);
                        if(vectorFila(m1,permutacion,fila+1,columna)!=true){
                            for(int j=0;j<espacios;j++){
                                m.getCelda()[fila+j+1][columna].setValor(permutacion[j]);
                                m.getCelda()[fila+j+1][columna].setTipo("t4");
                            }
                            Matriz m2 = new Matriz(); Celdaa[][] c1 = clonarM(m); m2.setMatriz(c1);
                            resolver(m2,soluciones); 
                        }

                    }
                    Matriz m2 = new Matriz(); Celdaa[][] c1 = clonarM(m); m2.setMatriz(c1);
                    resolver(m2,soluciones); 
                    //System.out.println("ABAJO: "+"Fila: "+fila+"columna: "+columna+" Numero: "+numero+" espacios: "+espacios+" permutaciones:"+resultado.size());
                }
            }
        }
    }
     
    public static int cantidaFila(Matriz m){
        int conta = 0;
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                if("t3".equals(m.getCelda()[i][j].getTipo()) && m.getCelda()[i][j].getDerecha() !=-1){
                    conta++; 
                }
            }
        }
        return conta;
    }
    
    public static int cantidaFilaL(Matriz m){
        int conta = 0;
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                if("t3".equals(m.getCelda()[i][j].getTipo()) && m.getCelda()[i][j].getDerecha() !=-1){
                    if(m.getCelda()[i][j].isFila()==true){
                       conta++; 
                    }
                }
            }
        }
        return conta;
    }
    
    public static int cantidaColum(Matriz m){
        int conta = 0;
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                if("t3".equals(m.getCelda()[i][j].getTipo()) && m.getCelda()[i][j].getAbajo() !=-1){
                    conta++; 
                }
            }
        }
        return conta;
    }
    
    public static int cantidaColumL(Matriz m){
        int conta = 0;
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                if("t3".equals(m.getCelda()[i][j].getTipo()) && m.getCelda()[i][j].getAbajo() !=-1){
                    if(m.getCelda()[i][j].isColumna()==true){
                       conta++; 
                    }
                }
            }
        }
        return conta;
    }
    
    public static int[] siguienteFila(Matriz m){
         int[] resultado = new int[2];
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
                Celdaa celd = m.getCelda()[i][j];
                if("t3".equals(celd.getTipo()) && celd.getDerecha()!=-1 && celd.isFila() == false){
                    resultado[0] = i;
                    resultado[1] = j;
                    return resultado;
                }
            }
        }
        
        return resultado;
    }
   
//    public static int[] siguienteFila(Matriz m){
//        int []result = new int[3];
//        for(int i=0;i<m.getTamano();i++){
//            int contador = 0;
//            int fila = i;
//            for(int j=0;j<m.getTamano();j++){
//                if("t2".equals(m.getCelda()[i][j].getTipo())){
//                    contador++;
//                }else{
//                    if(contador!=0 && contador!=1){
//                        if((result[0]>contador  || result[0]==0) && tieneDerecha(m,fila,j-contador)==false){                          
//                            result[0]=contador;
//                            result[1]=fila;
//                            result[2]=j-contador-1;
//                            contador= 0;
//                        }else{
//                            contador =0;
//                        }
//                    }
//                }
//            }
//        }       
//        //imprimir(result);
//        return result;
//    }
    
    public static int[] siguienteColumna(Matriz m){
        int []result = new int[2];
        for(int i=0;i<m.getTamano();i++){
            for(int j=0;j<m.getTamano();j++){
              Celdaa celd = m.getCelda()[i][j];
                if("t3".equals(celd.getTipo()) && celd.getAbajo()!=-1 && celd.isColumna() == false){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }       
        //imprimir(result);
        return result;
    }
    
    public static Matriz resolverI(Matriz m,ArrayList soluciones){
        int[] coordenadas = siguienteFila(m);
        int fila = coordenadas[0];
        int columna = coordenadas[1];
        int numero = m.getCelda()[fila][columna].getDerecha();
        int espacios = casillasFila(m, fila, columna);
        //System.out.println("Fila: "+fila+" columna: "+columna+" Espacios: "+espacios+" fomar el numero: "+numero+" estado: "+m.getCelda()[fila][columna].isFila());
        int result[] = new int[espacios];
        ArrayList<int[]> resultado = new ArrayList<>();
        backNum(result,numero,resultado,0);
        Celdaa[][] celd = new Celdaa[m.getTamano()][m.getTamano()];
        celd = clonarM(m); Matriz m1 = new Matriz(); m1.setMatriz(celd);
        m.getCelda()[fila][columna].setFila(true);
        for(int i=0;i<resultado.size();i++){
            int [] permutacion = resultado.get(i);
            if(vectorFila(m1,permutacion,fila+1,columna)!=true && columnaCorrecto(m,permutacion,fila,columna)!=true){
                for(int j=0;j<espacios;j++){
                    m.getCelda()[fila][columna+j+1].setValor(permutacion[j]);
                    m.getCelda()[fila][columna+j+1].setTipo("t4");
                }
                Matriz m2 = new Matriz(); Celdaa[][] c1 = clonarM(m); m2.setMatriz(c1);
                reso(m2,soluciones); 
            }
        }
        return null;
    }
    
    public static Matriz resolverC(Matriz m,ArrayList soluciones){
        int[] coordenadas =  siguienteColumna(m);
        int fila = coordenadas[0];
        int columna = coordenadas[1];
        int numero = m.getCelda()[fila][columna].getAbajo();
        int espacios = casillasColumna(m,fila,columna);
        //System.out.println("Fila: "+fila+" columna: "+columna+" Espacios: "+espacios+" fomar el numero: "+numero+" estado: "+m.getCelda()[fila][columna].isFila());
        int result[] = new int[espacios];
        ArrayList<int[]> resultado = new ArrayList<>();
        backNum(result,numero,resultado,0);
        Celdaa[][] celd = new Celdaa[m.getTamano()][m.getTamano()];
        celd = clonarM(m); Matriz m1 = new Matriz(); m1.setMatriz(celd);
        m.getCelda()[fila][columna].setColumna(true);
        for(int i=0;i<resultado.size();i++){
            int[] permutacion = resultado.get(i);
            if(vectorColumna(m1,permutacion,fila+1,columna)!=true){
                for(int j=0;j<permutacion.length;j++){
                    m.getCelda()[fila+j+1][columna].setValor(permutacion[j]);
                    m.getCelda()[fila+j+1][columna].setTipo("t4");
                }
                Matriz m2 = new Matriz(); Celdaa[][] c1 = clonarM(m); m2.setMatriz(c1);
                reso(m2,soluciones); 
            }
        }
        return null;
    }
    
    public static Matriz reso(Matriz m,ArrayList soluciones){
        int colocada = cantidadLista(m);
        //System.out.println(colocada);
        if(colocada >= m.getDimensiones()){
            if(solucion(m)==false&&solucionColumnas(m)==true){
                System.out.println("solucion:");
                soluciones.add(m);
            }else{
                System.out.println("-.-");
            }  
        }else{
            int totalFila = cantidaColum(m);
            int momento = cantidaColumL(m);
            //System.out.println(momento);
            if(momento<totalFila){
                return resolverC(m,soluciones);
            }else{
                System.out.println("Al parecer la filas estan listas");
                return resolverI(m, soluciones);
            }
        }
        return null;
    }
    
    
}

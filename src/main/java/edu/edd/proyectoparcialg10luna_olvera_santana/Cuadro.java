/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.edd.proyectoparcialg10luna_olvera_santana;
import java.util.*;

/**
 * Ejemplo de rellenar cuadro paint con backtracking usando una cola
 * 
 * @author miguel
 * @version 3.0
 */
public class Cuadro {
    /**
     * Clase interna para representar un Punto (fila, columna).
     * 
     */
    private class Punto {
        private int fila;
        private int col;
        
        public Punto (int fila, int col){
            this.fila = fila;
            this.col = col;
        }
        public int getFila (){
            return fila;
        }
        public int getCol (){
            return col;
        }  
        @SuppressWarnings("unused")
        public void setFila (int fila){
            this.fila = fila;
        }
        @SuppressWarnings("unused")
        public void setCol (int col){
            this.col = col;
        }

        @Override
        public String toString(){
            return "("+fila+", "+col+" )";
        }
    }

    /**
     * Matriz 2D que representa al cuadro en paint
     */
    private int[][] matriz;
    
    /**
     * Constructor que crea una nueva matriz 2D por defecto.
     * 
     */
    public Cuadro(){
        matriz = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 0, 0},
        {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 0, 0},
        {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 0},
        {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 0},
        {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2},
        {0, 0, 0, 3, 0, 0, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2},
        {3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2},
        {0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 2, 2, 2, 2, 2, 2, 2},
        {0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 2, 2, 2, 2, 2, 2, 2},
        {0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 2, 2, 2, 2, 2, 2, 2},
        {0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 2, 2, 2, 2, 2, 2, 2},
        {0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 2, 2, 2, 2, 2, 2, 2},
        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},        
        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}};
    }
    
    public Cuadro(int[][] matriz){
        this.matriz = matriz;
    }
    
    public int[][] getMatriz() {
        return matriz;
    }
    
    @Override
    public String toString(){
        StringBuilder texto = new StringBuilder();
        for (int[] fila : matriz){
            for (int j = 0; j<fila.length; j++)
                texto.append(fila[j]).append(" ");
            texto.append(System.getProperty("line.separator"));
        }
        return texto.toString();
    }
    
    private boolean izqVacio(Punto a){
        if (a.getCol()==0 )return false;
        return matriz[a.getFila()][a.getCol()-1] <= 0;
    }
    private boolean derVacio(Punto a){
        if (a.getCol()==matriz[0].length-1 )return false;
        return matriz[a.getFila()][a.getCol()+1] <= 0;
    }
    private boolean arrVacio(Punto a){
        if (a.getFila()==0 )return false;
        return matriz[a.getFila()-1][a.getCol()] <= 0;
    }
    private boolean abjVacio(Punto a){
        if (a.getFila()==matriz.length-1 )return false;
        return matriz[a.getFila()+1][a.getCol()] <= 0;
    }
    private boolean vacio(Punto a){
        return matriz[a.fila][a.col] <= 0;
    }
    
    public void pintarCuadro(int fila, int col, int color) {
        Punto p=new Punto(fila,col);
        if(color ==0 || !vacio(p)) return;
        ArrayDeque<Punto> pila = new ArrayDeque<>();
        boolean fin = false;
        while(!fin){
            System.out.println(pila);
            if(vacio(p)){
                matriz[p.fila][p.col]=color;
                pila.push(p);
            }
            if(derVacio(p)){
                p = new Punto(p.fila,p.col+1);
            }else if(arrVacio(p)){
                p = new Punto(p.fila-1,p.col);
            }else if(izqVacio(p)){
                p = new Punto(p.fila,p.col-1);
            }else if(abjVacio(p)){
                p = new Punto(p.fila+1,p.col);
            }else {
                if(!pila.isEmpty()){
                    p = pila.pop();
                }else{fin = true;} 
            }
        }
    }
}
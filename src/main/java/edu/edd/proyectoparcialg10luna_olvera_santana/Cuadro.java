/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.edd.proyectoparcialg10luna_olvera_santana;

import java.io.*;
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

        public Punto(int fila, int col) {
            this.fila = fila;
            this.col = col;
        }

        public int getFila() {
            return fila;
        }

        public int getCol() {
            return col;
        }
//        @SuppressWarnings("unused")
//        public void setFila (int fila){
//            this.fila = fila;
//        }
//        @SuppressWarnings("unused")
//        public void setCol (int col){
//            this.col = col;
//        }

        @Override
        public String toString() {
            return "(" + fila + ", " + col + " )";
        }
    }

    /**
     * Matriz 2D que representa al cuadro en paint
     */
    private int[][] matriz;
    private boolean[][] visitado;

    /**
     * Constructor que crea una nueva matriz 2D por defecto.
     *
     */
    public Cuadro() {
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
//    public Cuadro(int[][] matriz){
//        this.matriz = matriz;
//    }

    public Cuadro(String archivo) throws IOException {
        inicializarDesdeArchivo(archivo);
    }

    private void inicializarDesdeArchivo(String archivo) throws IOException {
        matriz = cargarDesdeArchivo(archivo);
    }

    public int[][] getMatriz() {
        return matriz;
    }

    private int[][] cargarDesdeArchivo(String archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String[] dimensiones = br.readLine().split(",");
            int filas = Integer.parseInt(dimensiones[0]);
            int columnas = Integer.parseInt(dimensiones[1]);
            int[][] nuevaMatriz = new int[filas][columnas];

            for (int i = 0; i < filas; i++) {
                String[] valores = br.readLine().split(",");
                for (int j = 0; j < columnas; j++) {
                    nuevaMatriz[i][j] = Integer.parseInt(valores[j]);
                }
            }
            return nuevaMatriz;
        }
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        for (int[] fila : matriz) {
            for (int j = 0; j < fila.length; j++) {
                texto.append(fila[j]).append(" ");
            }
            texto.append(System.lineSeparator());
        }
        return texto.toString();
    }

    public List<Set<Punto>> detectarClusteres() {
        List<Set<Punto>> clusteres = new ArrayList<>();
        visitado = new boolean[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (!visitado[i][j] && matriz[i][j] != 0) {
                    Set<Punto> cluster = new HashSet<>();
                    explorarCluster(i, j, matriz[i][j], cluster);
                    clusteres.add(cluster);
                }
            }
        }
        return clusteres;
    }

    private void explorarCluster(int fila, int col, int color, Set<Punto> cluster) {
        if (fila < 0 || fila >= matriz.length || col < 0 || col >= matriz[0].length || visitado[fila][col] || matriz[fila][col] != color) {
            return;
        }
        visitado[fila][col] = true;
        cluster.add(new Punto(fila, col));

        explorarCluster(fila + 1, col, color, cluster); // Abajo
        explorarCluster(fila - 1, col, color, cluster); // Arriba
        explorarCluster(fila, col + 1, color, cluster); // Derecha
        explorarCluster(fila, col - 1, color, cluster); // Izquierda
    }

    public void imprimirMatriz() {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    private boolean izqVacio(Punto a) {
        if (a.getCol() == 0) {
            return false;
        }
        return matriz[a.getFila()][a.getCol() - 1] <= 0;
    }

    private boolean derVacio(Punto a) {
        if (a.getCol() == matriz[0].length - 1) {
            return false;
        }
        return matriz[a.getFila()][a.getCol() + 1] <= 0;
    }

    private boolean arrVacio(Punto a) {
        if (a.getFila() == 0) {
            return false;
        }
        return matriz[a.getFila() - 1][a.getCol()] <= 0;
    }

    private boolean abjVacio(Punto a) {
        if (a.getFila() == matriz.length - 1) {
            return false;
        }
        return matriz[a.getFila() + 1][a.getCol()] <= 0;
    }

    private boolean vacio(Punto a) {
        return matriz[a.fila][a.col] <= 0;
    }

    public void pintarCuadro(int fila, int col, int color) {
        Punto p = new Punto(fila, col);
        if (color == 0 || !vacio(p)) {
            return;
        }
        ArrayDeque<Punto> pila = new ArrayDeque<>();
        boolean fin = false;
        while (!fin) {
            System.out.println(pila);
            if (vacio(p)) {
                matriz[p.fila][p.col] = color;
                pila.push(p);
            }
            if (derVacio(p)) {
                p = new Punto(p.fila, p.col + 1);
            } else if (arrVacio(p)) {
                p = new Punto(p.fila - 1, p.col);
            } else if (izqVacio(p)) {
                p = new Punto(p.fila, p.col - 1);
            } else if (abjVacio(p)) {
                p = new Punto(p.fila + 1, p.col);
            } else {
                if (!pila.isEmpty()) {
                    p = pila.pop();
                } else {
                    fin = true;
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.edd.proyectoparcialg10luna_olvera_santana;

import java.io.*;
import java.util.*;

/**
 *
 * @author miguel
 * @version 3.0
 */
public class Cuadro {
 

    public class Punto {
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

        @Override
        public String toString() {
            return "(" + fila + ", " + col + ")";
        }
    }

    private int[][] matriz; 
    private boolean[][] visitado;

    public Cuadro() {
        cargarMatrizDesdeArchivo("resources/Cuadro.txt.txt");
    }

    private void cargarMatrizDesdeArchivo(String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {

        String linea = reader.readLine();
        if (linea != null) {
            // Extraer las dimensiones (filas y columnas)
            String[] dimensiones = linea.split(",");
            int numFilas = Integer.parseInt(dimensiones[0].trim());
            int numColumnas = Integer.parseInt(dimensiones[1].trim());
            
            matriz = new int[numFilas][numColumnas];
            
            int filaActual = 0;
            while ((linea = reader.readLine()) != null && filaActual < numFilas) {
                String[] valores = linea.split(",");
                for (int col = 0; col < valores.length && col < numColumnas; col++) {
                    matriz[filaActual][col] = Integer.parseInt(valores[col].trim());
                }
                filaActual++;
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.err.println("Error al convertir los valores en la matriz: " + e.getMessage());
    }
}

    public void imprimirMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int[][] getMatriz() {
        return matriz;
    }

    public List<Set<Punto>> detectarClusteres() {
        List<Set<Punto>> clusteres = new ArrayList<>();
        visitado = new boolean[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (!visitado[i][j] && matriz[i][j] != -1) {
                    Set<Punto> cluster = explorarClusterConPila(i, j);
                    clusteres.add(cluster);
                }
            }
        }
        clusteres.sort((a, b) -> b.size() - a.size());
        
        return clusteres;
    }

    private Set<Punto> explorarClusterConPila(int fila, int col) {
        int color = matriz[fila][col];
        Set<Punto> cluster = new HashSet<>();
        Stack<Punto> pila = new Stack<>();
        pila.push(new Punto(fila, col));

        while (!pila.isEmpty()) {
            Punto actual = pila.pop();

            int x = actual.getFila();
            int y = actual.getCol();

            if (x < 0 || x >= matriz.length || y < 0 || y >= matriz[0].length || visitado[x][y] || matriz[x][y] != color) {
                continue;
            }

            visitado[x][y] = true;
            cluster.add(actual);

            pila.push(new Punto(x + 1, y)); // Abajo
            pila.push(new Punto(x - 1, y)); // Arriba
            pila.push(new Punto(x, y + 1)); // Derecha
            pila.push(new Punto(x, y - 1)); // Izquierda
        }

        return cluster;
    }

    public void pintarCluster(Set<Punto> cluster, int color) {
        for (Punto p : cluster) {
            matriz[p.getFila()][p.getCol()] = color - 1;
        }
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.edd.proyectoparcialg10luna_olvera_santana;

import java.util.*;

/**
 *
 * @author miguel
 */
public class PintarCuadroTerminal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cuadro cuadro = new Cuadro();

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Mostrar matriz");
            System.out.println("2. Pintar celda");
            System.out.println("3. Detectar clústeres");
            System.out.println("4. Pintar clúster específico");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarMatriz(cuadro);
                    break;

                case 2:
                    pintarCelda(cuadro, scanner);
                    break;

                case 3:
                    detectarClusteres(cuadro);
                    break;

                case 4:
                    pintarCluster(cuadro, scanner);
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static void mostrarMatriz(Cuadro cuadro) {
        System.out.println("\nMatriz actual:");
        cuadro.imprimirMatriz();
    }

    private static void pintarCelda(Cuadro cuadro, Scanner scanner) {
        System.out.print("\nIngrese la fila: ");
        int fila = scanner.nextInt();
        System.out.print("Ingrese la columna: ");
        int col = scanner.nextInt();
        System.out.print("Ingrese el color (número entero): ");
        int color = scanner.nextInt();

        cuadro.pintarCuadro(fila, col, color);
        System.out.println("Celda pintada.");
        mostrarMatriz(cuadro);
    }

    private static void detectarClusteres(Cuadro cuadro) {
        List<Set<Cuadro.Punto>> clusteres = cuadro.detectarClusteres();
        System.out.println("\nClústeres detectados:");
        if (clusteres.isEmpty()) {
            System.out.println("No se detectaron clústeres.");
        } else {
            for (int i = 0; i < clusteres.size(); i++) {
                System.out.println("Clúster " + (i + 1) + ": " + clusteres.get(i));
            }
        }
    }

    private static void pintarCluster(Cuadro cuadro, Scanner scanner) {
        List<Set<Cuadro.Punto>> clusteres = cuadro.detectarClusteres();
        if (clusteres.isEmpty()) {
            System.out.println("No se detectaron clústeres.");
            return;
        }

        System.out.println("\nSeleccione un clúster para pintar:");
        for (int i = 0; i < clusteres.size(); i++) {
            System.out.println((i + 1) + ". Clúster con " + clusteres.get(i).size() + " puntos.");
        }

        System.out.print("Ingrese el número del clúster: ");
        int clusterIndex = scanner.nextInt() - 1;

        if (clusterIndex < 0 || clusterIndex >= clusteres.size()) {
            System.out.println("Índice de clúster inválido.");
            return;
        }

        System.out.print("Ingrese el color (número entero): ");
        int color = scanner.nextInt();

        Set<Cuadro.Punto> clusterSeleccionado = clusteres.get(clusterIndex);
        for (Cuadro.Punto punto : clusterSeleccionado) {
            cuadro.getMatriz()[punto.getFila()][punto.getCol()] = color;
        }

        System.out.println("Clúster pintado.");
        mostrarMatriz(cuadro);
    }
}

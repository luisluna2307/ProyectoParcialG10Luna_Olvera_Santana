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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cuadro cuadro = new Cuadro();
        Stack<Set<Cuadro.Punto>> pilaClusteres = new Stack<>();

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Mostrar matriz");
            System.out.println("2. Detectar clústeres");
            System.out.println("3. Pintar clúster específico");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarMatriz(cuadro);
                    break;

                case 2:
                    detectarClusteres(cuadro, pilaClusteres);
                    break;

                case 3:
                    pintarCluster(cuadro, pilaClusteres, scanner);
                    break;

                case 4:
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

    private static void detectarClusteres(Cuadro cuadro, Stack<Set<Cuadro.Punto>> pilaClusteres) {
        pilaClusteres.clear(); 
        List<Set<Cuadro.Punto>> clusteres = cuadro.detectarClusteres();

        System.out.println("\nClústeres detectados:");
        if (clusteres.isEmpty()) {
            System.out.println("No se detectaron clústeres.");
        } else {
            for (int i = clusteres.size() - 1; i >= 0; i--) {
                pilaClusteres.push(clusteres.get(i)); 
            }

            for (int i = 0; i < clusteres.size(); i++) {
                Set<Cuadro.Punto> cluster = clusteres.get(i);
                System.out.println("Clúster " + (i + 1) + ":");
                System.out.println("  - Tamaño: " + cluster.size());
                System.out.println("  - Color: " + cuadro.getMatriz()[cluster.iterator().next().getFila()][cluster.iterator().next().getCol()]);
                System.out.println("  - Píxeles: " + cluster);
            }
        }
    }

    private static void pintarCluster(Cuadro cuadro, Stack<Set<Cuadro.Punto>> pilaClusteres, Scanner scanner) {
        if (pilaClusteres.isEmpty()) {
            System.out.println("No hay clústeres detectados. Por favor, detecte los clústeres primero.");
            return;
        }

        System.out.println("\nSeleccione un clúster para pintar (de 1 a " + pilaClusteres.size() + "):");
        Stack<Set<Cuadro.Punto>> copiaPila = (Stack<Set<Cuadro.Punto>>) pilaClusteres.clone();

        for (int i = copiaPila.size(); i > 0; i--) {
            System.out.println(i + ". Clúster con " + copiaPila.pop().size() + " puntos.");
        }

        System.out.print("Ingrese el número del clúster: ");
        int clusterIndex = scanner.nextInt();

        if (clusterIndex < 1 || clusterIndex > pilaClusteres.size()) {
            System.out.println("Índice de clúster inválido.");
            return;
        }

        Stack<Set<Cuadro.Punto>> tempStack = new Stack<>();
        Set<Cuadro.Punto> clusterSeleccionado = null;

        for (int i = 0; i < clusterIndex; i++) {
            clusterSeleccionado = pilaClusteres.pop();
            tempStack.push(clusterSeleccionado);
        }

        while (!tempStack.isEmpty()) {
            pilaClusteres.push(tempStack.pop());
        }

        System.out.println("\nSeleccione un color:");
        System.out.println("1. Blanco (0)");
        System.out.println("2. Rojo (1)");
        System.out.println("3. Verde (2)");
        System.out.println("4. Azul (3)");

        System.out.print("Ingrese el número del color: ");
        int colorSeleccionado = scanner.nextInt();

        int color = -1; 
        if (colorSeleccionado == 1) color = 0; 
        else if (colorSeleccionado == 2) color = 1;
        else if (colorSeleccionado == 3) color = 2; 
        else if (colorSeleccionado == 4) color = 3; 
        else {
            System.out.println("Color inválido.");
            return;
        }

        // Pintar el clúster
        cuadro.pintarCluster(clusterSeleccionado, color);
        System.out.println("Clúster pintado con color " + color);
        mostrarMatriz(cuadro);
    }
}
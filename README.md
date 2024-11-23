# **Simulación de Pintura por Clústeres con Backtracking**

## **Descripción**
Este proyecto implementa una simulación para rellenar un cuadro de colores basado en clústeres utilizando estructuras de datos, algoritmos de Backtracking y una interfaz gráfica. El sistema permite cargar una matriz de un archivo de texto, detectar clústeres (agrupaciones de píxeles conectados), ordenarlos por tamaño y pintar cada clúster de forma iterativa, utilizando Backtracking para explorar los píxeles adyacentes.

---

## **Estructura del Proyecto**
```
src/
 ├── edu/
 │    └── edd/
 │         └── proyectoparcialg10luna_olvera_santana/
 │         │   ├── Cuadro.java
 │         │   ├── Cluster.java
 │         │   ├── PintarCuadroTerminal.java
 │         │   └── PintarCuadroGUI.java
resources/
 └── Cuadro.txt
```

### **Archivos y Paquetes**
- **`Cuadro.java`**:
  Contiene la lógica principal para cargar la matriz, detectar clústeres y realizar el relleno.
- **`Cluster.java`**:
  Representa cada clúster detectado, almacenando su color, tamaño y lista de píxeles.
- **`PintarCuadroTerminal.java`**:
  Proporciona una interfaz de consola para probar las funcionalidades principales (cargar matriz, detectar y pintar clústeres).
- **`PintarCuadroGUI.java`**:
  Implementa una interfaz gráfica interactiva para visualizar y pintar los clústeres.
- **`resources/Cuadro.txt`**:
  Archivo de entrada que contiene la matriz inicial de colores.
  
---

## **Funcionalidades**
1. **Carga de Matriz**:
   - Carga un archivo de texto para representar una matriz de colores.
2. **Detección de Clústeres**:
   - Identifica clústeres conectados por color.
3. **Ordenamiento de Clústeres**:
   - Ordena los clústeres por tamaño (descendente) y posición.
4. **Relleno con Backtracking**:
   - Utiliza Backtracking para pintar píxeles conectados del clúster.
5. **Interfaz Gráfica**:
   - Visualiza y controla el proceso de pintura de los clústeres.

---

## **Estructuras y Algoritmos**
1. **Matriz (`int[][]`)**:
   - Representa el cuadro en memoria.
2. **Clúster (`Cluster`)**:
   - Almacena información de los clústeres (color, tamaño, píxeles).
3. **Punto (`Punto`)**:
   - Maneja coordenadas individuales.
4. **Backtracking con Pila (`ArrayDeque`)**:
   - Explora y pinta píxeles conectados.

---

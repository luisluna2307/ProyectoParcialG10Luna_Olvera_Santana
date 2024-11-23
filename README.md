# **Proyecto: Pintura y Detección de Clústeres con Backtracking**

## **Descripción**
Este proyecto simula un sistema para pintar una matriz bidimensional (representación de un cuadro) y detectar regiones conectadas de colores (clústeres) utilizando algoritmos de Backtracking. La interfaz gráfica permite visualizar los resultados, mientras que la terminal ofrece una forma interactiva de probar las funcionalidades básicas.

---

## **Características**
1. **Carga de Matriz**:
   - Desde un archivo (`Cuadro.txt`) o predefinida en el programa.
2. **Detección de Clústeres**:
   - Encuentra regiones conectadas del mismo color.
3. **Pintura**:
   - Cambia el color de celdas individuales o clústeres completos.
4. **Interacción**:
   - Interfaz gráfica (GUI) o terminal (CLI) para interactuar con el sistema.

---

## **Estructura del Proyecto**
```
src/
 ├── edu/
 │    └── edd/
 │         └── proyectoparcialg10luna_olvera_santana/
 │              ├── Cuadro.java
 │              ├── PintarCuadroGUI.java
 │              └── PintarCuadroTerminal.java
resources/
 └── Cuadro.txt
```

### **Archivos y Propósitos**
1. **`Cuadro.java`**:
   - Lógica central del programa:
     - Carga y representación de la matriz.
     - Detección de clústeres.
     - Pintura de celdas o regiones completas.
   - Contiene una clase interna `Punto` para manejar coordenadas.

2. **`PintarCuadroGUI.java`**:
   - Implementa la interfaz gráfica.
   - Permite visualizar la matriz, detectar clústeres y pintar regiones seleccionadas.

3. **`PintarCuadroTerminal.java`**:
   - Ofrece una interfaz de consola para interactuar con la matriz.
   - Menú con opciones para mostrar la matriz, pintar celdas y detectar clústeres.

4. **`Cuadro.txt`**:
   - Archivo de entrada que contiene una representación de la matriz.
   - Ejemplo:
     ```
     6,5
     1,1,0,0,2
     1,1,0,0,2
     1,2,1,1,1
     0,2,2,2,1
     0,3,3,2,2
     0,3,3,2,2
     ```
---

## **Ejemplo de Flujo de Trabajo**
1. **Carga de la Matriz**:
   - El sistema cargará una matriz predefinida o desde `Cuadro.txt`.
2. **Detección de Clústeres**:
   - El usuario puede detectar clústeres y verlos listados en consola o resaltados en la GUI.
3. **Pintura**:
   - Cambia el color de celdas específicas o de clústeres seleccionados.
4. **Salida**:
   - La matriz actualizada se imprime en consola o se visualiza en la GUI.
---

## **Notas**
- El archivo `Cuadro.txt` debe respetar el formato de dimensiones y contenido para evitar errores.

---

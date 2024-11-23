/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.edd.proyectoparcialg10luna_olvera_santana;

/**
 *
 * @author miguel
 */
public class PintarCuadroTerminal {

    /**
     * @param args the command line arguments
     */
    public static void main(String [ ] args){
        Cuadro cuadro =new Cuadro();
        System.out.println(cuadro);          
        cuadro.pintarCuadro(2,2,8);
        cuadro.pintarCuadro(0,10,9);
        cuadro.pintarCuadro(8,4,7);
        System.out.println(" "); 
        System.out.println(cuadro);
    }
}
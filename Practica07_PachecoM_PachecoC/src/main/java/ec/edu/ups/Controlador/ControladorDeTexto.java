/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.Controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xpacheco
 */
public class ControladorDeTexto {
    
    //Declaracion de atributos 
    private String ruta;
    private File Fichero;
    private List<Character> Abecedario;
    private Map<Character, Character> Diccionario;
    
    
    //metodo constructor de ControladorDeTexto
     public ControladorDeTexto() {
        Abecedario = new ArrayList<>();
        Diccionario = new HashMap<>();
        Diccionario = crearDiccionario();
    }
     
     
    //metodo para crear un diccionario
      public Map<Character, Character> crearDiccionario() {
        //Declaracion de nuevas varibles
        String abe = "abcdefghijklmnñopqrstuvwxyz";
        String num = "0123456789";
        String caritas = "☺☻♥♦♣♠•◘○◙♀";
        String espacio = " ";
        int aux = (abe.length() - 1);

        for (int i = 0; i < abe.length(); i++) {
            Diccionario.put(abe.charAt(i), abe.charAt(aux));
            aux--;
        }
         Diccionario.put(espacio.charAt(0), caritas.charAt(10));
        
         /* Almacenamiento del valor en la posision especifiacada y la
         * asosiacion con la clave especificada en el map
         */
         
        Diccionario.put(num.charAt(0), caritas.charAt(0));
        Diccionario.put(num.charAt(1), caritas.charAt(1));
        Diccionario.put(num.charAt(2), caritas.charAt(2));
        Diccionario.put(num.charAt(3), caritas.charAt(3));
        Diccionario.put(num.charAt(4), caritas.charAt(4));
        Diccionario.put(num.charAt(5), caritas.charAt(5));
        Diccionario.put(num.charAt(6), caritas.charAt(6));
        Diccionario.put(num.charAt(7), caritas.charAt(7));
        Diccionario.put(num.charAt(8), caritas.charAt(8));
        Diccionario.put(num.charAt(9), caritas.charAt(9));

        return Diccionario;
        
}
      

    //metodo para la comprobacion de la ruta del archivo
    public boolean comprobar(String ruta, String nombre) {
        nombre = nombre.concat(".txt");
        Fichero = new File(ruta + File.separator + nombre);

        if (Fichero.exists()) {
            return true;
        } else {
            return false;
        }
    }  

    //Metodo para crear un nuevo fichero con el  metodo createNewFile
    public String crearFichero(String ruta, String nombre) {

        nombre = nombre.concat(".txt");
        Fichero = new File(ruta + File.separator + nombre);

        try {
            Fichero.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return Fichero.getAbsolutePath();
    }
    
    
    //metodo que se va a utilizar para la encriptacion del texto 
    public void encriptar(String rutaFinal, String texto) {
        //fichero = new File(rutaAbsoluta);
        //texto = texto.toLowerCase();
        String aux = "";

        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            String le = String.valueOf(letra);
            for (Map.Entry<Character, Character> letra2 : Diccionario.entrySet()) {
                String le2 = String.valueOf(letra2.getKey());

                if (le.equalsIgnoreCase(le2)) {
                    // si la varibale le asignida el dato de tipo String letra es mayuscula nos devolvera un true
                    if (Character.isUpperCase(letra)) {
                        aux = aux.concat(String.valueOf(letra2.getValue()).toUpperCase());
                        System.out.println(aux);
                    } else {
                        aux = aux.concat(String.valueOf(letra2.getValue()));
                        System.out.println(aux);
                    }
                }
            }
        }

        try {
            //Inicializacion  del objeto archivo escritura con un FileWritter
            FileWriter archivoEscritura = new FileWriter(rutaFinal, false);
            //Inicializacion del objeto escritura con un BufferedWriter 
            BufferedWriter escritura = new BufferedWriter(archivoEscritura);

            escritura.append(aux);

            escritura.close();
            archivoEscritura.close();

        } catch (FileNotFoundException ex) {
            System.out.println("La ruta no se ha encontrado o no existe");
        } catch (IOException ex2) {
            System.out.println("Error de escritura");
        } catch (Exception ex3) {
            System.out.println("Error General");
        }
    }

    
    //metodo para comprobar si la ruta existe
      public boolean comprobarRuta(String ruta) {
        Fichero = new File(ruta);

        if (Fichero.exists()) {
            return true;
        } else {
            return false;
        }
    }
}

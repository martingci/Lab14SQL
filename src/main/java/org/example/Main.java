package org.example;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        menu();
    }

    public static void menu() throws SQLException, ClassNotFoundException {
        while (true){
            mostrarOpcionesMenu();
            int opcion = leerOpcionMenu();
            if (opcion == 3){
                break;
            } else {
                ejecutarOpcionMenu(opcion);
            }
        }
        System.out.println("Cerrando programa...");
    }

    public static void ejecutarOpcionMenu(int opcion) throws SQLException, ClassNotFoundException {
        String[] args1 = {};
        if (opcion == 1){
            AddSQL.main(args1);
        } else if (opcion == 2) {
            UpdateSQL.main(args1);
        } else {
            System.out.println();
        }
    }

    public static int leerOpcionMenu(){
        int opcion;
        while(true){
            try{
                System.out.print("Seleccione una opción: ");
                opcion = scanner().nextInt();
                if(opcion >= 1 && opcion <= 3){
                    break;
                } else {
                    System.out.println("Ingrese un número valido.");
                }
            } catch (InputMismatchException e ){
                System.out.println("Ingrese una entrada valida. Intente de nuevo.");
            }
        }
        return opcion;
    }

    public static String pedirString(String mensaje) {
        System.out.print(mensaje);
        String string;
        while (true){
            string = scanner().nextLine();
            if (string.isEmpty()){
                System.out.print("Por favor ingrese una entrada no vacía: ");
            }else{break;}
        }
        return string;
    }

    public static int pedirInt(String mensaje) {
        int value;
        while (true) {
            try {
                System.out.print(mensaje);
                value = scanner().nextInt();
                break;
            } catch(Exception InputMismatchException){
                System.out.print("Entrada no válida. Ingrese un número. "); // excepcion en caso de no ser numero
            }
        }
        return value;
    }

    public static Scanner scanner() {
        return new Scanner(System.in);
    }


    public static void mostrarOpcionesMenu(){
        System.out.println("Tamaño Pizza: ");
        System.out.println("1. Añadir Pizza.");
        System.out.println("2. Modificar Pizza.");
        System.out.println("3. Salir.");
    }
}

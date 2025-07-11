package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UpdateSQL {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Cocina";

    static final String USER = "root";
    static final String PASS = "T19910921t";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        Statement stmt = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        stmt = conn.createStatement(); String sql;
        sql = "SELECT * FROM Pizza";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("Listado de Pizzas:");
        List<Integer> ids = new java.util.ArrayList();
        for (int i = 0; rs.next(); i++) {
            ids.add(rs.getInt("idPizza"));
            System.out.println("ID: " + rs.getInt("idPizza") + ", Nombre: " + rs.getString("nombrePizza") + ", Valor: " + rs.getInt("valorPizza") + ", Tamaño: " + rs.getInt("Tamanho_idTamanho"));
        }

        // UPDATE

        int id_modificar = pedirInt("Ingrese el ID de la pizza que desea modificar: ");
        if (!ids.contains(id_modificar)) {
            System.out.println("ID no encontrado. Por favor, intente de nuevo.");
            return;
        }

        String nuevo_nombre = pedirString("Ingrese el nuevo nombre de la pizza: ");
        int nuevo_precio = pedirInt("Ingrese el nuevo precio de la pizza: ");
        int tamanho = pedirInt("Ingrese el nuevo tamaño de la pizza (1. Pequeña, 2. Mediana, 3. Grande, 4. Extra Grande): ");
        if (tamanho < 1 || tamanho > 4) {
            System.out.println("Tamaño no válido. Por favor, intente de nuevo.");
            return;
        }

        String query = SQLUpdate(id_modificar, nuevo_nombre, nuevo_precio, tamanho);
        PreparedStatement preparedStmt = conn.prepareStatement(query);

        stmt.executeUpdate(query);

        rs.close();
        stmt.close();
        conn.close();
    }

    public static String SQLUpdate(int id, String nombre, int nuevoValor, int nuevoTamanho) {
        return "UPDATE Pizza SET nombrePizza = '" + nombre + "', valorPizza = " + nuevoValor + ", Tamanho_idTamanho = " + nuevoTamanho + " WHERE idPizza = " + id;
    }

    public static void menu(){
        while (true){
            mostrarOpcionesMenu();
            int opcion = leerOpcionMenu();
            if (opcion == 6){
                break;
            } else {
                ejecutarOpcionMenu(opcion);
            }
        }
        System.out.println("Cerrrando programa...");
    }

    public static void ejecutarOpcionMenu(int opcion){
        if (opcion == 1){
            System.out.println();
        } else if (opcion == 2){
            System.out.println();
        } else if (opcion == 3){
            System.out.println();
        } else if (opcion == 4){
            System.out.println();
        } else if (opcion == 5){
            System.out.println();
        }
    }

    public static int leerOpcionMenu(){
        int opcion;
        while(true){
            try{
                System.out.print("Seleccione una opción: ");
                opcion = scanner().nextInt();
                if(opcion >= 1 && opcion <= 6){
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
        System.out.println("1. Modificar Nombre.");
        System.out.println("2. Modificar Precio.");
        System.out.println("3. Modificar Tamaño.");
        System.out.println("4. Salir.");
    }

}



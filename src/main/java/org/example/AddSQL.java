package org.example;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class AddSQL {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Cocina";

    static final String USER = "root";
    static final String PASS = "T19910921t";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Connection conn = null;
        Statement stmt = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);


        stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM Pizza";
        ResultSet rs = stmt.executeQuery(sql);
        List<Integer> ids = new java.util.ArrayList();
        for (int i = 0; rs.next(); i++) {
            ids.add(rs.getInt("idPizza"));
        }
        int new_id = ids.getLast() + 1;

        String nombre = pedirString("Ingrese el nombre de la pizza: ");
        int precio = pedirInt("Ingrese el precio de la pizza: ");
        int tamanho = pedirInt("Ingrese el tamaño de la pizza (1. Pequeña, 2. Mediana, 3. Grande, 4. Extra Grande): ");
        if (tamanho < 1 || tamanho > 4) {
            System.out.println("Tamaño no válido. Por favor, intente de nuevo.");
            return;
        }

        sql = "INSERT INTO Pizza (idPizza, valorPizza, nombrePizza, Tamanho_idTamanho) VALUES (" + new_id + "," + precio + ",'" + nombre + "'" +  "," + tamanho + ")";
        stmt.executeUpdate(sql);

        rs.close();
        stmt.close();
        conn.close();
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

}

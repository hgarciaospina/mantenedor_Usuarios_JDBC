package org.hgarcia.mantenedor.usuarios.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String url = "jdbc:mysql://localhost:3306/mantenedor_usuarios?serverTimeZone=UTC";
    private static final String username = "root";
    private static  final String password = "Leandro2009*";
    private static Connection connection;
    public static Connection getInstance() throws SQLException{
        if(connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public static void cerrarConexion() {
        try {
            if (getInstance() != null && !getInstance().isClosed()) {
                getInstance().close();
                System.out.println("Conexión a la base de datos cerrada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
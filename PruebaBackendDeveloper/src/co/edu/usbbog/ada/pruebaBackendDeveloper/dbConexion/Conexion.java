package co.edu.usbbog.ada.pruebaBackendDeveloper.dbConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SantiagoEcheDuque
 */
public class Conexion {

    private static final String base = "PruebasProducto";
    private static final String usuario = "postgres";
    private static final String contrasenia = "password";
    private static final String url = "jdbc:postgresql://localhost:5432/" + base;
    private static Connection con = null;

    public static Connection getConexion() {

        try {

            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, contrasenia);
//            System.out.println("Base de datos conectado con exito.");

        } catch (SQLException e) {

            System.out.println("ERROR DE CONEXION:" + e.getMessage());
        } catch (ClassNotFoundException e2) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e2);

        }
        return con;
    }

}

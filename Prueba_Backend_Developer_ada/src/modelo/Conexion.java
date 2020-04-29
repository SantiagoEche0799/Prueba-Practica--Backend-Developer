package modelo;

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

    private final String base = "PruebasProducto";
    private final String usuario = "postgres";
    private final String contrasenia = "password";
    private final String url = "jdbc:postgresql://localhost:5432/" + base;
    private Connection con = null;

    public Connection getConexion() {

        try {

            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.usuario, this.contrasenia);
//            System.out.println("Base de datos conectado con exito.");

        } catch (SQLException e) {

            System.out.println("ERROR DE CONEXION:" + e.getMessage());
        } catch (ClassNotFoundException e2) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e2);

        }
        return con;
    }

}

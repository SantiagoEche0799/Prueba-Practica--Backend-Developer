/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.ada.pruebaBackendDeveloper.bo;

import co.edu.usbbog.ada.pruebaBackendDeveloper.dao.Producto_ImplementacionDao;
import co.edu.usbbog.ada.pruebaBackendDeveloper.dbConexion.Conexion;
import co.edu.usbbog.ada.pruebaBackendDeveloper.modelo.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author SantiagoEcheDuque
 */
public class Producto_Bo {

    private String mensaje = "";

    private final Producto_ImplementacionDao productoDao = new Producto_ImplementacionDao();

    public String guardar(Producto producto) {

        Connection conex = Conexion.getConexion();

        try {
            mensaje = productoDao.guardar(conex, producto);
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        } finally {
            try {
                if (conex != null) {
                    conex.close();
                }
            } catch (SQLException e) {
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }

    public String modificar(Producto producto) {

        Connection conex = Conexion.getConexion();

        try {
            mensaje = productoDao.modificar(conex, producto);
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        } finally {
            try {
                if (conex != null) {
                    conex.close();
                }
            } catch (SQLException e) {
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }

    public String eliminar(String codigo) {

        Connection conex = Conexion.getConexion();

        try {
            mensaje = productoDao.eliminar(conex, codigo);
        } catch (Exception e) {
            mensaje = mensaje + " " + e.getMessage();
        } finally {
            try {
                if (conex != null) {
                    conex.close();
                }
            } catch (SQLException e) {
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }

    public void buscarProducto(JTable detalle, String codigo) {

        Connection conex = Conexion.getConexion();

        try {
            productoDao.buscar(conex, codigo, detalle);
            conex.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void actualizarTabla(JTable detalle) {

        Connection conex = Conexion.getConexion();

        try {
            productoDao.actualizarTabla(conex, detalle);
            conex.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public double calcularVentas(JTable detalle) {

        return productoDao.precioUnitario(detalle);
    }
}

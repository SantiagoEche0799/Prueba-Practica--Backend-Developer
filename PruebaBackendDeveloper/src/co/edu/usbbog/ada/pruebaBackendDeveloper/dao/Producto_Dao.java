/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.ada.pruebaBackendDeveloper.dao;

import co.edu.usbbog.ada.pruebaBackendDeveloper.modelo.Producto;
import java.sql.Connection;
import javax.swing.JTable;

/**
 *
 * @author SantiagoEcheDuque
 */
public interface Producto_Dao {
    
    public String guardar (Connection con, Producto producto);
    
    public String modificar(Connection con, Producto producto);
    
    public String eliminar(Connection con, String codigo);
    
    public void buscar(Connection con, String codigo, JTable detalle );
    
    public void actualizarTabla(Connection con, JTable detalle);
    
    public double precioUnitario(JTable detalle);
}

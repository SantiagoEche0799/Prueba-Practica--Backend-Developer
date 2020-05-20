/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.ada.pruebaBackendDeveloper.dao;


import co.edu.usbbog.ada.pruebaBackendDeveloper.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author SantiagoEcheDuque
 */
public class Producto_ImplementacionDao implements Producto_Dao {

    private String mensaje = "";

    @Override
    public String guardar(Connection con, Producto producto) {

        PreparedStatement ps = null;

        String sql = "INSERT INTO Productos (codigo, nombre, marca, "
                + "fecha_Vencimiento, costo, cantidad) VALUES (?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getMarca());
            ps.setString(4, producto.getFechaVenci());
            ps.setDouble(5, producto.getCosto());
            ps.setInt(6, producto.getCantidad());

            mensaje = "PRODUCTO GUARDADO";

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            mensaje = "ERROR AL GUARDAR EL PRODUCTO " + e.getMessage();
        }

        return mensaje;
    }

    @Override
    public String modificar(Connection con, Producto producto) {
        PreparedStatement ps = null;
        String sql = "UPDATE Productos SET nombre=?, marca=?, "
                + "fecha_Vencimiento=?, costo=?, cantidad=? WHERE codigo=?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getMarca());
            ps.setString(3, producto.getFechaVenci());
            ps.setDouble(4, producto.getCosto());
            ps.setInt(5, producto.getCantidad());
            ps.setString(6, producto.getCodigo());

            mensaje = "PRODUCTO MODIFICADO";
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            mensaje = "ERROR AL MODIFICAR EL PRODUCTO";
        }

        return mensaje;
    }

    @Override
    public String eliminar(Connection con, String codigo) {

        PreparedStatement ps = null;
////        String codigo = detalle.getValueAt(detalle.getSelectedRow(),0).toString();
        String sql = "DELETE FROM Productos WHERE codigo=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            mensaje = "PRODUCTO ELIMINADO";
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            mensaje = "ERROR AL ELIMINAR PRODUCTO\n"+ e.getMessage();
        }

        return mensaje;
    }

    @Override
    public void buscar(Connection con, String codigo, JTable detalle) {

        String where = "";
        DefaultTableModel modelo;
        String colum[] = {"Codigo", "Nombre", "Marca", "Fecha de Vencimiento",
            "Costo", "Cantidad"};
        modelo = new DefaultTableModel(null, colum);

        if (!"".equals(codigo)) {
            where = "WHERE codigo = '" + codigo + "'";
        }
        String sql = "SELECT codigo, nombre, marca, fecha_Vencimiento, costo,"
                + "cantidad FROM Productos " + where;

        String filas[] = new String[colum.length];

        Statement s = null;
        ResultSet result = null;

        try {
            s = con.createStatement();
            result = s.executeQuery(sql);

            while (result.next()) {
                for (int i = 0; i < colum.length; i++) {
                    filas[i] = result.getString(i + 1);
                }
                modelo.addRow(filas);
            }
            detalle.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error\nNo se encontro el producto"
                    + " a buscar.");
        }

    }

    @Override
    public void actualizarTabla(Connection con, JTable detalle) {

        DefaultTableModel modelo;
        String colum[] = {"Codigo", "Nombre", "Marca", "Fecha de Vencimiento",
            "Costo", "Cantidad"};
        modelo = new DefaultTableModel(null, colum);

        String sql = "SELECT codigo, nombre, marca, fecha_Vencimiento, costo,"
                + "cantidad FROM Productos";

        String filas[] = new String[colum.length];

        Statement s = null;
        ResultSet result = null;

        try {
            s = con.createStatement();
            result = s.executeQuery(sql);

            while (result.next()) {
                for (int i = 0; i < colum.length; i++) {
                    filas[i] = result.getString(i + 1);
                }
                modelo.addRow(filas);
            }
            detalle.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error\nNo se pueden mostrar los"
                    + " productos");
        }

    }

    @Override
    public double precioUnitario(JTable detalle) {
        double ventaTotal = 0, precioProducto, cantidad, costo;
        ArrayList<Double> sumaPrecios = new ArrayList();
        int totalRow = detalle.getRowCount();
        totalRow -= 1;

        for (int i = 0; i <= (totalRow); i++) {
            cantidad = Integer.parseInt(String.valueOf(detalle.getValueAt(i, 5)));
            costo = Double.parseDouble(String.valueOf(detalle.getValueAt(i, 4)));
            precioProducto = cantidad * costo;
            sumaPrecios.add(i, precioProducto);
            ventaTotal += sumaPrecios.get(i);
        }
        
        return Math.round(ventaTotal);
    }

}

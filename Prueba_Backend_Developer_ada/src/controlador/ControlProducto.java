/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Producto;
import vista.frmProductos;

/**
 *
 * @author SantiagoEcheDuque
 */
public class ControlProducto implements ActionListener {

    private Producto mod;
    private frmProductos vista;

    public ControlProducto(Producto mod, frmProductos vista) {
        this.mod = mod;
        this.vista = vista;
        this.vista.jButton_BucarCodigo.addActionListener(this);
        this.vista.jButton_Refresh.addActionListener(this);
        this.vista.jButton_Refresh.addActionListener(this);
        this.vista.jButton_Eliminar.addActionListener(this);
        this.vista.jButton_Guardar.addActionListener(this);
        this.vista.jButton_LimpiarRegistro.addActionListener(this);
        this.vista.jButton_Modificar.addActionListener(this);
        this.vista.jButton_TotalVen.addActionListener(this);

    }

    DefaultTableModel modelo = new DefaultTableModel();

    public void inciar() {
        vista.setTitle("PRODUCTO");
        vista.setLocationRelativeTo(null);
        vista.jTextField_Id.setVisible(false);

    }

    public void actualizarTabla() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            vista.jTable_Productos.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            String sql = "SELECT codigo, nombre, marca, fecha_Vencimiento, costo,"
                    + "cantidad FROM Productos";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Código");
            modelo.addColumn("Nombre");
            modelo.addColumn("Marca");
            modelo.addColumn("Fecha de Vencimiento");
            modelo.addColumn("Costo");
            modelo.addColumn("Cantidad");
            int[] anchos = {50, 100, 100, 100, 100, 100};
            for (int i = 0; i < anchos.length; i++) {
                vista.jTable_Productos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        /**
         * ACCION QUE VA A REALIZAR EL BOTON DE BUSQUEDA
         */
        if (event.getSource() == vista.jButton_BucarCodigo) {

            String campo = vista.jTextField_Codigo.getText();
            String where = "";
            if (!"".equals(campo)) {
                where = "WHERE codigo = '" + campo + "'";
            }

            try {
                DefaultTableModel modelo = new DefaultTableModel();
                vista.jTable_Productos.setModel(modelo);
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();

                String sql = "SELECT codigo, nombre, marca, fecha_Vencimiento, costo,"
                        + "cantidad FROM Productos " + where;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                modelo.addColumn("Código");
                modelo.addColumn("Nombre");
                modelo.addColumn("Marca");
                modelo.addColumn("Fecha de Vencimiento");
                modelo.addColumn("Costo");
                modelo.addColumn("Cantidad");
                int[] anchos = {50, 100, 100, 100, 100, 100};
                for (int i = 0; i < vista.jTable_Productos.getColumnCount(); i++) {
                    vista.jTable_Productos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }

                while (rs.next()) {
                    Object[] filas = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }

            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }

        /**
         * ACCION QUE VA A REALIZAR EL BOTON DE ACTUALIZAR LA TABLA CON LOS
         * DATOS
         */
        if (event.getSource() == vista.jButton_Refresh) {
            actualizarTabla();
        }

        /**
         * ACCION QUE VA A REALIZAR EL BOTON DE LIMPIAR TABLA
         */
//        if (event.getSource() == vista.jButton_CleanTable) {
//            vista.jTable_Productos.removeAll();
//        }

        /**
         * ACCION QUE VA A REALIZAR EL BOTON DE ELIMINAR UNA FILA
         */
        if (event.getSource() == vista.jButton_Eliminar) {

            PreparedStatement ps = null;
            try {
                Conexion objCon = new Conexion();
                Connection conn = objCon.getConexion();

//                int fila = vista.jTable_Productos.getSelectedRow();
                String codigo = vista.jTable_Productos.getValueAt(vista.jTable_Productos.getSelectedRow(),
                        0).toString();

                ps = conn.prepareStatement("DELETE FROM Productos WHERE codigo=?;");
                ps.setString(1, codigo);
                ps.execute();

                JOptionPane.showMessageDialog(null, "Producto Eliminado");
//                modelo.removeRow(fila);
                actualizarTabla();
                limpiar();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al Eliminar Producto");
                System.out.println(ex.toString());
            }
        }

        /**
         * ACCION QUE VA A REALIZAR EL BOTON DE GUARDAR UN REGISTRO
         */
        if (event.getSource() == vista.jButton_Guardar) {

            PreparedStatement ps = null;

            try {
                Conexion objCon = new Conexion();
                Connection conn = objCon.getConexion();
                ps = conn.prepareStatement("INSERT INTO Productos (codigo, nombre, "
                        + "marca, fecha_Vencimiento, costo, cantidad) VALUES (?,?,?,?,?,?)");

                ps.setString(1, vista.jTextField_Codigo.getText());
                ps.setString(2, vista.jTextField_Nombre.getText());
                ps.setString(3, vista.jTextField_Marca.getText());
                ps.setString(4, vista.jTextField_FechaVenci.getText());
                ps.setDouble(5, Double.parseDouble(vista.jTextField_Costo.getText()));
                ps.setInt(6, Integer.parseInt(vista.jTextField_Canti.getText()));

                ps.execute();

                JOptionPane.showMessageDialog(null, "Producto Guardado");

                modelo.addRow(new Object[]{
                    vista.jTextField_Codigo.getText(),
                    vista.jTextField_Nombre.getText(),
                    vista.jTextField_Marca.getText(),
                    vista.jTextField_FechaVenci.getText(),
                    vista.jTextField_Costo.getText(),
                    vista.jTextField_Canti.getText()
                });

                actualizarTabla();

                limpiar();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al Guardar Producto");
                System.out.println(ex);
            }

        }

        /**
         * ACCION QUE VA A REALIZAR EL BOTON DE LIMPAR LOS CAMPOS DE TEXTO PARA
         * REGISTAR UN PRODUCTO
         */
        if (event.getSource() == vista.jButton_LimpiarRegistro) {
            limpiar();
        }

        /**
         * ACCION QUE VA A REALIZAR EL BOTON DE MODIFICAR UN REGISTRO
         */
        if (event.getSource() == vista.jButton_Modificar) {

            int Fila = vista.jTable_Productos.getSelectedRow();

            PreparedStatement ps = null;
            try {
                Conexion objCon = new Conexion();
                Connection conn = objCon.getConexion();
                ps = conn.prepareStatement("UPDATE Productos SET nombre=?, marca=?, "
                        + "fecha_Vencimiento=?, costo=?, cantidad=? WHERE codigo=?");

                ps.setString(1, vista.jTextField_Nombre.getText());
                ps.setString(2, vista.jTextField_Marca.getText());
                ps.setString(3, vista.jTextField_FechaVenci.getText());
                ps.setDouble(4, Double.parseDouble(vista.jTextField_Costo.getText()));
                ps.setInt(5, Integer.parseInt(vista.jTextField_Canti.getText()));
                ps.setString(6, vista.jTextField_Codigo.getText());

                ps.execute();

                JOptionPane.showMessageDialog(null, "Producto Modificado");
                vista.jTable_Productos.setValueAt(vista.jTextField_Codigo.getText(), Fila, 0);
                vista.jTable_Productos.setValueAt(vista.jTextField_Nombre.getText(), Fila, 1);
                vista.jTable_Productos.setValueAt(vista.jTextField_Marca.getText(), Fila, 2);
                vista.jTable_Productos.setValueAt(vista.jTextField_FechaVenci.getText(), Fila, 3);
                vista.jTable_Productos.setValueAt(vista.jTextField_Costo.getText(), Fila, 4);
                vista.jTable_Productos.setValueAt(vista.jTextField_Canti.getText(), Fila, 5);

                limpiar();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al Modificar Producto");
                System.out.println(ex);
            }
        }

        /**
         * ACCION QUE VA A REALIZAR EL BOTON DE TOTAL VENTAS
         */
        if (event.getSource() == vista.jButton_TotalVen) {

            vista.jTextField_TotalVentas.setText(String.valueOf(precioUnitario()));
        }
    }

    public double precioUnitario() {
        double ventaTotal = 0, precioProducto, cantidad, costo;
        ArrayList<Double> sumaPrecios = new ArrayList();
        int totalRow = vista.jTable_Productos.getRowCount();
        totalRow -= 1;

        for (int i = 0; i <= (totalRow); i++) {
            cantidad = Integer.parseInt(String.valueOf(vista.jTable_Productos.getValueAt(i, 5)));
            costo = Double.parseDouble(String.valueOf(vista.jTable_Productos.getValueAt(i, 4)));
            precioProducto = cantidad * costo;
            sumaPrecios.add(i, precioProducto);
            ventaTotal += sumaPrecios.get(i);
        }
        return ventaTotal;
    }

    private void limpiar() {

        vista.jTextField_Codigo.setText("");
        vista.jTextField_Nombre.setText("");
        vista.jTextField_Marca.setText("");
        vista.jTextField_FechaVenci.setText("");
        vista.jTextField_Costo.setText("");
        vista.jTextField_Canti.setText("");
        vista.jTextField_TotalVentas.setText("");

    }

}

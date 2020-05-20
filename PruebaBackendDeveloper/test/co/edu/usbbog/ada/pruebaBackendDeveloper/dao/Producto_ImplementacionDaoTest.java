/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.ada.pruebaBackendDeveloper.dao;

import co.edu.usbbog.ada.pruebaBackendDeveloper.dbConexion.Conexion;
import co.edu.usbbog.ada.pruebaBackendDeveloper.modelo.Producto;
import co.edu.usbbog.ada.pruebaBackendDeveloper.vista.frmProductos;
import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SantiagoEcheDuque
 */
public class Producto_ImplementacionDaoTest {

    /**
     * Test of guardar method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testGuardar() {
        System.out.println("guardar");
        Connection con = Conexion.getConexion();
        Producto producto = new Producto();
        producto.setCodigo("002");
        producto.setNombre("Queso");
        producto.setMarca("alpina");
        producto.setFechaVenci("30-06-2020");
        producto.setCosto(4500.35);
        producto.setCantidad(10);
        Producto_ImplementacionDao producoDao = new Producto_ImplementacionDao();
        String expResult = "PRODUCTO GUARDADO";
        String result = producoDao.guardar(con, producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of modificar method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testModificar() {
        System.out.println("modificar");
        Connection con = Conexion.getConexion();
        Producto producto = new Producto();
        producto.setCodigo("003");
        producto.setNombre("Bebida en caja");
        producto.setMarca("Milo");
        producto.setFechaVenci("30-07-2020");
        producto.setCosto(3700.90);
        producto.setCantidad(7);
        Producto_ImplementacionDao objProductoDao = new Producto_ImplementacionDao();
        String expResult = "PRODUCTO MODIFICADO";
        String result = objProductoDao.modificar(con, producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of eliminar method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        Connection con = Conexion.getConexion();
//        JTable detalleVentas = new JTable(vista.jTable_Productos.getModel());
        Producto producto = new Producto();
        producto.setCodigo("003");
        producto.setNombre("Bebida en caja");
        producto.setMarca("Milo");
        producto.setFechaVenci("30-07-2020");
        producto.setCosto(3700.90);
        producto.setCantidad(7);
        String codigo = producto.getCodigo();
        Producto_ImplementacionDao objProductoDao = new Producto_ImplementacionDao();
        String expResult = "PRODUCTO ELIMINADO";
        String result = objProductoDao.eliminar(con, codigo);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscar method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        frmProductos vista = new frmProductos();
        Connection con = Conexion.getConexion();
        Producto producto = new Producto();
        producto.setCodigo("002");
        producto.setNombre("Queso");
        producto.setMarca("alpina");
        producto.setFechaVenci("30-06-2020");
        producto.setCosto(4500.35);
        producto.setCantidad(10);
        String codigo = producto.getCodigo();
        Producto_ImplementacionDao instance = new Producto_ImplementacionDao();
        instance.buscar(con, codigo, vista.jTable_Productos);

    }

    /**
     * Test of actualizarTabla method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testActualizarTabla() {
        System.out.println("actualizarTabla");
        Connection con = Conexion.getConexion();
        frmProductos vista = new frmProductos();
        Producto_ImplementacionDao instance = new Producto_ImplementacionDao();
        instance.actualizarTabla(con, vista.jTable_Productos);
        
    }

    /**
     * Test of precioUnitario method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testPrecioUnitario() {
        System.out.println("precioUnitario");
        frmProductos vista = new frmProductos();
        Producto_ImplementacionDao instance = new Producto_ImplementacionDao();
        double expResult = instance.precioUnitario(vista.jTable_Productos);
        double result = instance.precioUnitario(vista.jTable_Productos);
        assertEquals(expResult, result, 0.0);
       
    }

}

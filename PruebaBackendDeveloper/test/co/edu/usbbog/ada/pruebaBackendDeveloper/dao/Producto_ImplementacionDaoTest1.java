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
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author SantiagoEcheDuque
 */
public class Producto_ImplementacionDaoTest1 {

    static Producto_ImplementacionDao proDao;
    static Producto producto;
    String respuesta = "";

    @BeforeClass
    public static void before() {
        System.out.println("CREACION DEL OBJETO");
        producto = new Producto();
        producto.setCodigo("002");
        producto.setNombre("Queso");
        producto.setMarca("alpina");
        producto.setFechaVenci("30-06-2020");
        producto.setCosto(4500.35);
        producto.setCantidad(10);
    }

    /**
     * Test of guardar method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testGuardar() {
        System.out.println("GUARDAR");
        Connection con = Conexion.getConexion();
        proDao = new Producto_ImplementacionDao();
        String expResult = "PRODUCTO GUARDADO";
        String result = proDao.guardar(con, producto);
        assertNotNull(proDao);
        assertNotNull(result);
        System.out.println(producto.toString());
        assertEquals(expResult, result);

    }

    /**
     * Test of modificar method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testModificar() {
        System.out.println("MODIFICAR");
        System.out.println(producto.toString());
        Connection con = Conexion.getConexion();
        producto.setCodigo("002");
        producto.setNombre("Bebida en caja");
        producto.setMarca("Milo");
        producto.setFechaVenci("30-07-2020");
        producto.setCosto(3700.90);
        producto.setCantidad(7);
        proDao = new Producto_ImplementacionDao();
        String expResult = "PRODUCTO MODIFICADO";
        String result = proDao.modificar(con, producto);
        assertNotNull(proDao);
        assertNotNull(result);
        assertEquals(expResult, result);
        System.out.println(producto.toString());
        assertSame(producto, producto);
    }

    /**
     * Test of eliminar method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testEliminar() {
        System.out.println("ELIMINAR");
        Connection con = Conexion.getConexion();
        String codigo = producto.getCodigo();
        assertNotNull(codigo);
        System.out.println("Codigo: "+codigo);
        proDao = new Producto_ImplementacionDao();
        String expResult = "PRODUCTO ELIMINADO";
        String result = proDao.eliminar(con, codigo);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscar method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testBuscar() {
        System.out.println("BUSCAR");
        frmProductos vista = new frmProductos();
        Connection con = Conexion.getConexion();
        String expResult = "002";
        String codigo = producto.getCodigo();
        assertNotNull(codigo);
        assertEquals(expResult, codigo);
        System.out.println(producto.toString());
        proDao = new Producto_ImplementacionDao();
        proDao.buscar(con, codigo, vista.jTable_Productos);

    }

    /**
     * Test of actualizarTabla method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testActualizarTabla() {
        System.out.println("ACTUALIZAR TABLA");
        Connection con = Conexion.getConexion();
        frmProductos vista = new frmProductos();
        proDao = new Producto_ImplementacionDao();
        proDao.actualizarTabla(con, vista.jTable_Productos);
        assertNotNull(proDao);
        assertNotNull(vista.jTable_Productos);

    }

    /**
     * Test of precioUnitario method, of class Producto_ImplementacionDao.
     */
    @Test
    public void testPrecioUnitario() {
        System.out.println("PRECIO UNITARIO");
        frmProductos vista = new frmProductos();
        proDao = new Producto_ImplementacionDao();
        long expResult = (long) proDao.precioUnitario(vista.jTable_Productos);
        long result = (long) proDao.precioUnitario(vista.jTable_Productos);
        assertEquals(expResult, result);

    }

    @AfterClass
    public static void limpar() {
        frmProductos vista = new frmProductos();
        vista.jTable_Productos.removeAll();
    }

}

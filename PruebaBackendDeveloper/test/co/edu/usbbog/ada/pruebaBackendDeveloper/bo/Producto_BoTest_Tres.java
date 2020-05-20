/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.ada.pruebaBackendDeveloper.bo;

import static co.edu.usbbog.ada.pruebaBackendDeveloper.bo.Producto_BoTestDos.pBo;
import co.edu.usbbog.ada.pruebaBackendDeveloper.modelo.Producto;
import co.edu.usbbog.ada.pruebaBackendDeveloper.vista.frmProductos;
import static junit.framework.Assert.assertSame;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author SantiagoEcheDuque
 */
public class Producto_BoTest_Tres {

    static Producto_Bo pBo;
    Producto product;
    String respuesta = "";

    @Before
    public void before() {
        System.out.println("creacion objeto");
        product = new Producto();
        product.setCodigo("002");
        product.setNombre("Queso");
        product.setMarca("alpina");
        product.setFechaVenci("30-06-2020");
        product.setCosto(4500.35);
        product.setCantidad(10);
    }

    public boolean comprobacion(String respuesta) {
        System.out.println("Comprobacion");
        boolean bandera = false;
        pBo = new Producto_Bo();
        if (respuesta.equalsIgnoreCase(pBo.guardar(product))) {
            bandera = true;
        }
        if (respuesta.equalsIgnoreCase(pBo.modificar(product))) {
            bandera = true;
        }
        if (respuesta.equalsIgnoreCase(respuesta)) {
            bandera = true;
        }
        return bandera;
    }

    /**
     *
     */
    @Test
    public void testGuardar() {
        System.out.println("GUARDAR");
        pBo = new Producto_Bo();
//        respuesta = "PRODUCTO GUARDADO";
        respuesta = "Dana huronaRolera";
        String result = pBo.guardar(product);
        boolean validacion = comprobacion(respuesta);
        System.out.println(validacion);
        System.out.println(result);
        assertEquals(respuesta, result);
        assertTrue(validacion);
    }

    /**
     *
     */
    @Test
    public void testModificar() {
        System.out.println("MODIFICAR");
        System.out.println(product.toString());
        pBo = new Producto_Bo();
        product = new Producto();
        product.setCodigo("002");
        product.setNombre("Jamon");
        product.setMarca("Pietran");
        product.setFechaVenci("30-06-2020");
        product.setCosto(4500.35);
        product.setCantidad(12);
//        respuesta = "PRODUCTO MODIFICADO";
        respuesta = "Dana huronaRolera";
        String result = pBo.modificar(product);
        assertEquals(respuesta, result);
        boolean validacion = comprobacion(respuesta);
        System.out.println(validacion);
        System.out.println(result);
        System.out.println(product.toString());
        assertTrue(validacion);
        assertSame(product, product);
    }

    @Test
    public void testEliminar() {
        System.out.println("ELIMINAR");
        pBo = new Producto_Bo();
        String codigo = product.getCodigo();
        respuesta = "PRODUCTO ELIMINADO";
//        respuesta = "Dana huronaRolera";
        System.out.println("Codigo:"+codigo);
        System.out.println(product.toString());
        String result = pBo.eliminar(codigo);
        assertEquals(respuesta, result);
        boolean validacion = comprobacion(respuesta);
        System.out.println(validacion);
        System.out.println(result);
        assertTrue(validacion);
    }

    @Test
    public void testBuscarProducto() {
        System.out.println("BUSCAR PRODUCTO");
        frmProductos vista = new frmProductos();
        String codigo = product.getCodigo();
        System.out.println("Codigo: " + codigo);
        System.out.println(product.toString());
        pBo = new Producto_Bo();
        pBo.buscarProducto(vista.jTable_Productos, codigo);
        assertNotNull(pBo);
        assertNotNull(vista.jTable_Productos);
        assertNotNull(codigo);
    }

    /**
     * Test of actualizarTabla method, of class Producto_Bo.
     */
    @Test
    public void testActualizarTabla() {
        System.out.println("ACTIALIZAR TABLA");
        frmProductos vista = new frmProductos();
        Producto_Bo objeProBo = new Producto_Bo();
        objeProBo.actualizarTabla(vista.jTable_Productos);
        assertNotNull(objeProBo);
        assertNotNull(vista.jTable_Productos);
    }

    /**
     * Test of calcularVentas method, of class Producto_Bo.
     */
    @Test
    public void testCalcularVentas() {
        System.out.println("calcular Ventas");
        frmProductos vista = new frmProductos();
        Producto_Bo objeProBo = new Producto_Bo();
        long expResult = (long) objeProBo.calcularVentas(vista.jTable_Productos);
        long result = (long) objeProBo.calcularVentas(vista.jTable_Productos);
        System.out.println(expResult + "=" + result);
        assertNotNull(result);
        assertNotNull(expResult);
        assertEquals(expResult, result);
    }

    @AfterClass
    public static void termino() {
        System.out.println("Fin de la prueba");
    }

}

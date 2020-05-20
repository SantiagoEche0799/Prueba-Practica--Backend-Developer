/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.ada.pruebaBackendDeveloper.bo;

import co.edu.usbbog.ada.pruebaBackendDeveloper.modelo.Producto;
import co.edu.usbbog.ada.pruebaBackendDeveloper.vista.frmProductos;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SantiagoEcheDuque
 */
public class Producto_BoTestDos {
    
    static Producto_Bo pBo;
    Producto product;
    String respuesta ="";
    
    @BeforeClass
    public static void inicio(){
        System.out.println("Inicio de la prueba");        
    }
    
    @Before 
    public void before(){
        product = new Producto();
        product.setCodigo("002");
        product.setNombre("Queso");
        product.setMarca("alpina");
        product.setFechaVenci("30-06-2020");
        product.setCosto(4500.35);
        product.setCantidad(10);
    }
    
    @Test
    public void testGuardar(){
        pBo = new Producto_Bo();
        respuesta = "PRODUCTO GUARDADO";
        String result = pBo.guardar(product);
        assertEquals(respuesta, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(respuesta)) {
            fail("The test case is a prototype.");
        }
    }
    
    @After 
    public void Comprobracion(){
        pBo = new Producto_Bo();
        if (respuesta.equalsIgnoreCase(pBo.guardar(product))) {
            System.out.println("PRODUCTO GUARDADO");
        } else if(respuesta.equalsIgnoreCase(pBo.eliminar(respuesta))){
            System.out.println("PRODUCTO ELIMINADO");
        }else if(respuesta.equalsIgnoreCase(pBo.modificar(product))){
            System.out.println("PRODUCTO MODIFICADO");
        }
    }
    
    @Test
    public void testModificar(){
        pBo= new Producto_Bo();
        product = new Producto();
        product.setCodigo("002");
        product.setNombre("Jamon");
        product.setMarca("Pietran");
        product.setFechaVenci("30-06-2020");
        product.setCosto(4500.35);
        product.setCantidad(12);
        respuesta="PRODUCTO MODIFICADO";
        String result = pBo.modificar(product);
        assertEquals(respuesta, result);
    }
    
    @Test
    public void testBuscarProducto(){
        System.out.println("buscarProducto");
        frmProductos vista = new frmProductos();
        String codigo = product.getCodigo();
        System.out.println("Codigo: "+codigo);
        System.out.println(product.toString());
        pBo = new Producto_Bo();
        pBo.buscarProducto(vista.jTable_Productos, codigo);
        assertNotNull(pBo);
        assertNotNull(vista.jTable_Productos);
        assertNotNull(codigo);
    }
    
    
    @Test
    public void testEliminar(){
        pBo=new Producto_Bo();
        String codigo = product.getCodigo();
        respuesta="PRODUCTO ELIMINADO";
        String result=pBo.eliminar(codigo);
        assertEquals(respuesta, result);       
    }
    
    @AfterClass
    public static void termino(){
        System.out.println("Fin de la prueba");
    }
}

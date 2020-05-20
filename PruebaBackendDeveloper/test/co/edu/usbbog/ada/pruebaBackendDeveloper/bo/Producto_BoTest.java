package co.edu.usbbog.ada.pruebaBackendDeveloper.bo;

import co.edu.usbbog.ada.pruebaBackendDeveloper.modelo.Producto;
import co.edu.usbbog.ada.pruebaBackendDeveloper.vista.frmProductos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SantiagoEcheDuque
 */
public class Producto_BoTest {

    /**
     * Test of guardar method, of class Producto_Bo.
     */
    @Test
    public void testGuardar() {
        System.out.println("guardar");
        Producto producto = new Producto();
        producto.setCodigo("002");
        producto.setNombre("Queso");
        producto.setMarca("alpina");
        producto.setFechaVenci("30-06-2020");
        producto.setCosto(4500.35);
        producto.setCantidad(10);
        Producto_Bo proBo = new Producto_Bo();
        String expResult = "PRODUCTO GUARDADO";
        String result = proBo.guardar(producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of modificar method, of class Producto_Bo.
     */
    @Test
    public void testModificar() {
        System.out.println("modificar");
        Producto producto = new Producto();
        producto.setCodigo("003");
        producto.setNombre("Bebida en caja");
        producto.setMarca("Milo");
        producto.setFechaVenci("30-07-2020");
        producto.setCosto(3700.90);
        producto.setCantidad(7);
        Producto_Bo objeProBo = new Producto_Bo();
        String expResult = "PRODUCTO MODIFICADO";
        String result = objeProBo.modificar(producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (!result.equals(expResult)) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of eliminar method, of class Producto_Bo.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        Producto producto = new Producto();
        producto.setCodigo("003");
        producto.setNombre("Bebida en caja");
        producto.setMarca("Milo");
        producto.setFechaVenci("30-07-2020");
        producto.setCosto(3700.90);
        producto.setCantidad(7);

        String codigo = producto.getCodigo();
        Producto_Bo objeProBo = new Producto_Bo();
        String expResult = "PRODUCTO ELIMINADO";
        String result = objeProBo.eliminar(codigo);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscarProducto method, of class Producto_Bo.
     */
    @Test
    public void testBuscarProducto() {
        System.out.println("buscarProducto");
        frmProductos vista = new frmProductos();

        Producto producto = new Producto();
        producto.setCodigo("002");
        producto.setNombre("Queso");
        producto.setMarca("alpina");
        producto.setFechaVenci("30-06-2020");
        producto.setCosto(4500.35);
        producto.setCantidad(10);
        String codigo = producto.getCodigo();
        Producto_Bo objeProBo = new Producto_Bo();
        objeProBo.buscarProducto(vista.jTable_Productos, codigo);
    }

    /**
     * Test of actualizarTabla method, of class Producto_Bo.
     */
    @Test
    public void testActualizarTabla() {
        System.out.println("actualizarTabla");
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
        System.out.println("calcularVentas");
        frmProductos vista = new frmProductos();
        Producto_Bo objeProBo = new Producto_Bo();
        long expResult =(long) objeProBo.calcularVentas(vista.jTable_Productos);
        long result = (long) objeProBo.calcularVentas(vista.jTable_Productos);
        assertEquals(expResult, result);
    }

}

package mvc;

import controlador.ControlProducto;
import modelo.Producto;
import vista.frmProductos;

/**
 *
 * @author SantiagoEcheDuque
 */
public class MVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Producto modelo = new Producto();
        frmProductos vista = new frmProductos();
        
        ControlProducto controller = new ControlProducto(modelo, vista);
        
        controller.inciar();
        vista.setVisible(true);
        
    }

}

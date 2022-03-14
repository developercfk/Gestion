
package com.developercfk.interfaz;

import com.developercfk.pojo.Producto;
import com.developercfk.pojo.Venta;
import com.developercfk.pojo.Ventayproducto;
import java.util.List;

/**
 *
 * @author CFK Local
 */
public interface Venta_y_productoInterfaz {
    
   public void createVenta_y_producto(Ventayproducto ventaYProducto);
   public Ventayproducto readVenta_y_producto(int id);
   public void updateVenta_y_producto(Ventayproducto ventaYProducto);
   public void deleteVenta_y_producto(Ventayproducto ventaYProducto);
   public List<Ventayproducto> searchVenta_y_productoByIdVenta(Producto producto);
   public List<Ventayproducto> searchVenta_y_productoByIdProducto(Venta venta);
}

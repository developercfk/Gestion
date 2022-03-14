package com.developercfk.pojo;
/**
 *
 * @author CFK Local
 */
public class Ventayproducto  implements java.io.Serializable {


     private Integer idfactura;
     private Producto producto;
     private Venta venta;

    public Ventayproducto() {
    }

    public Ventayproducto(Producto producto, Venta venta) {
       this.producto = producto;
       this.venta = venta;
    }
   
    public Integer getIdfactura() {
        return this.idfactura;
    }
    
    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Venta getVenta() {
        return this.venta;
    }
    
    public void setVenta(Venta venta) {
        this.venta = venta;
    }




}



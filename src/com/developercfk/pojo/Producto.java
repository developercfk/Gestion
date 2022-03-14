package com.developercfk.pojo;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author CFK Local
 */
public class Producto  implements java.io.Serializable {


     private Integer idproducto;
     private String categoria;
     private String nombreProducto;
     private double precioProducto;
     private int stock;
     private Set ventayproductos = new HashSet(0);

    public Producto() {
    }

	
    public Producto(String categoria, String nombreProducto, double precioProducto, int stock) {
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stock = stock;
    }
    public Producto(String categoria, String nombreProducto, double precioProducto, int stock, Set ventayproductos) {
       this.categoria = categoria;
       this.nombreProducto = nombreProducto;
       this.precioProducto = precioProducto;
       this.stock = stock;
       this.ventayproductos = ventayproductos;
    }
   
    public Integer getIdproducto() {
        return this.idproducto;
    }
    
    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getNombreProducto() {
        return this.nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public double getPrecioProducto() {
        return this.precioProducto;
    }
    
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }
    public int getStock() {
        return this.stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    public Set getVentayproductos() {
        return this.ventayproductos;
    }
    
    public void setVentayproductos(Set ventayproductos) {
        this.ventayproductos = ventayproductos;
    }




}



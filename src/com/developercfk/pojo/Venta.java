package com.developercfk.pojo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author CFK Local
 */
public class Venta  implements java.io.Serializable {


     private Integer idventa;
     private Empleado empleado;
     private Date fechaRealizacion;
     private double costoTotal;
     private int cantidadProducto;
     private Set ventayproductos = new HashSet(0);

    public Venta() {
    }

	
    public Venta(Empleado empleado, Date fechaRealizacion, double costoTotal, int cantidadProducto) {
        this.empleado = empleado;
        this.fechaRealizacion = fechaRealizacion;
        this.costoTotal = costoTotal;
        this.cantidadProducto = cantidadProducto;
    }
    public Venta(Empleado empleado, Date fechaRealizacion, double costoTotal, int cantidadProducto, Set ventayproductos) {
       this.empleado = empleado;
       this.fechaRealizacion = fechaRealizacion;
       this.costoTotal = costoTotal;
       this.cantidadProducto = cantidadProducto;
       this.ventayproductos = ventayproductos;
    }
   
    public Integer getIdventa() {
        return this.idventa;
    }
    
    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Date getFechaRealizacion() {
        return this.fechaRealizacion;
    }
    
    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    public double getCostoTotal() {
        return this.costoTotal;
    }
    
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    public int getCantidadProducto() {
        return this.cantidadProducto;
    }
    
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public Set getVentayproductos() {
        return this.ventayproductos;
    }
    
    public void setVentayproductos(Set ventayproductos) {
        this.ventayproductos = ventayproductos;
    }




}



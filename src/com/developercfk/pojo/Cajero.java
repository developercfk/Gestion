package com.developercfk.pojo;
/**
 *
 * @author CFK Local
 */
public class Cajero  implements java.io.Serializable {


     private int idcajero;
     private Empleado empleado;

    public Cajero() {
    }

    public Cajero(int idcajero, Empleado empleado) {
       this.idcajero = idcajero;
       this.empleado = empleado;
    }
   
    public int getIdcajero() {
        return this.idcajero;
    }
    
    public void setIdcajero(int idcajero) {
        this.idcajero = idcajero;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }




}



package com.developercfk.pojo;

/**
 *
 * @author CFK Local
 */
public class Administrador  implements java.io.Serializable {


     private int idadministrador;
     private Empleado empleado;

    public Administrador() {
    }

    public Administrador(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public Administrador(int idadministrador, Empleado empleado) {
       this.idadministrador = idadministrador;
       this.empleado = empleado;
    }
   
    public int getIdadministrador() {
        return this.idadministrador;
    }
    
    public void setIdadministrador(int idadministrador) {
        this.idadministrador = idadministrador;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }




}



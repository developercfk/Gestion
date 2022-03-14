package com.developercfk.pojo;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author CFK Local
 */
public class Empleado  implements java.io.Serializable {


     private String dni;
     private String nombreUsuario;
     private String contrasenaUsuario;
     private Date fechaInicio;
     private String nombreEmpleado;
     private Set administradors = new HashSet(0);
     private Set ventas = new HashSet(0);
     private Set cajeros = new HashSet(0);

    public Empleado() {
    }

	
    public Empleado(String dni, String nombreUsuario, String contrasenaUsuario, Date fechaInicio, String nombreEmpleado) {
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.fechaInicio = fechaInicio;
        this.nombreEmpleado = nombreEmpleado;
    }
    public Empleado(String dni, String nombreUsuario, String contrasenaUsuario, Date fechaInicio, String nombreEmpleado, Set administradors, Set ventas, Set cajeros) {
       this.dni = dni;
       this.nombreUsuario = nombreUsuario;
       this.contrasenaUsuario = contrasenaUsuario;
       this.fechaInicio = fechaInicio;
       this.nombreEmpleado = nombreEmpleado;
       this.administradors = administradors;
       this.ventas = ventas;
       this.cajeros = cajeros;
    }
   
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasenaUsuario() {
        return this.contrasenaUsuario;
    }
    
    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getNombreEmpleado() {
        return this.nombreEmpleado;
    }
    
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    public Set getAdministradors() {
        return this.administradors;
    }
    
    public void setAdministradors(Set administradors) {
        this.administradors = administradors;
    }
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }
    public Set getCajeros() {
        return this.cajeros;
    }
    
    public void setCajeros(Set cajeros) {
        this.cajeros = cajeros;
    }




}



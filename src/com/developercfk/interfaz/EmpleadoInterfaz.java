package com.developercfk.interfaz;

import com.developercfk.pojo.Empleado;
import java.util.List;
/**
 *
 * @author developerCFK
 */
public interface EmpleadoInterfaz {
    
    //CRUD---
    public void createEmploye(Empleado employe);
    public Empleado readEmploye(String dni);
    public void updateEmploye(Empleado employe);
    public void deleteEmploye(Empleado employe);
    
    //Consultas-----
   public List<Empleado> searchEmployeByName(String name);
   public Empleado searchEmployeByUsername(String username);
   public List<Empleado> searchEmployeByDate(String date);
   public List<Empleado> searchEmployeBetweenDate(String dateMin , String dateMax);
   public List<Empleado> searchEmployeByDateMin(String dateMin);
   public List<Empleado> searchEmployeByDateMax(String dateMax);
   public List<Empleado> listAllEmploye();
}

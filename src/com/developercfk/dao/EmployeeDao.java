package com.developercfk.dao;

import com.developercfk.interfaz.EmpleadoInterfaz;
import com.developercfk.pojo.Empleado;
import com.developercfk.utilidades.GestionUtil;
import com.developercfk.utilidades.Operation;
import com.developercfk.utilidades.Tabla;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author CFK Local
 */
public class EmployeeDao implements EmpleadoInterfaz{
    
    
    private final Session session;
    private Transaction transaction;
    
    private boolean errorTransation;
    private boolean operationFailed;
    
    private String errorMessage;
    private String operationMessage;
    
    private List<Empleado> employeList;
    private Empleado empleado;
    
    
    

    public EmployeeDao(Session session) {
        
        this.session = session;
        
    }

    @Override
    public void createEmploye(Empleado employe) {
        
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            if(employe == null){
              
                operationMessage = GestionUtil.messageSucceful(employe, Operation.CREATE, Tabla.EMPLEADO);
                operationFailed = true;
                
            }else{
               
                
                   
                transaction = session.beginTransaction();
                
                getSession().save(employe);
                getSession().flush();
                transaction.commit();
                this.setOperationMessage(GestionUtil.messageSucceful(employe, Operation.CREATE, Tabla.EMPLEADO));
             
            }
            
            
            
        } catch (HibernateException e) {
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            
        } catch(Exception e){
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
        }
    }

    @Override
    public Empleado readEmploye(String dni) {
       
        operationFailed = false;
        errorTransation = false;
        setEmpleado(null);
        
        try {
            
            setEmpleado(getSession().get(Empleado.class, dni));
            if(getEmpleado() == null){
                
                this.setOperationMessage(GestionUtil.messageSucceful(getEmpleado(), Operation.READ, Tabla.EMPLEADO));
                this.setOperationFailed(true);
            }else{
                
                this.setOperationMessage(GestionUtil.messageSucceful(getEmpleado(), Operation.READ, Tabla.EMPLEADO));
                
            }
            
            
        } catch (Exception e) {
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
        }
        
        return getEmpleado();
    }

    @Override
    public void updateEmploye(Empleado employe) {
        
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            if(employe == null){
                
                this.setOperationMessage(GestionUtil.messageSucceful(employe, Operation.UPDATE, Tabla.EMPLEADO));
                this.setOperationFailed(true);
                
            }else{
                
                
                   
                transaction = session.beginTransaction();
                
                getSession().update(employe);
                getSession().flush();
                transaction.commit();
                this.setOperationMessage(GestionUtil.messageSucceful(employe, Operation.UPDATE, Tabla.EMPLEADO));
                
            }
            
        } catch (HibernateException e) {
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
        } catch (Exception e){
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
        }
    }

    @Override
    public void deleteEmploye(Empleado employe) {
        
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            
            
            if (employe == null) {
                
                this.setOperationFailed(true);
                this.setOperationMessage(GestionUtil.messageSucceful(employe, Operation.DELETE, Tabla.EMPLEADO));
            }else{
                
                
                   
                transaction = session.beginTransaction();
                
                this.getSession().delete(employe);
                getSession().flush();
                transaction.commit();
                this.setOperationFailed(false);
                this.setOperationMessage(GestionUtil.messageSucceful(employe, Operation.DELETE, Tabla.EMPLEADO));
            }
            
        } catch (HibernateException e) {
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            
        } catch (Exception e){
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
        }
    }

    @Override
    public List<Empleado> searchEmployeByName(String name) {
       
         setEmployeList(null);
         operationFailed = false;
         errorTransation = false;
        
        try {
            
            setEmployeList((List<Empleado>) getSession().createCriteria(Empleado.class).add(Restrictions.like("nombreEmpleado", name + "%")).list());
           
            if ( employeList.isEmpty()) {
                
                setOperationMessage("He doesn't have an employer with the name: " + name);
                setOperationFailed(true);
                
                
            }else{
                
                
                setOperationMessage("Loading...");
            }
            
        } catch (HibernateException e) {
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        } catch (Exception e){
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        }
        
        
        return getEmployeList();
    }

    @Override
    public Empleado searchEmployeByUsername(String username) {
      
        setEmpleado(null);
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            setEmpleado((Empleado) getSession().createCriteria(Empleado.class).add(Restrictions.eq("nombreUsuario", username)).uniqueResult());
           
            if (getEmpleado() == null) {
                
                setOperationMessage("He doesn't have an employer with the username: " + username);
                setOperationFailed(true);
            }else{
                
                
                setOperationMessage("Loading...");
            }
            
        } catch (HibernateException e) {
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            
        } catch (Exception e){
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            
        }
        
        
        return getEmpleado();
        
        
    }

    @Override
    public List<Empleado> searchEmployeByDate(String date) {
        
        setEmployeList(null);
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            setEmployeList((List<Empleado>) getSession().createCriteria(Empleado.class).add(Restrictions.eq("fechaInicio", new Date(date))).list());
           
            if (getEmployeList().isEmpty()) {
                
                setOperationMessage("No date matches this date: " + date);
                setOperationFailed(true);
                
            }else{
                
                
                setOperationMessage("Loading...");
            }
            
        } catch (HibernateException e) {
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        } catch (Exception e){
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        }
        
        
        return getEmployeList();
        
    }

    @Override
    public List<Empleado> searchEmployeBetweenDate(String dateMin, String dateMax) {
        setEmployeList(null);
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            setEmployeList((List<Empleado>) getSession().createCriteria(Empleado.class).add(Restrictions.between("fechaInicio", new Date(dateMin), new Date(dateMax) )).list());
           
            if (getEmployeList().isEmpty()) {
                
                setOperationMessage("No date matches this interval: " + dateMin + dateMax);
                setOperationFailed(true);
              
            }else{
                
                
                setOperationMessage("Loading...");
            }
            
        } catch (HibernateException e) {
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        } catch (Exception e){
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        }
        
        
        return getEmployeList();
    }
    
    @Override
    public List<Empleado> searchEmployeByDateMin(String dateMin) {
        setEmployeList(null);
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            setEmployeList((List<Empleado>) getSession().createCriteria(Empleado.class).add( Restrictions.ge( "fechaInicio", new Date(dateMin) ) ).list() );
           
            if (getEmployeList().isEmpty()) {
                
                setOperationMessage("It has no date greater than or equal to this date: " + dateMin );
                setOperationFailed(true);
               
            }else{
                
                
                setOperationMessage("Loading...");
            }
            
        } catch (HibernateException e) {
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        } catch (Exception e){
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        }
        
        
        return getEmployeList();
    }
    
    @Override
    public List<Empleado> searchEmployeByDateMax(String dateMax) {
       
        
        setEmployeList(null);
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            setEmployeList((List<Empleado>) getSession().createCriteria(Empleado.class).add( Restrictions.le( "fechaInicio", new Date(dateMax) ) ).list() );
           
            if (getEmployeList().isEmpty()) {
                
                setOperationMessage("It has no date less than or equal to this date: " + dateMax );
                setOperationFailed(true);
                
            }else{
                
                
                setOperationMessage("Loading...");
            }
            
        } catch (HibernateException e) {
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        } catch (Exception e){
            
            this.setErrorTransation(true);
            this.setErrorMessage(e.getMessage());
            employeList = new ArrayList<>();
            
        }
        
        
        return getEmployeList();
    }
    
    @Override
    public List<Empleado> listAllEmploye() {
        
        setEmployeList(null);
        operationFailed = false;
        errorTransation = false;
        
        try {
            
            employeList = session.createCriteria(Empleado.class).list();
            
            if(employeList.isEmpty()){
                
                operationFailed = true;
                operationMessage = "The list of employees is empty";
                
                
            }else{
                
                
                operationMessage = "Loading...";
            }
            
        } catch (HibernateException e) {
            
            errorMessage = e.getMessage();
            errorTransation = true;
            employeList = new ArrayList<>();
        } catch (Exception e){
            
            errorMessage = e.getMessage();
            errorTransation = true;
            employeList = new ArrayList<>();
        }
        
        return getEmployeList();
    }

//Getters y Setters

    public Session getSession() {
        return session;
    }

    public boolean isErrorTransation() {
        return errorTransation;
    }

    public void setErrorTransation(boolean errorTransation) {
        this.errorTransation = errorTransation;
    }

    public boolean isOperationFailed() {
        return operationFailed;
    }

    public void setOperationFailed(boolean operationFailed) {
        this.operationFailed = operationFailed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getOperationMessage() {
        return operationMessage;
    }

    public void setOperationMessage(String operationMessage) {
        this.operationMessage = operationMessage;
    }

    private List<Empleado> getEmployeList() {
        return employeList;
    }

    private void setEmployeList(List<Empleado> employeList) {
        this.employeList = employeList;
    }

    private Empleado getEmpleado() {
        return empleado;
    }

    private void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    

  

    
    
    
    
    
    
    
}

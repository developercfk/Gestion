
package com.developercfk.dao;

import com.developercfk.interfaz.AdministradorInterfaz;
import com.developercfk.pojo.Administrador;
import com.developercfk.utilidades.GestionUtil;
import com.developercfk.utilidades.Operation;
import com.developercfk.utilidades.Tabla;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CFK Local
 */
public class AdministratorDao implements AdministradorInterfaz{
  
    
    private final Session session;
    private Transaction transaction;
    
    private boolean errorTransation;
    private boolean operationFailed;
    
    private String errorMessage;
    private String operationMessage;
    
    private List<Administrador> administratorList;
    private Administrador administrador;

    public AdministratorDao(Session session) {
        this.session = session;
    }

    @Override
    public void createAdministrator(Administrador administrador) {
       
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
            if(administrador == null){
                
                
                operationMessage = GestionUtil.messageSucceful(administrador, Operation.CREATE, Tabla.ADMINISTRATOR);
                operationFailed = true;
               
            }else{
               
               
                   
                transaction = session.beginTransaction();
                
                getSession().save(administrador);
                getSession().flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(administrador, Operation.CREATE, Tabla.ADMINISTRATOR));
                
            }
            
          
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            
        }
        
        
    }

    @Override
    public Administrador readAdministrator(int id) {
       
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
           administrador = getSession().get(Administrador.class, id);
            
            if(administrador == null){
                
                setOperationMessage(GestionUtil.messageSucceful(administrador, Operation.READ, Tabla.ADMINISTRATOR));
                setOperationFailed(true);
            }else{
                
                setOperationMessage(GestionUtil.messageSucceful(administrador, Operation.READ, Tabla.ADMINISTRATOR));
            }
            
        } catch (Exception e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        }
        
        return administrador;
    }

    @Override
    public void updateAdministrator(Administrador administrador) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
            if(administrador == null){
                
                
                operationMessage = GestionUtil.messageSucceful(administrador, Operation.UPDATE, Tabla.ADMINISTRATOR);
                operationFailed = true;
               
            }else{
               
                
                   
                transaction = session.beginTransaction();
                
                getSession().update(administrador);
                
                getSession().flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(administrador, Operation.UPDATE, Tabla.ADMINISTRATOR));
                
            }
            
          
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            
        }
    }
    
    @Override
    public void deleteAdministrator(Administrador administrador) {
       
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            if(administrador == null){
                
               operationMessage = GestionUtil.messageSucceful(administrador, Operation.DELETE, Tabla.ADMINISTRATOR);
               operationFailed = true;
            }else{
               
                
                   
                transaction = session.beginTransaction();
                
                getSession().delete(administrador);
                getSession().flush();
                transaction.commit();
                operationMessage = GestionUtil.messageSucceful(administrador, Operation.DELETE, Tabla.ADMINISTRATOR);
                
            }
            
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            
        }
        
       
    }

    @Override
    public List<Administrador> ListAdministrator() {
       
        setErrorTransation(false);
        setOperationFailed(false);
        administratorList = null;
        
        try {
            
            administratorList = getSession().createCriteria(Administrador.class).list();
            
            if(administratorList.isEmpty()){
                
                setOperationFailed(true);
                setOperationMessage("the list of administrator is empty");
                
            }else{
              
                 setOperationMessage("Loading...");
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            administratorList = new ArrayList<>();
            
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            administratorList = new ArrayList<>();
        }
        
        return administratorList ;
    }
    
    
    //Getters Y Setters...

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

   
    
    
    
    
    
}

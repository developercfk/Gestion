
package com.developercfk.dao;

import com.developercfk.interfaz.CajeroInterfaz;
import com.developercfk.pojo.Cajero;
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
public class CajeroDao implements CajeroInterfaz{

    
    
    private final Session session;
    private Transaction transaction;
    
    private boolean errorTransation;
    private boolean operationFailed;
    
    private String errorMessage;
    private String operationMessage;
    
    private List<Cajero> cajeroList;
    private Cajero cajero;

    public CajeroDao(Session session) {
        this.session = session;
    }
    
    
    @Override
    public void createCashier(Cajero cashier) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        
        try {
            
            if (cashier == null) {
                
                setOperationMessage(GestionUtil.messageSucceful(cashier, Operation.CREATE, Tabla.CASHIER));
                setOperationFailed(true);
                
            } else {
                
               
                   
                transaction = session.beginTransaction();
                
                session.save(cashier);
                session.flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(cashier, Operation.CREATE, Tabla.CASHIER));
            }
            
      
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            
        }catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        }
        
        
    }

    @Override
    public Cajero readCashier(int id) {
        
        cajero = null;
        setOperationFailed(false);
        setErrorTransation(false);
        
        try {
            
           cajero = session.get(Cajero.class, id);
           
            if (cajero == null) {
                
                setOperationMessage(GestionUtil.messageSucceful(cajero, Operation.READ, Tabla.CASHIER));
                setOperationFailed(true);
                
            } else {
                
                setOperationMessage(GestionUtil.messageSucceful(cajero, Operation.READ, Tabla.CASHIER));
            }
            
        } catch (Exception e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        }
        
        return cajero;
    }

    @Override
    public void updateCashier(Cajero cashier) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        
        try {
            
            if (cashier == null) {
                
                setOperationMessage(GestionUtil.messageSucceful(cashier, Operation.UPDATE, Tabla.CASHIER));
                setOperationFailed(true);
                
            } else {
                
                
                   
                transaction = session.beginTransaction();
                
                session.update(cashier);
                session.flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(cashier, Operation.UPDATE, Tabla.CASHIER));
            }
            
      
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            
        }catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        }
    }
    
    @Override
    public void deleteCashier(Cajero cashier) {
        
       
        setOperationFailed(false);
        setErrorTransation(false);
        
        try {
            
           
           
            if (cashier == null) {
                
                setOperationMessage(GestionUtil.messageSucceful(cashier, Operation.DELETE, Tabla.CASHIER));
                
            } else {
                
                
                   
                transaction = session.beginTransaction();
                
                session.delete(cashier);
                session.flush();
                transaction.commit();
                
                setOperationMessage(GestionUtil.messageSucceful(cashier, Operation.DELETE, Tabla.CASHIER));
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
    public List<Cajero> listCashier() {
      
        setOperationFailed(false);
        setErrorTransation(false);
        cajeroList = null;
        
        try {
            
          cajeroList =  session.createCriteria(Cajero.class).list();
          
            if (cajeroList.isEmpty()) {
                
                operationFailed = true;
                setOperationMessage("The cashier list is empty");
            }else{
                setOperationMessage("Loading...");
                
            }
            
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            cajeroList = new ArrayList<>();
            
        }catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            cajeroList = new ArrayList<>();
        }
        
        return cajeroList;
    }
    
    //Getters y Setters...

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

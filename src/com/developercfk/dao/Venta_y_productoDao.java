package com.developercfk.dao;

import com.developercfk.interfaz.Venta_y_productoInterfaz;
import com.developercfk.pojo.Producto;
import com.developercfk.pojo.Venta;
import com.developercfk.pojo.Ventayproducto;
import com.developercfk.utilidades.GestionUtil;
import com.developercfk.utilidades.Operation;
import com.developercfk.utilidades.Tabla;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author CFK Local
 */
public class Venta_y_productoDao implements Venta_y_productoInterfaz{
    
    private final Session session;
    private Transaction transaction;
    
    private boolean errorTransation;
    private boolean operationFailed;
    
    private String errorMessage;
    private String operationMessage;
    
    private List<Ventayproducto> ventaYProductoList;

    public Venta_y_productoDao(Session session) {
        this.session = session;
    }

    @Override
    public void createVenta_y_producto(Ventayproducto ventaYProducto) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
            if(ventaYProducto != null){
                
                
                   
                transaction = session.beginTransaction();
                
                
                session.save(ventaYProducto);
                session.flush();
                transaction.commit(); 
                
                setOperationMessage(GestionUtil.messageSucceful(ventaYProducto, Operation.CREATE, Tabla.VENTA_Y_PRODUCTO));
                
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(ventaYProducto, Operation.CREATE, Tabla.VENTA_Y_PRODUCTO));
                setOperationFailed(true);
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
    public Ventayproducto readVenta_y_producto(int id) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        Ventayproducto ventaYProducto = null;
        
        try {
            
            ventaYProducto = session.get(Ventayproducto.class, id);
            
            if(ventaYProducto != null){
                
                
                setOperationMessage(GestionUtil.messageSucceful(ventaYProducto, Operation.READ, Tabla.VENTA_Y_PRODUCTO));
                
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(ventaYProducto, Operation.READ, Tabla.VENTA_Y_PRODUCTO));
                setOperationFailed(true);
            }
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        }
        
        return ventaYProducto;
    }

    @Override
    public void updateVenta_y_producto(Ventayproducto ventaYProducto) {
        
        setErrorTransation(false);
        setOperationFailed(false);
       
        
        try {
            
            
            
            if(ventaYProducto != null){
                
               
                   
                transaction = session.beginTransaction();
                
                session.update(ventaYProducto);
                session.flush();
                
                transaction.commit(); 
                
                setOperationMessage(GestionUtil.messageSucceful(ventaYProducto, Operation.UPDATE, Tabla.VENTA_Y_PRODUCTO));
                
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(ventaYProducto, Operation.UPDATE, Tabla.VENTA_Y_PRODUCTO));
                setOperationFailed(true);
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
    public void deleteVenta_y_producto(Ventayproducto ventaYProducto) {
        
        setErrorTransation(false);
        setOperationFailed(false);
       
        
        try {
            
            if(ventaYProducto != null){
                
                
                   
                transaction = session.beginTransaction();
                
                session.delete(ventaYProducto);
                session.flush();
                
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(ventaYProducto, Operation.DELETE, Tabla.VENTA_Y_PRODUCTO));
                
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(ventaYProducto, Operation.DELETE, Tabla.VENTA_Y_PRODUCTO));
                setOperationFailed(true);
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
    public List<Ventayproducto> searchVenta_y_productoByIdVenta(Producto producto) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaYProductoList = null;
        
        try {
            
            ventaYProductoList = session.createCriteria(Ventayproducto.class).add(Restrictions.eq("producto", producto)).list();
            
            if( !ventaYProductoList.isEmpty()){
                
                
                setOperationMessage("Loading...");
                
            }else{
               
                setOperationMessage("No match exists for this product");
                setOperationFailed(true);
                
            }
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaYProductoList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaYProductoList = new ArrayList<>();
        }
        
        return ventaYProductoList;
    }

    @Override
    public List<Ventayproducto> searchVenta_y_productoByIdProducto(Venta venta) {
        setErrorTransation(false);
        setOperationFailed(false);
        ventaYProductoList = null;
        
        try {
            
            ventaYProductoList = session.createCriteria(Ventayproducto.class).add(Restrictions.eq("venta", venta)).list();
            
            if(!ventaYProductoList.isEmpty()){
                
                
                setOperationMessage("Loading...");
                
            }else{
               
                setOperationMessage("No match exists for this sale");
                
                setOperationFailed(true);
            }
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaYProductoList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaYProductoList = new ArrayList<>();
        }
        
        return ventaYProductoList;
    }
    
    
    //Getters Y Setters...

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

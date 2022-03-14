package com.developercfk.dao;

import com.developercfk.interfaz.VentaInterfaz;
import com.developercfk.pojo.Empleado;
import com.developercfk.pojo.Venta;
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
public class SaleDao implements VentaInterfaz{
    
    private final Session session;
    private Transaction transaction;
    
    private boolean errorTransation;
    private boolean operationFailed;
    
    private String errorMessage;
    private String operationMessage;
    
    private List<Venta> ventaList;

    public SaleDao(Session session) {
        this.session = session;
    }

    @Override
    public void createVenta(Venta venta) {
       
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
            if(venta != null){
                
                
                   
                transaction = session.beginTransaction();
                
                session.save(venta);
                session.flush();
                transaction.commit();
                
                setOperationMessage(GestionUtil.messageSucceful(venta, Operation.CREATE, Tabla.VENTA));
            
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(venta, Operation.CREATE, Tabla.VENTA));
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
    public Venta readVenta(int id) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        Venta venta = null;
        
        try {
            
            venta = session.get(Venta.class, id);
            
            if(venta != null){
                
                setOperationMessage(GestionUtil.messageSucceful(venta, Operation.READ, Tabla.VENTA));
            
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(venta, Operation.READ, Tabla.VENTA));
                setOperationFailed(true);
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        }
        return venta;
    }

    @Override
    public void updateVenta(Venta venta) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
            if(venta != null){
                
                   
                transaction = session.beginTransaction();
                
                session.update(venta);
                session.flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(venta, Operation.UPDATE, Tabla.VENTA));
            
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(venta, Operation.UPDATE, Tabla.VENTA));
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
    public void deleteVenta(Venta venta) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
            if(venta != null){
                
                
                   
                transaction = session.beginTransaction();
                
                session.delete(venta);
                session.flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(venta, Operation.DELETE, Tabla.VENTA));
            
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(venta, Operation.DELETE, Tabla.VENTA));
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
    public List<Venta> searchSaleByEmploye(Empleado employe) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.eq("empleado", employe)).list();
            if(!ventaList.isEmpty()){
                
                
                setOperationMessage("Loading...");
            
            }else{
               
                setOperationMessage("There is no sales associated with this employee.");
                
                setOperationFailed(true);
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleByDate(String date) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.eq("fechaRealizacion", new Date(date))).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");
            
            }else{
               
                setOperationMessage("There is no sales associated with this date : " + date);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleByTotalPrice(double price) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.eq("costoTotal", price)).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with this price : " + price);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleByProductQuantity(int quantity) {
        
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.eq("cantidadProducto", quantity)).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with this quantity : " + quantity);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
        
    }

    @Override
    public List<Venta> searchSaleBetweenDate(String dateMin, String dateMax) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.between("fechaRealizacion", new Date(dateMin), new Date(dateMax))).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with date between : " + dateMin + " AND " + dateMax);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
        
    }

    @Override
    public List<Venta> searchSaleBetweenTotalPrice(double priceMin, double priceMax) {
       
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.between("costoTotal", priceMin, priceMax)).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with price between : " + priceMin + " AND " + priceMax);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleBetweenQuantity(int quantityMin, int quantityMax) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.between("cantidadProducto", quantityMin, quantityMax)).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with quantity between : " + quantityMin + " AND " + quantityMax);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleByDateMin(String dateMin) {
       
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.ge("fechaRealizacion", new Date(dateMin))).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with date >= " + dateMin);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
        
    }

    @Override
    public List<Venta> searchSaleByDateMax(String dateMax) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.le("fechaRealizacion", new Date(dateMax))).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with date <= " + dateMax);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleByPriceMin(double priceMin) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.ge("costoTotal", priceMin)).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with price >= " + priceMin);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleByPriceMax(double priceMax) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.le("costoTotal", priceMax)).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with price <= " + priceMax);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleByProductQuantityMin(int quantityMin) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.ge("cantidadProducto", quantityMin)).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with quantity >= " + quantityMin);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }

    @Override
    public List<Venta> searchSaleByProductQuantityMax(int quantityMax) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).add(Restrictions.le("cantidadProducto", quantityMax)).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");  
            
            }else{
               
                setOperationMessage("There is no sales associated with quantity <= " + quantityMax);
                setOperationFailed(true);
                
                
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }
    
    
    @Override
    public List<Venta> listSales() {
      
        setOperationFailed(false);
        setErrorTransation(false);
        ventaList = null;
        
        try {
            
            ventaList = session.createCriteria(Venta.class).list();
            
            if(!ventaList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product found in the list");
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            ventaList = new ArrayList<>();
        }
        
        return ventaList;
    }
    
    
    //Getter Y Setter...

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

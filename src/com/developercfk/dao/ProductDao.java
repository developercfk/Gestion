package com.developercfk.dao;

import com.developercfk.interfaz.ProductoInterfaz;
import com.developercfk.pojo.Producto;
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
public class ProductDao implements ProductoInterfaz{

    private final Session session;
    private Transaction transaction;
    
    private boolean errorTransation;
    private boolean operationFailed;
    
    private String errorMessage;
    private String operationMessage;
    
    private List<Producto> productList;
    
    public ProductDao(Session session) {
        
        this.session = session;
    }

    @Override
    public void createProduct(Producto producto) {
       
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
            
            if(producto != null){
                
               
                   
                transaction = session.beginTransaction();
                
                session.save(producto);
                session.flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(producto, Operation.CREATE, Tabla.PRODUCT));
                
            }else{
               
                setOperationMessage(GestionUtil.messageSucceful(producto, Operation.CREATE, Tabla.PRODUCT));
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
    public Producto readProduct(int id) {
        
        setErrorTransation(false);
        setOperationFailed(false);
        Producto product = null;
        try {
           
            product = session.get(Producto.class, id);
            
            
            if(product != null ){
                
                setOperationMessage(GestionUtil.messageSucceful(product, Operation.READ, Tabla.PRODUCT));
                
            }else{
                
                setOperationMessage(GestionUtil.messageSucceful(product, Operation.READ, Tabla.PRODUCT));
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        }
        
        return product;
    }

    @Override
    public void updateProduct(Producto producto) {
       
        setErrorTransation(false);
        setOperationFailed(false);
        
        try {
            
            if(producto != null){
                
                transaction = session.beginTransaction();
                
                session.update(producto);
                session.flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(producto, Operation.UPDATE, Tabla.PRODUCT));
            }else{
                setOperationMessage(GestionUtil.messageSucceful(producto, Operation.UPDATE, Tabla.PRODUCT));
                setOperationFailed(true);
                
            }
            
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        } catch ( Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
        }
    }

    @Override
    public void deleteProduct(Producto producto) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        
        try {
            
            if(producto != null){
                   
                transaction = session.beginTransaction();
                
                session.delete(producto);
                session.flush();
                transaction.commit();
                setOperationMessage(GestionUtil.messageSucceful(producto, Operation.DELETE, Tabla.PRODUCT));
                
            }else{
                setOperationMessage(GestionUtil.messageSucceful(producto, Operation.DELETE, Tabla.PRODUCT));
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
    public List<Producto> searchProductByName(String name) {
      
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.like("nombreProducto", name + "%")).list();
            
            if(!productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the name: " + name);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductByCategorie(String categorie) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.eq("categoria", categorie)).list();
            
            if( !productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the categorie: " + categorie);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductByPrice(Double price) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.eq("precioProducto", price)).list();
            
            if(!productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the price = " + price);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductByPriceMin(Double priceMin) {
       
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.ge("precioProducto", priceMin)).list();
            
            if(!productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the price >= : " + priceMin);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductByPriceMax(Double priceMax) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.le("precioProducto", priceMax)).list();
            
            if(!productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the price <= " + priceMax);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductBetweenPrice(Double priceMin, Double priceMax) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.between("precioProducto", priceMin , priceMax)).list();
            
            if( !productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the price between : " + priceMin+ " AND " + priceMax);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductByStock(int stock) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.eq("stock", stock)).list();
            
            if( !productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the stock equal : " + stock);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductByStockMin(int stockMin) {
       
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.ge("stock", stockMin)).list();
            
            if(!productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the stock >= " + stockMin);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductByStockMax(int stockMax) {
       
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.le("stock", stockMax)).list();
            
            if(!productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the stock <= " + stockMax);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }

    @Override
    public List<Producto> searchProductBetweenStock(int stockMin, int stockMax) {
        
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).add(Restrictions.between("stock", stockMin , stockMax)).list();
            
            if(!productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product with the stock between " + stockMin + " AND " + stockMax);
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
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

    @Override
    public List<Producto> listProduct() {
        
        setOperationFailed(false);
        setErrorTransation(false);
        productList = null;
        
        try {
            
            productList = session.createCriteria(Producto.class).list();
            
            if(!productList.isEmpty()){
                
                setOperationMessage("Loading...");
            }else{
                
                setOperationMessage("No product found in the list");
                setOperationFailed(true);
            }
            
        } catch (HibernateException e) {
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        } catch (Exception e){
            
            setErrorMessage(e.getMessage());
            setErrorTransation(true);
            productList = new ArrayList<>();
        }
        
        return productList;
    }
    
    
    

    
}

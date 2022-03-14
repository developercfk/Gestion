package com.developercfk.interfaz;

import com.developercfk.pojo.Producto;
import java.util.List;

/**
 *
 * @author developerCFK
 */
public interface ProductoInterfaz {
    
    public void createProduct(Producto producto);
    public Producto readProduct(int id);
    public void updateProduct(Producto producto);
    public void deleteProduct(Producto producto);
    
    public List<Producto> searchProductByName(String name);
    public List<Producto> searchProductByCategorie(String categorie);
    public List<Producto> searchProductByPrice(Double price);
    public List<Producto> searchProductByPriceMin(Double priceMin);
    public List<Producto> searchProductByPriceMax(Double priceMax);
    public List<Producto> searchProductBetweenPrice(Double priceMin, Double priceMax);
    public List<Producto> searchProductByStock(int stock);
    public List<Producto> searchProductByStockMin(int stockMin);
    public List<Producto> searchProductByStockMax(int stockMax);
    public List<Producto> searchProductBetweenStock(int stockMin , int stockMax);
    public List<Producto> listProduct();
   
    
    
    
}

package com.developercfk.interfaz;

import com.developercfk.pojo.Empleado;
import com.developercfk.pojo.Venta;
import java.util.List;

/**
 *
 * @author CFK Local
 */
public interface VentaInterfaz {
    
    public void createVenta(Venta venta);
    public Venta readVenta(int id);
    public void updateVenta(Venta venta);
    public void deleteVenta(Venta venta);
    
    public List<Venta> searchSaleByEmploye(Empleado employe);
    public List<Venta> searchSaleByDate(String date);
    public List<Venta> searchSaleByTotalPrice(double price);
    public List<Venta> searchSaleByProductQuantity(int quantity);
    public List<Venta> searchSaleBetweenDate(String dateMin , String dateMax);
    public List<Venta> searchSaleBetweenTotalPrice(double priceMin , double priceMax);
    public List<Venta> searchSaleBetweenQuantity(int quantityMin , int quantityMax);
    public List<Venta> searchSaleByDateMin(String dateMin);
    public List<Venta> searchSaleByDateMax(String dateMax);
    public List<Venta> searchSaleByPriceMin(double priceMin);
    public List<Venta> searchSaleByPriceMax(double priceMax);
    public List<Venta> searchSaleByProductQuantityMin(int quantityMin);
    public List<Venta> searchSaleByProductQuantityMax(int quantityMax);
    public List<Venta> listSales();
    
    
    
    
}

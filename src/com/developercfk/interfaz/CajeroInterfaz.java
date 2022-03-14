
package com.developercfk.interfaz;

import com.developercfk.pojo.Cajero;
import java.util.List;

/**
 *
 * @author CFK Local
 */
public interface CajeroInterfaz {
   
    public void createCashier(Cajero cashier);
    public Cajero readCashier(int id);
    public void updateCashier(Cajero cashier);
    public void deleteCashier(Cajero cashier);
    public List<Cajero> listCashier();
  
 
  
}

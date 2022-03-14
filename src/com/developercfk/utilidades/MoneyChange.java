
package com.developercfk.utilidades;

/**
 *
 * @author CFK Local
 */
public enum MoneyChange {
    
    
   EURO("EUR"),DOLLAR("USD"),FCFA("FCFA"),YEN("JPY"),LIVRE("GBP"); 

   
   private final String money;
   
    private MoneyChange(String money) {
      this.money = money;  
    }

    public String getMoney() {
        return money;
    }
   
   
   
}

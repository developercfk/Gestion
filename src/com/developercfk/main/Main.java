package com.developercfk.main;

import com.developercfk.dao.AdministratorDao;
import com.developercfk.dao.EmployeeDao;
import com.developercfk.dao.ProductDao;
import com.developercfk.dao.SaleDao;
import com.developercfk.dao.Venta_y_productoDao;
import com.developercfk.pojo.Administrador;
import com.developercfk.pojo.Empleado;
import com.developercfk.pojo.Producto;
import com.developercfk.pojo.Venta;
import com.developercfk.pojo.Ventayproducto;
import com.developercfk.utilidades.GestionUtil;
import com.developercfk.utilidades.HibernateUtil;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author developerCFK
 */
public class Main {
    
    public static void main(String[] args) {
     
        System.out.println("com.developercfk.main.Main.main() OPEN SESSION");
        Session session = HibernateUtil.getSessionFactory().openSession();

        String str = "Chris tian";
        
        String[] tab = GestionUtil.splitBySpace(str);
        
        for (String string : tab) {
            
            System.out.println(string);
            System.out.println(string.substring(1));
        }
        
        
        
        
        
    }
}

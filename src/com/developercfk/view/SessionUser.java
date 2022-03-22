/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developercfk.view;

import com.developercfk.dao.AdministratorDao;
import com.developercfk.dao.CajeroDao;
import com.developercfk.dao.EmployeeDao;
import com.developercfk.dao.ProductDao;
import com.developercfk.dao.SaleDao;
import com.developercfk.dao.Venta_y_productoDao;
import com.developercfk.pojo.Administrador;
import com.developercfk.pojo.Cajero;
import com.developercfk.pojo.Empleado;
import com.developercfk.pojo.Producto;
import com.developercfk.pojo.Venta;
import com.developercfk.pojo.Ventayproducto;
import com.developercfk.utilidades.GestionUtil;
import com.developercfk.utilidades.MoneyChange;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;
import org.hibernate.Session;

/**
 *
 * @author CFK Local
 */
public class SessionUser extends javax.swing.JPanel {

    private final Session session;
    private final Empleado employeSession;
    
    private ProductDao productDao;
    private EmployeeDao empleadoDao;
    private AdministratorDao administratorDao;
    private CajeroDao cashierDao;
    private SaleDao saleDao;
    private Venta_y_productoDao saleAndProductDao;
    
    private Empleado empleado;
    private String enter;
    private double total;
    
    private List<Producto> productList;
    private List<Empleado> empleadoList;
    private List<Venta> saleList;
    
    private Set<Ventayproducto> saleAndProductSet;
    private final Set<Venta> saleSet;
    
    private DefaultTableModel modeloTableProduct;
    private DefaultTableModel modeloTableEmploye;
    private DefaultTableModel modeloTableSale;
    private final DefaultListModel<String> listModel;
    private final List<Producto> addListProductModel;
    List<Integer> integerList;
    
    private final String[] columnProduct;
    private final String[] columnEmploye;
    private final String[] columnVenta;
    private int rows;
    private int column;
    
    
    /**
     * Creates new form SessionUser
     * @param session
     * @param employeSession
     */
    public SessionUser(Session session, Empleado employeSession) {
       
        this.columnProduct = new String[]{"ID PRODUCT", "NAME PRODUCT", "CATEGORY", "PRICE", "STOCK"};
        this.columnEmploye = new String[]{"DNI","NAME","USERNAME","DATE"};
        this.columnVenta = new String[]{"ID" , "DNI EMPLEADO" , "DATE OF COMPLETION" , "TOTAL COST" , "PRODUCT QUANTITY"};
        this.session = session;
        this.employeSession = employeSession;
        this.listModel = new DefaultListModel<>(); 
        this.addListProductModel = new ArrayList<>();
        this.integerList = new ArrayList<>();
        
        initComponents();
        setEditableFieldSale();
        loadProductDynamic(null);
        loadEmployeDynamic(null);
        loadSaleDynamic(null);
        jLabelUserConnect.setText(employeSession.getNombreUsuario() );
        saleSet = employeSession.getVentas();
        
    }
    
    //---------------------PRODUCT-------------------------------------------------------------
    private void loadProductDynamic(List<Producto> list){
        
       
        productDao = new ProductDao(session);
        
        if(list == null){
            
           productList = productDao.listProduct(); 
        }else if(list.isEmpty()){
            
            JOptionPane.showMessageDialog(null, "no result found");
            
        }else{
            
            productList = list;
        }
        modeloTableProduct = new DefaultTableModel(tableDataProducts(productList), columnProduct);
        jTableProductDefault.setModel(modeloTableProduct);
    }
    
    private void loadProductByName(String name){
        
        productList = productDao.searchProductByName(name);
        loadProductDynamic( productList );
        
    }
    
    private void loadProductByCategory(String category){
        
        productList = productDao.searchProductByCategorie(category);
        loadProductDynamic(productList);
    }
    
    private void loadProductByPrice(double price){
        
        productList = productDao.searchProductByPrice(price);
        loadProductDynamic(productList);
    }
    
    private void loadProductByMinPrice(double priceMin){
        
        productList = productDao.searchProductByPriceMin(priceMin);
        loadProductDynamic(productList);
    }
    
    private void loadProductByMaxPrice(double priceMax){
        
        productList = productDao.searchProductByPriceMax(priceMax);
        loadProductDynamic(productList);
    }
    
    private void loadProductBetweenPrice(double priceMin, double priceMax){
        
        productList = productDao.searchProductBetweenPrice(priceMin, priceMax);
        loadProductDynamic(productList);
    }
    
    private void loadProductByStock(int stock){
        
        productList = productDao.searchProductByStock(stock);
        loadProductDynamic(productList);
    }
    
    private void loadProductByStockMin(int stockMin){
        
        productList = productDao.searchProductByStockMin(stockMin);
        loadProductDynamic(productList);
        
    }
    
    private void loadProductByStockMax(int stockMax){
        
        productList = productDao.searchProductByStockMax(stockMax);
        loadProductDynamic(productList);
        
    }
    
    private void loadProductBetweenStock(int stockMin, int stockMax){
        
        productList = productDao.searchProductBetweenStock(stockMin, stockMax);
        loadProductDynamic(productList);
    }
    
    private String[][] tableDataProducts(List<Producto> list){
        
        rows = list.size();
        column = columnProduct.length;
        Producto currentProduct;
        String[][] tab = new String[rows][column];
        
        for (int i = 0; i < rows; i++) {
            
            currentProduct = list.get(i);
            for (int j = 0; j < column; j++) {
               
                switch(j){
                    
                    case 0 : tab[i][j] = Integer.toString( currentProduct.getIdproducto() );
                    break;
                    
                    case 1 : tab[i][j] = currentProduct.getNombreProducto() ;
                    break;
                    
                    case 2 : tab[i][j] = currentProduct.getCategoria() ;
                    break;
                    
                    case 3 : tab[i][j] = Double.toString( currentProduct.getPrecioProducto() );
                    break;
                    
                    case 4 : tab[i][j] = Integer.toString( currentProduct.getStock() );
                    break;
                    
                } 
               
            }
        }
        
       
        return tab;
    }
    //--------------------------------------------------------------------------------------------
    
    
    
    //------------------------EMPLOYE-------------------------------------------------------------
    
    private void loadEmployeDynamic(List<Empleado> list){
        
       
        empleadoDao = new EmployeeDao(session);
        
        if(list == null){
            
           empleadoList = empleadoDao.listAllEmploye();
        }else if(list.isEmpty()){
            
            JOptionPane.showMessageDialog(null, "no result found");
         
        }else{
            empleadoList = list; 
        }
        modeloTableEmploye = new DefaultTableModel(tableDataEmploye(empleadoList), columnEmploye);
        jTableDataEmploye.setModel(modeloTableEmploye);
        
    }
    
    private void loadEmployeByName(String name){
        
        empleadoList = empleadoDao.searchEmployeByName(name);
        loadEmployeDynamic(empleadoList);
    }
    
    private void loadEmployeByUsername(String username){
        
        empleado = empleadoDao.searchEmployeByUsername(username);
        empleadoList = new ArrayList<>();
        empleadoList.add(empleado);
        loadEmployeDynamic(empleadoList);
        
    }
    
    private void loadEmployeByDni(String dni){
        
        empleado = empleadoDao.readEmploye(dni);
        empleadoList = new ArrayList<>();
        empleadoList.add(empleado);
        loadEmployeDynamic(empleadoList);
        
    }
    
    private void loadEmployeByDate(String date){
       
        empleadoList = empleadoDao.searchEmployeByDate(date);
        loadEmployeDynamic(empleadoList);
        
    }
    
    private void loadEmployeByDateMin(String dateMin){
        
        empleadoList = empleadoDao.searchEmployeByDateMin(dateMin);
        loadEmployeDynamic(empleadoList);
        
    }
    
    private void loadEmployeByDateMax(String dateMax){
        
        empleadoList = empleadoDao.searchEmployeByDateMax(dateMax);
        loadEmployeDynamic(empleadoList);
        
    }
    
    private void loadEmployeBetween(String dateMin, String dateMax){
        
        empleadoList = empleadoDao.searchEmployeBetweenDate(dateMin, dateMax);
        loadEmployeDynamic(empleadoList);
    }
    
    
    private List<Empleado> listEmployeAdministrator(){
        
        administratorDao = new AdministratorDao(session);
        List<Administrador> administradorList = administratorDao.ListAdministrator();
        empleadoList = new ArrayList<>();
        
        for (int i = 0; i < administradorList.size(); i++) {
            
            empleado = administradorList.get(i).getEmpleado();
            empleadoList.add(empleado);
        }
        
        return empleadoList;
        
    }
    
    private void loadAdministratorByName(String name){
        
        empleadoList = empleadoDao.searchEmployeByName(name);
        List<Administrador> administrators = administratorDao.ListAdministrator();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < administrators.size(); i++) {
            
            if( empleadoList.contains( administrators.get(i).getEmpleado() ) ){
                
                adminDniLike.add( administrators.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic(adminDniLike);
    }
    
    private void loadAdministratorByDate(String date){
        
        empleadoList = empleadoDao.searchEmployeByDate(date);
        List<Administrador> administrators = administratorDao.ListAdministrator();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < administrators.size(); i++) {
            
            if( empleadoList.contains( administrators.get(i).getEmpleado() ) ){
                
                adminDniLike.add( administrators.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic(adminDniLike);
        
    }
    
    private void loadAdministratorByDateMin(String dateMin){
        
        empleadoList = empleadoDao.searchEmployeByDateMin(dateMin);
        List<Administrador> administrators = administratorDao.ListAdministrator();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < administrators.size(); i++) {
            
            if( empleadoList.contains( administrators.get(i).getEmpleado() ) ){
                
                adminDniLike.add( administrators.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic(adminDniLike);
        
    }
    
    private void loadAdministratorByDateMax(String dateMax){
       
        empleadoList = empleadoDao.searchEmployeByDateMax(dateMax);
        List<Administrador> administrators = administratorDao.ListAdministrator();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < administrators.size(); i++) {
            
            if( empleadoList.contains( administrators.get(i).getEmpleado() ) ){
                
                adminDniLike.add( administrators.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic(adminDniLike);
    }
    
    private void loadAdministratorDateBetween(String dateMin, String dateMax){
        
        empleadoList = empleadoDao.searchEmployeBetweenDate(dateMin, dateMax);
        List<Administrador> administrators = administratorDao.ListAdministrator();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < administrators.size(); i++) {
            
            if( empleadoList.contains( administrators.get(i).getEmpleado() ) ){
                
                adminDniLike.add( administrators.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic(adminDniLike);
        
    }
    
    private List<Empleado> listEmployeCashier(){
        
        cashierDao = new CajeroDao(session);
        List<Cajero> cajeroList = cashierDao.listCashier();
        empleadoList = new ArrayList<>();
        
        for (int i = 0; i < cajeroList.size(); i++) {
            
            empleado = cajeroList.get(i).getEmpleado();
            empleadoList.add(empleado);
        }
        
        
        return empleadoList;
    }
    
    private void loadCashierByName(String name){
        
        empleadoList = empleadoDao.searchEmployeByName(name);
        List<Cajero> cashierList = cashierDao.listCashier();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < cashierList.size(); i++) {
            
            if( empleadoList.contains( cashierList.get(i).getEmpleado() ) ){
                
                adminDniLike.add( cashierList.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic( adminDniLike );
        
    }
    
    private void loadCashierByDate(String date){
        
        empleadoList = empleadoDao.searchEmployeByDateMin(date);
        List<Cajero> cashierList = cashierDao.listCashier();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < cashierList.size(); i++) {
            
            if( empleadoList.contains( cashierList.get(i).getEmpleado() ) ){
                
                adminDniLike.add( cashierList.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic( adminDniLike );
        
    }
    
    private void loadCashierByDateMin(String dateMin){
        
        empleadoList = empleadoDao.searchEmployeByDateMin(dateMin);
        List<Cajero> cashierList = cashierDao.listCashier();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < cashierList.size(); i++) {
            
            if( empleadoList.contains( cashierList.get(i).getEmpleado() ) ){
                
                adminDniLike.add( cashierList.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic(adminDniLike);
    }
    
    private void loadCashierByDateMax(String dateMax){
        
        empleadoList = empleadoDao.searchEmployeByDateMax(dateMax);
        List<Cajero> cashierList = cashierDao.listCashier();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < cashierList.size(); i++) {
            
            if( empleadoList.contains( cashierList.get(i).getEmpleado() ) ){
                
                adminDniLike.add( cashierList.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic(adminDniLike);
        
    }
    
    private void loadCashierDateBetween(String dateMin, String dateMax){
        
        empleadoList = empleadoDao.searchEmployeBetweenDate(dateMin, dateMax);
        List<Cajero> cashierList = cashierDao.listCashier();
        
        List<Empleado> adminDniLike = new ArrayList<>();
        
        for (int i = 0; i < cashierList.size(); i++) {
            
            if( empleadoList.contains( cashierList.get(i).getEmpleado() ) ){
                
                adminDniLike.add( cashierList.get(i).getEmpleado() );
            }
            
        }
        
        loadEmployeDynamic(adminDniLike);
        
    }
    
    private String[][] tableDataEmploye(List<Empleado> list){
      
        rows = list.size();
        column = columnEmploye.length;
        Empleado currentEmpleado;
        String[][] tab = new String[rows][column];
        
        
        for (int i = 0; i < rows; i++) {
            
            currentEmpleado = list.get(i);
            for (int j = 0; j < column; j++) {
               
                switch(j){
                    
                    case 0 : tab[i][j] = currentEmpleado.getDni();
                    break;
                    
                    case 1 : tab[i][j] = currentEmpleado.getNombreEmpleado();
                    break;
                    
                    case 2 : tab[i][j] = currentEmpleado.getNombreUsuario();
                    break;
                    
                    case 3 : tab[i][j] = currentEmpleado.getFechaInicio().toString();
                    break;
                    
                    
                    
                } 
               
            }
        }
        
        
        return tab;
    }
    //-------------------------------------------------------------------------------------------------
    
    private void setEditableFieldProduct(boolean editableTextField, boolean editableValueMin, boolean editableValueMax){
   
        if(editableTextField){
            
            jTextFieldSearch.setEnabled(true);
            jTextFieldSearch.setBackground(Color.WHITE);
            
        }else{
            
            jTextFieldSearch.setEnabled(false);
            jTextFieldSearch.setBackground(Color.LIGHT_GRAY);
            
              
        }
        
        
        
        
        if(editableValueMin){
            
            jTextFieldValueMin.setEditable(true);
            jTextFieldValueMin.setBackground(Color.WHITE);
            
           
            
            if(jComboBoxSearchProduct.getSelectedIndex() == 3 || jComboBoxSearchProduct.getSelectedIndex() == 7 ){
                jLabelMinValue.setText("Value : ");
            }else{
                jLabelMinValue.setText("Min value : ");
            }

        }else{
            jTextFieldValueMin.setEditable(false);
            jTextFieldValueMin.setBackground(Color.LIGHT_GRAY);
           
           
        }
        
        
        
        
        if(editableValueMax){
            jTextFieldValueMax.setEditable(true);
            jTextFieldValueMax.setBackground(Color.WHITE);
            
           
        }else{
            
            jTextFieldValueMax.setEditable(false);
            jTextFieldValueMax.setBackground(Color.LIGHT_GRAY);
            
            
        }

    }
    
    private void setEditableFieldEmploye(){
        
        
        int index = jComboBoxEmployeSearch.getSelectedIndex();
        
        switch(index){
            
            case 0:
                jTextFieldYearMax.setEditable(false);
                jTextFieldMonthMax.setEditable(false);
                jTextFieldDayMax.setEditable(false);
                jTextFieldYearMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMax.setBackground(Color.LIGHT_GRAY);
                
                jTextFieldYearMin.setEditable(false);
                jTextFieldMonthMin.setEditable(false);
                jTextFieldDayMin.setEditable(false);
                jTextFieldYearMin.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMin.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMin.setBackground(Color.LIGHT_GRAY);
                
                jTextFieldEmployeSearch.setEditable(true);
                jTextFieldEmployeSearch.setBackground(Color.WHITE);
                
                
                break;
            
            case 1:
                jTextFieldYearMax.setEditable(false);
                jTextFieldMonthMax.setEditable(false);
                jTextFieldDayMax.setEditable(false);
                jTextFieldYearMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMax.setBackground(Color.LIGHT_GRAY);
                
                jTextFieldYearMin.setEditable(false);
                jTextFieldMonthMin.setEditable(false);
                jTextFieldDayMin.setEditable(false);
                jTextFieldYearMin.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMin.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMin.setBackground(Color.LIGHT_GRAY);
                
                jTextFieldEmployeSearch.setEditable(true);
                jTextFieldEmployeSearch.setBackground(Color.WHITE);
                
                break;
                
            case 2:
               jTextFieldYearMax.setEditable(false);
                jTextFieldMonthMax.setEditable(false);
                jTextFieldDayMax.setEditable(false);
                jTextFieldYearMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMax.setBackground(Color.LIGHT_GRAY);
                
                jTextFieldYearMin.setEditable(false);
                jTextFieldMonthMin.setEditable(false);
                jTextFieldDayMin.setEditable(false);
                jTextFieldYearMin.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMin.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMin.setBackground(Color.LIGHT_GRAY);
                
                jTextFieldEmployeSearch.setEditable(true);
                jTextFieldEmployeSearch.setBackground(Color.WHITE);
                
                break;
            
            case 3:
                jTextFieldYearMax.setEditable(false);
                jTextFieldMonthMax.setEditable(false);
                jTextFieldDayMax.setEditable(false);
                jTextFieldYearMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMax.setBackground(Color.LIGHT_GRAY);
                
                jLabelMinDateEmploye.setText("Value YY/MM/DD :");
                jTextFieldYearMin.setEditable(true);
                jTextFieldMonthMin.setEditable(true);
                jTextFieldDayMin.setEditable(true);
                jTextFieldYearMin.setBackground(Color.WHITE);
                jTextFieldMonthMin.setBackground(Color.WHITE);
                jTextFieldDayMin.setBackground(Color.WHITE);
                
                jTextFieldEmployeSearch.setEditable(false);
                jTextFieldEmployeSearch.setBackground(Color.LIGHT_GRAY);
                
                break;
                
            case 4:
                jTextFieldYearMax.setEditable(true);
                jTextFieldMonthMax.setEditable(true);
                jTextFieldDayMax.setEditable(true);
                jTextFieldYearMax.setBackground(Color.WHITE);
                jTextFieldMonthMax.setBackground(Color.WHITE);
                jTextFieldDayMax.setBackground(Color.WHITE);
                
                jTextFieldYearMin.setEditable(true);
                jTextFieldMonthMin.setEditable(true);
                jTextFieldDayMin.setEditable(true);
                jTextFieldYearMin.setBackground(Color.WHITE);
                jTextFieldMonthMin.setBackground(Color.WHITE);
                jTextFieldDayMin.setBackground(Color.WHITE);
                
                jTextFieldEmployeSearch.setEditable(false);
                jTextFieldEmployeSearch.setBackground(Color.LIGHT_GRAY);
                
                break;
                
            case 5:
                jTextFieldYearMax.setEditable(false);
                jTextFieldMonthMax.setEditable(false);
                jTextFieldDayMax.setEditable(false);
                jTextFieldYearMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMax.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMax.setBackground(Color.LIGHT_GRAY);
                
                jTextFieldYearMin.setEditable(true);
                jTextFieldMonthMin.setEditable(true);
                jTextFieldDayMin.setEditable(true);
                jTextFieldYearMin.setBackground(Color.WHITE);
                jTextFieldMonthMin.setBackground(Color.WHITE);
                jTextFieldDayMin.setBackground(Color.WHITE);
                
                jTextFieldEmployeSearch.setEditable(false);
                jTextFieldEmployeSearch.setBackground(Color.LIGHT_GRAY);
                
                break;
                
            case 6:
                jTextFieldYearMax.setEditable(true);
                jTextFieldMonthMax.setEditable(true);
                jTextFieldDayMax.setEditable(true);
                jTextFieldYearMax.setBackground(Color.WHITE);
                jTextFieldMonthMax.setBackground(Color.WHITE);
                jTextFieldDayMax.setBackground(Color.WHITE);
                
                jTextFieldYearMin.setEditable(false);
                jTextFieldMonthMin.setEditable(false);
                jTextFieldDayMin.setEditable(false);
                jTextFieldYearMin.setBackground(Color.LIGHT_GRAY);
                jTextFieldMonthMin.setBackground(Color.LIGHT_GRAY);
                jTextFieldDayMin.setBackground(Color.LIGHT_GRAY);
                
                jTextFieldEmployeSearch.setEditable(false);
                jTextFieldEmployeSearch.setBackground(Color.LIGHT_GRAY);
                
                break;
                
            
                
            
        }
        
    }
    
    
    //----------------------------SALE ----------------------------------------------------------------
    private void loadSaleDynamic(List<Venta> list){
        
        saleDao = new SaleDao(session);
        
        if(list == null){
            
          saleList = saleDao.listSales(); 
          
        }else if(list.isEmpty()){
            
            JOptionPane.showMessageDialog(null, "no result found");
         
        }else{
            saleList = list; 
        }
        modeloTableSale = new DefaultTableModel(tableDataSale(saleList), columnVenta);
        jTableDateSale.setModel(modeloTableSale);
        
    }
    
    private Object[][] tableDataSale(List<Venta> list) {
        
        rows = list.size();
        column = columnVenta.length;
        Venta currentSale;
        String[][] tab = new String[rows][column];
        
        
        for (int i = 0; i < rows; i++) {
            
            currentSale = list.get(i);
            for (int j = 0; j < column; j++) {
               
                switch(j){
                    
                    case 0 : tab[i][j] = Integer.toString( currentSale.getIdventa() );
                    break;
                    
                    case 1 : tab[i][j] = currentSale.getEmpleado().getDni();
                    break;
                    
                    case 2 : tab[i][j] = currentSale.getFechaRealizacion().toString();
                    break;
                    
                    case 3 : tab[i][j] = Double.toString( currentSale.getCostoTotal() ) ;
                    break;
                    
                    case 4: tab[i][j] = Integer.toString( currentSale.getCantidadProducto() ) ;
                    break;
                    
                    
                    
                } 
               
            }
        }
        
        
        return tab;
    }
    
    private void setEditableFieldSale(){
        
        int index = jComboBoxSales.getSelectedIndex();
        
        switch (index) {
            case 0:
                jLabelValueSaleMin.setText("");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(false);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 1:
                jLabelValueSaleMin.setText("ID value");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 2:
                jLabelValueSaleMin.setText("");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setEditable(true);
                jTextFieldSales.setBackground(Color.WHITE);
                jTextFieldMinValue0.setVisible(false);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 3:
                jLabelValueSaleMin.setText("Cost value");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 4:
                jLabelValueSaleMin.setText("Cost Min");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 5:
                jLabelValueSaleMin.setText("");
                jLabelValueSaleMax.setText("Cost Max");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(false);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(true);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 6:
                jLabelValueSaleMin.setText("Cost Min");
                jLabelValueSaleMax.setText("Cost Max");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(true);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 7:
                jLabelValueSaleMin.setText("Quantity value");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 8:
                jLabelValueSaleMin.setText("Quantity Min");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 9:
                jLabelValueSaleMin.setText("");
                jLabelValueSaleMax.setText("Quantity Max");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(false);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(true);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 10:
                jLabelValueSaleMin.setText("Quantity Min");
                jLabelValueSaleMax.setText("Quantity Max");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(true);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 11:
                jLabelValueSaleMin.setText("Date value");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(true);
                jTextFieldMinValue2.setVisible(true);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 12:
                jLabelValueSaleMin.setText("Date Min");
                jLabelValueSaleMax.setText("");
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(true);
                jTextFieldMinValue2.setVisible(true);
                jTextFieldMaxValue0.setVisible(false);
                jTextFieldMaxValue1.setVisible(false);
                jTextFieldMaxValue2.setVisible(false);
                break;
                
            case 13:
                jLabelValueSaleMin.setText("");
                jLabelValueSaleMax.setText("Date Max");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(false);
                jTextFieldMinValue1.setVisible(false);
                jTextFieldMinValue2.setVisible(false);
                jTextFieldMaxValue0.setVisible(true);
                jTextFieldMaxValue1.setVisible(true);
                jTextFieldMaxValue2.setVisible(true);
                break;
                
            case 14:
                jLabelValueSaleMin.setText("Date Min");
                jLabelValueSaleMax.setText("Date Max");
                jTextFieldSales.setEditable(false);
                jTextFieldSales.setBackground(Color.LIGHT_GRAY);
                jTextFieldMinValue0.setVisible(true);
                jTextFieldMinValue1.setVisible(true);
                jTextFieldMinValue2.setVisible(true);
                jTextFieldMaxValue0.setVisible(true);
                jTextFieldMaxValue1.setVisible(true);
                jTextFieldMaxValue2.setVisible(true);
                break;
        }
        
        
        
    }
    
    private void searchSaleByID(int id){
        
       Venta v = saleDao.readVenta(id);
       saleList = new ArrayList<>();
       
       if(v == null){
           
           JOptionPane.showMessageDialog(null, "no result found");
           
       }else{
           
          saleList.add(v);
           loadSaleDynamic(saleList);
       }
        
    }
    
    private void searchSaleByDniEmploye(String dni){
        
        empleado = empleadoDao.readEmploye(dni);
        saleList = saleDao.searchSaleByEmploye(empleado);
        
        if(empleado == null){
            
            JOptionPane.showMessageDialog(null, "no result found");
        }else{
           
           
            loadSaleDynamic(saleList);
        }
    }
    
    private void searchSaleByCost(double costTotal){
        
       saleList =  saleDao.searchSaleByTotalPrice(costTotal);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
       
        
    }
    
    private void searchSaleByCostMin(double costTotalMin){
       
        saleList =  saleDao.searchSaleByPriceMin(costTotalMin);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
    }
    
    private void searchSaleByCostMax(double costTotalMax){
        
        saleList =  saleDao.searchSaleByPriceMax(costTotalMax);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
    }
    
    private void searchSaleByCostBetween(double costTotalMin, double costTotalMax){
       
       saleList =  saleDao.searchSaleBetweenTotalPrice(costTotalMin, costTotalMax);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
        
    }
   
    private void searchSaleByDate(String date){
        
        saleList =  saleDao.searchSaleByDate(date);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
        
    }
    
    private void searchSaleByDateMin(String dateMin){
        
       saleList =  saleDao.searchSaleByDateMin(dateMin);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
    }
    
    private void searchSaleByDateMax(String dateMax){
        
        saleList =  saleDao.searchSaleByDateMax(dateMax);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
    }
    
    private void searchSaleByDateBetween(String dateMin, String dateMax){
        
        saleList =  saleDao.searchSaleBetweenDate(dateMin, dateMax);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
        
    }
    
    private void searchSaleByProductQuantity(int quantity){
       
        saleList =  saleDao.searchSaleByProductQuantity(quantity);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
    }
    
    private void searchSaleByProductQuantityMin(int quantityMin){
        
       saleList =  saleDao.searchSaleByProductQuantityMin(quantityMin);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
        
    }
    
    private void searchSaleByProductQuantityMax(int quantityMax){
    
        
       saleList =  saleDao.searchSaleByProductQuantityMax(quantityMax);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
        
    }
    
    private void searchSaleByProductQuantityBetween(int quantityMin, int quantityMax){
        
        saleList =  saleDao.searchSaleBetweenQuantity(quantityMin, quantityMax);
       
       if(saleList.isEmpty()){
          
           JOptionPane.showMessageDialog(null, "no result found");
       }else{
          
           loadSaleDynamic(saleList);
       }
        
    }
    
    private void reafreshInvoice(){
        
        enter = jTextFieldProductID.getText();
        jListInvoice.setModel(listModel);
       
        
        double priceProduct;
        int quantity = 1;
        String productName ;
        String resultShow;
        
        Producto product = productDao.readProduct( Integer.parseInt(enter) );
        
        if(product != null){
          
                priceProduct = product.getPrecioProducto();
                productName = product.getNombreProducto();
                
                resultShow = "x"+quantity +"  "+ productName +"  "+ priceProduct +" " +MoneyChange.EURO.getMoney();


                if(addListProductModel.contains(product)){

                    updateQuantityProductInvoice( addListProductModel.indexOf(product) , resultShow ,product);
                    total = Double.sum( total, product.getPrecioProducto() ) ;
                    
                    
                   
                }else{
                    addListProductModel.add(product);
                    listModel.addElement(resultShow);
                    total =  Double.sum( total, product.getPrecioProducto() ) ;
                    integerList.add( quantity );
                    
                }
            
            
        }else{
            
            System.out.println("Le produit n'existe pas...");
        }
        
        total = Math.round(total * 100.0) / 100.0;
        
        jLabelPriceValue.setText(total + " " + MoneyChange.EURO.getMoney());
        
    }
    
    private void updateQuantityProductInvoice(int index, String resulShow , Producto product){
        
       
        String element = listModel.getElementAt(index);
        String[] splitSpace = GestionUtil.splitBySpace(element);
        String quantity = splitSpace[0];
        
        int unit = Integer.parseInt( quantity.substring(1) ) + 1;
        double priceXunit = Math.round( (product.getPrecioProducto()*unit) * 100.0 ) / 100.0;
        
        resulShow = "x" + unit + "  " + product.getNombreProducto() + "  " +  priceXunit  +" "+ MoneyChange.EURO.getMoney();
       
        listModel.set(index, resulShow);
        integerList.set(index, unit);
       
        
        
    }
    
    private void updateQuantityProductInDataBase(Producto p ,int qty){
            
            if( p.getStock() > 0){
                
                
                p.setStock(p.getStock() - qty);
               
                productDao.updateProduct(p);
                
            }else{
                
                productDao.deleteProduct(p);
            }
    }
    
    private void createSale(List<Producto> productList){
        
        saleAndProductDao = new Venta_y_productoDao(session);
        saleDao = new SaleDao(session);
        ArrayList<Ventayproducto> arrayList = new ArrayList<>();
        saleAndProductSet = new HashSet<>();
        int productSaleQuantity = addListProductModel.size();
        Venta venta = new Venta(this.employeSession, new Date(), total, productSaleQuantity);
        Producto product = null;
        
        saleDao.createVenta(venta);
        this.employeSession.getVentas().add(venta);
        
        for (int i = 0; i < productList.size(); i++) {
            
           product = productList.get(i);
           Ventayproducto vYp = new Ventayproducto(product, venta);
           saleAndProductSet.add(vYp);
           arrayList.add(vYp);
           
        }
        
            if(  product == null  ){
                
                System.out.println("Product equal null...");  
            }else{
                product.setVentayproductos(saleAndProductSet);
                venta.setVentayproductos(saleAndProductSet);
            }
        
        empleadoDao.updateEmploye(this.employeSession);
        
        for (int i = 0; i < arrayList.size(); i++) {
          
            saleAndProductDao.createVenta_y_producto(arrayList.get(i));
        }
        
    }
    
    private void AddNewEmployeButton(String title){
        
        JFrame frame = new JFrame();
        Object[] options = {"Create employe", "Cancel"};
        NewEmploye addEmploye = new NewEmploye();
        String name;
        String dni;
        String username;
        String pwd;
        
       
        int selection = JOptionPane.showOptionDialog(frame, addEmploye, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options , options[1] );
        
        switch( addEmploye.getjComboBoxPoste().getSelectedIndex() ){
            
            case 0:
                JOptionPane.showMessageDialog(null, "Select a status for the employer. eg. \"Administrator o Cashier\" ","Warning",JOptionPane.WARNING_MESSAGE);
                break;
                
            case 1:
               
                if(selection == 0){
                    
                        name = addEmploye.getjTextFieldName().getText();
                        dni = addEmploye.getjTextFieldIdCard().getText();
                        username = addEmploye.getjTextFieldUsername().getText();
                        pwd = addEmploye.getjTextFieldPassword().getText();

                        if(name.isEmpty() || dni.isEmpty() || username.isEmpty() || pwd.isEmpty() ){

                            JOptionPane.showMessageDialog(null, "Fill in all fields" ,"Warning",JOptionPane.WARNING_MESSAGE); 
                        }else{

                            empleado = new Empleado(dni, username, pwd, new Date(), name);
                            empleadoDao.createEmploye(empleado);
                            AdministratorDao adminDao = new AdministratorDao(session);
                            Administrador administrator = new Administrador(empleado);
                            adminDao.createAdministrator(administrator);

                            JOptionPane.showMessageDialog(null, "Successful operation");
                        }
                    
                }
                
                break;
                
            case 2:
                
                if(selection == 0){
                    
                    name = addEmploye.getjTextFieldName().getText();
                    dni = addEmploye.getjTextFieldIdCard().getText();
                    username = addEmploye.getjTextFieldUsername().getText();
                    pwd = addEmploye.getjTextFieldPassword().getText();
                    
                    if(name.isEmpty() || dni.isEmpty() || username.isEmpty() || pwd.isEmpty() ){
                        
                        JOptionPane.showMessageDialog(null, "Fill in all fields" ,"Warning",JOptionPane.WARNING_MESSAGE); 
                        
                    }else{
                        
                        empleado = new Empleado(dni, username, pwd, new Date(), name);
                        empleadoDao.createEmploye(empleado);
                        CajeroDao cajeroDao = new CajeroDao(session);
                        Cajero cashier = new Cajero(empleado);
                        cajeroDao.createCashier(cashier);

                        JOptionPane.showMessageDialog(null, "Successful operation");  
                        
                    }
                    
                }
                break;
        }
       
        
        
        
    }
    
    private void updateEmployeButton(String title){
        
        JFrame frame = new JFrame();
        Object[] options = {"Update employe", "Cancel"};
        String dni = JOptionPane.showInputDialog(null, "Enter employer identification number");
        empleado = empleadoDao.readEmploye(dni);
        String name;
        String username;
        String password;
        
        if (empleado != null) {
            
           EditEmploye editAndDeleteEmploye = new EditEmploye(this.empleado);

           int selection = JOptionPane.showOptionDialog(frame, editAndDeleteEmploye, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options , options[1] );
        
            if(selection == 0){
                
                name = editAndDeleteEmploye.getjTextFieldName().getText();
                username = editAndDeleteEmploye.getjTextFieldUsername().getText();
                password = editAndDeleteEmploye.getjTextFieldPassword().getText();
                empleado.setNombreEmpleado(name);
                empleado.setNombreUsuario(username);
                empleado.setContrasenaUsuario(password);
                empleadoDao.updateEmploye(empleado);
                
                JOptionPane.showMessageDialog(null,"Employ, modify successfully");
            }
        
        
        }else{
            
            JOptionPane.showMessageDialog(null, "No employ find with ID: " + dni , "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        
        
        
        
        
    }
    
    private void deleteEmployeButton(String title){
        
        JFrame frame = new JFrame();
        Object[] options = {"Delete employe", "Cancel"};
        String dni = JOptionPane.showInputDialog(null, "Enter employer identification number");
        empleado = empleadoDao.readEmploye(dni);
        
        if (empleado != null) {
            
           EditEmploye editAndDeleteEmploye = new EditEmploye(this.empleado);
           
           int selection = JOptionPane.showOptionDialog(frame, editAndDeleteEmploye, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options , options[1] );
        
            if(selection == 0){
                
                empleadoDao.deleteEmploye(empleado);
                
                JOptionPane.showMessageDialog(null,"Employ remove successfully");
            }
        
        
        }else{
            
            JOptionPane.showMessageDialog(null, "No employ find with ID: " + dni , "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void addNewProductButton(String title){
        JFrame frame = new JFrame();
        Object[] options = {"Add Product", "Cancel"};
        ProductPopup productPopup = new ProductPopup();
        
         int selection = JOptionPane.showOptionDialog(frame, productPopup, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options , options[1] );
         
         
         if(selection == 0){
             
             try {
                 
                 
                   
                    Producto producto = new Producto();
                    producto.setNombreProducto(productPopup.getjTextFieldProductName().getText());
                    producto.setCategoria(productPopup.getjComboBoxCategory().getSelectedItem().toString());
                    producto.setPrecioProducto(Double.parseDouble( productPopup.getjTextFieldPrice().getText() ) );
                    producto.setStock(Integer.parseInt( productPopup.getjTextFieldStock().getText() ));
                    productDao.createProduct(producto);
                     
                 } catch (NumberFormatException e) {
                     
                    JOptionPane.showMessageDialog(null, e.getMessage() , "WARNING", JOptionPane.WARNING_MESSAGE); 
                 }
             
         }
         
        
    }
    
    private void updateProductButton(String title){
       
        
        try {
          
            JFrame frame = new JFrame();
            Object[] options = {"Update Product", "Cancel"};
            String idProduct = JOptionPane.showInputDialog(null, "Enter product identification number");
            Producto producto = productDao.readProduct(Integer.parseInt(idProduct));
            
            if(producto != null){
               
                ProductPopup productPopup = new ProductPopup(producto);

                int selection = JOptionPane.showOptionDialog(frame, productPopup, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options , options[1] );


                if(selection == 0){

                    producto.setNombreProducto(productPopup.getjTextFieldProductName().getText());
                    producto.setCategoria(productPopup.getjComboBoxCategory().getSelectedItem().toString());
                    producto.setPrecioProducto(Double.parseDouble( productPopup.getjTextFieldPrice().getText() ) );
                    producto.setStock(Integer.parseInt( productPopup.getjTextFieldStock().getText() ));
                    productDao.updateProduct(producto);
                }
                
            }else{
                
                JOptionPane.showMessageDialog(null, "No product found with the identifier: " + idProduct); 
            }
            
            
            
        } catch (HeadlessException | NumberFormatException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage() , "WARNING", JOptionPane.WARNING_MESSAGE); 
        }
        
    }
    
    private void deleteProductButton(String title){
        
        try {
          
            JFrame frame = new JFrame();
            Object[] options = {"Update Product", "Cancel"};
            String idProduct = JOptionPane.showInputDialog(null, "Enter product identification number");
            Producto producto = productDao.readProduct(Integer.parseInt(idProduct));
            
            if(producto != null){
               
                ProductPopup productPopup = new ProductPopup(producto);

                int selection = JOptionPane.showOptionDialog(frame, productPopup, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options , options[1] );


                if(selection == 0){

                    productDao.deleteProduct(producto);
                 }
                
            }else{
                
               JOptionPane.showMessageDialog(null, "No product found with the identifier: " + idProduct); 
            }
            
            
            
        } catch (HeadlessException | NumberFormatException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage() , "WARNING", JOptionPane.WARNING_MESSAGE); 
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupEmployeSearch = new javax.swing.ButtonGroup();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldProductID = new javax.swing.JTextField();
        jLabelProductID = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListInvoice = new javax.swing.JList<>();
        jLabelInvoice = new javax.swing.JLabel();
        jButtonRemove = new javax.swing.JButton();
        jLabelTextTotal = new javax.swing.JLabel();
        jLabelPriceValue = new javax.swing.JLabel();
        jButtonToPay = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaInvoice = new javax.swing.JTextArea();
        jButtonPrintInvoiceHome = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableDateSale = new javax.swing.JTable();
        jComboBoxSales = new javax.swing.JComboBox<>();
        jTextFieldSales = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabelValueSaleMin = new javax.swing.JLabel();
        jLabelValueSaleMax = new javax.swing.JLabel();
        jTextFieldMinValue0 = new javax.swing.JTextField();
        jTextFieldMaxValue0 = new javax.swing.JTextField();
        jTextFieldMinValue1 = new javax.swing.JTextField();
        jTextFieldMaxValue1 = new javax.swing.JTextField();
        jTextFieldMinValue2 = new javax.swing.JTextField();
        jTextFieldMaxValue2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductDefault = new javax.swing.JTable();
        jComboBoxSearchProduct = new javax.swing.JComboBox<>();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearchProduct = new javax.swing.JButton();
        jTextFieldValueMin = new javax.swing.JTextField();
        jTextFieldValueMax = new javax.swing.JTextField();
        jLabelMaxValue = new javax.swing.JLabel();
        jLabelMinValue = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldEmployeSearch = new javax.swing.JTextField();
        jButtonEmployeSearch = new javax.swing.JButton();
        jComboBoxEmployeSearch = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDataEmploye = new javax.swing.JTable();
        jRadioButtonCashier = new javax.swing.JRadioButton();
        jRadioButtonAdministrator = new javax.swing.JRadioButton();
        jRadioButtonAll = new javax.swing.JRadioButton();
        jLabelMinDateEmploye = new javax.swing.JLabel();
        jLabelMaxDateEmploye = new javax.swing.JLabel();
        jTextFieldDayMin = new javax.swing.JTextField();
        jTextFieldMonthMin = new javax.swing.JTextField();
        jTextFieldDayMax = new javax.swing.JTextField();
        jTextFieldMonthMax = new javax.swing.JTextField();
        jTextFieldYearMin = new javax.swing.JTextField();
        jTextFieldYearMax = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButtonEditEmploye = new javax.swing.JButton();
        jButtonDeleteEmploye = new javax.swing.JButton();
        jButtonNewEmploye = new javax.swing.JButton();
        jButtonNewProduct = new javax.swing.JButton();
        jButtonDeleteProduct = new javax.swing.JButton();
        jButtonEditProduct = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelUserConnect = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 153, 0));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabelProductID.setText("PRODUCT ID");

        jButtonAdd.setText("Add>>");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(jListInvoice);

        jLabelInvoice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInvoice.setText("INVOICE");

        jButtonRemove.setText("<<Remove");
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jLabelTextTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTextTotal.setText("TOTAL :");

        jLabelPriceValue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPriceValue.setText("00.00  EUR");

        jButtonToPay.setText("To Pay");
        jButtonToPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonToPayActionPerformed(evt);
            }
        });

        jTextAreaInvoice.setColumns(20);
        jTextAreaInvoice.setRows(5);
        jScrollPane5.setViewportView(jTextAreaInvoice);

        jButtonPrintInvoiceHome.setText("Print");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldProductID)
                    .addComponent(jLabelProductID, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabelInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelTextTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonPrintInvoiceHome, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTextTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPrintInvoiceHome, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("   Home", new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/981080_basic_home_house_thiago p.png")), jPanel1); // NOI18N

        jTableDateSale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "DNI EMPLEADO", "DATE OF COMPLETION", "TOTAL COST", "PRODUCT QUANTITY"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableDateSale);

        jComboBoxSales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All sales", "Search by ID", "Search by  Dni Employe", "Search by cost", "Search by cost Min", "Search by cost Max", "Search by cost between", "Search by product quantity", "Search by product quantity Min", "Search by product quantity Max", "Search by product quantity between", "Search by date", "Search by date Min", "Search by date Max", "Search by date between" }));
        jComboBoxSales.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSalesItemStateChanged(evt);
            }
        });

        jTextFieldSales.setBackground(Color.LIGHT_GRAY);

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelValueSaleMin.setText("Value Min");

        jLabelValueSaleMax.setText("Value Max");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelValueSaleMin, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelValueSaleMax, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextFieldMinValue0, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldMinValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldMinValue2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxSales, javax.swing.GroupLayout.Alignment.LEADING, 0, 255, Short.MAX_VALUE)
                                    .addComponent(jTextFieldSales, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextFieldMaxValue0, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldMaxValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldMaxValue2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jComboBoxSales, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSales, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelValueSaleMin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMinValue0, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMinValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMinValue2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelValueSaleMax, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMaxValue0, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaxValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaxValue2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Sales", new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/Sale2541.png")), jPanel6); // NOI18N

        jTableProductDefault.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID PRODUCT", "NAME PRODUCT", "CATEGORY", "PRICE", "STOCK"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableProductDefault);

        jComboBoxSearchProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All product", "Search by name", "Search by category", "Search by price", "Search by price minimal", "Search by price maximal", "Search by price between", "Search by stock", "Search by stock minimal", "Search by stock maximal", "Search by stock between" }));
        jComboBoxSearchProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSearchProductItemStateChanged(evt);
            }
        });

        jTextFieldSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jButtonSearchProduct.setText("Search");
        jButtonSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchProductActionPerformed(evt);
            }
        });

        jTextFieldValueMin.setEditable(false);
        jTextFieldValueMin.setBackground(Color.LIGHT_GRAY);
        jTextFieldValueMin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextFieldValueMax.setEditable(false);
        jTextFieldValueMax.setBackground(Color.LIGHT_GRAY);
        jTextFieldValueMax.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelMaxValue.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLabelMaxValue.setText("Max value :");

        jLabelMinValue.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLabelMinValue.setText("Min value  :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelMinValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldValueMin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelMaxValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldValueMax, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jComboBoxSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValueMin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMinValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMaxValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValueMax, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Product", new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/2639894_product_icon (1).png")), jPanel2); // NOI18N

        jButtonEmployeSearch.setText("Search");
        jButtonEmployeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmployeSearchActionPerformed(evt);
            }
        });

        jComboBoxEmployeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Searsh by name", "Search by dni", "Search by username", "Search by date", "Search by date between", "Search by date Min", "Search by date Max" }));
        jComboBoxEmployeSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxEmployeSearchItemStateChanged(evt);
            }
        });

        jTableDataEmploye.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "NAME", "USERNAME", "DATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableDataEmploye);

        buttonGroupEmployeSearch.add(jRadioButtonCashier);
        jRadioButtonCashier.setText("CASHIER");
        jRadioButtonCashier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonCashierItemStateChanged(evt);
            }
        });

        buttonGroupEmployeSearch.add(jRadioButtonAdministrator);
        jRadioButtonAdministrator.setText("ADMINISTRATOR");
        jRadioButtonAdministrator.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonAdministratorItemStateChanged(evt);
            }
        });

        buttonGroupEmployeSearch.add(jRadioButtonAll);
        jRadioButtonAll.setSelected(true);
        jRadioButtonAll.setText("ALL");
        jRadioButtonAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonAllItemStateChanged(evt);
            }
        });

        jLabelMinDateEmploye.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelMinDateEmploye.setText("Min value YY/MM/DD :");

        jLabelMaxDateEmploye.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelMaxDateEmploye.setText("Max value YY/MM/DD:");

        jTextFieldDayMin.setEditable(false);
        jTextFieldDayMin.setBackground(Color.LIGHT_GRAY);

        jTextFieldMonthMin.setEditable(false);
        jTextFieldMonthMin.setBackground(Color.LIGHT_GRAY);

        jTextFieldDayMax.setEditable(false);
        jTextFieldDayMax.setBackground(Color.LIGHT_GRAY);

        jTextFieldMonthMax.setEditable(false);
        jTextFieldMonthMax.setBackground(Color.LIGHT_GRAY);

        jTextFieldYearMin.setEditable(false);
        jTextFieldYearMin.setBackground(Color.LIGHT_GRAY);

        jTextFieldYearMax.setEditable(false);
        jTextFieldYearMax.setBackground(Color.LIGHT_GRAY);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonAll)
                            .addComponent(jRadioButtonAdministrator)
                            .addComponent(jRadioButtonCashier)
                            .addComponent(jComboBoxEmployeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMinDateEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMaxDateEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldYearMin, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldMonthMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldDayMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldEmployeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEmployeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldYearMax, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldMonthMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldDayMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 469, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jComboBoxEmployeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonAdministrator)
                .addGap(4, 4, 4)
                .addComponent(jRadioButtonCashier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEmployeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEmployeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jLabelMinDateEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldYearMin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMonthMin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDayMin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMaxDateEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldDayMax, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldMonthMax, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldYearMax, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Employe", new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/309042_group_users_people_icon (1).png")), jPanel3); // NOI18N

        jButtonEditEmploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/1608463_update_user_icon.png"))); // NOI18N
        jButtonEditEmploye.setText("Edit employe");
        jButtonEditEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditEmployeActionPerformed(evt);
            }
        });

        jButtonDeleteEmploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/1608463_remove_user_icon.png"))); // NOI18N
        jButtonDeleteEmploye.setText("Delete employe");
        jButtonDeleteEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteEmployeActionPerformed(evt);
            }
        });

        jButtonNewEmploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/1608463_plus_user_icon (1).png"))); // NOI18N
        jButtonNewEmploye.setText("New employe");
        jButtonNewEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewEmployeActionPerformed(evt);
            }
        });

        jButtonNewProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/create_product.png"))); // NOI18N
        jButtonNewProduct.setText("New product");
        jButtonNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewProductActionPerformed(evt);
            }
        });

        jButtonDeleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/7341112_remove_package_icon.png"))); // NOI18N
        jButtonDeleteProduct.setText("Delete product");
        jButtonDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteProductActionPerformed(evt);
            }
        });

        jButtonEditProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/update_package_icon.png"))); // NOI18N
        jButtonEditProduct.setText("Edit  product");
        jButtonEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditProductActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PANEL ADMIN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonDeleteEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEditEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonNewEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(115, 115, 115)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNewEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDeleteEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Setting", new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/1891011_circle_cog_customize_gea.png")), jPanel4); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel3.setText("Contact : christianfokoua@outlook.es");

        jLabel4.setText("Version 1.0.0");

        jLabel5.setText("Inventory management software");

        jLabel6.setText("By DeveloperCfk");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(449, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("     Help", new javax.swing.ImageIcon(getClass().getResource("/com/developercfk/img/help_question_questions_.png")), jPanel5); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(33, Short.MAX_VALUE)))
        );

        jLabelUserConnect.setBackground(new java.awt.Color(255, 204, 153));
        jLabelUserConnect.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("USER :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUserConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUserConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchProductActionPerformed
       
        enter = jTextFieldSearch.getText();
        int select = jComboBoxSearchProduct.getSelectedIndex();
        double decimalValueMin, decimalValueMax;
        int integerValueMin, integerValueMax;
        
        switch(select){
            
            case 0 : loadProductDynamic(null);
            break;
            
            case 1 : loadProductByName(enter);
            break;
            
            case 2 : loadProductByCategory(enter);
            break;
            
            case 3 : 
                decimalValueMin = Double.parseDouble( jTextFieldValueMin.getText() );
                loadProductByPrice( decimalValueMin );
            break;
            
            case 4 : 
                decimalValueMin = Double.parseDouble( jTextFieldValueMin.getText() );
                loadProductByMinPrice( decimalValueMin );
            break;
            
            case 5 : 
                decimalValueMax = Double.parseDouble( jTextFieldValueMax.getText() )  ; 
                loadProductByMaxPrice( decimalValueMax );
            break;
            
            case 6 :
                decimalValueMin = Double.parseDouble( jTextFieldValueMin.getText() ) ;
                decimalValueMax = Double.parseDouble( jTextFieldValueMax.getText() )  ;  
                loadProductBetweenPrice(decimalValueMin, decimalValueMax);
            break;
            
            case 7 :
                integerValueMin = Integer.parseInt(jTextFieldValueMin.getText());
                loadProductByStock(integerValueMin);
            break;
            
            case 8 : 
                integerValueMin = Integer.parseInt(jTextFieldValueMin.getText());
                loadProductByStockMin(integerValueMin);
            break;
            
            case 9 : 
                integerValueMax = Integer.parseInt(jTextFieldValueMax.getText());
                loadProductByStockMax(integerValueMax);
            break;
            
            case 10 : 
                integerValueMin = Integer.parseInt(jTextFieldValueMin.getText());
                integerValueMax = Integer.parseInt(jTextFieldValueMax.getText());
                loadProductBetweenStock(integerValueMin, integerValueMax);
            break;
            
                
            
        }
    }//GEN-LAST:event_jButtonSearchProductActionPerformed

    private void jComboBoxSearchProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSearchProductItemStateChanged

        
       jTextFieldSearch.setText("");
       jTextFieldValueMin.setText("");
       jTextFieldValueMax.setText("");
       int index = jComboBoxSearchProduct.getSelectedIndex();
       
        
        
       
       switch(index){
           
           case 0 : loadProductDynamic(null);
               setEditableFieldProduct(false, false, false);
           
               break;
               
           case 1 : setEditableFieldProduct(true, false, false);
               
               break;
           
           case 2 : setEditableFieldProduct(true, false, false);
               
               break;
          
           case 3 : setEditableFieldProduct(false, true, false);
               
               break;
           
           case 4 : setEditableFieldProduct(false, true, false);
               
               break;
            
           case 5 : setEditableFieldProduct(false, false, true);
               
               break;
               
           case 6 : setEditableFieldProduct(false, true, true);
               
               break;
               
           case 7 : setEditableFieldProduct(false, true, false);
               
               break;
               
           case 8 : setEditableFieldProduct(false, true, false);

               break;
               
           case 9 : setEditableFieldProduct(false, false, true);
               
               break;
               
           case 10 :setEditableFieldProduct(false, true, true);
               break;
       }
        
    }//GEN-LAST:event_jComboBoxSearchProductItemStateChanged

    private void jButtonEmployeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmployeSearchActionPerformed
        
        int index = jComboBoxEmployeSearch.getSelectedIndex();
        enter = jTextFieldEmployeSearch.getText();
        String enterMax = jTextFieldYearMax.getText() + "/"+ jTextFieldMonthMax.getText() + "/" + jTextFieldDayMax.getText();
        String enterMin = jTextFieldYearMin.getText() + "/"+ jTextFieldMonthMin.getText() + "/" + jTextFieldDayMin.getText();
        
        switch(index){
            
            
            case 0 :
                if( jRadioButtonAdministrator.isSelected() ){
                    loadAdministratorByName(enter);
                    
                }else if(jRadioButtonCashier.isSelected()){
                    loadCashierByName(enter);
                }else{
                    loadEmployeByName(enter);
                }
            break;
            
            case 1 : 
                loadEmployeByDni(enter);
                
            break;
            
            case 2:
                loadEmployeByUsername(enter);
            break;
            
            case 3:
                 if(jRadioButtonAdministrator.isSelected() ){
                     loadAdministratorByDate(enter);
                     
                 }else if(jRadioButtonCashier.isSelected()){
                     loadCashierByDate(enter);
                     
                 }else{
                     loadEmployeByDate(enter);
                 }
            break;
            
            case 4:
                if(jRadioButtonAdministrator.isSelected()){
                    loadAdministratorDateBetween(enterMin, enterMax);
                    
                }else if(jRadioButtonCashier.isSelected()){
                    loadCashierDateBetween(enterMin, enterMax);
                    
                }else{
                    loadEmployeBetween(enterMin, enterMax);
                }
            break;
            
            case 5:
                if(jRadioButtonAdministrator.isSelected()){
                    loadAdministratorByDateMin(enterMin);
                    
                }else if(jRadioButtonCashier.isSelected()){
                    loadCashierByDateMin(enterMin);
                    
                }else{
                    loadEmployeByDateMin(enterMin);
                    
                }
            break;
            
            case 6:
                if(jRadioButtonAdministrator.isSelected()){
                    loadAdministratorByDateMax(enterMax);
                    
                }else if(jRadioButtonCashier.isSelected()){
                    loadCashierByDateMax(enterMax);
                    
                }else{
                    loadEmployeByDateMax(enterMax);
                    
                }
            break;    
        }
    }//GEN-LAST:event_jButtonEmployeSearchActionPerformed

    private void jRadioButtonAdministratorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonAdministratorItemStateChanged
        
        if(jRadioButtonAdministrator.isSelected()){
            
           loadEmployeDynamic( listEmployeAdministrator() );
            
        }

        
    }//GEN-LAST:event_jRadioButtonAdministratorItemStateChanged

    private void jRadioButtonCashierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonCashierItemStateChanged
        
        if( jRadioButtonCashier.isSelected() ){
            
            loadEmployeDynamic( listEmployeCashier() ) ;
        }
        
    }//GEN-LAST:event_jRadioButtonCashierItemStateChanged

    private void jRadioButtonAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonAllItemStateChanged
        
        
        if( jRadioButtonAll.isSelected() ){
            
            loadEmployeDynamic(null) ;
        }
    }//GEN-LAST:event_jRadioButtonAllItemStateChanged

    private void jComboBoxEmployeSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxEmployeSearchItemStateChanged
       
        jTextFieldMonthMax.setText("");
        jTextFieldMonthMin.setText("");
        jTextFieldDayMax.setText("");
        jTextFieldDayMin.setText("");
        jTextFieldYearMax.setText("");
        jTextFieldYearMin.setText("");
        jTextFieldEmployeSearch.setText("");
        setEditableFieldEmploye();
    }//GEN-LAST:event_jComboBoxEmployeSearchItemStateChanged

    private void jComboBoxSalesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSalesItemStateChanged
       
        jTextFieldSales.setText("");
        jTextFieldMinValue0.setText("");
        jTextFieldMinValue1.setText("");
        jTextFieldMinValue2.setText("");
        jTextFieldMaxValue0.setText("");
        jTextFieldMaxValue1.setText("");
        jTextFieldMaxValue2.setText("");
        setEditableFieldSale();
        
        
    }//GEN-LAST:event_jComboBoxSalesItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        enter = jTextFieldSales.getText();
        
        String enterValueMin0 = jTextFieldMinValue0.getText();
        String enterValueMin1 = jTextFieldMinValue1.getText();
        String enterValueMin2 = jTextFieldMinValue2.getText();
        
        String enterValueMax0 = jTextFieldMaxValue0.getText();
        String enterValueMax1 = jTextFieldMaxValue1.getText();
        String enterValueMax2 = jTextFieldMaxValue2.getText();
        
        int index = jComboBoxSales.getSelectedIndex();
        
        switch(index){
            
            case 0: 
                loadSaleDynamic(null);
                break;
            
            
            case 1:
                searchSaleByID(Integer.parseInt(enterValueMin0));
                break;
                
            case 2 :
                searchSaleByDniEmploye(enter);
                break;
            
            case 3:
                searchSaleByCost(Double.parseDouble(enterValueMin0));
                break;
                
            case 4:
                searchSaleByCostMin(Double.parseDouble(enterValueMin0));
                break;
                
            case 5:
                searchSaleByCostMax(Double.parseDouble(enterValueMax0));
                break;
                
            case 6:
                searchSaleByCostBetween(Double.parseDouble(enterValueMin0), Double.parseDouble(enterValueMax0));
                break;
                
            case 7:
                searchSaleByProductQuantity(Integer.parseInt(enterValueMin0));
                break;
                
            case 8:
                searchSaleByProductQuantityMin(Integer.parseInt(enterValueMin0));
                break;
                
            case 9:
                searchSaleByProductQuantityMax(Integer.parseInt(enterValueMax0));
                break;
                
            case 10:
                searchSaleByProductQuantityBetween(Integer.parseInt(enterValueMin0), Integer.parseInt(enterValueMax0));
                break;
                
            case 11:
                searchSaleByDate(enterValueMin0 + "/" + enterValueMin1 + "/" + enterValueMin2);
                break;
                
            case 12:
                 searchSaleByDateMin(enterValueMin0 + "/" + enterValueMin1 + "/" + enterValueMin2);
                 break;
            
            case 13:
                searchSaleByDateMax(enterValueMax0 + "/" + enterValueMax1 + "/" + enterValueMax2);
                break;
                
            case 14:
                searchSaleByDateBetween(enterValueMin0 + "/" + enterValueMin1 + "/" + enterValueMin2
                        , enterValueMax0 + "/" + enterValueMax1 + "/" + enterValueMax2);
                break;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
       
       jTextAreaInvoice.removeAll();
       reafreshInvoice();
        
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        
        int indexProductInList = jListInvoice.getSelectedIndex();
        double priceProductInList = addListProductModel.get(indexProductInList).getPrecioProducto();
        String valueElementSelect = listModel.get(indexProductInList);
        
        String[] spaceSplit = GestionUtil.splitBySpace(valueElementSelect);
        String quantity = spaceSplit[0];
        
        priceProductInList = priceProductInList * Double.parseDouble( quantity.substring(1) );
        total = total - priceProductInList;
        
        listModel.remove(indexProductInList);
        addListProductModel.remove(indexProductInList);
        integerList.remove(indexProductInList);
        
        total = Math.round(total * 100.0) / 100.0;
        jLabelPriceValue.setText( total + " " + MoneyChange.EURO.getMoney());
        
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jButtonToPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonToPayActionPerformed
      
        Date date = new Date();
        SimpleDateFormat formatDate =  new SimpleDateFormat("E_YYYY_MM_DD  hh_mm_ss a zzz");
        String nameFile = employeSession.getDni() + " " + formatDate.format(date)+"Sale" + ".txt";
        File file = new File("src\\com\\developercfk\\file\\"+nameFile);
        
        GestionUtil.createFile(file);
        
        for (int i = 0; i < addListProductModel.size(); i++) {
            
            jTextAreaInvoice.append(listModel.get(i));
            jTextAreaInvoice.append("\n");
            Producto p = productDao.readProduct(addListProductModel.get(i).getIdproducto());
            int qty = integerList.get(i);
            updateQuantityProductInDataBase(p, qty);
        }
        
        
        createSale(addListProductModel);
        jTextAreaInvoice.append("\n");
        jTextAreaInvoice.append( jLabelPriceValue.getText() );
        GestionUtil.writeFile(file, jTextAreaInvoice.getText(), false);
        total = 0.0;
        listModel.removeAllElements();
        addListProductModel.clear();
        integerList.clear();
        
        
    }//GEN-LAST:event_jButtonToPayActionPerformed

    private void jButtonNewEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewEmployeActionPerformed
      
        AddNewEmployeButton("Add Employe");
        
    }//GEN-LAST:event_jButtonNewEmployeActionPerformed

    private void jButtonEditEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditEmployeActionPerformed
        
        updateEmployeButton("Update Employe");
    }//GEN-LAST:event_jButtonEditEmployeActionPerformed

    private void jButtonDeleteEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteEmployeActionPerformed
        deleteEmployeButton("Remove Employe");
    }//GEN-LAST:event_jButtonDeleteEmployeActionPerformed

    private void jButtonNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewProductActionPerformed
        
        addNewProductButton("Add Product");
    }//GEN-LAST:event_jButtonNewProductActionPerformed

    private void jButtonEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditProductActionPerformed
        updateProductButton("Edit product");
    }//GEN-LAST:event_jButtonEditProductActionPerformed

    private void jButtonDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteProductActionPerformed
        deleteProductButton("Remove product");
    }//GEN-LAST:event_jButtonDeleteProductActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupEmployeSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDeleteEmploye;
    private javax.swing.JButton jButtonDeleteProduct;
    private javax.swing.JButton jButtonEditEmploye;
    private javax.swing.JButton jButtonEditProduct;
    private javax.swing.JButton jButtonEmployeSearch;
    private javax.swing.JButton jButtonNewEmploye;
    private javax.swing.JButton jButtonNewProduct;
    private javax.swing.JButton jButtonPrintInvoiceHome;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSearchProduct;
    private javax.swing.JButton jButtonToPay;
    private javax.swing.JComboBox<String> jComboBoxEmployeSearch;
    private javax.swing.JComboBox<String> jComboBoxSales;
    private javax.swing.JComboBox<String> jComboBoxSearchProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelInvoice;
    private javax.swing.JLabel jLabelMaxDateEmploye;
    private javax.swing.JLabel jLabelMaxValue;
    private javax.swing.JLabel jLabelMinDateEmploye;
    private javax.swing.JLabel jLabelMinValue;
    private javax.swing.JLabel jLabelPriceValue;
    private javax.swing.JLabel jLabelProductID;
    private javax.swing.JLabel jLabelTextTotal;
    private javax.swing.JLabel jLabelUserConnect;
    private javax.swing.JLabel jLabelValueSaleMax;
    private javax.swing.JLabel jLabelValueSaleMin;
    private javax.swing.JList<String> jListInvoice;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButtonAdministrator;
    private javax.swing.JRadioButton jRadioButtonAll;
    private javax.swing.JRadioButton jRadioButtonCashier;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableDataEmploye;
    private javax.swing.JTable jTableDateSale;
    private javax.swing.JTable jTableProductDefault;
    private javax.swing.JTextArea jTextAreaInvoice;
    private javax.swing.JTextField jTextFieldDayMax;
    private javax.swing.JTextField jTextFieldDayMin;
    private javax.swing.JTextField jTextFieldEmployeSearch;
    private javax.swing.JTextField jTextFieldMaxValue0;
    private javax.swing.JTextField jTextFieldMaxValue1;
    private javax.swing.JTextField jTextFieldMaxValue2;
    private javax.swing.JTextField jTextFieldMinValue0;
    private javax.swing.JTextField jTextFieldMinValue1;
    private javax.swing.JTextField jTextFieldMinValue2;
    private javax.swing.JTextField jTextFieldMonthMax;
    private javax.swing.JTextField jTextFieldMonthMin;
    private javax.swing.JTextField jTextFieldProductID;
    private javax.swing.JTextField jTextFieldSales;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldValueMax;
    private javax.swing.JTextField jTextFieldValueMin;
    private javax.swing.JTextField jTextFieldYearMax;
    private javax.swing.JTextField jTextFieldYearMin;
    // End of variables declaration//GEN-END:variables

    
}

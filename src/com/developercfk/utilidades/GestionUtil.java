
package com.developercfk.utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author CFK Local
 */
public class GestionUtil {

    public GestionUtil() {
    }
    
    
    public static boolean intercambiarJPanel(JFrame jFrame, JPanel jPanelCurrent, JPanel jPanelSwich){
        boolean result = false;
        
        if(jFrame != null && jPanelCurrent != null && jPanelSwich != null ){
            
            jPanelCurrent.setVisible(false);
            jFrame.setContentPane(jPanelSwich);
            jPanelSwich.setVisible(true);
            jFrame.setSize(jPanelSwich.getPreferredSize());
        }else{
            
            JOptionPane.showMessageDialog(null, "It seems that at least one component is null", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        
        return result;
    }
    
    public static String messageSucceful(Object object,Operation operation, Tabla tabla){
        
        String message;
        
        if( object != null && operation.equals( Operation.CREATE )  && tabla.equals(Tabla.ADMINISTRATOR) ){
          
            message = "Administrator create successfully"+ object.toString();
            
        }else if( object != null && operation.equals( Operation.READ )  && tabla.equals(Tabla.ADMINISTRATOR) ){
            
            message = "Loading admin complete" + object.toString();
            
        }else if( object != null && operation.equals( Operation.UPDATE )  && tabla.equals(Tabla.ADMINISTRATOR) ){
            
            message = "Successful admin update" + object.toString();
        
            
        }else if( object != null && operation.equals( Operation.DELETE )  && tabla.equals(Tabla.ADMINISTRATOR) ){   
            
            message = "Delete admin successfully" + object.toString();
        
            
            
        }else if(object != null && operation.equals(Operation.CREATE) && tabla.equals(Tabla.CASHIER)){
            
            message = "Cashier create successfully" + object.toString();
            
        }else if(object != null && operation.equals(Operation.READ) && tabla.equals(Tabla.CASHIER) ){
            
            message = "Loading cashier complete" + object.toString();
            
        }else if(object != null && operation.equals(Operation.UPDATE) && tabla.equals(Tabla.CASHIER)){
            
            message = "Successful cashier update" + object.toString();
            
        }else if(object != null && operation.equals(Operation.DELETE) && tabla.equals(Tabla.CASHIER)){
            
            message = "Delete cashier successfully" + object.toString();
          
            
            
        }else if( object != null && operation.equals(Operation.CREATE) && tabla.equals(Tabla.EMPLEADO) ){
            
            message = "Employ create successfully" + object.toString();
            
        }else if( object != null && operation.equals(Operation.READ) && tabla.equals(Tabla.EMPLEADO) ){
            
             message = "Loading Employ complete" + object.toString();
        }else if( object != null && operation.equals(Operation.UPDATE) && tabla.equals(Tabla.EMPLEADO) ){
            
            message = "Successful Employ update" + object.toString();
        }else if( object != null && operation.equals(Operation.DELETE) && tabla.equals(Tabla.EMPLEADO) ){
            
            message = "Delete Employ successfully" + object.toString();
            
            
        }else if( object != null && operation.equals(Operation.CREATE) && tabla.equals(Tabla.PRODUCT) ){
            
            message = "Product create successfully" + object.toString();
        }else if( object != null && operation.equals(Operation.READ) && tabla.equals(Tabla.PRODUCT) ){
            
             message = "Loading Product complete" + object.toString();
        }else if( object != null && operation.equals(Operation.UPDATE) && tabla.equals(Tabla.PRODUCT) ){
            
             message = "Successful Product update" + object.toString();
        }else if( object != null && operation.equals(Operation.DELETE) && tabla.equals(Tabla.PRODUCT) ){
            
            message = "Delete Product successfully" + object.toString();
            
            
        }else if( object != null && operation.equals(Operation.CREATE) && tabla.equals(Tabla.VENTA) ){
            
            message = "Sale create successfully" + object.toString();
        }else if( object != null && operation.equals(Operation.READ) && tabla.equals(Tabla.VENTA) ){
            
            message = "Loading Sale complete" + object.toString();
        }else if( object != null && operation.equals(Operation.UPDATE) && tabla.equals(Tabla.VENTA) ){
            
            message = "Successful Sale update" + object.toString();
        }else if( object != null && operation.equals(Operation.DELETE) && tabla.equals(Tabla.VENTA) ){
            
            message = "Delete Sale successfully" + object.toString();
            
            
            
        }else if(object != null && operation.equals(Operation.CREATE) && tabla.equals(Tabla.VENTA_Y_PRODUCTO)){
           
            message = "Invoice create successfully" + object.toString();
        }else if(object != null && operation.equals(Operation.READ) && tabla.equals(Tabla.VENTA_Y_PRODUCTO)){
            
            message = "Loading invoice complete" + object.toString();
        }else if(object != null && operation.equals(Operation.UPDATE) && tabla.equals(Tabla.VENTA_Y_PRODUCTO)){
            
            message = "Successful invoice update" + object.toString();
        }else if(object != null && operation.equals(Operation.DELETE) && tabla.equals(Tabla.VENTA_Y_PRODUCTO)){
            
            message = "Delete invoice successfully" + object.toString();
        }
        
        
        else {
            
            message = "Operation fail";
        }
        
        return message;
        
    }
    
    public static String[] splitBySpace(String string){
        
       String[] tab = string.split(" ");
        
       return tab;
    }
    
    public static void verificateIntegerFields(JTextField jtextfield){
        
        try {
            
            Integer.parseInt( jtextfield.getText() );
            
            
        } catch (NumberFormatException e) {
            
             JOptionPane.showMessageDialog(null, "Enter a valid integer number  e.g : 1,10,120... ", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void verificateDoubleFields(JTextField jtextfield){
        
        try {
            
            Double.parseDouble(jtextfield.getText() );
            
            
        } catch (NumberFormatException e) {
            
             JOptionPane.showMessageDialog(null, "Enter a valid decimal number  e.g : 1.0,10.0,120.0 ... ", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }
   
    public static void readFile(File file){
        
        String line;
        
        try {
            
            BufferedReader br = new BufferedReader( new FileReader(file) );
            
            while ( (line = br.readLine()) != null ) {                
                
                System.out.println(line);
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void writeFile(File file, String text,boolean append){
       
        BufferedWriter bw ;
        try {
            
            bw = new BufferedWriter(new FileWriter(file,append));

                if(file.length() == 0){
                 
                      bw.write(text);

                  }else{

                      bw.newLine();
                      bw.write(text);

                  }
            bw.close();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(GestionUtil.class.getName()).log(Level.SEVERE, null, ex);
            
        } 

        
    }

    public static void createFile(File file){
        
        try {
            boolean b = file.createNewFile();
            
            if(b){
                
                System.out.println("File Create...");
            }else{
                
                System.out.println("File No Create...");
            }
            
            
        } catch (IOException ex) {
           
            System.err.println(ex.getMessage());
        }
    }


}

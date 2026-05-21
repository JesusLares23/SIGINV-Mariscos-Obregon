/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.persistencia_orden;

import com.mycompany.config_mariscos.MongoClientProvider;

/**
 *
 * @author joser
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MongoClientProvider.INSTANCE.init(); 

    // 2. DESPUÉS: Instanciar el DAO
        OrdenDAO miDao = new OrdenDAO();
        
        try{
        miDao.insertarOrdenProvisional();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}

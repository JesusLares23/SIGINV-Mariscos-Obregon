/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.presentacion_mariscos;

/**
 *
 * @author demib
 */
public class NavegadorUI {
    private static javax.swing.JPanel pnlContainer;
    private static javax.swing.JLabel lblVentana;
    
    // se inicializa una sola vez desde MainFrame
    public static void init(javax.swing.JPanel container, javax.swing.JLabel ventana) {
        pnlContainer = container;
        lblVentana = ventana;
    }
    
    /**
     * 
     * 
     * @param panel  
     * @param titulo 
     */
    public static void navegar(javax.swing.JPanel panel, String titulo) {
        pnlContainer.removeAll();
        pnlContainer.add(panel, java.awt.BorderLayout.CENTER);
        lblVentana.setText(titulo);
        pnlContainer.revalidate();
        pnlContainer.repaint();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios_orden;

/**
 *
 * @author anapa
 */
import com.mycompany.dto_mariscos.OrdenDTO;

public class FachadaOrden {
    private IGestorOrden gestor = new GestorOrden();

    public OrdenDTO confirmarOrden(OrdenDTO orden) {
        return gestor.confirmarOrden(orden);
    }
    
}

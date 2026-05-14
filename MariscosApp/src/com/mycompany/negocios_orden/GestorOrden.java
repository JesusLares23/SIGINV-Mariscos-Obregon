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
public class GestorOrden implements IGestorOrden {
    @Override
    public OrdenDTO confirmarOrden(OrdenDTO orden) {

        System.out.println("Procesando orden...");
        System.out.println("Cliente: " + orden.getCliente());

        return orden;
}
    }


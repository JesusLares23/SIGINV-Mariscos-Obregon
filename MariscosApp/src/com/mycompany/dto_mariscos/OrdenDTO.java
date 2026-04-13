/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos;

/**
 *
 * @author anapa
 */
import java.util.List;
public class OrdenDTO {
    private String cliente;
    private List<Insumo> insumos;
    private double total;

    public OrdenDTO(String cliente, List<Insumo> insumos, double total) {
        this.cliente = cliente;
        this.insumos = insumos;
        this.total = total;
    }

    public String getCliente() { return cliente; }
    public List<Insumo> getInsumos() { return insumos; }
    public double getTotal() { return total; }
}
    


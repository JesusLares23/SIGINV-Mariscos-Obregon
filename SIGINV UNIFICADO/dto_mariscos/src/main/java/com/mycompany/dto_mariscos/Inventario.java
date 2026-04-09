/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos;


/**
 *
 * @author demib
 */
public class Inventario {
    //Lista del inventario de insumos
    private Insumo insumo;
    //Stock de cuanto se registro
    private double stockInicial;
    //Stock actual del insumo
    private double stockActual;
    //Stock minimo para marcarlo como stock bajo
    private double stockMinimo;

    public Inventario() {
    }

    public Inventario(Insumo insumo, double stockInicial, double stockActual, double stockMinimo) {
        this.insumo = insumo;
        this.stockInicial = stockInicial;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public double getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(double stockInicial) {
        this.stockInicial = stockInicial;
    }

    public double getStockActual() {
        return stockActual;
    }

    public void setStockActual(double stockActual) {
        this.stockActual = stockActual;
    }

    public double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    @Override
    public String toString() {
        return "Inventario{" + "insumo=" + insumo + ", stockInicial=" + stockInicial + ", stockActual=" + stockActual + ", stockMinimo=" + stockMinimo + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
}

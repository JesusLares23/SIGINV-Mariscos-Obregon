/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos;

/**
 * Clase para mapear items de órdenes en MongoDB
 */
public class ItemOrden {
    private String nombre;
    private Double cantidad;

    public ItemOrden() {
    }

    public ItemOrden(String nombre, Double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ItemOrden{" + "nombre=" + nombre + ", cantidad=" + cantidad + '}';
    }
}
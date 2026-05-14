/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiproveedores;

/**
 *
 * @author joser
 */
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private String unidadMedida;
    private String proveedor;

    public Producto() {
    }

    public Producto(int id, String nombre, String unidadMedida, String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precio = 0.0;
        this.unidadMedida = unidadMedida;
        this.proveedor = proveedor;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    
}

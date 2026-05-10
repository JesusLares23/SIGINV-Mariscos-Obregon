/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos;

/**
 *
 * @author demib
 */
public class Insumo {
    //Nombre Insumo
    private String nombre;
    //Kg,g,lt,pieza,
    private String unidadMedida;
    //Categoria del producto
    private String categoria;

    public Insumo() {
    }

    public Insumo(String nombre, String unidadMedida, String categoria) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Insumo{" + "nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", categoria=" + categoria + '}';
    }
    
    
    
    

    
    
    
}

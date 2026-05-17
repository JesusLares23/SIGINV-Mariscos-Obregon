/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos.mermas;


import java.time.LocalDateTime;

/**
 *
 * @author demib
 */
public class MermaDTO {
    private String id;
    
    private String nombreInsumo;
    
    private double cantPerdida;
    
    private String unidadMedida;
    
    private String causa;
    
    private String descripcion;
    
    private String ubicacion;
    
    private LocalDateTime fechaOcurrido;
    


    public MermaDTO() {
    }

    public MermaDTO(String id, String nombreInsumo, double cantPerdida, String unidadMedida, String causa, String descripcion, String ubicacion, LocalDateTime fechaOcurrido) {
        this.id = id;
        this.nombreInsumo = nombreInsumo;
        this.cantPerdida = cantPerdida;
        this.unidadMedida = unidadMedida;
        this.causa = causa;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fechaOcurrido = fechaOcurrido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public double getCantPerdida() {
        return cantPerdida;
    }

    public void setCantPerdida(double cantPerdida) {
        this.cantPerdida = cantPerdida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getFechaOcurrido() {
        return fechaOcurrido;
    }

    public void setFechaOcurrido(LocalDateTime fechaOcurrido) {
        this.fechaOcurrido = fechaOcurrido;
    }
    
    
    
    

    
}

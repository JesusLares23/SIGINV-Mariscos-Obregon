/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos.mermas;

import java.time.Instant;

/**
 *
 * @author joser
 */
public class InsumoDTO {
    private String id;
    //Nombre Insumo
    private String nombre;
    //Kg,g,lt,pieza,
    private String unidadMedida;
    //Categoria del producto
    private String categoria;
    
    private Instant createdAt;
    
    private Instant updatedAt;
    
    private String imagenUrl;

    private String qrUrl;

    public InsumoDTO(String id, String nombre, String unidadMedida, String categoria, Instant createdAt, Instant updatedAt, String imagenUrl, String qrUrl) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.categoria = categoria;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imagenUrl = imagenUrl;
        this.qrUrl = qrUrl;
    }

    public InsumoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    @Override
    public String toString() {
        return "InsumoDTO{" + "id=" + id + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", categoria=" + categoria + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", imagenUrl=" + imagenUrl + ", qrUrl=" + qrUrl + '}';
    }
    
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos.insumo;

import java.util.Date;

/**
 *
 * @author 52644
 */
public class InsumoDTO {

    private String id;
    private String nombre;
    private String unidadMedida;
    private String categoria;
    private String imagenUrl;
    private Date createdAt;
    private Date updatedAt;

    public InsumoDTO() {
    }

    public InsumoDTO(String nombre, String unidadMedida, String categoria, String imagenUrl) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "InsumoDTO{" + "nombre=" + nombre + ", categoria=" + categoria + '}';
    }
}

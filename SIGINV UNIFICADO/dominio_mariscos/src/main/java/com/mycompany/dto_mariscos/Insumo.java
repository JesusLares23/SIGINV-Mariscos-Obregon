/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos;

import java.time.Instant;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public class Insumo {
    private ObjectId _id;
    //Nombre Insumo
    private String nombre;
    //Kg,g,lt,pieza,
    private String unidadMedida;
    //Categoria del producto
    private String categoria;
    
    
    
    private Instant createdAt;
    
    private Instant updatedAt;
    
    //URL de la imagen alojada en Imgur
    @BsonProperty("imagenUrl")
    private String imagenUrl;

    public Insumo() {
    }

    public Insumo(ObjectId _id, String nombre, String unidadMedida, String categoria, String imagenUrl, Instant createdAt, Instant updatedAt) {
        this._id = _id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
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
    
    

    

    

    @Override
    public String toString() {
        return "Insumo{" + "_id=" + _id + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", categoria=" + categoria + '}';
    }
    
    

    
    
    
    
    

    
    
    
}

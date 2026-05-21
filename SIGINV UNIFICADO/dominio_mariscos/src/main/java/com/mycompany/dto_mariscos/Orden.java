package com.mycompany.dto_mariscos;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

/**
 * Entidad que representa una orden de compra ya generada.
 */
public class Orden {
    @BsonId
    private ObjectId _id;
    
    private int numeroOrden;
    private Instant fechaCreacion;
    private String responsable;       
    private String proveedor;         
    private String estado;            // "Pendiente", "Confirmada", "Pagada", etc.
    private String estadoFacturacion; // "Sin Facturar" / "Facturada"
    @BsonIgnore
    private Map<String, Double> items; // ObjectId de insumo y cantidad solicitada

    public Orden(ObjectId _id, int numeroOrden, Instant fechaCreacion, String responsable, String proveedor, String estado, String estadoFacturacion, Map<String, Double> items) {
        this._id = _id;
        this.numeroOrden = numeroOrden;
        this.fechaCreacion = fechaCreacion;
        this.responsable = responsable;
        this.proveedor = proveedor;
        this.estado = estado;
        this.estadoFacturacion = estadoFacturacion;
        this.items = items;
    }

    public Orden(int numeroOrden, Instant fechaCreacion, String responsable, String proveedor, String estado, String estadoFacturacion, Map<String, Double> items) {
        this.numeroOrden = numeroOrden;
        this.fechaCreacion = fechaCreacion;
        this.responsable = responsable;
        this.proveedor = proveedor;
        this.estado = estado;
        this.estadoFacturacion = estadoFacturacion;
        this.items = items;
    }

    

    public Orden() {
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoFacturacion() {
        return estadoFacturacion;
    }

    public void setEstadoFacturacion(String estadoFacturacion) {
        this.estadoFacturacion = estadoFacturacion;
    }

    public Map<String, Double> getItems() {
        return items;
    }

    public void setItems(Map<String, Double> items) {
        this.items = items;
    }

    

    @Override
    public String toString() {
        return "Orden{" + "numeroOrden=" + numeroOrden + ", fechaCreacion=" + fechaCreacion + ", responsable=" + responsable + ", proveedor=" + proveedor + ", estado=" + estado + ", estadoFacturacion=" + estadoFacturacion + ", items=" + items + '}';
    }

    
}
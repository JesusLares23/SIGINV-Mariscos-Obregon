/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos.mermas;

import com.mycompany.dto_mariscos.Insumo;
import java.time.Instant;
import java.util.Map;

/**
 *
 * @author joser
 */
public class OrdenDTO {
    private String id;
    private int numeroOrden;
    private Instant fechaCreacion;
    private String responsable;       
    private String proveedor;         
    private String estado;            // "Pendiente", "Confirmada", "Pagada", etc.
    private String estadoFacturacion; // "Sin Facturar" / "Facturada"
    private Map<Insumo, Double> items;

    public OrdenDTO(String id, int numeroOrden, Instant fechaCreacion, String responsable, String proveedor, String estado, String estadoFacturacion, Map<Insumo, Double> items) {
        this.id = id;
        this.numeroOrden = numeroOrden;
        this.fechaCreacion = fechaCreacion;
        this.responsable = responsable;
        this.proveedor = proveedor;
        this.estado = estado;
        this.estadoFacturacion = estadoFacturacion;
        this.items = items;
    }

    public OrdenDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Map<Insumo, Double> getItems() {
        return items;
    }

    public void setItems(Map<Insumo, Double> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrdenDTO{" + "id=" + id + ", numeroOrden=" + numeroOrden + ", fechaCreacion=" + fechaCreacion + ", responsable=" + responsable + ", proveedor=" + proveedor + ", estado=" + estado + ", estadoFacturacion=" + estadoFacturacion + ", items=" + items + '}';
    }
    
}
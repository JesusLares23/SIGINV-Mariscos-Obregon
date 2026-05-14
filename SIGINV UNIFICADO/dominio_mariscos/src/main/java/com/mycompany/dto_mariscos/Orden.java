package com.mycompany.dto_mariscos;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Entidad que representa una orden de compra ya generada.
 */
public class Orden {
    private int numeroOrden;
    private Date fechaCreacion;
    private String responsable;       
    private String proveedor;         
    private String estado;            // "Pendiente", "Confirmada", "Pagada", etc.
    private String estadoFacturacion; // "Sin Facturar" / "Facturada"
    private Map<Insumo, Double> items; // insumo y cantidad solicitada

    public Orden() {
        this.items = new LinkedHashMap<>();
        this.fechaCreacion = new Date();
        this.estado = "Pendiente";
        this.estadoFacturacion = "Sin Facturar";
    }

    public Orden(int numeroOrden, Date fechaCreacion, String responsable, String proveedor, String estado, String estadoFacturacion, Map<Insumo, Double> items) {
        this.numeroOrden = numeroOrden;
        this.fechaCreacion = fechaCreacion;
        this.responsable = responsable;
        this.proveedor = proveedor;
        this.estado = estado;
        this.estadoFacturacion = estadoFacturacion;
        this.items = items;
    }




    // Getters y Setters
    
    public String getEstadoFacturacion() {
    return estadoFacturacion;}

    public void setEstadoFacturacion(String estadoFacturacion) {
        this.estadoFacturacion = estadoFacturacion;}
    public int getNumeroOrden() { return numeroOrden; }
    public void setNumeroOrden(int numeroOrden) { this.numeroOrden = numeroOrden; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Map<Insumo, Double> getItems() { return items; }
    public void setItems(Map<Insumo, Double> items) { this.items = items; }

    @Override
    public String toString() {
        return "Orden{" + "numeroOrden=" + numeroOrden + ", proveedor=" + proveedor + ", estado=" + estado + ", items=" + items + '}';
    }
}
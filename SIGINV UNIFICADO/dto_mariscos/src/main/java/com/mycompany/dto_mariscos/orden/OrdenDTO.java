/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos.orden;

import com.mycompany.dto_mariscos.insumo.InsumoDTO;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 *
 * @author 52644
 */
public class OrdenDTO {

    private int numeroOrden;
    private Date fechaCreacion;
    private String responsable;
    private String proveedor;
    private String estado;
    private String estadoFacturacion;
    private Map<InsumoDTO, Double> items;

    public OrdenDTO() {
        this.items = new LinkedHashMap<>();
    }

    public OrdenDTO(int numeroOrden, Date fechaCreacion, String responsable, String proveedor,
            String estado, String estadoFacturacion, Map<InsumoDTO, Double> items) {
        this.numeroOrden = numeroOrden;
        this.fechaCreacion = fechaCreacion;
        this.responsable = responsable;
        this.proveedor = proveedor;
        this.estado = estado;
        this.estadoFacturacion = estadoFacturacion;
        this.items = items;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
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

    public Map<InsumoDTO, Double> getItems() {
        return items;
    }

    public void setItems(Map<InsumoDTO, Double> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrdenDTO{" + "numeroOrden=" + numeroOrden + ", proveedor=" + proveedor
                + ", estado=" + estado + ", estadoFacturacion=" + estadoFacturacion + '}';
    }
}

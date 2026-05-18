/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos.solicitudFacturas;

import java.util.Date;

/**
 *
 * @author 52644
 */

public class SolicitudFacturaDTO {

    private String id;
    private int numeroOrden;
    private String estadoFactura;
    private Date fechaSolicitud;
    private String usoCFDI;
    private String rfc;
    private String razonSocial;
    private String regimenFiscal;
    private String calle;
    private String codigoPostal;
    private String correo;
    private String numeroFactura;
    private Date fechaFactura;

    public SolicitudFacturaDTO() {
    }

    public SolicitudFacturaDTO(int numeroOrden, String estadoFactura, Date fechaSolicitud,
            String usoCFDI, String rfc, String razonSocial, String regimenFiscal,
            String calle, String codigoPostal, String correo) {
        this.numeroOrden = numeroOrden;
        this.estadoFactura = estadoFactura;
        this.fechaSolicitud = fechaSolicitud;
        this.usoCFDI = usoCFDI;
        this.rfc = rfc;
        this.razonSocial = razonSocial;
        this.regimenFiscal = regimenFiscal;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.correo = correo;
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

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getUsoCFDI() {
        return usoCFDI;
    }

    public void setUsoCFDI(String usoCFDI) {
        this.usoCFDI = usoCFDI;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    @Override
    public String toString() {
        return "SolicitudFacturaDTO{" + "numeroOrden=" + numeroOrden + ", estadoFactura=" + estadoFactura + '}';
    }
}

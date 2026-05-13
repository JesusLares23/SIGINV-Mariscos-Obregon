/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_mariscos;

import java.time.Instant;
import java.util.Date;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.time.Instant;
import java.util.Date;

public class SolicitudFactura {

    @BsonProperty("_id")
    private org.bson.types.ObjectId id;
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
    private String respuestaProveedor;
    private String numeroFactura;
    private Date fechaFactura;
    private Instant createdAt;
    private Instant updatedAt;

    public SolicitudFactura() {
        this.estadoFactura = "Sin Factura";
        this.fechaSolicitud = new Date();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public SolicitudFactura(int numeroOrden, String usoCFDI, String rfc, String razonSocial,
            String regimenFiscal, String calle, String codigoPostal, String correo) {
        this();
        this.numeroOrden = numeroOrden;
        this.usoCFDI = usoCFDI;
        this.rfc = rfc;
        this.razonSocial = razonSocial;
        this.regimenFiscal = regimenFiscal;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.correo = correo;
    }

    // Getters y Setters
    public org.bson.types.ObjectId getId() {
        return id;
    }

    public void setId(org.bson.types.ObjectId id) {
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

    public String getRespuestaProveedor() {
        return respuestaProveedor;
    }

    public void setRespuestaProveedor(String respuestaProveedor) {
        this.respuestaProveedor = respuestaProveedor;
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
        return "SolicitudFactura{" + "numeroOrden=" + numeroOrden
                + ", estadoFactura=" + estadoFactura + ", numeroFactura=" + numeroFactura + '}';
    }
}

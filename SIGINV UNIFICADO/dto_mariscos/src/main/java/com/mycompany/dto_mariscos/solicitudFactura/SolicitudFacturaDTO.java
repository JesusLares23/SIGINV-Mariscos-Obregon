package com.mycompany.dto_mariscos.solicitudFactura;

import java.util.Date;

/**
 *
 * @author 52644
 */
public class SolicitudFacturaDTO {

    private String id;
    private String Orden_id;
    private String rfc;
    private String razonSocial;
    private String usoCFDI;
    private String regimenFiscal;
    private String calle;
    private String colonia;
    private String cp;
    private String correo;
    private String estado; // "Sin Facturar", "Pendiente", "Facturado"
    private Date fechaSolicitud;

    public SolicitudFacturaDTO() {
    }

    public SolicitudFacturaDTO(String id, String Orden_id, String rfc, String razonSocial, String usoCFDI, String regimenFiscal, String calle, String colonia, String cp, String correo, String estado, Date fechaSolicitud) {
        this.id = id;
        this.Orden_id = Orden_id;
        this.rfc = rfc;
        this.razonSocial = razonSocial;
        this.usoCFDI = usoCFDI;
        this.regimenFiscal = regimenFiscal;
        this.calle = calle;
        this.colonia = colonia;
        this.cp = cp;
        this.correo = correo;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrden_id() {
        return Orden_id;
    }

    public void setOrden_id(String Orden_id) {
        this.Orden_id = Orden_id;
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

    public String getUsoCFDI() {
        return usoCFDI;
    }

    public void setUsoCFDI(String usoCFDI) {
        this.usoCFDI = usoCFDI;
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

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
    
}
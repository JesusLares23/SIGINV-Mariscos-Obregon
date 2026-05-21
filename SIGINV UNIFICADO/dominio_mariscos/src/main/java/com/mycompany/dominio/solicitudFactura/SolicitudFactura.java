package com.mycompany.dominio.solicitudFactura;

import java.util.Date;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

/**
 *
 * @author 52644
 */

public class SolicitudFactura {

    @BsonId
    private ObjectId _id;
    private ObjectId Orden_id;
    private String rfc;
    private String razonSocial;
    private String usoCFDI;
    private String regimenFiscal;
    private Direccion direccion;
    private String correo; 
    private String estado; // "Sin Facturar", "Pendiente", "Facturado"
    private Date fechaSolicitud;

    public SolicitudFactura() {
        this.fechaSolicitud = new Date();
      
    }

    public SolicitudFactura(ObjectId _id, ObjectId Orden_id, String rfc, String razonSocial, String usoCFDI, String regimenFiscal, Direccion direccion, String correo, String estado, Date fechaSolicitud) {
        this._id = _id;
        this.Orden_id = Orden_id;
        this.rfc = rfc;
        this.razonSocial = razonSocial;
        this.usoCFDI = usoCFDI;
        this.regimenFiscal = regimenFiscal;
        this.direccion = direccion;
        this.correo = correo;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }



    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getOrden_id() {
        return Orden_id;
    }

    public void setOrden_id(ObjectId Orden_id) {
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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

    @Override
    public String toString() {
        return "SolicitudFactura{" + "_id=" + _id + ", Orden_id=" + Orden_id + ", rfc=" + rfc + ", razonSocial=" + razonSocial + ", usoCFDI=" + usoCFDI + ", regimenFiscal=" + regimenFiscal + ", direccion=" + direccion + ", correo=" + correo + ", estado=" + estado + ", fechaSolicitud=" + fechaSolicitud + '}';
    }

  

}

package com.mycompany.dominio.solicitudFactura;

import java.util.Date;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class SolicitudFactura {

    @BsonProperty("_id")
    private ObjectId id;  
    private String rfc;
    private String pedido_Id;       // ID del pedido asociado
    private String usoCFDI;
    private String razonSocial;
    private Direccion direccion;    // Objeto anidado para dirección
    private String correo;
    private String estado;          // Estado de la factura ("sin factura", "facturado")
    private String regimenFiscal;
    private Date fechaSolicitud;    // Fecha en que se genera la solicitud

    public SolicitudFactura() {}

    public SolicitudFactura(ObjectId id, String rfc, String pedido_Id, String usoCFDI,
                            String razonSocial, Direccion direccion, String correo,
                            String estado, String regimenFiscal, Date fechaSolicitud) {
        this.id = id;
        this.rfc = rfc;
        this.pedido_Id = pedido_Id;
        this.usoCFDI = usoCFDI;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.correo = correo;
        this.estado = estado;
        this.regimenFiscal = regimenFiscal;
        this.fechaSolicitud = fechaSolicitud;
    }

    // 🔑 Getters y Setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    public String getPedido_Id() { return pedido_Id; }
    public void setPedido_Id(String pedido_Id) { this.pedido_Id = pedido_Id; }

    public String getUsoCFDI() { return usoCFDI; }
    public void setUsoCFDI(String usoCFDI) { this.usoCFDI = usoCFDI; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getRegimenFiscal() { return regimenFiscal; }
    public void setRegimenFiscal(String regimenFiscal) { this.regimenFiscal = regimenFiscal; }

    public Date getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(Date fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    @Override
    public String toString() {
        return "SolicitudFactura{" +
                "id=" + id +
                ", rfc=" + rfc +
                ", pedido_Id=" + pedido_Id +
                ", usoCFDI=" + usoCFDI +
                ", razonSocial=" + razonSocial +
                ", direccion=" + direccion +
                ", correo=" + correo +
                ", estado=" + estado +
                ", regimenFiscal=" + regimenFiscal +
                ", fechaSolicitud=" + fechaSolicitud +
                '}';
    }
}

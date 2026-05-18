package com.mycompany.dto_mariscos.solicitudFactura;

import java.util.Date;

public class SolicitudFacturaDTO {

    private String id;            // ID en Mongo (ObjectId en string)
    private String idPedido;      // Número de pedido asociado
    private String rfc;
    private String razonSocial;   // Proveedor o razón social
    private String usoCFDI;
    private String regimenFiscal;
    private String calle;
    private String colonia;       // Se mapea a "ciudad" en dominio
    private String cp;
    private String correo;
    private Date fechaSolicitud;  // Fecha en que se genera la solicitud
    private String estadoFactura; // "sin factura" / "facturado"

    // 🔑 Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdPedido() { return idPedido; }
    public void setIdPedido(String idPedido) { this.idPedido = idPedido; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getUsoCFDI() { return usoCFDI; }
    public void setUsoCFDI(String usoCFDI) { this.usoCFDI = usoCFDI; }

    public String getRegimenFiscal() { return regimenFiscal; }
    public void setRegimenFiscal(String regimenFiscal) { this.regimenFiscal = regimenFiscal; }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

    public String getCp() { return cp; }
    public void setCp(String cp) { this.cp = cp; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Date getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(Date fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getEstadoFactura() { return estadoFactura; }
    public void setEstadoFactura(String estadoFactura) { this.estadoFactura = estadoFactura; }
}

package com.mycompany.controller_mariscos.solicitudFactura;

import com.mycompany.dto_mariscos.solicitudFactura.SolicitudFacturaDTO;
import com.mycompany.dominio.solicitudFactura.SolicitudFactura;
import com.mycompany.dominio.solicitudFactura.Direccion;
import com.mycompany.persistencia_mariscos.solicitudFactura.SolicitudFacturaDAO;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;

public class SolicitudFacturaControl implements ISolicitudFacturaControl {

    private final SolicitudFacturaDAO dao;

    public SolicitudFacturaControl() {
        this.dao = new SolicitudFacturaDAO();
    }

    @Override
    public void guardar(SolicitudFacturaDTO dto) throws DaoException {
        SolicitudFactura solicitud = convertirADominio(dto);
        dao.guardar(solicitud);
    }

    @Override
    public SolicitudFacturaDTO buscarPorId(String id) throws DaoException, EntityNotFoundException {
        SolicitudFactura solicitud = dao.buscarPorId(new ObjectId(id));
        return convertirADTO(solicitud);
    }

    @Override
    public List<SolicitudFacturaDTO> obtenerTodas() throws DaoException {
        List<SolicitudFactura> lista = dao.obtenerTodas();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizar(SolicitudFacturaDTO dto) throws DaoException, EntityNotFoundException {
        SolicitudFactura solicitud = convertirADominio(dto);
        solicitud.setId(new ObjectId(dto.getId())); 
        dao.actualizar(solicitud);
    }


    private SolicitudFactura convertirADominio(SolicitudFacturaDTO dto) {
        SolicitudFactura solicitud = new SolicitudFactura();
        if (dto.getId() != null) {
            solicitud.setId(new ObjectId(dto.getId()));
        }
        solicitud.setPedido_Id(dto.getIdPedido());
        solicitud.setRfc(dto.getRfc());
        solicitud.setRazonSocial(dto.getRazonSocial());
        solicitud.setUsoCFDI(dto.getUsoCFDI());
        solicitud.setRegimenFiscal(dto.getRegimenFiscal());
        solicitud.setCorreo(dto.getCorreo());
        solicitud.setEstado(dto.getEstadoFactura());
        solicitud.setFechaSolicitud(dto.getFechaSolicitud());

   
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setCiudad(dto.getColonia()); 
        direccion.setCp(dto.getCp());
        solicitud.setDireccion(direccion);

        return solicitud;
    }

    private SolicitudFacturaDTO convertirADTO(SolicitudFactura solicitud) {
        SolicitudFacturaDTO dto = new SolicitudFacturaDTO();
        dto.setId(solicitud.getId() != null ? solicitud.getId().toHexString() : null);
        dto.setIdPedido(solicitud.getPedido_Id());
        dto.setRfc(solicitud.getRfc());
        dto.setRazonSocial(solicitud.getRazonSocial());
        dto.setUsoCFDI(solicitud.getUsoCFDI());
        dto.setRegimenFiscal(solicitud.getRegimenFiscal());
        dto.setCorreo(solicitud.getCorreo());
        dto.setEstadoFactura(solicitud.getEstado());
        dto.setFechaSolicitud(solicitud.getFechaSolicitud());

    
        if (solicitud.getDireccion() != null) {
            dto.setCalle(solicitud.getDireccion().getCalle());
            dto.setColonia(solicitud.getDireccion().getCiudad());
            dto.setCp(solicitud.getDireccion().getCp());
        }

        return dto;
    }
}

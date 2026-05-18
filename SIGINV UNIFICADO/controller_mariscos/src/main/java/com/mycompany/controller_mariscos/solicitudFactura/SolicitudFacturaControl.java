/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller_mariscos.solicitudFactura;

import com.mycompany.controller_mariscos.orden.IOrdenControl;
import com.mycompany.controller_mariscos.orden.OrdenControl;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.dto_mariscos.SolicitudFactura;
import com.mycompany.dto_mariscos.SolicitudFacturaDTO;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import com.mycompany.persistencia_mariscos.orden.IOrdenDAO;
import com.mycompany.persistencia_mariscos.orden.OrdenDAO;
import com.mycompany.persistencia_mariscos.solicitudFactura.ISolicitudFacturaDAO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;



/**
 *
 * @author 52644
 */
public class SolicitudFacturaControl implements ISolicitudFacturaControl {

    private ISolicitudFacturaDAO solicitudFacturaDAO;
    private IOrdenDAO ordenDAO;
    private IOrdenControl ordenControl;

    public SolicitudFacturaControl(ISolicitudFacturaDAO solicitudFacturaDAO) {
        this.solicitudFacturaDAO = solicitudFacturaDAO;
        this.ordenDAO = new OrdenDAO();
        this.ordenControl = new OrdenControl(ordenDAO);
    }

    @Override
    public void crearSolicitudFactura(SolicitudFacturaDTO solicitudDTO) throws DaoException, EntityNotFoundException {
        int numeroOrden = solicitudDTO.getNumeroOrden();

        Orden orden = ordenControl.obtenerOrdenPorNumero(numeroOrden);

        if (!"Revisado".equals(orden.getEstado())) {
            throw new DaoException("La orden " + numeroOrden + " no ha sido revisada");
        }

        if ("Facturado".equals(orden.getEstadoFacturacion())) {
            throw new DaoException("La orden " + numeroOrden + " ya ha sido facturada");
        }

        SolicitudFactura solicitud = convertirDTOAEntidad(solicitudDTO);

        if (solicitud.getNumeroOrden() <= 0) {
            throw new IllegalArgumentException("El número de orden es obligatorio");
        }
        if (solicitud.getRfc() == null || solicitud.getRfc().isEmpty()) {
            throw new IllegalArgumentException("El RFC es obligatorio");
        }

        solicitudFacturaDAO.create(solicitud);
    }

    @Override
    public SolicitudFacturaDTO obtenerSolicitudFacturaPorNumeroOrden(int numeroOrden) throws DaoException {
        SolicitudFactura solicitud = solicitudFacturaDAO.findByNumeroOrden(numeroOrden)
                .orElseThrow(() -> new RuntimeException("SolicitudFactura no encontrada para la orden: " + numeroOrden));
        return convertirADTO(solicitud);
    }

    @Override
    public List<SolicitudFacturaDTO> obtenerTodasSolicitudes() throws DaoException {
        List<SolicitudFactura> solicitudes = solicitudFacturaDAO.findAll();
        return convertirADTOs(solicitudes);
    }

    @Override
    public List<SolicitudFacturaDTO> obtenerSolicitudesPorEstado(String estado) throws DaoException {
        List<SolicitudFactura> solicitudes = solicitudFacturaDAO.findByEstadoFactura(estado);
        return convertirADTOs(solicitudes);
    }

    @Override
    public List<SolicitudFacturaDTO> obtenerOrdenesFacturables(int ano, String ordenamiento) throws DaoException {
        List<SolicitudFactura> solicitudes = solicitudFacturaDAO.findByAno(ano);

        solicitudes = solicitudes.stream()
                .filter(s -> "Sin Facturar".equals(s.getEstadoFactura()))
                .collect(Collectors.toList());

        if ("antiguo".equalsIgnoreCase(ordenamiento)) {
            solicitudes.sort(Comparator.comparing(SolicitudFactura::getFechaSolicitud));
        } else if ("nuevo".equalsIgnoreCase(ordenamiento)) {
            solicitudes.sort(Comparator.comparing(SolicitudFactura::getFechaSolicitud).reversed());
        }

        return convertirADTOs(solicitudes);
    }

    @Override
    public List<SolicitudFacturaDTO> paginarOrdenesFacturables(List<SolicitudFacturaDTO> solicitudes, int numeroPagina, int registrosPorPagina) {
        int inicio = (numeroPagina - 1) * registrosPorPagina;
        int fin = Math.min(inicio + registrosPorPagina, solicitudes.size());

        if (inicio >= solicitudes.size()) {
            return new ArrayList<>();
        }

        return solicitudes.subList(inicio, fin);
    }

    @Override
    public void actualizarSolicitudFactura(SolicitudFacturaDTO solicitudDTO) throws DaoException, EntityNotFoundException {
        SolicitudFactura solicitud = convertirDTOAEntidad(solicitudDTO);

        boolean actualizado = solicitudFacturaDAO.update(solicitud);
        if (!actualizado) {
            throw new RuntimeException("No se pudo actualizar la solicitud de factura");
        }
    }

    @Override
    public void eliminarSolicitudFactura(String id) throws DaoException, EntityNotFoundException {
        ObjectId objectId = new ObjectId(id);
        solicitudFacturaDAO.deleteById(objectId);
    }

    @Override
    public void cambiarEstadoFacturaAEnviada(int numeroOrden) throws DaoException, EntityNotFoundException {
        SolicitudFactura solicitud = solicitudFacturaDAO.findByNumeroOrden(numeroOrden)
                .orElseThrow(() -> new RuntimeException("SolicitudFactura no encontrada para la orden: " + numeroOrden));
        solicitud.setEstadoFactura("Facturado");
        solicitudFacturaDAO.update(solicitud);
        ordenControl.cambiarEstadoFacturacion(numeroOrden, "Facturado");
    }

    private SolicitudFacturaDTO convertirADTO(SolicitudFactura solicitud) {
        SolicitudFacturaDTO dto = new SolicitudFacturaDTO(
                solicitud.getNumeroOrden(),
                solicitud.getEstadoFactura(),
                solicitud.getFechaSolicitud(),
                solicitud.getUsoCFDI(),
                solicitud.getRfc(),
                solicitud.getRazonSocial(),
                solicitud.getRegimenFiscal(),
                solicitud.getCalle(),
                solicitud.getCodigoPostal(),
                solicitud.getCorreo()
        );
        dto.setId(solicitud.getId().toString());
        dto.setNumeroFactura(solicitud.getNumeroFactura());
        dto.setFechaFactura(solicitud.getFechaFactura());
        return dto;
    }

    private List<SolicitudFacturaDTO> convertirADTOs(List<SolicitudFactura> solicitudes) {
        return solicitudes.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private SolicitudFactura convertirDTOAEntidad(SolicitudFacturaDTO dto) {
        SolicitudFactura solicitud = new SolicitudFactura(
                dto.getNumeroOrden(),
                dto.getUsoCFDI(),
                dto.getRfc(),
                dto.getRazonSocial(),
                dto.getRegimenFiscal(),
                dto.getCalle(),
                dto.getCodigoPostal(),
                dto.getCorreo()
        );
        if (dto.getId() != null && !dto.getId().isEmpty()) {
            solicitud.setId(new ObjectId(dto.getId()));
        }
        solicitud.setEstadoFactura(dto.getEstadoFactura());
        solicitud.setNumeroFactura(dto.getNumeroFactura());
        solicitud.setFechaFactura(dto.getFechaFactura());
        return solicitud;
    }
}

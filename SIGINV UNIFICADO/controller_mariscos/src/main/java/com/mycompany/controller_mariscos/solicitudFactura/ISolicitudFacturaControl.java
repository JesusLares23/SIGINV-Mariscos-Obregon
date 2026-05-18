/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controller_mariscos.solicitudFactura;


import com.mycompany.dto_mariscos.SolicitudFacturaDTO;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
/**
 *
 * @author 52644
 */
public interface ISolicitudFacturaControl {
    
    void crearSolicitudFactura(SolicitudFacturaDTO solicitudDTO) throws DaoException, EntityNotFoundException;
    
    SolicitudFacturaDTO obtenerSolicitudFacturaPorNumeroOrden(int numeroOrden) throws DaoException;
    
    List<SolicitudFacturaDTO> obtenerTodasSolicitudes() throws DaoException;
    
    List<SolicitudFacturaDTO> obtenerSolicitudesPorEstado(String estado) throws DaoException;
    
    List<SolicitudFacturaDTO> obtenerOrdenesFacturables(int ano, String ordenamiento) throws DaoException;
    
    List<SolicitudFacturaDTO> paginarOrdenesFacturables(List<SolicitudFacturaDTO> solicitudes, int numeroPagina, int registrosPorPagina);
    
    void actualizarSolicitudFactura(SolicitudFacturaDTO solicitudDTO) throws DaoException, EntityNotFoundException;
    
    void eliminarSolicitudFactura(String id) throws DaoException, EntityNotFoundException;
    
    void cambiarEstadoFacturaAEnviada(int numeroOrden) throws DaoException, EntityNotFoundException;
}
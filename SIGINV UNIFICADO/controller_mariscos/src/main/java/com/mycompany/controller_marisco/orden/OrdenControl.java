/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller_marisco.orden;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.dto_mariscos.mermas.OrdenDTO;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.persistencia_orden.IOrdenDAO;
import com.mycompany.persistencia_orden.OrdenDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author joser
 */
public class OrdenControl implements IOrdenControl{
    IOrdenDAO ordenDAO;
    public OrdenControl() {
        this.ordenDAO = new OrdenDAO();
    }
    
    @Override
    public List<OrdenDTO> listarOrdenes() throws Exception {
        try {
            List<Orden> entidades = ordenDAO.listarOrdenes();
            List<OrdenDTO> dtos = new ArrayList<>();
            
            for (Orden entidad : entidades) {
                dtos.add(convertirADTO(entidad));
            }
            return dtos;
        } catch (DaoException e) {
            throw new Exception("Error en control al listar órdenes: " + e.getMessage());
        }
    }
    @Override
    public OrdenDTO buscarOrdenPorId(String id) throws Exception {
        try {
            Orden entidad = ordenDAO.buscarOrdenPorId(id);
            return (entidad != null) ? convertirADTO(entidad) : null;
        } catch (DaoException e) {
            throw new Exception("Error en control al buscar orden: " + e.getMessage());
        }
    }
    @Override
    public boolean actualizarEstadoOrden(String id, String nuevoEstado) throws Exception {
        try {
            if (nuevoEstado == null || nuevoEstado.isEmpty()) {
                throw new Exception("El estado no puede estar vacío.");
            }
            return ordenDAO.actualizarEstadoOrden(id, nuevoEstado);
        } catch (DaoException e) {
            throw new Exception("Error en control al actualizar estado: " + e.getMessage());
        }
    }
    
    /**
     * Nuevo método para actualizar específicamente el estado de facturación
     */
    public boolean actualizarEstadoFacturacion(String id, String nuevoEstadoFacturacion) throws Exception {
        try {
            if (nuevoEstadoFacturacion == null || nuevoEstadoFacturacion.isEmpty()) {
                throw new Exception("El estado de facturación no puede estar vacío.");
            }
            return ordenDAO.actualizarEstadoFacturacion(id, nuevoEstadoFacturacion);
        } catch (DaoException e) {
            throw new Exception("Error en control al actualizar estado de facturación: " + e.getMessage());
        }
    }
    
    @Override
    public List<com.mycompany.dto_mariscos.Orden> listarOrdenesEntidades() throws Exception {
        try {
            return ordenDAO.listarOrdenes();
        } catch (DaoException e) {
            throw new Exception("Error en control al listar órdenes: " + e.getMessage());
        }
    }
    
    /**
     * Método auxiliar para mapear de Entidad (Mongo) a DTO (UI)
     */
    private OrdenDTO convertirADTO(Orden entidad) {
        OrdenDTO dto = new OrdenDTO();
        
        if (entidad.getId()!= null) {
            dto.setId(entidad.getId().toHexString());
        }
        
        dto.setNumeroOrden(entidad.getNumeroOrden());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setResponsable(entidad.getResponsable());
        dto.setProveedor(entidad.getProveedor());
        dto.setEstado(entidad.getEstado());
        dto.setEstadoFacturacion(entidad.getEstadoFacturacion());
        
        if (entidad.getItems() != null) {
            dto.setItems(new HashMap<>(entidad.getItems()));
        } else {
            dto.setItems(new HashMap<>());
        }
        
        return dto;
    }
    
    @Override
public List<Orden> obtenerOrdenesFacturables(int ano, String ordenamiento) throws DaoException {
    try {
        List<Orden> ordenes = ordenDAO.findByAno(ano);
        
        List<Orden> facturables = new ArrayList<>();
        for (Orden orden : ordenes) {
            String estado = orden.getEstadoFacturacion();
            if (estado != null && 
                (estado.equalsIgnoreCase("pendiente") || 
                 estado.equalsIgnoreCase("sin facturar") ||
                 estado.equalsIgnoreCase("por facturar"))) {
                facturables.add(orden);
            }
        }
        
        if ("nuevo".equalsIgnoreCase(ordenamiento)) {
            facturables.sort((o1, o2) -> o2.getFechaCreacion().compareTo(o1.getFechaCreacion()));
        } else {
            facturables.sort((o1, o2) -> o1.getFechaCreacion().compareTo(o2.getFechaCreacion()));
        }
        
        return facturables;
    } catch (Exception ex) {
        throw new DaoException("Error al obtener órdenes facturables: " + ex.getMessage(), ex);
    }
}

    @Override
    public boolean actualizarSoloEstadoFacturacion(String id, String nuevoEstadoFacturacion) throws Exception {
    try {
        if (nuevoEstadoFacturacion == null || nuevoEstadoFacturacion.isEmpty()) {
            throw new Exception("El estado de facturación no puede estar vacío.");
        }
        return ordenDAO.actualizarSoloEstadoFacturacion(id, nuevoEstadoFacturacion);
    } catch (DaoException e) {
        throw new Exception("Error en control al actualizar estado de facturación: " + e.getMessage());
    }
}
    
}
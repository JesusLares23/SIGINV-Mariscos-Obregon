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
     * Método auxiliar para mapear de Entidad (Mongo) a DTO (UI)
     */
    private OrdenDTO convertirADTO(Orden entidad) {
        OrdenDTO dto = new OrdenDTO();

        // Mapeo de campos simples
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toHexString());
        }
        dto.setNumeroOrden(entidad.getNumeroOrden());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setResponsable(entidad.getResponsable());
        dto.setProveedor(entidad.getProveedor());
        dto.setEstado(entidad.getEstado());
        dto.setEstadoFacturacion(entidad.getEstadoFacturacion());

        // Mapeo del nuevo Map<String, Double>
        // Llave: String (ID del Insumo), Valor: Double (Cantidad)
        if (entidad.getItems() != null) {
        dto.setItems(new HashMap<>(entidad.getItems()));
            System.out.println("DEBUG: Se mapearon " + entidad.getItems().size() + " items al DTO");
        } else {
            System.out.println("DEBUG: La entidad Orden traía el mapa de items NULO");
        }

        return dto;
    }

}

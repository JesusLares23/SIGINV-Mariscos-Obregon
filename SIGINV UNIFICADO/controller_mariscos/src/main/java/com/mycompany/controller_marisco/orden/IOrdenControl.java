package com.mycompany.controller_marisco.orden;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.dto_mariscos.mermas.OrdenDTO;
import com.mycompany.exception_mariscos.DaoException;
import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author joser
 */
public interface IOrdenControl {
    /**
     * Obtenemos listado de ordenes
     * @return 
     * @throws java.lang.Exception 
     */
    List<OrdenDTO> listarOrdenes() throws Exception;
    
    /**
     * Busca y regresa la orden según el id ingresado
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    OrdenDTO buscarOrdenPorId(String id) throws Exception;
    
    /**
     * Actualiza el estado general de la orden según el id
     * @param id
     * @param nuevoEstado
     * @return 
     * @throws java.lang.Exception 
     */
    boolean actualizarEstadoOrden(String id, String nuevoEstado) throws Exception;
    
    /**
     * Actualiza el estado de facturación de la orden según el id
     * @param id
     * @param nuevoEstadoFacturacion
     * @return 
     * @throws java.lang.Exception 
     */
    boolean actualizarEstadoFacturacion(String id, String nuevoEstadoFacturacion) throws Exception;
    
    /**
     * Obtenemos listado de órdenes (entidades de dominio)
     * @return 
     * @throws java.lang.Exception 
     */
    List<com.mycompany.dto_mariscos.Orden> listarOrdenesEntidades() throws Exception;
    
    List<Orden> obtenerOrdenesFacturables(int ano, String ordenamiento) throws DaoException;
  boolean actualizarSoloEstadoFacturacion(String id, String nuevoEstadoFacturacion)throws Exception;
 
}
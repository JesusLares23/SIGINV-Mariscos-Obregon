/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.persistencia_orden;

import com.mycompany.dto_mariscos.Orden;
import com.mycompany.exception_mariscos.DaoException;
import java.util.List;

/**
 *
 * @author joser
 */
public interface IOrdenDAO {
    
    /**
     * Obtenemos listado de ordenes
     * @return
     * @throws DaoException 
     */
    List<Orden> listarOrdenes() throws DaoException;
    
    /**
     * Busca y regresa la orden según el id ingresado
     * @param id
     * @return
     * @throws DaoException 
     */
    Orden buscarOrdenPorId(String id) throws DaoException;
    
    /**
     * Actualiza el estado de la orden según el id
     * @param id
     * @param nuevoEstado
     * @return
     * @throws DaoException 
     */
    boolean actualizarEstadoOrden(String id, String nuevoEstado) throws DaoException;
    
        boolean actualizarEstadoFacturacion(String id, String nuevoEstadoFacturacion) throws DaoException;
    
    List<Orden> findByAno(int ano) throws DaoException;
    boolean actualizarSoloEstadoFacturacion(String id, String nuevoEstadoFacturacion) throws DaoException;
 
}

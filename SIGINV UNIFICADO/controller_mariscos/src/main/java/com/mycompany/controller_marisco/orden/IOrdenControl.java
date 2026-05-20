package com.mycompany.controller_marisco.orden;

import com.mycompany.dto_mariscos.mermas.OrdenDTO;
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
     * Actualiza el estado de la orden según el id
     * @param id
     * @param nuevoEstado
     * @return 
     * @throws java.lang.Exception 
     */
    boolean actualizarEstadoOrden(String id, String nuevoEstado) throws Exception;
}

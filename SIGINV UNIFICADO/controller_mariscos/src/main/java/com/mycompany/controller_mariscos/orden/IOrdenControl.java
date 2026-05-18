/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controller_mariscos.orden;



import com.mycompany.dto_mariscos.Orden;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author 52644
 */

public interface IOrdenControl {
    
    int crearOrden(Orden orden) throws DaoException;
    
    Orden obtenerOrdenPorNumero(int numeroOrden) throws DaoException;
    
    List<Orden> obtenerTodasOrdenes() throws DaoException;
    
    List<Orden> obtenerOrdenesPorEstadoFacturacion(String estadoFacturacion) throws DaoException;
    
    List<Orden> obtenerOrdenesPorAno(int ano) throws DaoException;
    
    void actualizarOrden(Orden orden) throws DaoException, EntityNotFoundException;
    
    void eliminarOrden(int numeroOrden) throws DaoException, EntityNotFoundException;
    
    void cambiarEstadoFacturacion(int numeroOrden, String nuevoEstado) throws DaoException, EntityNotFoundException;
}
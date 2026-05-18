/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.persistencia_mariscos.orden;


import com.mycompany.dto_mariscos.Orden;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author 52644
 */

public interface IOrdenDAO {
    
    int create(Orden entity) throws DaoException;
    
    Optional<Orden> findByNumeroOrden(int numeroOrden) throws DaoException;
    
    List<Orden> findAll() throws DaoException;
    
    List<Orden> findByEstadoFacturacion(String estadoFacturacion) throws DaoException;
    
    List<Orden> findByAno(int ano) throws DaoException;
    
    boolean update(Orden entity) throws DaoException, EntityNotFoundException;
    
    boolean deleteByNumeroOrden(int numeroOrden) throws DaoException, EntityNotFoundException;
    
    long deleteAll() throws DaoException;
}

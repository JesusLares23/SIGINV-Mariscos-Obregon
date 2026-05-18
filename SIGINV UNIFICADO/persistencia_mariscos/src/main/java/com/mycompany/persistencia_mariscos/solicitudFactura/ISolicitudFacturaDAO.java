/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.persistencia_mariscos.solicitudFactura;

import com.mycompany.dto_mariscos.SolicitudFactura;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author 52644
 */
public interface ISolicitudFacturaDAO {

    ObjectId create(SolicitudFactura entity) throws DaoException;

    Optional<SolicitudFactura> findById(ObjectId id) throws DaoException;

    Optional<SolicitudFactura> findByNumeroOrden(int numeroOrden) throws DaoException;

    List<SolicitudFactura> findAll() throws DaoException;

    List<SolicitudFactura> findByEstadoFactura(String estadoFactura) throws DaoException;

    List<SolicitudFactura> findByAno(int ano) throws DaoException;

    boolean update(SolicitudFactura entity) throws DaoException, EntityNotFoundException;

    boolean deleteById(ObjectId id) throws DaoException, EntityNotFoundException;

    long deleteAll() throws DaoException;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.persistencia_mariscos.insumo;

import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public interface IInsumoDAO {
    ObjectId create(Insumo entity) throws DaoException;
    
    Optional<Insumo> findById(ObjectId _id) throws DaoException;
    
    Optional<Insumo> findByNombre(String name) throws DaoException;
    
    List<Insumo> findAll() throws DaoException;
    
    boolean update(Insumo entity) throws DaoException, EntityNotFoundException;
    
    boolean deleteById(ObjectId _id) throws DaoException, EntityNotFoundException;

    long deleteAll() throws DaoException;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.persistencia_mariscos.inventario;

import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.dto_mariscos.Inventario;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public interface IInventarioDAO {
    ObjectId create(Inventario entity) throws DaoException;
    
    Optional<Inventario> findById(ObjectId _id) throws DaoException;
    
    Optional<Inventario> findByNombre(String name) throws DaoException;
    
    List<Inventario> findAll() throws DaoException;
    
    boolean update(Inventario entity) throws DaoException, EntityNotFoundException;
    
    boolean deleteById(ObjectId _id) throws DaoException, EntityNotFoundException;

    long deleteAll() throws DaoException;
}

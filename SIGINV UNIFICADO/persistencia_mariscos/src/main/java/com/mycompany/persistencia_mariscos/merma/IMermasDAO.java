/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia_mariscos.merma;


import com.mycompany.dto_mariscos.Merma;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public interface IMermasDAO {
    ObjectId create(Merma entity) throws DaoException;
    
    Optional<Merma> findById(ObjectId _id) throws DaoException;
    
    Optional<Merma> findByNombre(String name) throws DaoException;
    
    List<Merma> findAll() throws DaoException;
    
    boolean update(Merma entity) throws DaoException, EntityNotFoundException;
    
    boolean deleteById(ObjectId _id) throws DaoException, EntityNotFoundException;

    long deleteAll() throws DaoException;
}

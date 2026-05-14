/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia_mariscos.insumo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mycompany.config_mariscos.MongoClientProvider;
import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public class InsumoDAO implements IInsumoDAO{
    
    
    private MongoCollection<Insumo> col;
    
    public InsumoDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("Insumos", Insumo.class);
    }
    
    

    @Override
    public ObjectId create(Insumo entity) throws DaoException {
        try {
                    if (entity.getId()== null) entity.setId(new ObjectId());
                    entity.setCreatedAt(Instant.now());
                    entity.setUpdatedAt(Instant.now());
                    col.insertOne(entity);
                    return entity.getId();
                } catch (MongoException e) {
                    throw new DaoException("Error insertando Insumo", e);
                }
    }

    @Override
    public Optional<Insumo> findById(ObjectId _id) throws DaoException {
        try {
                    return Optional.ofNullable(col.find(Filters.eq("_id", _id)).first());
                } catch (MongoException e) {
                    throw new DaoException("Error consultando Insumo por ID", e);
                }
    }

    @Override
    public Optional<Insumo> findByNombre(String nombre) throws DaoException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("title", nombre)).first());
        } catch (MongoException e) {
            throw new DaoException("Error consultando curso por Insumo", e);
        }
    }

    

    @Override
    public List<Insumo> findAll() throws DaoException {
        try {
            return col.find().into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando todos los Insumos", e);
        }
    }

    @Override
    public boolean update(Insumo entity) throws DaoException, EntityNotFoundException {
        try {
            entity.setUpdatedAt(Instant.now());
            
            var result = col.updateOne(
                Filters.eq("_id", entity.getId()),
                Updates.combine(
                    Updates.set("nombre", entity.getNombre()),
                    Updates.set("unidadMedida", entity.getUnidadMedida()),
                    Updates.set("categoria", entity.getCategoria()),
                    Updates.set("imagenUrl", entity.getImagenUrl()),
                    Updates.set("updatedAt", entity.getUpdatedAt())
                    
                )
            );
            if (result.getMatchedCount() == 0)
                throw new EntityNotFoundException("Insumo no encontrado: " + entity.getId());
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DaoException("Error actualizando Insumo", e);
        }
    }

    @Override
    public boolean deleteById(ObjectId _id) throws DaoException, EntityNotFoundException {
        try {
            var result = col.deleteOne(Filters.eq("_id", _id));
            if (result.getDeletedCount() == 0)
                throw new EntityNotFoundException("Insumo no encontrado: " + _id);
            return true;
        } catch (MongoException e) {
            throw new DaoException("Error eliminando Insumo", e);
        }
    }

    @Override
    public long deleteAll() throws DaoException {
        try {
            return col.deleteMany(Filters.exists("_id")).getDeletedCount();
        } catch (MongoException e) {
            throw new DaoException("Error eliminando todos los Insumos", e);
        }
    }
    
    //FALTA AHORA EL CONTROLLER
    
}

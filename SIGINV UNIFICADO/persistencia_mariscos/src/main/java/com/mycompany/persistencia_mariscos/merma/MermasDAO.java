/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia_mariscos.merma;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mycompany.config_mariscos.MongoClientProvider;
import com.mycompany.dto_mariscos.Inventario;
import com.mycompany.dto_mariscos.Merma;
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
public class MermasDAO implements IMermasDAO{
    
    private MongoCollection<Merma> col;
    
    public MermasDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("Mermas", Merma.class);
    }
    
    

    @Override
    public ObjectId create(Merma entity) throws DaoException {
        try {
                    if (entity.getId()== null) entity.setId(new ObjectId());
                    entity.setCreatedAt(Instant.now());
                    entity.setUpdatedAt(Instant.now());
                    col.insertOne(entity);
                    return entity.getId();
                } catch (MongoException e) {
                    throw new DaoException("Error insertando Merma", e);
                }
    }

    @Override
    public Optional<Merma> findById(ObjectId _id) throws DaoException {
        try {
                    return Optional.ofNullable(col.find(Filters.eq("_id", _id)).first());
                } catch (MongoException e) {
                    throw new DaoException("Error consultando merma por ID", e);
                }
    }

    @Override
    public Optional<Merma> findByNombre(String nombre) throws DaoException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("nombre", nombre)).first());
        } catch (MongoException e) {
            throw new DaoException("Error consultando por el nombre de la merma", e);
        }
    }

    

    @Override
    public List<Merma> findAll() throws DaoException {
        try {
            return col.find().into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando todas las Mermas", e);
        }
    }

    @Override
    public boolean update(Merma entity) throws DaoException, EntityNotFoundException {
        try {
            entity.setUpdatedAt(Instant.now());
            
            var result = col.updateOne(
                Filters.eq("_id", entity.getId()),
                Updates.combine(
                    Updates.set("insumo", entity.getInsumo()),
                    Updates.set("cantPerdida", entity.getCantPerdida()),
                    Updates.set("causa", entity.getCausa()),
                    Updates.set("descripcion", entity.getDescripcion()),
                    Updates.set("ubicacion", entity.getUbicacion()),
                    Updates.set("fechaOcurrido", entity.getFechaOcurrido()),
                    Updates.set("updatedAt", entity.getUpdatedAt())
                    
                )
            );
            if (result.getMatchedCount() == 0)
                throw new EntityNotFoundException("Merma no encontrado: " + entity.getId());
            
            return result.getModifiedCount() > 0;
            
        } catch (MongoException e) {
            throw new DaoException("Error actualizando Merma", e);
        }
    }

    @Override
    public boolean deleteById(ObjectId _id) throws DaoException, EntityNotFoundException {
        try {
            var result = col.deleteOne(Filters.eq("_id", _id));
            if (result.getDeletedCount() == 0)
                throw new EntityNotFoundException("Merma no encontrada: " + _id);
            return true;
        } catch (MongoException e) {
            throw new DaoException("Error eliminando Merma", e);
        }
    }

    @Override
    public long deleteAll() throws DaoException {
        try {
            return col.deleteMany(Filters.exists("_id")).getDeletedCount();
        } catch (MongoException e) {
            throw new DaoException("Error eliminando todas las Mermas", e);
        }
    }
}

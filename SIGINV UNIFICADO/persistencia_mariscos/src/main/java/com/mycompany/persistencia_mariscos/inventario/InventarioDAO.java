/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia_mariscos.inventario;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mycompany.config_mariscos.MongoClientProvider;

import com.mycompany.dto_mariscos.Inventario;
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
public class InventarioDAO implements IInventarioDAO {

    private MongoCollection<Inventario> col;

    public InventarioDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("Inventario", Inventario.class);
    }

    @Override
    public ObjectId create(Inventario entity) throws DaoException {
        try {
            if (entity.getId() == null) {
                entity.setId(new ObjectId());
            }
            entity.setCreatedAt(Instant.now());
            entity.setUpdatedAt(Instant.now());
            col.insertOne(entity);
            return entity.getId();
        } catch (MongoException e) {
            throw new DaoException("Error insertando Inventario", e);
        }
    }

    @Override
    public Optional<Inventario> findById(ObjectId _id) throws DaoException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("_id", _id)).first());
        } catch (MongoException e) {
            throw new DaoException("Error consultando Inventario por ID", e);
        }
    }

    @Override
    public Optional<Inventario> findByNombre(String nombre) throws DaoException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("title", nombre)).first());
        } catch (MongoException e) {
            throw new DaoException("Error consultando curso por Inventario", e);
        }
    }

    @Override
    public List<Inventario> findAll() throws DaoException {
        try {
            return col.find().into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando todos los Inventarios", e);
        }
    }

    @Override
    public boolean update(Inventario entity) throws DaoException, EntityNotFoundException {
        try {
            entity.setUpdatedAt(Instant.now());

            var result = col.updateOne(
                    Filters.eq("_id", entity.getId()),
                    Updates.combine(
                            Updates.set("stockInicial", entity.getStockInicial()),
                            Updates.set("stockActual", entity.getStockActual()),
                            Updates.set("stockMinimo", entity.getStockMinimo()),
                            Updates.set("updatedAt", entity.getUpdatedAt())
                    )
            );
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("Inventario no encontrado: " + entity.getId());
            }

            return result.getModifiedCount() > 0;

        } catch (MongoException e) {
            throw new DaoException("Error actualizando Inventario", e);
        }
    }

    @Override
    public boolean deleteById(ObjectId _id) throws DaoException, EntityNotFoundException {
        try {
            var result = col.deleteOne(Filters.eq("_id", _id));
            if (result.getDeletedCount() == 0) {
                throw new EntityNotFoundException("Inventario no encontrado: " + _id);
            }
            return true;
        } catch (MongoException e) {
            throw new DaoException("Error eliminando Inventario", e);
        }
    }

    @Override
    public long deleteAll() throws DaoException {
        try {
            return col.deleteMany(Filters.exists("_id")).getDeletedCount();
        } catch (MongoException e) {
            throw new DaoException("Error eliminando los inventarios de todos los insumos", e);
        }
    }

}

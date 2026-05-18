/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia_mariscos.orden;




import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mycompany.config_mariscos.MongoClientProvider;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author 52644
 */

public class OrdenDAO implements IOrdenDAO {
    
    private MongoCollection<Orden> col;
    
    public OrdenDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("Orden", Orden.class);
    }

    @Override
    public int create(Orden entity) throws DaoException {
        try {
            entity.setFechaCreacion(new Date());
            entity.setEstado("Pendiente");
            entity.setEstadoFacturacion("Sin Facturar");
            col.insertOne(entity);
            return entity.getNumeroOrden();
        } catch (MongoException e) {
            throw new DaoException("Error insertando Orden", e);
        }
    }

    @Override
    public Optional<Orden> findByNumeroOrden(int numeroOrden) throws DaoException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("numeroOrden", numeroOrden)).first());
        } catch (MongoException e) {
            throw new DaoException("Error consultando Orden por numeroOrden", e);
        }
    }

    @Override
    public List<Orden> findAll() throws DaoException {
        try {
            return col.find().into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando todas las Ordenes", e);
        }
    }

    @Override
    public List<Orden> findByEstadoFacturacion(String estadoFacturacion) throws DaoException {
        try {
            return col.find(Filters.eq("estadoFacturacion", estadoFacturacion)).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando Orden por estado de facturación", e);
        }
    }

    @Override
    public List<Orden> findByAno(int ano) throws DaoException {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, ano);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date startDate = cal.getTime();

            cal.set(Calendar.MONTH, Calendar.DECEMBER);
            cal.set(Calendar.DAY_OF_MONTH, 31);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            Date endDate = cal.getTime();

            return col.find(
                Filters.and(
                    Filters.gte("fechaCreacion", startDate),
                    Filters.lte("fechaCreacion", endDate)
                )
            ).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando Orden por año", e);
        }
    }

    @Override
    public boolean update(Orden entity) throws DaoException, EntityNotFoundException {
        try {
            var result = col.updateOne(
                Filters.eq("numeroOrden", entity.getNumeroOrden()),
                Updates.combine(
                    Updates.set("fechaCreacion", entity.getFechaCreacion()),
                    Updates.set("responsable", entity.getResponsable()),
                    Updates.set("proveedor", entity.getProveedor()),
                    Updates.set("estado", entity.getEstado()),
                    Updates.set("estadoFacturacion", entity.getEstadoFacturacion()),
                    Updates.set("items", entity.getItems())
                )
            );
            if (result.getMatchedCount() == 0)
                throw new EntityNotFoundException("Orden no encontrada: " + entity.getNumeroOrden());
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DaoException("Error actualizando Orden", e);
        }
    }

    @Override
    public boolean deleteByNumeroOrden(int numeroOrden) throws DaoException, EntityNotFoundException {
        try {
            var result = col.deleteOne(Filters.eq("numeroOrden", numeroOrden));
            if (result.getDeletedCount() == 0)
                throw new EntityNotFoundException("Orden no encontrada: " + numeroOrden);
            return true;
        } catch (MongoException e) {
            throw new DaoException("Error eliminando Orden", e);
        }
    }

    @Override
    public long deleteAll() throws DaoException {
        try {
            return col.deleteMany(Filters.exists("numeroOrden")).getDeletedCount();
        } catch (MongoException e) {
            throw new DaoException("Error eliminando todas las Ordenes", e);
        }
    }
}
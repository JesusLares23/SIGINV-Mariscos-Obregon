/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia_mariscos.solicitudFactura;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mycompany.config_mariscos.MongoClientProvider;
import com.mycompany.dto_mariscos.SolicitudFactura;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author 52644
 */

public class SolicitudFacturaDAO implements ISolicitudFacturaDAO {

    private MongoCollection<SolicitudFactura> col;

    public SolicitudFacturaDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("SolicitudFactura", SolicitudFactura.class);
    }

    @Override
    public ObjectId create(SolicitudFactura entity) throws DaoException {
        try {
            if (entity.getId() == null) {
                entity.setId(new ObjectId());
            }
            entity.setCreatedAt(Instant.now());
            entity.setUpdatedAt(Instant.now());
            col.insertOne(entity);
            return entity.getId();
        } catch (MongoException e) {
            throw new DaoException("Error insertando SolicitudFactura", e);
        }
    }

    @Override
    public Optional<SolicitudFactura> findById(ObjectId id) throws DaoException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("_id", id)).first());
        } catch (MongoException e) {
            throw new DaoException("Error consultando SolicitudFactura por ID", e);
        }
    }

    @Override
    public Optional<SolicitudFactura> findByNumeroOrden(int numeroOrden) throws DaoException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("numeroOrden", numeroOrden)).first());
        } catch (MongoException e) {
            throw new DaoException("Error consultando SolicitudFactura por numeroOrden", e);
        }
    }

    @Override
    public List<SolicitudFactura> findAll() throws DaoException {
        try {
            return col.find().into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando todas las SolicitudFactura", e);
        }
    }

    @Override
    public List<SolicitudFactura> findByEstadoFactura(String estadoFactura) throws DaoException {
        try {
            return col.find(Filters.eq("estadoFactura", estadoFactura)).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando SolicitudFactura por estado", e);
        }
    }

    @Override
    public List<SolicitudFactura> findByAno(int ano) throws DaoException {
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
                            Filters.gte("fechaSolicitud", startDate),
                            Filters.lte("fechaSolicitud", endDate)
                    )
            ).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error consultando SolicitudFactura por año", e);
        }
    }

    @Override
    public boolean update(SolicitudFactura entity) throws DaoException, EntityNotFoundException {
        try {
            entity.setUpdatedAt(Instant.now());

            var result = col.updateOne(
                    Filters.eq("_id", entity.getId()),
                    Updates.combine(
                            Updates.set("numeroOrden", entity.getNumeroOrden()),
                            Updates.set("estadoFactura", entity.getEstadoFactura()),
                            Updates.set("fechaSolicitud", entity.getFechaSolicitud()),
                            Updates.set("usoCFDI", entity.getUsoCFDI()),
                            Updates.set("rfc", entity.getRfc()),
                            Updates.set("razonSocial", entity.getRazonSocial()),
                            Updates.set("regimenFiscal", entity.getRegimenFiscal()),
                            Updates.set("calle", entity.getCalle()),
                            Updates.set("codigoPostal", entity.getCodigoPostal()),
                            Updates.set("correo", entity.getCorreo()),
                            Updates.set("respuestaProveedor", entity.getRespuestaProveedor()),
                            Updates.set("numeroFactura", entity.getNumeroFactura()),
                            Updates.set("fechaFactura", entity.getFechaFactura()),
                            Updates.set("updatedAt", entity.getUpdatedAt())
                    )
            );
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("SolicitudFactura no encontrada: " + entity.getId());
            }
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DaoException("Error actualizando SolicitudFactura", e);
        }
    }

    @Override
    public boolean deleteById(ObjectId id) throws DaoException, EntityNotFoundException {
        try {
            var result = col.deleteOne(Filters.eq("_id", id));
            if (result.getDeletedCount() == 0) {
                throw new EntityNotFoundException("SolicitudFactura no encontrada: " + id);
            }
            return true;
        } catch (MongoException e) {
            throw new DaoException("Error eliminando SolicitudFactura", e);
        }
    }

    @Override
    public long deleteAll() throws DaoException {
        try {
            return col.deleteMany(Filters.exists("_id")).getDeletedCount();
        } catch (MongoException e) {
            throw new DaoException("Error eliminando todas las SolicitudFactura", e);
        }
    }
}

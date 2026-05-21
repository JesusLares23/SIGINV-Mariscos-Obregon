package com.mycompany.persistencia_mariscos.solicitudFactura;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mycompany.config_mariscos.MongoClientProvider;
import com.mycompany.dominio.solicitudFactura.SolicitudFactura;

import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

public class SolicitudFacturaDAO implements ISolicitudFacturaDAO {

    private final MongoCollection<SolicitudFactura> coleccion;

    public SolicitudFacturaDAO() {
        this.coleccion = MongoClientProvider.INSTANCE
                .getCollection("solicitudes_factura", SolicitudFactura.class);

    }

    @Override
    public void guardar(SolicitudFactura solicitud) throws DaoException {
        try {
            coleccion.insertOne(solicitud);
        } catch (MongoException e) {
            throw new DaoException("Error al guardar la solicitud de factura.", e);
        }
    }

    @Override
    public SolicitudFactura buscarPorId(ObjectId id) throws DaoException, EntityNotFoundException {
        try {
            SolicitudFactura solicitud = coleccion.find(Filters.eq("_id", id)).first();
            if (solicitud == null) {
                throw new EntityNotFoundException("No se encontró la solicitud con ID: " + id);
            }
            return solicitud;
        } catch (MongoException e) {
            throw new DaoException("Error al buscar la solicitud.", e);
        }
    }

    @Override
    public List<SolicitudFactura> obtenerTodas() throws DaoException {
        try {
            List<SolicitudFactura> lista = new ArrayList<>();
            for (SolicitudFactura solicitud : coleccion.find()) {
                lista.add(solicitud);
            }
            return lista;
        } catch (MongoException e) {
            throw new DaoException("Error al obtener las solicitudes.", e);
        }
    }

    @Override
    public void actualizar(SolicitudFactura solicitud) throws DaoException, EntityNotFoundException {
        try {
            SolicitudFactura existente = coleccion.find(Filters.eq("_id", solicitud.getId())).first();
            if (existente == null) {
                throw new EntityNotFoundException("No existe la solicitud a actualizar.");
            }
            coleccion.replaceOne(Filters.eq("_id", solicitud.getId()), solicitud);
        } catch (MongoException e) {
            throw new DaoException("Error al actualizar la solicitud.", e);
        }
    }

    public boolean existeSolicitudParaOrden(String idOrden) {
        ObjectId objectId = new ObjectId(idOrden);

        SolicitudFactura encontrada = coleccion.find(
                Filters.eq("orden_id", objectId)
        ).first();

        return encontrada != null;
    }
}

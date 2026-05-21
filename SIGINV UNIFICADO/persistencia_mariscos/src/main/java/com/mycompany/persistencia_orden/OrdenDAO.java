package com.mycompany.persistencia_orden;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.config_mariscos.MongoClientProvider;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.exception_mariscos.DaoException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
/**
 *
 * @author joser
 */
public class OrdenDAO implements IOrdenDAO{
    
    private MongoCollection<Orden> col;
    
    /**
     * Constructor de clase donde inicializamos instancia de la coleccion
     */
    public OrdenDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("Ordenes", Orden.class);
    }
    /**
     * Obtenemos listado de ordenes
     * @return
     * @throws DaoException 
     */
    @Override
    public List<Orden> listarOrdenes() throws DaoException {
        try {
            List<Orden> lista = new ArrayList<>();
            col.find().into(lista);
            return lista;
        } catch (Exception e) {
            throw new DaoException("Error al listar órdenes desde MongoDB: " + e.getMessage());
        }
    }
    /**
     * Busca y regresa la orden según el id ingresado
     * @param id
     * @return
     * @throws DaoException 
     */
    @Override
    public Orden buscarOrdenPorId(String id) throws DaoException {
        try {
            if (!ObjectId.isValid(id)) {
                return null;
            }
            return col.find(Filters.eq("_id", new ObjectId(id))).first();
        } catch (Exception e) {
            throw new DaoException("Error al buscar orden por ID: " + e.getMessage());
        }
    }
    /**
     * Actualiza el estado general de la orden según el id
     * @param id
     * @param nuevoEstado
     * @return
     * @throws DaoException 
     */
    @Override
    public boolean actualizarEstadoOrden(String id, String nuevoEstado) throws DaoException {
        try {
            if (!ObjectId.isValid(id)) {
                return false;
            }
            
            UpdateResult result = col.updateOne(
                Filters.eq("_id", new ObjectId(id)),
                Updates.set("estado", nuevoEstado)
            );
            
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            throw new DaoException("Error al actualizar el estado de la orden: " + e.getMessage());
        }
    }
    
    /**
     * Actualiza el estado de facturación de la orden según el id
     * @param id
     * @param nuevoEstadoFacturacion
     * @return
     * @throws DaoException 
     */
    @Override
public boolean actualizarEstadoFacturacion(String id, String nuevoEstadoFacturacion) throws DaoException {
    try {
        if (!ObjectId.isValid(id)) {
            return false;
        }

        UpdateResult result = col.updateOne(
            Filters.eq("_id", new ObjectId(id)),
            Updates.set("estado", nuevoEstadoFacturacion) // <-- AQUÍ EL FIX
        );

        return result.getModifiedCount() > 0;

    } catch (Exception e) {
        throw new DaoException("Error al actualizar el estado de facturación de la orden: " + e.getMessage());
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
public boolean actualizarSoloEstadoFacturacion(String id, String nuevoEstadoFacturacion) throws DaoException {
    try {
        if (!ObjectId.isValid(id)) {
            return false;
        }

        UpdateResult result = col.updateOne(
            Filters.eq("_id", new ObjectId(id)),
            Updates.set("estadoFacturacion", nuevoEstadoFacturacion)
        );

        return result.getModifiedCount() > 0;

    } catch (Exception e) {
        throw new DaoException("Error al actualizar el estado de facturación de la orden: " + e.getMessage());
    }
}
    
}
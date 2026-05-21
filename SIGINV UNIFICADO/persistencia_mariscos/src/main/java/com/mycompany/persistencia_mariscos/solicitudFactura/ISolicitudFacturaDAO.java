package com.mycompany.persistencia_mariscos.solicitudFactura;




import com.mycompany.dominio.solicitudFactura.SolicitudFactura;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
import org.bson.types.ObjectId;


/**
 *
 * @author 52644
 */

public interface ISolicitudFacturaDAO {

    void guardar(SolicitudFactura solicitud) throws DaoException;

    SolicitudFactura buscarPorId(ObjectId id) throws DaoException, EntityNotFoundException;

    List<SolicitudFactura> obtenerTodas() throws DaoException;

    void actualizar(SolicitudFactura solicitud) throws DaoException, EntityNotFoundException;
    
    public boolean existeSolicitudParaOrden(String idOrden) throws Exception;
}


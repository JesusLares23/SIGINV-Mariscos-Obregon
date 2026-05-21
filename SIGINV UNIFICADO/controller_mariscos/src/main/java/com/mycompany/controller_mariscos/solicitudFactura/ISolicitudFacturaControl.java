
package com.mycompany.controller_mariscos.solicitudFactura;



import com.mycompany.dto_mariscos.solicitudFactura.SolicitudFacturaDTO;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;

public interface ISolicitudFacturaControl {

    void guardar(SolicitudFacturaDTO dto) throws DaoException;

    SolicitudFacturaDTO buscarPorId(String id) throws DaoException, EntityNotFoundException;

    List<SolicitudFacturaDTO> obtenerTodas() throws DaoException;

    void actualizar(SolicitudFacturaDTO dto) throws DaoException, EntityNotFoundException;
    
    boolean existeSolicitudParaOrden(String idOrden) throws Exception;
    
    
}
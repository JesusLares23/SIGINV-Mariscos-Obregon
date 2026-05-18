package com.mycompany.controller_mariscos.orden;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.util.List;
/**
 *
 * @author 52644
 */
public interface IOrdenControl {
    
    int crearOrden(Orden orden) throws DaoException;
    
    Orden obtenerOrdenPorNumero(int numeroOrden) throws DaoException;
    
    List<Orden> obtenerTodasOrdenes() throws DaoException;
    
    List<Orden> obtenerOrdenesPorEstadoFacturacion(String estadoFacturacion) throws DaoException;
    
    List<Orden> obtenerOrdenesPorAno(int ano) throws DaoException;
    

    
    List<Orden> obtenerOrdenesFacturables(int ano, String ordenamiento) throws DaoException;
    
    void actualizarOrden(Orden orden) throws DaoException, EntityNotFoundException;
    
    void eliminarOrden(int numeroOrden) throws DaoException, EntityNotFoundException;
    
    void cambiarEstadoFacturacion(int numeroOrden, String nuevoEstado) throws DaoException, EntityNotFoundException;
}
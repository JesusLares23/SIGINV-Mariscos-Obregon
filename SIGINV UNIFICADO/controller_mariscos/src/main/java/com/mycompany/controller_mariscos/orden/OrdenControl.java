/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controller_mariscos.orden;

import com.mycompany.dto_mariscos.Orden;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import com.mycompany.persistencia_mariscos.orden.IOrdenDAO;
import java.util.List;

/**
 *
 * @author 52644
 */
public class OrdenControl implements IOrdenControl {

    private IOrdenDAO ordenDAO;

    public OrdenControl(IOrdenDAO ordenDAO) {
        this.ordenDAO = ordenDAO;
    }

    @Override
    public int crearOrden(Orden orden) throws DaoException {
        if (orden.getProveedor() == null || orden.getProveedor().isEmpty()) {
            throw new IllegalArgumentException("El proveedor es obligatorio");
        }
        return ordenDAO.create(orden);
    }

    @Override
    public Orden obtenerOrdenPorNumero(int numeroOrden) throws DaoException {
        return ordenDAO.findByNumeroOrden(numeroOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada: " + numeroOrden));
    }

    @Override
    public List<Orden> obtenerTodasOrdenes() throws DaoException {
        return ordenDAO.findAll();
    }

    @Override
    public List<Orden> obtenerOrdenesPorEstadoFacturacion(String estadoFacturacion) throws DaoException {
        return ordenDAO.findByEstadoFacturacion(estadoFacturacion);
    }

    @Override
    public List<Orden> obtenerOrdenesPorAno(int ano) throws DaoException {
        return ordenDAO.findByAno(ano);
    }

    @Override
    public void actualizarOrden(Orden orden) throws DaoException, EntityNotFoundException {
        boolean actualizado = ordenDAO.update(orden);
        if (!actualizado) {
            throw new RuntimeException("No se pudo actualizar la orden");
        }
    }

    @Override
    public void eliminarOrden(int numeroOrden) throws DaoException, EntityNotFoundException {
        ordenDAO.deleteByNumeroOrden(numeroOrden);
    }

    @Override
    public void cambiarEstadoFacturacion(int numeroOrden, String nuevoEstado) throws DaoException, EntityNotFoundException {
        Orden orden = obtenerOrdenPorNumero(numeroOrden);
        orden.setEstadoFacturacion(nuevoEstado);
        actualizarOrden(orden);
    }
}

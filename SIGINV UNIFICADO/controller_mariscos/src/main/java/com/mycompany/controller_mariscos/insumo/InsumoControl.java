/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller_mariscos.insumo;

import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import com.mycompany.persistencia_mariscos.insumo.IInsumoDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public class InsumoControl implements IInsumoControl{
    private IInsumoDAO insumoDAO;

    public InsumoControl(IInsumoDAO insumoDAO) {
        this.insumoDAO = insumoDAO;
    }

    @Override
    public ObjectId crearInsumo(Insumo insumo) throws DaoException {
        // validacion
        if (insumo.getNombre()== null || insumo.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        return insumoDAO.create(insumo);
    }

    public Insumo obtenerInsumo(ObjectId id) throws DaoException {
        return insumoDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
    }

    @Override
    public List<Insumo> obtenerTodos() throws DaoException {
        return insumoDAO.findAll();
    }

    @Override
    public void actualizarInsumo(Insumo insumo) throws DaoException, EntityNotFoundException {
        boolean actualizado = insumoDAO.update(insumo);

        if (!actualizado) {
            throw new RuntimeException("No se pudo actualizar");
        }
    }

    @Override
    public void eliminarInsumo(ObjectId id) throws DaoException, EntityNotFoundException {
        insumoDAO.deleteById(id);
    }

    @Override
    public Insumo obtenerInsumoPorId(ObjectId id) throws Exception {
        return insumoDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado con ID: " + id));
    }

    @Override
    public Insumo obtenerInsumoPorNombre(String nombre) throws Exception {
        return insumoDAO.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado con nombre: " + nombre));
    }
}

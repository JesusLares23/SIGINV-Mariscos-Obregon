/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller_mariscos.inventario;

import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.dto_mariscos.Inventario;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;

import com.mycompany.persistencia_mariscos.inventario.IInventarioDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public class InventarioControl implements IInventarioControl{
    private IInventarioDAO inventarioDAO;

    public InventarioControl(IInventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    @Override
    public ObjectId crearInventario(Inventario inventario) throws DaoException {
        // validacion
        if (inventario.getInsumo().getNombre()== null || inventario.getInsumo().getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        return inventarioDAO.create(inventario);
    }

    public Inventario obtenerInventario(ObjectId id) throws DaoException {
        return inventarioDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    @Override
    public List<Inventario> obtenerTodos() throws DaoException {
        return inventarioDAO.findAll();
    }

    @Override
    public void actualizarInventario(Inventario inventario) throws DaoException, EntityNotFoundException {
        boolean actualizado = inventarioDAO.update(inventario);

        if (!actualizado) {
            throw new RuntimeException("No se pudo actualizar");
        }
    }

    @Override
    public void eliminarInventario(ObjectId id) throws DaoException, EntityNotFoundException {
        inventarioDAO.deleteById(id);
    }

    @Override
    public Inventario obtenerInventarioPorId(ObjectId id) throws Exception {
        return inventarioDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));
    }

    @Override
    public Inventario obtenerInventarioPorNombreInsumo(String nombre) throws Exception {
        return inventarioDAO.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con nombre: " + nombre));
    }
}

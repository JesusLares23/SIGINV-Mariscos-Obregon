/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controller_mariscos.inventario;

import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.dto_mariscos.Inventario;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public interface IInventarioControl {
    ObjectId crearInventario(Inventario inventario) throws Exception;

    Inventario obtenerInventarioPorId(ObjectId id) throws Exception;

    Inventario obtenerInventarioPorNombreInsumo(String nombre) throws Exception;

    List<Inventario> obtenerTodos() throws Exception;

    void actualizarInventario(Inventario inventario) throws Exception;

    void eliminarInventario(ObjectId id) throws Exception;
}

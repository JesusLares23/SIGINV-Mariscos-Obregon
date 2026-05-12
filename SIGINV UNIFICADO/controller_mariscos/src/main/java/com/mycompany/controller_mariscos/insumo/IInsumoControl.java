/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controller_mariscos.insumo;

import com.mycompany.dto_mariscos.Insumo;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public interface IInsumoControl {
    ObjectId crearInsumo(Insumo insumo) throws Exception;

    Insumo obtenerInsumoPorId(ObjectId id) throws Exception;

    Insumo obtenerInsumoPorNombre(String nombre) throws Exception;

    List<Insumo> obtenerTodos() throws Exception;

    void actualizarInsumo(Insumo insumo) throws Exception;

    void eliminarInsumo(ObjectId id) throws Exception;
}

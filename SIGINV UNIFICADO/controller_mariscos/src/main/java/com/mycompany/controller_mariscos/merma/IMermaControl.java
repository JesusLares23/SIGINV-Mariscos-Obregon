/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controller_mariscos.merma;

import com.mycompany.dto_mariscos.Inventario;
import com.mycompany.dto_mariscos.Merma;
import com.mycompany.dto_mariscos.mermas.MermaDTO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public interface IMermaControl {
    ObjectId crearMerma(MermaDTO mermaDTO) throws Exception;

    MermaDTO obtenerMermaPorId(ObjectId id) throws Exception;

    MermaDTO obtenerMermaPorNombreInsumo(String nombre) throws Exception;

    List<MermaDTO> obtenerTodos() throws Exception;

    void actualizarMerma(MermaDTO mermaDTO) throws Exception;

    void eliminarMerma(ObjectId id) throws Exception;
}

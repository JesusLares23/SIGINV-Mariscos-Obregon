/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller_mariscos.merma;

import com.mycompany.controller_mariscos.inventario.IInventarioControl;
import com.mycompany.controller_mariscos.inventario.InventarioControl;
import com.mycompany.dto_mariscos.Inventario;
import com.mycompany.dto_mariscos.Merma;
import com.mycompany.dto_mariscos.mermas.MermaDTO;
import com.mycompany.persistencia_mariscos.inventario.IInventarioDAO;
import com.mycompany.persistencia_mariscos.inventario.InventarioDAO;
import com.mycompany.persistencia_mariscos.merma.IMermasDAO;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author demib
 */
public class MermaControl implements IMermaControl {
    private IMermasDAO mermaDAO;
    
    private IInventarioDAO inventarioDAO = new InventarioDAO();
    private IInventarioControl inventarioControl = new InventarioControl(inventarioDAO);

    public MermaControl(IMermasDAO mermaDAO) {
        this.mermaDAO = mermaDAO;
    }

    @Override
    public ObjectId crearMerma(MermaDTO mermaDTO) throws Exception {
        
        Merma merma = convertirDTOEntidad(mermaDTO);
        
        
        
        // validacion
        if (merma.getInsumo().getNombre()== null || merma.getInsumo().getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        
        //Obtenemos el inventario por el nombre del insumo
        Inventario inv = inventarioControl.obtenerInventarioPorNombreInsumo(merma.getInsumo().getNombre());
        
        //Restar stock
        Double nuevoStock = inv.getStockActual() - merma.getCantPerdida();
        
        //Setear nuevo stock a stockActual
        inv.setStockActual(nuevoStock);
        
        //Actualizar en mongodb
        inventarioControl.actualizarInventario(inv);
        
        return mermaDAO.create(merma);
    }

    @Override
    public MermaDTO obtenerMermaPorId(ObjectId id) throws Exception {
        
        Merma merma = mermaDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Merma no encontrada"));
        return convertirEntidadDTO(merma);
                
    }

    @Override
    public MermaDTO obtenerMermaPorNombreInsumo(String nombre) throws Exception {
        Merma merma = mermaDAO.findByNombre(nombre)
            .orElseThrow(() -> new RuntimeException("Merma no encontrada con nombre: " + nombre));
            
        return convertirEntidadDTO(merma);
                
                
    }

    @Override
    public List<MermaDTO> obtenerTodos() throws Exception {
        
        List<Merma> mermas = mermaDAO.findAll();
        
        List<MermaDTO> mermasDTO = new ArrayList<>();
        for(Merma merma: mermas){
            mermasDTO.add(convertirEntidadDTO(merma));
        }
        
        return mermasDTO;
    }

    @Override
    public void actualizarMerma(MermaDTO mermaDTO) throws Exception {
        Merma merma = convertirDTOEntidad(mermaDTO);
        
        boolean actualizado = mermaDAO.update(merma);

        if (!actualizado) {
            throw new RuntimeException("No se pudo actualizar");
        }
    }

    @Override
    public void eliminarMerma(ObjectId id) throws Exception {
        mermaDAO.deleteById(id);
    }

    private Merma convertirDTOEntidad(MermaDTO dto) throws Exception {
        
        
            Merma merma = new Merma();
            
            //Solo asigna el id si existe y no esta vacio
            if(dto.getId() != null  && !dto.getId().isEmpty()){
                merma.setId(new ObjectId(dto.getId()));
            }
            
            //Obtiene el Inventario por el nombre del insumo
            Inventario inv = inventarioControl.obtenerInventarioPorNombreInsumo(dto.getNombreInsumo());

            merma.setInsumo(inv.getInsumo());

            merma.setCantPerdida(dto.getCantPerdida());
            merma.setCausa(dto.getCausa());
            merma.setDescripcion(dto.getDescripcion());
            merma.setUbicacion(dto.getUbicacion());

            merma.setFechaOcurrido(
                    dto.getFechaOcurrido()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
            );
            
            merma.setCreatedAt(Instant.now());
            merma.setUpdatedAt(Instant.now());

            return merma;
        

        
    }
    
    
    
    
    private MermaDTO convertirEntidadDTO(Merma merma) {

        MermaDTO dto = new MermaDTO();

        dto.setId(merma.getId().toHexString());
        dto.setNombreInsumo(merma.getInsumo().getNombre());
        dto.setCantPerdida(merma.getCantPerdida());
        dto.setUnidadMedida(
                merma.getInsumo().getUnidadMedida()
        );

        dto.setCausa(merma.getCausa());
        dto.setDescripcion(merma.getDescripcion());
        dto.setUbicacion(merma.getUbicacion());
        
        
        dto.setFechaOcurrido(
            LocalDateTime.ofInstant(
                merma.getFechaOcurrido(),
                ZoneId.systemDefault()
            )
        );
        
        
        

        return dto;
    }
}

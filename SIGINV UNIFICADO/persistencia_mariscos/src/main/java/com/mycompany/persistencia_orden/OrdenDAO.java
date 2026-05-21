/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.persistencia_orden;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.config_mariscos.MongoClientProvider;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.exception_mariscos.DaoException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author joser
 */
public class OrdenDAO implements IOrdenDAO{
    
    private MongoCollection<Orden> col;
    
    /**
     * Constructor de clase donde inicializamos instancia de la coleccion
     */
    public OrdenDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("Ordenes", Orden.class);
    }

    /**
     * Obtenemos listado de ordenes
     * @return
     * @throws DaoException 
     */
    @Override
    public List<Orden> listarOrdenes() throws DaoException {
        try {
            List<Orden> lista = new ArrayList<>();
            col.find().into(lista);
            return lista;
        } catch (Exception e) {
            throw new DaoException("Error al listar órdenes desde MongoDB: " + e.getMessage());
        }
    }

    /**
     * Busca y regresa la orden según el id ingresado
     * @param id
     * @return
     * @throws DaoException 
     */
    @Override
    public Orden buscarOrdenPorId(String id) throws DaoException {
        try {
            if (!ObjectId.isValid(id)) {
                return null;
            }
            return col.find(Filters.eq("_id", new ObjectId(id))).first();
        } catch (Exception e) {
            throw new DaoException("Error al buscar orden por ID: " + e.getMessage());
        }
    }

    /**
     * Actualiza el estado de la orden según el id
     * @param id
     * @param nuevoEstado
     * @return
     * @throws DaoException 
     */
    @Override
    public boolean actualizarEstadoOrden(String id, String nuevoEstado) throws DaoException {
        try {
            if (!ObjectId.isValid(id)) {
                return false;
            }
            
            UpdateResult result = col.updateOne(
                Filters.eq("_id", new ObjectId(id)),
                Updates.set("estado", nuevoEstado)
            );
            
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            throw new DaoException("Error al actualizar el estado de la orden: " + e.getMessage());
        }
    }
    
    public void insertarOrdenProvisional() throws DaoException {
        try {
            Orden orden = new Orden();
            orden.setNumeroOrden(2026002);
            orden.setFechaCreacion(Instant.now());
            orden.setResponsable("Sistema - Test");
            orden.setProveedor("Mariscos del Yaqui");
            orden.setEstado("Pendiente");
            orden.setEstadoFacturacion("Sin Facturar");

            Map<String, Double> items = new HashMap<>();
            items.put("6a00dc3cb11dcb39618c0b32", 45.5);
            orden.setItems(items);

            col.insertOne(orden);
            System.out.println("Orden de prueba insertada con éxito.");
        } catch (Exception e) {
            throw new DaoException("Error al insertar orden provisional: " + e.getMessage());
        }
    }

}

package com.mycompany.apifacturacion;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import org.bson.Document;
import org.bson.types.ObjectId;

public class ApiFacturacion {

    public static void procesarSolicitudes() {

        MongoClient cliente
                = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase db
                = cliente.getDatabase("mariscos");

        MongoCollection<Document> solicitudes
                = db.getCollection("solicitudes_factura");

        MongoCollection<Document> ordenes
                = db.getCollection("ordenes");

        FindIterable<Document> pendientes
                = solicitudes.find(eq("estado", "Sin Facturar"));

        for (Document solicitud : pendientes) {

            ObjectId ordenId
                    = solicitud.getObjectId("orden_id");

            // actualizar solicitud
            solicitudes.updateOne(
                    eq("_id", solicitud.getObjectId("_id")),
                    set("estado", "Facturada")
            );

            // 🔥 AQUÍ CORRECTO
            ordenes.updateOne(
                    eq("_id", ordenId),
                    set("estadoFacturacion", "Facturada")
            );

        }

        cliente.close();
    }
}

package com.mycompany.dto_mariscos;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Entidad que representa el carrito de compra temporal.
 * Almacena insumos y cantidades seleccionadas.
 */
public class Carrito {
    private Map<Insumo, Double> items; // insumo y cantidad

    public Carrito() {
        this.items = new LinkedHashMap<>();
    }

    public Map<Insumo, Double> getItems() {
        return items;
    }

    public void agregarInsumo(Insumo insumo, double cantidad) {
        items.put(insumo, items.getOrDefault(insumo, 0.0) + cantidad);
    }

    public void quitarInsumo(Insumo insumo) {
        items.remove(insumo);
    }

    public void actualizarCantidad(Insumo insumo, double nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            items.remove(insumo);
        } else {
            items.put(insumo, nuevaCantidad);
        }
    }

    public void vaciar() {
        items.clear();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "Carrito{" + "items=" + items + '}';
    }
}
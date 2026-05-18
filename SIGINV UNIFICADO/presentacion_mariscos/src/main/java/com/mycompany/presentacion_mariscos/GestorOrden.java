package com.mycompany.presentacion_mariscos;
import com.mycompany.dto_mariscos.Carrito;
import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.dto_mariscos.ItemOrden;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para la generación de órdenes de compra.
 * Contiene la lógica de negocio del caso de uso "Generar orden de compra".
 */
public class GestorOrden {
    
    // Contador estático para simular números de orden únicos
    private static int contadorOrdenes = 1;
    
    // Contraseña mock para validación del encargado de cocina
    private static final String CONTRASENA_ADMIN = "admin123";
    
    /**
     * Crea una nueva orden de compra a partir del carrito actual,
     * el proveedor seleccionado y el responsable.
     * @param carrito Carrito con los insumos y cantidades
     * @param proveedor Nombre del proveedor seleccionado
     * @param responsable Nombre del encargado de cocina
     * @return Orden generada
     */
    public Orden crearOrden(Carrito carrito, String proveedor, String responsable) {
        if (carrito == null || carrito.estaVacio()) {
            throw new IllegalStateException("No se puede crear una orden con el carrito vacío");
        }
        if (proveedor == null || proveedor.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar un proveedor");
        }
        
        // Convertir los items del carrito (Map<Insumo, Double>) a List<ItemOrden>
        List<ItemOrden> itemsOrden = new ArrayList<>();
        Map<Insumo, Double> itemsCarrito = carrito.getItems();
        
        for (Map.Entry<Insumo, Double> entry : itemsCarrito.entrySet()) {
            Insumo insumo = entry.getKey();
            Double cantidad = entry.getValue();
            
            // Crear ItemOrden con el nombre del insumo y la cantidad
            ItemOrden item = new ItemOrden(insumo.getNombre(), cantidad);
            itemsOrden.add(item);
        }
        
        Orden orden = new Orden();
        orden.setNumeroOrden(contadorOrdenes++);
        orden.setResponsable(responsable);
        orden.setProveedor(proveedor);
        orden.setItems(itemsOrden);  // ⬅️ Ahora setea List<ItemOrden>
        orden.setEstado("Pendiente");
        // La fecha de creación ya se asigna en el constructor de Orden
        
        return orden;
    }
    
    /**
     * Confirma la orden, cambiando su estado a "Confirmada".
     * Requiere validación de contraseña.
     * @param orden Orden a confirmar
     * @param contrasenia Contraseña ingresada
     * @return true si la contraseña es correcta y se confirma la orden
     */
    public boolean confirmarOrden(Orden orden, String contrasenia) {
        if (orden == null) {
            throw new IllegalArgumentException("La orden no puede ser nula");
        }
        if (!CONTRASENA_ADMIN.equals(contrasenia)) {
            return false;
        }
        orden.setEstado("Confirmada");
        return true;
    }
    
    /**
     * Envía la orden (simulación). En un caso real, podría notificar al proveedor,
     * guardar en BD, etc.
     * @param orden Orden a enviar
     */
    public void enviarOrden(Orden orden) {
        if (orden == null) {
            throw new IllegalArgumentException("Orden nula");
        }
        // Simulación: solo se imprime en consola
        System.out.println("Enviando orden #" + orden.getNumeroOrden() + " al proveedor " + orden.getProveedor());
        // En un sistema real aquí se llamaría a API de proveedor o se guardaría en BD
    }
    
    /**
     * Método auxiliar para vaciar el carrito después de generar la orden.
     * No es estrictamente necesario aquí, pero ayuda a mantener la separación.
     * @param carrito Carrito a vaciar
     */
    public void limpiarCarrito(Carrito carrito) {
        if (carrito != null) {
            carrito.vaciar();
        }
    }
    
    // getter para la contraseña mock (solo para pruebas)
    public static String getContrasenaAdmin() {
        return CONTRASENA_ADMIN;
    }
}
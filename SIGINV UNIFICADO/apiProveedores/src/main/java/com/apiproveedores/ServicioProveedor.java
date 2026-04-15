/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiproveedores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author joser
 */
public class ServicioProveedor extends JFrame{
    private List<Producto> productos;
    private Map<String, String> unidadesDeMedida;
    
    private final String[] proveedores = {
        "Distribuidora del Puerto", "Mariscos del Pacífico", 
        "Frutas La Central", "Abarrotes El Galeón", "Pesca Artesanal S.A."
    };
    
    
    public ServicioProveedor() {
        setTitle("Simulador Gestor de Proveedores");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        configurarDiccionarioUnidades();    
        inicializarProductos();

        JButton btnGenerar = new JButton("Generar Lista de Precios (JSON)");
        btnGenerar.setPreferredSize(new Dimension(250, 50));
        
        btnGenerar.addActionListener(e -> {
            generarJson();
            JOptionPane.showMessageDialog(this, "¡Archivo 'lista_proveedores.json' generado con éxito!");
        });

        add(btnGenerar);
    }
    
    
    private void configurarDiccionarioUnidades() {
        unidadesDeMedida = new HashMap<>();
        // --- PESCADOS ---
        unidadesDeMedida.put("Filete de Huachinango", "kg");
        unidadesDeMedida.put("Robalo Entero", "kg");
        unidadesDeMedida.put("Atún Aleta Amarilla", "kg");
        unidadesDeMedida.put("Salmón Noruego", "kg");
        unidadesDeMedida.put("Sierra para Ceviche", "kg");
        unidadesDeMedida.put("Medallón de Atún Aleta Azul", "kg");
        unidadesDeMedida.put("Lonja de Salmón Chileno", "kg");
        unidadesDeMedida.put("Filete de Tilapia Fresco", "kg");
        unidadesDeMedida.put("Filete de Mero", "kg");
        unidadesDeMedida.put("Medallón de Pez Espada", "kg");
        unidadesDeMedida.put("Trucha Arcoíris", "kg");

        // --- MARISCOS Y CEFALÓPODOS ---
        unidadesDeMedida.put("Camarón U15 (Gigante)", "kg");
        unidadesDeMedida.put("Camarón 21/25 (Coctelero)", "kg");
        unidadesDeMedida.put("Camarón con Cabeza 16/20", "kg");
        unidadesDeMedida.put("Pulpo Maya Grande", "kg");
        unidadesDeMedida.put("Tentáculos de Pulpo Cocido", "kg");
        unidadesDeMedida.put("Langosta del Caribe", "pieza");
        unidadesDeMedida.put("Callo de Hacha", "kg");
        unidadesDeMedida.put("Almeja Chocolata", "docena");
        unidadesDeMedida.put("Ostión en su Concha", "docena");
        unidadesDeMedida.put("Calamar Americano", "kg");
        unidadesDeMedida.put("Anillos de Calamar Gigante", "kg");
        unidadesDeMedida.put("Mejillón de Labios Verdes", "caja 1kg");
        unidadesDeMedida.put("Jaiba Entera", "kg");
        unidadesDeMedida.put("Pinzas de Cangrejo Real", "kg");
        unidadesDeMedida.put("Surimi en Barra", "kg");

        // --- VEGETALES Y FRUTAS ---
        unidadesDeMedida.put("Aguacate Hass Extra", "kg");
        unidadesDeMedida.put("Limón Colima Sin Semilla", "kg");
        unidadesDeMedida.put("Cebolla Morada", "kg");
        unidadesDeMedida.put("Cilantro Fresco", "manojo");
        unidadesDeMedida.put("Chile Habanero", "kg");
        unidadesDeMedida.put("Chile Jalapeño", "kg");
        unidadesDeMedida.put("Tomate Cherry", "domo 500g");
        unidadesDeMedida.put("Pepino Europeo", "kg");
        unidadesDeMedida.put("Mango Ataulfo", "kg");
        unidadesDeMedida.put("Piña Miel", "pieza");
        unidadesDeMedida.put("Ajo Blanco Pelado", "kg");

        // --- ABARROTES Y SALSAS ---
        unidadesDeMedida.put("Aceite de Oliva Virgen", "litro");
        unidadesDeMedida.put("Aceite Vegetal", "bidón 20L");
        unidadesDeMedida.put("Vinagre de Arroz", "botella 1L");
        unidadesDeMedida.put("Salsa Inglesa", "botella 150ml");
        unidadesDeMedida.put("Salsa Tabasco", "botella 150ml");
        unidadesDeMedida.put("Salsa de Soya", "botella 1L");
        unidadesDeMedida.put("Salsa Valentina Etiqueta Negra", "litro");
        unidadesDeMedida.put("Salsa Macha", "frasco 250g");
        unidadesDeMedida.put("Jugo de Tomate con Almeja", "litro");
        unidadesDeMedida.put("Mayonesa Clásica", "frasco 1kg");
        unidadesDeMedida.put("Arroz para Sushi", "costal 5kg");
        unidadesDeMedida.put("Pan Molido Panko", "bolsa 1kg");
        unidadesDeMedida.put("Alga Nori", "paquete 50 hojas");

        // --- ESPECIAS Y COMPLEMENTOS ---
        unidadesDeMedida.put("Tostadas de Maíz Horneadas", "paquete c/20");
        unidadesDeMedida.put("Galletas Saladas", "caja 12 paquetes");
        unidadesDeMedida.put("Sal de Mar Gruesa", "costal 1kg");
        unidadesDeMedida.put("Pimienta Negra Molida", "frasco 500g");
        unidadesDeMedida.put("Páprika Ahumada", "frasco 250g");
        unidadesDeMedida.put("Orégano Seco", "bolsa 200g");
        unidadesDeMedida.put("Consomé de Camarón en Polvo", "bote 1kg");
        unidadesDeMedida.put("Servilletas de Papel", "paquete 500pz");
    }
    
    private void inicializarProductos() {
        productos = new ArrayList<>();
        // Usamos las llaves del HashMap para obtener los nombres reales
        int id = 1;
        Random rnd = new Random();

        for (String nombreArticulo : unidadesDeMedida.keySet()) {
            String unidad = unidadesDeMedida.get(nombreArticulo);
            String prov = proveedores[rnd.nextInt(proveedores.length)];
            
            productos.add(new Producto(id++, nombreArticulo, unidad, prov));
        }
    }
    
    private void generarJson() {
        Random rnd = new Random();
        for (Producto p : productos) {
            double precio = 30 + (700 * rnd.nextDouble());
            p.setPrecio(Math.round(precio * 100.0) / 100.0);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("catalogo_proveedores.json")) {
            gson.toJson(productos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //prueba para conexión  con pantalla de proveedores
public List<Producto> obtenerProductos() {
    if (productos == null) {
        configurarDiccionarioUnidades();
        inicializarProductos();
    }
    return productos;
}
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ServicioProveedor().setVisible(true);
        });
    }
    
}


/**
 * 
 * // --- PESCADOS ---
            "Filete de Huachinango", "Robalo Entero", "Atún Aleta Amarilla", "Salmón Noruego", "Sierra para Ceviche",
            "Mojarra Tilapia", "Filete de Mero", "Medallón de Pez Espada", "Bacalao Noruego", "Pámpano Fresco",
            // --- MARISCOS ---
            "Camarón U15 (Gigante)", "Camarón 21/25 (Coctelero)", "Pulpo Maya Grande", "Langosta del Caribe", "Callo de Hacha",
            "Almeja Chocolata", "Ostión en su Concha", "Calamar Americano", "Jaiba Entera", "Manitas de Cangrejo Moro",
            "Mejillón Negro", "Tentáculo de Potón", "Anillo de Calamar", "Langostino de Río", "Caracol Marino",
            // --- VEGETALES Y FRUTAS ---
            "Aguacate Hass Extra", "Limón Colima Sin Semilla", "Cebolla Morada", "Cebolla Blanca", "Cilantro Fresco",
            "Chile Habanero Manzano", "Chile Serranno", "Tomate Saladette", "Pepino Europeo", "Mango Ataulfo",
            "Piña Miel", "Naranja para Jugo", "Ajo Blanco Pelado", "Jengibre Fresco", "Apio",
            // --- ABARROTES Y CONDIMENTOS ---
            "Aceite de Oliva Virgen", "Salsa Inglesa", "Salsa Tabasco", "Salsa de Soya", "Mayonesa Clásica",
            "Tostadas de Maíz Horneadas", "Galletas Saladas", "Sal de Mar Gruesa", "Pimienta Negra Molida", "Páprika Ahumada"
 * 
 * 
 * 
 */
package com.mycompany.presentacion_mariscos.SeleccionProveedor;

import com.apiproveedores.Producto;
import com.apiproveedores.ServicioProveedor;
import com.mycompany.dto_mariscos.Carrito;
import com.mycompany.presentacion_mariscos.PnlCarrito;
import com.mycompany.presentacion_mariscos.PnlProductos;
import com.mycompany.presentacion_mariscos.PnlResumenOrden;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.*;
import javax.swing.*;

public class PnlProveedores extends JPanel {

    private JPanel pnlContainer;
    private PnlProductos pnlProductos;
    private PnlCarrito pnlCarrito;
    private JLabel lblVentana;

    private Map<String, Set<String>> proveedorProductos;

    private String proveedorSeleccionado;
    private JPanel tarjetaSeleccionada;

    public PnlProveedores() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        cargarProveedores();
        construirPanel();
    }

    private void cargarProveedores() {
        proveedorProductos = new HashMap<>();

        ServicioProveedor servicio = new ServicioProveedor();
        List<Producto> productos = servicio.obtenerProductos();

        if (productos != null) {
            for (Producto p : productos) {
                proveedorProductos
                        .computeIfAbsent(p.getProveedor(), k -> new HashSet<>())
                        .add(p.getNombre());
            }
        }
    }

    private void construirPanel() {

        JPanel lista = new JPanel();
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
        lista.setBackground(Color.WHITE);
        lista.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        for (String proveedor : proveedorProductos.keySet()) {
            JPanel card = crearTarjetaProveedor(proveedor, proveedorProductos.get(proveedor));
            lista.add(card);
            lista.add(Box.createVerticalStrut(15));
        }

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(null);

        add(scroll, BorderLayout.CENTER);

        // BOTONES
        JPanel pnlBotones = new JPanel(new BorderLayout());
        pnlBotones.setBackground(Color.WHITE);
        pnlBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton btnAnterior = crearBoton("< Regresar");
        btnAnterior.addActionListener(e -> irAnterior());

        JButton btnSiguiente = crearBoton("Siguiente >");
        btnSiguiente.addActionListener(e -> irSiguiente());

        pnlBotones.add(btnAnterior, BorderLayout.WEST);
        pnlBotones.add(btnSiguiente, BorderLayout.EAST);

        add(pnlBotones, BorderLayout.SOUTH);

        proveedorSeleccionado = DatosOrden.proveedorSeleccionado;
        restaurarSeleccion();

    }

    private JPanel crearTarjetaProveedor(String proveedor, Set<String> productos) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(500, 120));

        card.putClientProperty("proveedor", proveedor);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblNombre = new JLabel(proveedor);
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel lblInfo = new JLabel("Productos disponibles: " + productos.size());
        lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JPanel pnlTexto = new JPanel(new GridLayout(2, 1));
        pnlTexto.setBackground(Color.WHITE);
        pnlTexto.add(lblNombre);
        pnlTexto.add(lblInfo);

        JButton btnSeleccionar = crearBoton("Seleccionar Proveedor");

        btnSeleccionar.addActionListener(e -> {

            proveedorSeleccionado = proveedor;

            if (tarjetaSeleccionada != null) {
                tarjetaSeleccionada.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }

            tarjetaSeleccionada = card;

            card.setBorder(BorderFactory.createLineBorder(new Color(35, 53, 74), 3));
        });

        card.add(pnlTexto, BorderLayout.CENTER);
        card.add(btnSeleccionar, BorderLayout.SOUTH);

        return card;
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(35, 53, 74));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        return btn;
    }

    private void irSiguiente() {
        if (proveedorSeleccionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un proveedor antes de continuar");
            return;
        }

        DatosOrden.proveedorSeleccionado = proveedorSeleccionado;
        System.out.println("Proveedor seleccionado: " + proveedorSeleccionado);

        Carrito carrito = pnlCarrito.getCarritoEntidad();   // Declaración local
        String responsable = "Encargado de cocina";

        PnlResumenOrden resumen = new PnlResumenOrden(
                pnlContainer, 
                this, 
                pnlCarrito, 
                pnlProductos, 
                lblVentana, 
                carrito, 
                proveedorSeleccionado, 
                responsable 
        );

        pnlContainer.removeAll();
        pnlContainer.add(resumen, BorderLayout.CENTER);
        lblVentana.setText("Resumen de la Orden");
        pnlContainer.revalidate();
        pnlContainer.repaint();

    }

    private void irAnterior() {
        if (pnlContainer != null && pnlProductos != null && pnlCarrito != null && lblVentana != null) {
            pnlContainer.removeAll();
            pnlContainer.add(pnlProductos, BorderLayout.CENTER);
            pnlContainer.add(pnlCarrito, BorderLayout.EAST);
            lblVentana.setText("Generar Orden De Compra");
            pnlContainer.revalidate();
            pnlContainer.repaint();
        }
    }

    private void restaurarSeleccion() {

        if (DatosOrden.proveedorSeleccionado == null) {
            return;
        }

        for (Component comp : getComponents()) {
            buscarYMarcar(comp);
        }
    }

    private void buscarYMarcar(Component comp) {

        if (comp instanceof JPanel panel) {

            Object nombre = panel.getClientProperty("proveedor");

            if (nombre != null && nombre.equals(DatosOrden.proveedorSeleccionado)) {

                if (tarjetaSeleccionada != null) {
                    tarjetaSeleccionada.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                }

                tarjetaSeleccionada = panel;
                panel.setBorder(BorderFactory.createLineBorder(new Color(35, 53, 74), 3));
                proveedorSeleccionado = (String) nombre;
            }

            for (Component child : panel.getComponents()) {
                buscarYMarcar(child);
            }
        }
    }

    public void setReferences(JPanel pnlContainer, PnlProductos pnlProductos,
            PnlCarrito pnlCarrito, JLabel lblVentana) {
        this.pnlContainer = pnlContainer;
        this.pnlProductos = pnlProductos;
        this.pnlCarrito = pnlCarrito;
        this.lblVentana = lblVentana;
    }

    /*
    
    para dar reset al proveedor seleccionado en la orden
    
    DatosOrden.proveedorSeleccionado = null;
    
    --------------------------------------------------------------------------
    
    Agregar al boton de regresarde confirmacion de 
    orden para que puedas cambiar de eleccion de proveedor
    
    restaurarSeleccion();
    
     */
}

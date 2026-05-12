/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.presentacion_mariscos.CarritoInsumos;

import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.dto_mariscos.Inventario;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author demib
 */


// CLASE PARA LA CREACION Y ESTILIZACION DE COMO SE MUESTRA UN INSUMO EN EL PANEL CARRITO
public class ItemCarrito extends JPanel {

    private Insumo insumo;
    private Inventario inventario;
    private Double cantidad;

    private JLabel lblStock;
    private JLabel lblNombre;
    private JLabel lblCantidad;
    private JButton btnEliminar;
    private JButton btnMenos;
    private JButton btnMas;

    public ItemCarrito(Inventario inventario, Double cantidad, PnlCarrito pnlCarrito) {
        this.inventario = inventario;
        this.cantidad = cantidad;
        initComponentes();

        

        // al eliminar, quita de la vista Y de la lista
        btnEliminar.addActionListener(e -> {
            pnlCarrito.eliminarItem(this, inventario.getInsumo());
        });
    }
    
    
    private void initComponentes() {
        setLayout(new BorderLayout(5, 2));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        setBackground(Color.WHITE);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        // Label stock (arriba)
        lblStock = new JLabel("Stock: " + inventario.formatearStock(inventario.getStockActual())+ " (Bajo)");
        lblStock.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lblStock.setForeground(Color.GRAY);

        // Nombre del insumo
        lblNombre = new JLabel(inventario.getInsumo().getNombre());
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 13));

        // Panel izquierdo (stock + nombre)
        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new BoxLayout(pnlInfo, BoxLayout.Y_AXIS));
        pnlInfo.setBackground(Color.WHITE);
        pnlInfo.add(lblStock);
        pnlInfo.add(lblNombre);

        // Boton eliminar
        btnEliminar = new JButton("🗑");
        btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnEliminar.setPreferredSize(new Dimension(32, 32));
        btnEliminar.setFocusable(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        btnEliminar.setBackground(Color.WHITE);

        // Boton menos
        btnMenos = new JButton("-");
        btnMenos.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnMenos.setPreferredSize(new Dimension(32, 32));
        btnMenos.setFocusable(false);
        btnMenos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMenos.setBackground(new Color(50, 50, 50));
        btnMenos.setForeground(Color.WHITE);
        btnMenos.setBorder(BorderFactory.createEmptyBorder());

        // Boton mas
        btnMas = new JButton("+");
        btnMas.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnMas.setPreferredSize(new Dimension(32, 32));
        btnMas.setFocusable(false);
        btnMas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMas.setBackground(new Color(50, 50, 50));
        btnMas.setForeground(Color.WHITE);
        btnMas.setBorder(BorderFactory.createEmptyBorder());

        // Label cantidad
        lblCantidad = new JLabel(cantidad + " " + inventario.getInsumo().getUnidadMedida());
        lblCantidad.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
        lblCantidad.setPreferredSize(new Dimension(50, 32));

        // Acciones
        btnMas.addActionListener(e -> {
            //Unidad de Medida del Insumo para validaciones
            String unidadMedida = inventario.getInsumo().getUnidadMedida();

            //Validacion para la unidad de medida
            if (unidadMedida.equals("botella") || unidadMedida.equals("paquete") || unidadMedida.equals("pieza")) {
                
                cantidad += 1;
                lblCantidad.setText(cantidad.intValue() + "");
            } else {
                cantidad += 0.5;
                lblCantidad.setText(cantidad + " " + inventario.getInsumo().getUnidadMedida());
            }

            
        });

        btnMenos.addActionListener(e -> {
            if (cantidad > 0) {

                //Unidad de Medida del Insumo para validaciones
                String unidadMedida = inventario.getInsumo().getUnidadMedida();

                //Validacion para la unidad de medida
                if (unidadMedida.equals("botella") || unidadMedida.equals("paquete") || unidadMedida.equals("pieza")) {

                    cantidad -= 1;
                    lblCantidad.setText(cantidad.intValue() + "");
                } else {
                    cantidad -= 0.5;
                    lblCantidad.setText(cantidad + " " + inventario.getInsumo().getUnidadMedida());
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            Container padre = getParent();
            padre.remove(this);
            padre.revalidate();
            padre.repaint();
        });

        // Panel derecho (eliminar + menos + cantidad + mas)
        JPanel pnlControles = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        pnlControles.setBackground(Color.WHITE);
        pnlControles.add(btnEliminar);
        pnlControles.add(btnMenos);
        pnlControles.add(lblCantidad);
        pnlControles.add(btnMas);

        add(pnlInfo, BorderLayout.WEST);
        add(pnlControles, BorderLayout.EAST);
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public JLabel getLblStock() {
        return lblStock;
    }

    public void setLblStock(JLabel lblStock) {
        this.lblStock = lblStock;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnMenos() {
        return btnMenos;
    }

    public void setBtnMenos(JButton btnMenos) {
        this.btnMenos = btnMenos;
    }

    public JButton getBtnMas() {
        return btnMas;
    }

    public void setBtnMas(JButton btnMas) {
        this.btnMas = btnMas;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.presentacion_mariscos.CarritoInsumos;

import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.dto_mariscos.Inventario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.net.HttpURLConnection;

/**
 *
 * @author demib
 */
// CLASE PARA LA CREACION Y ESTILIZACION DE UN CARD DE INSUMO 
public class CardInsumos extends JPanel {

    private JLabel lblNombre;
    private JLabel lblEstado;
    private JLabel lblStock;
    private JLabel lblImagen;
    private JTextField txtCantidad;
    private JLabel lblUnidad;
    private JButton btnAgregar;

    public CardInsumos(Inventario inventario) {

        Insumo insumo = inventario.getInsumo();
        String nombre = insumo.getNombre();
        String unidad = insumo.getUnidadMedida();
        double stock = inventario.getStockActual();
        String imagenUrl = inventario.getInsumo().getImagenUrl();

        String estado = "Disponible";

        System.out.println("Insumo: " + nombre + " | URL: " + imagenUrl);

        setPreferredSize(new Dimension(180, 260));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        lblNombre = new JLabel(nombre);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblEstado = new JLabel("Estado: " + estado);
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (stock <= inventario.getStockMinimo()) {
            estado = "Bajo";
            lblEstado.setText("Estado: " + estado);
            lblEstado.setForeground(Color.red);
        }

        //IMAGEN
        lblImagen = new JLabel();
        lblImagen.setOpaque(true);
        lblImagen.setBackground(Color.WHITE);
        lblImagen.setPreferredSize(new Dimension(90, 90));
        lblImagen.setMaximumSize(new Dimension(90, 90));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Llamamos al metodo cargarImagen para que cargue la img alojada en imgur
        cargarImagen(lblImagen, imagenUrl);

        lblStock = new JLabel("Stock: " + inventario.formatearStock(inventario.getStockActual()));
        lblStock.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtCantidad = new JTextField("1");
        txtCantidad.setPreferredSize(new Dimension(35, 25));

        lblUnidad = new JLabel(unidad);

        btnAgregar = new JButton("+");

        JPanel pnlInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        pnlInferior.setOpaque(false);
        pnlInferior.add(txtCantidad);
        pnlInferior.add(lblUnidad);
        pnlInferior.add(btnAgregar);

        add(Box.createVerticalStrut(10));
        add(lblNombre);
        add(Box.createVerticalStrut(10));
        add(lblEstado);
        add(Box.createVerticalStrut(10));
        add(lblImagen);
        add(Box.createVerticalStrut(10));
        add(lblStock);
        add(Box.createVerticalGlue());
        add(pnlInferior);
        add(Box.createVerticalStrut(10));
    }

    private void cargarImagen(JLabel label, String url) {
        if (url == null || url.isBlank()) {
            label.setText("IMG");
            return;
        }

        // se hace en hilo separado para no congelar la UI
        new Thread(() -> {
            try {
                URL imageUrl = new URL(url);
                ImageIcon icon = new ImageIcon(imageUrl);
                Image scaled = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                javax.swing.SwingUtilities.invokeLater(() -> {
                    label.setIcon(new ImageIcon(scaled));
                    label.setText(""); // quita el texto IMG
                });
            } catch (Exception e) {
                javax.swing.SwingUtilities.invokeLater(() -> label.setText("Sin img"));
            }
        }).start();
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblEstado() {
        return lblEstado;
    }

    public void setLblEstado(JLabel lblEstado) {
        this.lblEstado = lblEstado;
    }

    public JLabel getLblStock() {
        return lblStock;
    }

    public void setLblStock(JLabel lblStock) {
        this.lblStock = lblStock;
    }

    public JLabel getLblImagen() {
        return lblImagen;
    }

    public void setLblImagen(JLabel lblImagen) {
        this.lblImagen = lblImagen;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(JTextField txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public JLabel getLblUnidad() {
        return lblUnidad;
    }

    public void setLblUnidad(JLabel lblUnidad) {
        this.lblUnidad = lblUnidad;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(JButton btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

}

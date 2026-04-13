/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.presentacion_mariscos;

/**
 *
 * @author anapa
 */
import com.mycompany.dto_mariscos.*;
import com.mycompany.negocios_orden.FachadaOrden;

import javax.swing.*;
import java.awt.*;

public class ConfirmacionOrdenFrame extends JFrame {
    private OrdenDTO orden;

    public ConfirmacionOrdenFrame(OrdenDTO orden) {
        this.orden = orden;

        setTitle("Confirmación");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        String texto = "Cliente: " + orden.getCliente() + "\n\n";

        for (Insumo i : orden.getInsumos()) {
            texto += "- " + i.getNombre() + "\n";
        }

        texto += "\nTotal: $" + orden.getTotal();

        area.setText(texto);

        JButton btnConfirmar = new JButton("Confirmar");

        btnConfirmar.addActionListener(e -> confirmar());

        add(new JScrollPane(area), BorderLayout.CENTER);
        add(btnConfirmar, BorderLayout.SOUTH);
    }

    private void confirmar() {
        FachadaOrden fachada = new FachadaOrden();
        fachada.confirmarOrden(orden);

        JOptionPane.showMessageDialog(this, "Orden Confirmada");
    }
    
}

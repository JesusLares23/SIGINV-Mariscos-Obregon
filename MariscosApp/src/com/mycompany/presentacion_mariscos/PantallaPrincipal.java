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
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class PantallaPrincipal extends JFrame{
    public PantallaPrincipal() {
        setTitle("Sistema Mariscos");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnOrden = new JButton("Crear Orden");

        btnOrden.addActionListener(e -> crearOrden());

        add(btnOrden);
    }

    private void crearOrden() {

        List<Insumo> insumos = new ArrayList<>();
        insumos.add(new Insumo("Camarón"));
        insumos.add(new Insumo("Pulpo"));

        OrdenDTO orden = new OrdenDTO("Ana", insumos, 200);

        new ConfirmacionOrdenFrame(orden).setVisible(true);
    }
    
}


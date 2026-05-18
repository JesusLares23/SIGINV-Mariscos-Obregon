package com.mycompany.presentacion_mariscos.SolicitudFactura;

import com.mycompany.controller_mariscos.solicitudFactura.ISolicitudFacturaControl;
import com.mycompany.dto_mariscos.SolicitudFacturaDTO;
import com.mycompany.exception_mariscos.DaoException;
import com.mycompany.exception_mariscos.EntityNotFoundException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class pnlConfirmacionFactura extends javax.swing.JPanel {

    private SolicitudFacturaDTO solicitudDTO;
    private ISolicitudFacturaControl solicitudControl;

    public pnlConfirmacionFactura() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConfirmacion = new javax.swing.JTextArea();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 400));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 0, 24));
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setText("Confirmación de Solicitud");

        txtConfirmacion.setColumns(20);
        txtConfirmacion.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtConfirmacion.setForeground(new java.awt.Color(0, 0, 0));
        txtConfirmacion.setRows(5);
        txtConfirmacion.setEditable(false);
        jScrollPane1.setViewportView(txtConfirmacion);

        btnConfirmar.setBackground(new java.awt.Color(35, 53, 74));
        btnConfirmar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(35, 53, 74));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 16));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTitulo)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(180, 180, 180)
                                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblTitulo)
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20))
        );
    }

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            solicitudControl.crearSolicitudFactura(solicitudDTO);
            JOptionPane.showMessageDialog(this, "Solicitud de factura creada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            javax.swing.JDialog dialog = (javax.swing.JDialog) javax.swing.SwingUtilities.getWindowAncestor(this);
            if (dialog != null) {
                dialog.dispose();
            }
        } catch (EntityNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "No se encontró la entidad: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(this, "Error en acceso a datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        javax.swing.JDialog dialog = (javax.swing.JDialog) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (dialog != null) {
            dialog.dispose();
        }
    }

    public void setSolicitudDTO(SolicitudFacturaDTO solicitud) {
        this.solicitudDTO = solicitud;
        mostrarConfirmacion();
    }

    public void setSolicitudControl(ISolicitudFacturaControl control) {
        this.solicitudControl = control;
    }

    private void mostrarConfirmacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("Orden Número: ").append(solicitudDTO.getNumeroOrden()).append("\n\n");
        sb.append("Datos Fiscales:\n");
        sb.append("RFC: ").append(solicitudDTO.getRfc()).append("\n");
        sb.append("Razón Social: ").append(solicitudDTO.getRazonSocial()).append("\n");
        sb.append("Régimen Fiscal: ").append(solicitudDTO.getRegimenFiscal()).append("\n");
        sb.append("Uso CFDI: ").append(solicitudDTO.getUsoCFDI()).append("\n\n");
        sb.append("Dirección:\n");
        sb.append("Calle: ").append(solicitudDTO.getCalle()).append("\n");
        sb.append("Código Postal: ").append(solicitudDTO.getCodigoPostal()).append("\n\n");
        sb.append("Correo: ").append(solicitudDTO.getCorreo()).append("\n");
        sb.append("Fecha Solicitud: ").append(sdf.format(solicitudDTO.getFechaSolicitud())).append("\n");
        sb.append("Estado: ").append(solicitudDTO.getEstadoFactura());

        txtConfirmacion.setText(sb.toString());
    }

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtConfirmacion;
}

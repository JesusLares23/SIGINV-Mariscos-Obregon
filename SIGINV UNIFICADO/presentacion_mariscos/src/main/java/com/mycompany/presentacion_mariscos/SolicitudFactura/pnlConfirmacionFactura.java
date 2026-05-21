package com.mycompany.presentacion_mariscos.SolicitudFactura;

//import com.mycompany.apifacturacion.ApiFacturacion;
import com.mycompany.controller_marisco.orden.IOrdenControl;
import com.mycompany.controller_mariscos.solicitudFactura.ISolicitudFacturaControl;
import com.mycompany.dto_mariscos.mermas.OrdenDTO;
import com.mycompany.dto_mariscos.solicitudFactura.SolicitudFacturaDTO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class pnlConfirmacionFactura extends JPanel {

    private SolicitudFacturaDTO solicitud;
    private ISolicitudFacturaControl control;

    private JTextArea area;

    private JButton btnConfirmar;
    private JButton btnCancelar;

    private JPasswordField txtPassword;

    private OrdenDTO orden;

    private String idOrden;

    private IOrdenControl ordenControl;
    
    private SeleccionOrden seleccionOrdenPanel;

    public pnlConfirmacionFactura() {

        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);

        txtPassword = new JPasswordField(20);

        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(e -> confirmar());
        btnCancelar.addActionListener(e -> cancelar());

        JPanel southPanel = new JPanel(new BorderLayout());

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Contraseña: "));
        passwordPanel.add(txtPassword);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botonesPanel.add(btnConfirmar);
        botonesPanel.add(btnCancelar);

        southPanel.add(passwordPanel, BorderLayout.CENTER);
        southPanel.add(botonesPanel, BorderLayout.EAST);

        add(new JScrollPane(area), BorderLayout.CENTER);

        add(southPanel, BorderLayout.SOUTH);
    }

    public void setSolicitudDTO(SolicitudFacturaDTO solicitud) {

        this.solicitud = solicitud;

        String texto
                = "RFC: " + solicitud.getRfc() + "\n\n"
                + "Razón Social: " + solicitud.getRazonSocial() + "\n\n"
                + "Uso CFDI: " + solicitud.getUsoCFDI() + "\n\n"
                + "Régimen Fiscal: "
                + solicitud.getRegimenFiscal() + "\n\n"
                + "Dirección:\n"
                + solicitud.getCalle() + ", "
                + solicitud.getColonia() + " CP "
                + solicitud.getCp() + "\n\n"
                + "Correo: "
                + solicitud.getCorreo() + "\n\n"
                + "Orden ID: "
                + solicitud.getOrden_id();

        area.setText(texto);
    }

    public void setOrden(OrdenDTO orden) {
        this.orden = orden;
    }

    public void setControl(ISolicitudFacturaControl control) {
        this.control = control;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public void setOrdenControl(IOrdenControl control) {
        this.ordenControl = control;
    }
    
    public void setSeleccionOrdenPanel(SeleccionOrden seleccionOrdenPanel) {
        this.seleccionOrdenPanel = seleccionOrdenPanel;
    }

    private void confirmar() {

        btnConfirmar.setEnabled(false);
        btnCancelar.setEnabled(false);

        String password = new String(txtPassword.getPassword());

        if (!"1234".equals(password)) {

            btnConfirmar.setEnabled(true);
            btnCancelar.setEnabled(true);

            JOptionPane.showMessageDialog(
                    this,
                    "Contraseña incorrecta"
            );

            return;
        }

        try {

            // VALIDAR DUPLICADOS
            if (control.existeSolicitudParaOrden(idOrden)) {

                btnConfirmar.setEnabled(true);
                btnCancelar.setEnabled(true);

                JOptionPane.showMessageDialog(
                        this,
                        "Esta orden ya tiene una solicitud"
                );

                return;
            }

            // GUARDAR SOLICITUD
            control.guardar(solicitud);
            
            // ACTUALIZAR ESTADO DE FACTURACIÓN DE LA ORDEN A "facturado"
            actualizarEstadoOrden();
 
            // PROCESAR SOLICITUDES Y ACTUALIZAR ESTADO EN MONGO
            //ApiFacturacion.procesarSolicitudes();
        

            JOptionPane.showMessageDialog(
                    this,
                    "Factura registrada correctamente"
            );
     
            // CERRAR DIALOGO
            JDialog dialog
                    = (JDialog) SwingUtilities.getWindowAncestor(this);

            dialog.dispose();

        } catch (Exception ex) {

            btnConfirmar.setEnabled(true);
            btnCancelar.setEnabled(true);

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }
    
    private void cancelar() {
        JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(this);
        dialog.dispose();
    }
    
    /**
     * Actualiza ESPECÍFICAMENTE el estado de facturación de la orden a "facturado"
     */
    private void actualizarEstadoOrden() {
        try {
            if (ordenControl != null && idOrden != null && !idOrden.isEmpty()) {
                // Usar el nuevo método que SOLO actualiza estadoFacturacion
                ordenControl.actualizarSoloEstadoFacturacion(idOrden, "facturado");
                System.out.println("DEBUG: Orden actualizada a estado de facturación 'facturado': " + idOrden);
            }
        } catch (Exception ex) {
            System.out.println("ERROR al actualizar estado de facturación de orden: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
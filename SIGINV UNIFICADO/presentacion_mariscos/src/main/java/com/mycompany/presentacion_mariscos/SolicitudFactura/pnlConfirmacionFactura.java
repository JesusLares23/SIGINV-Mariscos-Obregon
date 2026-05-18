package com.mycompany.presentacion_mariscos.SolicitudFactura;


import com.mycompany.dto_mariscos.Orden;
import com.mycompany.dto_mariscos.solicitudFactura.SolicitudFacturaDTO;
import com.mycompany.controller_mariscos.solicitudFactura.ISolicitudFacturaControl;
import javax.swing.*;
import java.awt.*;

public class pnlConfirmacionFactura extends JPanel {
    private SolicitudFacturaDTO solicitud;
    private ISolicitudFacturaControl control;
    private JTextArea area;
    private JButton btnConfirmar;
    private JPasswordField txtPassword;   // campo de contraseña
    private Orden orden;                  // referencia a la orden seleccionada

    public pnlConfirmacionFactura() {
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);

        txtPassword = new JPasswordField(20);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> confirmar());

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(new JLabel("Contraseña: "), BorderLayout.WEST);
        southPanel.add(txtPassword, BorderLayout.CENTER);
        southPanel.add(btnConfirmar, BorderLayout.EAST);

        add(new JScrollPane(area), BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    public void setSolicitudDTO(SolicitudFacturaDTO solicitud) {
        this.solicitud = solicitud;
        String texto =
                "RFC: " + solicitud.getRfc() + "\n\n"
                + "Razón Social: " + solicitud.getRazonSocial() + "\n\n"
                + "Uso CFDI: " + solicitud.getUsoCFDI() + "\n\n"
                + "Régimen Fiscal: " + solicitud.getRegimenFiscal() + "\n\n"
                + "Dirección:\n"
                + solicitud.getCalle() + ", "
                + solicitud.getColonia() + " CP "
                + solicitud.getCp() + "\n\n"
                + "Correo: " + solicitud.getCorreo() + "\n\n"
                + "Pedido ID: " + solicitud.getIdPedido();
        area.setText(texto);
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public void setControl(ISolicitudFacturaControl control) {
        this.control = control;
    }

    private void confirmar() {
        String password = new String(txtPassword.getPassword());
        if (!"1234".equals(password)) { // ejemplo de validación
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            control.guardar(solicitud);
            if (orden != null) {
                orden.setEstadoFacturacion("Facturado");
            }
            JOptionPane.showMessageDialog(this, "Factura registrada correctamente");
            SwingUtilities.getWindowAncestor(this).dispose(); // cerrar el diálogo
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

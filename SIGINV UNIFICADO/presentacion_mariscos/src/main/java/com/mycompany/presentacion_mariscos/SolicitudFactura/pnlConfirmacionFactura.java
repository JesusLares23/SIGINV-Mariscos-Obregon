package com.mycompany.presentacion_mariscos.SolicitudFactura;

import com.mycompany.controller_mariscos.orden.IOrdenControl;
import com.mycompany.controller_mariscos.solicitudFactura.ISolicitudFacturaControl;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.dto_mariscos.solicitudFactura.SolicitudFacturaDTO;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import javax.swing.JButton;
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
    private JPasswordField txtPassword;
    private Orden orden;
    private int idOrden;
    private IOrdenControl ordenControl;

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
        String texto
                = "RFC: " + solicitud.getRfc() + "\n\n"
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

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public void setOrdenControl(IOrdenControl control) {
        this.ordenControl = control;  // ⬅️ AGREGAR
    }

    private void confirmar() {
        String password = new String(txtPassword.getPassword());
        if (!"1234".equals(password)) {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            control.guardar(solicitud);

            // ⬅️ AGREGAR: Actualizar el estado de la orden en la BD
            if (ordenControl != null && idOrden > 0) {
                Orden orden = ordenControl.obtenerOrdenPorNumero(idOrden);
                orden.setEstadoFacturacion("Facturado");
                ordenControl.actualizarOrden(orden);
                System.out.println("DEBUG: Orden #" + idOrden + " actualizada a Facturado");
            }

            JOptionPane.showMessageDialog(this, "Factura registrada correctamente");
            SwingUtilities.getWindowAncestor(this).dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

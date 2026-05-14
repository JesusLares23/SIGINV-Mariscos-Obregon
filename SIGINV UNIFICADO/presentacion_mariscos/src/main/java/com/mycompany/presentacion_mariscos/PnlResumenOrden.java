package com.mycompany.presentacion_mariscos;

import com.mycompany.dto_mariscos.Carrito;
import com.mycompany.dto_mariscos.Insumo;
import com.mycompany.dto_mariscos.Orden;
import com.mycompany.presentacion_mariscos.GestorOrden;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PnlResumenOrden extends JPanel {

    private JPanel pnlContainer;
    private JPanel pnlAnterior;
    private JPanel pnlCarritoPanel;
    private JPanel pnlProductosPanel;
    private JLabel lblVentana;
    private Carrito carrito;
    private String proveedorSeleccionado;
    private String responsable;

    private Orden ordenActual;
    private GestorOrden gestorOrden;

    // Componentes
    private JLabel lblNumeroOrden, lblFecha, lblResponsable, lblEstado;
    private JLabel lblNombreProveedor, lblCategoriaProveedor, lblTiempoEntrega;
    private JTextArea txtNotas;
    private JTable tablaInsumos;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotalInsumos;
    private JButton btnEditar, btnCancelar, btnConfirmar;

    public PnlResumenOrden(JPanel pnlContainer, JPanel pnlAnterior,
            JPanel pnlCarritoPanel, JPanel pnlProductosPanel,
            JLabel lblVentana, Carrito carrito,
            String proveedorSeleccionado, String responsable) {
        this.pnlContainer = pnlContainer;
        this.pnlAnterior = pnlAnterior;
        this.pnlCarritoPanel = pnlCarritoPanel;
        this.pnlProductosPanel = pnlProductosPanel;
        this.lblVentana = lblVentana;
        this.carrito = carrito;
        this.proveedorSeleccionado = proveedorSeleccionado;
        this.responsable = responsable;

        gestorOrden = new GestorOrden();
        ordenActual = gestorOrden.crearOrden(carrito, proveedorSeleccionado, responsable);

        initComponents();
        cargarDatosOrden();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel principal con tres columnas usando GridBagLayout
        JPanel pnlPrincipal = new JPanel(new GridBagLayout());
        pnlPrincipal.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        // ----- COLUMNA IZQUIERDA (datos de orden, proveedor, notas) -----
        JPanel pnlIzquierda = new JPanel();
        pnlIzquierda.setLayout(new BoxLayout(pnlIzquierda, BoxLayout.Y_AXIS));
        pnlIzquierda.setBackground(Color.WHITE);

        // 1. Datos de la Orden
        JPanel pnlDatosOrden = new JPanel(new BorderLayout());
        pnlDatosOrden.setBorder(BorderFactory.createTitledBorder("Datos de la Orden"));
        JPanel pnlDatosGrid = new JPanel(new GridLayout(0, 2, 10, 5));
        pnlDatosGrid.setBackground(Color.WHITE);
        lblNumeroOrden = new JLabel();
        lblFecha = new JLabel();
        lblResponsable = new JLabel();
        lblEstado = new JLabel();
        pnlDatosGrid.add(new JLabel("Núm. Orden:"));
        pnlDatosGrid.add(lblNumeroOrden);
        pnlDatosGrid.add(new JLabel("Fecha:"));
        pnlDatosGrid.add(lblFecha);
        pnlDatosGrid.add(new JLabel("Responsable:"));
        pnlDatosGrid.add(lblResponsable);
        pnlDatosGrid.add(new JLabel("Estado:"));
        pnlDatosGrid.add(lblEstado);
        pnlDatosOrden.add(pnlDatosGrid, BorderLayout.CENTER);
        pnlIzquierda.add(pnlDatosOrden);
        pnlIzquierda.add(Box.createVerticalStrut(15));

        // 2. Proveedor
        JPanel pnlProveedor = new JPanel(new BorderLayout());
        pnlProveedor.setBorder(BorderFactory.createTitledBorder("Proveedor"));
        JPanel pnlProveedorGrid = new JPanel(new GridLayout(0, 2, 10, 5));
        pnlProveedorGrid.setBackground(Color.WHITE);
        lblNombreProveedor = new JLabel();
        lblCategoriaProveedor = new JLabel("Mariscos");  // mock
        lblTiempoEntrega = new JLabel("24 h");
        pnlProveedorGrid.add(new JLabel("Nombre:"));
        pnlProveedorGrid.add(lblNombreProveedor);
        pnlProveedorGrid.add(new JLabel("Categoría:"));
        pnlProveedorGrid.add(lblCategoriaProveedor);
        pnlProveedorGrid.add(new JLabel("Tiempo entrega:"));
        pnlProveedorGrid.add(lblTiempoEntrega);
        pnlProveedor.add(pnlProveedorGrid, BorderLayout.CENTER);
        pnlIzquierda.add(pnlProveedor);
        pnlIzquierda.add(Box.createVerticalStrut(15));

        // 3. Notas adicionales
        JPanel pnlNotas = new JPanel(new BorderLayout());
        pnlNotas.setBorder(BorderFactory.createTitledBorder("Notas adicionales"));
        txtNotas = new JTextArea(5, 20);
        txtNotas.setLineWrap(true);
        txtNotas.setWrapStyleWord(true);
        JScrollPane scrollNotas = new JScrollPane(txtNotas);
        pnlNotas.add(scrollNotas, BorderLayout.CENTER);
        pnlIzquierda.add(pnlNotas);
        pnlIzquierda.add(Box.createVerticalGlue());

        gbc.gridx = 0;
        gbc.weightx = 0.35;
        gbc.fill = GridBagConstraints.BOTH;
        pnlPrincipal.add(pnlIzquierda, gbc);

        // ----- COLUMNA CENTRAL: Tabla de insumos -----
        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.setBackground(Color.WHITE);
        pnlCentro.setBorder(BorderFactory.createTitledBorder("Lista de Insumos Solicitados"));

        modeloTabla = new DefaultTableModel(new String[]{"Insumo", "Cantidad", "Unidad"}, 0);
        tablaInsumos = new JTable(modeloTabla);
        tablaInsumos.setRowHeight(25);
        JScrollPane scrollTabla = new JScrollPane(tablaInsumos);
        pnlCentro.add(scrollTabla, BorderLayout.CENTER);

        lblTotalInsumos = new JLabel("Total de Insumos: 0", SwingConstants.RIGHT);
        lblTotalInsumos.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlCentro.add(lblTotalInsumos, BorderLayout.SOUTH);

        gbc.gridx = 1;
        gbc.weightx = 0.45;
        pnlPrincipal.add(pnlCentro, gbc);

        // ----- COLUMNA DERECHA: Botones verticales -----
        JPanel pnlDerecha = new JPanel();
        pnlDerecha.setLayout(new BoxLayout(pnlDerecha, BoxLayout.Y_AXIS));
        pnlDerecha.setBackground(Color.WHITE);
        pnlDerecha.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnEditar = new JButton("Editar Orden");
        btnCancelar = new JButton("Cancelar Orden");
        btnConfirmar = new JButton("Confirmar Orden");
        estilizarBoton(btnEditar);
        estilizarBoton(btnCancelar);
        estilizarBoton(btnConfirmar);
        btnEditar.setAlignmentX(CENTER_ALIGNMENT);
        btnCancelar.setAlignmentX(CENTER_ALIGNMENT);
        btnConfirmar.setAlignmentX(CENTER_ALIGNMENT);

        pnlDerecha.add(btnEditar);
        pnlDerecha.add(Box.createVerticalStrut(15));
        pnlDerecha.add(btnCancelar);
        pnlDerecha.add(Box.createVerticalStrut(15));
        pnlDerecha.add(btnConfirmar);
        pnlDerecha.add(Box.createVerticalGlue());

        gbc.gridx = 2;
        gbc.weightx = 0.2;
        pnlPrincipal.add(pnlDerecha, gbc);

        add(pnlPrincipal, BorderLayout.CENTER);

        // Acciones de botones (igual que antes)
        btnEditar.addActionListener(e -> editarOrden());
        btnCancelar.addActionListener(e -> cancelarOrden());
        btnConfirmar.addActionListener(e -> confirmarOrden());
    }

    private JPanel crearPanelTitulo(String titulo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(lblTitulo, BorderLayout.NORTH);
        JPanel contenido = new JPanel();
        contenido.setBackground(Color.WHITE);
        panel.add(contenido, BorderLayout.CENTER);
        return panel;
    }

    private void estilizarBoton(JButton btn) {
        btn.setBackground(new Color(35, 53, 74));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(160, 40));
        btn.setMaximumSize(new Dimension(160, 40));
    }

    private void cargarDatosOrden() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        lblNumeroOrden.setText(String.valueOf(ordenActual.getNumeroOrden()));
        lblFecha.setText(sdf.format(ordenActual.getFechaCreacion()));
        lblResponsable.setText(ordenActual.getResponsable());
        lblEstado.setText(ordenActual.getEstado());
        lblNombreProveedor.setText(ordenActual.getProveedor());

        // Cargar tabla de insumos
        Map<Insumo, Double> items = ordenActual.getItems();
        modeloTabla.setRowCount(0);
        int total = 0;
        for (Map.Entry<Insumo, Double> entry : items.entrySet()) {
            Insumo insumo = entry.getKey();
            Double cantidad = entry.getValue();
            modeloTabla.addRow(new Object[]{
                insumo.getNombre(),
                cantidad,
                insumo.getUnidadMedida()
            });
            total++;
        }
        lblTotalInsumos.setText("Total de Insumos: " + total);
    }

    private void editarOrden() {
        if (pnlContainer != null && pnlProductosPanel != null && pnlCarritoPanel != null) {
            pnlContainer.removeAll();
            pnlContainer.add(pnlProductosPanel, BorderLayout.CENTER);
            pnlContainer.add(pnlCarritoPanel, BorderLayout.EAST);
            lblVentana.setText("Generar Orden De Compra");
            pnlContainer.revalidate();
            pnlContainer.repaint();
        }
    }

    private void cancelarOrden() {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Cancelar orden? Se perderán los datos del carrito.",
                "Cancelar Orden", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            carrito.vaciar();
            editarOrden(); // regresa a pantalla de productos
        }
    }

    private void confirmarOrden() {
        // Por ahora solo muestra mensaje. Luego se integrará con ConfirmacionOrden
        javax.swing.JOptionPane.showMessageDialog(this,
                "Navegar a Confirmación de Orden (pendiente de integración)");
    }

    public Orden getOrdenActual() {
        return ordenActual;
    }
}

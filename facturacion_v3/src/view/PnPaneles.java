package view;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import model.Cliente;
import model.Factura;
import model.Producto;
import model.Usuario;

import java.awt.Color;

public class PnPaneles extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltro;
	private JTable tabla;

	public PnPaneles(Usuario user, String nombrePanel) {
		setBounds(0, 0, 774, 528);
		setLayout(null);
		
		JPanel pnTipo = new JPanel();
		pnTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), nombrePanel, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnTipo.setBounds(10, 8, 754, 509);
		add(pnTipo);
		pnTipo.setLayout(null);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(10, 20, 655, 20);
		pnTipo.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 734, 413);
		pnTipo.add(scrollPane);
		
		switch (nombrePanel) {
		case "Usuarios":
			tabla = new TbUsuarios(txtFiltro.getText());
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row = tabla.getSelectedRow();
					if (row != -1) {
						Usuario usu = (Usuario) tabla.getValueAt(row, 0);
						DlgUsuario dialog = new DlgUsuario(usu);
						Usuario c = dialog.mostrar();
						if (c != null) {
							((TbUsuarios) tabla).actualizarTabla(txtFiltro.getText());
						}
					}
				}
			});
			break;
		case "Productos":
			tabla = new TbProductos(txtFiltro.getText());
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row = tabla.getSelectedRow();
					if (row!=-1) {
							Producto pro = (Producto) tabla.getValueAt(row, 0);
							DlgProducto dialog = new DlgProducto(pro);
							Producto p = dialog.mostrar();
							if (p!=null) {
								((TbProductos) tabla).actualizarTabla(txtFiltro.getText());
							}
					}
				}
			});
			break;
		case "Clientes":
			tabla = new TbClientes(txtFiltro.getText());
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row = tabla.getSelectedRow();
					if (row != -1) {
						Cliente cli = (Cliente) tabla.getValueAt(row, 0);
						DlgCliente dialog = new DlgCliente(cli);
						Cliente c = dialog.mostrar();
						if (c != null) {
							((TbClientes) tabla).actualizarTabla(txtFiltro.getText());
						}
					}
				}
			});
			break;
		case "Facturas":
			tabla = new TbFacturas(txtFiltro.getText());
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row = tabla.getSelectedRow();
					if (row != -1) {
						Factura fac = (Factura) tabla.getValueAt(row, 0);
						DlgFactura dialog = new DlgFactura(fac);
						Factura f = dialog.mostrar();
						if (f != null) {
							((TbFacturas) tabla).actualizarTabla(txtFiltro.getText());
						}
					}
				}
			});
			
			break;
		default:
			break;
		}
		scrollPane.setViewportView(tabla);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(675, 20, 69, 23);
		pnTipo.add(btnFiltrar);
		
		JButton btnNuevo = new JButton(" + " + nombrePanel );
		btnNuevo.setBounds(292, 475, 170, 23);
		pnTipo.add(btnNuevo);
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (nombrePanel) {
				case "Usuarios":
				{
					DlgUsuario dialogo = new DlgUsuario(null);
					Usuario u = dialogo.mostrar();
					if (u!=null) {
						((TbUsuarios) tabla).actualizarTabla(txtFiltro.getText());
					}
				}
					break;
				case "Productos":
				{
					DlgProducto dialogo = new DlgProducto(null);
					Producto prod = dialogo.mostrar();
					if (prod!=null) {
						((TbProductos) tabla).actualizarTabla(txtFiltro.getText());
					}
				}
					break;
				case "Clientes":
				{
					DlgCliente dialogo = new DlgCliente(null);
					Cliente cli = dialogo.mostrar();
					if (cli!=null) {
						((TbClientes) tabla).actualizarTabla(txtFiltro.getText());
					}
				}
					break;
				case "Facturas":
				{
					DlgFactura dialogo = new DlgFactura(null);
					Factura fac = dialogo.mostrar();
					if (fac!=null) {
						((TbFacturas) tabla).actualizarTabla(txtFiltro.getText());
					}
				}
					break;
				default:
					break;
				}
			}
		});
		
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (nombrePanel) {
				case "Usuarios":
					((TbUsuarios) tabla).actualizarTabla(txtFiltro.getText());
					break;
				case "Productos":
					((TbProductos) tabla).actualizarTabla(txtFiltro.getText());
					break;
				case "Clientes":
					((TbClientes) tabla).actualizarTabla(txtFiltro.getText());
					break;
				case "Facturas":
					((TbFacturas) tabla).actualizarTabla(txtFiltro.getText());
					break;
				default:
					break;
				}
			}
		});
		

	}
}

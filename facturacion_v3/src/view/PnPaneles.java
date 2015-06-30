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
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

public class PnPaneles extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltro;
	private JTable tabla;

	public PnPaneles(Usuario user, String nombrePanel) {
		//setBounds(0, 0, 790, 435);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("fill:min:grow"),}));
		
		JPanel pnTipo = new JPanel();
		pnTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), nombrePanel, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(pnTipo, "1, 1, fill, fill");
		pnTipo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.PREF_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),
				FormFactory.PREF_ROWSPEC,}));
		
		JPanel panel = new JPanel();
		pnTipo.add(panel, "1, 1, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("25px"),}));
		
		txtFiltro = new JTextField();
		panel.add(txtFiltro, "1, 1, fill, fill");
		txtFiltro.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setIcon(new ImageIcon(PnPaneles.class.getResource("/iconos/sc10715.png")));
		panel.add(btnFiltrar, "3, 1, fill, fill");
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		pnTipo.add(scrollPane, "1, 3, fill, fill");
		
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
						Usuario u = dialog.mostrar();
						if (u!=null) {
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
						if (c!=null) {
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
						if (f!=null) {
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
		
		JButton btnNuevo = new JButton(" + " + nombrePanel );
		pnTipo.add(btnNuevo, "1, 4, fill, fill");
		
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
		

	}
}

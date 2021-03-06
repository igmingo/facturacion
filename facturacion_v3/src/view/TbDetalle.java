package view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Detalle;

// TABLA facturasdetalle BASE DE DATOS
//id int(10) UNSIGNED No auto_increment
//facturaId int(10) UNSIGNED No facturas -> id
//prodId int(10) UNSIGNED No productos -> id
//prodNombre varchar(30) No
//prodPrecio double No
//prodIva double No
//cantidad int(11) No

public class TbDetalle extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	private Double totalFactura;
	
	public TbDetalle(String f) {
		this.filtro = f;
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {		
				"Detalle", "Precio", "IVA", "Cantidad"
			}
		) {
			Class[] columnTypes = new Class[] {
				Detalle.class, Double.class, Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		setAutoCreateRowSorter(true);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = getSelectedRow();
				if (row!=-1) {
					Detalle fd = (Detalle) getValueAt(row, 0);
						DlgDetalle dialog = new DlgDetalle (fd);
						Detalle c = dialog.mostrar();
						if (c!=null) {
							repaint();
						}
				}
			}
		});
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Double getTotalFactura() {
		actualizaTotalFactura();
		return totalFactura;
	}

	private void actualizaTotalFactura() {
		this.totalFactura = 0.0;
		for (int row = 0; row < getRowCount(); row++) {
			Detalle fc = (Detalle) getValueAt(row, 0);
			this.totalFactura += (fc.getProdPrecio() + (100+fc.getProdIva())/100.0) * fc.getCantidad();
		}
	}

	public void setTotalFactura(Double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public void addDetalle(Detalle fd) {
		
	}
	
	public void putDetalles(ArrayList<Detalle> listaDetalles) {
		removeAll();
		//"Detalle", "Precio", "IVA", "Cantidad"
		if (listaDetalles!=null) {
			ArrayList<Vector<Object>> tableData = null;
			tableData = new ArrayList<>();
			for (Detalle fd : listaDetalles) {
				Vector<Object> filaData = new Vector<>();
				filaData.add(fd);
				filaData.add(fd.getProdPrecio());
				filaData.add(fd.getProdIva());
				filaData.add(fd.getCantidad());
				tableData.add(filaData);
			}
		}
	}

	public ArrayList<Detalle> getListaDetalles() {
		ArrayList<Detalle> listaDetalles = new ArrayList<>();
		for (int row = 0; row < getRowCount(); row++) {
			Detalle fc = (Detalle) getValueAt(row, 0);
			listaDetalles.add(fc);
		}
		return listaDetalles;
	}
}

package view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Producto;
import dao.BDDProductos;


public class TbProductos extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	
	public TbProductos(String filtro) {
		this.filtro = filtro;
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Producto", "Precio", "Stock"
			}
		) {
			Class[] columnTypes = new Class[] {
				Producto.class, Double.class, Integer.class
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
		actualizarTabla(this.filtro);
	}
	
	public void actualizarTabla(String filtro) {
		this.setFiltro(filtro);
		ArrayList<Vector<Object>> tabla = new BDDProductos().recuperaTablaProductos(filtro);
		if (tabla!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			dtm.setRowCount(0);
			for (Vector<Object> fila : tabla) {
				dtm.addRow(fila);
			}
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}

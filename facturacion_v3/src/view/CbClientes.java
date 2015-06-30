package view;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import util.Utilidades;
import model.Cliente;
import dao.BDDClientes;

public class CbClientes extends JComboBox<Cliente> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cbFiltro;
	
	public CbClientes() {
		super();
		cbFiltro = null;
		setEditable(true);
		setSelectedIndex(-1);
		addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				setSelectedIndex(-1);
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if (cbFiltro!=null && cbFiltro.length()>0){
					setFiltro(((JTextField) getEditor().getEditorComponent()).getText());
					recargarComboFiltrado();
				} else {
					setFiltro(((JTextField) getEditor().getEditorComponent()).getText());
					recargarComboFiltrado();
				}
			}
		});
		
	}
	
	public String getFiltro() {
		return cbFiltro;
	}

	public void setFiltro(String f) {
		this.cbFiltro = f;
	}

	protected void recargarComboFiltrado() {
		removeAllItems();
		ArrayList<String> filtros = new ArrayList<>();
		filtros.add("clientes.nombre LIKE '%" + cbFiltro + "%'");
		filtros.add("clientes.apellidos LIKE '%" + cbFiltro + "%'");
		filtros.add("clientes.nif LIKE '%" + cbFiltro + "%'");
		String filtroString = Utilidades.creaFiltroOR(filtros);
		ArrayList<Cliente> lista = new BDDClientes().recuperaPorFiltro(filtroString);
		if (lista!=null) {
			for (Cliente c : lista) {
				addItem(c);
			}
			//setSelectedIndex(lista.size()-1);
		}
	}

//	public CbClientes(int idCliente) {
//		super();
//		setEditable(true);
//		try {
//			recargarCombo(idCliente);
//		} catch (Exception e) {
//		}
//	}
	
	public Cliente obtenerClienteSeleccionado() {
		return getItemAt(getSelectedIndex());
	}
	
	public int obtenerClienteIdSeleccionado() {
		//Devuelve el ID del Genero selecionado en el Combo
		return getItemAt(getSelectedIndex()).getId();
	}
	
	public void ponerClienteIdSeleccionado(Integer idCliente) {
		//Devuelve el ID del Genero selecionado en el Combo
		recargarCombo(idCliente);
	}
	
	private void recargarCombo(Integer idCliente) {
		if (idCliente!=null){
			int idSel = idCliente;
			int pos = 0;
			removeAllItems();
			ArrayList<Cliente> lista = new BDDClientes().recuperaPorFiltro("clientes.id =" + idCliente);
			if (lista!=null) {
				for (Cliente c : lista) {
					addItem(c);
					if (c.getId() == idSel) {
						setSelectedIndex(pos);
					}
					pos++;
				}
			}
		}
	}

	/**Carga todos los Generos del Repositorio ClientesBDD, y deja seleccionado el que tiene el idCliente
	 * @param idCliente
	 */
	public void recargarCombo() {
		removeAllItems();
		ArrayList<Cliente> lista = new BDDClientes().recuperaPorFiltro(null);
		if (lista!=null) {
			for (Cliente c : lista) {
				addItem(c);
			}
			setSelectedIndex(-1);
		}
	}
	
//	public void recargarCombo() {
//		int clienteId = obtenerClienteIdSeleccionado();
//		recargarCombo(-1);
//		ponerClienteIdSeleccionado(clienteId);
//	}
//	
//	public void cargarCombo() {
//			removeAllItems();
//			ArrayList<Cliente> lista = new BDDClientes().recuperaTodos();
//			for (Cliente c : lista) {
//				addItem(c);
//			}
//	}
	
	public String obtenerNombreCliente() {
		return ((JTextField) getEditor().getEditorComponent()).getText();
	}

}

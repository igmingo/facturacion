package view;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class DlgRptFacPorId extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbFacturas table;
	private Map<String, Object> retorno = null;
	private JTextField txtFiltro;
	
	public DlgRptFacPorId() {
		setBounds(new Rectangle(0, 0, 400, 300));
		setModal(true);
		setTitle("Informe de Facturas");
		
		JPanel pnBotones = new JPanel();
		getContentPane().add(pnBotones, BorderLayout.SOUTH);
		pnBotones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnReturnParams = new JButton("Crear factura para imprimir");
		pnBotones.add(btnReturnParams);
		btnReturnParams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow()>=0 ) {
					retorno = new HashMap<String, Object>();
					retorno.put("facturaid", table.obtenerFacturaId());
					setVisible(false);
				}
			}
		});
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//retorno = null;
				setVisible(false);
			}
		});
		pnBotones.add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Factura", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new TbFacturas(null);
		scrollPane.setViewportView(table);
		table.actualizarTabla(null);
		
		txtFiltro = new JTextField();
		txtFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.actualizarTabla(txtFiltro.getText());
			}
		});
		panel.add(txtFiltro, BorderLayout.NORTH);
		txtFiltro.setColumns(10);

	}
	
	public Map<String, Object> mostrar() {
		setVisible(true);
		dispose();
		return retorno;
	}

}

package view;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import com.jgoodies.forms.layout.Sizes;

import dao.BDDClientes;
import dao.BDDFacturas;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.Utilidades;
import model.Cliente;
import model.Detalle;
import model.Factura;

import java.awt.Color;

// TABLA facturas BASE DE DATOS
//id int(10) UNSIGNED No auto_increment
//clienteId int(10) UNSIGNED No clientes -> id
//numero int(11) No
//fecha date No
//porcDescuento double No
//porcRecargoEquivalencia	double No
//impTotal double No
//impRecargo double No
//impIva double No
//dirCorreo mediumtext No
//dirFactura mediumtext No
//dirEnvio mediumtext No
//cobrada tinyint(1) No

public class DlgFactura extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Factura factura;
	private JTextField txtNumero;
	private JDateChooser dateFecha;
	
	private CbClientes cbCliente;
	private JSpinner numPorcDescuento;
	private JSpinner numPorcRecargoequivalencia;
	
	private PnDetalles pnDetalles;
	
	private JSpinner numImpBase;
	private JSpinner numImprecargo;
	private JSpinner numImpiva;
	private JSpinner numImporteTotal;
	
	private JTextArea txtDirCorreo;
	private JTextArea txtDirFactura;
	private JTextArea txtDirEnvio;
	
	private JCheckBox chkbxCobrada;
	private JTextField txtNifCliente;

	public DlgFactura(Factura fac) {
		//De la factura que paso, solo necesito el ID (en principio)
		//Para crear una factura se fac = null;
		this.factura = fac;
		
		setBounds(new Rectangle(0, 0, 800, 600));
		setModal(true);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("31px"),}));
		
		JPanel pnButtons = new JPanel();
		getContentPane().add(pnButtons, "1, 4, fill, fill");
		pnButtons.setLayout(new GridLayout(0, 3, 30, 0));
		
		JButton btnGrabar = new JButton("Guardar");
		pnButtons.add(btnGrabar);
		JButton btnBaja = new JButton("Dar de Baja");
		pnButtons.add(btnBaja);
		JButton btnCancelar = new JButton("Cancelar");
		pnButtons.add(btnCancelar);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "1, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblFactura = new JLabel("Factura");
		panel.add(lblFactura, "2, 1, right, default");
		
		JPanel pnNumFactura = new JPanel();
		panel.add(pnNumFactura, "4, 1, fill, fill");
		pnNumFactura.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow(2)"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("20px"),}));
		
		JLabel lblNfactura = new JLabel("N\u00FAmero");
		pnNumFactura.add(lblNfactura, "1, 1, left, center");
		
		txtNumero = new JTextField();
		txtNumero.setEnabled(false);
		pnNumFactura.add(txtNumero, "3, 1, fill, fill");
		txtNumero.setText("numFactura");
		txtNumero.setColumns(10);
		
		JLabel lblFechaDeFactura = new JLabel("Fecha");
		pnNumFactura.add(lblFechaDeFactura, "5, 1, left, center");
		
		dateFecha = new JDateChooser();
		pnNumFactura.add(dateFecha, "7, 1, fill, fill");
		
		chkbxCobrada = new JCheckBox();
		pnNumFactura.add(chkbxCobrada, "9, 1");
		chkbxCobrada.setText("\u00BFcobrada?");
		
		JLabel lblCliente = new JLabel("Cliente");
		panel.add(lblCliente, "2, 3, right, default");
		
		JPanel pnCliente = new JPanel();
		panel.add(pnCliente, "4, 3, fill, fill");
		pnCliente.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnClienteTodo = new JPanel();
		pnCliente.add(pnClienteTodo);
		pnClienteTodo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("fill:pref:grow"),
				new RowSpec(RowSpec.FILL, Sizes.bounded(Sizes.PREFERRED, Sizes.constant("50dlu", false), Sizes.constant("50dlu", false)), 1),}));
		
		JPanel pnDatosCliente = new JPanel();
		pnClienteTodo.add(pnDatosCliente, "1, 1, fill, fill");
		pnDatosCliente.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("min:grow(2)"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("60dlu"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("fill:20px"),}));
		
		cbCliente = new CbClientes();
		pnDatosCliente.add(cbCliente, "1, 1, fill, fill");
		
		JLabel lblNif = new JLabel("NIF");
		pnDatosCliente.add(lblNif, "3, 1, left, center");
		
		txtNifCliente = new JTextField();
		txtNifCliente.setEditable(false);
		txtNifCliente.setText("NIF");
		pnDatosCliente.add(txtNifCliente, "5, 1, fill, fill");
		txtNifCliente.setColumns(5);
		
		JLabel lblDescuento = new JLabel("% Descuento");
		pnDatosCliente.add(lblDescuento, "7, 1, left, center");
		lblDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		
		numPorcDescuento = new JSpinner();
		pnDatosCliente.add(numPorcDescuento, "9, 1, fill, fill");
		numPorcDescuento.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
		JLabel lblRecargoEquivalencia = new JLabel("% Rec Eq.");
		pnDatosCliente.add(lblRecargoEquivalencia, "11, 1, left, center");
		lblRecargoEquivalencia.setHorizontalAlignment(SwingConstants.RIGHT);
		
		numPorcRecargoequivalencia = new JSpinner();
		pnDatosCliente.add(numPorcRecargoequivalencia, "13, 1, fill, fill");
		numPorcRecargoequivalencia.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
		JPanel pnDirecciones = new JPanel();
		pnClienteTodo.add(pnDirecciones, "1, 2, fill, fill");
		pnDirecciones.setLayout(new GridLayout(0, 3, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Direcci\u00F3n Facturaci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		pnDirecciones.add(scrollPane);
		
		txtDirFactura = new JTextArea();
		txtDirFactura.setFont(UIManager.getFont("FormattedTextField.font"));
		scrollPane.setViewportView(txtDirFactura);
		txtDirFactura.setText("dirFactura");
		txtDirFactura.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Direcci\u00F3n Env\u00EDo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		pnDirecciones.add(scrollPane_1);
		
		txtDirEnvio = new JTextArea();
		txtDirEnvio.setFont(UIManager.getFont("FormattedTextField.font"));
		scrollPane_1.setViewportView(txtDirEnvio);
		txtDirEnvio.setText("dirEnvio");
		txtDirEnvio.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Direcci\u00F3n Correo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		pnDirecciones.add(scrollPane_2);
		
		txtDirCorreo = new JTextArea();
		txtDirCorreo.setFont(UIManager.getFont("FormattedTextField.font"));
		scrollPane_2.setViewportView(txtDirCorreo);
		txtDirCorreo.setText("dirCorreo");
		txtDirCorreo.setColumns(10);
		
		JLabel lblProductos = new JLabel("Productos");
		panel.add(lblProductos, "2, 5, right, default");
		
		JLabel lblImporte = new JLabel("Importe");
		panel.add(lblImporte, "2, 7, right, default");
		
		JPanel pnImportes = new JPanel();
		panel.add(pnImportes, "4, 7, fill, fill");
		pnImportes.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default):grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default):grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default):grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default):grow(2)"),},
			new RowSpec[] {
				RowSpec.decode("25px"),}));
		
		JLabel lblImporteTotal = new JLabel("Con Descuento");
		lblImporteTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		pnImportes.add(lblImporteTotal, "1, 1, fill, fill");
		
		numImpBase = new JSpinner();
		numImpBase.setEnabled(false);
		numImpBase.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pnImportes.add(numImpBase, "3, 1, fill, fill");
		
		JLabel lblImporteIva = new JLabel("IVA");
		lblImporteIva.setHorizontalAlignment(SwingConstants.RIGHT);
		pnImportes.add(lblImporteIva, "4, 1, 2, 1, fill, fill");
		
		numImpiva = new JSpinner();
		numImpiva.setEnabled(false);
		numImpiva.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pnImportes.add(numImpiva, "7, 1, fill, fill");
		
		JLabel lblImporteRecago = new JLabel("Rec Eq.");
		lblImporteRecago.setHorizontalAlignment(SwingConstants.RIGHT);
		pnImportes.add(lblImporteRecago, "9, 1, fill, fill");
		
		numImprecargo = new JSpinner();
		numImprecargo.setEnabled(false);
		numImprecargo.setMinimumSize(new Dimension(50, 20));
		numImprecargo.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pnImportes.add(numImprecargo, "11, 1, fill, fill");
		
		JLabel lblImporteTotal_1 = new JLabel("TOTAL");
		lblImporteTotal_1.setHorizontalAlignment(SwingConstants.RIGHT);
		pnImportes.add(lblImporteTotal_1, "13, 1, fill, fill");
		
		numImporteTotal = new JSpinner();
		numImporteTotal.setEnabled(false);
		numImporteTotal.setFont(UIManager.getFont("ToolTip.font"));
		pnImportes.add(numImporteTotal, "15, 1, fill, fill");
		
		
		/*		
		 * 
		 * LISTENERS
		 * 
		 * */
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factura = null;
				setVisible(false);
			}
		});
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				setVisible(false);
			}
		});
		
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (guardar()) {
					setVisible(false);
				}
			}
		});
		
		cbCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente cli = cbCliente.obtenerClienteSeleccionado();
				if (cli!=null) {
					txtNifCliente.setText(cli.getNif());
					numPorcDescuento.setValue(cli.getPorcDescuento()*100);
					numPorcRecargoequivalencia.setValue(cli.getPorcRecargoEquivalencia()*100);
					txtDirCorreo.setText(cli.getDirCorreo());
					txtDirEnvio.setText(cli.getDirEnvio());
					txtDirFactura.setText(cli.getDirFactura());
				}
			}
		});
		
		numPorcDescuento.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcular();
			}
		});
		
		try {
			pnDetalles.numImporteProductos.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					calcular();
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/*
		 * INICIALIZACION
		 * ES UNA FACTURA EXISTENTE O UNA FACTURA NUEVA
		 * */
		if (this.factura!=null) {
			//MODIFICAR FACTURA
			setTitle("Modificar Factura");
			//RECUPERAMOS LA FACTURA
			this.factura = new BDDFacturas().recuperaFacturaCompletaPorId(factura.getId());
			//RECUPERA UN CLIENTE
			if (this.factura.getCliente()==null) {
				try {
					this.factura.setCliente(new BDDClientes().recuperaPorId(this.factura.getClienteId()));
				} catch (Exception e1) {
				}
			}
		} else {
			//NUEVA FACTURA
			setTitle("Nueva Factura");
			factura = new Factura();
			factura.setId(0);
		}
		pnDetalles = new PnDetalles(factura.getId());
		panel.add(pnDetalles, "4, 5, fill, fill");
		setForm();
		calcular();
	}
	
	private void calcular() {
		double porcInversoDescuento = 1.0 - ((double) numPorcDescuento.getValue())/100.0;
		numImpBase.setValue(porcInversoDescuento * pnDetalles.obtenerImporteProductos());
		numImpiva.setValue(porcInversoDescuento * pnDetalles.obtenerImporteIVA());
		numImprecargo.setValue((double) numImpBase.getValue() * ((double) numPorcRecargoequivalencia.getValue())/100.0 );
		double impTotal = (double) numImpBase.getValue() + (double) numImpiva.getValue() + (double) numImprecargo.getValue() ;
		numImporteTotal.setValue(impTotal);
	}
	
	private void eliminar() {
		//Integer id = Utilidades.validarEntero(txtId.getText());
		int id = factura.getId();
		if (id>0) {
			int pregunta = JOptionPane.showConfirmDialog(null, "�Desea eliminar la factura?\n", "Eliminar Factura", JOptionPane.OK_CANCEL_OPTION);
			if (pregunta==JOptionPane.OK_OPTION) {
				boolean eliminado = new BDDFacturas().darDeBaja(id);
				if (eliminado) {
					factura = null;
					mostrarMensaje("Factura Eliminada.");
				} else {
					mostrarMensaje("No se ha podido eliminar.");
				}
			}
		}
	}
	
	private boolean guardar() {
		calcular();
		boolean ok = false;
		factura = getForm();
		if (factura!=null) {
			//El formulario es VALIDO
			int respuesta = new BDDFacturas().grabarFacturaCompleta(factura);
			System.out.println("Respuesta " + respuesta);
			if (respuesta>=0) {
				factura.setId(respuesta);
				setForm();
				mostrarMensaje("Factura guardada correctamente.");
				ok = true;
			} else {
				mostrarMensaje("Error al guardar.");
			}
		} else {
			mostrarMensaje("El formulario no es correcto.");
		}
		return ok;
	}

	/**
	 * Rellena los datos de la Factura en el Formulario
	 * @param factura es la instancia del la Factura con el que vamos a rellenar el forumulario. Si es null, se rellena un formulario con id = 0;
	 */
	private void setForm() {
		if (factura!=null && factura.getId()>0) {
			setTitle("Modificar Factura");
			//txtId.setText(""+ factura.getId());
			txtNumero.setText(""+ factura.getNumero());
			dateFecha.setDate(factura.getFecha());
			cbCliente.ponerClienteIdSeleccionado(factura.getClienteId());
			txtNifCliente.setText(factura.getNifCliente());
			pnDetalles.ponerListaDetalles(factura.getDetalles());
			numPorcDescuento.setValue(factura.getPorcDescuento()*100);
			numPorcRecargoequivalencia.setValue(factura.getPorcRecargoEquivalencia()*100);
			numImpBase.setValue(factura.getImpTotal());
			numImprecargo.setValue(factura.getImpRecargo());
			numImpiva.setValue(factura.getImpIva());
			txtDirCorreo.setText(factura.getDirCorreo());
			txtDirFactura.setText(factura.getDirFactura());
			txtDirEnvio.setText(factura.getDirEnvio());
			chkbxCobrada.setSelected(factura.isCobrada());
		} else {
			setTitle("Nueva Factura");
			txtNumero.setText("0");
			dateFecha.setDate(new Date());
			cbCliente.setSelectedIndex(-1);
			txtNifCliente.setText("");
			pnDetalles.ponerListaDetalles(null);
			numPorcDescuento.setValue(0.0);
			numPorcRecargoequivalencia.setValue(0.0);
			numImpBase.setValue(0);
			numImprecargo.setValue(0);
			numImpiva.setValue(0);
			txtDirCorreo.setText("");
			txtDirFactura.setText("");
			txtDirEnvio.setText("");
			chkbxCobrada.setSelected(false);
		}
	}
	/**
	 * Recoge el formulario y crea una instancia de Factura
	 * @return Retorna una instacia de Factura. Null si el formulario esta incorrecto.
	 */
	private Factura getForm() {
		Factura fac = null;
		int id = factura.getId();
		Cliente cliente = cbCliente.obtenerClienteSeleccionado();
		//Integer id = Utilidades.validarEntero(txtId.getText().trim());
		//String nombreCliente = cbCliente.obtenerNombreCliente();
		//String nifCliente = txtNifCliente.getText().trim();
		//String nifCliente = cbCliente.obtenerClienteSeleccionado().getNif();
		Integer numero = Utilidades.validarEntero(txtNumero.getText().trim());
		Date fecha = dateFecha.getDate();
		Double porcDescuento = (Double) numPorcDescuento.getValue()/100.0;
		Double porcRecargoEquivalencia = (Double) numPorcRecargoequivalencia.getValue()/100.0;
		Double impTotal = Utilidades.validarDouble("" + numImpBase.getValue());
		Double impRecargo = Utilidades.validarDouble(""	+ numImpBase.getValue());
		Double impIva = Utilidades.validarDouble("" + numImpBase.getValue());
		String dirCorreo = txtDirCorreo.getText().trim();
		String dirFactura = txtDirFactura.getText().trim();
		String dirEnvio = txtDirEnvio.getText().trim();
		Boolean cobrada = chkbxCobrada.isSelected();
		ArrayList<Detalle> detalles = pnDetalles.recuperarListaDetalles();
		//ArrayList<FacturaDetalle> detalles = tbFacturaDetalles.getListaDetalles();
		try {
			fac = new Factura(id, cliente, numero, fecha, porcDescuento,
					porcRecargoEquivalencia, impTotal, impRecargo, impIva,
					dirCorreo, dirFactura, dirEnvio, cobrada, detalles);
		} catch (Exception e) {
			mostrarMensaje("Error de formulario");
		}
		return fac;
	}
	
	private void mostrarMensaje(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	/**
	 * Hace visible el di�logo Modal para editar un Cliente. Si se hace invisible el Dialogo, se cierra y elimina de la memoria.
	 * @return Retorna la instacia del Cliente que se ha guardado
	 */
	public Factura mostrar() {
		setVisible(true);
		dispose();
		return factura;
	}
}

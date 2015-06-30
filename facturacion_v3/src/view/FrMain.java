package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;

import controller.RptCreador;
import model.Factura;
import model.Usuario;

import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.SystemColor;

public class FrMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JPanel pnPrincipal;
	
	//LOGIN
	private PnLogin pnLogin;
	private Usuario user = null;
	
	public FrMain() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");		
			
//			UIManager.getIcon("FileView.directoryIcon");
//			UIManager.getIcon("FileView.fileIcon");
//			UIManager.getIcon("FileView.computerIcon");
//			UIManager.getIcon("FileView.hardDriveIcon");
//			UIManager.getIcon("FileView.floppyDriveIcon");
//
//			UIManager.getIcon("FileChooser.newFolderIcon");
//			UIManager.getIcon("FileChooser.upFolderIcon");
//			UIManager.getIcon("FileChooser.homeFolderIcon");
//			UIManager.getIcon("FileChooser.detailsViewIcon");
//			UIManager.getIcon("FileChooser.listViewIcon");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		user = new Usuario();
		user.setLoged(true);
		
		setResizable(false);
		setBounds(0, 0, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//getContentPane().setLayout(null);
		
		/* 
		 * PANEL DE LOGIN
		 */
		pnLogin = new PnLogin(user);
		pnLogin.setBounds(0, 0, getWidth(), getHeight());
		getContentPane().add(pnLogin);
		pnLogin.setVisible(!user.isLoged());
		
		// Se construye el JDesktopPane
		JDesktopPane escritorio = new JDesktopPane();
		escritorio.setBackground(SystemColor.controlShadow);
		getContentPane().add(escritorio);
		
		// Se construye el JToolBar
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 800, 25);
		//getContentPane().add(toolBar);
		
		JButton btnNuevaFactura = new JButton("Nueva Factura");
		btnNuevaFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaFactura();
			}
		});
		btnNuevaFactura.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/sc036.png")));
		toolBar.add(btnNuevaFactura);
		JButton btnNewButton = new JButton("Imprimir Factura");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imprimirFactura();
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/sc05504.png")));
		toolBar.add(btnNewButton);
		escritorio.add(toolBar);
		toolBar.setVisible(user.isLoged());
		
		// Se construye fondo del escritorio
		ImageIcon fondo = new ImageIcon("./images/fondo.jpg");
		fondo = new ImageIcon(new ImageIcon("./images/fondo.jpg").getImage().getScaledInstance(811,611, Image.SCALE_SMOOTH));
		JLabel lblFondo = new JLabel();
		lblFondo.setBounds(0, 0, 800, 600);
		lblFondo.setIcon(fondo);
		escritorio.add(lblFondo);
		
		// Se construye el JPanel Principal
		JPanel pnPrincipal = new JPanel();
		pnPrincipal.setOpaque(false);
		pnPrincipal.setLayout(new BorderLayout());
		
		// Se construye el JInternalFrame con el Panel Principal
		JInternalFrame jifPrincipal = new JInternalFrame();
		jifPrincipal.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jifPrincipal.getContentPane().add(pnPrincipal, BorderLayout.CENTER);
		jifPrincipal.pack();
		jifPrincipal.setResizable(true);
		//jifPrincipal.setMaximizable(true);
		jifPrincipal.setClosable(true);
		jifPrincipal.setBounds(0, 25, 794, 524);
		jifPrincipal.setVisible(false);
		escritorio.add(jifPrincipal);
		
		// Se construyen los MENUS
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnFacturas = new JMenu("Facturas");
		menuBar.add(mnFacturas);
		JMenuItem mntmNuevaFactura = new JMenuItem("Nueva Factura...");
		mntmNuevaFactura.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/sc036.png")));
		mnFacturas.add(mntmNuevaFactura);
		
		JSeparator separator_4 = new JSeparator();
		mnFacturas.add(separator_4);
		JMenuItem mntmListadoDeFacturas = new JMenuItem("Editor de Facturas");
		mntmListadoDeFacturas.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/sc038.png")));
		mnFacturas.add(mntmListadoDeFacturas);
		
		JSeparator separator_5 = new JSeparator();
		mnFacturas.add(separator_5);
		
		JMenuItem mntmInfFacturas = new JMenuItem("Listado de Facturas");
		mnFacturas.add(mntmInfFacturas);
		mntmInfFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInforme("Facturas");
			}
		});
		
		JMenuItem mntmInFacturasDeCliente = new JMenuItem("Listado de Facturas de Cliente");
		mntmInFacturasDeCliente.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/sx12466.png")));
		mnFacturas.add(mntmInFacturasDeCliente);
		mntmInFacturasDeCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInforme("FacturasDeCliente");
			}
		});
		
		JSeparator separator_7 = new JSeparator();
		mnFacturas.add(separator_7);
		
		JMenuItem mntmInFacturaPorId = new JMenuItem("Imprimir Factura...");
		mntmInFacturaPorId.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/sc05504.png")));
		mnFacturas.add(mntmInFacturaPorId);
		
		JSeparator separator_8 = new JSeparator();
		mnFacturas.add(separator_8);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				//setVisible(false);
			}
		});
		mnFacturas.add(mntmSalir);
		mntmInFacturaPorId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imprimirFactura();
			}
		});
		
		mntmNuevaFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaFactura();
			}
		});
		
		mntmListadoDeFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel(pnPrincipal, "Facturas",jifPrincipal);
			}
		});
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		JMenuItem mntmNuevoProducto = new JMenuItem("Nuevo Producto...");
		mntmNuevoProducto.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/library_16.png")));
		mnProductos.add(mntmNuevoProducto);
		
		JSeparator separator_1 = new JSeparator();
		mnProductos.add(separator_1);
		JMenuItem mntmListadoDeProductos = new JMenuItem("Editor de Productos");
		mnProductos.add(mntmListadoDeProductos);
		
		JSeparator separator = new JSeparator();
		mnProductos.add(separator);
		
		JMenuItem mntmInfProductos = new JMenuItem("Listado de todos los Productos");
		mntmInfProductos.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/sx12454.png")));
		mnProductos.add(mntmInfProductos);
		
		JMenuItem mntmInfProductosPorPrecio = new JMenuItem("Listado de Productos por Precio");
		mnProductos.add(mntmInfProductosPorPrecio);
		mntmInfProductosPorPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInforme("ProductosDesde");
			}
		});
		mntmInfProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInforme("Productos");
			}
		});
		
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente...");
		mntmNuevoCliente.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/user.png")));
		mnNewMenu.add(mntmNuevoCliente);
		
		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);
		JMenuItem mntmListadoDeClientes = new JMenuItem("Editor de Clientes");
		mnNewMenu.add(mntmListadoDeClientes);
		
		JSeparator separator_3 = new JSeparator();
		mnNewMenu.add(separator_3);
		
		JMenuItem mntmInformeDeClientes = new JMenuItem("Listado de Clientes");
		mntmInformeDeClientes.setIcon(new ImageIcon(FrMain.class.getResource("/iconos/sx12454.png")));
		mnNewMenu.add(mntmInformeDeClientes);
		mntmInformeDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInforme("Clientes");
			}
		});
		
		JMenu mnConsultas = new JMenu("Informes");
		menuBar.add(mnConsultas);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		JMenuItem mntmAdminstracinDeUsuarios = new JMenuItem("Administraci\u00F3n de Usuarios");
		mnUsuarios.add(mntmAdminstracinDeUsuarios);
		menuBar.setVisible(user.isLoged());
		
		// LISTENERS
		
		mntmListadoDeProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel(pnPrincipal, "Productos", jifPrincipal);
			}
		});
		
		mntmNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgProducto dialogo = new DlgProducto(null);
				dialogo.mostrar();
			}
		});
		
		mntmNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgCliente dialogo = new DlgCliente(null);
				dialogo.mostrar();
			}
		});
		
		mntmListadoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel(pnPrincipal, "Clientes", jifPrincipal);
			}
		});
		
		mntmAdminstracinDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel(pnPrincipal, "Usuarios", jifPrincipal);
			}
		});
		
//		getContentPane().addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
//			@Override
//			public void ancestorResized(HierarchyEvent e) {
//				Dimension dim = getContentPane().getSize();
//				pnLogin.setSize(dim);
////				if (dim.getWidth()>100 && dim.getWidth()>100) {
////					lblFondo.setIcon(new ImageIcon(new ImageIcon(
////							"./images/fondo.jpg").getImage()
////							.getScaledInstance(
////									(int) dim.getWidth(),
////									(int) dim.getHeight(),
////									Image.SCALE_SMOOTH)));
////				}
//				pnLogin.repaint();
//				//System.out.println(getContentPane().getSize());
//			}
//		});
		
		pnLogin.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user = pnLogin.login();
				if (user==null) {
					System.out.println("Error de Login");
					mostrarMensaje("Error de Identificación");
				} else {
					System.out.println("Login correcto");
					setTitle(user.getName());
					pnLogin.setVisible(!user.isLoged());
					menuBar.setVisible(user.isLoged());
					toolBar.setVisible(user.isLoged());
					//jifPrincipal.setVisible(user.isLoged());
				}
			}
		});
		
	}

	protected void imprimirFactura() {
		abrirInforme("FacturaPorId");
	}

	protected void nuevaFactura() {
		DlgFactura dialogo = new DlgFactura(null);
		Factura fac = dialogo.mostrar();
	}

	protected Boolean abrirInforme(String string) {
		return new RptCreador(string).mostrar();
	}

	protected void mostrarPanel(JPanel pn, String pnName, JInternalFrame jif) {
		pn.removeAll();
		pn.add(new PnPaneles(user, pnName));
		pn.repaint();
		pn.validate();
		jif.setVisible(user.isLoged());
	}
	
	private void mostrarMensaje(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	public void mostrar() {
		setVisible(true);
	}
}

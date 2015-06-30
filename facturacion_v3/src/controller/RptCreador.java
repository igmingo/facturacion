package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import view.DlgRptFacPorCliente;
import view.DlgRptFacPorId;
import view.DlgRptProd;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class RptCreador {

	JasperViewer visualizador = null;

	public RptCreador(String string) {
		Map<String, Object> parametros = null;
		JasperReport report = null;
		switch (string) {
		case "FacturaPorId":
			parametros = new DlgRptFacPorId().mostrar();
			if (parametros!=null) {
				report = cargarInforme("rpt_facturacompleta.jasper");
			}
			break;
		case "FacturasDeCliente":
			parametros = new DlgRptFacPorCliente().mostrar();
			if (parametros!=null) {
				report = cargarInforme("rpt_facturasporcliente.jasper");
			}
			break;
		case "ProductosDesde":
			parametros = new DlgRptProd().mostrar();
			if (parametros!=null) {
				report = cargarInforme("rpt_productosprecioorden.jasper");
			}
			//"desdeprecio", "hastaprecio", "orden"
			break;
		case "Productos":
			report = cargarInforme("rpt_productos.jasper");
			break;
		case "Clientes":
			report = cargarInforme("rpt_clientes.jasper");
			break;
		case "Facturas":
			report = cargarInforme("rpt_facturas.jasper");
			break;
		default:
			break;
		}
		
		if (report!=null) {
			try {
				//JasperViewer reportVisor;
				Connection cnx = new MysqlConexion().getConection();
				System.out.println("Parametros " + parametros);
				JasperPrint reportRelleno;
				reportRelleno = JasperFillManager.fillReport(report, parametros, cnx);
				try {
					cnx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				visualizador = new JasperViewer(reportRelleno, false );
				//Para que solo abra un viewReport
				//reportVisor.viewReport(reportRelleno, false);
				//visualizador.setVisible(true);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}

	private JasperReport cargarInforme(String nameReport) {
		JasperReport informe = null;
		try {
			informe = (JasperReport) JRLoader.loadObjectFromFile("./informes/" + nameReport);
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return informe;
	}

	public Boolean mostrar() {
		if (visualizador!=null){
			try {
				visualizador.setVisible(true);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return null;
		}
	}
	
	public JasperViewer getVisualizador() {
		return visualizador;
	}

	public void setVisualizador(JasperViewer visualizador) {
		this.visualizador = visualizador;
	}
	
	
	
}

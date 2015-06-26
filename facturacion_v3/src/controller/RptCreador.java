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

	JasperViewer visualizador;

	public RptCreador(String string) {
		String nameReport = "";
		Map<String, Object> parametros = null;
		switch (string) {
		case "FacturaPorId":
			nameReport = "rpt_facturacompleta.jasper";	
			parametros = new DlgRptFacPorId().mostrar();
			break;
		case "FacturasDeCliente":
			nameReport = "rpt_facturasporcliente.jasper";	
			parametros = new DlgRptFacPorCliente().mostrar();
			break;
		case "ProductosDesde":
			nameReport = "rpt_productosprecioorden.jasper";
			parametros = new DlgRptProd().mostrar();
			//"desdeprecio", "hastaprecio", "orden"
			break;
		case "Productos":
			nameReport = "rpt_productos.jasper";
			break;
		case "Clientes":
			nameReport = "rpt_clientes.jasper";
			break;
		case "Facturas":
			nameReport = "rpt_facturas.jasper";
			break;
		default:
			break;
		}
		
		try {
			JasperReport report;
			JasperPrint reportRelleno;
			//JasperViewer reportVisor;
			report = (JasperReport) JRLoader.loadObjectFromFile("./informes/" + nameReport);
			Connection cnx = new MysqlConexion().getConection();
			System.out.println("Parametros " + parametros);
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

	public boolean mostrar() {
		try {
			visualizador.setVisible(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public JasperViewer getVisualizador() {
		return visualizador;
	}

	public void setVisualizador(JasperViewer visualizador) {
		this.visualizador = visualizador;
	}
	
	
	
}

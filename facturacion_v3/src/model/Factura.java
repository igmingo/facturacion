package model;

import java.util.ArrayList;
import java.util.Date;

public class Factura {

	
//	id int(10) UNSIGNED No auto_increment
//	clienteId int(10) UNSIGNED No clientes -> id
//	nombreCliente
//	nifCliente
//	numero int(11) No
//	fecha date No
//	porcDescuento double No
//	porcRecargoEq
//	uivalencia
//	double No
//	impTotal double No
//	impRecargo double No
//	impIva double No
//	dirCorreo mediumtext No
//	dirFactura mediumtext No
//	dirEnvio mediumtext No
//	cobrada tinyint(1) No
//	estado int(11) No
	
	
     private int id;
     private int clienteId;
     private Cliente cliente;
     private String nombreCliente;
     private String nifCliente;
     private int numero;
     private Date fecha;
     private double porcDescuento;
     private double porcRecargoEquivalencia;
     private double impTotal;
     private double impRecargo;
     private double impIva;
     private String dirCorreo;
     private String dirFactura;
     private String dirEnvio;
     private boolean cobrada;
     private int estado;
     private ArrayList<Detalle> detalles;
     public static final int BAJA = -1;
     public static final int ALTA = 0;

    public Factura() {
    }
    
    public Factura(int id, Cliente cliente, int numero,
			Date fecha, double porcDescuento, double porcRecargoEquivalencia,
			double impTotal, double impRecargo, double impIva,
			String dirCorreo, String dirFactura, String dirEnvio,
			boolean cobrada, ArrayList<Detalle> detalles) {
		this.id = id;
		this.cliente = cliente;
		if (cliente!=null) {
			this.clienteId = cliente.getId();
			this.nombreCliente = cliente.toString();
			this.nifCliente = cliente.getNif();
		}
		this.numero = numero;
		this.fecha = fecha;
		this.porcDescuento = porcDescuento;
		this.porcRecargoEquivalencia = porcRecargoEquivalencia;
		this.impTotal = impTotal;
		this.impRecargo = impRecargo;
		this.impIva = impIva;
		this.dirCorreo = dirCorreo;
		this.dirFactura = dirFactura;
		this.dirEnvio = dirEnvio;
		this.cobrada = cobrada;
		this.estado = 0;
		this.detalles = detalles;
	}
	
    public Factura(int id, int clienteId, String nombreCliente, String nifCliente, int numero,
			Date fecha, double porcDescuento, double porcRecargoEquivalencia,
			double impTotal, double impRecargo, double impIva,
			String dirCorreo, String dirFactura, String dirEnvio,
			boolean cobrada) {
		this.id = id;
		this.clienteId = clienteId;
		this.cliente = null;
		this.nombreCliente = nombreCliente;
		this.nifCliente = nifCliente;
		this.numero = numero;
		this.fecha = fecha;
		this.porcDescuento = porcDescuento;
		this.porcRecargoEquivalencia = porcRecargoEquivalencia;
		this.impTotal = impTotal;
		this.impRecargo = impRecargo;
		this.impIva = impIva;
		this.dirCorreo = dirCorreo;
		this.dirFactura = dirFactura;
		this.dirEnvio = dirEnvio;
		this.cobrada = cobrada;
		this.estado = 0;
		this.detalles = null;
	}

//	public Factura(int id, int clienteId, int numero, Date fecha, double porcDescuento, double porcRecargoEquivalencia, double impTotal, double impRecargo, double impIva, String dirCorreo, String dirFactura, String dirEnvio, boolean cobrada) {
//        this.id = id;
//    	this.clienteId = clienteId;
//        this.numero = numero;
//        this.fecha = fecha;
//        this.porcDescuento = porcDescuento;
//        this.porcRecargoEquivalencia = porcRecargoEquivalencia;
//        this.impTotal = impTotal;
//        this.impRecargo = impRecargo;
//        this.impIva = impIva;
//        this.dirCorreo = dirCorreo;
//        this.dirFactura = dirFactura;
//        this.dirEnvio = dirEnvio;
//        this.cobrada = cobrada;
//    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public double getPorcDescuento() {
        return this.porcDescuento;
    }
    
    public void setPorcDescuento(double porcDescuento) {
        this.porcDescuento = porcDescuento;
    }
    public double getPorcRecargoEquivalencia() {
        return this.porcRecargoEquivalencia;
    }
    
    public void setPorcRecargoEquivalencia(double porcRecargoEquivalencia) {
        this.porcRecargoEquivalencia = porcRecargoEquivalencia;
    }
    public double getImpTotal() {
        return this.impTotal;
    }
    
    public void setImpTotal(double impTotal) {
        this.impTotal = impTotal;
    }
    public double getImpRecargo() {
        return this.impRecargo;
    }
    
    public void setImpRecargo(double impRecargo) {
        this.impRecargo = impRecargo;
    }
    public double getImpIva() {
        return this.impIva;
    }
    
    public void setImpIva(double impIva) {
        this.impIva = impIva;
    }
    public String getDirCorreo() {
        return this.dirCorreo;
    }
    
    public void setDirCorreo(String dirCorreo) {
        this.dirCorreo = dirCorreo;
    }
    public String getDirFactura() {
        return this.dirFactura;
    }
    
    public void setDirFactura(String dirFactura) {
        this.dirFactura = dirFactura;
    }
    public String getDirEnvio() {
        return this.dirEnvio;
    }
    
    public void setDirEnvio(String dirEnvio) {
        this.dirEnvio = dirEnvio;
    }
    public boolean isCobrada() {
        return this.cobrada;
    }
    
    public void setCobrada(boolean cobrada) {
        this.cobrada = cobrada;
    }
    
	public ArrayList<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<Detalle> detalles) {
		this.detalles = detalles;
	}
	
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getNifCliente() {
		return nifCliente;
	}

	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "N� " + numero + " (" + (fecha.getYear()+1900) + ")";
	}

}



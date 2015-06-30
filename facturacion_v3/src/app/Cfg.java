package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Cfg {
	
	private static String pathname = "./config.cfg";
	public static Map<String,String> configMap;
	
//	private boolean guardar() {	
//		try {
//			FileWriter archivo=null;
//			PrintWriter pw = null;
//			archivo = new FileWriter(pathname);
//			pw = new PrintWriter(archivo);
//			for (int i = 0; i < lista.size(); i++) {
//				String registro = lista.get(i).toSend();
//				pw.println(registro);
//			}
//			archivo.close();
//			pw.close();
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
	
	public static boolean loadConfigMap(String path) {
		pathname = path;
		return loadConfigMap();
	}
	
	public static String DBhost() {
		return configMap.get("DBhost");
	}
	
	public static String DBport() {
		return configMap.get("DBport");
	}
	
	public static String DBname() {
		return configMap.get("DBname");
	}
	
	public static String DBuser() {
		return configMap.get("DBuser");
	}
	
	public static String DBpassword() {
		return configMap.get("DBpassword");
	}
	
	public static boolean loadConfigMap() {
		Map<String,String> lista = new HashMap<String,String>();
		try {
			File archivo = null;
			FileReader fr = null;
			BufferedReader br = null;
			archivo = new File(pathname);			
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String registro;
			while ( (registro=br.readLine())!=null ) {
				String[] linea = registro.split("=");
					lista.put(linea[0].trim(), linea[1].trim() );
			}
			br.close();
			fr.close();
			configMap = lista;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//GETTER Y SETTERS
	
	public static String getPathname() {
		return pathname;
	}

	public static void setPathname(String path) {
		pathname = path;
	}

//	@Override
//	public String toString() {
//		String salida = "";
//		Iterator<Entry<String,String>> it = getConfigMap().entrySet().iterator();
//		while (it.hasNext()) {
//		  Entry<String,String> e = it.next();
//		  salida += e.getKey() + ":" + e.getValue() + "\n";
//		}
//		return salida;
//	}
	
}

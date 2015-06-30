package app;

import view.FrMain;

public class App {

	public static void main(String[] args) {
			Cfg.loadConfigMap("./config.cfg");
			//System.out.println(Cfg.configMap);
			FrMain main = new FrMain();
			main.mostrar();
	}

}

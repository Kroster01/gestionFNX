package pet.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PrintLog {
	
	public static Logger getLogger(){
		Logger logger = Logger.getLogger("PetScopeLog");
		logger.setUseParentHandlers(false);
		return logger;
	}
	
	public static FileHandler getHandler(){
		FileHandler fh = null;
		try {
			fh = new FileHandler(Constantes.RUTA_LOG, true);
			
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		return fh;
	}
	
	public static void closeHandler(FileHandler fh){
		fh.flush();
		fh.close();
	}
}

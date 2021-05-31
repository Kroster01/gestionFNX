package pet.acciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import pet.util.Constantes;
import pet.util.PrintLog;

public class LineaBaseAction {
	
	public static boolean creacionLinSolution(String[] datosLinBase) {
		boolean copy = false;

		String idHuno = "";
		String idUbicacion = "";

		idHuno = datosLinBase[0];
		idUbicacion = datosLinBase[1];

		File srcDir = new File(Constantes.RUTA_SANT);
		File dstDir = new File(idUbicacion + Constantes.S_DOUBLE_SLASH + idHuno);
		copy = copyDirectory(srcDir, dstDir);

		return copy;
	}

	public static boolean creacionLinCentro(String[] datosLinBase) {
		boolean copy = false;
		String idPeticion = "";
		String tipoPet = "";
		String idUbicacion = "";
		idPeticion = datosLinBase[0];
		tipoPet = datosLinBase[1];
		idUbicacion = datosLinBase[2];

		if(tipDocuPet(tipoPet)){
			File srcDir = new File(Constantes.RUTA_ME);
			File dstDir = new File(idUbicacion + Constantes.S_DOUBLE_SLASH + idPeticion);
			copy = copyDirectory(srcDir, dstDir);
		} else if (tipoPet.equals(Constantes.S_TIP_PET_SO) ||tipoPet.equals(Constantes.S_TIP_PET_SP)) {
			File srcDir = new File(Constantes.RUTA_SO);
			File dstDir = new File(idUbicacion + Constantes.S_DOUBLE_SLASH + idPeticion);
			copy = copyDirectory(srcDir, dstDir);
		} else {
			copy = false;
		}

		return copy;
	}
	
	public static boolean tipDocuPet(String tipoPet){
		
		if(tipoPet.equals(Constantes.S_TIP_PET_CO) ){
			return true;
		}
		if( tipoPet.equals(Constantes.S_TIP_PET_DTCO)){
			return true;
		}
		if(tipoPet.equals(Constantes.S_TIP_PET_DT)){
			return true;
		}
		if(tipoPet.equals(Constantes.S_TIP_PET_ES)){
			return true;
		}
		
		return false;
	}

	public static boolean copyDirectory(File srcDir, File dstDir) {
		try {
			if (srcDir.isDirectory()) {
				if (!dstDir.exists()) {
					dstDir.mkdir();
				}
				String[] children = srcDir.list();
				for (int i = 0; i < children.length; i++) {
					copyDirectory(new File(srcDir, children[i]), new File(dstDir, children[i]));
				}
				return true;
			} else {
				copyFile(srcDir, dstDir);
				return true;
			}
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * Copia un solo archivo
	 * 
	 * @param s
	 * @param t
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void copyFile(File s, File t) {
		try {
			FileChannel in = (new FileInputStream(s)).getChannel();
			FileChannel out = (new FileOutputStream(t)).getChannel();
			in.transferTo(0, s.length(), out);
			in.close();
			out.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
	}
}

package pet.util;


public class Configuraciones {
	
	private static String URL = "";
	private static String USER = "";
	private static String PASSWORD = "";
	private static String BASE_PATH = "";
	private static String PDF_PATH = "";
	
	public static String getConfigValue(String var){
		if(var.equals("url")){
			return URL;
		}else if(var.equals("user")){
			return USER;
		}else if(var.equals("pass")){
			return PASSWORD;
		}else if(var.equals("base_path")){
			return BASE_PATH;
		}else if(var.equals("pdf_path")){
			return PDF_PATH;
		}else{
			return null;
		}
	}

}

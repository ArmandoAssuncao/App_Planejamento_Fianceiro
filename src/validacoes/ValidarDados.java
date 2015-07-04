package validacoes;

public class ValidarDados {
	public enum VALIDAR{letra, numero};
	
	
	public static boolean validarVazio(String str){
		if(str.equals("")){
			return false;
		}
		return true;
	}
	
	public static boolean validarTamanho(String str, int tamMax){
		if(str.length() > tamMax){
			return false;
		}
		return true;
	}
	
	public static boolean validarString(String str){
		if(str.toLowerCase().matches("[a-z0-9áàâãéèêíïóôõöúçñ_-]+")){
			return true;
		}
		return false;
	}
	
	public static boolean validarNumeroDouble(String str){
		if(!str.equals("")){
			if(!str.matches("[0-9]{1}.*")){
				return false;
			}
			else if(!str.matches("^.*[0-9]$")){
				return false;
			}
			else if(!str.matches("[0-9]{0,10}\\.{0,1}[0-9]{0,10}")){
				return false;
			}
			else if(!str.matches("([0-9]|\\.){0,10}")){
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean validarInicioString(String str, String valorPossivel){
		if(!str.matches(valorPossivel + "{1}.*")){
			return false;
		}
		
		return true;
	}
	
	public static boolean validarFimString(String str, String valorPossivel){
		if(!str.matches("^.*" + valorPossivel + "$")){
			return false;
		}
		
		return true;
	}
	
}
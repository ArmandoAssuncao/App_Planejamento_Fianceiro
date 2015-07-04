package funcoes;

/**
 * Valida várias entradas do aplicativo.
 * @author Armando Assunção
 * @author Richardson William
 *
 */ 
public class ValidarDados {
	/**
	 * Enumeração definindo qual tipo de dado será validado
	 */
	public enum VALIDAR{
	/** Quando selecionado validar letra*/
	letra, 
	/**Quando selecionado validar número*/
	numero};
	
	
	/**
	 * Verifica se a <code>String</code> é vazia.
	 * @param str <code>String</code> a ser validada.
	 * @return <code>true</code> se está correta, <code>false</code> em caso contrário.
	 */
	public static boolean validarVazio(String str){
		if(str.equals("")){
			return false;
		}
		return true;
	}
	
	/**
	 * @param str <code>String</code> a ser validada.
	 * @param tamMax tamanho máximo da <code>String</code>.
	 * @return <code>true</code> se está correta, <code>false</code> em caso contrário.
	 */
	public static boolean validarTamanho(String str, int tamMax){
		if(str.length() > tamMax){
			return false;
		}
		return true;
	}
	
	/**
	 * Valida uma <code>String</code> seguinte uma expressão regular.
	 * @param str <code>String</code> a ser validada.
	 * @return <code>true</code> se está correta, <code>false</code> em caso contrário.
	 */
	public static boolean validarString(String str){
		if(str.toLowerCase().matches("[a-z0-9áàâãéèêíïóôõöúçñ _-]+")){
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se a <code>String</code> contém um número <code>double</code> válido
	 * @param str <code>String</code> a ser validada.
	 * @return <code>true</code> se está correta, <code>false</code> em caso contrário.
	 */
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
	
	/**
	 *  Valida o primeiro caracter de uma <code>String</code>
	 * @param str <code>String</code> a ser validada.
	 * @param valorPossivel valores que podem aparecer no início da <code>String</code> str.
	 * @return <code>true</code> se está correta, <code>false</code> em caso contrário.
	 */
	public static boolean validarInicioString(String str, String valorPossivel){
		if(!str.matches(valorPossivel + "{1}.*")){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Valida o fim de uma <code>String</code>.
	 * @param str <code>String</code> a ser validada.
	 * @param valorPossivel valores que podem aparecer no início da <code>String</code> str.
	 * @return <code>true</code> se está correta, <code>false</code> em caso contrário.
	 */
	public static boolean validarFimString(String str, String valorPossivel){
		if(!str.matches("^.*" + valorPossivel + "$")){
			return false;
		}
		
		return true;
	}
	
}
package classes;

/**
 * Armazena informa��es sobre a renda.
 * Possui os par�metros <code>int</code>codigo e <code>String</code> descri��o herdados
 * da superclasse abstrata <code>Identificacao</code>.
 * @author Armando e Richardson
 */
public class Renda extends Identificacao {
	
	/**
	 * Contrutor padr�o.
	 */
	public Renda() {
		super();
	}
	
	/**
	 *  Construtor sobrecarregado da classe Renda. Recebe o seguinte par�metro:
	 *  @param codigo <code>int</code> de indentifica��o da renda.
	 */
	public Renda(int codigo){
		super(codigo);
	}
	
	/**
	 * Contrutor sobrecarregado da classe Renda. Recebe os seguintes par�metros:
	 * @param codigo <code>int</code> de indentifica��o da renda.
	 * @param descricao <code>String</code> da renda.
	 */
	public Renda(int codigo, String descricao) {
		super(codigo, descricao);
	}
	
	/**
	 * Retorna uma refer�ncia em String do c�digo e da descri��o
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Renda [ " + super.toString() + " ]";
	}
}//class Renda

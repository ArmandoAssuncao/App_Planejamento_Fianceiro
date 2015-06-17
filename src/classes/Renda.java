package classes;

/**
 * Armazena informações sobre a renda.
 * Possui os parâmetros <code>int</code>codigo e <code>String</code> descrição herdados
 * da superclasse abstrata <code>Identificacao</code>.
 * @author Armando e Richardson
 */
public class Renda extends Identificacao {
	
	/**
	 * Contrutor padrão.
	 */
	public Renda() {
		super();
	}
	
	/**
	 *  Construtor sobrecarregado da classe Renda. Recebe o seguinte parâmetro:
	 *  @param codigo <code>int</code> de indentificação da renda.
	 */
	public Renda(int codigo){
		super(codigo);
	}
	
	/**
	 * Contrutor sobrecarregado da classe Renda. Recebe os seguintes parâmetros:
	 * @param codigo <code>int</code> de indentificação da renda.
	 * @param descricao <code>String</code> da renda.
	 */
	public Renda(int codigo, String descricao) {
		super(codigo, descricao);
	}
	
	/**
	 * Retorna uma referência em String do código e da descrição
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Renda [ " + super.toString() + " ]";
	}
}//class Renda

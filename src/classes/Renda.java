package classes;

/**
 * Armazena informações sobre a renda.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class Renda {
	private String descricao;
	
	/**
	 * Contrutor padrão.
	 */
	public Renda() {}
	
	
	/**
	 * Contrutor sobrecarregado da classe Renda. Recebe o seguinte parâmetro:
	 * @param descricao <code>String</code> da renda.
	 */
	public Renda(String descricao) {
		this.descricao = descricao;
	}
	
	/** Retornar uma <code>String</code> com a descrição da renda
	 * @return <code>String</code> com a descrição da renda
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/** Define a descrição da renda
	 * @param descricao <code>String</code> com a nova descrição da renda
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Retorna uma referência em String da descrição
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Renda [ " + descricao + " ]";
	}
}//class Renda

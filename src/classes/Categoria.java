package classes;

/**
 * Armazena informações sobre a categoria dos gastos.
 * Possui os parâmetro <code>String</code> descrição.
 * @author Armando e Richardson
 */
public class Categoria {
	private String descricao;
	
	/**
	 * Construtor padrão.
	 */
	public Categoria() {
	}//Categoria()
	
	/**
	 * Construtor sobrecarregado da classe Categoria. Recebe o seguinte parâmetro:
	 * @param descricao <code>String</code> da categoria.
	 */
	public Categoria(String descricao){
		this.descricao = descricao;
	}//Categoria()
	
	
	/** Retornar uma <code>String</code> com a descrição da categoria
	 * @return <code>String</code> com a descrição da categoria
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/** Define a descrição da categoria
	 * @param descricao <code>String</code> com a nova descrição da categoria
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna uma referência em String da descrição da Categoria.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Categoria [ descricao:" + descricao + " ]";
	}

}//class Categoria
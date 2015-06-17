package classes;

/**
 * A classe Identificacao, possui os atributos que são comuns entre as classes do trabalho. 
 * Os atributos são um <code>int</code> codigo e <code>String</code> descricao.
 * @author Armando e Richardson
 */
public abstract class Identificacao {
	protected int codigo;
	protected String descricao;
	
	/**
	 * Construtor padrão.
	 */
	public Identificacao() {}
	
	/**
	 * Contrutor sobrecarregado da classe Identificacao. Recebe o seguinte parâmetro:
	 * @param codigo <code>int</code> de identificação.
	 */
	public Identificacao(int codigo){
		this.codigo = codigo;
	}//Identificacao
	
	/**
	 * Contrutor sobrecarregado da classe Identificacao. Recebe os seguintes parâmetros:
	 * @param codigo <code>int</code> de identificação.
	 * @param descricao <code>String</code> com o nome identificando o item.
	 */
	public Identificacao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}//Identificacao()
	
	/**
	 * Retorna o código cadastrado.
	 * @return um <code>int</code> com o código
	 */
	public int getCodigo() {
		return codigo;
	}//getCodigo()

	/**
	 * Atribui o valor recebido por parâmetro como código do item.
	 * @param codigo <code>int</code> com o código do item.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}//setCodigo()
	
	/**
	 * Retorna a descrição cadastrada.
	 * @return um <code>String</code> com a descrição do item.
	 */
	public String getDescricao() {
		return descricao;
	}//getDescricao()
	
	/**
	 * Atribui o valor recebido por parâmetro como descrição do item.
	 * @param descricao <code>String</code> do item.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}//setDescricao()
	
	/**
	 * Retorna uma referência em String do código e da descrição.
	 * @return um <code>String</code> referênte aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Código: " + codigo + ", Descrição: " + descricao;
	}//toString()
}//class Identificacao

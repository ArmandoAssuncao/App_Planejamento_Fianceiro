package classes;
/**
 * Armazena informações sobre a categoria dos gastos.
 * Possui os parâmetros <code>int</code>codigo e <code>String</code> descrição herdados
 * da superclasse abstrata <code>Identificacao</code>.
 * @author Armando e Richardson
 */
public class Categoria extends Identificacao {
	/**
	 * Construtor padrão.
	 */
	public Categoria() {
		super();
	}//Categoria()
	
	/**
	 * Construtor sobrecarregado da classe Categoria. Recebe o seguinte parâmetro:
	 * @param codigo <code>int</code> da categoria.
	 */
	public Categoria(int codigo){
		super(codigo);
	}//Categoria()
	
	/**
	 * Construtor sobrecarregado da classe Categoria. Recebe os seguintes parâmetros:
	 * @param codigo <code>int</code> da categoria.
	 * @param descricao <code>String</code> da categoria.
	 */
	public Categoria(int codigo, String descricao) {
		super(codigo, descricao);
	}//Categoria()
	
	/**
	 * Retorna uma referência em String do código e da descrição da Categoria.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Categoria [ " + super.toString() + " ]";
	}

	/**
	 * Verifica se existe uma <code>Categoria</code> com o id fornecido no banco de dados.
	 * @param codigoCategoria <code>int</code> com o codigo da categoria a ser pesquisada.
	 * @return <code>true</code> se a categoria existe, <code>false</code> em caso contrário.
	 */
	public static boolean exists(int codigoCategoria){
		//TODO
		return true;
	}
	
	/**
	 * Retorna o id da categoria no banco de dados
	 * @return <code>int</code> com o id da categoria no banco de dados, caso não encontre retorna <code>0</code>
	 */
	public static int getId(){
		//TODO
		return 1;
	}
}//class Categoria
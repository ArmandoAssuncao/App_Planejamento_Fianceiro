package classes;

/**
 * Guarda a forma como foi efetuado o pagamento da despesa.
 * Possui os parâmetros <code>int</code>codigo e <code>String</code> descrição herdados
 * da superclasse abstrata <code>Identificacao</code>.
 * @author Armando e Richardson
 */
public class FormaPagamento extends Identificacao{
	/**
	 * Contrutor padrão.
	 */
	public FormaPagamento(){}

	/**
	 * Construtor sobrecarregado da classe FormaPagamento. Recebe o seguinte parâmetro:
	 * @param codigo <code>int</code> da forma de pagamento.
	 */
	public FormaPagamento(int codigo) {
		super(codigo);
	}

	/**
	 * Contrutor sobrecarregado da classe FormaPagamento. Rebebe os seguintes parâmetros:
	 * @param codigo <code>int</code> da forma de pagamento.
	 * @param descricao <code>String</code> da forma de pagamento.
	 */
	public FormaPagamento(int codigo, String descricao) {
		super(codigo, descricao);
	}
	
	/**
	 * Retorna uma referência em String do código e da descrição da forma de pagamento.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Forma de Pagamento [ " + super.toString() + " ]";
	}
}//class FormaPagamento

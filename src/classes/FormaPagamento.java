package classes;

/**
 * Guarda a forma como foi efetuado o pagamento da despesa.
 * Possui os par�metros <code>int</code>codigo e <code>String</code> descri��o herdados
 * da superclasse abstrata <code>Identificacao</code>.
 * @author Armando e Richardson
 */
public class FormaPagamento extends Identificacao{
	/**
	 * Contrutor padr�o.
	 */
	public FormaPagamento(){}

	/**
	 * Construtor sobrecarregado da classe FormaPagamento. Recebe o seguinte par�metro:
	 * @param codigo <code>int</code> da forma de pagamento.
	 */
	public FormaPagamento(int codigo) {
		super(codigo);
	}

	/**
	 * Contrutor sobrecarregado da classe FormaPagamento. Rebebe os seguintes par�metros:
	 * @param codigo <code>int</code> da forma de pagamento.
	 * @param descricao <code>String</code> da forma de pagamento.
	 */
	public FormaPagamento(int codigo, String descricao) {
		super(codigo, descricao);
	}
	
	/**
	 * Retorna uma refer�ncia em String do c�digo e da descri��o da forma de pagamento.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Forma de Pagamento [ " + super.toString() + " ]";
	}
}//class FormaPagamento

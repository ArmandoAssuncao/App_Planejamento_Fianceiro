package enumeracoes;

/**
 * Esta classe contém os tipos de pagamento.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public enum FormaPagamento {
	/**
	 * Pagamento a vista.
	 */
	AVISTA("A Vista"),
	
	/**
	 * Pagamento com cartão.
	 */
	CARTAO("Cartão de Credito"),
	
	/**
	 * Pagamento com cheque
	 */
	CHEQUE("Cheque"),
	
	/**
	 * Pagamento a prazo.
	 */
	PRAZO("Prazo");

	private String formaPagamento;
	
	/**
	 * Construtor sobrecarregado.
	 * @param formaPagamento do tipo <code>String</code>.
	 */
	FormaPagamento(String formaPagamento) {
		setFormaPagamento(formaPagamento);
	}
	
	/**
	 * Retorna o tipo do pagamento armanzenado na <code>String</code>.
	 * @return uma <code>String</code>.
	 */
	public String getFormaPagamento() {
		return formaPagamento;
	}
	
	/**
	 * Define o valor da variavel.
	 * @param formaPagamento do tipo <code>String</code>.
	 */
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}
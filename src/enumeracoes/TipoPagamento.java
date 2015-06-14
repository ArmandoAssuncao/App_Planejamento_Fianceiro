package enumeracoes;

/**
 * Est� classe cont�m os tipos de pagamento.
 * @author Samuel e Wagner 
 */
public enum TipoPagamento {
	AVISTA("A Vista"),
	CARTAO("Cart�o de Credito"),
	CHEQUE("Cheque"),
	PRAZO("Prazo");
	private String tipoPagamento;
	
	/**
	 * Construtor sobrecarregado.
	 * @param tipoPagamento do tipo <code>String</code>.
	 */
	TipoPagamento(String tipoPagamento) {
		setTipoPagamento(tipoPagamento);
	}
	
	/**
	 * Retorna o tipo do pagamento armanzenado na <code>String</code>.
	 * @return uma <code>String</code>.
	 */
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	
	/**
	 * Define o valor da variavel.
	 * @param tipoPagamento do tipo <code>String</code>.
	 */
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
}
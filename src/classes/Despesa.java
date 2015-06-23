package classes;

import java.util.Calendar;
/**
 * Contém atributos e métodos para armazenar as informações das despesas realizadas.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class Despesa {
	private int idDespesa;
	private String descricao;
	private Calendar dataDespesa;
	private Calendar dataPagamento;
	private String numeroCheque;
	private double valorDespesa;
	private int numeroParcelas;
	
	/**
	 * Construtor padrão. Inicia as variáveis de instância
	 * 
	 * dataDespesa <code>Calendar</code> e dataPagamento <code>Calendar</code>  com a data atual do sistema.
	 */
	public Despesa() {
		dataDespesa = Calendar.getInstance();
		dataPagamento = Calendar.getInstance();
	}//Despesa()
	
	/**
	 * Construtor sobrecarregado da classe Despesa. Recebe como argumento os seguintes parâmetros.
	 * 
	 * @param idDespesa <code>int</code> com o id da despesa
	 * @param descricao <code>String</code> com a descrição da despesa
	 * @param dataDespesa <code>Calendar</code> com a data da despesa. 
	 * @param dataPagamento <code>Calendar</code> com a data em que o pagamento foi realizado.
	 * @param numeroCheque <code>String</code> caso este tenha sido utilizado.
	 * @param valorDespesa <code>double</code> com o valor gasto com a despesa.
	 * @param numeroParcelas <code>int</code> caso o pagamento tenha sido parcelado.
	 */
	public Despesa(int idDespesa, String descricao, Calendar dataDespesa, Calendar dataPagamento, String numeroCheque,
				   double valorDespesa, int numeroParcelas) {
		this.idDespesa = idDespesa;
		this.descricao = descricao;
		this.dataDespesa = dataDespesa;
		this.dataPagamento = dataPagamento;
		this.numeroCheque = numeroCheque;
		this.valorDespesa = valorDespesa;
		this.numeroParcelas = numeroParcelas;
	}//Despesa()
	

	/**
	 * Retorna o id da despesa
	 * @return um <code>int</code> com o id da despesa
	 */
	public int getIdDespesa() {
		return idDespesa;
	}

	
	/** Define o id da despesa
	 * @param idDespesa <code>int</code> com o id da despesa
	 */
	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}

	/**
	 * Retorna a descrição da despesa
	 * @return uma <code>String</code> com a descrição da despesa
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Define a descrição da despesa
	 * @param descricao <code>String</code> com a nova descrição da despesa
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna a data da despesa.
	 * @return um <code>Calendar</code> com a data da despesa.
	 */
	public Calendar getDataDespesa() {
		return dataDespesa;
	}//getDataDespesa()

	/**
	 * Atribui a data da despesa recebida por parâmetro.
	 * @param dataDespesa <code>Calendar</code> com a data em que a despesa foi realizada.
	 */
	public void setDataDespesa(Calendar dataDespesa) {
		this.dataDespesa = dataDespesa;
	}//setDataDespesa()

	/**
	 * Retorna a data de pagamento.
	 * @return um <code>Calendar</code> com a data em que o pagamento foi realizado.
	 */
	public Calendar getDataPagamento() {
		return dataPagamento;
	}//getDataPagamento
	
	/**
	 * Atribui a data de pagamento recebida por parâmetro.
	 * @param dataPagamento <code>Calendar</code> com a data em que o pagamento foi realizado.
	 */
	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}//setDataPagamento()

	/**
	 * Retorna o número do cheque.
	 * @return um <code>String</code> com o número do cheque.
	 */
	public String getNumeroCheque() {
		return numeroCheque;
	}//getNumeroCheque()

	/**
	 * Atribui o número do cheque (caso esta seja a forma de pagamento) recebida por parâmetro.
	 * @param numeroCheque <code>String</code> com o número do cheque.
	 */
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}//setNumeroCheque()
	
	/**
	 * Retorna o valor da despesa.
	 * @return um <code>double</code> com o valor da despesa.
	 */
	public double getValorDespesa() {
		return valorDespesa;
	}//getValorDespesa()
	
	/**
	 * Atribui o valor da despesa recebido por parâmetro.
	 * @param valorDespesa <code>double</code> com o valor da despesa.
	 */
	public void setValorDespesa(double valorDespesa) {
		this.valorDespesa = valorDespesa;
	}//setValorDespesa()
	
	/**
	 * Retorna a quantidade de parcelas.
	 * @return um <code>int</code> quantidade de parcelas em que a despesa será quitada.
	 */
	public int getNumeroParcelas() {
		return numeroParcelas;
	}//getNumeroParcelas()
	
	/**
	 * Atribui a quantidade de parcelas recebida como parâmetro.
	 * @param numeroParcelas <code>int</code> número de parcelas em que a despesa será quitada.
	 */
	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}//setNumeroParcelas()
	
	/**
	 * Retorna uma referência em String contendo as informações da despesa.
	 * @return um <code>String</code> com as informações da despesa.
	 */
	@Override
	public String toString() {
		return "ID: " + idDespesa + ", descricao:" + descricao +			
																String.format(", Data da despesa: %02d/%02d/%04d", 
																			dataDespesa.get(Calendar.DAY_OF_MONTH),
																			dataDespesa.get(Calendar.MONTH) + 1, 
																			dataDespesa.get(Calendar.YEAR)) + 
															    String.format(", Data de pagamento: %02d/%02d/%04d" , 
															    		    dataPagamento.get(Calendar.DAY_OF_MONTH),
															    		    dataPagamento.get(Calendar.MONTH) + 1,
															    		    dataPagamento.get(Calendar.YEAR)) + 
				 ", Número do Cheque " + numeroCheque + String.format(", Valor da despesa: R$%1.2f", valorDespesa) + 
				 ", Número de Parcelas: " + numeroParcelas;
	}//toString()
}//class Despesa

package classes;

import java.util.Calendar;
/**
 * Contém atributos e métodos para armazenar as informações das despesas realizadas.
 * Os atributos <code>int</code>codigo e <code>String</code> são herdados da superclasse abstrata Identificação.
 * 
 * @author Armando e Richardson
 *
 */
public class Despesa extends Identificacao {
	private int codigoCategoria;
	private int codigoPagamento;
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
		super();
		dataDespesa = Calendar.getInstance();
		dataPagamento = Calendar.getInstance();
	}//Despesa()
	
	/**
	 * Construtor sobrecarregado da classe Despesa. Recebe como argumento os seguintes parâmetros.
	 * 
	 * @param codigoDespesa <code>int</code> com o codigo da despesa
	 * @param descricao <code>String</code> com a descrição da despesa a ser cadastrada.
	 * @param codigoCategoria <code>int</code> com o código da categoria da despesa.
	 * @param codigoPagamento <code>int</code> com o código do pagamento informando a forma de pagamento utilizada.
	 * @param dataDespesa <code>Calendar</code> com a data da despesa. 
	 * @param dataPagamento <code>Calendar</code> com a data em que o pagamento foi realizado.
	 * @param numeroCheque <code>String</code> caso este tenha sido utilizado.
	 * @param valorDespesa <code>double</code> com o valor gasto com a despesa.
	 * @param numeroParcelas <code>int</code> caso o pagamento tenha sido parcelado.
	 */
	public Despesa(int codigoDespesa, String descricao, int codigoCategoria, int codigoPagamento,
				   Calendar dataDespesa, Calendar dataPagamento, String numeroCheque,
				   double valorDespesa, int numeroParcelas) {
		super(codigoDespesa, descricao);
		this.codigoCategoria = codigoCategoria;
		this.codigoPagamento = codigoPagamento;
		this.dataDespesa = dataDespesa;
		this.dataPagamento = dataPagamento;
		this.numeroCheque = numeroCheque;
		this.valorDespesa = valorDespesa;
		this.numeroParcelas = numeroParcelas;
	}//Despesa()

	/**
	 * Retorna o código da categoria.
	 * @return um <code>int</code> com o código da categoria da despesa.
	 */
	public int getCodigoCategoria() {
		return codigoCategoria;
	}//getCodigoCategoria()

	/**
	 * Atribui o codigo da categora recebido por parâmetro.
	 * @param codigoCategoria <code>int</code> com o código da categoria da despesa.
	 */
	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}//setCodigoCategoria()

	/**
	 * Retorna o código do pagamento.
	 * @return um <code>int</code> com o código do pagamento.
	 */
	public int getCodigoPagamento() {
		return codigoPagamento;
	}//getCoditoPagamento()
	
	/**
	 * Atribui o codigo do pagamento recebido por parâmetro.
	 * @param codigoPagamento <code>int</code> com o código do tipo de pagamento realizado.
	 */
	public void setCodigoPagamento(int codigoPagamento) {
		this.codigoPagamento = codigoPagamento;
	}//setCodigoPagamento()

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
	 * Atribui o número do cheque (caso está seja a forma de pagamento) recebida por parâmetro.
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
		return super.toString() + "Código da categoria: " + codigoCategoria
				+ ", Código do pagamento: " + codigoPagamento + String.format(", Data da despesa: %02d/%02d/%04d", 
																			dataDespesa.get(Calendar.DAY_OF_MONTH),
																			dataDespesa.get(Calendar.MONTH) + 1, 
																			dataDespesa.get(Calendar.YEAR)) + 
															    String.format(", Data de pagamento: %02d/%02d/%04d" , 
															    		    dataPagamento.get(Calendar.DAY_OF_MONTH),
															    		    dataPagamento.get(Calendar.MONTH) + 1,
															    		    dataPagamento.get(Calendar.YEAR)) + 
				 ", Número do Cheque: " + numeroCheque + String.format(", Valor da despesa: R$%1.2f", valorDespesa) + 
				 ", Número de Parcelas: " + numeroParcelas;
	}//toString()
}//class Despesa

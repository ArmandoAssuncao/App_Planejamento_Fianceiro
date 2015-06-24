package classes;

import java.util.Calendar;

import persistencia.RendaDAO;

import classes.RendaMensal;

/**
 * Armazena informações sobre a renda dos gastos.
 * Possui os parâmetro <code>String</code> descrição.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class Renda{
	private String descricao;
	
	private RendaMensal rendaMensal;
	
	/**
	 * Construtor padrão.
	 */
	public Renda() {
		rendaMensal = new RendaMensal();
	}//Renda()
	
	/**
	 * Construtor sobrecarregado da classe Renda. Recebe o seguinte parâmetro:
	 * @param descricao <code>String</code> da renda.
	 */
	public Renda(String descricao){
		this.descricao = descricao;
	}//Renda()
	
	/**
	 * Construtor sobrecarregado da classe Renda. Recebe os seguintes parâmetro:
	 * @param descricao <code>String</code> da renda.
	 * @param dataRenda <code>Calendar</code> da renda.
	 * @param valor <code>double</code> da renda.
	 */
	public Renda(String descricao, Calendar dataRenda, double valor){
		this.descricao = descricao;
		rendaMensal.setDataRenda(dataRenda);
		rendaMensal.setValor(valor);
	}//Renda()
	
	
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
	
	
	/** Retornar uma <code>RendaMensal</code> com a renda mensal da renda
	 * @return <code>RendaMensal</code> com a renda mensal da renda
	 */
	public RendaMensal getRendaMensal() {
		return rendaMensal;
	}

	/** Define a renda mensal da renda
	 * @param rendaMensal <code>RendaMensal</code> com a nova renda mensal da renda
	 */
	public void setRendaMensal(RendaMensal rendaMensal) {
		this.rendaMensal = rendaMensal;
	}
	
	/** Retornar um <code>Calendar</code> com a data da renda mensal da renda
	 * @return <code>Calendar</code> com a data da renda mensal da renda
	 */
	public Calendar getDataRenda() {
		return rendaMensal.getDataRenda();
	}

	/** Define a data da renda mensal da renda
	 * @param dataRenda <code>Calendar</code> com a nova data da renda mensal da renda
	 */
	public void dataRenda(Calendar dataRenda) {
		this.rendaMensal.setDataRenda(dataRenda);
	}
	
	/** Retornar um <code>double</code> com o valor da renda mensal da renda
	 * @return <code>double</code> com valor da renda mensal da renda
	 */
	public double getValorRenda() {
		return rendaMensal.getValor();
	}

	/** Define o valor da renda mensal da renda
	 * @param valorRenda <code>double</code> com o novo valor da renda mensal da renda
	 */
	public void setValorRenda(double valorRenda) {
		this.rendaMensal.setValor(valorRenda);
	}

	/**
	 * Retorna uma referência em String da descrição da Renda.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Renda [ descricao:" + descricao + " ]" + rendaMensal.toString();
	}

}//class Renda
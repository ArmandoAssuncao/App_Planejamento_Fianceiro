package classes;

import java.util.Calendar;
/**
 * Armazena as informações sobre as metas e gastos referentes a um Mes/Ano determinado.
 * Os atributos são um <code>Calendar</code> mesAnoMeta e um <code>double</code> valor.
 * @author Armando e Richardson
 */
public class MetaMensal {
	private Calendar mesAnoMeta;
	private double valor;
	
	/**
	 * Construtor padrão.
	 */
	public MetaMensal() {
		super();
	}//MetaMensal()
	
	/**
	 * Construtor sobrecarregado da classe MetaMensal. Recebe os seguintes parâmetros:
	 * @param descricao <code>String</code> da meta mensal
	 * @param mesAnoMeta <code>Calendar</code> com o mês e o ano da meta mensal.
	 * @param valor <code>double</code> da meta para os gastos.
	 */
	public MetaMensal(Calendar mesAnoMeta, double valor) {
		this.mesAnoMeta = mesAnoMeta;
		this.valor = valor;
	}//MetaMensal()

	/**
	 * Retorna o mês/ano da meta.
	 * @return<code>Calendar</code> 
	 */
	public Calendar getMesAnoMeta() {
		return mesAnoMeta;
	}//getMesAnoMeta()
	
	/**
	 * Atribui o mês/ano da meta mensal.
	 * @param mesAnoMeta <code>Calendar</code>
	 */
	public void setMesAnoMeta(Calendar mesAnoMeta) {
		this.mesAnoMeta = mesAnoMeta;
	}//setMesAnoMeta
	
	/**
	 * Retornar o valor estipulado para a meta mensal.
	 * @return valor <code>double</code>
	 */
	public double getValor() {
		return valor;
	}//getValor()

	/**
	 * Atribui o valor da meta mensal.
	 * @param valor <code>double</code>
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}//setValor()
	
	/**
	 * Retornar uma referência de uma String contendo as informações da meta.
	 * @return <code>String</code>
	 */
	@Override
	public String toString() {
		return "Meta Mensal [ Mes/Ano da Meta:" + mesAnoMeta + String.format(", Valor R$ %1.2f", valor) + " ]";
	}//toString()
}//class MetaMensal

package classes;

import java.util.Calendar;
/**
 * Armazena as informa��es sobre as metas e gastos referentes a um Mes/Ano determinado.
 * Os atributos s�o um <code>Calendar</code> mesAnoMeta e um <code>double</code> valor.
 * @author Armando e Richardson
 */
public class MetaMensal extends Categoria {
	private Calendar mesAnoMeta;
	private double valor;
	
	/**
	 * Construtor padr�o.
	 */
	public MetaMensal() {
		super();
	}//MetaMensal()
	
	/**
	 * Construtor sobrecarregado da classe MetaMensal. Recebe os seguintes par�metros:
	 * @param codigo <code>int</code> da meta mensal.
	 * @param mesAnoMeta <code>Calendar</code> com o m�s e o ano da meta mensal.
	 * @param valor <code>double</code> da meta para os gastos.
	 */
	public MetaMensal(int codigo, Calendar mesAnoMeta, double valor) {
		super(codigo);
		this.mesAnoMeta = mesAnoMeta;
		this.valor = valor;
	}//MetaMensal()

	/**
	 * Retorna o m�s/ano da meta.
	 * @return<code>Calendar</code> 
	 */
	public Calendar getMesAnoMeta() {
		return mesAnoMeta;
	}//getMesAnoMeta()
	
	/**
	 * Atribui o m�s/ano da meta mensal.
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
	 * Retornar uma refer�ncia de uma String contendo as informa��es da meta.
	 * @return <code>String</code>
	 */
	@Override
	public String toString() {
		return "Meta Mensal [ C�digo: " + getCodigo() + ", Mes/Ano da Meta:" + mesAnoMeta + String.format(", Valor R$ %1.2f", valor) + " ]";
	}//toString()
}//class MetaMensal

package classes;

import java.util.Calendar;
/**
 * Armazena as informações sobre as metas e gastos referentes a um Mes/Ano determinado.
 * Os atributos são um <code>Calendar</code> mesAnoMeta e um <code>double</code> valor.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class MetaMensal {
	private Calendar mesAnoMeta;
	private double valor;
	private double alerta;
	private int id;
	
	/**
	 * Construtor padrão.
	 */
	public MetaMensal() {
		super();
	}//MetaMensal()
	
	/**
	 * Construtor sobrecarregado da classe MetaMensal. Recebe os seguintes parâmetros:
	 * @param id <code>int</code> da categoria associada a meta mensal.
	 * @param mesAnoMeta <code>Calendar</code> com o mês e o ano da meta mensal.
	 * @param valor <code>double</code> da meta para os gastos.
	 * @param alerta <code>double</code> exibe uma mensagem quando o valor em porcento da categoria chegar ao do alerta.
	 */
	public MetaMensal(int id, Calendar mesAnoMeta, double valor, double alerta) {
		this.id = id;
		this.mesAnoMeta = mesAnoMeta;
		this.valor = valor;
		this.alerta = alerta;
	}//MetaMensal()
	
	/**
	 * Construtor sobrecarregado da classe MetaMensal. Recebe os seguintes parâmetros:
	 * @param mesAnoMeta <code>Calendar</code> com o mês e o ano da meta mensal.
	 * @param valor <code>double</code> da meta para os gastos.
	 * @param alerta <code>double</code> exibe uma mensagem quando o valor em porcento da categoria chegar ao do alerta.
	 */
	public MetaMensal(Calendar mesAnoMeta, double valor, double alerta) {
		this.mesAnoMeta = mesAnoMeta;
		this.valor = valor;
		this.alerta = alerta;
	}//MetaMensal()

	/**
	 * Retorna o mês/ano da meta.
	 * @return <code>Calendar</code> 
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
	 * Retornar o alerta estipulado da meta mensal
	 * @return alerta <code>double</code>
	 */
	public double getAlerta() {
		return alerta;
	}

	/**
	 * Atribui o alerta da meta mensal.
	 * @param alerta <code>double</code>
	 */
	public void setAlerta(double alerta) {
		this.alerta = alerta;
	}
	
	
	/**
	 * Retorna o id da renda.
	 * @return <code>int</code> id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Atribui o id da renda.
	 * @param id <code>int</code>
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retornar uma referência de uma String contendo as informações da meta.
	 * @return <code>String</code>
	 */
	@Override
	public String toString() {
		return "Meta Mensal [ Mes/Ano da Meta:" + mesAnoMeta + String.format(", Valor R$ %1.2f", valor) + String.format(", Alerta %1.2f%%", alerta) + " ]";
	}//toString()
}//class MetaMensal

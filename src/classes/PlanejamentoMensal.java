package classes;

import java.util.Calendar;
/**
 * Define os atributos e métodos da classe PlanejamentoMensal. A classe possui os seguintes atributos:
 * <code>int</code> codigoDespesa referente ao código da despesa e um <code>Calendar</code> mesAnoPlanejamento
 * referente ao mês/ano do planejamento.
 * @author Armando e Richardson
 */
public class PlanejamentoMensal {
	private int codigoDespesa;
	private Calendar mesAnoPlanejamento;
	
	/**
	 * Construtor padrão da classe PlanejamentoMensal.
	 */
	public PlanejamentoMensal() {}
	
	/**
	 * Construtor sobrecarregado da classe PlanejamentoMensal. Recebe os seguintes parâmetros.
	 * @param codigoDespesa <code>int</code> referênte ao código da despesa.
	 * @param mesAnoPlanejamento <code>Calendar</code> referênte ao mês/ano do planejamento.
	 */
	public PlanejamentoMensal(int codigoDespesa, Calendar mesAnoPlanejamento) {
		this.codigoDespesa = codigoDespesa;
		this.mesAnoPlanejamento = mesAnoPlanejamento;
	}//Planejamento()
	
	/**
	 * Retorna o código da despesa.
	 * @return um <code>int</code> com o código da despesa.
	 */
	public int getCodigoDespesa() {
		return codigoDespesa;
	}//getCodigoDespesa()
	
	/**
	 * Atribui o código da despesa.
	 * @param codigoDespesa <code>int</code> com o código da despesa.
	 */
	public void setCodigoDespesa(int codigoDespesa) {
		this.codigoDespesa = codigoDespesa;
	}//setCodigoDespesa()
	
	/**
	 * Retorna o mês/ano do planejamento.
	 * @return um <code>Calendar</code> com o mês/ano do planejamento.
	 */
	public Calendar getMesAnoPlanejamento() {
		return mesAnoPlanejamento;
	}//getMesAnoPlanejamento()
	
	/**
	 * Atribui o mês/ano do planejamento.
	 * @param mesAnoPlanejamento <code>Calendar</code> com o mês/ano do planejamento.
	 */
	public void setMesAnoPlanejamento(Calendar mesAnoPlanejamento) {
		this.mesAnoPlanejamento = mesAnoPlanejamento;
	}//setMesAnoPlanejamento()
	
	/**
	 * Retornar uma referência de uma String contendo as informações do planejamento mensal.
	 * @return um <code>String</code> com as informações de planejamento.
	 */
	@Override
	public String toString() {
		return "Despesa [ Código Despesa: " + codigoDespesa + ", Mês/Ano Planejamento:" + mesAnoPlanejamento + " ]";
	}//toString()
}//class PlanejamentoMensal

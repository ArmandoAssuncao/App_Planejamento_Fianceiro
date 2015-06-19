package classes;

import java.util.Calendar;
/**
 * Define os atributos e métodos da classe PlanejamentoMensal. A classe possui os seguinte atributo:
 * <code>Calendar</code> mesAnoPlanejamento referente ao mês/ano do planejamento.
 * @author Armando e Richardson
 */
public class PlanejamentoMensal {
	private Calendar mesAnoPlanejamento;
	
	/**
	 * Construtor padrão da classe PlanejamentoMensal.
	 */
	public PlanejamentoMensal() {}
	
	/**
	 * Construtor sobrecarregado da classe PlanejamentoMensal. Recebe os seguinte parâmetro.
	 * @param mesAnoPlanejamento <code>Calendar</code> referente ao mês/ano do planejamento.
	 */
	public PlanejamentoMensal(Calendar mesAnoPlanejamento) {
		this.mesAnoPlanejamento = mesAnoPlanejamento;
	}//Planejamento()
	
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
		return "Despesa [ mês/Ano Planejamento:" + mesAnoPlanejamento + " ]";
	}//toString()
}//class PlanejamentoMensal

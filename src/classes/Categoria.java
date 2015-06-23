package classes;

import java.util.Calendar;

import persistencia.CategoriaDAO;

/**
 * Armazena informações sobre a categoria dos gastos.
 * Possui os parâmetro <code>String</code> descrição.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class Categoria extends CategoriaDAO{
	private String descricao;
	
	private MetaMensal metaMensal;
	
	/**
	 * Construtor padrão.
	 */
	public Categoria() {
		metaMensal = new MetaMensal();
	}//Categoria()
	
	/**
	 * Construtor sobrecarregado da classe Categoria. Recebe o seguinte parâmetro:
	 * @param descricao <code>String</code> da categoria.
	 */
	public Categoria(String descricao){
		this.descricao = descricao;
	}//Categoria()
	
	/**
	 * Construtor sobrecarregado da classe Categoria. Recebe os seguintes parâmetro:
	 * @param descricao <code>String</code> da categoria.
	 * @param mesAnoMeta <code>Calendar</code> da categoria.
	 * @param valor <code>double</code> da categoria.
	 */
	public Categoria(String descricao, Calendar mesAnoMeta, double valor){
		this.descricao = descricao;
		metaMensal.setMesAnoMeta(mesAnoMeta);
		metaMensal.setValor(valor);
	}//Categoria()
	
	
	/** Retornar uma <code>String</code> com a descrição da categoria
	 * @return <code>String</code> com a descrição da categoria
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/** Define a descrição da categoria
	 * @param descricao <code>String</code> com a nova descrição da categoria
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	/** Retornar uma <code>MetaMensal</code> com a meta mensal da categoria
	 * @return <code>MetaMensal</code> com a meta mensal da categoria
	 */
	public MetaMensal getMetaMensal() {
		return metaMensal;
	}

	/** Define a meta mensal da categoria
	 * @param metaMensal <code>MetaMensal</code> com a nova meta mensal da categoria
	 */
	public void setMetaMensal(MetaMensal metaMensal) {
		this.metaMensal = metaMensal;
	}
	
	/** Retornar um <code>Calendar</code> com a data da meta mensal da categoria
	 * @return <code>Calendar</code> com a data da meta mensal da categoria
	 */
	public Calendar getMesAnoMeta() {
		return metaMensal.getMesAnoMeta();
	}

	/** Define a data da meta mensal da categoria
	 * @param mesAnoMeta <code>Calendar</code> com a nova data da meta mensal da categoria
	 */
	public void setMesAnoMeta(Calendar mesAnoMeta) {
		this.metaMensal.setMesAnoMeta(mesAnoMeta);
	}
	
	/** Retornar um <code>double</code> com o valor da meta mensal da categoria
	 * @return <code>double</code> com valor da meta mensal da categoria
	 */
	public double getValorMeta() {
		return metaMensal.getValor();
	}

	/** Define o valor da meta mensal da categoria
	 * @param valorMeta <code>double</code> com o novo valor da meta mensal da categoria
	 */
	public void setValorMeta(double valorMeta) {
		this.metaMensal.setValor(valorMeta);
	}

	/**
	 * Retorna uma referência em String da descrição da Categoria.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Categoria [ descricao:" + descricao + " ]" + metaMensal.toString();
	}

}//class Categoria
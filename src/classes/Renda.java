package classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import funcoes.Converte;

/**
 * Armazena informações sobre a renda dos gastos.
 * Possui os parâmetro <code>String</code> descrição.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class Renda{
	private String descricao = new String();
	private List<RendaMensal> rendasMensais = new ArrayList<RendaMensal>();
	
	/**
	 * Construtor padrão.
	 */
	public Renda() {}//Renda()
	
	/**
	 * Construtor sobrecarregado da classe Renda. Recebe o seguinte parâmetro:
	 * @param descricao <code>String</code> da renda.
	 */
	public Renda(String descricao){
		this.descricao = descricao;
	}//Renda()
	
//	/**
//	 * Construtor sobrecarregado da classe Renda. Recebe os seguintes parâmetro:
//	 * @param descricao <code>String</code> da renda.
//	 * @param dataRenda <code>Calendar</code> da renda.
//	 * @param valor <code>double</code> da renda.
//	 */
//	public Renda(String descricao, Calendar dataRenda, double valor){
//		this.descricao = descricao;
//		rendasMensais.setDataRenda(dataRenda);
//		rendasMensais.setValor(valor);
//	}//Renda()
	
	
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
	
	/**
	 *  Retorna uma lista de rendas mensais armazenadas no objeto <code>Renda</code>. 
	 * @return o objeto List.
	 */
	public List<RendaMensal> getRendasMensais() {
		return rendasMensais;
	}

//É necessário este método?	
//	public void setResndasMensais(List<RendaMensal> rendasMensais) {
//		this.rendasMensais = rendasMensais;
//	}
	
	/** Retorna um objeto <code>RendaMensal</code>.
	 * @param data data da rendaMensal
	 * @return <code>RendaMensal</code> com a renda mensal da renda
	 */	
	public RendaMensal obterRendaMensal(Calendar data){
		for(Iterator<RendaMensal> rendaIterator = rendasMensais.iterator();rendaIterator.hasNext();){
			RendaMensal renda = rendaIterator.next();
			if(Converte.calendarToString(renda.getDataRenda()).equals(Converte.calendarToString(renda.getDataRenda())))
				return renda;
		}
		return null;
	}

	/** Insere uma renda mensal.
	 * @param rendaMensal objeto <code>RendaMensal</code> a ser inserido.
	 * @return <code>true</code> se a inserção foi realizada,<code>false</code> se não..
	 */
	public boolean adicionarRendaMensal(RendaMensal rendaMensal) {
		return rendasMensais.add(rendaMensal);
	}
	
//	/** Retornar um <code>Calendar</code> com a data da renda mensal da renda
//	 * @return <code>Calendar</code> com a data da renda mensal da renda
//	 */
//	public Calendar getDataRenda() {
//		return rendasMensais.getDataRenda();
//	}
//
//	/** Define a data da renda mensal da renda
//	 * @param dataRenda <code>Calendar</code> com a nova data da renda mensal da renda
//	 */
//	public void dataRenda(Calendar dataRenda) {
//		this.rendasMensais.setDataRenda(dataRenda);
//	}
	
//	/** Retornar um <code>double</code> com o valor da renda mensal da renda
//	 * @return <code>double</code> com valor da renda mensal da renda
//	 */
//	public double getValorRenda() {
//		return rendasMensais.getValor();
//	}
//
//	/** Define o valor da renda mensal da renda
//	 * @param valorRenda <code>double</code> com o novo valor da renda mensal da renda
//	 */
//	public void setValorRenda(double valorRenda) {
//		this.rendasMensais.setValor(valorRenda);
//	}

	/**
	 * Retorna uma referência em String da descrição da Renda.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Renda [ descricao:" + descricao + " ]" + rendasMensais.toString();
	}
}//class Renda
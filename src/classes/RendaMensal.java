package classes;

import java.util.Calendar;

/**
 * /**
 *  Armazena informações sobre a renda mensal. Herda os atributos da classe Renda.
 *  Adiciona os seguintes atributos: valor do tipo <code>double</code> e data do tipo <code>Calendar</code>.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class RendaMensal {
	private int id;
	private double valor;
	private Calendar dataRenda;
	
	/**
	 * Construtor padrão.
	 */
	public RendaMensal() {
		super();
	}
	
	/**
	 * Construtor sobrecarregado, recebe os seguintes parâmetros:
	 * @param id <code>int</code> da renda associada a renda mensal. 
	 * @param data <code>Calendar</code> com a data da receita.
	 * @param valor <code>double</code> da renda mensal.
	 */
	public RendaMensal(int id, Calendar data, double valor) {
		setId(id);
		setValor(valor);
		setDataRenda(data);
	}

	/**
	 * Construtor sobrecarregado, recebe os seguintes parâmetros:
	 * @param data <code>Calendar</code> com a data da receita.
	 * @param valor <code>double</code> da renda mensal.
	 */
	public RendaMensal(Calendar data, double valor) {
		setValor(valor);
		setDataRenda(data);
	}

	/**
	 * Retorna o valor cadastrado.
	 * @return <code>double</code> valor.
	 */
	public double getValor() {
		return valor;
	}
	
	/**
	 * Atribui o valor recebido por parâmetro como valor do item.
	 * @param valor <code>double</code>
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	/**
	 * Retorna a data da renda.
	 * @return <code>Calendar</code> data.
	 */
	public Calendar getDataRenda() {
		return dataRenda;
	}

	/**
	 * Atribui a data da renda.
	 * @param data <code>Calendar</code>
	 */
	public void setDataRenda(Calendar data) {
		this.dataRenda = data;
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
	 * Retorna uma referência em String da descrição, data e do valor da renda mensal.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Renda Mensal [ " + 
				String.format("Data da Renda: %02d/%02d/%02d, Valor: %.2f", 
						dataRenda.get(Calendar.DAY_OF_MONTH),
						dataRenda.get(Calendar.MONTH) + 1,
						dataRenda.get(Calendar.YEAR), valor) + " ]";
	}
}

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
	 * @param data <code>Calendar</code> com a data da receita.
	 * @param valor <code>double</code> da renda mensal.
	 */
	public RendaMensal(Calendar data, double valor) {
		setValor(valor);
		setData(data);
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
	public Calendar getData() {
		return dataRenda;
	}

	/**
	 * Atribui a data da renda.
	 * @param data <code>Calendar</code>
	 */
	public void setData(Calendar data) {
		this.dataRenda = data;
	}

	/**
	 * Retorna uma referência em String da descrição, data e do valor da renda mensal.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Renda Mensal [ " + 
				String.format("Data da Renda: %02d/%02d/%04d", 
						dataRenda.get(Calendar.DAY_OF_MONTH),
						dataRenda.get(Calendar.MONTH) + 1,
						dataRenda.get(Calendar.YEAR) + 
						String.format(" , Valor: %.2f", valor)) + " ]";
	}
}

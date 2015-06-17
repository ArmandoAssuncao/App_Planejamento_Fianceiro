package classes;

import java.util.Calendar;

/**
 * /**
 *  Armazena informações sobre a renda mensal. Herda os atributos da classe Renda.
 *  Adiciona os seguintes atributos: valor do tipo <code>double</code> e data do tipo <code>Calendar</code>.
 * @author Armando e Richardson
 */
public class RendaMensal extends Renda {
	private double valor;
	private Calendar data;
	
	/**
	 * Construtor padrão.
	 */
	public RendaMensal() {
		super();
	}

	/**
	 * Construtor sobrecarregado, recebe os seguintes parâmetros:
	 * @param codigo <code>int</code> de identificação da renda mensal.
	 * @param data <code>Calendar</code> com a data da receita.
	 * @param valor <code>double</code> da renda mensal.
	 */
	public RendaMensal(int codigo,Calendar data, double valor) {
		setCodigo(codigo);
		setValor(valor);
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
		return data;
	}

	/**
	 * Atribui a data da renda.
	 * @param data <code>Calendar</code>
	 */
	public void setData(Calendar data) {
		this.data = data;
	}

	/**
	 * Retorna uma referência em String do código, descrição e do valor da renda mensal.
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "Renda Mensal [ Código: " + getCodigo() + 
				String.format("Data da Renda: %02d/%02d/%04d", 
						data.get(Calendar.DAY_OF_MONTH),
						data.get(Calendar.MONTH) + 1,
						data.get(Calendar.YEAR) + 
						String.format(" , Valor: %.2f", valor)) + " ]";
	}
}

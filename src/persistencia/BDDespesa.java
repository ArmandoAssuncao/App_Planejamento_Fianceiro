package persistencia;


import java.util.Calendar;

import classes.Categoria;
import classes.Despesa;

/**
 * Classe abstrata para manipular os dados das despesas no banco de dados
 * 
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see BDPlanejamentoFinanceiro
 *
 */
public abstract class BDDespesa extends BDPlanejamentoFinanceiro {

	/**
	 * Insere um objeto Despesa no banco de dados.
	 * 
	 * @param despesa objeto <code>Despesa</code> que será inserido no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>.
	 */
	protected int inserir(Despesa despesa){
		int codigoCategoria  = despesa.getCodigoCategoria();
		int codigoPagamento = despesa.getCodigoPagamento();
		Calendar dataDaDespesa = despesa.getDataDespesa();
		Calendar dataDoPagamento = despesa.getDataPagamento();
		String numeroCheque = despesa.getNumeroCheque();
		int numeroParcelas = despesa.getNumeroParcelas();
		double valorDespesa = despesa.getValorDespesa();
		
		int codigoIdentificador = despesa.getCodigo();
		String descricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(despesa.getDescricao());
		
		String comandoInsercao = "INSERT INTO candidato VALUES";
		
		//Verifica se a categoria onde a despesa será cadastrada existe
		if(Categoria.categoriaExists(codigoCategoria)){ //TODO metodo categoriaexists nao implementado
			int idCategoria = -1;
			//Obtem o id da despesa
			idCategoria = Categoria.getId(codigoCategoria);
			//TODO 
		}else{//Codigo da categoria nao existe.
			
		}
		return 1;
	}//inserir
}//class BDDespesa
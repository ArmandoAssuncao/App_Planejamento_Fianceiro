package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import classes.Despesa;
import funcoes.Converte;
import gui.JanelaMensagem;

/**
 * Classe abstrata para manipular os dados das despesas no banco de dados
 * 
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see PlanejamentoFinanceiroDAO
 *
 */
public class DespesaDAO extends PlanejamentoFinanceiroDAO {

	/**
	 * Insere um objeto Despesa no banco de dados.
	 * 
	 * @param despesa objeto <code>Despesa</code> que será inserido no banco de dados
	 * @param descricaoCategoria utilizado para obter o id da Categoria que será armazenado no banco de dados.
	 * @param descricaoPagamento utilizado para obter o id da FormaPagamento que será armazenado no banco de dados.
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>.
	 * 
	 * @see Despesa
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	public int inserir(Despesa despesa,String descricaoCategoria,String descricaoPagamento){
		try {
			despesa.setIdCategoria(new CategoriaDAO().getId(descricaoCategoria));
			despesa.setIdFormaPagamento(new FormaPagamentoDAO().getId(descricaoPagamento));
			String descricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(despesa.getDescricao());
			String dataDaDespesa = Converte.calendarToString(despesa.getDataDespesa());
			String dataDoPagamento = Converte.calendarToString(despesa.getDataPagamento());
			String numeroCheque = despesa.getNumeroCheque();
			double valorDespesa = despesa.getValorDespesa();
			int numeroParcelas = despesa.getNumeroParcelas();
			
			String comandoInsercao = "INSERT INTO despesa VALUES ";
			
			String comandoSql = comandoInsercao  + String.format("%s, \'%s\', %d, \'%s\', \'%s\', %d, %d, "+valorDespesa +", %d",
					" NEXT VALUE FOR seq_despesa ", descricao,despesa.getIdCategoria(), dataDaDespesa,dataDoPagamento,
					despesa.getIdFormaPagamento(),numeroCheque,numeroParcelas) ;
			
			this.executaUpdate(comandoSql);
			
			//Obtém o ultimo idDespesa gerado na tabela despesa, e adiciona no objeto despesa.
			ResultSet res = this.executaQuery("SELECT MAX(idDespesa) AS ultimoIdGerado FROM despesa");
			res.next();
			int idDespesa = res.getInt("ultimoIdGerado");
			despesa.setIdDespesa(idDespesa);
			res.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		
		return BancoDeDados.RESULTADO_SUCESSO;
	}//inserir

	/**
	 * Verifica se existe uma despesa com a data indicada no banco de dados.
	 * @param idDespesa <code>int</code> com o id da Despesa que está armazenada no banco.
	 * @return <code>true</code> se a despesa existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public boolean exists(int idDespesa) throws SQLException {
		int contagem = 0;
		this.abreConexao();
		
		String comandoSql = "" + "SELECT COUNT(*) AS contagem FROM despesa WHERE idDespesa=" + idDespesa;
		
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		resultadoQuery.next();
		String contagemDespesas = resultadoQuery.getString("contagem");
		if (contagemDespesas != null){
		 contagem = Integer.parseInt(contagemDespesas);
		}
		
		this.fechaConexao();
		return contagem > 0 ? true : false;
	}

	/**
	 * Atualiza os dados da despesa no banco de dados.
	 * @param despesa <code>Despesa</code> que será sobrescrita no banco de dados.
	 * @param id <code>int</code> com o id da despesa no banco de dados.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	public boolean atualizarDados(Despesa despesa, int id){
		try {
			if(!exists(id)){
				JanelaMensagem.mostraMensagemErro(null, "Despesa com id especificado não existente no banco de dados.");
				return false;
			}
			
			String descricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(despesa.getDescricao());
			String dataDaDespesa = Converte.calendarToString(despesa.getDataDespesa());
			String dataDoPagamento = Converte.calendarToString(despesa.getDataPagamento());
			String numeroCheque = despesa.getNumeroCheque();
			double valorDespesa = despesa.getValorDespesa();
			int numeroParcelas = despesa.getNumeroParcelas();
			

			String comandoUpdate = "UPDATE despesa SET ";
			String clausulaWhere = "WHERE idDespesa=" + id;
			
			String comandoSql = comandoUpdate  + String.format("descricao=\'%s\', idCategoria=%d, dataDespesa=\'%s\',"
					+ " dataPagamento=\'%s\', idFormaPagamento=%d, numeroCheque=%d, valor="+valorDespesa+", numeroParcelas=%d %s",""
					+ descricao,despesa.getIdCategoria(), dataDaDespesa,
					dataDoPagamento,despesa.getIdFormaPagamento(),numeroCheque,numeroParcelas,clausulaWhere) ;
			
			this.executaUpdate(comandoSql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return false;
		}

		return true;
	}//atualizarDados()
	
	/**
	 * Exclui uma Despesa no banco de dados.
	 * @param id <code>int</code> com o id da <code>Despesa</code> a ser excluida.
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados 
	 */
	public boolean excluir(int id) throws SQLException{
		this.abreConexao();
		
		String comandoSql = "DELETE FROM despesa WHERE idDespesa="+id;
		
		this.executaUpdate(comandoSql);
		
		this.fechaConexao();
		return true;
	}

	/**
	 * Pesquisa despesa pela descricao
	 * @param id <code>int</code> com o id da Despesa que está armazenada no banco.
	 * @return objeto <code>Despesa</code> com a despesa que tem a descrição especificada, ou <code>null</code>, se não encontrada.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public Despesa pesquisar(int id) throws SQLException{
		Despesa despesa = null;
		String comandoSql = "SELECT * FROM despesa WHERE idDespesa=" + id;
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		try {
			resultadoQuery.next();
			String descricao = resultadoQuery.getString("descricao");
			int idDespesa = resultadoQuery.getInt("idDespesa");
			int idCategoria = resultadoQuery.getInt("idCategoria");
			Calendar dataDespesa = Converte.stringToCalendar(resultadoQuery.getString("dataDespesa"));
			Calendar dataPagamento = Converte.stringToCalendar(resultadoQuery.getString("dataPagamento"));
			int idFormaPagamento = resultadoQuery.getInt("idFormaPagamento");
			String numeroCheque = resultadoQuery.getString("numeroCheque");
			double valor = resultadoQuery.getDouble("valor");
			int numeroDeParcelas = resultadoQuery.getInt("numeroDeParcelas");
				
			despesa = new Despesa(idDespesa, descricao, dataDespesa, dataPagamento, numeroCheque, valor, numeroDeParcelas,idCategoria,idFormaPagamento);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}finally{
			BANCO_DE_DADOS_PF.fechaConexao();
		}
		return despesa;		
	}//pesquisar
	
	/**
	 * Retorna todas as entradas da tabela despesa.
	 * @return {@code List<Despesa>} com todas as despesas da tabela
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public List<Despesa> todasAsDespesas() throws SQLException{
		List<Despesa> despesas = new ArrayList<Despesa>();
		BANCO_DE_DADOS_PF.abreConexao();
		
		String comandoSql = "SELECT * FROM despesa";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		
		try {
			while(resultadoQuery.next()){
				String descricao = resultadoQuery.getString("descricao");
				int idDespesa = resultadoQuery.getInt("idDespesa");
				int idCategoria = resultadoQuery.getInt("idCategoria");
				Calendar dataDespesa = Converte.stringToCalendar(resultadoQuery.getString("dataDespesa"));
				Calendar dataPagamento = Converte.stringToCalendar(resultadoQuery.getString("dataPagamento"));
				int idFormaPagamento = resultadoQuery.getInt("idFormaPagamento");
				String numeroCheque = resultadoQuery.getString("numeroCheque");
				double valor = resultadoQuery.getDouble("valor");
				int numeroDeParcelas = resultadoQuery.getInt("numeroDeParcelas");
				
				despesas.add(new Despesa(idDespesa, descricao, dataDespesa, dataPagamento, numeroCheque, valor, numeroDeParcelas,idCategoria,idFormaPagamento));
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return despesas;
	}
}//class BDDespesa
package persistencia;

import gui.JanelaMensagem;

import java.sql.ResultSet;
import java.sql.SQLException;

import enumeracoes.FormaPagamento;

/**
 * Classe para manipular objetos da classe <code>FormaPagamento</code> no banco de dados.
 *
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see PlanejamentoFinanceiroDAO
 */
public class FormaPagamentoDAO extends PlanejamentoFinanceiroDAO {

	/** Insere um objeto <code>FormaPagamento</code> no banco de dados
	 * @param formaPagamento <code>FormaPagamento</code> que será inserida no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see FormaPagamento
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	public int inserir(FormaPagamento formaPagamento){
		String descricao =  formaPagamento.getFormaPagamento();
		
		String comandoInsercao = "INSERT INTO forma_pagamento VALUES";
		try{
			if(!exists(descricao)){//Verifica se existe uma forma_pagamento com o mesmo nome;
				String comandoSql = comandoInsercao + String.format("(%s, \'%s\');", "NEXT VALUE FOR seq_forma_pagamento", descricao); 
				try {
					this.executaUpdate(comandoSql);
				} catch (SQLException e) {
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
					return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
				}
			}
			else
				return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;// FormaPagamento com descrição duplicada.
		}
		catch(SQLException e){
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		
		return BancoDeDados.RESULTADO_SUCESSO;
	}//inserir
	
	/**
	 * Atualiza os dados da forma_pagamento no banco de dados.
	 * @param formaPagamento <code>FormaPagamento</code> que será sobrescrita no banco de dados.
	 * @param descricao <code>String</code> com a descrição da forma_pagamento no banco de dados.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	/*public boolean atualizarDados(FormaPagamento formaPagamento, String descricao){
		int id = 0;
		try {
			id = getId(descricao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da FormaPagamento Invalida");
			return false;
		}
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(formaPagamento.getFormaPagamento());
		String comandoUpdate = "UPDATE forma_pagamento SET ";
		String clausulaWhere = " WHERE idFormaPagamento=" + id;
		
		String comandoSql = comandoUpdate + String.format("descricao=\'%s\'%s", novaDescricao, clausulaWhere);
		
		try {
			this.executaUpdate(comandoSql);
		} catch (SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return false;
		}
		return true;
	}*/
	
	/**
	 * Exclui uma forma_pagamento no banco de dados.
	 * @param descricao <code>String</code> com a descrição da <code>FormaPagamento</code> a ser excluida. 
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	/*public boolean excluir(String descricao) throws SQLException{
		this.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "DELETE FROM forma_pagamento WHERE descricao=";
		
		this.executaUpdate(comandoSql + String.format("\'%s\'", novaDescricao));
		
		this.fechaConexao();
		
		return true;
	}*/
	
	/**
	 * Verifica se existe uma forma_pagamento com a descrição indicada no banco de dados.
	 * @param descricao <code>String</code> com a descrição da forma_pagamento a ser removida.
	 * @return <code>true</code> se a forma_pagamento existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public boolean exists(String descricao) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "" + "SELECT COUNT(*) AS contagem FROM forma_pagamento WHERE descricao=";
		ResultSet resultadoQuery = this.executaQuery(comandoSql + String.format("\'%s\'", novaDescricao));
		
		resultadoQuery.next();
		String contagemFormaPagamentos = resultadoQuery.getString("contagem");
		if (contagemFormaPagamentos != null){
			contagem = Integer.parseInt(contagemFormaPagamentos);
		}
		
		this.fechaConexao();
		
		return contagem > 0 ? true : false;
	}
	
	/**
	 * Pesquisa forma_pagamento pela descricao
	 * @param descricao <code>String</code> com a descrição da <code>FormaPagamento</code> a ser pesquisada. 
	 * @return {@code List<FormaPagamento>} com as forma_pagamentos que tem na descrição a descrição especificado
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	/*public static List<FormaPagamento> pesquisar(String descricao) throws SQLException{
		List<FormaPagamento> formaPagamentos = new ArrayList<FormaPagamento>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "SELECT * FROM forma_pagamento WHERE descricao LIKE \'%" + novaDescricao + "%\'";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		try {
			//resultSet posiciona o cursor antes da primeira linha, entao o next() abaixo ja o coloca na primeira linha, caso haja
			while(resultadoQuery.next()){
				novaDescricao = resultadoQuery.getString("descricao");
				
				formaPagamentos.add(new FormaPagamento(novaDescricao));
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return formaPagamentos;
	}//pesquisar*/
	
	/**
	 * Retorna todas as entradas da tabela forma_pagamento.
	 * @return {@code List<FormaPagamento>} com todas as forma_pagamentos da tabela
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	/*public static List<FormaPagamento> todasAsFormaPagamentos() throws SQLException{
		List<FormaPagamento> formaPagamentos = new ArrayList<FormaPagamento>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		String comandoSql = "SELECT * FROM forma_pagamento";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		while(resultadoQuery.next()){
			//int idFormaPagamento = resultadoQuery.getInt("idFormaPagamento");
			String descricao = resultadoQuery.getString("descricao");
			
			formaPagamentos.add(new FormaPagamento(descricao));
		}//while
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return formaPagamentos;
	}*/

	/**
	 *   Retorna o id da forma_pagamento no banco de dados
	 * @param descricao <code>String</code> com a descrição da forma_pagamento
	 * @return <code>int</code> com o id da forma_pagamento no banco de dados, caso não encontre retorna <code>0</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public int getId(String descricao) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		String comandoSql = "SELECT idFormaPagamento FROM forma_pagamento WHERE descricao=\'" + descricao + "\'";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		if(resultadoQuery.next())
			id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	}
	
	/**
	 *   Retorna a descrição da forma_pagamento no banco de dados
	 * @param id <code>int</code> com o id da forma_pagamento
	 * @return <code>String</code> com a descrição da forma_pagamento no banco de dados, caso não encontre retorna <code>null</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public String getDescricao(int id) throws SQLException{
		String descricao = null;
		
		this.abreConexao();
		String comandoSql = "SELECT descricao FROM forma_pagamento WHERE idFormaPagamento=" + id;
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		if(resultadoQuery.next())
			descricao = resultadoQuery.getString("descricao");
		
		this.fechaConexao();
		
		return descricao;
	}
}//class BDFormaPagamento
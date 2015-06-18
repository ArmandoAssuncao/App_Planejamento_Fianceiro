package persistencia;

import gui.JanelaMensagem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.FormaPagamento;

/**
 * Classe para manipular objetos da classe <code>FormaPagamento</code> no banco de dados.
 *
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see BDPlanejamentoFinanceiro
 */
public abstract class BDFormaPagamento  extends BDPlanejamentoFinanceiro  {

	/** Insere um objeto <code>FormaPagamento</code> no banco de dados
	 * @param formaPagamento <code>FormaPagamento</code> que será inserido no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see FormaPagamento
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	protected int inserir(FormaPagamento formaPagamento){
		int codigo = formaPagamento.getCodigo();
		String nome =  formaPagamento.getDescricao();
		
		String comandoInsercao = "INSERT INTO formaPagamento VALUES";
		
		if(true){ //TODO pesquisar se  a formaPagamento existe, se existe, nao e possivel cadastrar.
			String comandoSql = comandoInsercao + "()";  //TODO comando sql
			try {
				this.executaUpdate(comandoSql);
			} catch (SQLException e) {
				e.printStackTrace();
				JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
				return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
			}
		}
		//else // FormaPagamento existente. 
			return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;
	}//inserir
	
	/**
	 * Atualiza os dados da formaPagamento no banco de dados.
	 * @param formaPagamento <code>FormaPagamento</code> que será sobrescrita no banco de dados.
	 * @param id <code>int</code> com o id da formaPagamento no banco de dados.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	public boolean atualizarDados(FormaPagamento formaPagamento, int id){
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da FormaPagamento Invalida");
			return false;
		}
		
		String comandoUpdate = "UPDATE formaPagamento SET ";		
		String clausulaWhere = " WHERE idCategoria=" + id;
		String novoNome = formaPagamento.getDescricao();
		
		String comandoSql = comandoUpdate + ""; //TODO comando sql
		try {
			this.executaUpdate(comandoSql);
		} catch (SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Exclui uma formaPagamento no banco de dados.
	 * @param nome <code>String</code> com o nome da <code>FormaPagamento</code> a ser excluida. 
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean excluir(String nome) throws SQLException{
		this.abreConexao();
		
		BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		
		this.executaUpdate("DELETE FROM formaPagamento WHERE nome=\'" + nome + " \'"); //TODO comando sql
		
		this.fechaConexao();
		
		return true;
	}
	
	/**
	 * Verifica se existe uma formaPagamento com o nome indicado no banco de dados.
	 * @param nome <code>String</code> com o nome da formaPagamento a ser removida.
	 * @return <code>true</code> se a formaPagamento existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean exists(String nome) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		nome = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		ResultSet resultadoQuery = this.executaQuery("" +
				"SELECT COUNT(*) AS contagem FROM formaPagamento WHERE nome=\'" + nome + "\'"); //TODO comando sql
		resultadoQuery.next();
		String contagemCategorias = resultadoQuery.getString("contagem");
		if (contagemCategorias != null){
			contagem = Integer.parseInt(contagemCategorias);
		}
		
		this.fechaConexao();
		
		return contagem > 0 ? true : false;
	}
	
	/**
	 * Pesquisa formaPagamento pelo nome
	 * @param nome <code>String</code> com o nome da <code>FormaPagamento</code> a ser pesquisada. 
	 * @return {@code List<FormaPagamento>} com as categorias que tem no nome o nome especificado
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected static List<FormaPagamento> pesquisar(String nome) throws SQLException{
		String descricao;
		int codigo;
		
		List<FormaPagamento> categorias = new ArrayList<FormaPagamento>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		nome = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery("SELECT * FROM formaPagamento WHERE nome LIKE \'%" + nome + "%\'");//TODO comando sql
		
		try {
			//resultSet posiciona o cursor antes da primeira linha, entao o next() abaixo ja o coloca na primeira linha, caso haja
			while(resultadoQuery.next()){
				codigo = Integer.parseInt(resultadoQuery.getString("codigo"));
				descricao = resultadoQuery.getString("descricao");
				
				categorias.add(new FormaPagamento(codigo, descricao));
			}//while
		} catch (NumberFormatException  | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return categorias;
	}//pesquisar

	/**
	 *   Retorna o id da formaPagamento no banco de dados
	 * @param nome <code>String</code> com o nome da formaPagamento
	 * @return <code>int</code> com o id da formaPagamento no banco de dados, caso não encontre retorna <code>0</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected int getId(String nome) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		ResultSet resultadoQuery = this.executaQuery("SELECT idCategoria FROM formaPagamento WHERE nome=\'" + nome + "\'");//TODO comando sql
		
		resultadoQuery.next();
		id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	}

}//class BDFormaPagamento

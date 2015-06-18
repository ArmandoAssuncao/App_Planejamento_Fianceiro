package persistencia;

import gui.JanelaMensagem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Renda;

/**
 * Classe para manipular objetos da classe <code>Renda</code> no banco de dados.
 *
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see BDPlanejamentoFinanceiro
 */
public abstract class BDRenda extends BDPlanejamentoFinanceiro {

	/** Insere um objeto <code>Renda</code> no banco de dados
	 * @param renda <code>Renda</code> que será inserido no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see Renda
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	protected int inserir(Renda renda){
		int codigo = renda.getCodigo();
		String nome =  renda.getDescricao();
		
		String comandoInsercao = "INSERT INTO renda VALUES";
		
		if(true){ //TODO pesquisar se  a renda existe, se existe, nao e possivel cadastrar.
			String comandoSql = comandoInsercao + "()";  //TODO comando sql
			try {
				this.executaUpdate(comandoSql);
			} catch (SQLException e) {
				e.printStackTrace();
				JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
				return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
			}
		}
		//else // Renda existente. 
			return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;
	}//inserir
	
	/**
	 * Atualiza os dados da renda no banco de dados.
	 * @param renda <code>Renda</code> que será sobrescrita no banco de dados.
	 * @param id <code>int</code> com o id da renda no banco de dados.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	public boolean atualizarDados(Renda renda, int id){
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da Renda Invalida");
			return false;
		}
		
		String comandoUpdate = "UPDATE renda SET ";		
		String clausulaWhere = " WHERE idRenda=" + id;
		String novoNome = renda.getDescricao();
		
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
	 * Exclui uma renda no banco de dados.
	 * @param nome <code>String</code> com o nome da <code>Renda</code> a ser excluida. 
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean excluir(String nome) throws SQLException{
		this.abreConexao();
		
		BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		
		this.executaUpdate("DELETE FROM renda WHERE nome=\'" + nome + " \'"); //TODO comando sql
		
		this.fechaConexao();
		
		return true;
	}
	
	/**
	 * Verifica se existe uma renda com o nome indicado no banco de dados.
	 * @param nome <code>String</code> com o nome da renda a ser removida.
	 * @return <code>true</code> se a renda existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean exists(String nome) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		nome = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		ResultSet resultadoQuery = this.executaQuery("" +
				"SELECT COUNT(*) AS contagem FROM renda WHERE nome=\'" + nome + "\'"); //TODO comando sql
		resultadoQuery.next();
		String contagemCategorias = resultadoQuery.getString("contagem");
		if (contagemCategorias != null){
			contagem = Integer.parseInt(contagemCategorias);
		}
		
		this.fechaConexao();
		
		return contagem > 0 ? true : false;
	}
	
	/**
	 * Pesquisa renda pelo nome
	 * @param nome <code>String</code> com o nome da <code>Renda</code> a ser pesquisada. 
	 * @return {@code List<Renda>} com as categorias que tem no nome o nome especificado
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected static List<Renda> pesquisar(String nome) throws SQLException{
		String descricao;
		int codigo;
		
		List<Renda> categorias = new ArrayList<Renda>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		nome = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery("SELECT * FROM renda WHERE nome LIKE \'%" + nome + "%\'");//TODO comando sql
		
		try {
			//resultSet posiciona o cursor antes da primeira linha, entao o next() abaixo ja o coloca na primeira linha, caso haja
			while(resultadoQuery.next()){
				codigo = Integer.parseInt(resultadoQuery.getString("codigo"));
				descricao = resultadoQuery.getString("descricao");
				
				categorias.add(new Renda(codigo, descricao));
			}//while
		} catch (NumberFormatException  | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return categorias;
	}//pesquisar

	/**
	 *   Retorna o id da renda no banco de dados
	 * @param nome <code>String</code> com o nome da renda
	 * @return <code>int</code> com o id da renda no banco de dados, caso não encontre retorna <code>0</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected int getId(String nome) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		ResultSet resultadoQuery = this.executaQuery("SELECT idCategoria FROM renda WHERE nome=\'" + nome + "\'");//TODO comando sql
		
		resultadoQuery.next();
		id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	}

}//class BDRenda

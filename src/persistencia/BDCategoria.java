package persistencia;

import gui.JanelaMensagem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Categoria;

/**
 * Classe para manipular objetos da classe <code>Categoria</code> no bando de dados.
 *
 * @author Richardson William
 * 
 * @see BDPlanejamentoFinanceiro
 */
public abstract class BDCategoria extends BDPlanejamentoFinanceiro {

	/** Insere um objeto <code>Categoria</code> no banco de dados
	 * @param categoria <code>Categoria</code> que será inserido no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see Categoria
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	protected int inserir(Categoria categoria){
		int codigo = categoria.getCodigo();
		String descricao =  categoria.getDescricao();
		
		String comandoInsercao = "INSERT INTO categoria VALUES";
		
		if(true){ //TODO pesquisar se  a categoria existe, se existe, nao e possivel cadastrar.
			String comandoSql = comandoInsercao + "";  //TODO comando sql
			try {
				this.executaUpdate(comandoSql);
			} catch (SQLException e) {
				e.printStackTrace();
				JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
				return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
			}
		}
		//else // Categoria existente. 
			return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;
	}//inserir
	
	/**
	 * Atualiza os dados da categoria no banco de dados.
	 * @param categoria <code>Categoria</code> que será sobrescrita no banco de dados.
	 * @param id <code>int</code> com o id da categoria no banco de dados.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	public boolean atualizarDados(Categoria categoria, int id){
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da Categoria Invalida");
			return false;
		}
		
		String comandoUpdate = "UPDATE categoria SET ";		
		String clausulaWhere = " WHERE idCategoria=" + id;
		String novoNome = categoria.getDescricao();
		
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
	 * Exclui uma categoria no banco de dados.
	 * @param nome <code>String</code> com o nome da <code>Categoria</code> a ser excluida. 
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean excluir(String nome) throws SQLException{
		this.abreConexao();
		
		BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		
		this.executaUpdate("DELETE FROM categoria WHERE descricao=\'" + nome + " \'"); //TODO comando sql
		
		this.fechaConexao();
		
		return true;
	}
	
	/**
	 * Verifica se existe uma categoria com o nome indicado no banco de dados.
	 * @param nome <code>String</code> com o nome da categoria a ser removida.
	 * @return <code>true</code> se a categoria existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean exists(String nome) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		nome = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		ResultSet resultadoQuery = this.executaQuery("" +
				"SELECT COUNT(*) AS contagem FROM categoria WHERE nome=\'" + nome + "\'"); //TODO comando sql
		resultadoQuery.next();
		String contagemCategorias = resultadoQuery.getString("contagem");
		if (contagemCategorias != null){
			contagem = Integer.parseInt(contagemCategorias);
		}
		
		this.fechaConexao();
		
		return contagem > 0 ? true : false;
	}
	
	/**
	 * Pesquisa categoria pelo nome
	 * @param nome <code>String</code> com o nome da <code>Categoria</code> a ser pesquisada. 
	 * @return {@code List<Categoria>} com as categorias que tem no nome o nome especificado
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected static List<Categoria> pesquisar(String nome) throws SQLException{
		String descricao;
		int codigo;
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		nome = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(nome);
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery("SELECT * FROM categoria WHERE nome LIKE \'%" + nome + "%\'");
		
		try {
			//resultSet posiciona o cursor antes da primeira linha, entao o next() abaixo ja o coloca na primeira linha, caso haja
			while(resultadoQuery.next()){
				codigo = Integer.parseInt(resultadoQuery.getString("codigo"));
				descricao = resultadoQuery.getString("descricao");
				
				categorias.add(new Categoria(codigo, descricao));
			}//while
		} catch (NumberFormatException  | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return categorias;
	}//pesquisar
}//class BDCategoria

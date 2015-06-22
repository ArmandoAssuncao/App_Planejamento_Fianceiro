package persistencia;

import gui.JanelaMensagem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Categoria;

/**
 * Classe para manipular objetos da classe <code>Categoria</code> no banco de dados.
 *
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see PlanejamentoFinanceiroDAO
 */
public class CategoriaDAO extends PlanejamentoFinanceiroDAO {

	/** Insere um objeto <code>Categoria</code> no banco de dados
	 * @param categoria <code>Categoria</code> que será inserida no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see Categoria
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	public int inserir(Categoria categoria){
		String descricao =  categoria.getDescricao();
		
		String comandoInsercao = "INSERT INTO categoria VALUES";
		try{
			if(!exists(descricao)){//Verifica se existe uma categoria com o mesmo nome;
				String comandoSql = comandoInsercao + String.format("(%s, \'%s\');", "NEXT VALUE FOR seq_categoria", descricao); 
				try {
					this.executaUpdate(comandoSql);
				} catch (SQLException e) {
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
					return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
				}
			}
			else
				return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;// Categoria com descrição duplicada.
		}
		catch(SQLException e){
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		
		return BancoDeDados.RESULTADO_SUCESSO;
	}//inserir
	
	/**
	 * Atualiza os dados da categoria no banco de dados.
	 * @param categoria <code>Categoria</code> que será sobrescrita no banco de dados.
	 * @param descricao <code>String</code> com a descrição da categoria no banco de dados.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	public boolean atualizarDados(Categoria categoria, String descricao){
		int id = 0;
		try {
			id = getId(descricao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da Categoria Invalida");
			return false;
		}
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(categoria.getDescricao());
		String comandoUpdate = "UPDATE categoria SET ";
		String clausulaWhere = " WHERE idCategoria=" + id;
		
		String comandoSql = comandoUpdate + String.format("descricao=\'%s\'%s", novaDescricao, clausulaWhere);
		
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
	 * @param descricao <code>String</code> com a descrição da <code>Categoria</code> a ser excluida. 
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public boolean excluir(String descricao) throws SQLException{
		this.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "DELETE FROM categoria WHERE descricao=";
		
		this.executaUpdate(comandoSql + String.format("\'%s\'", novaDescricao));
		
		this.fechaConexao();
		
		return true;
	}
	
	/**
	 * Verifica se existe uma categoria com a descrição indicada no banco de dados.
	 * @param descricao <code>String</code> com a descrição da categoria a ser removida.
	 * @return <code>true</code> se a categoria existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public boolean exists(String descricao) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "" + "SELECT COUNT(*) AS contagem FROM categoria WHERE descricao=";
		ResultSet resultadoQuery = this.executaQuery(comandoSql + String.format("\'%s\'", novaDescricao));
		
		resultadoQuery.next();
		String contagemCategorias = resultadoQuery.getString("contagem");
		if (contagemCategorias != null){
			contagem = Integer.parseInt(contagemCategorias);
		}
		
		this.fechaConexao();
		
		return contagem > 0 ? true : false;
	}
	
	/**
	 * Pesquisa categoria pela descricao
	 * @param descricao <code>String</code> com a descrição da <code>Categoria</code> a ser pesquisada. 
	 * @return {@code List<Categoria>} com as categorias que tem na descrição a descrição especificado
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	/*public static List<Categoria> pesquisar(String descricao) throws SQLException{
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "SELECT * FROM categoria WHERE descricao LIKE \'%" + novaDescricao + "%\'";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		try {
			//resultSet posiciona o cursor antes da primeira linha, entao o next() abaixo ja o coloca na primeira linha, caso haja
			while(resultadoQuery.next()){
				novaDescricao = resultadoQuery.getString("descricao");
				
				categorias.add(new Categoria(novaDescricao));
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return categorias;
	}//pesquisar*/
	
	/**
	 * Retorna todas as entradas da tabela categoria.
	 * @return {@code List<Categoria>} com todas as categorias da tabela
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public List<Categoria> todasAsCategorias() throws SQLException{
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		String comandoSql = "SELECT * FROM categoria";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		while(resultadoQuery.next()){
			//int idCategoria = resultadoQuery.getInt("idCategoria");
			String descricao = resultadoQuery.getString("descricao");
			
			categorias.add(new Categoria(descricao));
		}//while
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return categorias;
	}

	/**
	 *   Retorna o id da categoria no banco de dados
	 * @param descricao descrição <code>String</code> com a descrição da categoria
	 * @return <code>int</code> com o id da categoria no banco de dados, caso não encontre retorna <code>0</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public int getId(String descricao) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		String comandoSql = "SELECT idCategoria FROM categoria WHERE descricao=\'" + descricao + "\'";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		if(resultadoQuery.next())
			id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	}
}//class BDCategoria
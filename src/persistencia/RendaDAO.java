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
 * @see PlanejamentoFinanceiroDAO
 */
public abstract class RendaDAO extends PlanejamentoFinanceiroDAO {

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
		String descricao =  renda.getDescricao();
		
		String comandoInsercao = "INSERT INTO renda VALUES";
		try{
			if(!exists(descricao)){//Verifica se existe uma renda com a mesma descrição; //TODO
				String comandoSql = comandoInsercao + String.format("(%s, \'%s\');", "NEXT VALUE FOR seq_renda", descricao);
				try {
					this.executaUpdate(comandoSql);
				} catch (SQLException e) {
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
					return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
				}
			}
			else
				return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;// Renda com descrição duplicada.
		}
		catch(SQLException e){
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		
		return BancoDeDados.RESULTADO_SUCESSO;
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
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(renda.getDescricao());
		String comandoUpdate = "UPDATE renda SET ";
		String clausulaWhere = " WHERE idRenda=" + id;
		
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
	 * Exclui uma renda no banco de dados.
	 * @param descricao <code>String</code> com a descrição da <code>Renda</code> a ser excluida. 
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean excluir(String descricao) throws SQLException{
		this.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "DELETE FROM renda WHERE descricao=";
		
		this.executaUpdate(comandoSql + String.format("\'%s\'", novaDescricao));
		
		this.fechaConexao();
		
		return true;
	}
	
	/**
	 * Verifica se existe uma renda com a descrição indicada no banco de dados.
	 * @param descricao <code>String</code> com a descrição da renda.
	 * @return <code>true</code> se a renda existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean exists(String descricao) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "" + "SELECT COUNT(*) AS contagem FROM renda WHERE descricao=";
		ResultSet resultadoQuery = this.executaQuery(comandoSql + String.format("\'%s\'", novaDescricao));
		
		resultadoQuery.next();
		String contagemRendas = resultadoQuery.getString("contagem");
		if (contagemRendas != null){
			contagem = Integer.parseInt(contagemRendas);
		}
		
		this.fechaConexao();
		
		return contagem > 0 ? true : false;
	}
	
	/**
	 * Pesquisa renda pela descrição
	 * @param descricao <code>String</code> com a descrição da <code>Renda</code> a ser pesquisada. 
	 * @return {@code List<Renda>} com as rendas que tem na descrição a descrição especificada
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected static List<Renda> pesquisar(String descricao) throws SQLException{
		List<Renda> rendas = new ArrayList<Renda>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "SELECT * FROM renda WHERE descricao LIKE \'%" + novaDescricao + "%\'";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		try {
			//resultSet posiciona o cursor antes da primeira linha, entao o next() abaixo ja o coloca na primeira linha, caso haja
			while(resultadoQuery.next()){
				novaDescricao = resultadoQuery.getString("descricao");
				
				rendas.add(new Renda(novaDescricao));
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return rendas;
	}//pesquisar

	/**
	 *   Retorna o id da renda no banco de dados
	 * @param descricao <code>String</code> com a descrição da renda
	 * @return <code>int</code> com o id da renda no banco de dados, caso não encontre retorna <code>0</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected int getId(String descricao) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		String comandoSql = "SELECT idRenda FROM renda WHERE descricao=\'" + descricao + "\'";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		resultadoQuery.next();
		id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	}

}//class BDRenda

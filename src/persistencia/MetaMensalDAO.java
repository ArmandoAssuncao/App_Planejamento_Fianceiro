package persistencia;

import funcoes.Converte;
import gui.JanelaMensagem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import classes.Categoria;
import classes.MetaMensal;

/**
 * Classe para manipular objetos da classe <code>MetaMensal</code> no banco de dados.
 *
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see PlanejamentoFinanceiroDAO
 */
public class MetaMensalDAO extends PlanejamentoFinanceiroDAO {

	/** Insere um objeto <code>MetaMensal</code> no banco de dados
	 * @param metaMensal <code>MetaMensal</code> que será inserida no banco de dados
	 * @param descricaoCategoria <code>String</code> que será associada com a meta mensal no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see Categoria
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	public int inserir(MetaMensal metaMensal, String descricaoCategoria){
		String mesAnoMeta = Converte.calendarToString(metaMensal.getMesAnoMeta());
		double valor = metaMensal.getValor();
		double alerta = metaMensal.getAlerta();
		
		String comandoInsercao = "INSERT INTO meta_mensal VALUES";
		try{
			if(!exists(metaMensal.getMesAnoMeta(), descricaoCategoria)){//Verifica se existe uma meta mensal com a mesma data e descrição de categoria;
				int idCategoria = getId(descricaoCategoria);
				
				String comandoSql = comandoInsercao + "(" + idCategoria + ",\'" + mesAnoMeta + "\'," + valor + "," + alerta + ")";
				try {
					this.executaUpdate(comandoSql);
				} catch (SQLException e) {
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
					return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
				}
			}
			else
				return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;// MetaMensal com a mesma data e categoria.
		}
		catch(SQLException e){
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		
		return BancoDeDados.RESULTADO_SUCESSO;
	}//inserir
	
	/**
	 * Atualiza os dados da metaMensal no banco de dados.
	 * @param novaMetaMensal <code>MetaMensal</code> que será a nova MetaMensal no banco de dados.
	 * @param mesAnoMeta <code>Calendar</code> que será sobrescrita no banco de dados.
	 * @param descricaoCategoria <code>String</code> com a descrição da categoria no banco de dados.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	public boolean atualizarDados(MetaMensal novaMetaMensal, Calendar mesAnoMeta, String descricaoCategoria){
		//verifica se existe a MetaMensal
		try {
			if(exists(mesAnoMeta, descricaoCategoria))
				return false;
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		int id = 0;
		try {
			id = getId(descricaoCategoria);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da Categoria Invalida");
			return false;
		}
		
		String novoMesAnoMeta = Converte.calendarToString(novaMetaMensal.getMesAnoMeta());
		double novoValor = novaMetaMensal.getValor();
		double novoAlerta = novaMetaMensal.getAlerta();
		
		String comandoUpdate = "UPDATE meta_mensal SET ";
		String clausulaWhere = " WHERE idCategoria=" + id + " AND mesAnoMeta=\'" + novoMesAnoMeta + "\'";
		
		String comandoSql = comandoUpdate + "valor=" + novoValor + ", alerta=" + novoAlerta + clausulaWhere;
		
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
	 * Exclui uma metaMensal no banco de dados.
	 * @param mesAnoMeta <code>Calendar</code> com a data da <code>MetaMensal</code> a ser excluida.
	 * @param descricaoCategoria <code>String</code> com a descrição da <code>Categoria</code> da <code>MetaMensal</code> a ser excluida.
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	/*public boolean excluir(Calendar mesAnoMeta, String descricaoCategoria) throws SQLException{
		//verifica se existe a MetaMensal
		if(!exists(mesAnoMeta, descricaoCategoria))
			return false;
		
		int id = 0;
		try {
			id = getId(descricaoCategoria);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da Categoria Invalida");
			return false;
		}
		
		this.abreConexao();
		
		String excluirMesAnoMeta = Converte.calendarToString(mesAnoMeta);
		
		String comandoSql = "DELETE FROM meta_mensal WHERE idCategoria=" + id + " AND mesAnoMeta=\'" + excluirMesAnoMeta + "\'";
		
		this.executaUpdate(comandoSql);
		
		this.fechaConexao();
		
		return true;
	}*/
	
	/**
	 * Verifica se existe uma MetaMensal com a data e descricao indicada no banco de dados.
	 * @param mesAnoMeta <code>Calendar</code> com a data da MetaMensal a ser pesquisa.
	 * @param descricaoCategoria <code>String</code> com a descricao da Categoria associada a MetaMensal a ser pesquisada.
	 * @return <code>true</code> se a categoria existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public boolean exists(Calendar mesAnoMeta, String descricaoCategoria) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		int id = 0;
		try {
			id = getId(descricaoCategoria);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (id <= 0){
			return false;
		}
		
		String data = Converte.calendarToString(mesAnoMeta);
		
		String comandoSql = "" + "SELECT COUNT(*) AS contagem FROM meta_mensal WHERE idCategoria=" + id + " AND mesAnoMeta=\'" + data + "\'";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
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
	 * Retorna todas as entradas da tabela meta_mensal.
	 * @return {@code List<MetaMensal>} com todas as metas mensais da tabela
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public static List<MetaMensal> todasAsMetasMensais() throws SQLException{
		List<MetaMensal> metasMensais = new ArrayList<MetaMensal>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		String comandoSql = "SELECT * FROM meta_mensal";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		while(resultadoQuery.next()){
			//int idCategoria = resultadoQuery.getInt("idCategoria");
			Calendar mesAnoMeta = Converte.stringToCalendar( resultadoQuery.getString("mesAnoMeta") );
			double valor = resultadoQuery.getDouble("valor");
			double alerta = resultadoQuery.getDouble("alerta");
			
			metasMensais.add(new MetaMensal(mesAnoMeta, valor, alerta));
		}//while
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return metasMensais;
	}

	/**
	 *   Retorna o id da categoria associada a MetaMensal no banco de dados
	 * @param descricao <code>String</code> com a descrição da categoria
	 * @return <code>int</code> com o id da categoria no banco de dados, caso não encontre retorna <code>0</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	private int getId(String descricao) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		String comandoSql = "SELECT idCategoria FROM categoria WHERE descricao=\'" + descricao + "\'";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		if(resultadoQuery.next())
			id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	}
}//class MetaMensalDAO

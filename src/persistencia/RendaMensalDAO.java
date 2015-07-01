package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import classes.Renda;
import classes.RendaMensal;
import funcoes.Converte;
import gui.JanelaMensagem;

/**
 * Classe para manipular objetos da classe <code>RendaMensal</code> no banco de dados.
 *
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see PlanejamentoFinanceiroDAO
 */
public class RendaMensalDAO extends PlanejamentoFinanceiroDAO {

	/** Insere um objeto <code>RendaMensal</code> no banco de dados
	 * @param rendaMensal <code>RendaMensal</code> que será inserida no banco de dados
	 * @param descricaoRenda <code>String</code> que será associada com a meta mensal no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see Renda
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	public int inserir(RendaMensal rendaMensal, String descricaoRenda){
		String dataRenda = Converte.calendarToString(rendaMensal.getDataRenda());
		double valor = rendaMensal.getValor();
		
		String comandoInsercao = "INSERT INTO renda_mensal VALUES";
		try{
			if(!exists(rendaMensal.getDataRenda(), descricaoRenda)){//Verifica se existe uma meta mensal com a mesma data e descrição de renda;
				int idRenda = getId(descricaoRenda);

				String comandoSql = comandoInsercao + "(" + idRenda + ",\'" + dataRenda + "\'," + valor + ")";
				try {
					this.executaUpdate(comandoSql);
				} catch (SQLException e) {
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
					return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
				}
			}
			else
				return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;// RendaMensal com a mesma data e renda.
		}
		catch(SQLException e){
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		
		return BancoDeDados.RESULTADO_SUCESSO;
	}//inserir
	
	/**
	 * Atualiza os dados da rendaMensal no banco de dados.
	 * @param novaRendaMensal <code>RendaMensal</code> que será a nova RendaMensal no banco de dados.
	 * @param dataRenda <code>Calendar</code> que será sobrescrita no banco de dados.
	 * @param descricaoRenda <code>String</code> com a descrição da renda no banco de dados.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	public boolean atualizarDados(RendaMensal novaRendaMensal, Calendar dataRenda, String descricaoRenda){
		//verifica se existe a RendaMensal
		try {
			if(!exists(dataRenda, descricaoRenda))
				return false;
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		int id = 0;
		try {
			id = getId(descricaoRenda);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da Renda Invalida");
			return false;
		}
		
		String novaDataRenda = Converte.calendarToString(novaRendaMensal.getDataRenda());
		double novoValor = novaRendaMensal.getValor();
		
		String comandoUpdate = "UPDATE meta_mensal SET ";
		String clausulaWhere = " WHERE idRenda=" + id + " AND dataRenda=\'" + novaDataRenda + "\'";
		
		String comandoSql = comandoUpdate + "valor=" + novoValor + clausulaWhere;
		//String comandoSql = comandoUpdate + "dataRenda=\'" + novaDataRenda + "\', valor=" + novoValor + clausulaWhere;
		
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
	 * Exclui uma rendaMensal no banco de dados.
	 * @param dataRenda <code>Calendar</code> com a data da <code>RendaMensal</code> a ser excluida.
	 * @param descricaoRenda <code>String</code> com a descrição da <code>Renda</code> da <code>RendaMensal</code> a ser excluida.
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	/*public boolean excluir(Calendar dataRenda, String descricaoRenda) throws SQLException{
		//verifica se existe a RendaMensal
		if(!exists(dataRenda, descricaoRenda))
			return false;
		
		int id = 0;
		try {
			id = getId(descricaoRenda);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da Renda Invalida");
			return false;
		}
		
		this.abreConexao();
		
		String excluirDataRenda = Converte.calendarToString(dataRenda);
		
		String comandoSql = "DELETE FROM meta_mensal WHERE idRenda=" + id + " AND dataRenda=\'" + excluirDataRenda + "\'";
		
		this.executaUpdate(comandoSql);
		
		this.fechaConexao();
		
		return true;
	}*/
	
	/**
	 * Verifica se existe uma RendaMensal com a data e descricao indicada no banco de dados.
	 * @param dataRenda <code>Calendar</code> com a data da RendaMensal a ser pesquisa.
	 * @param descricaoRenda <code>String</code> com a descricao da Renda associada a RendaMensal a ser pesquisada.
	 * @return <code>true</code> se a renda existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public boolean exists(Calendar dataRenda, String descricaoRenda) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		int id = 0;
		try {
			id = getId(descricaoRenda);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (id <= 0){
			return false;
		}
		
		String data = Converte.calendarToString(dataRenda);
		
		String comandoSql = "" + "SELECT COUNT(*) AS contagem FROM renda_mensal WHERE idRenda=" + id + " AND dataRenda=\'" + data + "\'";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		resultadoQuery.next();
		String contagemRendas = resultadoQuery.getString("contagem");
		if (contagemRendas != null){
			contagem = Integer.parseInt(contagemRendas);
		}
		
		this.fechaConexao();
		
		return contagem > 0 ? true : false;
	}
	
	/**
	 * Pesquisa renda pela descricao
	 * @param descricao <code>String</code> com a descrição da <code>Renda</code> a ser pesquisada. 
	 * @return {@code List<Renda>} com as rendas que tem na descrição a descrição especificado
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	/*public static List<Renda> pesquisar(String descricao) throws SQLException{
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
	}//pesquisar*/
	
	/**
	 * Retorna todas as entradas da tabela renda_mensal.
	 * @return {@code List<RendaMensal>} com todas as rendas mensais da tabela
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public static List<RendaMensal> todasAsRendasMensais() throws SQLException{
		List<RendaMensal> rendaMensais = new ArrayList<RendaMensal>();
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		String comandoSql = "SELECT * FROM renda_mensal";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		while(resultadoQuery.next()){
			//int idRenda = resultadoQuery.getInt("idRenda");
			Calendar dataRenda = Converte.stringToCalendar( resultadoQuery.getString("dataRenda") );
			double valor = resultadoQuery.getDouble("valor");
			
			rendaMensais.add(new RendaMensal(dataRenda, valor));
		}//while
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return rendaMensais;
	}
	
	/**
	 * Retorna todas as entradas do mês e ano da tabela renda_mensal.
	 * @param mesAno <code>Calendar</code> com o mês e ano selecionado.
	 * @return {@code List<RendaMensal>} com todas as rendas mensais do mês e ano da tabela
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public static List<RendaMensal> rendaMensalDoMesAno(Calendar mesAno) throws SQLException{
		List<RendaMensal> rendasMensal = new ArrayList<RendaMensal>();
		BANCO_DE_DADOS_PF.abreConexao();
		
		String comandoSql = "SELECT * FROM renda_mensal";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		try {
			while(resultadoQuery.next()){
				//int idRenda = resultadoQuery.getInt("idRenda");//TODO
				Calendar dataRenda = Converte.stringToCalendar(resultadoQuery.getString("dataRenda"));
				double valor = resultadoQuery.getDouble("valor");
				
				String mes = String.valueOf(mesAno.get(Calendar.MONTH));
				String ano = String.valueOf(mesAno.get(Calendar.YEAR));
				String mesBD = String.valueOf(dataRenda.get(Calendar.MONTH)+1);
				String anoBD = String.valueOf(dataRenda.get(Calendar.YEAR));
				
				if(mes.equals(mesBD) && ano.equals(anoBD)){
					rendasMensal.add(new RendaMensal(dataRenda, valor));
				}
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return rendasMensal;
	}

	/**
	 *   Retorna o id da renda associada a RendaMensal no banco de dados
	 * @param descricao <code>String</code> com a descrição da renda
	 * @return <code>int</code> com o id da renda no banco de dados, caso não encontre retorna <code>0</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public int getId(String descricao) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		String comandoSql = "SELECT idRenda FROM renda WHERE descricao=\'" + descricao + "\'";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		if(resultadoQuery.next())
			id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	}
	
	public int getId(String descricao,String data) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		/* select * from renda where descricao='renda2' and idrenda = (select idrenda from renda_mensal where datarenda='22/22/2222')*/
		String comandoSql = "SELECT idRenda FROM renda WHERE descricao=\'" + descricao + "\' and idRenda = (select idRenda from renda_mensal where datarenda=\'"+data+"\')";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		if(resultadoQuery.next())
			id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	}
}//class RendaMensalDAO

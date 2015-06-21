package persistencia;


import gui.JanelaMensagem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	 * 
	 * @see Despesa
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	protected int inserir(Despesa despesa,String descricaoCategoria,String descricaoPagamento){
		String descricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(despesa.getDescricao());
		int codigoCategoria = 0;//TODO new Categoria().getId(descricaoCategoria);
		int codigoPagamento = 0;//TODO new Pagamento().getId(descricaoPagamento);
		String dataDaDespesa = calendarToString(despesa.getDataDespesa());
		String dataDoPagamento = calendarToString(despesa.getDataPagamento());
		String numeroCheque = despesa.getNumeroCheque();
		double valorDespesa = despesa.getValorDespesa();
		int numeroParcelas = despesa.getNumeroParcelas();
		
		
		String comandoInsercao = "INSERT INTO despesa VALUES ";
		
		String comandoSql = comandoInsercao  + String.format("%s, \'%s\', %d, \'%s\', \'%s\', %d, %d, "+valorDespesa +", %d",
				" NEXT VALUE FOR seq_despesa ", descricao,codigoCategoria, dataDaDespesa,dataDoPagamento,
				codigoPagamento,numeroCheque,numeroParcelas) ;
		
		try{
			if(!exists(despesa.getDataDespesa())){
					try {
						this.executaUpdate(comandoSql);
					} catch (SQLException e) {
						e.printStackTrace();
						JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
						return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
					}
				}
				else
					return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;
		}catch(SQLException e){
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		return BancoDeDados.RESULTADO_SUCESSO;
	}//inserir

	/**
	 * Verifica se existe uma despesa com a data indicada no banco de dados.
	 * @param dataDespesa <code>Calendar</code> com a data a ser pesquisada.
	 * @return <code>true</code> se a despesa existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean exists(Calendar dataDespesa) throws SQLException {
		int contagem = 0;
		this.abreConexao();
		
		String comandoSql = "" + "SELECT COUNT(*) AS contagem FROM despesa WHERE dataDespesa=";
		ResultSet resultadoQuery = this.executaQuery(comandoSql + String.format("\'%s\'", calendarToString(dataDespesa)));
		
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
		if (id <= 0){
			JanelaMensagem.mostraMensagemErro(null, "ID da Despesa Invalida");
			return false;
		}
		
		String descricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(despesa.getDescricao());
		int codigoCategoria = 0;
		String dataDaDespesa = calendarToString(despesa.getDataDespesa());
		String dataDoPagamento = calendarToString(despesa.getDataPagamento());
		int codigoPagamento = 0;
		String numeroCheque = despesa.getNumeroCheque();
		double valorDespesa = despesa.getValorDespesa();
		int numeroParcelas = despesa.getNumeroParcelas();
		

		String comandoUpdate = "UPDATE despesa SET ";
		String clausulaWhere = "WHERE idDespesa=" + id;
		
		String comandoSql = comandoUpdate  + String.format("descricao=\'%s\', idCategoria=%d, dataDespesa=\'%s\',"
				+ " dataPagamento=\'%s\', idFormaPagamento=%d, numeroCheque=%d, valor="+valorDespesa+", numeroParcelas=%d %s",""
				+ descricao,codigoCategoria, dataDaDespesa,
				dataDoPagamento,codigoPagamento,numeroCheque,numeroParcelas,clausulaWhere) ;
		
		try {
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
	 * @param descricao <code>String</code> com a descrição da <code>Despesa</code> a ser pesquisada. 
	 * @return {@code List<Despesa>} com as despesas que tem descrição especificada
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected List<Despesa> pesquisar(String descricao) throws SQLException{
		List<Despesa> despesas = new ArrayList<Despesa>();
		BANCO_DE_DADOS_PF.abreConexao();
		
		String novaDescricao = BancoDeDados.substituiAspasSimplesPorUmaValidaNoBD(descricao);
		String comandoSql = "SELECT * FROM despesa WHERE descricao LIKE \'%" + novaDescricao + "%\'";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		
		try {
			while(resultadoQuery.next()){
				novaDescricao = resultadoQuery.getString("descricao");
			//	int idDespesa = resultadoQuery.getInt("idDespesa");
			//	int idCategoria = resultadoQuery.getInt("idCategoria");
				Calendar dataDespesa = stringToCalendar(resultadoQuery.getString("dataDespesa"));
				Calendar dataPagamento = stringToCalendar(resultadoQuery.getString("dataPagamento"));
			//	int idFormaPagamento = resultadoQuery.getInt("idFormaPagamento");
				String numeroCheque = resultadoQuery.getString("numeroCheque");
				double valor = resultadoQuery.getDouble("valor");
				int numeroDeParcelas = resultadoQuery.getInt("numeroDeParcelas");
				
				despesas.add(new Despesa(novaDescricao, dataDespesa, dataPagamento, numeroCheque, valor, numeroDeParcelas));
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return despesas;
	}//pesquisar

	/**
	 * Retorna o id de uma Despesa no banco de dados
	 * @param data <code>Calendar</code> com a data a ser pesquisada.
	 * @return <code>int</code> com o id da renda no banco de dados, caso não encontre retorna <code>0</code>
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected int getId(Calendar data) throws SQLException{
		int id = 0;
		
		this.abreConexao();
		String comandoSql = "SELECT idDespesa FROM despesa WHERE dataDespesa=\'" + data + "\'";
		ResultSet resultadoQuery = this.executaQuery(comandoSql);
		
		resultadoQuery.next();
		id = resultadoQuery.getInt(1); //valor da coluna um, unica coluna
		
		this.fechaConexao();
		
		return id;
	} 
	
	/**
	 * Retorna uma representação em <code>String</code> da data.
	 * @param dataPagamento objeto <code>Calendar</code> com uma data.
	 * @return <code>String</code> no formato DD/MM/AAAA representando a data.
	 */
	private String calendarToString(Calendar dataPagamento) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return  sdf.format(dataPagamento);
	}
	
	/**
	 * Retorna uma representação em <code>Calendar</code> da data em <code>String</code>.
	 * @param string objeto <code>String</code> com uma data.
	 * @return <code>Calendar</code> obtida do parâmetro data.
	 */
	private Calendar stringToCalendar(String string) {
//		Calendar c = Calendar.getInstance();
//		int ano = Integer.parseInt(string.substring(0, 2));
//		int mes = Integer.parseInt(string.substring(3,5));
//		int dia = Integer.parseInt(string.substring(6, 10));
//		c.set(ano, mes, dia);
//		return c;
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		try {
			c.setTime(sdf.parse(string));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
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
			//	int idDespesa = resultadoQuery.getInt("idDespesa");
			//	int idCategoria = resultadoQuery.getInt("idCategoria");
				Calendar dataDespesa = stringToCalendar(resultadoQuery.getString("dataDespesa"));
				Calendar dataPagamento = stringToCalendar(resultadoQuery.getString("dataPagamento"));
			//	int idFormaPagamento = resultadoQuery.getInt("idFormaPagamento");
				String numeroCheque = resultadoQuery.getString("numeroCheque");
				double valor = resultadoQuery.getDouble("valor");
				int numeroDeParcelas = resultadoQuery.getInt("numeroDeParcelas");
				
				despesas.add(new Despesa(descricao, dataDespesa, dataPagamento, numeroCheque, valor, numeroDeParcelas));
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return despesas;
	}
}//class BDDespesa
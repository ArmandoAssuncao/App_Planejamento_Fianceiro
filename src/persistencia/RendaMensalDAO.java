package persistencia;

import gui.JanelaMensagem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import classes.Categoria;
import classes.RendaMensal;

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
	 * @param rendaMensal <code>RendaMensal</code> que será inserida no banco de dados.
	 * @param idRenda <code>int</code> com o id da renda associada.
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see Categoria
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	protected int inserir(RendaMensal rendaMensal, int idRenda){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataRendaMensal = sdf.format(rendaMensal.getData().getTime());
		double valor = rendaMensal.getValor();
		
		String comandoInsercao = "INSERT INTO renda_mensal VALUES";
		try{
			if(!exists(rendaMensal.getData(), idRenda)){//Verifica se existe uma rendaMensal com a mesma data e id de Renda;
				String comandoSql = comandoInsercao + "(\'" + dataRendaMensal + "\'," + valor + "," + idRenda + ")";
				try {
					this.executaUpdate(comandoSql);
				} catch (SQLException e) {
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
					return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
				}
			}
			else
				return BancoDeDados.RESULTADO_ERRO_REGISTRO_DUPLICADO;// rendaMensal com data e id duplicada.
		}
		catch(SQLException e){
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		
		return BancoDeDados.RESULTADO_SUCESSO;
	}//inserir
	
	
	/**
	 * Atualiza os dados da renda mensal no banco de dados.
	 * @param rendaMensal <code>RendaMensal</code> que será sobrescrita no banco de dados.
	 * @param idRenda <code>int</code> com o id da renda associada.
	 * @return <code>true</code> se os dados foram atualizados, <code>false</code> em caso constrário.
	 */
	public boolean atualizarDados(RendaMensal rendaMensal, int idRenda){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String novaDataRendaMensal = sdf.format(rendaMensal.getData().getTime());
		double novoValor = rendaMensal.getValor();
		
		String comandoUpdate = "UPDATE renda_mensal SET ";
		String clausulaWhere = " WHERE dataRenda=\'" + novaDataRendaMensal + "\' AND " + "idRenda=" + idRenda;
		
		String comandoSql = comandoUpdate + "dataRenda=\'" + novaDataRendaMensal + "\',valor=" + novoValor + clausulaWhere;
		
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
	 * Exclui uma renda mensal no banco de dados.
	 * @param dataRendaMensal <code>Calendar</code> com a data da <code>RendaMensal</code> a ser excluida.
	 * @param idRenda <code>int</code> com o id da renda associada da <code>RendaMensal</code> a ser excluida.
	 * @return <code>true</code> se os dados foram removidos, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean excluir(Calendar dataRendaMensal, int idRenda) throws SQLException{
		this.abreConexao();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(dataRendaMensal.getTime());
		
		String comandoSql = "DELETE FROM renda_mensal WHERE dataRenda=\'" + data + "\' AND idRenda=" + idRenda;
		
		this.executaUpdate(comandoSql);
		
		this.fechaConexao();
		
		return true;
	}
	
	/**
	 * Verifica se existe uma rendaMensal com a data e id da renda associada indicada no banco de dados.
	 * @param dataRendaMensal <code>Calendar</code> com a data da meta mensal.
	 * @param idRenda <code>int</code> com o id da renda associada.
	 * @return <code>true</code> se a renda mensal existe, <code>false</code> em caso constrário.
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected boolean exists(Calendar dataRendaMensal, int idRenda) throws SQLException{
		int contagem = 0;
		this.abreConexao();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(dataRendaMensal.getTime());
		
		String comandoSql = "" + "SELECT COUNT(*) AS contagem FROM renda_mensal WHERE ";
		ResultSet resultadoQuery = this.executaQuery(comandoSql + String.format("dataRenda=\'%s\' AND idRenda=\'%s\'", data, idRenda));
		
		resultadoQuery.next();
		String contagemCategorias = resultadoQuery.getString("contagem");
		if (contagemCategorias != null){
			contagem = Integer.parseInt(contagemCategorias);
		}
		
		this.fechaConexao();
		
		return contagem > 0 ? true : false;
	}
	
	/**
	 * Pesquisa renda mensal pela data e id da renda associada
	 * @param dataRendaMensal <code>Calendar</code> com a data da <code>RendaMensal</code> a ser pesquisada.
	 * @param idRenda <code>int</code> com o id da renda associada da <code>RendaMensal</code> a ser pesquisada.
	 * @return {@code List<Categoria>} com as categorias que tem na descrição a descrição especificado
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	protected static RendaMensal pesquisar(Calendar dataRendaMensal, int idRenda) throws SQLException{
		RendaMensal rendaMensal = new RendaMensal();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		
		BANCO_DE_DADOS_PF.abreConexao();
		
		String data = sdf.format(dataRendaMensal.getTime());
		
		String comandoSql = "SELECT * FROM renda_mensal WHERE dataRenda=\'" + data + "\' AND idRenda=" + idRenda;
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		try {
			//resultSet posiciona o cursor antes da primeira linha, entao o next() abaixo ja o coloca na primeira linha, caso haja
			while(resultadoQuery.next()){
				try{
					calendar.setTime(sdf.parse(resultadoQuery.getString("dataRenda")));
				}
				catch(ParseException e){
					e.printStackTrace();
				}
				rendaMensal.setData(calendar);
				rendaMensal.setValor(resultadoQuery.getDouble("valor"));
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return rendaMensal;
	}//pesquisar
}

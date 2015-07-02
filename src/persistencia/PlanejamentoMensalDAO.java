package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import funcoes.Converte;
import gui.JanelaMensagem;

/**
 * Classe para manipular a tabela planejamento_mensal no banco de dados.
 *
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see PlanejamentoFinanceiroDAO
 */
public class PlanejamentoMensalDAO  extends PlanejamentoFinanceiroDAO {
	
	/** Insere dados na tabela planejamento_mensal no banco de dados
	 * @param idDespesa <code>int</code> com o id da despesa a ser inserido na tabela planejamento_mensal no banco de dados
	 * @param mesAno <code>Calendar</code> com a data da despesa a ser inserida na tabela planejamento_mensal no banco de dados
	 * @return <code>int</code> com o resultado da inserção. Este valor é uma constante definida na classe <code>BancoDeDados</code>
	 * 
	 * @see Calendar
	 * @see BancoDeDados#RESULTADO_SUCESSO
	 * @see BancoDeDados#RESULTADO_ERRO_REGISTRO_DUPLICADO
	 * @see BancoDeDados#RESULTADO_ERRO_BANCO_DADOS
	 * @see BancoDeDados#RESULTADO_ERRO_DESCONHECIDO
	 */
	public int inserir(int idDespesa, Calendar mesAno){
		
		String comandoInsercao = "INSERT INTO planejamento_mensal VALUES";
		String comandoSql = comandoInsercao + String.format("(%s, \'%s\');", "NEXT VALUE FOR seq_planejamento_mensal_despesa", Converte.calendarToString(mesAno)); 
		try {
			this.executaUpdate(comandoSql);
		} catch (SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
			return BancoDeDados.RESULTADO_ERRO_BANCO_DADOS;
		}
		
		return BancoDeDados.RESULTADO_SUCESSO;
	}//inserir
	
	/**
	 * Retorna todos os ids do mês e ano da tabela despesa.
	 * @param mesAno <code>Calendar</code> com o mês e ano selecionado.
	 * @return {@code List<Integer>} com todas os ids do mês e ano da tabela
	 * @throws SQLException possível erro gerado por má configuração do banco de dados
	 */
	public static List<Integer> idsDespesasDoMesAno(Calendar mesAno) throws SQLException{
		List<Integer> idsDespesa = new ArrayList<Integer>();
		BANCO_DE_DADOS_PF.abreConexao();
		
		String comandoSql = "SELECT * FROM planejamento_mensal";
		ResultSet resultadoQuery = BANCO_DE_DADOS_PF.executaQuery(comandoSql);
		
		try {
			while(resultadoQuery.next()){
				int idDespesa = resultadoQuery.getInt("idDespesa");
				Calendar mesAnoPlanejamento = Converte.stringToCalendar(resultadoQuery.getString("mesAnoPlanejamento"));
				
				String mes = String.valueOf(mesAno.get(Calendar.MONTH));
				String ano = String.valueOf(mesAno.get(Calendar.YEAR));
				String mesBD = String.valueOf(mesAnoPlanejamento.get(Calendar.MONTH));
				String anoBD = String.valueOf(mesAnoPlanejamento.get(Calendar.YEAR));
				
				if(mes.equals(mesBD) && ano.equals(anoBD)){
					idsDespesa.add(new Integer(idDespesa));
				}
				
			}//while
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
		}
		
		BANCO_DE_DADOS_PF.fechaConexao();
		return idsDespesa;
	}
}

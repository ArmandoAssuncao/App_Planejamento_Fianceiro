package eventos.painelGraficos;

import gui.graficos.JanelaBalancoDespesas;
import gui.graficos.JanelaBalancoMensal;
import gui.painelGraficos.PainelGraficos;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import persistencia.CategoriaDAO;
import persistencia.DespesaDAO;
import persistencia.MetaMensalDAO;
import persistencia.RendaMensalDAO;
import classes.Categoria;
import classes.Despesa;
import classes.MetaMensal;
import classes.RendaMensal;

/**
 * Trata os eventos do painel de gráficos
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see ActionListener
 */
public class TEPainelGraficos implements ActionListener{
	private PainelGraficos painelGraficos;
	Window janelaPai;
	
	/**
	 * Cria uma instância do tratador de eventos do <code>PainelGraficos</code>
	 * 
	 * @param painelGraficos painel que será manipulado.
	 * @param janelaPai janela onde o painelDespesas está contido.
	 */
	public TEPainelGraficos(PainelGraficos painelGraficos, Window janelaPai) {
		this.janelaPai = janelaPai;
		this.painelGraficos = painelGraficos;
	}
	
	/**
	 * Trata o evento de mudança do estado
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == painelGraficos.getBotaoBalancoTotal()){
			new JanelaBalancoMensal();
		}
		else if(event.getSource() == painelGraficos.getBotaoBalancoDespesa()){
			new JanelaBalancoDespesas();
		}
		
		//
		else if(event.getSource() == painelGraficos.getBotaoGraficoPizzaTotal()){
			List<Despesa> despesas = new ArrayList<Despesa>();
			List<RendaMensal> rendasMensal = new ArrayList<RendaMensal>();
			int idInvestimento;
			Double valorTotalInvestimentos = 0.0;
			Double valorTotalGastos = 0.0;
			Double valorTotalRendas = 0.0;
			Double valorSaldoAtual;
			
			Calendar mesAno = Calendar.getInstance();
			System.out.println(mesAno.get(Calendar.MONTH));
			
			try {
				idInvestimento = new CategoriaDAO().getId("Investimentos");
				despesas.addAll(DespesaDAO.despesasDoMesAno(mesAno));
				rendasMensal.addAll(RendaMensalDAO.rendaMensalDoMesAno(mesAno));
			} catch (SQLException e1) {
				e1.printStackTrace();
				return;
			}
			
			for(int indice = 0; indice < despesas.size(); indice++){
				Despesa despesa = despesas.get(indice);
				if(despesa.getIdCategoria() == idInvestimento){
					valorTotalInvestimentos += despesa.getValorDespesa();
				}
				else{
					valorTotalGastos += despesa.getValorDespesa();
				}
			}
			
			for(int indice = 0; indice < rendasMensal.size(); indice++){
				valorTotalRendas += rendasMensal.get(indice).getValor();
			}
			
			valorSaldoAtual = valorTotalRendas - valorTotalGastos - valorTotalInvestimentos;
			
			painelGraficos.adicionarGrafico("Balanço Mensal", new String[]{"Investimentos", "Gastos", "Receitas", "Saldo Atual"},
					new Double[]{valorTotalInvestimentos, valorTotalGastos, valorTotalRendas, valorSaldoAtual}, 1);
		}
		
		//
		else if(event.getSource() == painelGraficos.getBotaoGraficoBarraCategoria()){
			List<Categoria> arrayCategorias = new ArrayList<>();
			List<Despesa> arrayDespesas = new ArrayList<Despesa>();
			
			Calendar mesAno = Calendar.getInstance();
			
			try {
				arrayCategorias.addAll(CategoriaDAO.todasAsCategorias());
				arrayDespesas.addAll(DespesaDAO.despesasDoMesAno(mesAno));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String dados[] = new String[arrayCategorias.size()];
			Double valores[] = new Double[arrayCategorias.size()];
			Arrays.fill(valores, 0.0);
			
			//nome de cada categoria
			for(int i = 0; i < arrayCategorias.size(); i++){
				dados[i] = arrayCategorias.get(i).getDescricao();
			}
			
			//valor total de cada categoria
			for(int j = 0; j < arrayDespesas.size(); j++){
				valores[arrayDespesas.get(j).getIdCategoria() -1] += arrayDespesas.get(j).getValorDespesa();
			}
			
			painelGraficos.adicionarGrafico("Gastos das Categorias", dados, valores, 2);
		}
		
		//
		else if(event.getSource() == painelGraficos.getBotaoGraficoLinhaMetaMensal()){
			List<Categoria> arrayCategorias = new ArrayList<>();
			List<Despesa> arrayDespesas = new ArrayList<Despesa>();
			MetaMensal MetaMensal = null;
			int idCategoria = 0;
			
			String categoriaSelecionada = painelGraficos.getValorjComboBoxCategorias();
			
			Calendar mesAno = Calendar.getInstance();
			
			try {
				idCategoria = new CategoriaDAO().getId(categoriaSelecionada);
				MetaMensal = MetaMensalDAO.pesquisaMetaMensal(idCategoria, mesAno);
				arrayDespesas.addAll(DespesaDAO.despesasDoMesAno(mesAno));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//valor total de cada categoria
			Double valorTotalCategoria = 0.0;
			for(int j = 0; j < arrayDespesas.size(); j++){
				
				valorTotalCategoria += arrayDespesas.get(j).getValorDespesa();
			}
			
			System.out.println(idCategoria);
			painelGraficos.adicionarGrafico("gastos com " + categoriaSelecionada, new String[]{"campo1", "campo2"}, new Double[]{0.0, valorTotalCategoria}, 3);
		}
		
		//
		else if(event.getSource() == painelGraficos.getBotaoGraficoPizzaFormaPagamento()){
			List<Despesa> despesas = new ArrayList<Despesa>();
			Double valorTotalAVista = 0.0;
			Double valorTotalCartao = 0.0;
			Double valorTotalCheque = 0.0;
			Double valorTotalPrazo = 0.0;
			
			Calendar mesAno = Calendar.getInstance();
			System.out.println(mesAno.get(Calendar.MONTH));
			
			try {
				despesas.addAll(DespesaDAO.despesasDoMesAno(mesAno));
			} catch (SQLException e1) {
				e1.printStackTrace();
				return;
			}
			
			for(int indice = 0; indice < despesas.size(); indice++){
				Despesa despesa = despesas.get(indice);
				if(despesa.getIdFormaPagamento() == 1){
					valorTotalAVista += despesa.getValorDespesa();
				}
				else if(despesa.getIdFormaPagamento() == 2){
					valorTotalCartao += despesa.getValorDespesa();
				}
				else if(despesa.getIdFormaPagamento() == 3){
					valorTotalCheque += despesa.getValorDespesa();
				}
				else if(despesa.getIdFormaPagamento() == 4){
					valorTotalPrazo += despesa.getValorDespesa();
				}
			}
			
			painelGraficos.adicionarGrafico("Balanço Mensal da Forma de Pagamento", new String[]{"À Vista", "Cartão de Credito", "Cheque", "Prazo"},
					new Double[]{valorTotalAVista, valorTotalCartao, valorTotalCheque, valorTotalPrazo}, 1);
		}
	}
	
}
package eventos.painelGraficos;

import gui.graficos.JanelaBalancoDespesas;
import gui.graficos.JanelaBalancoMensal;
import gui.painelGraficos.PainelGraficos;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
		
		else if(event.getSource() == painelGraficos.getBotaoGraficoBarraCategoria()){
			painelGraficos.adicionarGrafico("titulo Barra", new String[]{"campo1", "campo2"}, new Double[]{1.0,2.0}, 2);
		}
		
		else if(event.getSource() == painelGraficos.getBotaoGraficoLinhaMetaMensal()){
			List<Categoria> arrayCategorias = new ArrayList<>();
			List<MetaMensal> arrayMetasMensal = new ArrayList<>();
			
			Calendar mesAno = Calendar.getInstance();
			System.out.println(mesAno.get(Calendar.MONTH));
			
			try {
				arrayMetasMensal.addAll(MetaMensalDAO.metaMensalDoMesAno(mesAno));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String dados[] = new String[arrayMetasMensal.size()];
			Double valores[] = new Double[arrayMetasMensal.size()];
			
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			
			try {
				for(int i = 0; i < arrayMetasMensal.size(); i++){
					int idCategoria = arrayMetasMensal.get(i).getId(); 
					
					dados[i] = categoriaDAO.getDescricao(idCategoria);
					valores[i] = arrayMetasMensal.get(i).getValor();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			painelGraficos.adicionarGrafico("Gráfico das Categorias", dados, valores, 2);
		}
		
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
			
			painelGraficos.adicionarGrafico("Balanço Mensal por Forma de Pagamento", new String[]{"À Vista", "Cartão de Credito", "Cheque", "Prazo"},
					new Double[]{valorTotalAVista, valorTotalCartao, valorTotalCheque, valorTotalPrazo}, 1);
		}
	}
	
}
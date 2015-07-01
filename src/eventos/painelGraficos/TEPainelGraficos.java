package eventos.painelGraficos;

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
import persistencia.RendaMensalDAO;
import classes.Despesa;
import classes.RendaMensal;

public class TEPainelGraficos implements ActionListener{
	private PainelGraficos painelGraficos;
	Window janelaPai;
	
	public TEPainelGraficos(PainelGraficos painelGraficos, Window janelaPai) {
		this.janelaPai = janelaPai;
		this.painelGraficos = painelGraficos;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == painelGraficos.getBotaoBalancoTotal()){
		}
		else if(event.getSource() == painelGraficos.getBotaoBalancoDespesa()){
		}
		else if(event.getSource() == painelGraficos.getBotaoGraficoPizzaTotal()){
			List<Despesa> despesas = new ArrayList<Despesa>();
			List<RendaMensal> rendasMensal = new ArrayList<RendaMensal>();
			int idInvestimento;
			int valorTotalInvestimentos = 0;
			int valorTotalGastos = 0;
			int valorTotalRendas = 0;
			int valorSaldoAtual;
			
			Calendar mesAno = Calendar.getInstance();
			mesAno.set(2015, 6, 1); //TODO Pegar o mes e ano do ComboBox
			System.out.println(mesAno.get(Calendar.MONTH));
			
			try {
				idInvestimento = new CategoriaDAO().getId("Investimentos");
				RendaMensalDAO.rendaMensalDoMesAno(mesAno);
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
					new int[]{valorTotalInvestimentos, valorTotalGastos, valorTotalRendas, valorSaldoAtual}, 1);
		}
		else if(event.getSource() == painelGraficos.getBotaoGraficoBarraCategoria()){
			painelGraficos.adicionarGrafico("titulo Barra", new String[]{"campo1", "campo2"}, new int[]{1,2}, 2);
		}
		else if(event.getSource() == painelGraficos.getBotaoGraficoLinhaMetaMensal()){
			painelGraficos.adicionarGrafico("titulo Linha", new String[]{"campo1", "campo2"}, new int[]{1,2}, 3);
		}
		else if(event.getSource() == painelGraficos.getBotaoGraficoPizzaFormaPagamento()){
			painelGraficos.adicionarGrafico("titulo Pizza forma pagamento", new String[]{"campo1", "campo2"}, new int[]{1,2}, 1);
		}
	}
	
}
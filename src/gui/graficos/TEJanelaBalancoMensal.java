package gui.graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import classes.Categoria;
import classes.Despesa;
import classes.MetaMensal;
import classes.Renda;
import classes.RendaMensal;
import funcoes.Converte;
import persistencia.CategoriaDAO;
import persistencia.DespesaDAO;
import persistencia.RendaMensalDAO;

public class TEJanelaBalancoMensal  implements ActionListener {
	JanelaBalancoMensal janelaBalancoMensal;
	

	public TEJanelaBalancoMensal(JanelaBalancoMensal janelaBalancoMensal) {
		this.janelaBalancoMensal = janelaBalancoMensal;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == janelaBalancoMensal.getjComboBoxMes()){
			Calendar mesAno = Converte.stringToCalendar(janelaBalancoMensal.getTextoJComboBoxMes());
			
			List<Renda> rendas = new ArrayList<>();
			List<RendaMensal> rendasMensal = new ArrayList<>();
			List<Categoria> categorias = new ArrayList<>();
			List<MetaMensal> metasMensal = new ArrayList<>();
			List<Despesa> despesas = new ArrayList<>();
			List<Despesa> despesasInvestimentos = new ArrayList<>();
			
			String tituloReceita = "Receitas";
			String tituloGastos = "Gastos";
			
			int idInvestimento;
			double valorTotalInvestimentos = 0;
			double valorTotalGastos = 0;
			double valorTotalRendas = 0;
			double valorSaldoAtual;

			
			try {
				rendasMensal.addAll(RendaMensalDAO.rendaMensalDoMesAno(mesAno));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
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
					despesasInvestimentos.add(despesa);
				}
				else{
					valorTotalGastos += despesa.getValorDespesa();
					despesas.add(despesa);
				}
			}
			
			for(int indice = 0; indice < rendasMensal.size(); indice++){
				valorTotalRendas += rendasMensal.get(indice).getValor();
			}
			
			valorSaldoAtual = valorTotalRendas - valorTotalGastos - valorTotalInvestimentos;
			
			
			
			//RendaMensalDAO.rendaMensalDoMesAno(data);
			
		}
	}

}

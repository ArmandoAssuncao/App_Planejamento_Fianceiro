package gui.graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import classes.Despesa;
import funcoes.Converte;
import persistencia.DespesaDAO;

public class TEJanelaBalancoDespesas  implements ActionListener {
	JanelaBalancoDespesas janelaBalancoDespesas;
	

	public TEJanelaBalancoDespesas(JanelaBalancoDespesas janelaBalancoDespesas) {
		this.janelaBalancoDespesas= janelaBalancoDespesas;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == janelaBalancoDespesas.getjComboBoxMes()){
			janelaBalancoDespesas.limpaTabela();
			
			
			Calendar mesAno = Converte.stringToCalendar(janelaBalancoDespesas.getTextoJComboBoxMes());
			
			List<Despesa> arrayDespesas = new ArrayList<>(500);
			List<Despesa> arrayDespesasAVista = new ArrayList<>();
			List<Despesa> arrayDespesasPrazo = new ArrayList<>();
			List<Despesa> arrayDespesasCheque = new ArrayList<>();
			List<Despesa> arrayDespesasCartao = new ArrayList<>();
			
			double valorTotalDespesasAVista = 0;
			double valorTotalDespesasPrazo = 0;
			double valorTotalDespesasCheque = 0;
			double valorTotalDespesasCartao = 0;
			
			
			//Obtem as despesas
			try {
				arrayDespesas.addAll(DespesaDAO.despesasDoMesAno(mesAno));
			} catch (SQLException e1) {
				e1.printStackTrace();
				return;
			}
			
			for(int indice = 0; indice < arrayDespesas.size(); indice++){
				Despesa despesa = arrayDespesas.get(indice);
				if(despesa.getIdFormaPagamento() == 1)
					arrayDespesasAVista.add(despesa);
				else if(despesa.getIdFormaPagamento() == 2)
					arrayDespesasCartao.add(despesa);
				else if(despesa.getIdFormaPagamento() == 3)
					arrayDespesasCheque.add(despesa);
				else if(despesa.getIdFormaPagamento() == 4)
					arrayDespesasPrazo.add(despesa);
			}
			
			for(int indice = 0; indice < arrayDespesasAVista.size(); indice++){
				valorTotalDespesasAVista += arrayDespesasAVista.get(indice).getValorDespesa();
			}
			for(int indice = 0; indice < arrayDespesasCartao.size(); indice++){
				valorTotalDespesasCartao += arrayDespesasCartao.get(indice).getValorDespesa();
			}
			for(int indice = 0; indice < arrayDespesasCheque.size(); indice++){
				valorTotalDespesasCheque += arrayDespesasCheque.get(indice).getValorDespesa();
			}
			for(int indice = 0; indice < arrayDespesasPrazo.size(); indice++){
				valorTotalDespesasPrazo += arrayDespesasPrazo.get(indice).getValorDespesa();
			}
			
			String aVista[] = new String[]{"Pagamento À vista:", "R$ " + valorTotalDespesasAVista};
			String cartao[] = new String[]{"Cartão de credito:", "R$ " + valorTotalDespesasCartao};
			String cheque[] = new String[]{"Cheque", "R$ " + valorTotalDespesasCheque};
			String prazo[] = new String[]{"A Prazo:", "R$ " + valorTotalDespesasPrazo};
			
			janelaBalancoDespesas.adicionarLinhaVaziaTabela(60);
			janelaBalancoDespesas.adicionarDadosTabela(aVista);
			janelaBalancoDespesas.adicionarDadosTabela(cartao);
			janelaBalancoDespesas.adicionarDadosTabela(cheque);
			janelaBalancoDespesas.adicionarDadosTabela(prazo);
			
			//Adiciona pagamento A vista
			janelaBalancoDespesas.adicionarLinhaVaziaTabela(60);
			janelaBalancoDespesas.adicionarTituloTabela("A Vista", 1);
			janelaBalancoDespesas.adicionarDadosTabela(new String[]{"Descrição", "Valor", "Data", "Data Pagamento", "Nº de Parcelas", "Nº do Cheque"});
			for(int indice = 0; indice < arrayDespesasAVista.size(); indice++){
				String descricao = arrayDespesasAVista.get(indice).getDescricao();
				double valor = arrayDespesasAVista.get(indice).getValorDespesa();
				String data = Converte.calendarToString(arrayDespesasAVista.get(indice).getDataDespesa());
				String dataPagamento = Converte.calendarToString(arrayDespesasAVista.get(indice).getDataPagamento());
				int numeroParcelas = arrayDespesasAVista.get(indice).getNumeroParcelas();
				String numeroCheque = arrayDespesasAVista.get(indice).getNumeroCheque();
				janelaBalancoDespesas.adicionarDadosTabela(new String[]{descricao, "R$ " + valor, data, dataPagamento, "" + numeroParcelas, numeroCheque});
			}
			
			//Adiciona pagamento cartao de credito
			janelaBalancoDespesas.adicionarLinhaVaziaTabela(60);
			janelaBalancoDespesas.adicionarTituloTabela("Cartão de Crédito", 1);
			janelaBalancoDespesas.adicionarDadosTabela(new String[]{"Descrição", "Valor", "Data", "Data Pagamento", "Nº de Parcelas", "Nº do Cheque"});
			for(int indice = 0; indice < arrayDespesasCartao.size(); indice++){
				String descricao = arrayDespesasCartao.get(indice).getDescricao();
				double valor = arrayDespesasCartao.get(indice).getValorDespesa();
				String data = Converte.calendarToString(arrayDespesasCartao.get(indice).getDataDespesa());
				String dataPagamento = Converte.calendarToString(arrayDespesasCartao.get(indice).getDataPagamento());
				int numeroParcelas = arrayDespesasCartao.get(indice).getNumeroParcelas();
				String numeroCheque = arrayDespesasCartao.get(indice).getNumeroCheque();
				janelaBalancoDespesas.adicionarDadosTabela(new String[]{descricao, "R$ " + valor, data, dataPagamento, "" + numeroParcelas, numeroCheque});
			}
			
			//Adiciona pagamento cheque
			janelaBalancoDespesas.adicionarLinhaVaziaTabela(60);
			janelaBalancoDespesas.adicionarTituloTabela("Cheque", 1);
			//janelaBalancoMensal.adicionarLinhaVaziaTabela(20);
			janelaBalancoDespesas.adicionarDadosTabela(new String[]{"Descrição", "Valor", "Data", "Data Pagamento", "Nº de Parcelas", "Nº do Cheque"});
			for(int indice = 0; indice < arrayDespesasCheque.size(); indice++){
				String descricao = arrayDespesasCheque.get(indice).getDescricao();
				double valor = arrayDespesasCheque.get(indice).getValorDespesa();
				String data = Converte.calendarToString(arrayDespesasCheque.get(indice).getDataDespesa());
				String dataPagamento = Converte.calendarToString(arrayDespesasCheque.get(indice).getDataPagamento());
				int numeroParcelas = arrayDespesasCheque.get(indice).getNumeroParcelas();
				String numeroCheque = arrayDespesasCheque.get(indice).getNumeroCheque();
				janelaBalancoDespesas.adicionarDadosTabela(new String[]{descricao, "R$ " + valor, data, dataPagamento, "" + numeroParcelas, numeroCheque});
			}
			
			//Adiciona pagamento prazo
			janelaBalancoDespesas.adicionarLinhaVaziaTabela(60);
			janelaBalancoDespesas.adicionarTituloTabela("Cheque", 1);
			//janelaBalancoMensal.adicionarLinhaVaziaTabela(20);
			janelaBalancoDespesas.adicionarDadosTabela(new String[]{"Descrição", "Valor", "Data", "Data Pagamento", "Nº de Parcelas", "Nº do Cheque"});
			for(int indice = 0; indice < arrayDespesasPrazo.size(); indice++){
				String descricao = arrayDespesasPrazo.get(indice).getDescricao();
				double valor = arrayDespesasPrazo.get(indice).getValorDespesa();
				String data = Converte.calendarToString(arrayDespesasPrazo.get(indice).getDataDespesa());
				String dataPagamento = Converte.calendarToString(arrayDespesasPrazo.get(indice).getDataPagamento());
				int numeroParcelas = arrayDespesasPrazo.get(indice).getNumeroParcelas();
				String numeroCheque = arrayDespesasPrazo.get(indice).getNumeroCheque();
				janelaBalancoDespesas.adicionarDadosTabela(new String[]{descricao, "R$ " + valor, data, dataPagamento, "" + numeroParcelas, numeroCheque});
			}
		}
	}

}

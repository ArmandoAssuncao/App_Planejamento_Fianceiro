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
import persistencia.MetaMensalDAO;
import persistencia.RendaDAO;
import persistencia.RendaMensalDAO;

public class TEJanelaBalancoMensal  implements ActionListener {
	JanelaBalancoMensal janelaBalancoMensal;
	

	public TEJanelaBalancoMensal(JanelaBalancoMensal janelaBalancoMensal) {
		this.janelaBalancoMensal = janelaBalancoMensal;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == janelaBalancoMensal.getjComboBoxMes()){
			janelaBalancoMensal.limpaTabela();
			
			
			Calendar mesAno = Converte.stringToCalendar(janelaBalancoMensal.getTextoJComboBoxMes());
			
			List<Renda> arrayRendas = new ArrayList<>();
			List<RendaMensal> arrayRendasMensal = new ArrayList<>();
			List<Categoria> arrayCategorias = new ArrayList<>();
			List<MetaMensal> arrayMetasMensal = new ArrayList<>();
			List<Despesa> arrayDespesas = new ArrayList<>(500);
			List<Despesa> arrayDespesasInvestimentos = new ArrayList<>();
			List<Despesa> arrayDespesasGastos = new ArrayList<>();
			
			int idInvestimento;
			double valorTotalInvestimentos = 0;
			double valorTotalGastos = 0;
			double valorTotalRendas = 0;
			double valorSaldoAtual;

			//Obtem as rendas e rendas mensais
			try {
				arrayRendasMensal.addAll(RendaMensalDAO.rendaMensalDoMesAno(mesAno));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			RendaDAO rendaDAO= new RendaDAO();
			try {
				for(int indice = 0; indice < arrayRendasMensal.size(); indice++){
					String descricao = rendaDAO.getDescricao(arrayRendasMensal.get(indice).getId());
					arrayRendas.add(new Renda(descricao));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//Obtem as categorias e metas mensais
			try {
				arrayMetasMensal.addAll(MetaMensalDAO.metaMensalDoMesAno(mesAno));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			try {
				for(int indice = 0; indice < arrayMetasMensal.size(); indice++){
					String descricao = categoriaDAO.getDescricao(arrayMetasMensal.get(indice).getId());
					arrayCategorias.add(new Categoria(descricao));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			//Obtem as despesas
			try {
				idInvestimento = new CategoriaDAO().getId("Investimentos");
				arrayDespesas.addAll(DespesaDAO.despesasDoMesAno(mesAno));
			} catch (SQLException e1) {
				e1.printStackTrace();
				return;
			}
			
			for(int indice = 0; indice < arrayDespesas.size(); indice++){
				Despesa despesa = arrayDespesas.get(indice);
				if(despesa.getIdCategoria() == idInvestimento){
					valorTotalInvestimentos += despesa.getValorDespesa();
					arrayDespesasInvestimentos.add(despesa);
				}
				else{
					valorTotalGastos += despesa.getValorDespesa();
					arrayDespesasGastos.add(despesa);
				}
			}
			
			for(int indice = 0; indice < arrayRendasMensal.size(); indice++){
				valorTotalRendas += arrayRendasMensal.get(indice).getValor();
			}
			
			valorSaldoAtual = valorTotalRendas - valorTotalGastos - valorTotalInvestimentos;

			System.out.println(valorTotalGastos);
			System.out.println(valorTotalInvestimentos);
			System.out.println(valorTotalRendas);
			
			String rendas[] = new String[]{"Renda total:", "R$ " + valorTotalRendas};
			String saldo[] = new String[]{"SaldoAtual", "R$ " + valorSaldoAtual};
			String gastos[] = new String[]{"Gastos", "R$ " + valorTotalGastos};
			String investimentos[] = new String[]{"Investimentos", "R$ " + valorTotalInvestimentos};
			
			janelaBalancoMensal.adicionarLinhaVaziaTabela(60);
			janelaBalancoMensal.adicionarDadosTabela(rendas);
			janelaBalancoMensal.adicionarDadosTabela(gastos);
			janelaBalancoMensal.adicionarDadosTabela(investimentos);
			janelaBalancoMensal.adicionarDadosTabela(saldo);
			
			//Adiciona as rendas
			janelaBalancoMensal.adicionarLinhaVaziaTabela(60);
			janelaBalancoMensal.adicionarTituloTabela("Receitas", 1);
			janelaBalancoMensal.adicionarDadosTabela(new String[]{"Descrição", "Valor", "Data"});
			for(int indice = 0; indice < arrayRendasMensal.size(); indice++){
				String descricao = arrayRendas.get(indice).getDescricao();
				double valor = arrayRendasMensal.get(indice).getValor();
				String data = Converte.calendarToString(arrayRendasMensal.get(indice).getDataRenda());
				janelaBalancoMensal.adicionarDadosTabela(new String[]{descricao, "R$ " + valor, data});
			}
			
			//Adiciona os Investimentos
			janelaBalancoMensal.adicionarLinhaVaziaTabela(60);
			janelaBalancoMensal.adicionarTituloTabela("Investimentos", 1);
			janelaBalancoMensal.adicionarDadosTabela(new String[]{"Descrição", "Valor", "Data", "Data Pagamento", "Nº de Parcelas", "Nº do Cheque"});
			for(int indice = 0; indice < arrayDespesasInvestimentos.size(); indice++){
				String descricao = arrayDespesasInvestimentos.get(indice).getDescricao();
				double valor = arrayDespesasInvestimentos.get(indice).getValorDespesa();
				String data = Converte.calendarToString(arrayDespesasInvestimentos.get(indice).getDataDespesa());
				String dataPagamento = Converte.calendarToString(arrayDespesasInvestimentos.get(indice).getDataPagamento());
				int numeroParcelas = arrayDespesasInvestimentos.get(indice).getNumeroParcelas();
				String numeroCheque = arrayDespesasInvestimentos.get(indice).getNumeroCheque();
				janelaBalancoMensal.adicionarDadosTabela(new String[]{descricao, "R$ " + valor, data, dataPagamento, "" + numeroParcelas, numeroCheque});
			}
			
			//Adiciona as Despesas
			janelaBalancoMensal.adicionarLinhaVaziaTabela(60);
			janelaBalancoMensal.adicionarTituloTabela("Gastos", 1);
			//janelaBalancoMensal.adicionarLinhaVaziaTabela(20);
			janelaBalancoMensal.adicionarDadosTabela(new String[]{"Descrição", "Valor", "Data", "Data Pagamento", "Nº de Parcelas", "Nº do Cheque"});
			for(int indice = 0; indice < arrayDespesasGastos.size(); indice++){
				String descricao = arrayDespesasGastos.get(indice).getDescricao();
				double valor = arrayDespesasGastos.get(indice).getValorDespesa();
				String data = Converte.calendarToString(arrayDespesasGastos.get(indice).getDataDespesa());
				String dataPagamento = Converte.calendarToString(arrayDespesasGastos.get(indice).getDataPagamento());
				int numeroParcelas = arrayDespesasGastos.get(indice).getNumeroParcelas();
				String numeroCheque = arrayDespesasGastos.get(indice).getNumeroCheque();
				janelaBalancoMensal.adicionarDadosTabela(new String[]{descricao, "R$ " + valor, data, dataPagamento, "" + numeroParcelas, numeroCheque});
			}
		}
	}

}

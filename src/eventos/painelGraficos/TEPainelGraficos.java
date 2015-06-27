package eventos.painelGraficos;

import gui.painelGraficos.PainelGraficos;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEPainelGraficos implements ActionListener{
	private PainelGraficos painelGraficos;
	Window janelaPai;
	
	public TEPainelGraficos(PainelGraficos painelGraficos, Window janelaPai) {
		this.janelaPai = janelaPai;
		this.painelGraficos = painelGraficos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == painelGraficos.getBotaoBalancoTotal()){
		}
		else if(e.getSource() == painelGraficos.getBotaoBalancoDespesa()){
		}
		else if(e.getSource() == painelGraficos.getBotaoGraficoPizzaTotal()){
		}
		else if(e.getSource() == painelGraficos.getBotaoGraficoBarraCategoria()){
		}
		else if(e.getSource() == painelGraficos.getBotaoGraficoLinhaMetaMensal()){
		}
		else if(e.getSource() == painelGraficos.getBotaoGraficoPizzaFormaPagamento()){
		}
	}
	
}
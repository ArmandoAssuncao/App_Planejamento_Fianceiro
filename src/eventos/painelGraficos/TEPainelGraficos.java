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
			painelGraficos.adicionarGrafico("titulo Pizza", new String[]{"campo1", "campo2"}, new int[]{1,2}, 1);
		}
		else if(e.getSource() == painelGraficos.getBotaoGraficoBarraCategoria()){
			painelGraficos.adicionarGrafico("titulo Barra", new String[]{"campo1", "campo2"}, new int[]{1,2}, 2);
		}
		else if(e.getSource() == painelGraficos.getBotaoGraficoLinhaMetaMensal()){
			painelGraficos.adicionarGrafico("titulo Linha", new String[]{"campo1", "campo2"}, new int[]{1,2}, 3);
		}
		else if(e.getSource() == painelGraficos.getBotaoGraficoPizzaFormaPagamento()){
			painelGraficos.adicionarGrafico("titulo Pizza forma pagamento", new String[]{"campo1", "campo2"}, new int[]{1,2}, 1);
		}
	}
	
}
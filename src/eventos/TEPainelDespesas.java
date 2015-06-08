package eventos;

import gui.Categoria.JanelaCriarCategoria;
import gui.painelDespesas.PainelDespesas;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEPainelDespesas implements ActionListener{
	private PainelDespesas painelDespesas;
	Window janelaPai;
	
	public TEPainelDespesas(PainelDespesas painelDespesas, Window janelaPai) {
		this.janelaPai = janelaPai;
		this.painelDespesas = painelDespesas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == painelDespesas.getBotaoNovaCategoria()){
			System.out.println(e.getSource());
			new JanelaCriarCategoria(janelaPai);
		}
		else if(e.getSource() == painelDespesas.getBotaoAdicionarDespesa()){
			System.out.println("botao adicionar despesa");
		}
	}
	
}

package eventos;

import gui.Categoria.JanelaCriarCategoria;
import gui.painelDespesas.PainelDespesas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEPainelDespesas implements ActionListener{
	
	private PainelDespesas painelDespesas;
	

	public TEPainelDespesas(PainelDespesas painelDespesas) {
		super();
		this.painelDespesas = painelDespesas;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == painelDespesas.getBotaoNovaCategoria()){
			System.out.println(arg0.getSource());
			new JanelaCriarCategoria();
		}
		else if(arg0.getSource() == painelDespesas.getBotaoAdicionarDespesa()){
			System.out.println("botao adicionar despesa");
		}
	}
	
}

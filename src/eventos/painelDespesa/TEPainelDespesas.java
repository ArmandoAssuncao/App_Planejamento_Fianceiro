package eventos.painelDespesa;

import gui.categoria.JanelaCriarCategoria;
import gui.categoria.JanelaEditarCategoria;
import gui.categoria.JanelaRemoverCategoria;
import gui.despesa.JanelaCriarDespesa;
import gui.painelDespesas.IgPainelDespesas;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEPainelDespesas implements ActionListener{
	private IgPainelDespesas painelDespesas;
	Window janelaPai;
	
	public TEPainelDespesas(IgPainelDespesas painelDespesas, Window janelaPai) {
		this.janelaPai = janelaPai;
		this.painelDespesas = painelDespesas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == painelDespesas.getBotaoNovaCategoria()){
			new JanelaCriarCategoria(painelDespesas);
		}
		else if(e.getSource() == painelDespesas.getBotaoExcluirCategoria()){
			if(painelDespesas.getAbasCategoria().getNumeroDeAbas() != 0)
				new JanelaRemoverCategoria(painelDespesas.getAbasCategoria());
		}
		else if(e.getSource() == painelDespesas.getBotaoEditarCategoria()){
			if(painelDespesas.getAbasCategoria().getNumeroDeAbas() != 0)
				new JanelaEditarCategoria(painelDespesas);
		}
		else if(e.getSource() == painelDespesas.getBotaoAdicionarDespesa()){
			if(painelDespesas.getAbasCategoria().getNumeroDeAbas() != 0){
				new JanelaCriarDespesa(painelDespesas.getAbasCategoria());
				//painelDespesas.atualizaPainelTitulo();
			}
				
		}
	}
	
}

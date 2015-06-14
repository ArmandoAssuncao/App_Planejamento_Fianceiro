package eventos.painelDespesa;

import gui.categoria.JanelaCriarCategoria;
import gui.categoria.JanelaEditarCategoria;
import gui.categoria.NAO_USADA_JanelaRemoverCategoria;
import gui.despesa.JanelaCriarDespesa;
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
			new JanelaCriarCategoria(painelDespesas.getAbasCategoria());
		}
		else if(e.getSource() == painelDespesas.getBotaoExcluirCategoria()){
			painelDespesas.getAbasCategoria().removerCategoria();
		}
		else if(e.getSource() == painelDespesas.getBotaoEditarCategoria()){
			new JanelaEditarCategoria(painelDespesas.getAbasCategoria());
		}
		else if(e.getSource() == painelDespesas.getBotaoAdicionarDespesa()){
			new JanelaCriarDespesa(painelDespesas.getAbasCategoria());
		}
	}
	
}

package eventos.categoria;

import gui.categoria.NAO_USADA_JanelaRemoverCategoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NAO_USADA_TEJanelaRemoverCategoria implements ActionListener{
	private NAO_USADA_JanelaRemoverCategoria janelaRemoverCategoria;
	
	public NAO_USADA_TEJanelaRemoverCategoria(NAO_USADA_JanelaRemoverCategoria janelaRemoverCategoria) {
		this.janelaRemoverCategoria = janelaRemoverCategoria;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == janelaRemoverCategoria.getBotaoRemover()){
			//janelaRemoverCategoria.removerCategoria();
		}
		else if(e.getSource() == janelaRemoverCategoria.getBotaoCancelar()){
			janelaRemoverCategoria.finalizaJanelaCategoria();
		}
		
	}
}

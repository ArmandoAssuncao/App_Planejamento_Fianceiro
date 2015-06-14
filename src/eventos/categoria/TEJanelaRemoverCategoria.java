package eventos.categoria;

import gui.categoria.JanelaRemoverCategoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEJanelaRemoverCategoria implements ActionListener{
	private JanelaRemoverCategoria janelaRemoverCategoria;
	
	public TEJanelaRemoverCategoria(JanelaRemoverCategoria janelaRemoverCategoria) {
		this.janelaRemoverCategoria = janelaRemoverCategoria;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == janelaRemoverCategoria.getBotaoRemover()){
			janelaRemoverCategoria.removerCategoria();
		}
		else if(e.getSource() == janelaRemoverCategoria.getBotaoCancelar()){
			janelaRemoverCategoria.finalizaJanelaCategoria();
		}
		
	}
}

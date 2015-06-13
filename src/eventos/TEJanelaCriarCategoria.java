package eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Categoria.JanelaCriarCategoria;

public class TEJanelaCriarCategoria  implements ActionListener{
	private JanelaCriarCategoria janelaCriarCategoria;
	
	public TEJanelaCriarCategoria(JanelaCriarCategoria janelaCriarCategoria) {
		this.janelaCriarCategoria = janelaCriarCategoria;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == janelaCriarCategoria.getBotaoCriar()){
			janelaCriarCategoria.criarCategoria();
		}
		else if(e.getSource() == janelaCriarCategoria.getBotaoCancelar()){
			janelaCriarCategoria.finalizaJanelaCategoria();
		}
		
	}
	
}

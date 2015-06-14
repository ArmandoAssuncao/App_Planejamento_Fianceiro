package eventos;


import gui.Categoria.JanelaEditarCategoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEJanelaEditarCategoria  implements ActionListener{
	private JanelaEditarCategoria janelaEditarCategoria;
	
	public TEJanelaEditarCategoria(JanelaEditarCategoria janelaEditarCategoria) {
		this.janelaEditarCategoria = janelaEditarCategoria;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == janelaEditarCategoria.getBotaoEditar()){
			janelaEditarCategoria.editarCategoria();
		}
		else if(e.getSource() == janelaEditarCategoria.getBotaoCancelar()){
			janelaEditarCategoria.finalizaJanelaCategoria();
		}
		
	}
	
}

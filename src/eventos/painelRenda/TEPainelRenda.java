package eventos.painelRenda;

import gui.painelRenda.IgPainelRenda;
import gui.renda.JanelaCriarRenda;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trata os eventos do painel renda
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see ActionListener
 */
public class TEPainelRenda implements ActionListener {
	private IgPainelRenda painelRenda;
	Window janelaPai;
	
	/**
	 * Cria uma instância do tratador de eventos do <code>IgPainelRenda</code>
	 * 
	 * @param painelRenda painel que será manipulado.
	 * @param janelaPai janela onde o painelDespesas está contido.
	 */
	public TEPainelRenda(IgPainelRenda painelRenda, Window janelaPai) {
		this.painelRenda = painelRenda;
		this.janelaPai = janelaPai;
	}
	
	/**
	 * Trata o evento de mudança do estado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == painelRenda.getBotaoAddRenda())
			new JanelaCriarRenda(painelRenda);
		else if(e.getSource() == painelRenda.getBotaoExcluirRenda())
			System.out.println("excluir");
		else System.out.println("editar ");
		
	}

}

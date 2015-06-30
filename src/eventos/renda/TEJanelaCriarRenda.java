package eventos.renda;

<<<<<<< HEAD
=======
import gui.categoria.JanelaCriarCategoria;
import gui.painelDespesas.IgPainelDespesas;
>>>>>>> 80426265dc0b1f451f6e4db0d3c809650bb99aa8
import gui.painelRenda.IgPainelRenda;
import gui.renda.JanelaCriarRenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

<<<<<<< HEAD
import classes.Renda;

=======
>>>>>>> 80426265dc0b1f451f6e4db0d3c809650bb99aa8
/**
 * Classe para tratar os eventos da janela Criar Renda.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see ActionListener
 */
public class TEJanelaCriarRenda implements ActionListener {

	
	private JanelaCriarRenda janelaCriarRenda;
	private IgPainelRenda igPainelRenda;
	
	/**
	 * Cria uma instância do tratador de eventos da janela.
	 * 
	 * @param janelaCriarRenda janela de onde vem os eventos.
	 * @param igPainelRenda painel interno da janela //TODO está certo?
<<<<<<< HEAD
	 * @wbp.parser.entryPoint
=======
>>>>>>> 80426265dc0b1f451f6e4db0d3c809650bb99aa8
	 */
	public TEJanelaCriarRenda(JanelaCriarRenda janelaCriarRenda, IgPainelRenda igPainelRenda) {
		this.janelaCriarRenda = janelaCriarRenda;
		this.igPainelRenda = igPainelRenda;
	}
	
	/**
	 * Trata os eventos de clique da janela.
<<<<<<< HEAD
	 * 
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == janelaCriarRenda.getBotaoCriar()){
			if(janelaCriarRenda.validaCampos()){
				Renda renda = janelaCriarRenda.retornaRenda();
				 
				
				
				igPainelRenda.criarRenda(renda,janelaCriarRenda.getDataJDateChooser().getCalendar());
				janelaCriarRenda.finalizaJanelaRenda();
			}//if
		}//if
		
		else if(e.getSource() == janelaCriarRenda.getBotaoCancelar()){
			janelaCriarRenda.finalizaJanelaRenda();
		}
	}//actionPerformed
=======
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

>>>>>>> 80426265dc0b1f451f6e4db0d3c809650bb99aa8
}

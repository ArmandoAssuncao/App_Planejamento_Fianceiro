package eventos.despesa;

import gui.despesa.JanelaCriarDespesa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEJanelaCriarDespesa implements ActionListener{
	private JanelaCriarDespesa janelaCriarDespesa;
	
	public TEJanelaCriarDespesa(JanelaCriarDespesa janelaCriarDespesa) {
		this.janelaCriarDespesa = janelaCriarDespesa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == janelaCriarDespesa.getBotaoCriar()){
			janelaCriarDespesa.criarDespesa();
		}
		else if(e.getSource() == janelaCriarDespesa.getBotaoCancelar()){
			janelaCriarDespesa.finalizaJanelaDespesa();
		}
	}
}

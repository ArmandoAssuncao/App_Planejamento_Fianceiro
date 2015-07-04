package gui.painelRenda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

/**
 * Painel de título da janela <code>IgPainelRenda</code>.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see IgPainelRenda
 */
public class PainelTituloPainelRenda extends JPanel {
	private JLabel rendaLabel;
	private JPanel panel;
	private JPanel sulPanel;
	private JLabel valorQtdRendasLabel;
	private JLabel valorTotalRendasLabel;
	private JLabel mediaDeRendaLabel;
	private JLabel alertaLabel;

	/**
	 * Construtor padrão.
	 */
	public PainelTituloPainelRenda() {
		setSize(750, 140);
		setPreferredSize(new Dimension(750,140));
		setLayout(new BorderLayout(0, 0));
		
		rendaLabel = new JLabel("Renda Mensal");
		rendaLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		rendaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(rendaLabel, BorderLayout.NORTH);
		
		JPanel centroPanel = new JPanel();
		add(centroPanel, BorderLayout.CENTER);
		centroPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(7, 7, 736, 59);
		centroPanel.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][]", "[][]"));
		
		valorQtdRendasLabel = new JLabel("<dynamic>");
		valorQtdRendasLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorQtdRendasLabel, "cell 1 1");
		
		JLabel qtdRendasLabel = new JLabel("rendas mensal cadastradas");
		panel.add(qtdRendasLabel, "cell 2 1");
		
		valorTotalRendasLabel = new JLabel("<dynamic>");
		valorTotalRendasLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorTotalRendasLabel, "cell 7 1");
		
		JLabel totalRendaasLabel = new JLabel("de renda neste mês");
		panel.add(totalRendaasLabel, "cell 8 1");
		
		mediaDeRendaLabel = new JLabel("<dynamic>");
		mediaDeRendaLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(mediaDeRendaLabel, "cell 13 1");
		
		JLabel metaLabel = new JLabel("de média de renda ");
		panel.add(metaLabel, "cell 14 1");
		
		sulPanel  = new JPanel();
		add(sulPanel,BorderLayout.SOUTH);
		
		alertaLabel = new JLabel("<dynamic>");
		sulPanel.add(alertaLabel);
		
		setVisible(true);
	}//construtor
	
	/**
	 * Atualiza o painel título de renda.
	 * @param qtdRendas quantidade de rendas cadastradas
	 * @param somaRendas soma de todas as rendas
	 */
	public void atualizarPainel(int qtdRendas, double somaRendas){
		valorQtdRendasLabel.setText(Integer.toString(qtdRendas));
		valorTotalRendasLabel.setText("R$ " + String.format("%.2f", somaRendas));
		mediaDeRendaLabel.setText("R$ " + String.format("%.2f", somaRendas/qtdRendas));
		
	}//atualizarPainel
}//class PainelTituloPainelRenda

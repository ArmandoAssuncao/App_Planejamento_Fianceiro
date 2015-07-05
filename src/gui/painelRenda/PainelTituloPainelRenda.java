package gui.painelRenda;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private JLabel valorQtdRendasLabel;
	private JLabel valorTotalRendasLabel;
	private JLabel mediaDeRendaLabel;

	/**
	 * Construtor padrão.
	 */
	public PainelTituloPainelRenda() {
		setPreferredSize(new Dimension(750,138));
		setLayout(new BorderLayout(0, 0));
		
		rendaLabel = new JLabel("Renda Mensal");
		rendaLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rendaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(rendaLabel, BorderLayout.NORTH);
		
		JPanel centroPanel = new JPanel();
		add(centroPanel, BorderLayout.CENTER);
		centroPanel.setLayout(new BorderLayout(0, 0));
		centroPanel.setBackground(new Color(205, 205, 205));//TODO COR
		
		panel = new JPanel();
		panel.setBounds(7, 7, 736, 59);
		centroPanel.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][]", "[][]"));
		panel.setBackground(new Color(205, 205, 205));//TODO COR
		
		Font fontValores = new Font("Tahoma", Font.PLAIN, 18);
		Font fontLabels = new Font("Tahoma", Font.PLAIN, 12);
		
		valorQtdRendasLabel = new JLabel("<>");
		valorQtdRendasLabel.setFont(fontValores);
		panel.add(valorQtdRendasLabel, "cell 1 1");
		
		JLabel qtdRendasLabel = new JLabel("rendas mensal cadastradas");
		qtdRendasLabel.setFont(fontLabels);
		panel.add(qtdRendasLabel, "cell 2 1");
		
		valorTotalRendasLabel = new JLabel("<>");
		valorTotalRendasLabel.setFont(fontValores);
		panel.add(valorTotalRendasLabel, "cell 12 1");
		
		JLabel totalRendaasLabel = new JLabel("de renda     ");
		totalRendaasLabel.setFont(fontLabels);
		panel.add(totalRendaasLabel, "cell 13 1");
		
		mediaDeRendaLabel = new JLabel("<>");
		mediaDeRendaLabel.setFont(fontValores);
		//panel.add(mediaDeRendaLabel, "cell 23 1");
		
		JLabel metaLabel = new JLabel("de média de renda ");
		metaLabel.setFont(fontLabels);
		//panel.add(metaLabel, "cell 24 1");
		
		setBackground(new Color(205, 205, 205));//TODO COR
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
		
		double mediaRenda;
		if(qtdRendas !=0)
			mediaRenda = somaRendas/qtdRendas;
		else
			mediaRenda = 0;
		
		mediaDeRendaLabel.setText("R$ " + String.format("%.2f", mediaRenda));
		
	}//atualizarPainel
}//class PainelTituloPainelRenda

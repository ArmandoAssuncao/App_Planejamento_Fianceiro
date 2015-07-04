package gui.painelGraficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Painel de título da janela <code>PainelGraficos</code>.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see PainelGraficos
 */
public class PainelTituloPainelGraficos extends JPanel {
	private JLabel tituloLabel;
	private JPanel panel;
	private JLabel valorQtdRendasLabel;
	private JLabel valorTotalRendasLabel;
	private JLabel mediaDeRendaLabel;

	/**
	 * Construtor padrão.
	 */
	public PainelTituloPainelGraficos() {
		setPreferredSize(new Dimension(750,130));
		
		setBorder(new LineBorder(Color.RED)); //TODO debug, apagar
		
		setLayout(new BorderLayout(0, 0));
		
		tituloLabel = new JLabel("Relatório");
		tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(tituloLabel, BorderLayout.NORTH);
		
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
		
		setVisible(true);
	}//construtor
	
	/**
	 * Atualiza o painel título de gráficoss
	 */
	public void atualizarPainel(){
		
		
	}//atualizarPainel

}//class PainelTituloPainelGraficos 

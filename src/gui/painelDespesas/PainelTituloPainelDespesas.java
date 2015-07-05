package gui.painelDespesas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;

/**
 * Painel de título da janela <code>IgPainelDespesas</code>.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see IgPainelDespesas
 */
public class PainelTituloPainelDespesas extends JPanel {
	private JLabel nomeCategoriaLabel;
	private JProgressBar metaProgressBar;
	private JPanel alertaPanel;
	private JPanel panel;
	private JLabel valorQtdDespesasLabel;
	private JLabel valorTotalDespesasLabel;
	private JLabel gastoPrevistoLabel;
	private JLabel alertaLabel;

	/**
	 * Construtor padrão.
	 */
	public PainelTituloPainelDespesas() {
		setPreferredSize(new Dimension(750,140));
		setLayout(new BorderLayout(0, 0));
		
		metaProgressBar = new JProgressBar();
		metaProgressBar.setStringPainted(true);
		add(metaProgressBar, BorderLayout.SOUTH);
		
		nomeCategoriaLabel = new JLabel("<dynamic>");
		nomeCategoriaLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		nomeCategoriaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(nomeCategoriaLabel, BorderLayout.NORTH);
		
		JPanel centroPanel = new JPanel();
		add(centroPanel, BorderLayout.CENTER);
		centroPanel.setLayout(new BorderLayout(0, 0));
		centroPanel.setBackground(new Color(205, 205, 205));//TODO COR
		
		alertaPanel = new JPanel();
		centroPanel.add(alertaPanel, BorderLayout.SOUTH);
		alertaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		alertaPanel.setBackground(new Color(205, 205, 205));//TODO COR
		
		alertaLabel = new JLabel("<dynamic>");
		alertaPanel.add(alertaLabel);
		
		panel = new JPanel();
		centroPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][]", "[][]"));
		panel.setBackground(new Color(205, 205, 205));//TODO COR
		
		valorQtdDespesasLabel = new JLabel("<dynamic>");
		valorQtdDespesasLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorQtdDespesasLabel, "cell 1 1");
		
		JLabel qtdDespesasLabel = new JLabel("despesas cadastradas");
		panel.add(qtdDespesasLabel, "cell 2 1");
		
		valorTotalDespesasLabel = new JLabel("<dynamic>");
		valorTotalDespesasLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorTotalDespesasLabel, "cell 7 1");
		
		JLabel totalDespesasLabel = new JLabel("total das despesas");
		panel.add(totalDespesasLabel, "cell 8 1");
		
		gastoPrevistoLabel = new JLabel("<dynamic>");
		gastoPrevistoLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(gastoPrevistoLabel, "cell 13 1");
		
		JLabel metaLabel = new JLabel("de gasto previsto");
		panel.add(metaLabel, "cell 14 1");
		
		setBackground(new Color(205, 205, 205));//TODO COR
		setVisible(true);
	}//construtor
	
	/**
	 * Atualiza o painel de despesas.
	 * @param nomeCategoria nome da categoria
	 * @param qtdDespesas quantidade de despesas cadastradas
	 * @param somaDespesas soma de todas as despesas
	 * @param metaPorcentagem meta em porcentagem de gastos definida pelo usuário 
	 * @param valorMetaDinheiro valor estimado de gastos em dinheiro
	 */
	public void atualizarPainel(String nomeCategoria,int qtdDespesas, double somaDespesas, double metaPorcentagem,
			double valorMetaDinheiro){
		//TODO debug: System.out.printf("\n%s %d %f %f %f",nomeCategoria,qtdDespesas,somaDespesas,metaPorcentagem,valorMetaDinheiro );
		nomeCategoriaLabel.setText(nomeCategoria);
		valorQtdDespesasLabel.setText(Integer.toString(qtdDespesas));
		valorTotalDespesasLabel.setText("R$ " + String.format("%.2f", somaDespesas));
		gastoPrevistoLabel.setText("R$ " + String.format("%.2f", valorMetaDinheiro));
		
		double porcentagemGasta = somaDespesas/valorMetaDinheiro*100;
		
		/*Define a cor da barra de progresso, entre verde, amarelo e vermelho. E exibe uma
		 * mensagem de alerta, se necessário. 
		 */
		 if (porcentagemGasta >= 100){
				metaProgressBar.setForeground(Color.RED);
				alertaLabel.setText("Você ultrapassou a meta de gastos em 100%");
		}else if(porcentagemGasta > metaPorcentagem){
			metaProgressBar.setForeground(new Color(204, 204, 0)); //Amarelo Escuro
			alertaLabel.setText("Você ultrapassou a margem de segurança de gastos");	
		}else{
			metaProgressBar.setForeground(new Color(0,132,232)); //Verde Escuro
			alertaLabel.setText("");
		}
		metaProgressBar.setValue((int)porcentagemGasta);
	}//atualizarPainel
}//class PainelTituloPainelDespesas

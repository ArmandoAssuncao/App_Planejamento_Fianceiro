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
	private JLabel valorDadosUmLabel;
	private JLabel valorDadosTresLabel;
	private JLabel valorDadosDoisLabel;
	private JLabel valorDadosQuatroLabel;
	private JLabel valorCamposQuatroLabel;
	private JLabel valorCamposTresLabel;
	private JLabel valorCamposUmLabel;
	private JLabel valorCamposDoisLabel;
	private JLabel valorDadosCincoLabel;
	private JLabel valorCamposCincoLabel;

	/**
	 * Construtor padrão.
	 */
	public PainelTituloPainelGraficos() {
		setPreferredSize(new Dimension(750,140));
		
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
		panel.setBounds(7, 0, 736, 85);
		centroPanel.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][]", "[][]"));
		
		valorDadosUmLabel = new JLabel("< >");
		valorDadosUmLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosUmLabel, "cell 0 0,alignx right,aligny center");
		
		valorCamposUmLabel = new JLabel("campo1");
		panel.add(valorCamposUmLabel, "cell 1 0,alignx left,aligny center");
		
		valorDadosDoisLabel = new JLabel("< >");
		valorDadosDoisLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosDoisLabel, "cell 9 0,alignx right,aligny center");
		
		valorCamposDoisLabel = new JLabel("campo2");
		panel.add(valorCamposDoisLabel, "cell 10 0,alignx left,aligny center");
		
		valorDadosCincoLabel = new JLabel("< >");
		valorDadosCincoLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosCincoLabel, "cell 18 0,alignx right");
		
		valorCamposCincoLabel = new JLabel("campo5");
		panel.add(valorCamposCincoLabel, "cell 19 0,alignx left");
		
		valorDadosTresLabel = new JLabel("< >");
		valorDadosTresLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosTresLabel, "cell 0 1,alignx right,aligny center");
		
		valorCamposTresLabel = new JLabel("campo3");
		panel.add(valorCamposTresLabel, "cell 1 1,alignx left,aligny center");
		
		valorDadosQuatroLabel = new JLabel("< >");
		valorDadosQuatroLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosQuatroLabel, "cell 9 1,alignx right,aligny center");
		
		valorCamposQuatroLabel = new JLabel("campo4");
		panel.add(valorCamposQuatroLabel, "cell 10 1,alignx left,aligny center");
		
		setVisible(true);
	}//construtor
	
	/**
	 * Atualiza o painel título da aba gráficos, quando o gráfico gerado for de pizza.
	 * @param valores Valores numéricos a serem colocados no painel de título
	 * @param campos Campos correspondentes aos valores a serem colocados na barra de título
	 * @param tituloGrafico tipo de gráfico.
	 */
	//tipo de grafico: 1 - valores reais, 2 - valores em porcentagem
	public void atualizarPainelTituloGp(String[] campos, Double[] valores,String tituloGrafico){
		valorCamposUmLabel.setText(campos[0]);
		valorDadosUmLabel.setText(valorDadosPainel(tituloGrafico, valores[0]));
		
		valorCamposDoisLabel.setText(campos[1]);
		valorDadosDoisLabel.setText(valorDadosPainel(tituloGrafico, valores[1]));
		
		valorCamposTresLabel.setText(campos[2]);
		valorDadosTresLabel.setText(valorDadosPainel(tituloGrafico, valores[2]));
		
		valorCamposQuatroLabel.setText(campos[3]);
		valorDadosQuatroLabel.setText(valorDadosPainel(tituloGrafico, valores[3]));
		
		valorCamposCincoLabel.setText("");
		valorDadosCincoLabel.setText("");
	}//atualizarPainel
	
	private String valorDadosPainel(String tituloGrafico,double valor){
		if(tituloGrafico.contains("Mensal"))
			return "R$ "+ String.format("%.2f", valor);
		else if (tituloGrafico.contains("Pagamento"))
			return valor + "%";
		return null;
	}
	
	/**
	 * Atualiza o painel título da aba gráficos, quando o gráfico gerado for de linha.
	 * @param nomeCategoria nome da Categoria que está no gráfico de linha.
	 */
	public void atualizarPainelTituloGl(String nomeCategoria){
		valorCamposUmLabel.setText("");
		valorDadosUmLabel.setText(nomeCategoria);
		
		Double[] valores = obtemValoresMetaCategoria(nomeCategoria);
		
		valorCamposDoisLabel.setText("para o alerta de gastos");
		valorDadosDoisLabel.setText("R$ "+ String.format("%.2f", 50.0f));
		
		valorCamposTresLabel.setText("");
		valorDadosTresLabel.setText("");
		
		valorCamposQuatroLabel.setText("");
		valorDadosQuatroLabel.setText("");
		
		valorCamposCincoLabel.setText("limite de gastos");
		valorDadosCincoLabel.setText("R$ "+ String.format("%.2f", 100f));
	}

	private Double[] obtemValoresMetaCategoria(String nomeCategoria) {
		Double[] valores = new Double[2];
		//TODO
		return valores;
	}
}//class PainelTituloPainelGraficos 

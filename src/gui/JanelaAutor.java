package gui;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cria uma GUI com as informações dos autores 
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class JanelaAutor extends JDialog {
	private static final long serialVersionUID = 5044062671944336458L;
	
	private final int TAM_JANELA_X = 450;
	private final int TAM_JANELA_Y = 300;
	
	/**
	 * Construtor padrão da classe 
	 *
	 */
	public JanelaAutor() {
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(Color.GRAY);
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel labelTitulo = new JLabel("Planejamento Financeiro");
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setFont(new Font("Tekton Pro", Font.PLAIN, 27));
		panelNorte.add(labelTitulo);
		
		JPanel panelSul = new JPanel();
		panelSul.setPreferredSize(new Dimension(10, 50));
		panelSul.setBackground(Color.GRAY);
		getContentPane().add(panelSul, BorderLayout.SOUTH);
		panelSul.setLayout(null);
		
		JButton botaoFechar = new JButton("Fechar");
		botaoFechar.setBounds(173, 11, 71, 23);
		botaoFechar.setFont(new Font("Tahoma", Font.BOLD, 11));
		botaoFechar.setBackground(Color.GRAY);
		botaoFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelSul.add(botaoFechar);
		
		JLabel lblCopyright = new JLabel("Copyright 2015-2015");
		lblCopyright.setBounds(322, 35, 102, 14);
		panelSul.add(lblCopyright);
		
		JLabel lblBancadaNegra = new JLabel("Bancada Negra");
		lblBancadaNegra.setVisible(true);
		lblBancadaNegra.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBancadaNegra.setBounds(10, 26, 108, 23);
		panelSul.add(lblBancadaNegra);
		
		JPanel panelLeste = new JPanel();
		panelLeste.setBackground(Color.GRAY);
		panelLeste.setPreferredSize(new Dimension(70, 10));
		getContentPane().add(panelLeste, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(Color.LIGHT_GRAY);
		panelCentro.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JTextArea textFieldAutor = new JTextArea();
		textFieldAutor.setText("Planejamento Financeiro\n\nCriado por:\n Armando Assunção\n Richardson William\n\nProjeto para diciplina da faculdade.");
		textFieldAutor.setEditable(false);
		textFieldAutor.setOpaque(false);
		panelCentro.add(textFieldAutor, BorderLayout.CENTER);
		
		JPanel panelOeste = new JPanel();
		panelOeste.setPreferredSize(new Dimension(5, 10));
		panelOeste.setBackground(Color.GRAY);
		getContentPane().add(panelOeste, BorderLayout.WEST);
		panelOeste.setLayout(new BorderLayout(0, 0));
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(TAM_JANELA_X, TAM_JANELA_Y);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setVisible(true);
	}
}

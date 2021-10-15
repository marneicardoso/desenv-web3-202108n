package com.marneicardoso.agenda.view;

import javax.swing.JOptionPane;

public class Tela {

	public void mostrar(String mensagem, String titulo, int icone) {
		JOptionPane.showMessageDialog(
				null,		// Componente pai
				mensagem,	// Mensagem	
				titulo,		// Tútulo da janela
				icone		// Ícone
				);
		
		// -1 Plane
		//  0 Error
		//  1 Information
		//  2 Warning
		//  3 Question
	}
	
	public String informar(String mensagem, String titulo, int icone) {
		return JOptionPane.showInputDialog(
				null,		// Componente pai
				mensagem,	// Mensagem	
				titulo,		// Tútulo da janela
				icone		// Ícone
				);
	}
	
	public Integer confirmar(String mensagem, String titulo, int icone) {
		return JOptionPane.showConfirmDialog(
				null,
				mensagem,	
				titulo,
				JOptionPane.YES_NO_OPTION,
				icone
				);
	}
}

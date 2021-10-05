package com.marneicardoso.agenda.model;

import java.util.ArrayList;
import java.util.List;

public class Lista {
	// Atributos
	private static List<Contato> lista;
	
	// Cria uma INSTÂNCIA ÚNICA da Lista (Padrão de Projeto: Singleton)
	public static List<Contato> getInstance() {
		// Se ainda NÃO existir a instância, cria uma
		if (lista == null) {
			lista = new ArrayList();
		}
		
		// Se JÁ existir, retorna a instância
		return lista;
	}
}

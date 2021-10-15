package com.marneicardoso.agenda.controller;

import java.sql.SQLException;

import com.marneicardoso.agenda.model.Agenda;

public class App {
	
	public static void main(String[] args) throws SQLException {
		// Cria o objeto da classe Agenda
		Agenda agenda = new Agenda();
		agenda.iniciarAgenda();
	}
}

package com.marneicardoso.agenda.model;

import com.marneicardoso.agenda.view.Tela;

public class Agenda {

	// Métodos
	public void iniciarAgenda() {
		// Cria o objeto da classe Tela
		Tela tela = new Tela();

		// Monta o Menu
		String menu = ":: Agenda de Contatos ::\n\n" +
				"1. Cadastrar\n" +
				"2. Buscar\n" +
				"3. Editar\n" +
				"4. Excluir\n\n";

		// Mantém o Menu ativo
		boolean isAtivo = true;

		// Loop principal do Sistema
		while (isAtivo) {
			String opcao = tela.informar(menu, "Informe uma opção do Menu", -1);

			switch (opcao) {
			case "1":
				cadastrarContato(tela);
				break;

			case "2":
				buscarContato(tela);
				break;

			case "3":
				editarContato(tela);
				break;

			case "4":
				excluirContato(tela);
				break;

			default:
				int sair = tela.confirmar("Deseja sair?", "Encerrar", 3);

				if (sair == 0) {
					isAtivo = false;
					tela.mostrar("Encerrando o sistema...", "Encerrando", 1);
				}
			} // fecha o switch
		} // fecha o while
	} // fecha o método iniciarAgenda()

	private void cadastrarContato(Tela tela) {
		
		// Usuário informa os dados
		String nome = tela.informar("Informe o Nome", "Nome", 1);
		String email = tela.informar("Informe o E-mail", "E-mail", 1);
		String fone = tela.informar("Informe o Telefone", "Fone", 1);
		
		// Cria o objeto da classe Contato
		Contato contato = new Contato(nome, email, fone);
		
		// Adiciona o Contato (preenchido) na Lista de contatos da Agenda
		Lista.getInstance().add(contato);
	} // fecha o método cadastrarContato()
	
	private void buscarContato(Tela tela) {
		// Guarda o número de registros na Lista
		int numeroRegistros = Lista.getInstance().size();
		
		if (numeroRegistros > 0) {
			String listaContatos = "";
			
			for (int i = 0; i < numeroRegistros; i++) {
				listaContatos +=
					"ID: " + (i +1) +
					"\nNome: " + Lista.getInstance().get(i).getNome() +
					"\nE-mail: " + Lista.getInstance().get(i).getEmail() +
					"\nFone: " + Lista.getInstance().get(i).getFone() +
					"\n\n";
			}
			
			// Mostra o resultado da busca
			tela.mostrar(listaContatos, "Contatos", 1);
			
		} else {
			tela.mostrar("Nenhum contato registrado", "Contatos", 1);
		}
	} // fecha o método buscarContato()
	
	private void editarContato(Tela tela) {
		// Mostra os registros de contato
		buscarContato(tela);
		
		// Verifica se há algum registro de contato
		if (Lista.getInstance().size() < 1) {
			return; // Volta para o Menu
		}
		
		// Usuário informa um ID para editar
		int id = 0;
		
		try {
			// Tenta converter para inteiro
			id = Integer.parseInt(tela.informar("Informe um ID para editar", "Editar contato", 1));
		
		} catch(Exception e) {
			tela.mostrar("Informe um ID numérico", "Editar contato", 2);
			return; // Volta para o Menu
		}
		
		// Verifica se o ID informado é maior que os registros
		if (id > Lista.getInstance().size()) {
			tela.mostrar("ID informado é inválido", "Editar contato", 2);
			return; // Volta para o Menu
		}
		
		// Usuário informa os novos dados do contato
		String novoNome = tela.informar("Informe o novo Nome", "Nome", 1);
		String novoEmail = tela.informar("Informe o novo E-mail", "E-mail", 1);
		String novoFone = tela.informar("Informe o novo Telefone", "Fone", 1);
		
		// Verifica se os campos foram preenchidos
		if (!novoNome.equals("")) {
			Lista.getInstance().get(id -1).setNome(novoNome);
		}
		
		if (!novoEmail.equals("")) {
			Lista.getInstance().get(id -1).setEmail(novoEmail);
		}
		
		if (!novoFone.equals("")) {
			Lista.getInstance().get(id -1).setFone(novoFone);
		}
		
		tela.mostrar("Contato editado com sucesso", "Editar contato", 1);
	}
	
	private void excluirContato(Tela tela) {
		// Mostra os registros de contato
		buscarContato(tela);
		
		// Verifica se há algum registro de contato
		if (Lista.getInstance().size() < 1) {
			return; // Volta para o Menu
		}
		
		// Usuário informa um ID para editar
		int id = 0;
		
		try {
			// Tenta converter para inteiro
			id = Integer.parseInt(tela.informar("Informe um ID para excluir", "Excluir contato", 1));
		
		} catch(Exception e) {
			tela.mostrar("Informe um ID numérico", "Excluir contato", 2);
			return; // Volta para o Menu
		}
		
		// Verifica se o ID informado é maior que os registros
		if (id > Lista.getInstance().size()) {
			tela.mostrar("ID informado é inválido", "Excluir contato", 2);
			return; // Volta para o Menu
		}
		
		String contato = Lista.getInstance().get(id -1).getNome();
		
		// Confirma a exclusão
		int confirma = tela.confirmar(
				"Deseja excluir o contato (" + contato + ")?",
				"Excluir contato",
				3);
		
		if (confirma == 0) {
			Lista.getInstance().remove(id -1);
			tela.mostrar("Contato (" + contato + ") excluído com sucesso", "Excluir contato", 1);
		}
	}
}













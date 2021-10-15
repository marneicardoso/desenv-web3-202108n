package com.marneicardoso.agenda.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.marneicardoso.agenda.view.Tela;

public class Agenda {

	// Métodos
	public void iniciarAgenda() throws SQLException {
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

	private void cadastrarContato(Tela tela) throws SQLException {
		
		// Usuário informa os dados
		String nome = tela.informar("Informe o Nome", "Nome", 1);
		String email = tela.informar("Informe o E-mail", "E-mail", 1);
		String fone = tela.informar("Informe o Telefone", "Fone", 1);
		
		// Cria o objeto da classe Contato
		Contato contato = new Contato(nome, email, fone);

		// Envia o Contato (preenchido) para o Banco de Dados
		ContatoDAO dao = new ContatoDAO();		
		boolean cadastrou = dao.cadastrarContatoDAO(contato);
		
		if (cadastrou) {
			tela.mostrar("Cadastro efetutado com sucesso!", "Cadastro", 1);
		
		} else {
			tela.mostrar("Erro ao cadastrar o Contato", "Cadastro", 0);
		}
		
	} // fecha o método cadastrarContato()
	
	private Boolean buscarContato(Tela tela) throws SQLException {
		
		ContatoDAO dao = new ContatoDAO();
		ResultSet resultado = dao.buscarContatoDAO(0); // 0 == Todos
		
		if (!resultado.next()) {
			tela.mostrar("Nenhum contato registrado", "Contatos", 1);
			return false; // Volta ao Menu
		}
		
		// Posiciona no início dos resultados
		resultado.beforeFirst();
		
		String contatos = "";
		
		// Percorre a lista de resultado
		while (resultado.next()) {
			contatos +=
				"ID: " + resultado.getInt("id") +
				"\nNome: " + resultado.getString("nome") +
				"\nE-mail: " + resultado.getString("email") +
				"\nFone: " + resultado.getString("fone") +
				"\n\n";
		}
		
		// Mostra o resultado da busca
		tela.mostrar(contatos, "Contatos", 1);
		return true;
		
	} // fecha o método buscarContato()

	private void editarContato(Tela tela) throws SQLException {
		
		// Se não houver nenhum contato
		if (!buscarContato(tela)) {
			return; // Volta ao Menu
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
		
		ContatoDAO dao = new ContatoDAO();
		
		// Dados do Contato no DB
		ResultSet resultado = dao.buscarContatoDAO(id);
		
		String nomeDB = "";
		String emailDB = "";
		String foneDB = "";
		
		if (resultado.next()) {
			nomeDB = resultado.getString("nome");
			emailDB = resultado.getString("email");
			foneDB = resultado.getString("fone");
		
		} else {
			tela.mostrar("ID informado é inválido", "Editar contato", 2);
			return; // Volta para o Menu
		}
		
		// Usuário informa os novos dados do contato
		String novoNome = tela.informar("Informe o novo Nome", "Nome", 1);
		String novoEmail = tela.informar("Informe o novo E-mail", "E-mail", 1);
		String novoFone = tela.informar("Informe o novo Telefone", "Fone", 1);
		
		// Verifica se os campos foram preenchidos
		if (!novoNome.equals("")) {
			nomeDB = novoNome;
		}
		
		if (!novoEmail.equals("")) {
			emailDB = novoEmail;
		}
		
		if (!novoFone.equals("")) {
			foneDB = novoFone;
		}
		
		// Atualiza os dados do Contato
		Contato contato = new Contato(nomeDB, emailDB, foneDB);
		
		// Atualiza no DB
		boolean editou = dao.editarContatoDAO(contato, id);
		
		if (editou) {
			tela.mostrar("Contato editado com sucesso", "Editar contato", 1);
		
		} else {
			tela.mostrar("Erro ao editar o Contato", "Editar contato", 0);
		}
	}
	
	private void excluirContato(Tela tela) throws SQLException {
		
		// Se não houver nenhum contato
		if (!buscarContato(tela)) {
			return; // Volta ao Menu
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
		
		ContatoDAO dao = new ContatoDAO();
		
		// Dados do Contato no DB
		ResultSet resultado = dao.buscarContatoDAO(id);
		String contato = "";
		
		if (resultado.next()) {
			contato = resultado.getString("nome");
		
		} else {
			tela.mostrar("ID informado é inválido", "Excluir contato", 2);
			return; // Volta para o Menu
		}
		
		// Confirma a exclusão
		int confirma = tela.confirmar(
				"Deseja excluir o contato (" + contato + ")?",
				"Excluir contato",
				3);
		
		if (confirma == 0) {
			boolean excluiu = dao.excluirContatoDAO(id); 

			if (excluiu) {
				tela.mostrar("Contato (" + contato + ") excluído com sucesso", "Excluir contato", 1);
			
			} else {
				tela.mostrar("Erro ao excluir o Contato", "Excluir contato", 0);
			}
		}
	}
}

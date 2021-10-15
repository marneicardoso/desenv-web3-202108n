package com.marneicardoso.agenda.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContatoDAO {

	public Boolean cadastrarContatoDAO(Contato contato) throws SQLException {
		// Instrução SQL para adicionar um registro no DB
		String sql =
			"INSERT INTO contato (nome, email, fone)" +
			"VALUES" +
			"(?, ?, ?)";
		
		ConexaoDB conexao = new ConexaoDB();
		Connection conn = conexao.abrirConexao(); // Interface de conexão
		PreparedStatement statement = conn.prepareStatement(sql);
		
		// Adiciona os valores informados na posição de cada interrogação
		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getEmail());
		statement.setString(3, contato.getFone());
		
		// Executa a instrução no DB
		statement.execute();
		
		// Fecha Statement e conexão com DB
		statement.close();	
		conexao.fecharConexao(conn);
		
		return true; // Retorna sucesso do registro
	}
	
	public ResultSet buscarContatoDAO(int id) throws SQLException {
		// Instrução SQL para buscar os registros no DB
		String sql = "";
		
		// Verifica o tipo de busca
		if (id == 0) {
			sql = "SELECT * FROM contato";
		
		} else {
			sql = "SELECT * FROM contato WHERE id = " + id;
		}
		
		ConexaoDB conexao = new ConexaoDB();
		Connection conn = conexao.abrirConexao(); // Interface de conexão
		PreparedStatement statement = conn.prepareStatement(sql);
		
		ResultSet resultadoDaBusca = statement.executeQuery();
		return resultadoDaBusca;
	}
	
	public Boolean editarContatoDAO(Contato contato, int idInformado) throws SQLException {
		// Instrução SQL para atualizar o registro no DB
		String sql =
			"UPDATE contato " +
			"SET nome = ?, email = ?, fone = ?" +
			"WHERE id = ?";
		
		ConexaoDB conexao = new ConexaoDB();
		Connection conn = conexao.abrirConexao(); // Interface de conexão
		PreparedStatement statement = conn.prepareStatement(sql);
		
		// Adiciona os valores informados na posição de cada interrogação
		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getEmail());
		statement.setString(3, contato.getFone());
		statement.setInt(4, idInformado);
		
		statement.executeUpdate();
		return true;
	}
	
	public Boolean excluirContatoDAO(int idInformado) throws SQLException {
		// Instrução SQL para excluir o registro no DB
		String sql =
			"DELETE FROM contato " +
			"WHERE id = ?";
		
		ConexaoDB conexao = new ConexaoDB();
		Connection conn = conexao.abrirConexao(); // Interface de conexão
		PreparedStatement statement = conn.prepareStatement(sql);
		
		// Adiciona os valores informados na posição de cada interrogação
		statement.setInt(1, idInformado);
		
		statement.executeUpdate();
		return true;
	}
}






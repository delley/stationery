package br.com.froli.stationery.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.froli.stationery.model.Produto;
import br.com.froli.stationery.model.Setor;

public class Database {
	
	private static final Logger LOGGER = Logger.getLogger(Database.class.getName());

	private List<Produto> produtos;
	private List<Setor> setores;
	private static Database instance;
	private Connection con;
	private static final String SQL_PRODUTO = "SELECT id, descricao, id_setor, fabricante, complemento, preco, oferta FROM produto";
	private static final String SQL_SETORES = "SELECT id, descricao FROM setores";
	
	private Database() {
		produtos = new ArrayList<>();
		setores = new ArrayList<>();

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/stationery");
			LOGGER.log(Level.INFO, "[Database] connection OK");
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.log(Level.INFO, "[Database] connection error");
			throw new RuntimeException(e);
		}
	}
	
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public void disconnect() {
		try {
			if (con != null) {
				con.close();
			}
			instance = null;
			LOGGER.log(Level.INFO, "[Database] connection closed");
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "[Database] connection close error");
		}

		con = null;
	}
	
	public Produto getProdutoPorId(Long id) {
		Produto produto = null;
		try {
			LOGGER.log(Level.INFO, "[Database.getProdutoPorId] product retrieve");
			String query = SQL_PRODUTO.concat(" WHERE id = ?");
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setLong(1, id);
			ResultSet resultSet = pstm.executeQuery();
			while (resultSet.next()) {
				produto = new Produto(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getBigDecimal(6),
						resultSet.getBoolean(7));
			}
			resultSet.close();
			pstm.close();
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "[Database.getProdutoPorId] SQL error");
		}
		return produto;
	}
	
	public List<Produto> getProdutos() {
		return getProdutos(-1L);
	}
	
	public List<Produto> getProdutos(Long idSetor) {
		try {
			LOGGER.log(Level.INFO, "[Database.getProdutos] data retrieve");
			produtos.clear();
			
			String query = null;
			PreparedStatement pstm = null;
			switch (idSetor.intValue()) {
			case -1:
				query = SQL_PRODUTO.concat(" ORDER BY descricao");
				pstm = con.prepareStatement(query);
				break;
			case 0:
				query = SQL_PRODUTO.concat(" WHERE oferta = true ORDER BY descricao");
				pstm = con.prepareStatement(query);
				break;
			default:
				query = SQL_PRODUTO.concat(" WHERE id_setor = ? ORDER BY descricao");
				pstm = con.prepareStatement(query);
				pstm.setLong(1, idSetor);
			}
			
			ResultSet resultSet = pstm.executeQuery();
			Produto produto = null;
			while (resultSet.next()) {
				produto = new Produto(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getBigDecimal(6),
						resultSet.getBoolean(7));
				produtos.add(produto);
			}
			resultSet.close();
			pstm.close();
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "[Database.getProdutos] SQL error");
		}
		return produtos;
	}
	
	public Setor getSetorPorDescricao(String descricao) {
		Setor setor = setores.get(0);
		if(descricao == null) {
			return setor;
		}
		
		for (Setor s : setores) {
			if(s.getDescricao().equals(descricao)) {
				return s;
			}
		}
		
		return setor;
	}
	
}

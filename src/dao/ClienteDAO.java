package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import to.ClienteTO;
import factory.ConnectionFactory;

public class ClienteDAO {
	int idGerado;
	
	public void incluir(ClienteTO to) {
		String sqlInsert = "INSERT INTO cliente(id, nome, fone) VALUES (?,?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getId());
			stm.setString(2, to.getNome());
			stm.setString(3, to.getFone());
			stm.execute();
			String sqlSelect = "select LAST_INSERT_ID()"; 
			try(PreparedStatement pst1 = conn.prepareStatement(sqlSelect);
					ResultSet rs = pst1.executeQuery();){
				if(rs.next()){
					idGerado = rs.getInt("1");
					}    
				} catch (SQLException e)  {
					e.printStackTrace(); 
					}			
			//COLOCAR AQUI O METODO DO PROFESSOR
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(ClienteTO to) {
		String sqlUpdate = "UPDATE cliente SET nome=?, fone=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, to.getNome());
			stm.setString(2, to.getFone());
			stm.setInt(3, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(ClienteTO to) {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ClienteTO carregar(int id) {
		ClienteTO to = new ClienteTO();
		String sqlSelect = "SELECT nome, fone FROM cliente WHERE cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setNome(rs.getString("nome"));
					to.setFone(rs.getString("fone"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return to;
	}
	
	
	
	public String consultarTodos() {
		//ClienteTO to = new ClienteTO();
		String nome, fone, resp="";
		int id, cont=0;
		String sqlSelect = "SELECT * FROM cliente";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			//stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					id = (rs.getInt("id"));
					nome=(rs.getString("nome"));
					fone=(rs.getString("fone"));
					resp = "\n" + resp + "<br>" + ((cont = cont + 1) + "º cliente: " + " ID: " + id + " Nome: " + nome + " Telefone: " + fone + "\n"); 
				System.out.println(resp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return resp;
	}
	
	
}

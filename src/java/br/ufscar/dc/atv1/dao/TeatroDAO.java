/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1.dao;
import br.ufscar.dc.atv1.login.JDBCUtil;
import br.ufscar.dc.atv1.model.Teatro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author 743528
 */
public class TeatroDAO {
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public TeatroDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/SiteIngressos", "root", "root");
    }


    public void insert(Teatro teatro, String senha) {
        String sql = "INSERT INTO Teatro (email,cnpj,nome,cidade) VALUES (?, ?, ?, ?)";
        String sqlUser = "Insert into Usuario (email, senha, ativo) values (?,?,?)";
        String sqlPapel = "Insert into Papel (email, nome) values (?,?)";
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);            
            statement.setString(1, teatro.getEmail());
            statement.setString(2, teatro.getCNPJ());
            statement.setString(3, teatro.getNome());
            statement.setString(4, teatro.getCidade());
            statement.executeUpdate();            
            statement.close();
            
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            statementUser.setString(1, teatro.getEmail());
            statementUser.setString(2, encoder.encode(senha));
            statementUser.setBoolean(3, true);
            statementUser.executeUpdate();
            statementUser.close();
            
            PreparedStatement statementPapel = conn.prepareStatement(sqlPapel);
            statementPapel.setString(1, teatro.getEmail());
            statementPapel.setString(2, "ROLE_TEATRO");
            statementPapel.executeUpdate();
            statementPapel.close();

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Teatro> getAll() {
        List<Teatro> listaTeatros = new ArrayList<>();
        String sql = "SELECT * FROM Teatro";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();            
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cidade = resultSet.getString("cidade");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String email = resultSet.getString("email");                
                
                Teatro teatro = new Teatro(email, cnpj, nome, cidade);
                listaTeatros.add(teatro);
            }
            resultSet.close(); 
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaTeatros;
    }

    public void delete(Teatro teatro) {
        String sql = "DELETE FROM Teatro WHERE cnpj = ?";
        String sqlUser = "DELETE FROM Usuario WHERE email = ?";
        String sqlPapel = "DELETE FROM Pepel WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, teatro.getCNPJ());
            statement.executeUpdate();
            statement.close();
            
            PreparedStatement statementUser = conn.prepareStatement(sql);
            statementUser.setString(1, teatro.getEmail());
            statementUser.executeUpdate();
            statementUser.close();
            
            PreparedStatement statementPapel = conn.prepareStatement(sql);
            statementPapel.setString(1, teatro.getEmail());
            statementPapel.executeUpdate();
            statementPapel.close();
            
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Teatro teatro,String senha) {
        String sql = "UPDATE Teatro SET nome = ?, cidade = ?";
        sql += " WHERE cnpj = ?";
        
        String sqlUser = "UPDATE Usuario SET senha = ? WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, teatro.getNome());            
            statement.setString(2, teatro.getCidade());
            statement.setString(3, teatro.getCNPJ());
            statement.executeUpdate();
            statement.close();
            
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            statementUser.setString(1, encoder.encode(senha));
            statementUser.setString(2, teatro.getEmail());
            statementUser.executeUpdate();
            statementUser.close();
            
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Teatro get(String cnpj) {
        Teatro teatro = null;
        String sql = "SELECT * FROM Teatro WHERE cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String cidade = resultSet.getString("cidade");
                teatro = new Teatro(email, cnpj, nome, cidade);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teatro;
    }
    
    public List<Teatro> getByCity(String city) {
        List<Teatro> listaTeatros = new ArrayList<>();
        String sql = "SELECT * FROM Teatro WHERE cidade = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, city);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {                
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String email = resultSet.getString("email");
                Teatro teatro = new Teatro(email, cnpj, nome, city);
                listaTeatros.add(teatro);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaTeatros;
    }
    
    public List<String> getCity() {
        List<String> listaCidades = new ArrayList<>();
        String sql = "SELECT DISTINCT cidade FROM Teatro";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cidade = resultSet.getString("cidade");               
                listaCidades.add(cidade);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCidades;
    }
    
    public String getSenha(String cnpj) {
        String senha = null;
        String sql = "SELECT senha FROM Usuario WHERE email = (SELECT email FROM teatro WHERE cnpj = ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                senha = resultSet.getString("senha");                
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return senha;
    }
}
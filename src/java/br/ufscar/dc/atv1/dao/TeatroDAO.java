/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1.dao;
import br.ufscar.dc.atv1.model.Teatro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author 743528
 */
public class TeatroDAO {
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

    public void insert(Teatro teatro) {
        String sql = "INSERT INTO Teatro (email,cnpj,nome,cidade) VALUES (?, ?, ?, ?, ?)";
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement.setString(1, teatro.getEmail());
            statement.setString(2, teatro.getCNPJ());
            statement.setString(3, teatro.getNome());
            statement.setString(4, teatro.getCidade());
            statement.executeUpdate();
            statement.close();
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
        String sql = "DELETE FROM Teatro where cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, teatro.getCNPJ());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Teatro teatro) {
        String sql = "UPDATE Teatro SET nome = ?, email = ?, cidade = ?";
        sql += " WHERE cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, teatro.getNome());
            statement.setString(2, teatro.getEmail());
            statement.setString(3, teatro.getCidade());
            statement.setString(4, teatro.getCNPJ());
            statement.executeUpdate();
            statement.close();
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
}
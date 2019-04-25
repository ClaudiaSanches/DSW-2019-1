/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1.dao;

import br.ufscar.dc.atv1.model.Site;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class SiteDAO {

    public SiteDAO() {
        try {            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/SiteIngressos", "root", "root");
    }

    public void insert(Site site, String senha) {
        String sql = "INSERT INTO Site (email,url,nome,telefone) VALUES (?, ?, ?, ?, ?)";
        String sqlUser = "Insert into Usuario (email, senha, ativo) values (?,?,?)";
        String sqlPapel = "Insert into Papel (email, nome) values (?,?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement.setString(1, site.getEmail());
            statement.setString(3, site.getUrl());
            statement.setString(4, site.getNome());
            statement.setString(5, site.getTelefone());
            statement.executeUpdate();
            statement.close();
            
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            statementUser.setString(1, site.getEmail());
            statementUser.setString(2, senha);
            statementUser.setBoolean(3, true);
            statementUser.executeUpdate();
            statementUser.close();
            
            PreparedStatement statementPapel = conn.prepareStatement(sqlPapel);
            statementPapel.setString(1, site.getEmail());
            statementPapel.setString(2, "ROLE_SITE");
            statementPapel.executeUpdate();
            statementPapel.close();
            
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Site> getAll() {
        List<Site> listaSites = new ArrayList<>();
        String sql = "SELECT * FROM Site";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String telefone = resultSet.getString("telefone");
                String nome = resultSet.getString("nome");
                String url = resultSet.getString("url");
                String email = resultSet.getString("email");
                Site site = new Site(email, nome, url, telefone);
                listaSites.add(site);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaSites;
    }

    public void delete(Site site) {
        String sql = "DELETE FROM Site where url = ?";
        String sqlUser = "DELETE FROM Usuario WHERE email = ?";
        String sqlPapel = "DELETE FROM Pepel WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, site.getUrl());
            statement.executeUpdate();
            statement.close();
            
            PreparedStatement statementUser = conn.prepareStatement(sql);
            statementUser.setString(1, site.getEmail());
            statementUser.executeUpdate();
            statementUser.close();
            
            PreparedStatement statementPapel = conn.prepareStatement(sql);
            statementPapel.setString(1, site.getEmail());
            statementPapel.executeUpdate();
            statementPapel.close();
            
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Site site,String senha) {
        String sql = "UPDATE Site SET nome = ?, email = ?, telefone = ?";
        sql += " WHERE url = ?";
        
        String sqlUser = "UPDATE Usuario SET senha = ? WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, site.getNome());
            statement.setString(2, site.getEmail());
            statement.setString(4, site.getTelefone());
            statement.setString(5, site.getUrl());
            statement.executeUpdate();
            statement.close();
            
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            statementUser.setString(1, senha);
            statementUser.setString(2, site.getEmail());
            statementUser.executeUpdate();
            statementUser.close();
            
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Site get(String url) {
        Site site = null;
        String sql = "SELECT * FROM Site WHERE url = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, url);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                site = new Site(email, nome, url, telefone);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return site;
    }    
   
    
}
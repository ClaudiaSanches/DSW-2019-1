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

    public void insert(Site site) {
        String sql = "INSERT INTO Site (email,senha,url,nome,telefone) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement.setString(1, site.getEmail());
            statement.setString(2, site.getSenha());
            statement.setString(3, site.getUrl());
            statement.setString(4, site.getNome());
            statement.setString(5, site.getTelefone());
            statement.executeUpdate();
            statement.close();
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
                String nome = resultSet.getString("titulo");
                String url = resultSet.getString("url");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                Site site = new Site(email, senha, nome, url, telefone);
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
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, site.getUrl());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Site site) {
        String sql = "UPDATE Site SET nome = ?, email = ?, senha = ?, telefone = ?";
        sql += " WHERE url = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, site.getNome());
            statement.setString(2, site.getEmail());
            statement.setString(3, site.getSenha());
            statement.setString(4, site.getTelefone());
            statement.setString(5, site.getUrl());
            statement.executeUpdate();
            statement.close();
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
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                site = new Site(email, senha, nome, url, telefone);
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
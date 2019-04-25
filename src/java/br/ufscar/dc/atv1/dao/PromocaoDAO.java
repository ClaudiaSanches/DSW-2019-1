/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1.dao;
import br.ufscar.dc.atv1.model.Promocao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author 743528
 */
public class PromocaoDAO {
    public PromocaoDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/SiteIngressos", "root", "root");
    }

    public void insert(Promocao promoção) {
        String sql = "INSERT INTO Promocao (nome,preco,diahorario,cnpj,url) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement.setString(1, promoção.getPeça());
            statement.setFloat(2, promoção.getPreco());
            statement.setString(3, promoção.getDiahorario());
            statement.setString(4, promoção.getTeatro());
            statement.setString(5, promoção.getSite());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Promocao> getAll() {
        List<Promocao> listaPromoções = new ArrayList<>();
        String sql = "SELECT * FROM Promocao";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String diahorario = resultSet.getString("diahorario");
                String nome = resultSet.getString("nome");
                Float preço = resultSet.getFloat("preco");
                String teatro = resultSet.getString("cnpj");
                String site = resultSet.getString("url");
                Promocao promoção = new Promocao(id,nome,preço,diahorario,site,teatro);
                listaPromoções.add(promoção);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPromoções;
    }

    public void delete(Promocao promoção) {
        String sql = "DELETE FROM Promocao where id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, promoção.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Promocao promoção) {
        String sql = "UPDATE Promoção SET nome = ?, preco = ?, diahorario = ?, site = ?, teatro = ?";
        sql += " WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, promoção.getPeça());
            statement.setFloat(2, promoção.getPreco());
            statement.setString(3, promoção.getDiahorario());
            statement.setString(4, promoção.getSite());
            statement.setString(5, promoção.getTeatro());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Promocao get(int id) {
        Promocao promoção = null;
        String sql = "SELECT * FROM Promocao WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                float preço = resultSet.getFloat("preço");
                String nome = resultSet.getString("nome");
                String diahorario = resultSet.getString("diahorario");
                String site = resultSet.getString("site");
                String teatro = resultSet.getString("teatro");
                promoção = new Promocao(id, nome, preço, diahorario, site, teatro);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return promoção;
    }
    
    public List<Promocao> getByCnpj(String cnpj) {
        List<Promocao> listaPromoções = new ArrayList<>();
        String sql = "SELECT * FROM Promocao WHERE cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String diahorario = resultSet.getString("diahorario");
                String nome = resultSet.getString("nome");
                Float preço = resultSet.getFloat("preco");
                String teatro = resultSet.getString("cnpj");
                String site = resultSet.getString("url");
                Promocao promoção = new Promocao(id,nome,preço,diahorario,site,teatro);
                listaPromoções.add(promoção);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPromoções;
    }

    public List<Promocao> getByEmailTeatro(String email) {
        List<Promocao> listaPromoções = new ArrayList<>();
        String sql = "SELECT * FROM Promocao WHERE cnpj = (SELECT cnpj FROM Teatro WHERE email = ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String diahorario = resultSet.getString("diahorario");
                String nome = resultSet.getString("nome");
                Float preço = resultSet.getFloat("preco");
                String teatro = resultSet.getString("cnpj");
                String site = resultSet.getString("url");
                Promocao promoção = new Promocao(id,nome,preço,diahorario,site,teatro);
                listaPromoções.add(promoção);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPromoções;    
    }
    
    public List<Promocao> getByEmailSite(String email) {
        List<Promocao> listaPromoções = new ArrayList<>();
        String sql = "SELECT * FROM Promocao WHERE url = (SELECT url FROM Site WHERE email = ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String diahorario = resultSet.getString("diahorario");
                String nome = resultSet.getString("nome");
                Float preço = resultSet.getFloat("preco");
                String teatro = resultSet.getString("cnpj");
                String site = resultSet.getString("url");
                Promocao promoção = new Promocao(id,nome,preço,diahorario,site,teatro);
                listaPromoções.add(promoção);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPromoções;    
    }
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.atv1.dao;
import br.ufscar.dc.atv1.model.Promoção;
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
public class PromoçãoDAO {
    public PromoçãoDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/SiteIngresso", "root", "root");
    }

    public void insert(Promoção promoção) {
        String sql = "INSERT INTO Promoção (peça,preço,diahorario,teatro,site) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement.setString(1, promoção.getPeça());
            statement.setFloat(2, promoção.getPreço());
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

    public List<Promoção> getAll() {
        List<Promoção> listaPromoções = new ArrayList<>();
        String sql = "SELECT * FROM Promoção";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String diahorario = resultSet.getString("diahorario");
                String peça = resultSet.getString("peça");
                Float preço = resultSet.getFloat("preço");
                String teatro = resultSet.getString("teatro");
                String site = resultSet.getString("site");
                Promoção promoção = new Promoção(id,peça,preço,diahorario,site,teatro);
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

    public void delete(Promoção promoção) {
        String sql = "DELETE FROM Promoção where id = ?";
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

    public void update(Promoção promoção) {
        String sql = "UPDATE Promoção SET peça = ?, preço = ?, diahorario = ?, site = ?, teatro = ?";
        sql += " WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, promoção.getPeça());
            statement.setFloat(2, promoção.getPreço());
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

    public Promoção get(int id) {
        Promoção promoção = null;
        String sql = "SELECT * FROM Promoção WHERE id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                float preço = resultSet.getFloat("preço");
                String peça = resultSet.getString("peça");
                String diahorario = resultSet.getString("diahorario");
                String site = resultSet.getString("site");
                String teatro = resultSet.getString("teatro");
                promoção = new Promoção(id, peça, preço, diahorario, site, teatro);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return promoção;
    }
}
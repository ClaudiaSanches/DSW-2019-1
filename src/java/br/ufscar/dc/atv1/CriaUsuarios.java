package br.ufscar.dc.atv1;

import br.ufscar.dc.atv1.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriaUsuarios {

    public static void main(String[] args) throws ClassNotFoundException {

        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            DataSource ds = JDBCUtil.getDataSource();

            Connection conn = ds.getConnection();

            String userSql = "Insert into Usuario (email, senha, ativo) "
                    + "values (?,?,?)";

            String roleSql = "Insert into Papel (email, nome)"
                    + "values (?,?)";

            // Criando Usuario admin com papel ROLE_ADMIN
            
            PreparedStatement userStatement = conn.prepareStatement(userSql);
            userStatement.setString(1, "admin@admin");
            userStatement.setString(2, encoder.encode("admin"));
            userStatement.setBoolean(3, true);
            userStatement.execute();

            PreparedStatement roleStatement = conn.prepareStatement(roleSql);
            roleStatement.setString(1, "admin@admin");
            roleStatement.setString(2, "ROLE_ADMIN");
            roleStatement.execute();

            // Criando Usuario teatro com papel ROLE_TEATRO
            userStatement = conn.prepareStatement(userSql);
            userStatement.setString(1, "teatro@teatro");
            userStatement.setString(2, encoder.encode("teatro"));
            userStatement.setBoolean(3, true);
            userStatement.execute();

            roleStatement = conn.prepareStatement(roleSql);
            roleStatement.setString(1, "teatro@teatro");
            roleStatement.setString(2, "ROLE_TEATRO");
            roleStatement.execute();
            
            // Criando Usuario site com papel ROLE_SITE
            userStatement = conn.prepareStatement(userSql);
            userStatement.setString(1, "site@site");
            userStatement.setString(2, encoder.encode("site"));
            userStatement.setBoolean(3, true);
            userStatement.execute();

            roleStatement = conn.prepareStatement(roleSql);
            roleStatement.setString(1, "site@site");
            roleStatement.setString(2, "ROLE_SITE");
            roleStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

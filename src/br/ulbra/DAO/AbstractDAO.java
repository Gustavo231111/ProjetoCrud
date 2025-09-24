package br.ulbra.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AbstractDAO {

    private static final String DRIVER = "com.mysql.jdbc.Driver"; //Driver JDBC
    private static final String URL = "jdbc:mysql://localhost:3306/bdaulabanco";// Url do banco de dados
    private static final String USER = "root"; //Login e senha do banco de dados
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            return null;
        }
    }

}

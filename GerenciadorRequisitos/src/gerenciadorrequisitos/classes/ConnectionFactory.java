/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadorrequisitos.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author allan
 */
public class ConnectionFactory {
    private Connection conexao = null;
    
    private String HOSTNAME = "localhost";
    private String DB_NAME = "ProjetoUnificado";
    private String USER_NAME = "root";
    private String USER_PASS = "Indied1432";
    private int PORT_NUMBER = 3306;
    
    public Connection getConnection(){
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://" + HOSTNAME + ":" + PORT_NUMBER + "/" + DB_NAME, USER_NAME, USER_PASS);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar no banco!");
            return null;
        }
        return conexao;
    }
    
    public void closeConnection() {
        try {
            if (conexao != null) {
                conexao.close();
                conexao = null;
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão com o banco!");
        }
    }
}

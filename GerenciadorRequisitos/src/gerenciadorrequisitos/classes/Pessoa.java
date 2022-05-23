/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadorrequisitos.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author allan
 */
public class Pessoa {
    private int id;
    private String nomeCompleto;
    private String usuario;
    private String email;
    private String senha;
    private String telefone;
    
    public void gravarPessoaNoBancoDeDados() {
        String sql = "INSERT INTO Usuarios(NomeCompleto, NomeUsuario, Email, Senha, Telefone) VALUES(\"" + nomeCompleto + "\", "
                + "\"" + usuario + "\", \"" + email + "\", \"" + senha + "\", \"" + telefone + "\")";
        try {
            ConnectionFactory conexaoBD = new ConnectionFactory();
            Connection conn = conexaoBD.getConnection();
        
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            
            conexaoBD.closeConnection();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível gravar!");
        }
        
        JOptionPane.showMessageDialog(null, "Cadastro criado com sucesso!");
    }
    
    public boolean autenticarUsuario(String u, String p) {             
        String sql = "SELECT ID, NomeCompleto, NomeUsuario, Email, Senha, Telefone FROM Usuarios WHERE NomeUsuario = \"" + u + "\" "
                + "AND Senha = \"" + p + "\"";
           
        try {
            ConnectionFactory conexaoBD = new ConnectionFactory();  
            Connection conn = conexaoBD.getConnection();
            
            Statement stm = conn.createStatement();
            
            ResultSet resultado; 
            resultado = stm.executeQuery(sql);
            
            while (resultado.next()) {
                id = resultado.getInt("ID");
                nomeCompleto = resultado.getString("NomeCompleto");
                usuario = resultado.getString("NomeUsuario");
                email = resultado.getString("Email");
                senha = resultado.getString("Senha");
                telefone = resultado.getString("Telefone");
            }
            
            conexaoBD.closeConnection();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível autenticar!");
        }
        
        if (u.equals(usuario) && p.equals(senha))return true;
        else return false;
    }
    
    public boolean validarUsuario(String u) {
        String exists = "";
        
        String sql = "SELECT NomeUsuario FROM Usuarios WHERE NomeUsuario = \"" + u + "\"";
       
        try {
            ConnectionFactory conexaoBD = new ConnectionFactory();  
            Connection conn = conexaoBD.getConnection();
            
            Statement stm = conn.createStatement();
            
            ResultSet resultado; 
            resultado = stm.executeQuery(sql);
            
            while(resultado.next()){
                exists = resultado.getString("NomeUsuario");
            }
            
            conexaoBD.closeConnection();

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível consultar!");
        }
        
        if (exists == null || exists == "") {
            return true;
        } else {
            return false;
        }
    }
    
    public void excluirConta() {
        String sql = "DELETE FROM Usuarios WHERE ID = " + id;
        String sqlProjeto = "DELETE FROM Projetos WHERE UsuarioProprietario = " + id;
        String sqlRequisito = "DELETE FROM Requisitos WHERE Autor = " + id;
        
        try {
            ConnectionFactory conexaoBD = new ConnectionFactory();
            Connection conn = conexaoBD.getConnection();
        
            Statement stm = conn.createStatement();
            stm.executeUpdate(sqlRequisito);
            stm.executeUpdate(sqlProjeto);
            stm.executeUpdate(sql);
            
            conexaoBD.closeConnection();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível deletar!");
        }
    }
    
    public void alterarDadosCadastrais(int idUSuario, String nomeComp, String user, String email, String pass, String fone) {
        String sql = "UPDATE Usuarios SET NomeCompleto = \"" + nomeComp + "\", NomeUsuario = \"" + user + "\", Email = \"" + email + "\", Senha = \"" + pass + "\", Telefone = \"" + fone + "\" WHERE ID = \"" + idUSuario + "\"";
        
        try {
            ConnectionFactory conexaoBD = new ConnectionFactory();  
            Connection conn = conexaoBD.getConnection();
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            
            conexaoBD.closeConnection();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível resgatar os dados desse usuário!");
        }
        
        nomeCompleto = nomeComp;
        usuario = user;
        this.email = email;
        senha = pass;
        telefone = fone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

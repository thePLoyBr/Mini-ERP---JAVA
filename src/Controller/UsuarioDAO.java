/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felip
 */
public class UsuarioDAO {

    private Connection conn;
    private PreparedStatement stmt;

    public UsuarioDAO() {
        conn = new ConnectionFactory().getConnection();
    }

    public ArrayList<Usuario> listarUsuario() {
        ArrayList<Usuario> listaUsuarios = new ArrayList();
        String sql = "SELECT * FROM tb_usuario";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                
                u.setCodigo(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome_usuario"));
                u.setSenha(rs.getString("senha_usuario"));
                u.setTelefone(rs.getString("telefone_usuario"));
                u.setAcesso(rs.getString("perfil_usuario"));

                listaUsuarios.add(u);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuarios;
    }
    
    public void cadastrarUsuario(Usuario usuario){
        String sql = "INSERT INTO tb_usuario (nome_usuario, senha_usuario, telefone_usuario, perfil_usuario) VALUES (?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getAcesso());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("ERRO no INSERT de Usuário : " + e.getMessage());
        }
    }
    
    public void excluirUsuario(Usuario usuario){
        String sql = "DELETE FROM tb_usuario WHERE id_usuario = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuario.getCodigo());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("ERRO ao excluir usuário" + e.getMessage());
        }
                
    }
}

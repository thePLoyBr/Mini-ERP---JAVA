/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author felip
 */
public class ClienteDAO {

    private Connection conn;
    private PreparedStatement stmt;

    public ClienteDAO() {
        conn = new ConnectionFactory().getConnection();
    }

    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO tb_cliente(nome_cliente,idade_cliente,celular_cliente) VALUES (?,?,?)";
        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getTelefone());
            stmt.execute();
            stmt.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!");
        } catch (Exception ex) {
            throw new RuntimeException("Erro INSERT CLIENTE : ", ex);
        }
    }

    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList();
        String sql = "SELECT * FROM tb_cliente ORDER BY nome_cliente";

        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
                
            while (rs.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setCodigo(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setIdade(rs.getInt("idade_cliente"));
                cliente.setTelefone(rs.getString("celular_cliente"));
                
                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            conn.close();            
        } catch (Exception e) {
            System.out.println("ERRO NA LISTA" + e);
        }
        return clientes;
    }

    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE tb_cliente SET nome_cliente=?, idade_cliente=?, celular_cliente=? WHERE id_cliente=?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getCodigo());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            throw new RuntimeException("Erro UPDATE CLIENTE : ", ex);
        }
    }

    public void excluirCliente(Cliente cliente) {
        String sql = "DELETE FROM tb_cliente WHERE id_cliente=?";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getCodigo());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar: ", ex);
        }
    }
}

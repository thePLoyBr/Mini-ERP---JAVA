/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Model.Produto;
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
public class ProdutoDAO {
    private Connection conn;
    private PreparedStatement stmt;
    
    
    public ProdutoDAO(){
        conn = new ConnectionFactory().getConnection();
    }
    
    public void cadastrarProduto(Produto produto){
        String sql = "INSERT INTO tb_produto(nome_produto,desc_produto,preco_produto,quantidade_produto) VALUES (?,?,?,?)";
        try{ 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getName());
            stmt.setString(2, produto.getDesc());
            stmt.setFloat(3, produto.getPrice());
            stmt.setInt(4, produto.getQuant());
            stmt.execute();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Produto Cadastrado com sucesso!");
        } catch(Exception ex){
            throw new RuntimeException("Erro INSERT PRODUTO : ", ex);
        }
    }
    public void atualizarProduto(Produto produto){
        String sql = "UPDATE tb_produto SET nome_produto = ?,desc_produto=?,preco_produto=?,quantidade_produto=? WHERE id_produto = ?";
        try{ 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getName());
            stmt.setString(2, produto.getDesc());
            stmt.setDouble(3, produto.getPrice());
            stmt.setInt(4, produto.getQuant());
            stmt.setInt(5, produto.getId());
            stmt.execute();
            stmt.close();
        } catch(Exception ex){
            throw new RuntimeException("Erro UPDATE PRODUTO : ", ex);
        }
    }
    public void excluirProduto(Produto produto){
        String sql = "DELETE FROM tb_produto WHERE id_produto=?";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch(Exception ex){
            throw new RuntimeException("Erro ao deletar produto: ", ex);
        }
    }
    
    public List<Produto> listarProdutoDAO() {
        List produtos = new ArrayList();
        String sql = "SELECT * FROM tb_produto ORDER BY nome_produto";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
                
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setName(rs.getString("nome_produto"));
                produto.setDesc(rs.getString("desc_produto"));
                produto.setPrice(rs.getFloat("preco_produto"));
                produto.setQuant(rs.getInt("quantidade_produto"));
                
                produtos.add(produto);
            }
            rs.close();
            stmt.close();
            conn.close();            
        } catch (Exception e) {
            System.out.println("ERRO NA LISTA" + e);
        }
        return produtos;
    }
    
    
      public List<Produto> buscarProdutoDAO(Produto produto) {
        List produtos = new ArrayList();
        String sql = "SELECT nome_produto FROM tb_produto WHERE nome_produto like %?%";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getName());
            ResultSet rs = stmt.executeQuery();
                
            while (rs.next()) {
                Produto p = new Produto();
                
                p.setId(rs.getInt("id_produto"));
                p.setName(rs.getString("nome_produto"));
                p.setDesc(rs.getString("desc_produto"));
                p.setPrice(rs.getFloat("preco_produto"));
                p.setQuant(rs.getInt("quantidade_produto"));

                produtos.add(p);
            }
            rs.close();
            stmt.close();
            conn.close();            
        } catch (Exception e) {
            System.out.println("ERRO NA BUSCA" + e);
        }
        return produtos;
    }
}
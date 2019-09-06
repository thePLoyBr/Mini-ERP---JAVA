/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felip
 */
public class LoginDAO {
    private Connection conn;
    private PreparedStatement stmt;
    
    public LoginDAO(){
        conn = new ConnectionFactory().getConnection();
    }
    
    public void verificarlogin(){
        String sql = "SELECT * FROM tb_usuario";
        
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

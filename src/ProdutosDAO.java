/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        if (conn != null){
            try {
                
                String sql = "INSERT INTO produtos (nome, valor, status) values(?,?,?);";
                prep = conn.prepareStatement(sql);
                prep.setString(1, produto.getNome());
                prep.setObject(2, produto.getValor());
                prep.setString(3, produto.getStatus());
                
            prep.execute();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!!!");
                
            } catch (SQLException erro) {
                
                erro.printStackTrace();
                
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
            }
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}


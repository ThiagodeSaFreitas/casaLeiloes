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
import java.util.HashSet;
import java.util.Set;


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
        
          try{
            conn = new conectaDAO().connectDB();
            
            
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);
            ResultSet res = prep.executeQuery();

            
            
            
            while(res.next()){
                ProdutosDTO p = new ProdutosDTO();
                p.setId(res.getInt("id"));
                p.setNome(res.getString("nome"));
                p.setValor(res.getInt("valor"));
                p.setStatus(res.getString("status"));
                
                
                
                
                listagem.add(p);
            }
            
            prep.close();
            conn.close();
            res.close();
            
            
                }catch (SQLException e){
            System.out.println("Erro ao listar os banco de dados");
            
        }
        
        return listagem;
    }
    
     public void venderProduto(int id) {
    conn = new conectaDAO().connectDB();

    if (conn != null) {
        try {
            String sql = "UPDATE produtos SET status = 'vendido' WHERE id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            
            int rowsAffected = prep.executeUpdate();

            prep.close();
            conn.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível encontrar o produto com o ID especificado.");
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao vender o produto: " + erro.getMessage());
        }
    }
}
    
    
    
    
        
}


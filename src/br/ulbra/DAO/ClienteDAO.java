package br.ulbra.DAO;

import br.ulbra.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO  extends AbstractDAO  implements CrudRepository<Cliente>{
   
    public void salvar(Cliente c)throws SQLException{
       String sql = "INSERT INTO tbcliente (nome, email, telefone) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefone());
            int affected = ps.executeUpdate();
            if (affected == 0) throw new SQLException("Falha ao inserir cliente.");
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        }
       
    }
   
    public Cliente buscarPorId(int id) throws SQLException{
           String sql = "SELECT id, nome, email, telefone FROM tbcliente WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                    );
                }
            }
        }
        return null;
    }
    public List<Cliente>listar()throws SQLException{
        String sql="Select id,nome,email,telefone FROM tbcliente ORDER by id";
        List<Cliente>Lista=new ArrayList<>();
        try(Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
          while(rs.next()){
             Cliente c = new Cliente(
           rs.getInt("id"),  
           rs.getString("nome"),
           rs.getString("email"),
           rs.getString("telefone"));
           Lista.add(c);
             
             
          }
        }
        return Lista;
    }
    public void atualizar(Cliente c )throws SQLException{
        String sql = "UPDATE tbcliente set nome= ?,email=?,telefone=? where id=?";
        try(Connection con = getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
        ps.setString(1,c.getNome());
        ps.setString(2,c.getEmail());
        ps.setString(3,c.getTelefone());
        ps.setInt(4,c.getId());
        ps.executeUpdate();
                
        }
    }
    public void remover( int id)throws SQLException{
        String sql = "Delete FROM tbcliente where id=?";
        try(Connection con = getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
        ps.setInt(1,id);
        ps.executeUpdate();
 }
}
  
    
}
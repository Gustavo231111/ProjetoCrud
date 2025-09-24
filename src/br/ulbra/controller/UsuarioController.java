package br.ulbra.controller;

import br.ulbra.dao.UsuarioDAO;
import br.ulbra.model.Cliente;
import br.ulbra.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aluno.saolucas
 */
public class UsuarioController {
private UsuarioDAO dao= new UsuarioDAO();

public void salvar(Usuario u ) throws SQLException{
    dao.salvar(u);
}
public void buscarPorId(int id)throws SQLException{
 dao.buscarPorId(0);

}
 public List<Usuario> listar()throws SQLException{   
  return dao.listar();
 
 }
  public void atualizar(Usuario u) throws SQLException{
      dao.atualizar(u);
  }
    
public void remover(int id) throws SQLException{
    dao.remover(id);
}
  
}

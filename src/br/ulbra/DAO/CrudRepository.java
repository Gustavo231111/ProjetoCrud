
package br.ulbra.DAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository <T> {
   public void salvar(T obj)throws SQLException;
   public T buscarPorId(int id)throws SQLException;
   public List<T> listar()throws SQLException;
   public void atualizar(T obj)throws SQLException;
   public void remover(int id)throws SQLException;
   
   
}

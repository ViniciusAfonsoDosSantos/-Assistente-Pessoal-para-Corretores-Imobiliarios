/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.GenericoModel;
import br.edu.cefsa.model.Parametro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 082200014
 * @param <E> = GenericoModel
 */
public abstract class GenericoDAO<E extends GenericoModel>{
     
    protected abstract List<Parametro> preparaParametros(GenericoModel entidade, boolean update);
    protected String insertSQL;
    protected String updateSQL;
    protected String tabela;
    
    public void inserir(GenericoModel entidade) throws PersistenciaException{
        HelperDAO.executaQuery(insertSQL, preparaParametros(entidade, false)); 
    }
    
    public void alterar(GenericoModel entidade) throws PersistenciaException{
        HelperDAO.executaQuery(updateSQL, preparaParametros(entidade, false));
    }

    public ResultSet listar() throws PersistenciaException {
        String sql = String.format("SELECT * FROM %s", this.tabela);
        return HelperDAO.executaSelect(sql, null);
    }

    public void remover(GenericoModel e) throws PersistenciaException {
         String sql = String.format("DELETE FROM %s WHERE ID=?", this.tabela);
         List<Parametro> parametro = new ArrayList<>();
         
         parametro.add(new Parametro(Integer.toString(e.getID()), "long"));
         HelperDAO.executaQuery(sql, parametro);
    }

    public ResultSet listarPorID(GenericoModel e) throws PersistenciaException {
        String sql = String.format("SELECT * FROM %s WHERE ID=?", this.tabela);
        List<Parametro> parametro = new ArrayList<>(); 
        parametro.add(new Parametro(Integer.toString(e.getID()), "long"));
        return HelperDAO.executaSelect(sql, parametro);
    }
}

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
import java.text.ParseException;
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
     
    protected abstract List<Parametro> preparaParametros(E entidade, boolean update);
    private String insertSQL;
    private String updateSQL;
    private String tabela;

    protected void setInsertSQL(String insertSQL) {
        this.insertSQL = insertSQL;
    }

    protected void setUpdateSQL(String updateSQL) {
        this.updateSQL = updateSQL;
    }

    protected void setTabela(String tabela) {
        this.tabela = tabela;
    }
    
    public void inserir(E entidade) throws PersistenciaException, ParseException{
        HelperDAO.executaQuery(insertSQL, preparaParametros(entidade, false)); 
    }
    
    public void alterar(E entidade) throws PersistenciaException, ParseException{
        HelperDAO.executaQuery(updateSQL, preparaParametros(entidade, true));
    }
    
    public void remover(GenericoModel e) throws PersistenciaException, ParseException {
         String sql = String.format("DELETE FROM %s WHERE ID=?", this.tabela);
         List<Parametro> parametro = new ArrayList<>();
         
         parametro.add(new Parametro(Integer.toString(e.getID()), "long"));
         HelperDAO.executaQuery(sql, parametro);
    }
}

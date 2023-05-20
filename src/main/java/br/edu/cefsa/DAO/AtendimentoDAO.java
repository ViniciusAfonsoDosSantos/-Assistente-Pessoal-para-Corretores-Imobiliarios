/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Atendimento;
import br.edu.cefsa.model.Parametro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class AtendimentoDAO<A extends Atendimento> extends GenericoDAO<A> implements IGenericoDAO<A> {
    
    public AtendimentoDAO(){
        super.setTabela("ASSISTENTECORRETORES.Atendimento");
        super.setInsertSQL("INSERT INTO ASSISTENTECORRETORES.ATENDIMENTO (CLIENTE_ID,IMOVEL_ID,DATA_ATENDIMENTO,ANOTACAO_ATENDIMENTO) VALUES (?,?,?,?)");
        super.setUpdateSQL("UPDATE ASSISTENTECORRETORES.ATENDIMENTO SET " + 
                        "CLIENTE_ID=? , " +
                        "IMOVEL_ID=? , " +
                        "DATA_ATENDIMENTO=? , " +
                        "ANOTACAO_ATENDIMENTO=? " +
                        "WHERE ATENDIMENTO_ID = ?");                    
    }
    
    @Override
    protected List<Parametro> preparaParametros(A atendimento, boolean update) {
        List parametros = new ArrayList();
        parametros.add(new Parametro(String.valueOf(atendimento.getClienteID()), "texto"));
        parametros.add(new Parametro(String.valueOf(atendimento.getImovelID()), "texto"));
        parametros.add(new Parametro(atendimento.getDataAtendimento(), "data"));
        parametros.add(new Parametro(atendimento.getAnotacoes(), "texto"));
        if(update){
            parametros.add(new Parametro(String.valueOf(atendimento.getID()), "long"));
        }
        return parametros;}

    @Override
    public List listar() throws PersistenciaException {
        List<Atendimento> atendimentos = new ArrayList<Atendimento>();
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Atendimento";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                atendimentos.add(new Atendimento(
                        result.getDate("DATA_ATENDIMENTO").toLocalDate(), 
                        result.getString("ANOTACAO_ATENDIMENTO"), 
                        result.getInt("CLIENTE_ID"),
                        result.getInt("IMOVEL_ID")
                        ));
            }
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atendimentos;
    }

    @Override
    public void remover(A e) throws PersistenciaException {
        String sql = "DELETE FROM ASSISTENTECORRETORES.Atendimento WHERE ATENDIMENTO_ID= ? ";
        List parametro = new ArrayList();
        parametro.add(new Parametro(Integer.toString(e.getAtendimentoId()), "long"));
        Integer Teste = e.getAtendimentoId();
        try {
            HelperDAO.executaQuery(sql,parametro);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Atendimento listarPorID(Atendimento e) throws PersistenciaException {
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Atendimento WHERE Atendimento_ID=?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1,e.getID());
            ResultSet result = pStatement.executeQuery();
            
            if (result.next()) {
                return new Atendimento(
                        result.getDate("DATA_ATENDIMENTO").toLocalDate(), 
                        result.getString("ANOTACAO_ATENDIMENTO"), 
                        result.getInt("CLIENTE_ID"),
                        result.getInt("IMOVEL_ID"));
            }
            else
                return null;
            }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public int listarUltimoAtendimento() throws PersistenciaException {
        String sql = "select MAX(ATENDIMENTO_ID) AS ATENDIMENTO_ID from ASSISTENTECORRETORES.ATENDIMENTO";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            
            if (result.next()) {
                
                return result.getInt("ATENDIMENTO_ID");
                                   
            }
            else
                return 0;
            }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    public List listarPorData(LocalDate e) throws PersistenciaException {
        List<Atendimento> atendimentos = new ArrayList<Atendimento>();
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Atendimento WHERE DATA_ATENDIMENTO=?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            Date date = java.sql.Date.valueOf(e);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pStatement.setDate(1, sqlDate);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                atendimentos.add(new Atendimento(
                        result.getInt("Atendimento_ID"),
                        result.getDate("DATA_ATENDIMENTO").toLocalDate(), 
                        result.getString("ANOTACAO_ATENDIMENTO"), 
                        result.getInt("CLIENTE_ID"),
                        result.getInt("IMOVEL_ID")
                        ));
            }
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atendimentos;
    }
}

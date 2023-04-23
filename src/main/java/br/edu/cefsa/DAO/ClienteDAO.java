/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cliente;
import br.edu.cefsa.model.GenericoModel;
import br.edu.cefsa.model.Parametro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 * @param <C>
 * @param <Cliente>
 */
public class ClienteDAO<C extends Cliente> extends GenericoDAO<C> implements IGenericoDAO<C>{

    public ClienteDAO() {
        super.setTabela("ASSISTENTECORRETORES.CLIENTE");
        super.setInsertSQL("INSERT INTO ASSISTENTECORRETORES.CLIENTE (Nome, CPF, Data_nascimento, Conjuge,Profissao, Telefone, Email, EnderecoResidencial, Estado, Cidade, Bairro, CEP, Anotacoes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
        super.setUpdateSQL("UPDATE ASSISTENTECORRETORES.CLIENTE "
                        + "SET NOME=?,"
                        + "CPF=? "
                        + "Data_nascimento=? "
                        + "Conjuge=? "
                        + "Profissao=? "
                        + "Telefone=? "
                        + "Email=? "
                        + "EnderecoResidencial=? "
                        + "Estado=? "
                        + "Cidade=? "
                        + "Bairro=? "
                        + "CEP=? "
                        + "Anotacoes=? "
                        + "WHERE CLIENTE_ID = ?");
    }
    
    @Override
    protected List preparaParametros(Cliente cliente, boolean update) {
        List parametros = new ArrayList();
        parametros.add(new Parametro(cliente.getNome(), "texto"));
        parametros.add(new Parametro(cliente.getCPF(), "texto"));
        parametros.add(new Parametro(cliente.getDataNascimento(), "data"));
        parametros.add(new Parametro(cliente.getConjuge(), "texto"));
        parametros.add(new Parametro(cliente.getProfissao(), "texto"));
        parametros.add(new Parametro(cliente.getTelefone(), "texto"));
        parametros.add(new Parametro(cliente.getEmail(), "texto"));
        parametros.add(new Parametro(cliente.getEnderecoResidencial(), "texto"));
        parametros.add(new Parametro(cliente.getEstado(), "texto"));
        parametros.add(new Parametro(cliente.getCidade(), "texto"));
        parametros.add(new Parametro(cliente.getBairro(), "texto"));
        parametros.add(new Parametro(cliente.getCEP(), "texto"));
        parametros.add(new Parametro(cliente.getAnotacao(),"texto"));
        if(update){
            parametros.add(new Parametro(String.valueOf(cliente.getID()), "long"));
        }
        return parametros;
    }
    
    @Override
    public List listar() throws PersistenciaException{
        List<Cliente> clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Cliente";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            
            while (result.next()) {
                clientes.add(new Cliente(
                        result.getString("NOME"), 
                        result.getString("CPF"), 
                        result.getDate("DATA_NASCIMENTO").toLocalDate(), 
                        result.getString("CONJUGE"),
                        result.getString("PROFISSAO"),
                        result.getString("TELEFONE"),
                        result.getString("EMAIL"),
                        result.getString("ENDERECORESIDENCIAL"),
                        result.getString("CEP"),
                        result.getString("ESTADO"),
                        result.getString("CIDADE"),
                        result.getString("BAIRRO"),
                        result.getString("ANOTACOES")));
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
        return clientes;
    }

    public Cliente listarPorID(Cliente c){
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Cliente WHERE CLIENTE_ID=?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1,c.getID());
            ResultSet result = pStatement.executeQuery();
            
            if (result.next()) {
                return new Cliente(
                        result.getString("NOME"), 
                        result.getString("CPF"), 
                        result.getDate("DATA_NASCIMENTO").toLocalDate(), 
                        result.getString("CONJUGE"),
                        result.getString("PROFISSAO"),
                        result.getString("TELEFONE"),
                        result.getString("EMAIL"),
                        result.getString("ENDERECORESIDENCIAL"),
                        result.getString("CEP"),
                        result.getString("ESTADO"),
                        result.getString("CIDADE"),
                        result.getString("BAIRRO"),
                        result.getString("ANOTACOES"));
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

    @Override
    public void remover(C e) throws PersistenciaException {
        String sql = "DELETE FROM ASSISTENTECORRETORES.Cliente WHERE CLIENTE_ID= ? ";
        List parametro = new ArrayList();
        parametro.add(new Parametro(Integer.toString(e.getID()), "long"));
        try {
            HelperDAO.executaQuery(sql,parametro);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

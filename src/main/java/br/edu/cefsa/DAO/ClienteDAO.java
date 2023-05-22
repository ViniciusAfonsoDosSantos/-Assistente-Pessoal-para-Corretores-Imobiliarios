/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cliente;
import br.edu.cefsa.model.GenericoModel;
import br.edu.cefsa.model.ImovelProcuradoCliente;
import br.edu.cefsa.model.Parametro;
import br.edu.cefsa.model.Usuario;
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
        super.setInsertSQL("INSERT INTO ASSISTENTECORRETORES.CLIENTE (Nome," +
"                                        CPF," +
"                                        Data_nascimento," +
"                                        Conjuge," +
"                                        Profissao," +
"                                        Telefone," +
"                                        Email," +
"                                        EnderecoResidencial," +
"                                        Estado," +
"                                        Cidade," +
"                                        Bairro," +
"                                        CEP," +
"                                        TIPO_IMOVEL," +
"                                        TIPO_AQUISICAO," +
"                                        NUM_DORMS_PROCURADO," +
"                                        NUM_VAGAS_PROCURADO," +
"                                        METRAGEM_PROCURADO," +
"                                        BAIRROS," +
"                                        CONDICOES," +
"                                        FAIXA_PRECO," +
"                                        ESTADO_CLIENTE)" +
"                                    VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        super.setUpdateSQL("UPDATE ASSISTENTECORRETORES.CLIENTE "
                        + "SET Nome=?,"
                        + "CPF=?, "
                        + "Data_nascimento=?, "
                        + "Conjuge=?, "
                        + "Profissao=?, "
                        + "Telefone=?, "
                        + "Email=?, "
                        + "EnderecoResidencial=?, "
                        + "Estado=?, "
                        + "Cidade=?, "
                        + "Bairro=?, "
                        + "CEP=?, "
                        + "TIPO_IMOVEL=?,"
                        + "TIPO_AQUISICAO=?,"
                        + "NUM_DORMS_PROCURADO=?,"
                        + "NUM_VAGAS_PROCURADO=?,"
                        + "METRAGEM_PROCURADO=?,"
                        + "BAIRROS=?,"
                        + "CONDICOES=?,"
                        + "FAIXA_PRECO=?,"
                        + "ESTADO_CLIENTE=?"
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
        parametros.add(new Parametro(cliente.getImovelProcuradoCliente().getTipoImovel(), "texto"));
        parametros.add(new Parametro(cliente.getImovelProcuradoCliente().getTipoAquisicao(), "texto"));
        parametros.add(new Parametro(String.valueOf(cliente.getImovelProcuradoCliente().getNumDorms()), "texto"));
        parametros.add(new Parametro(String.valueOf(cliente.getImovelProcuradoCliente().getNumVagas()), "texto"));
        parametros.add(new Parametro(String.valueOf(cliente.getImovelProcuradoCliente().getMetragem()), "texto"));
        parametros.add(new Parametro(cliente.getImovelProcuradoCliente().getBairros(), "texto"));
        parametros.add(new Parametro(cliente.getImovelProcuradoCliente().getCondicoes(), "texto"));
        parametros.add(new Parametro(cliente.getImovelProcuradoCliente().getFaixaPreco(), "texto"));
        parametros.add(new Parametro(cliente.getEstadoCliente(), "texto"));
        
        if(update){
            parametros.add(new Parametro(String.valueOf(cliente.getClienteId()), "long"));
        }
        return parametros;
    }
    
//////    @Override
    public List listar() throws PersistenciaException{
        List<Cliente> clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Cliente";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            
            while (result.next()) {
                ImovelProcuradoCliente imovelProcuradoCliente= new ImovelProcuradoCliente();
                imovelProcuradoCliente.setTipoImovel(result.getString("TIPO_IMOVEL"));
                imovelProcuradoCliente.setTipoAquisicao(result.getString("TIPO_AQUISICAO"));
                imovelProcuradoCliente.setNumDorms(result.getInt("NUM_DORMS_PROCURADO"));
                imovelProcuradoCliente.setNumVagas(result.getInt("NUM_VAGAS_PROCURADO"));
                imovelProcuradoCliente.setMetragem(result.getDouble("METRAGEM_PROCURADO"));
                imovelProcuradoCliente.setBairros(result.getString("BAIRROS"));
                imovelProcuradoCliente.setCondicoes(result.getString("CONDICOES"));
                imovelProcuradoCliente.setFaixaPreco(result.getString("FAIXA_PRECO"));
                
                clientes.add(new Cliente(
                        Integer.valueOf(result.getString("CLIENTE_ID")), 
                        result.getString("NOME"), 
                        result.getString("CPF"), 
                        result.getDate("DATA_NASCIMENTO").toLocalDate(), 
                        result.getString("CONJUGE"),
                        result.getString("PROFISSAO"),
                        result.getString("TELEFONE"),
                        result.getString("EMAIL"),
                        result.getString("CEP"),
                        result.getString("ENDERECORESIDENCIAL"),
                        result.getString("ESTADO"),
                        result.getString("CIDADE"),
                        result.getString("BAIRRO"),
                        imovelProcuradoCliente));
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
            pStatement.setLong(1,c.getClienteId());
            ResultSet result = pStatement.executeQuery();
            
            if (result.next()) {
                ImovelProcuradoCliente imovelProcuradoCliente= new ImovelProcuradoCliente();
                imovelProcuradoCliente.setTipoImovel(result.getString("TIPO_IMOVEL"));
                imovelProcuradoCliente.setTipoAquisicao(result.getString("TIPO_AQUISICAO"));
                imovelProcuradoCliente.setNumDorms(result.getInt("NUM_DORMS_PROCURADO"));
                imovelProcuradoCliente.setNumVagas(result.getInt("NUM_VAGAS_PROCURADO"));
                imovelProcuradoCliente.setMetragem(result.getDouble("METRAGEM_PROCURADO"));
                imovelProcuradoCliente.setBairros(result.getString("BAIRROS"));
                imovelProcuradoCliente.setCondicoes(result.getString("CONDICOES"));
                imovelProcuradoCliente.setFaixaPreco(result.getString("FAIXA_PRECO"));
                return new Cliente(
                        result.getString("NOME"), 
                        result.getString("CPF"), 
                        result.getDate("DATA_NASCIMENTO").toLocalDate(), 
                        result.getString("CONJUGE"),
                        result.getString("PROFISSAO"),
                        result.getString("TELEFONE"),
                        result.getString("EMAIL"),
                        result.getString("CEP"),
                        result.getString("ENDERECORESIDENCIAL"),
                        result.getString("ESTADO"),
                        result.getString("CIDADE"),
                        result.getString("BAIRRO"),
                        imovelProcuradoCliente);
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
        parametro.add(new Parametro(Integer.toString(e.getClienteId()), "long"));
        try {
            HelperDAO.executaQuery(sql,parametro);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Boolean vericaId(Cliente cliente) throws PersistenciaException, SQLException{
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Cliente WHERE CLIENTE_ID = ? ";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            try{
                pStatement.setString(1, Integer.toString(cliente.getClienteId()));  
            }
            catch(Exception ex){
                return true;
            }
            ResultSet result = pStatement.executeQuery();
            if(result == null){
                return true;
            }
            else
                return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
                connection.close();
        }
    }
}

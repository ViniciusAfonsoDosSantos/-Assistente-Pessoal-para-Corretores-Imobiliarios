/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cliente;
import br.edu.cefsa.model.GenericoModel;
import br.edu.cefsa.model.Parametro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 * @param <C>
 * @param <Cliente>
 */
public class ClienteDAO<C extends Cliente> extends GenericoDAO<C>{

    public ClienteDAO() {
        super.setTabela("ASSISTENTECORRETORES.CLIENTE");
        super.setInsertSQL("INSERT INTO ASSISTENTECORRETORES.CLIENTE"
                        + "(Nome, CPF, Data_nascimento, Conjuge, "
                        + "Profissao, Telefone, Email, EnderecoResidencial, "
                        + "Estado, Cidade, Bairro, CEP, Anotacoes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
        parametros.add(new Parametro(cliente.getCPF(), "long"));
        parametros.add(new Parametro(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), "data"));
        parametros.add(new Parametro(cliente.getConjuge(), "texto"));
        parametros.add(new Parametro(cliente.getProfissao(), "texto"));
        parametros.add(new Parametro(cliente.getTelefone(), "long"));
        parametros.add(new Parametro(cliente.getEmail(), "texto"));
        parametros.add(new Parametro(cliente.getEnderecoResidencial(), "texto"));
        parametros.add(new Parametro(cliente.getEstado(), "texto"));
        parametros.add(new Parametro(cliente.getCidade(), "texto"));
        parametros.add(new Parametro(cliente.getBairro(), "texto"));
        parametros.add(new Parametro(cliente.getAnotacao(),"texto"));
        if(update){
            parametros.add(new Parametro(String.valueOf(cliente.getID()), "long"));
        }
        return parametros;
    }
    
    public List listarClientes() throws PersistenciaException, SQLException{
        List<Cliente> clientes = new ArrayList();
        List result = super.listar();
        return null;
//        while(result.next()){
//            clientes.add(new Cliente(
//                    result.getString("NOME"),
//                    result.getString("CPF"),
//                    result.getDate("Data_nascimento").toLocalDate(),
//                    result.getString("Conjuge"),
//                    result.getString("Profissao"),
//                    result.getString("Telefone"),
//                    result.getString("Email"),
//                    result.getString("EnderecoResidencial"),
//                    result.getString("CEP"),
//                    result.getString("Estado"),
//                    result.getString("Cidade"),
//                    result.getString("Bairro"),
//                    result.getString("Anotacoes")
//            ));
        }
//        return clientes;
    
    
    public Cliente listarCliente(Cliente c){
//        ResultSet result = super.listarPorID(c);
//        if(result.next()){
//                return new Cliente(
//                    result.getString("NOME"),
//                    result.getString("CPF"),
//                    result.getDate("Data_nascimento").toLocalDate(),
//                    result.getString("Conjuge"),
//                    result.getString("Profissao"),
//                    result.getString("Telefone"),
//                    result.getString("Email"),
//                    result.getString("EnderecoResidencial"),
//                    result.getString("CEP"),
//                    result.getString("Estado"),
//                    result.getString("Cidade"),
//                    result.getString("Bairro"),
//                    result.getString("Anotacoes")); 
//            }
//            else
//                return null;
        return null;
        }
    
}

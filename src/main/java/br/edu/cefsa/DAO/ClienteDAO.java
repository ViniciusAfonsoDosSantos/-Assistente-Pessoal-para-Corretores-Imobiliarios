/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.model.Cliente;
import br.edu.cefsa.model.GenericoModel;
import br.edu.cefsa.model.Parametro;
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
        parametros.add(new Parametro(cliente.Nome, "texto"));
        parametros.add(new Parametro(cliente.CPF, "long"));
        parametros.add(new Parametro(cliente.DataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), "data"));
        parametros.add(new Parametro(cliente.Conjuge, "texto"));
        parametros.add(new Parametro(cliente.Profissao, "texto"));
        parametros.add(new Parametro(cliente.Telefone, "long"));
        parametros.add(new Parametro(cliente.Email, "texto"));
        parametros.add(new Parametro(cliente.EnderecoResidencial, "texto"));
        parametros.add(new Parametro(cliente.Estado, "texto"));
        parametros.add(new Parametro(cliente.Cidade, "texto"));
        parametros.add(new Parametro(cliente.Bairro, "texto"));
        parametros.add(new Parametro(cliente.Anotacao,"texto"));
        if(update){
            parametros.add(new Parametro(String.valueOf(cliente.getID()), "long"));
        }
        return parametros;
    }
}

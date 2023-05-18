/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cliente;
import br.edu.cefsa.model.Imovel;
import br.edu.cefsa.model.Parametro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class ImovelDAO<I extends Imovel> extends GenericoDAO<I> implements IGenericoDAO<I>{
    public ImovelDAO(){
        super.setTabela("ASSISTENTECORRETORES.IMOVEL");                  
        super.setInsertSQL("INSERT INTO ASSISTENTECORRETORES.IMOVEL (NOME,Tipo_Imovel,Venda,Locacao,Num_Dorms,Num_Vagas,METRAGEM,RUA,BAIRRO,CIDADE,CEP,COMPLEMENTO,CARACTERISTICAS,PRAZO_ENTREGA,FAIXA_PRECO,Imagem1,Imagem2,Imagem3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        super.setUpdateSQL("UPDATE ASSISTENTECORRETORES.IMOVEL "
                        + "NOME=?," +
                        "Tipo_Imovel=?," +
                        "Tipo_Locacao=?," +
                        "Num_Dorms=?," +
                        "Num_Vagas=?," +
                        "METRAGEM=?," +
                        "RUA=?," +
                        "BAIRRO=?," +
                        "CIDADE=?," +
                        "CEP=?," +
                        "COMPLEMENTO=?," +
                        "CARACTERISTICAS=?," +
                        "PRAZO_ENTREGA=?," +
                        "FAIXA_PRECO=?," +
                        "Imagem1=?," +
                        "Imagem2=?," +
                        "Imagem3=?" +
                        "WHERE IMOVEL_ID = ?");                    
    }
    
    @Override
    protected List<Parametro> preparaParametros(I imovel, boolean update) {
        List parametros = new ArrayList();
        parametros.add(new Parametro(imovel.getNome(), "texto"));
        parametros.add(new Parametro(imovel.getTipoImovel(), "texto"));
        parametros.add(new Parametro(imovel.isVenda(), "boolean"));
        parametros.add(new Parametro(imovel.isLocacao(), "boolean"));
        parametros.add(new Parametro(String.valueOf(imovel.getNumDorms()), "texto"));
        parametros.add(new Parametro(String.valueOf(imovel.getNumVagas()), "texto"));
        parametros.add(new Parametro(String.valueOf(imovel.getMetragem()), "texto"));
        parametros.add(new Parametro(imovel.getRua(), "texto"));
        parametros.add(new Parametro(imovel.getBairro(), "texto"));
        parametros.add(new Parametro(imovel.getCidade(), "texto"));
        parametros.add(new Parametro(imovel.getCEP(), "texto"));
        parametros.add(new Parametro(imovel.getComplemento(), "texto"));
        parametros.add(new Parametro(imovel.getCaracteristicas(), "texto"));
        parametros.add(new Parametro(imovel.getPrazoEntrega(), "data"));
        parametros.add(new Parametro(imovel.getFaixaPreco(), "texto"));
        parametros.add(new Parametro(imovel.getImagem1(), "byte"));
        parametros.add(new Parametro(imovel.getImagem2(), "byte"));
        parametros.add(new Parametro(imovel.getImagem3(), "byte"));
        if(update){
            parametros.add(new Parametro(String.valueOf(imovel.getID()), "long"));
        }
        return parametros;}

    @Override
    public List listar() throws PersistenciaException {
        List<Imovel> imoveis = new ArrayList<Imovel>();
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Imovel";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                imoveis.add(new Imovel(
                        result.getString("NOME"), 
                        result.getString("TIPO_IMOVEL"), 
                        result.getBoolean("VENDA"),
                        result.getBoolean("LOCACAO"),
                        result.getInt("NUM_DORMS"),
                        result.getInt("NUM_VAGAS"),
                        result.getDouble("METRAGEM"),
                        result.getString("RUA"),
                        result.getString("BAIRRO"),
                        result.getString("CIDADE"),
                        result.getString("CEP"),
                        result.getString("COMPLEMENTO"),
                        result.getString("CARACTERISTICAS"),
                        result.getDate("PRAZO_ENTREGA").toLocalDate(),
                        result.getString("FAIXA_PRECO"),
                        result.getBytes("Imagem1"),
                        result.getBytes("Imagem2"),
                        result.getBytes("Imagem3")
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
        return imoveis;
    }

    @Override
    public void remover(I e) throws PersistenciaException {
        String sql = "DELETE FROM ASSISTENTECORRETORES.Imovel WHERE Imovel_ID= ? ";
        List parametro = new ArrayList();
        parametro.add(new Parametro(Integer.toString(e.getID()), "long"));
        try {
            HelperDAO.executaQuery(sql,parametro);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Imovel listarPorID(Imovel e) throws PersistenciaException {
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Imovel WHERE Imovel_ID=?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1,e.getID());
            ResultSet result = pStatement.executeQuery();
            
            if (result.next()) {
                return new Imovel(
                        result.getString("NOME"), 
                        result.getString("TIPO_IMOVEL"), 
                        result.getBoolean("VENDA"),
                        result.getBoolean("LOCACAO"),
                        result.getInt("NUM_DORMS"),
                        result.getInt("NUM_VAGAS"),
                        result.getDouble("METRAGEM"),
                        result.getString("RUA"),
                        result.getString("BAIRRO"),
                        result.getString("CIDADE"),
                        result.getString("CEP"),
                        result.getString("COMPLEMENTO"),
                        result.getString("CARACTERISTICAS"),
                        result.getDate("PRAZO_ENTREGA").toLocalDate(),
                        result.getString("FAIXA_PRECO"),
                        result.getBytes("Imagem1"),
                        result.getBytes("Imagem2"),
                        result.getBytes("Imagem3")
                        );
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
    
}

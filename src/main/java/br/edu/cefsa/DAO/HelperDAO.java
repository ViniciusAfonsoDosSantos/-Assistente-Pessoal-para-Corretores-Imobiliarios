/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Parametro;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 082200014
 */
public class HelperDAO {
     private static PreparedStatement organizaParametros(PreparedStatement statement, List<Parametro> parametros) throws SQLException, ParseException{
        if(parametros != null){
            for(int i = 0; i < parametros.size(); i++){
                String pTipo = parametros.get(i).tipo;
                String pValor = parametros.get(i).valor;
                LocalDate pData = parametros.get(i).date;
                byte[] pByte = null;
                if("byte".equals(pTipo)){
                    pByte = parametros.get(i).bytes;
                }
                if("texto".equals(pTipo)){
                    statement.setString(i+1, pValor);
                }
                if("long".equals(pTipo)){
                    statement.setLong(i+1, Long.parseLong(pValor));
                }
                if("boolean".equals(pTipo)){
                    statement.setBoolean(i+1, Boolean.parseBoolean(pValor));
                }
                if("inteiro".equals(pTipo)){
                    int valor = Integer.parseInt(pValor);
                    statement.setInt(i+1, valor);
                }
                if("decimal".equals(pTipo)){
                    double valor = Double.parseDouble(pValor);
                    statement.setDouble(i+1, valor);
                }
                if("data".equals(pTipo)){  
                    statement.setDate(i+1, java.sql.Date.valueOf(pData));
                }
                if("byte".equals(pTipo)){
                    statement.setBlob(i+1, new ByteArrayInputStream(pByte));
                }
            }
        }
        return statement;
    }
    
    public static void executaQuery(String query, List<Parametro> parametrosStatement) throws PersistenciaException, ParseException {
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            PreparedStatement newStatement = organizaParametros(pStatement, parametrosStatement);
            newStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            //.getLogger(UsuarioDAO.class.getName()).log(LEVEL.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}

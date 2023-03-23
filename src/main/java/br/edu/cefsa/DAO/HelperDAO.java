/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author 082200014
 */
public class HelperDAO {
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        return Conexao.getInstance().getConnection();
    }
    
    public static void ExecutaComandoSQL(String sqlQuery){
        
    } 
}

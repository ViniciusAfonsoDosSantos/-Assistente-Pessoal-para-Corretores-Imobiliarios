/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author gabri
 */
public class UsuarioDAO implements IGenericoDAO<Usuario>{
    
    @Override
    public List listar() throws PersistenciaException {
        List<Usuario> users = new ArrayList<Usuario>();
		String sql = "SELECT * FROM ASSISTENTECORRETORES.Usuario";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                users.add(new Usuario(result.getString("NOME"), result.getString("EMAIL"), result.getString("SENHA"), result.getBoolean("TIPO")));
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
        return users;
    }
    
    @Override
    public void alterar(Usuario usuario) throws PersistenciaException {
        //troca nome e senha
        String sql = "UPDATE ASSISTENTECORRETORES.Usuario SET NOME=?,SENHA=? WHERE EMAIL = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getNome());
            pStatement.setString(2, usuario.getSenha());
            pStatement.setString(3, usuario.getEmail());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public void alterarTipo(Usuario usuario) throws PersistenciaException {
        //troca nome e senha
        String sql = "UPDATE ASSISTENTECORRETORES.Usuario SET TIPO=? WHERE EMAIL = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setBoolean(1, usuario.getTipo());
            pStatement.setString(2, usuario.getEmail());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void remover(Usuario usuario) throws PersistenciaException {
        String sql = "DELETE FROM ASSISTENTECORRETORES.Usuario WHERE EMAIL= ? ";
        Connection connection = null;
        try{
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getEmail());
            pStatement.execute();
        }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Usuario listarPorID(Usuario usuario) throws PersistenciaException {
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Usuario WHERE USER_ID = ? ";
        
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, usuario.ID);
            ResultSet result = pStatement.executeQuery();
            if(result.next()){
                return new Usuario(result.getString("NOME"), result.getString("EMAIL"), result.getString("SENHA"), result.getBoolean("TIPO"));
            }
            else
                return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Usuario listaPorEmail(String email) throws PersistenciaException{
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Usuario WHERE Email = ? ";
        
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, email);
            ResultSet result = pStatement.executeQuery();
            if(result.next()){
                return new Usuario(result.getString("NOME"), result.getString("EMAIL"), result.getString("SENHA"), result.getBoolean("TIPO"));
            }
            else
                return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void inserir(Usuario usuario) throws PersistenciaException {
        String sql = "INSERT INTO ASSISTENTECORRETORES.USUARIO(Nome, Email, Senha, Tipo) VALUES (?,?,?,?)";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getNome());
            pStatement.setString(2, usuario.getEmail());
            pStatement.setString(3, usuario.getSenha());
            pStatement.setBoolean(4, usuario.getTipo());
            pStatement.execute();
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
        }/*
        public static UsuarioDAO getNewInstance(){
            return new
        }*/
    }
}

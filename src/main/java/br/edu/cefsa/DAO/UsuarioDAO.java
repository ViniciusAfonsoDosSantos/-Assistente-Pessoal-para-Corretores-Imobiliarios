/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Parametro;
import br.edu.cefsa.model.Usuario;
import br.edu.cefsa.utils.PasswordUtils;
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
public class UsuarioDAO<U extends Usuario> extends GenericoDAO<U>{

    public UsuarioDAO() {
        super.setTabela("ASSISTENTECORRETORES.Usuario");
        super.setInsertSQL("INSERT INTO ASSISTENTECORRETORES.USUARIO(Nome, Email, Senha, Tipo) VALUES (?,?,?,?)");
        super.setUpdateSQL("UPDATE ASSISTENTECORRETORES.Usuario SET NOME=?,SENHA=? WHERE EMAIL = ?");
    }
    
    @Override
    protected List<Parametro> preparaParametros(U usuario, boolean update) {
        List parametros = new ArrayList();
        parametros.add(new Parametro(usuario.getNome(), "texto"));
        if(!update)
            parametros.add(new Parametro(usuario.getEmail(), "texto"));
        parametros.add(new Parametro(usuario.getSenha(), "texto"));
        if(!update)
            parametros.add(new Parametro(String.valueOf(usuario.getTipo()), "boolean"));
        if(update){
            parametros.add(new Parametro(usuario.getEmail(), "texto"));
        }
        return parametros;
    }
    
    public List listarUsuarios() throws PersistenciaException, SQLException {
        List<Usuario> users = new ArrayList<>();
//        ResultSet result = super.listar();
//        while (result.next()) {
//                users.add(new Usuario(result.getString("NOME"), result.getString("EMAIL"), result.getString("SENHA"), result.getBoolean("TIPO")));
//            }
//        return users;
        return null;
    }
    
    public void alterarTipo(Usuario usuario) throws PersistenciaException {
        //troca nome e senha
        String sql = "UPDATE ASSISTENTECORRETORES.Usuario SET TIPO=? WHERE EMAIL = ?";
        List parametros = new ArrayList();
        parametros.add(new Parametro(String.valueOf(usuario.getTipo()), "boolean"));
        parametros.add(new Parametro(usuario.getEmail(), "texto"));
        HelperDAO.executaQuery(sql, parametros);
    }
    
    public void remover(Usuario usuario) throws PersistenciaException {
        String sql = "DELETE FROM ASSISTENTECORRETORES.Usuario WHERE EMAIL= ? ";
        List parametro = new ArrayList();
        parametro.add(new Parametro(usuario.getEmail(), "texto"));
        HelperDAO.executaQuery(sql,parametro);
    }
    
    public Usuario listaPorEmail(String email) throws PersistenciaException, SQLException{
        String sql = "SELECT * FROM ASSISTENTECORRETORES.Usuario WHERE Email = ? ";
        List parametro = new ArrayList();
        parametro.add(new Parametro(email, "texto"));
        List result = HelperDAO.executaSelect(sql, parametro);
        return null;
    }
}

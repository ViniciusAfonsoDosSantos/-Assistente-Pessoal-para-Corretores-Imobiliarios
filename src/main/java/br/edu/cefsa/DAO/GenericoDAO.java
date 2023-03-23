/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 082200014
 */
public abstract class GenericoDAO<E>{
    protected String tabela;
    protected PreparedStatement parametrosSQL;
    
    List<E> listar() throws PersistenciaException{
        String sql = "SELECT * FROM "
    }
    
    void inserir(E e) throws PersistenciaException{
    
    }
    
    void alterar(E e) throws PersistenciaException{
    
    }
    
    void remover(E e) throws PersistenciaException{
    
    }
    
    E listarPorID(E e) throws PersistenciaException{
    
    }
    
}

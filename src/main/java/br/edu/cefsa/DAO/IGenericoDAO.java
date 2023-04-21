/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.DAO;

import br.edu.cefsa.exception.PersistenciaException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public interface IGenericoDAO<E> extends Serializable {

    List<E> listar() throws PersistenciaException;
    
    void remover(E e) throws PersistenciaException;

    E listarPorID(E e) throws PersistenciaException;
}



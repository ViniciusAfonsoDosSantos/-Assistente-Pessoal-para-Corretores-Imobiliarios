/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.Usuario;

/**
 *
 * @author Vinicius
 */
public class DadoUsuarioLogado {
    
    private static final DadoUsuarioLogado instancia = new DadoUsuarioLogado();
    
    private Usuario usuarioLogado;
    
    private DadoUsuarioLogado(){}
    
    public static DadoUsuarioLogado getInstancia(){
        return instancia;
    }
    
    public Usuario getUsuario(){
        return usuarioLogado;
    }
    
    public void setUsuario(Usuario usuarioLogado){
        this.usuarioLogado = usuarioLogado;
    }
}

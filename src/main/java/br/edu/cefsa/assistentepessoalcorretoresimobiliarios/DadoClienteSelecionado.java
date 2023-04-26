/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.Cliente;

/**
 *
 * @author Vinicius
 */
public class DadoClienteSelecionado {
    
    private static final DadoClienteSelecionado instancia = new DadoClienteSelecionado();
    
    private Cliente clienteSelecionado;
    
    private DadoClienteSelecionado(){}
    
    public static DadoClienteSelecionado getInstancia(){
        return instancia;
    }
    
    public Cliente getCliente(){
        return clienteSelecionado;
    }
    
    public void setCliente(Cliente clienteSelecionado){
        this.clienteSelecionado = clienteSelecionado;
    }
}

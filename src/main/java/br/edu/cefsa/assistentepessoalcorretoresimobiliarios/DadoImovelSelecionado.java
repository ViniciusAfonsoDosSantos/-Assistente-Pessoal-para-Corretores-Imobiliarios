/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.Imovel;

/**
 *
 * @author Vinicius
 */
public class DadoImovelSelecionado {
    
    private static final DadoImovelSelecionado instancia = new DadoImovelSelecionado();
    
    private Imovel imovelSelecionado;
    
    private DadoImovelSelecionado(){}
    
    public static DadoImovelSelecionado getInstancia(){
        return instancia;
    }
    
    public Imovel getImovel(){
        return imovelSelecionado;
    }
    
    public void setImovel(Imovel imovelSelecionado){
        this.imovelSelecionado = imovelSelecionado;
    }
}

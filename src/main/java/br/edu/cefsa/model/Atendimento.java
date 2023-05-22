/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.model;

import java.time.LocalDate;

/**
 *
 * @author gabri
 */
public class Atendimento extends GenericoModel{
    private Integer atendimentoId;
    private LocalDate dataAtendimento;
    private String anotacoes;
    private int clienteID;
    private int imovelID;
    private Cliente cliente;
    private Imovel imovel;

    public Atendimento(Integer atendimentoId, LocalDate dataAtendimento, String anotacoes, int clienteID, int imovelID) {
        this.atendimentoId = atendimentoId;
        this.dataAtendimento = dataAtendimento;
        this.anotacoes = anotacoes;
        this.clienteID = clienteID;
        this.imovelID = imovelID;
    }
    public Atendimento(LocalDate dataAtendimento, String anotacoes, int clienteID, int imovelID) {
        this.dataAtendimento = dataAtendimento;
        this.anotacoes = anotacoes;
        this.clienteID = clienteID;
        this.imovelID = imovelID;
    }    

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getImovelID() {
        return imovelID;
    }

    public void setImovelID(int imovelID) {
        this.imovelID = imovelID;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }
    
    public Integer getAtendimentoId(){
       return atendimentoId;
    }

    public void setAtendimentoId(Integer atendimentoId) {
        this.atendimentoId = atendimentoId;
    }    
}

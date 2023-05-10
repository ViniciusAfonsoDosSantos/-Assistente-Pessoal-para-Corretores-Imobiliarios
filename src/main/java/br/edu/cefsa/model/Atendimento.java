/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.model;

import java.time.LocalDateTime;

/**
 *
 * @author gabri
 */
public class Atendimento extends GenericoModel{
    private LocalDateTime dataAtendimento;
    private String anotacoes;
    private int clienteID;
    private int imovelID;
    private Cliente cliente;
    private Imovel imovel;

    public Atendimento(LocalDateTime dataAtendimento, String anotacoes, int clienteID, int imovelID) {
        this.dataAtendimento = dataAtendimento;
        this.anotacoes = anotacoes;
        this.clienteID = clienteID;
        this.imovelID = imovelID;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
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
}
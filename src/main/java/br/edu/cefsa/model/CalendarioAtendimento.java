/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.model;

import java.time.LocalDateTime;

/**
 *
 * @author Vinicius
 */
public class CalendarioAtendimento {
    
    private LocalDateTime data;
    private String anotacao;
    private Cliente cliente;
    

    public CalendarioAtendimento(LocalDateTime data, String anotacao, Cliente cliente) {
        this.data = data;
        this.anotacao = anotacao;
        this.cliente = cliente;
    }
    

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    @Override
    public String toString() {
        return "CalendarioAtendimento{" +
                "data=" + data +
                ", cliente='" + cliente + '\'' +
                ", anotação=" + anotacao +
                '}';
    }
}


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
public class Imovel extends GenericoModel {
    private String nome;
    private String tipoImovel;
    private String finalidade;
    private int numDorms;
    private int numVagas;
    private double metragem;
    private String rua;
    private String bairro;
    private String cidade;
    private String CEP;
    private String complemento;
    private String caracteristicas;
    private LocalDate prazoEntrega;
    private String faixaPreco;
    private byte[] imagem1;
    private byte[] imagem2;
    private byte[] imagem3;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    

    public int getNumDorms() {
        return numDorms;
    }

    public void setNumDorms(int numDorms) {
        this.numDorms = numDorms;
    }

    public int getNumVagas() {
        return numVagas;
    }

    public void setNumVagas(int numVagas) {
        this.numVagas = numVagas;
    }

    public double getMetragem() {
        return metragem;
    }

    public Imovel(String nome, String tipoImovel, String finalidade, int numDorms, int numVagas, double metragem, String rua, String bairro, String cidade, String CEP, String complemento, String caracteristicas, LocalDate prazoEntrega, String faixaPreco, byte[] imagem1, byte[] imagem2, byte[] imagem3) {
        this.nome = nome;
        this.tipoImovel = tipoImovel;
        this.finalidade = finalidade;
        this.numDorms = numDorms;
        this.numVagas = numVagas;
        this.metragem = metragem;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.CEP = CEP;
        this.complemento = complemento;
        this.caracteristicas = caracteristicas;
        this.prazoEntrega = prazoEntrega;
        this.faixaPreco = faixaPreco;
        this.imagem1 = imagem1;
        this.imagem2 = imagem2;
        this.imagem3 = imagem3;
    }

    
    public void setMetragem(double metragem) {
        this.metragem = metragem;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public LocalDate getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(LocalDate prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public String getFaixaPreco() {
        return faixaPreco;
    }

    public void setFaixaPreco(String faixaPreco) {
        this.faixaPreco = faixaPreco;
    }

    public byte[] getImagem1() {
        return imagem1;
    }

    public void setImagem1(byte[] imagem1) {
        this.imagem1 = imagem1;
    }

    public byte[] getImagem2() {
        return imagem2;
    }

    public void setImagem2(byte[] imagem2) {
        this.imagem2 = imagem2;
    }

    public byte[] getImagem3() {
        return imagem3;
    }

    public void setImagem3(byte[] imagem3) {
        this.imagem3 = imagem3;
    }

    public Imovel(String nome, String tipoImovel, String finalidade, int numDorms, int numVagas, double metragem, String rua, String bairro, String cidade, String CEP, String complemento, String caracteristicas, LocalDate prazoEntrega, String faixaPreco) {
        this.nome = nome;
        this.tipoImovel = tipoImovel;
        this.finalidade = finalidade;
        this.numDorms = numDorms;
        this.numVagas = numVagas;
        this.metragem = metragem;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.CEP = CEP;
        this.complemento = complemento;
        this.caracteristicas = caracteristicas;
        this.prazoEntrega = prazoEntrega;
        this.faixaPreco = faixaPreco;
    }
    
}

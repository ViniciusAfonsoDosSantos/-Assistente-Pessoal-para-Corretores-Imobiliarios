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
public class Cliente extends GenericoModel{
    public String Nome;
    public String CPF;
    public LocalDateTime DataNascimento; 
    public String Conjuge;
    public String Profissao;
    public String Telefone;
    public String Email;
    public String CEP;
    public String EnderecoResidencial;
    public String Estado; //dado vai vir de um selectbox
    public String Cidade;
    public String Bairro;
    public String Anotacao;

    public String getAnotacao() {
        return Anotacao;
    }

    public void setAnotacao(String Anotacao) {
        this.Anotacao = Anotacao;
    }
    
    //falta dados de anotação da modal.

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public LocalDateTime getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDateTime DataNascimento) {
        this.DataNascimento = DataNascimento;
    }

    public String getConjuge() {
        return Conjuge;
    }

    public void setConjuge(String Conjuge) {
        this.Conjuge = Conjuge;
    }

    public String getProfissao() {
        return Profissao;
    }

    public void setProfissao(String Profissao) {
        this.Profissao = Profissao;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEnderecoResidencial() {
        return EnderecoResidencial;
    }

    public void setEnderecoResidencial(String EnderecoResidencial) {
        this.EnderecoResidencial = EnderecoResidencial;
    }
    
    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

}

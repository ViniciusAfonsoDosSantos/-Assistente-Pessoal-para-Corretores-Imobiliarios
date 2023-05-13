/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.model;

import br.edu.cefsa.enumeradores.EstadoAtendimentoCliente;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author gabri
 */
public class Cliente extends GenericoModel {

    private Integer ClienteId;
    private String Nome;
    private String CPF;
    private LocalDate DataNascimento;
    private String Conjuge;
    private String Profissao;
    private String Telefone;
    private String Email;
    private String CEP;
    private String EnderecoResidencial;
    private String Estado; //dado vai vir de um selectbox
    private String Cidade;
    private String Bairro;
    private ImovelProcuradoCliente imovelProcuradoCliente;
    private EstadoAtendimentoCliente estadoCliente;

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
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

    public LocalDate getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDate DataNascimento) {
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
    
    public Cliente(String Nome) {
        this.Nome = Nome;
    }

    public int getClienteId() {
        return ClienteId;
    }

    public void setClienteId(Integer ClienteId) {
        this.ClienteId = ClienteId;
    }
    
    public ImovelProcuradoCliente getImovelProcuradoCliente() {
        return imovelProcuradoCliente;
    }

    public void setImovelProcuradoCliente(ImovelProcuradoCliente imovelProcuradoCliente) {
        this.imovelProcuradoCliente = imovelProcuradoCliente;
    }
    
    public EstadoAtendimentoCliente getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(EstadoAtendimentoCliente estadoCliente) {
        this.estadoCliente = estadoCliente;
    }
    
    public Cliente(Integer ClienteId, String Nome, String CPF, LocalDate DataNascimento, String Conjuge, String Profissao, String Telefone, String Email, String CEP, String EnderecoResidencial, String Estado, String Cidade, String Bairro, ImovelProcuradoCliente imovelProcuradoCliente) {
        this.ClienteId = ClienteId;
        this.Nome = Nome;
        this.CPF = CPF;
        this.DataNascimento = DataNascimento;
        this.Conjuge = Conjuge;
        this.Profissao = Profissao;
        this.Telefone = Telefone;
        this.Email = Email;
        this.CEP = CEP;
        this.EnderecoResidencial = EnderecoResidencial;
        this.Estado = Estado;
        this.Cidade = Cidade;
        this.Bairro = Bairro;
        this.imovelProcuradoCliente = imovelProcuradoCliente;
    }

    public Cliente(String Nome, String CPF, LocalDate DataNascimento, String Conjuge, String Profissao, String Telefone, String Email, String CEP, String EnderecoResidencial, String Estado, String Cidade, String Bairro, ImovelProcuradoCliente imovelProcuradoCliente) {
        this.Nome = Nome;
        this.CPF = CPF;
        this.DataNascimento = DataNascimento;
        this.Conjuge = Conjuge;
        this.Profissao = Profissao;
        this.Telefone = Telefone;
        this.Email = Email;
        this.CEP = CEP;
        this.EnderecoResidencial = EnderecoResidencial;
        this.Estado = Estado;
        this.Cidade = Cidade;
        this.Bairro = Bairro;
        this.imovelProcuradoCliente = imovelProcuradoCliente;
    }

    @Override
    public String toString() {
        return this.Nome;
    }
}

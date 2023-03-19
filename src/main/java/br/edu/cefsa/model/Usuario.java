/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.model;

/**
 *
 * @author gabri
 */
public class Usuario {
    public int ID;
    public String Nome;
    public String Email;
    public String Senha;

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSenha() {
        return Senha;
    }
    
    public int getCodigo(){
        return ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public Usuario(String Nome, String Email, String Senha) {
        this.Nome = Nome;
        this.Email = Email;
        this.Senha = Senha;
    }
}

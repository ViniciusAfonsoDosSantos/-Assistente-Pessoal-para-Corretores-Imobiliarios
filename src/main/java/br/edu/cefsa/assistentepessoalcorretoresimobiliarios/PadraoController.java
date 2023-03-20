/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author Vinicius
 */
public class PadraoController{
    
    DadoUsuarioLogado usuarioLogado = DadoUsuarioLogado.getInstancia();
    
    @FXML
    private void redirecionarCadastrarCliente() throws IOException {
        App.setRoot("telaCadastroCliente");
    }
    
    @FXML
    private void redirecionarTelaPrincipal() throws IOException {
        App.setRoot("telaPrincipal");
    }
    
    @FXML
    private void redirecionarLogin() throws IOException {
        App.setRoot("telaLogin");
    }
    
    @FXML
    private void redirecionarExpandirCliente() throws IOException {
        App.setRoot("telaExpansaoCliente");
    }
    
    @FXML
    private void redirecionarOpcoesUsuario() throws IOException {
        App.setRoot("telaUsuario");
    }
    
    @FXML
    private void redirecionarAdministrador() throws IOException {
        App.setRoot("telaAdministrador");
    }
    
    @FXML
    private void logOff() throws IOException {
        
        usuarioLogado.setUsuario(null);
        App.setRoot("telaLogin");
    }
    
}

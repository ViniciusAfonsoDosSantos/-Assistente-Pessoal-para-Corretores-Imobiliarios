/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.shape.Circle;

/**
 *
 * @author Vinicius
 */
public class TelaPrincipalController {
    
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
    private void redirecionarOpcoesCliente() throws IOException {
        App.setRoot("telaUsuario");
    }
}

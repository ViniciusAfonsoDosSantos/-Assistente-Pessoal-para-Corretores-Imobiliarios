/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class TelaUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void redirecionarCadastrarCliente() throws IOException {
        App.setRoot("telaCadastroCliente");
    }
    
    @FXML
    private void redirecionarTelaPrincipal() throws IOException {
        App.setRoot("telaPrincipal");
    }
    
    @FXML
    private void redirecionarOpcoesCliente() throws IOException {
        App.setRoot("telaUsuario");
    }
}

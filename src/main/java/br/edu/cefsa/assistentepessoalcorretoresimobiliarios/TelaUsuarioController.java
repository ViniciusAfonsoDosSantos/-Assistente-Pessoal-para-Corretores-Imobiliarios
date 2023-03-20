/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class TelaUsuarioController extends PadraoController  implements Initializable{

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtSenha;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtNome.setText(usuarioLogado.getUsuario().Nome);
        txtEmail.setText(usuarioLogado.getUsuario().Email);
        txtSenha.setText(usuarioLogado.getUsuario().Senha);
    }
    
}

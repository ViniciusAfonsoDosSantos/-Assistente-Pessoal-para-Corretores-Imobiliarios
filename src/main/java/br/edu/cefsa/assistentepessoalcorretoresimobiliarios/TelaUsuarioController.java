/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
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
    
    private DialogPane dialog;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtNome.setText(usuarioLogado.getUsuario().Nome);
        txtEmail.setText(usuarioLogado.getUsuario().Email);
        txtSenha.setText(usuarioLogado.getUsuario().Senha);
    }
    
    @FXML
    private void deletarUsuario(ActionEvent actionEvent){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deletar Usuário");
        alert.setContentText("Confirma a exclusão do usuário?");
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
        dialog.getStyleClass().add("dialog");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            
            //COLOCAR PARTE DE DELETAR AQUI!
            
            System.out.println("OKKKKKKKK");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.UsuarioDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Usuario;
import utils.ValidaDadosUtils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.ComponentesUtils;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class TelaUsuarioController extends PadraoController {

    @FXML
    private FontAwesomeIconView iconeADM;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private DialogPane dialog;
    
    @FXML
    private Label lbErroNomeUsuario; 
    
    @FXML
    private Label lbErroSenha; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iconeADM.setVisible(false);
        if (usuarioLogado.getUsuario().getTipo() == true) {
            iconeADM.setVisible(true);
        }

        txtNome.setText(usuarioLogado.getUsuario().getNome());
        txtEmail.setText(usuarioLogado.getUsuario().getEmail());
        txtSenha.setText(usuarioLogado.getUsuario().getSenha());
    }

    //unica responsabilidade deve ser a de deletar usuario
    @FXML
    private void deletarUsuario(ActionEvent actionEvent) throws PersistenciaException, IOException {
        Alert alert = ComponentesUtils.criaAlerta(AlertType.CONFIRMATION, "Deletar Usuário", "Confirma a exclusão do usuário?");
        atribuiDialog(alert, "estiloPrincipal.css", "dialog");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            UsuarioDAO dao = new UsuarioDAO();
            dao.remover(usuarioLogado.getUsuario());
            usuarioLogado.setUsuario(null);
            App.setRoot("telaLogin");
            System.out.println("OKKKKKKKK");
        }
    }

    private void atribuiDialog(Alert alert, String stylesheet, String styleClass){
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource(stylesheet).toString());
        dialog.getStyleClass().add(styleClass);
    }
    
    @FXML
    private void editarUsuario(ActionEvent actionEvent) throws PersistenciaException, IOException {
        
        apagaErros();
        if(!validaDadosUsuario()){
            return;
        }
        Alert alert = ComponentesUtils.criaAlerta(AlertType.CONFIRMATION, "Confirma as alterações?", "Confirma as alterações?");
        atribuiDialog(alert, "estiloPrincipal.css", "dialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {    
            UsuarioDAO dao = new UsuarioDAO();
            Usuario user = new Usuario(txtNome.getText(),txtEmail.getText(),txtSenha.getText(),usuarioLogado.getUsuario().getTipo());
            dao.alterar(user);
        }
    }
    
    private boolean validaDadosUsuario(){
        int status = 0;
        if (!ValidaDadosUtils.ValidaTexto(txtNome.getText())) {
            lbErroNomeUsuario.setText("Nome de usuário vazio");
            status++;
        }

        if (!ValidaDadosUtils.ValidaTexto(txtSenha.getText())) {
            lbErroSenha.setText("Senha vazia");
            status++;
        }
        
        //Utils para validarEmail
        //Util para criar alerta (recebe o Title e ContentText)
        if (!ValidaDadosUtils.VerificaEmail(txtEmail.getText())) {
            Alert alert = ComponentesUtils.criaAlerta(Alert.AlertType.ERROR, "Email no formato incorreto", "");
            alert.showAndWait();
            status++;
        }
        return (status == 0); 
    }
    
    private void apagaErros(){
        lbErroSenha.setText("");
        lbErroNomeUsuario.setText(""); 
    }

}

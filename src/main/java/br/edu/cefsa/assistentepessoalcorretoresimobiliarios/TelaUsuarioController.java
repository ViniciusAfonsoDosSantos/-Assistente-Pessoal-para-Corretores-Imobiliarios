/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.UsuarioDAO;
import static br.edu.cefsa.assistentepessoalcorretoresimobiliarios.TelaLoginController.VALID_EMAIL_ADDRESS_REGEX;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Usuario;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

        txtNome.setText(usuarioLogado.getUsuario().Nome);
        txtEmail.setText(usuarioLogado.getUsuario().Email);
        txtSenha.setText(usuarioLogado.getUsuario().Senha);
    }

    @FXML
    private void deletarUsuario(ActionEvent actionEvent) throws PersistenciaException, IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deletar Usuário");
        alert.setContentText("Confirma a exclusão do usuário?");
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
        dialog.getStyleClass().add("dialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            UsuarioDAO dao = new UsuarioDAO();
            dao.remover(usuarioLogado.getUsuario());

            usuarioLogado.setUsuario(null);
            App.setRoot("telaLogin");

            System.out.println("OKKKKKKKK");
        }
    }

    @FXML
    private void editarUsuario(ActionEvent actionEvent) throws PersistenciaException, IOException {
        
        apagaErros();
        if (txtNome.getText().equals("")) {
            lbErroNomeUsuario.setText("Nome de usuário vazio");
        }

        if (txtSenha.getText().equals("")) {
            lbErroSenha.setText("Senha vazia");
        }

        if (txtNome.getText().equals("") || txtSenha.getText().equals("")) {
            return;
        }
        
        if (validateEmailRegex(txtEmail.getText()) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email no formato incorreto");
            alert.showAndWait();
            return;
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alterar Usuário");
        alert.setContentText("Confirma as alterações?");
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
        dialog.getStyleClass().add("dialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            
            UsuarioDAO dao = new UsuarioDAO();
            Usuario user = new Usuario(txtNome.getText(),txtEmail.getText(),txtSenha.getText(),usuarioLogado.getUsuario().getTipo());
            dao.alterar(user);
           
        }
        
    }
    
    public static boolean validateEmailRegex(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
    
    public void apagaErros(){
        lbErroSenha.setText("");
        lbErroNomeUsuario.setText(""); 
    }
}

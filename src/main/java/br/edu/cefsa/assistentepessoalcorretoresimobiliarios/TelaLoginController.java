/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.UsuarioDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Usuario;
import utils.ValidaDadosUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.util.Duration;
import utils.ComponentesUtils;
import utils.PasswordUtils;

/**
 *
 * @author Vinicius
 */
public class TelaLoginController implements Initializable {

    
    @FXML
    private AnchorPane camada1;

    @FXML
    private AnchorPane camada2;

    @FXML
    private Label lbLogo;

    @FXML
    private Label lbTexto1;

    @FXML
    private Label lbTexto2;

    @FXML
    private Label lbTexto3;

    @FXML
    private Label lbTexto4;

    @FXML
    private Label lbCadastrese;

    @FXML
    private Label lbLogin;

    @FXML
    private Button btnEntrarLogin;

    @FXML
    private Button btnRedirecionarLogin;

    @FXML
    private Button btnRedirecionarCadastro;

    @FXML
    private Button btnCadastrese;

    @FXML
    private TextField txtNomeUsuario;

    @FXML
    private TextField txtSenhaLogin;

    @FXML
    private TextField txtSenhaCadastrese;

    @FXML
    private TextField txtEmail;

    @FXML
    private FontAwesomeIconView iconUsuarios;

    @FXML
    private FontAwesomeIconView iconUsuario;

    @FXML
    private FontAwesomeIconView iconEmail;

    @FXML
    private FontAwesomeIconView iconChaveLogin;

    @FXML
    private FontAwesomeIconView iconChaveCadastrese;

    @FXML
    private Label lbErroLogin;

    @FXML
    private Label lbErroEmail;

    @FXML
    private Label lbErroNomeUsuario;

    @FXML
    private Label lbErroSenha;

    private DialogPane dialog;

    DadoUsuarioLogado usuarioLogado = DadoUsuarioLogado.getInstancia();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtSenhaCadastrese.setVisible(false);
        btnRedirecionarLogin.setVisible(false);
        btnCadastrese.setVisible(false);
        txtNomeUsuario.setVisible(false);
        lbCadastrese.setVisible(false);
        iconUsuario.setVisible(false);
        iconChaveCadastrese.setVisible(false);
        lbTexto4.setVisible(false);
    }

    @FXML
    private void redirecionarCadastro() {
        apagaErros();

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(camada2);

        slide.setToX(507);
        slide.play();

        camada1.setTranslateX(-507);
        /*
        slide.setOnFinished((e->{
        
        }));
         */

        lbTexto4.setVisible(true);
        txtSenhaCadastrese.setVisible(true);
        btnRedirecionarLogin.setVisible(true);
        btnCadastrese.setVisible(true);
        txtNomeUsuario.setVisible(true);
        lbCadastrese.setVisible(true);
        iconUsuario.setVisible(true);
        iconChaveCadastrese.setVisible(true);
        lbLogin.setVisible(false);
        btnRedirecionarCadastro.setVisible(false);
        btnEntrarLogin.setVisible(false);
        txtSenhaLogin.setVisible(false);
        iconChaveLogin.setVisible(false);
        lbTexto3.setVisible(false);
    }

    @FXML
    private void redirecionarLogin() {
        apagaErros();

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(camada2);

        slide.setToX(0);
        slide.play();

        camada1.setTranslateX(0);

        /*
        slide.setOnFinished((e->{
        
        }));
         */
        lbTexto4.setVisible(false);
        txtSenhaCadastrese.setVisible(false);
        btnRedirecionarLogin.setVisible(false);
        btnCadastrese.setVisible(false);
        txtNomeUsuario.setVisible(false);
        lbCadastrese.setVisible(false);
        iconUsuario.setVisible(false);
        iconChaveCadastrese.setVisible(false);
        lbLogin.setVisible(true);
        btnRedirecionarCadastro.setVisible(true);
        btnEntrarLogin.setVisible(true);
        txtSenhaLogin.setVisible(true);
        iconChaveLogin.setVisible(true);
        lbTexto3.setVisible(true);
    }

    @FXML
    private void redirecionarTelaPrincipal() throws IOException {
        App.setRoot("telaPrincipal");
    }

    @FXML
    private void salvarUsuario() throws IOException, SQLException, PersistenciaException, ParseException {
        apagaErros();

        UsuarioDAO DAO = new UsuarioDAO();
        String nomeUser = txtNomeUsuario.getText();
        String senhaUserHasheada = PasswordUtils.encryptPassword(txtSenhaCadastrese.getText());
        
        String emailUser = txtEmail.getText();
        Usuario user = new Usuario(nomeUser, emailUser, senhaUserHasheada, false);
        //checar se email já existe.
        //verificar formato email
        //hashear senha
        if(!validaDadosUsuario())
            return;
        try {
            if (buscaUsuarioEmail(emailUser, DAO) == null) {
                DAO.inserir(user);
                redirecionarLogin();
                txtEmail.clear();
                txtSenhaCadastrese.clear();
            } else {
                
                Alert alert = ComponentesUtils.criaAlerta(Alert.AlertType.ERROR, "Erro!", "Usuário Existente!");
                atribuiDialog(alert, "estiloPrincipal.css", "dialog");
                alert.showAndWait();
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atribuiDialog(Alert alert, String stylesheet, String styleClass){
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource(stylesheet).toString());
        dialog.getStyleClass().add(styleClass);
    }
    
    private boolean validaDadosUsuario(){
        int status = 0;
        
        if (!ValidaDadosUtils.ValidaTexto(txtNomeUsuario.getText())) {
            lbErroNomeUsuario.setText("Dado não Preenchido");
            status++;
        }

        if (!ValidaDadosUtils.ValidaTexto(txtSenhaCadastrese.getText())) {
            lbErroSenha.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtEmail.getText())) {
            lbErroSenha.setText("Dado não Preenchido");
            status++;
        }
        
        //Utils para validarEmail
        //Util para criar alerta (recebe o Title e ContentText)
        if (!ValidaDadosUtils.VerificaEmail(txtEmail.getText())) {
            lbErroEmail.setText("Email invalido");
            status++;
        }
        return (status == 0); 
    }

    @FXML
    private void realizarLogin() throws IOException, SQLException {
        apagaErros();
        UsuarioDAO DAO = new UsuarioDAO();
        String emailUser = txtEmail.getText();
        String senhaUser = txtSenhaLogin.getText();
        Usuario user = new Usuario("", emailUser, senhaUser, false);
        //verificar formato email
        if (!ValidaDadosUtils.ValidaTexto(emailUser) || !ValidaDadosUtils.ValidaTexto(senhaUser)) {
            lbErroLogin.setText("Usuário ou senha invalidos!");
            return;
        }

        try {
            Usuario userBanco = buscaUsuarioEmail(emailUser, DAO);
            if (userBanco != null) { //existe user com email
                //ver se senha e email batem
                if (verificaLogin(user, userBanco)) {
                    usuarioLogado.setUsuario(userBanco);
                    redirecionarTelaPrincipal();
                } else {
                    lbErroLogin.setText("Usuário ou senha invalidos!");
                }

                txtEmail.clear();
                txtSenhaLogin.clear();
            } else {
                lbErroLogin.setText("Usuário ou senha invalidos!");
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Usuario buscaUsuarioEmail(String email, UsuarioDAO dao) throws PersistenciaException, SQLException {
        Usuario usuario = dao.listaPorEmail(email);
        if (usuario == null) //não existe nenhum email com esse nome 
        {
            return null;
        } else {
            return usuario; //existe um usuario com esse email
        }
    }

    private boolean verificaLogin(Usuario user, Usuario userBanco) {
        String senhaUserHash = PasswordUtils.encryptPassword(user.getSenha());
        if (user.getEmail().equals(userBanco.getEmail()) && senhaUserHash.equals(userBanco.getSenha())) {
            return true;
        } else {
            return false;
        }
    }

    public void apagaErros() {
        lbErroLogin.setText("");
        lbErroSenha.setText("");
        lbErroNomeUsuario.setText("");
        lbErroEmail.setText("");
    }

}

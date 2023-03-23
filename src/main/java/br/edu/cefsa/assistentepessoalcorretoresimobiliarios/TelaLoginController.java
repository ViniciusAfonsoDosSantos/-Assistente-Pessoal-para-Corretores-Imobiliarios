/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.UsuarioDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

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
    private void redirecionarCadastro(){
        
        
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(camada2);
        
        slide.setToX(390);
        slide.play();
        
        camada1.setTranslateX(-390);
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
    private void redirecionarLogin(){
        
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
    private void salvarUsuario() throws IOException{
        UsuarioDAO DAO = new UsuarioDAO();
       
        String nomeUser = txtNomeUsuario.getText();
        String senhaUser = txtSenhaCadastrese.getText();
        String emailUser = txtEmail.getText();
        Usuario user = new Usuario(nomeUser, senhaUser, emailUser, false);
        //checar se email já existe.
        //verificar formato email
        //hashear senha
        try {
            if(buscaUsuarioEmail(emailUser, DAO) == null){
                DAO.inserir(user);
                redirecionarLogin();
                txtEmail.clear();
                txtSenhaCadastrese.clear();
            }
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void realizarLogin() throws IOException{
        UsuarioDAO DAO = new UsuarioDAO();
       
        String emailUser = txtEmail.getText();
        String senhaUser = txtSenhaLogin.getText();
        Usuario user = new Usuario("", senhaUser, emailUser, false);
        //verificar formato email
        try {
            Usuario userBanco = buscaUsuarioEmail(emailUser, DAO);
            if(userBanco != null){ //existe user com email
                //ver se senha e email batem
                if(verificaLogin(user, userBanco)){
                    usuarioLogado.setUsuario(userBanco);
                    redirecionarTelaPrincipal();
                }
                txtEmail.clear();
                txtSenhaCadastrese.clear();
            }
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Usuario buscaUsuarioEmail(String email, UsuarioDAO dao) throws PersistenciaException {
        Usuario usuario = dao.listaPorEmail(email);
        if(usuario == null) //não existe nenhum email com esse nome 
            return null;
        else
            return usuario; //existe um usuario com esse email
    }
    
    private boolean verificaLogin(Usuario user, Usuario userBanco){
        if(user.getEmail().equals(userBanco.getEmail()) && user.getSenha().equals(userBanco.getSenha())){
            return true;
        }
        else
            return false;
    }
}

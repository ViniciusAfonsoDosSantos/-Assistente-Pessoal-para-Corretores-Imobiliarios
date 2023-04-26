/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Vinicius
 */
public class PadraoController  implements Initializable{
    
    @FXML
    private FontAwesomeIconView iconeADM;
    
    DadoUsuarioLogado usuarioLogado = DadoUsuarioLogado.getInstancia();
    DadoClienteSelecionado clienteSelecionado = DadoClienteSelecionado.getInstancia();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        iconeADM.setVisible(false);
        if(usuarioLogado.getUsuario().getTipo() == true){
            iconeADM.setVisible(true);
        }
    }
    
    @FXML
    private void redirecionarAdicionarImovel() throws IOException {
        App.setRoot("telaAdicionarImovel");
    }
    
    @FXML
    private void redirecionarCadastrarCliente() throws IOException {
        clienteSelecionado.setCliente(null);
        App.setRoot("telaCadastroCliente");
    }
    
    @FXML
    protected void redirecionarTelaPrincipal() throws IOException {
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

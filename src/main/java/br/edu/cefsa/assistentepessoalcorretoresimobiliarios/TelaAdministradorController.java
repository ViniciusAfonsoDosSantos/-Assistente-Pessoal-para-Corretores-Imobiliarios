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
public class TelaAdministradorController extends PadraoController {

    /**
     * Initializes the controller class.
     */
    /*
    @FXML
    private VBox sideBarVBox;
    
    @FXML
    private FontAwesomeIconView iconeADM;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(1 == 1){
            iconeADM.setVisible(false);
        }
    }
    
    @FXML
    private void teste() throws IOException {

        // iconeADM.setVisible(false);
        
        FontAwesomeIconView iconeADM = new FontAwesomeIconView();
        iconeADM.setGlyphName("USER_CIRCLE");
        iconeADM.setSize("1.5em");
        iconeADM.setStyleClass("icones");
        //Adicionar margin no icone;
        iconeADM.setOnMouseClicked(e -> redirecionarAdministrador());
        sideBarVBox.getChildren().add(iconeADM);
        
    }
     */
    
    @FXML
    private void redirecionarCadastrarCliente() throws IOException {
        App.setRoot("telaCadastroCliente");
    }
    
    @FXML
    private void redirecionarTelaPrincipal() throws IOException {
        App.setRoot("telaPrincipal");
    }
    
     @FXML
    private void redirecionarOpcoesUsuario() throws IOException {
        App.setRoot("telaUsuario");
    }
    
    @FXML
    private void redirecionarAdministrador() throws IOException {
        App.setRoot("telaAdministrador");
    }
}

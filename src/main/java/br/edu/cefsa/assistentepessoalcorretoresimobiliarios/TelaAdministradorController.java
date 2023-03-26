/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.UsuarioDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class TelaAdministradorController extends PadraoController {

    private List<Usuario> userLIst;

    /**
     * Initializes the controller class.
     */
    /*
    @FXML
    private VBox sideBarVBox;
    
    @FXML
    private FontAwesomeIconView iconeADM;
    
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
    private TableView tabelaUsuarios;
    
    @FXML 
    private TableColumn columnNome;
    
    @FXML 
    private TableColumn columnEmail;
        
    @FXML 
    private TableColumn columnAcoes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> userList = new ArrayList<Usuario>();
        try {
            userList = dao.listar();
            columnNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Email"));
            ObservableList ObList = FXCollections.observableList(userList);
            tabelaUsuarios.setItems(ObList);
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

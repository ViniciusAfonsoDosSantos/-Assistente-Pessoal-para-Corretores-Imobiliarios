/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.ClienteDAO;
import br.edu.cefsa.DAO.UsuarioDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cliente;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class ClienteBoxController {

    private DialogPane dialog;

    @FXML
    private AnchorPane clienteBox;

    @FXML
    private Label emailCliente;

    @FXML
    private Label enderecoCliente;

    @FXML
    private FontAwesomeIconView iconeEditar;

    @FXML
    private FontAwesomeIconView iconeRemover;

    @FXML
    private Label nomeCliente;

    @FXML
    private Label telefoneCliente;

    DadoClienteSelecionado clienteSelecionado = DadoClienteSelecionado.getInstancia();

    public void setData(Cliente cliente) throws IOException {

        nomeCliente.setText(cliente.getNome());
        enderecoCliente.setText("Endereço: " + cliente.getEnderecoResidencial());
        telefoneCliente.setText("Telefone: " + cliente.getTelefone());
        emailCliente.setText("Email: " + cliente.getEmail());
        iconeEditar.setOnMouseClicked((MouseEvent event) -> {

            try {
                clienteSelecionado.setCliente(cliente);
                App.setRoot("telaCadastroCliente");
            } catch (IOException ex) {
                Logger.getLogger(ClienteBoxController.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            
            try {
                stage.setUserData(cliente);
                App.setRoot("telaCadastroCliente");
            } catch (IOException e) {
                Logger.getLogger(ClienteBoxController.class.getName()).log(Level.SEVERE, null, e);
            }
             */
        });
                iconeRemover.setOnMouseClicked((MouseEvent event) -> {

            try {
                deletarCliente(cliente);
            } catch (IOException | PersistenciaException ex) {
                Logger.getLogger(ClienteBoxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void deletarCliente(Cliente cliente) throws PersistenciaException, IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletar Cliente");
        alert.setContentText("Confirma a exclusão do Cliente?");
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
        dialog.getStyleClass().add("dialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ClienteDAO dao = new ClienteDAO();
            dao.remover(cliente);
            //FXMLLoader fxmlLoader = new FXMLLoader();
            //fxmlLoader.setLocation(getClass().getResource("telaPrincipal.fxml"));
            System.out.println("cliente => " + cliente);
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.Cliente;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class ClienteBoxController {

    @FXML
    private AnchorPane clienteBox;

    @FXML
    private Label emailCliente;

    @FXML
    private Label enderecoCliente;

    @FXML
    private FontAwesomeIconView iconeVisualizar;

    @FXML
    private FontAwesomeIconView iconeEditar;

    @FXML
    private FontAwesomeIconView iconeRemover;

    @FXML
    private Label nomeCliente;

    @FXML
    private Label telefoneCliente;

    public void setData(Cliente cliente) throws IOException {

       
            iconeVisualizar.setOnMouseClicked((MouseEvent event) -> {
                
                try {
                    App.setRoot("telaExpansaoCliente");
                } catch (IOException ex) {
                    Logger.getLogger(ClienteBoxController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            nomeCliente.setText(cliente.getNome());
            enderecoCliente.setText("Endereço: " + cliente.getEnderecoResidencial());
            telefoneCliente.setText("Telefone: " + cliente.getTelefone());
            emailCliente.setText("Email: " + cliente.getEmail());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.ClienteDAO;
import br.edu.cefsa.DAO.ImovelDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cliente;
import br.edu.cefsa.model.Imovel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class ImovelBoxController {

    /**
     * Initializes the controller class.
     */
    private DialogPane dialog;
     
    @FXML
    private Label bairroImovel;

   

    @FXML
    private Label cidadeImovel;

    @FXML
    private FontAwesomeIconView iconeEditar;

    @FXML
    private FontAwesomeIconView iconeRemover;

    @FXML
    private Label nomeImovel;

    @FXML
    private Label ruaImovel;
    
    @FXML
    private AnchorPane imovelBoxx;

    ImageView imgRetrato;
    
    @FXML
    private Label lbPreco;

    DadoImovelSelecionado imovelSelecionado = DadoImovelSelecionado.getInstancia();

    public void setData(Imovel imovel) throws IOException {

        if (imovel.getImagem1() != null) {
            InputStream inputStream = new ByteArrayInputStream(imovel.getImagem1());
            imgRetrato = new ImageView();
            imgRetrato.setLayoutX(226);
            imgRetrato.setLayoutY(50);
            imgRetrato.setImage(new Image(inputStream));
            imgRetrato.setFitHeight(140);
            imgRetrato.setFitWidth(190);
            imgRetrato.setPreserveRatio(true);
            imovelBoxx.getChildren().add(imgRetrato);
        }
        nomeImovel.setText(imovel.getNome());
        ruaImovel.setText("Endereço: " + imovel.getRua());
        cidadeImovel.setText("Telefone: " + imovel.getCidade());
        bairroImovel.setText("Email: " + imovel.getBairro());
        lbPreco.setText("R$: " + imovel.getFaixaPreco());
        iconeEditar.setOnMouseClicked((MouseEvent event) -> {

            try {
                imovelSelecionado.setImovel(imovel);
                App.setRoot("telaAdicionarImovel");
            } catch (IOException ex) {
                Logger.getLogger(ClienteBoxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
                iconeRemover.setOnMouseClicked((MouseEvent event) -> {

            try {
                deletarCliente(imovel);
            } catch (IOException | PersistenciaException ex) {
                Logger.getLogger(ClienteBoxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
    }
    
    public void deletarCliente(Imovel imovel) throws PersistenciaException, IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletar Imovel");
        alert.setContentText("Confirma a exclusão do Imovel?");
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
        dialog.getStyleClass().add("dialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ImovelDAO dao = new ImovelDAO();
            dao.remover(imovel);
            App.setRoot("telaListarImoveis");
            System.out.println("cliente => " + imovel);
        }
    }

}

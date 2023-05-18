/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.Imovel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class ImovelBoxController {

    /**
     * Initializes the controller class.
     */
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
    private ImageView imgRetrato;

    DadoImovelSelecionado imovelSelecionado = DadoImovelSelecionado.getInstancia();

    public void setData(Imovel imovel) throws IOException {

        if (imovel.getImagem1() != null) {
            InputStream inputStream = new ByteArrayInputStream(imovel.getImagem1());
            imgRetrato.setImage(new Image(inputStream));
        }
        nomeImovel.setText(imovel.getNome());
        ruaImovel.setText("Endereço: " + imovel.getRua());
        cidadeImovel.setText("Telefone: " + imovel.getCidade());
        bairroImovel.setText("Email: " + imovel.getBairro());
        iconeEditar.setOnMouseClicked((MouseEvent event) -> {

            try {
                imovelSelecionado.setImovel(imovel);
                App.setRoot("telaAdicionarImovel");
            } catch (IOException ex) {
                Logger.getLogger(ClienteBoxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /*
                iconeRemover.setOnMouseClicked((MouseEvent event) -> {

            try {
                deletarCliente(cliente);
            } catch (IOException | PersistenciaException ex) {
                Logger.getLogger(ClienteBoxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         */
    }

}
